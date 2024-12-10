/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author janaf
 */
public class SearchEngine {
    UserDatabase udb=UserDatabase.getInstance();
    FriendsManagment fm=FriendsManagment.getInstance();
    HashMap<Integer, String> map=new HashMap<>();
    User me=UserDatabase.getCurrentuser();
    
    public ArrayList<String> UserSearchResults(String key){
        ArrayList<User> users=(ArrayList<User>) udb.getUsers();
        ArrayList<String> results=new ArrayList<>();
        int i=0;
        for(User u:users){
            if(u.getUsername().contains(key)){
                results.add(u.getUsername()+"   <<"+Iswhat(u.getUserId())+">>");
                map.put(i, u.getUserId());
                i++;
            }
        }
        return results;
    }
    public String Iswhat(String id){
        ArrayList<String> myFriends=fm.getFriendsof(me.getUserId());
        ArrayList<String> myRequests=fm.getFriendRequestsof(me.getUserId());
        ArrayList<String> myBlocks=fm.getBlocksof(me.getUserId());
        ArrayList<String> mysuggests=fm.getSuggestedTo(me.getUserId());
        
        if(myFriends.contains(id))
            return "friend";
        if(myRequests.contains(id))
            return "wants to be your friend";
        if(myBlocks.contains(id))
            return "blocked";
        if(mysuggests.contains(id))
            return "suggested";
        return "";
    }
    
    
    //send a request in case you havent sent before!!!!!!!!!!!!!!!!!!!!!!!//
    
    
}
