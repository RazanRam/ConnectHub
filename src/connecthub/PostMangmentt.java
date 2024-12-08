/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class PostMangmentt extends ContentCreation {
   

    public PostMangmentt() {
        
    }
    
            private static final String FILE_NAME ="newposts.json";
    public void addPostinfile() {
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
            JSONObject Dataofposts = new JSONObject(jsonfilecontent.toString());

            // Get the user's posts array or create a new one if it doesn't exist
            JSONArray userarrayofposts = Dataofposts.optJSONArray(getAuthorid());
            if (userarrayofposts == null) {
                userarrayofposts = new JSONArray();
                Dataofposts.put(getAuthorid(), userarrayofposts);
            }

            // Create a new post and add it to the array
            JSONObject newpost = new JSONObject();
            newpost.put("postid", System.currentTimeMillis());
            newpost.put("content", getContent());
            newpost.put("timestamp", System.currentTimeMillis());
            if (getImagepath() != null && !getImagepath().isEmpty()) {
                newpost.put("imagePath", getImagepath());
            }
            userarrayofposts.put(newpost);

            // Write the updated data back to the file
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(Dataofposts.toString(4));
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(PostMangmentt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public JSONArray getpostbyuserid(String userid){
            try {
        FileReader file = new FileReader(FILE_NAME);
        StringBuilder jsonContent = new StringBuilder();
        int i;
        while ((i = file.read()) != -1) {
            jsonContent.append((char) i);
        }
        file.close();
        
        JSONObject postsData = new JSONObject(jsonContent.toString());
        return postsData.optJSONArray(userid); // Returns posts for the specific user
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
            
        }
        
    }

