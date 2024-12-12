/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class NewsfeedService {
    User u=UserDatabase.getCurrentuser();
    FriendsManagment fm=FriendsManagment.getInstance();
    postStoryManagment psm=new postStoryManagment();
    
    
    public List<Story> getfriendsStories(){
            List<Story> friendsstories=new ArrayList();
            List<String> friendsID=fm.getFriendsof(u.getUserId());
            for(String f:friendsID){
                friendsstories.addAll(psm.getstories(f));
            }
        return friendsstories;
    }
    
    public List<Post> getfriendsPost(){
            List<Post> friendsstories=new ArrayList();
            List<String> friendsID=fm.getFriendsof(u.getUserId());
            for(String f:friendsID){
                friendsstories.addAll(psm.getPost(f));
            }
        return friendsstories;
    }
    
    public List<String> getListOfFriends(){
        return fm.getFriendsof(u.getUserId());
    }
    public List<String> getListofsuggestions(){
        return fm.getSuggestedTo(u.getUserId());
    }
}
    

