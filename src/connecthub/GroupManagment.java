/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class GroupManagment {

   
     private String filegroupposts="Groupposts.json";
    GroupDatabase gdp= GroupDatabase.getinstance();
    
    
    ArrayList<JSONObject> groupposts;
  
   public void addnewpost(String postid,String Groupid){
        GroupCommunity GC= new GroupCommunity();
        groupposts.add(GC.newpost(postid, Groupid));
        gdp.writeinfile(filegroupposts, groupposts);
        }

    public void removepost(String postid){
        ArrayList<JSONObject> posts=gdp.getfromfile(filegroupposts);
        Iterator<JSONObject>iterator=posts.iterator();
        while(iterator.hasNext()){
            JSONObject obj = iterator.next();
            if (obj.get("postid").equals(postid)) {
            iterator.remove();
            break;
    }
            gdp.writeinfile(filegroupposts,posts);
          
        }
            
        }
    
        
    }
    
  
    
