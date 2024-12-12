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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class GroupDatabase {

    public static GroupDatabase gdb = null;

    private GroupDatabase() {
    }

    public static GroupDatabase getinstance() {
        if (gdb == null) {

            gdb = new GroupDatabase();
        }
        return gdb;
    }

    public ArrayList<JSONObject> getfromfile(String filename) {
        ArrayList<JSONObject> jsonObjectList = new ArrayList<>();
        File file = new File(filename);
        if (file.exists() && file.length() > 0) {
            FileReader FR;
            try {
                FR = new FileReader(filename);
                BufferedReader reader = new BufferedReader(FR);
                StringBuilder jsonstring = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    jsonstring.append(line);
                }
                JSONArray jsonarray = new JSONArray(jsonstring.toString());
                for (int i = 0; i < jsonarray.length(); i++) {
                    jsonObjectList.add(jsonarray.getJSONObject(i));
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(GroupDatabase.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(GroupDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return jsonObjectList;
    }

    public void writeinfile(String filename, ArrayList<JSONObject> array) {

        File f = new File(filename);

        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            BufferedWriter BW = new BufferedWriter(fw);
            JSONArray jsonArray = new JSONArray(array);
            BW.write(jsonArray.toString(4));
            BW.close();
        } catch (IOException ex) {
            Logger.getLogger(GroupDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
