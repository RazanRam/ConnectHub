/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class postStoryManagment {
    ReadWriteStory story = new ReadWriteStory();
    ReadWrite getarray=new ReadWrite();
    private ArrayList<Post> allPosts=getarray.loadpost();
    private ArrayList<Story> allstories=story.loadStories();

    public void addPost(Post p) {
        allPosts.add(p);
        getarray.createfile(allPosts);
    }
    public void deletePost(Post p) {
       ArrayList<Post> listofpost = new ArrayList();
        for(Post u:allPosts){
           if(!u.getContentid().equals(p.getContentid())){
           listofpost.add(p);
           }
       }
        getarray.createfile(listofpost);
    }

   public ArrayList<Post> getPost(String userid){
       ArrayList<Post> listofpost = new ArrayList();
       for(Post u:allPosts){
           if(u.getAuthorid().equals(userid)){
               listofpost.add(u);
           }
       }
               return listofpost;
   }
   
   public void addStory(Story s) {
       allstories.add(s);
       story.WriteStoryInFile(allstories);
    }

    public void deleteStory(Story s) {  
        ArrayList<Story> updatedstories = new ArrayList();
        for (Story S : allstories) {
            Duration duration = Duration.between(S.getTimeStamp(), LocalDateTime.now());
            long hrs = duration.toHours();
            if (hrs < 24) {
                updatedstories.add(S);
            }
        } story.WriteStoryInFile(updatedstories);
    }
    
    public ArrayList<Story> getstories(String userid) {
        ArrayList<Story> listofstories = new ArrayList();
       for(Story u:allstories){
           if(u.getAuthorid().equals(userid)){
               listofstories.add(u);
           }
       }
               return listofstories;
    }
}
