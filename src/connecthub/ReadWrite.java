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
    public void createfile(){
        JSONObject writer1=new JSONObject();
        writer1.put("name","jana");
        writer1.put("id",1 );
        writer1.put("postcontent", "Hello world");
        JSONArray arr=new JSONArray();
        arr.put(writer1);
        
       
    
    
    File f =new File("posts.json");
    {   try {
        f.createNewFile();
        
        FileWriter W=new FileWriter(f);
        BufferedWriter Wr=new BufferedWriter(W);
        W.write(arr.toString(2));
        W.flush();
        W.close();
       System.out.println("jason array file created: "+arr);
         
        } catch (IOException ex) {
            Logger.getLogger(ReadWrite.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }}
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
        for (int i = 0; i < arr.length(); i++) {
            JSONObject post = arr.getJSONObject(i);
            String name = post.getString("name");
            int id = post.getInt("id");
            String txtpost = post.getString("postcontent");
            ContentFactory F = new ContentFactory();
            Post p = (Post) F.createpoststory("post");
            p.setAuthorid(String.valueOf(id));
            p.setContent(txtpost);
            posts.add(p);
            
        }
        
    }
    public ArrayList<Post> getpost(){ 
        return posts;
        
    }
   
    
    
    
}
