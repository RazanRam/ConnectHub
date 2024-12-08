/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

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
public class PostMangmentt {
    private static final String FILE_NAME ="posts.json";
    public void addPostinfile(String userid,String content,String imagepath){
        try {
            FileReader fr=new FileReader(FILE_NAME);
            StringBuilder jsonfilecontent =new StringBuilder();
            int i; 
            while((i=fr.read())!=-1){
                jsonfilecontent.append((char)i);
            }
            fr.close();
            JSONObject Dataofposts =new JSONObject(jsonfilecontent.toString());
            JSONArray userarrayofposts=Dataofposts.optJSONArray(userid);
            if(userarrayofposts==null){
                userarrayofposts=new JSONArray();
                Dataofposts.put(userid, userarrayofposts);
            }
            JSONObject newpost=new JSONObject();
            newpost.put("postid",System.currentTimeMillis());
            newpost.put("content",content);
            newpost.put("timestamo", System.currentTimeMillis());
            if(imagepath!=null&&!imagepath.isEmpty()){ newpost.put("imagePath", imagepath);}
            userarrayofposts.put(newpost);
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(Dataofposts.toString());
            writer.close();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PostMangmentt.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ex.printStackTrace();
        }}
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

