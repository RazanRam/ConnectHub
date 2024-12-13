/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import connecthub.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author janaf
 */
public class SearchEngine {
    UserDatabase udb=UserDatabase.getInstance();
    FriendsManagment fm=FriendsManagment.getInstance();
    User me=UserDatabase.getCurrentuser();
    GroupManagment gdb= new GroupManagment();
    HashMap<Integer, HashMap> groupmap=new HashMap<>();
    
    private HashMap<Integer, String> map=new HashMap<>();
    private int i=0;
    private int LastUseri=0;
    
    public ArrayList<String> UserSearchResults(String key){
        ArrayList<User> users=(ArrayList<User>) udb.getUsers();
        ArrayList<String> results=new ArrayList<>();
        for(User u:users){
            if(u.getUsername().contains(key)){
                results.add(u.getUsername()+"   "+Iswhat(u.getUserId()));
                map.put(i, u.getUserId());
                i++;
            }
        }
        LastUseri=i-1;
        
        return results;
    }
    public String Iswhat(String id){
        ArrayList<String> myFriends=fm.getFriendsof(me.getUserId());
        ArrayList<String> myRequests=fm.getFriendRequestsof(me.getUserId());
        ArrayList<String> RequestsFromMe=fm.getRequestedBy(me.getUserId());        
        ArrayList<String> myBlocks=fm.getBlocksof(me.getUserId());
        ArrayList<String> mysuggests=fm.getSuggestedTo(me.getUserId());
        
        if(myFriends.contains(id))
            return "<<friend>>";
        if(myRequests.contains(id))
            return "<<wants to be your friend>>";
        if(RequestsFromMe.contains(id))
            return "<<Pending Request>>";
        if(myBlocks.contains(id))
            return "<<blocked>>";
        if(mysuggests.contains(id))
            return "<<suggested>>";
        if(id.equals(me.getUserId()))
            return "<<Me>>";
        return "";
    }
    
    public ArrayList<String> GroupSearchResults(String key){
        ArrayList<HashMap> groups=gdb.getGroups();
        ArrayList<String> results=new ArrayList<>();
        
        for(HashMap g:groups){
            String grpName=(String) g.get("name");
            String grpID=(String) g.get("ID");
            if(grpName.contains(key)){
                results.add(grpName);
                map.put(i, grpID);
                groupmap.put(i, g);
                i++;
            }
        }
        return results;
    }

    public ArrayList<String> SearchResults(String key){
        ArrayList<String> results=new ArrayList<>(UserSearchResults(key));
        results.addAll(GroupSearchResults(key));
        return results;
    }
    
    public HashMap<Integer, String> getMap() {
        return map;
    }
    
    public int getLastUseri(){
        return LastUseri;
    }

    public HashMap<Integer, HashMap> getGroupmap() {
        return groupmap;
    }

    public void setGroupmap(HashMap<Integer, HashMap> groupmap) {
        this.groupmap = groupmap;
    }
    
}
