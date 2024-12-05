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

    public void createfile() {
        JSONArray arrposts = new JSONArray();

        for (Post p : posts) {
            JSONObject jp = new JSONObject();
            jp.put("contentid", p.getContentid());
            jp.put("authorID", p.getAuthorid());
            jp.put("content", p.getContent());
            jp.put("timeStamp", p.getTimeStamp());
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

    public void loadpost() {
        String S, content = new String();
        FileReader fr;
        try {
            fr = new FileReader("posts.json");
            BufferedReader br = new BufferedReader(fr);
            while ((S = br.readLine()) != null) {
                content += S;
            }
            br.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONArray arr = new JSONArray(content);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        for (int i = 0; i < arr.length(); i++) {
            JSONObject post = arr.getJSONObject(i);
            String contentid = post.getString("contentid");
            String name = post.getString("name");
            int id = post.getInt("id");
            String txtpost = post.getString("postcontent");
            String timeStamp = post.getString("timeStamp");
            ContentFactory F = new ContentFactory();
            Post p = (Post) F.createpoststory("post");
            p.setContentid(contentid);
            p.setAuthorid(String.valueOf(id));
            p.setContent(txtpost);
            LocalDateTime.parse(timeStamp, formatter);

            posts.add(p);

        }

    }

    public ArrayList<Post> getpost() {
        return posts;

    }

}
