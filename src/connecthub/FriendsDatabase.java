/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author janaf
 */
public class FriendsDatabase extends Database{
    public static FriendsDatabase fdb=null;
    
    private static String friendsFILE="friends.json";
    private static String friendsrequestsFILE="friendsrequests.json";
    private static String blocksFILE="blocks.json";
    private String filename;

    private ArrayList<friendship> Friends=new ArrayList<>();
    private ArrayList<friendship> FriendRqustes=new ArrayList<>();
    private ArrayList<friendship> Blocks=new ArrayList<>();
    private ArrayList<friendship> array;
    
    private FriendsDatabase() {
        filename=friendsFILE;
        array=Friends;
        LoadDATAbase();
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        LoadDATAbase();
        filename=blocksFILE;
        array=Blocks;
        LoadDATAbase();
    }
    
    public static FriendsDatabase getInstance(){
        if(fdb==null){
            fdb=new FriendsDatabase();
        }
        return fdb;
    }
    
    public void LoadDATAbase(){
        JSONArray frArr=loadDatabase(filename);
        for(int i=0;i<frArr.length();i++){
            JSONObject fObj=frArr.getJSONObject(i);
            String u1=fObj.getString("UserID1");
            String u2=fObj.getString("UserID2");
            friendship f=new friendship(u1, u2);
            array.add(f);
        }
    }
    @Override
    public void saveDatabase(){
        JSONArray frArr=new JSONArray();
        for(friendship f:array){
            JSONObject fObj=new JSONObject();
            fObj.put("UserID1", f.getUserID1());
            fObj.put("UserID2", f.getUserID2());
            frArr.put(fObj);
        }
        
        File f=new File(filename);
        try {
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
                BufferedWriter BW = new BufferedWriter(fw);
                
                BW.write(frArr.toString(2));
                BW.flush();
                BW.close();
            
        } catch (IOException ex) {
            Logger.getLogger(FriendsDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //user1 sent a friend request to user2
    //user1 sender
    //user2 reciever
    public void FriendRequest(String user1,String user2){
        friendship f=new friendship(user1, user2);
        FriendRqustes.add(f);
        
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        saveDatabase();
    }
    
    //user1 accept a friend request from user2
    public void AcceptRrquest(String user1,String user2){
        for(friendship f:FriendRqustes){
            if(f.getUserID1().equals(user2)){
                FriendRqustes.remove(f);
                break;
            }
        }
        friendship f=new friendship(user2, user1);
        Friends.add(f);
        
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        saveDatabase();
        filename=friendsFILE;
        array=Friends;
        saveDatabase();
    }
    
    //user1 decline a friend request from user2
    public void DeclineRequest(String user1,String user2){
        for(friendship f:FriendRqustes){
            if(f.getUserID1().equals(user2)){
                FriendRqustes.remove(f);
                break;
            }
        }
        
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        saveDatabase();
    }
    
    //user1 blocks user2
    public void Block(String user1,String user2){
        for(friendship f:Friends){
            if(f.hasUser(user2)&&f.hasUser(user1)){
                Friends.remove(f);
                break;
            }
        }
        friendship f=new friendship(user1, user2);
        Blocks.add(f);
        
        filename=blocksFILE;
        array=Blocks;
        saveDatabase();
        filename=friendsFILE;
        array=Friends;
        saveDatabase();
    }
    
    //user1 removes user2
    public void Remove(String user1,String user2){
        for(friendship f:Friends){
            if(f.hasUser(user2)&&f.hasUser(user1)){
                Friends.remove(f);
                break;
            }
        }
        
        filename=friendsFILE;
        array=Friends;
        saveDatabase();
    }

    public ArrayList<String> getFriendsof(String user1){
        ArrayList<String> U1Friends=new ArrayList<>();
        for(friendship f:Friends){
            if(f.getUserID1().equals(user1)){
                U1Friends.add(f.getUserID2());
            }
            else if(f.getUserID2().equals(user1)){
                U1Friends.add(f.getUserID1());
            }
        }
        return U1Friends;
    }
    
    public ArrayList<String> getFriendRequestsof(String user1){
        ArrayList<String> U1Friendreq=new ArrayList<>();
        for(friendship f:FriendRqustes){
            if(f.getUserID2().equals(user1)){
                U1Friendreq.add(f.getUserID1());
            }
        }
        return U1Friendreq;
    }
    
    //List of people who user1 blocked
    public ArrayList<String> getBlocksof(String user1){
        ArrayList<String> U1Blocks=new ArrayList<>();
        for(friendship f:Blocks){
            if(f.getUserID1().equals(user1)){
                U1Blocks.add(f.getUserID2());
            }
        }
        return U1Blocks;
    }
    
    /*get friends of user1
    for each friend of user1 --> get their friends
    suggest these friends to user1 on condition:
    1)not already a friend
    2)not Blocked by user1
    3)not already in suggestions list
    */
    public ArrayList<String> getSuggestedTo(String user1){
        ArrayList<String> U1SuggestedFriends=new ArrayList<>();
        ArrayList<String> U1Blocks=getBlocksof(user1);
        
        ArrayList<String> U1Friends=getFriendsof(user1);
        for(String Friend:U1Friends){
            
            ArrayList<String> FriendsofFriend=getFriendsof(Friend);
            for(String person:FriendsofFriend){
                if(!U1Friends.contains(person) && !U1Blocks.contains(person) && !U1SuggestedFriends.contains(person)){
                    U1SuggestedFriends.add(person);
                }
            }
        }
        return U1SuggestedFriends;
    }
    
    
}
