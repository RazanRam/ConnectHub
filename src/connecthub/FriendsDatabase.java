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

    private ArrayList<friend> Friends=new ArrayList<>();
    private ArrayList<friend> FriendRqustes=new ArrayList<>();
    private ArrayList<friend> Blocks=new ArrayList<>();
    private ArrayList<friend> array;

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
            JSONObject fObj=new JSONObject(i);
            String u1=fObj.getString("UserID1");
            String u2=fObj.getString("UserID2");
            friend f=new friend(u1, u2);
            array.add(f);
        }
    }
    @Override
    public void saveDatabase(){
        JSONArray frArr=new JSONArray();
        for(friend f:array){
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
    public void FriendRequest(String user1,String user2){
        friend f=new friend(user1, user2);
        FriendRqustes.add(f);
        
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        saveDatabase();
    }
    //user1 accept a friend request from user2
    public void AcceptRrquest(String user1,String user2){
        friend f=new friend(user2, user1);
        int i=FriendRqustes.indexOf(f);
        FriendRqustes.remove(i);
        Friends.add(f);
        
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        saveDatabase();
        filename=friendsFILE;
        array=Friends;
        saveDatabase();
    }
    //user1 decline a friend request from user2
    public void DeclineRrquest(String user1,String user2){
        friend f=new friend(user2, user1);
        int i=FriendRqustes.indexOf(f);
        FriendRqustes.remove(i);
        
        filename=friendsrequestsFILE;
        array=FriendRqustes;
        saveDatabase();
    }
    //user1 blocks usser2
    public void Block(String user1,String user2){
        friend f=new friend(user1, user2);
        friend fr=new friend(user2, user1);
        
    }
    
}
