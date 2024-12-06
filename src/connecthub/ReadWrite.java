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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class ReadWrite {

    private ArrayList<Post> posts = new ArrayList();

    public void createfile(ArrayList<Post> posts) {
     ArrayList<Post> oldPosts = loadpost();
      oldPosts.addAll(posts);
        JSONArray arrposts = new JSONArray();

        for (Post p : oldPosts) {
            JSONObject jp = new JSONObject();
            jp.put("contentid", p.getContentid());
            //jp.put("authorID", p.getAuthorid());
            jp.put("postcontent", p.getContent());
            jp.put("timeStamp", p.getTimeStamp());
            String imagePath=p.getImagepath();
            if(imagePath!=null){ jp.put("imagePath",imagePath);
            }else{jp.put("imagePath", "null");}
            arrposts.put(jp);
        }
        FileWriter file;
        try {
            file = new FileWriter("posts.json");
            file.write(arrposts.toString(4));
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<Post> loadpost() {
        String content = "";
        File file = new File("posts.json");
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content += line;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray arr = new JSONArray(content);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject post = arr.getJSONObject(i);
            String contentid = post.getString("contentid");
            String txtpost = post.getString("postcontent");
            String timeStamp = post.getString("timeStamp");
            String imagePath = post.getString("imagePath");
           // System.out.println("Loaded post: " + contentid + ", " + txtpost + ", " + timeStamp + ", " + imagePath); 
            //String authorID = post.getString("authorID");
            ContentFactory F = new ContentFactory();
            Post p = (Post) F.createpoststory("Post");
            p.setContentid(contentid);
            //p.setAuthorid(authorID);
            p.setContent(txtpost);
            p.setTimeStamp(LocalDateTime.parse(timeStamp, formatter));
            p.setimage(imagePath);

            posts.add(p);

        }
        return posts;

    }
    
}
