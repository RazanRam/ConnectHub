/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author janaf
 */
public class friendDataBase {
    public static friendDataBase fdb=null;

    private friendDataBase() {
    }
    
    public static friendDataBase getInstance(){
        if(fdb==null){
            fdb=new friendDataBase();
        }
        return fdb;
    }

    public ArrayList<friendship> LoadDATAbase(String filename){
        ArrayList<friendship> array=new ArrayList<>();
        File file = new File(filename);
        JSONArray frArr=new JSONArray();
         if (file.exists()) 
         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonString = new StringBuilder();

              String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }

                // Parse the JSON array
                JSONArray jsonArray = new JSONArray(jsonString.toString());
                frArr=jsonArray;
                reader.close();
}        catch (FileNotFoundException ex) {
             Logger.getLogger(friendDataBase.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(friendDataBase.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        for(int i=0;i<frArr.length();i++){
            JSONObject fObj=frArr.getJSONObject(i);
            String u1=fObj.getString("UserID1");
            String u2=fObj.getString("UserID2");
            friendship f=new friendship(u1, u2);
            array.add(f);
        }
        
        return array;
    }

    public void saveDatabase(String filename,ArrayList<friendship> array){
        JSONArray frArr=new JSONArray();
        for(friendship f:array){
            JSONObject fObj=new JSONObject();
            fObj.put("UserID1", f.getUserID1());
            fObj.put("UserID2", f.getUserID2());
            frArr.put(fObj);
        }
        
        File f=new File(filename);
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
                BufferedWriter BW = new BufferedWriter(fw);
                
                BW.write(frArr.toString(2));
                BW.flush();
                BW.close();
            
        } catch (IOException ex) {
            Logger.getLogger(friendDataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
