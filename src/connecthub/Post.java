/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class Post extends ContentCreation {

  ReadWrite getarray=new ReadWrite();
  ArrayList<Post> Posts=getarray.getpost();
    
    public Post() {
    }

    

    public Post getpost(String userid) {
        for (Post post : Posts) {
            if (post.getAuthorid().equals(userid)) {
                return post;
            }
            
        }
        
        return null;
    }

    public void addpost() {
    }

    public void deletepost() {
    }
    
}
