/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */

public class StoryManagment extends ContentCreation {

    public StoryManagment() {
    }
    
            private static final String FILE_NAME ="newstories.json";
    public void addstoryinfile() {
        //System.out.println("Author ID: " + getAuthorid());
         //System.out.println("Author ID: " + userid);
        

        try {
            // Ensure the file exists; if not, create it with an empty JSON object
            File file = new File(FILE_NAME);
            if (!file.exists()|| file.length() == 0) {
                //file.createNewFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("{}"); // Initialize with an empty JSON object
                }
            }

            // Read the current contents of the file
            FileReader fr = new FileReader(FILE_NAME);
            StringBuilder jsonfilecontent = new StringBuilder();
            int i;
            while ((i = fr.read()) != -1) {
                jsonfilecontent.append((char) i);
            }
            fr.close();

            // Parse the JSON data
            JSONObject Dataofstories= new JSONObject(jsonfilecontent.toString());

            // Get the user's posts array or create a new one if it doesn't exist
            JSONArray userarrayofstories = Dataofstories.optJSONArray(getAuthorid());
            if (userarrayofstories == null) {
                userarrayofstories = new JSONArray();
                Dataofstories.put(getAuthorid(), userarrayofstories);
            }

            // Create a new post and add it to the array
            JSONObject newstory = new JSONObject();
            newstory.put("storyid", System.currentTimeMillis());
            newstory.put("content", getContent());
            newstory.put("timestamp", System.currentTimeMillis());
            if (getImagepath() != null && !getImagepath().isEmpty()) {
                newstory.put("imagePath", getImagepath());
            }
            userarrayofstories.put(newstory);

            // Write the updated data back to the file
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(Dataofstories.toString(4));
            writer.close();

        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
        public JSONArray getstorytbyuserid(String userid){
            try {
        FileReader file = new FileReader(FILE_NAME);
        StringBuilder jsonContent = new StringBuilder();
        int i;
        while ((i = file.read()) != -1) {
            jsonContent.append((char) i);
        }
        file.close();
        
        JSONObject storiesData = new JSONObject(jsonContent.toString());
        return storiesData.optJSONArray(userid); // Returns stories for the specific user
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
            
        }}
