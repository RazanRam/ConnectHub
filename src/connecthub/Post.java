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
private   ArrayList<Post> allPosts=getarray.loadpost();

    public void addPost(Post p) {
        allPosts.add(p);
        getarray.createfile(allPosts);
    }
    public void deletePost(Post p) {
        for(Post u:allPosts){
           if(u.getContentid().equals(p.getContentid())){
           allPosts.remove(p);
           }
       }
        getarray.createfile(allPosts);
    }

   public  ArrayList<Post> getPost(String userid){
       ArrayList<Post> listofpost = new ArrayList();
       for(Post u:allPosts){
           if(u.getAuthorid().equals(userid)){
               listofpost.add(u);
           }
       }
               return listofpost

        ;
   }
   }

