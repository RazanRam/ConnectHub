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
 private ArrayList<Post> listofpost = new ArrayList();

    public void addPost(Post p) {
        listofpost.add(p);
        getarray.createfile(listofpost);
    }
    public void deletePost(Post p) {
        listofpost.remove(p);
        getarray.createfile(listofpost);
    }

   public ArrayList<Post> getPost(){
        return listofpost;
   }
   }

