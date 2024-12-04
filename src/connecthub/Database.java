/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;

/**
 *
 * @author Raz_RAMADAN
 */
public abstract class Database {
     
    public JSONArray loadDatabase(String filename) {
         File file = new File(filename);
        JSONArray JsonArray=new JSONArray();
         if (file.exists()) 
         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonString = new StringBuilder();

              String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }

                // Parse the JSON array
                JSONArray jsonArray = new JSONArray(jsonString.toString());
                JsonArray=jsonArray;
    reader.close();
}        catch (FileNotFoundException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
         }
         return JsonArray;
    }

  public void saveDatabase() {   
  }



}
