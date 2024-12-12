/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class PrimaryAdmin extends GroupManagment {
    public static PrimaryAdmin pa=null;
    User user=UserDatabase.getCurrentuser();
    private String userid=user.getUserId();
    private String filegroupadmins="GroupsAdmins.json";
    private String createdgroup="createdgroups.json";
  private ArrayList<JSONObject> groupadmins = new ArrayList<>();

    GroupDatabase gdb=GroupDatabase.getinstance();
    GroupDatabase gdp= GroupDatabase.getinstance();
    

     private PrimaryAdmin() {
    }
    public static PrimaryAdmin getInstance(){
        if(pa==null){
            pa=new PrimaryAdmin();
        }
        return pa;
    }
     public void removeAdmin(String groupId,String AdminID) {
        ArrayList<JSONObject> posts = gdp.getfromfile(filegroupadmins);
        Iterator<JSONObject> iterator = posts.iterator();
        while (iterator.hasNext()) {
            JSONObject obj = iterator.next();
            if (obj.get("adminid").equals(userid)) {
                iterator.remove();
                break;
            }
            gdp.writeinfile(filegroupadmins, posts);

        }
        
        
     }
   
    public void creatnewgroup(CreateGroup CG) {
    // Step 1: Load existing groups from the file
    CG.setGroupid();
    ArrayList<JSONObject> createdGroupsArray = gdb.getfromfile(createdgroup);

    // Step 2: Find or create the JSONObject for the current user
    JSONObject userObject = null;
    for (JSONObject obj : createdGroupsArray) {
        if (obj.get("Userid").equals(userid)) { // Match user ID
            userObject = obj; // Found user's data
            break;
        }
    }

    // Step 3: If the user's object doesn't exist, create a new one
    if (userObject == null) {
        userObject = new JSONObject();
        userObject.put("Userid", userid);

        // Add an empty JSON object for user's created groups
        userObject.put("mycreatedgroups", new JSONObject());
        createdGroupsArray.add(userObject); // Add the new user object to the array
    }

    // Step 4: Get the user's created groups JSONObject
    JSONObject userCreatedGroups = userObject.getJSONObject("mycreatedgroups");

    // Step 5: Create a new group and store its details
    
    
    
    
    

    // Step 6: Create a JSON array to hold the group's details
    JSONArray groupDetailsArray = new JSONArray();
   
    groupDetailsArray.put(new JSONObject().put("name", CG.getgroupname()));
    groupDetailsArray.put(new JSONObject().put("bio", CG.getgroupdescription()));
    groupDetailsArray.put(new JSONObject().put("image", CG.getgroupimage()));
         System.out.println(CG.getgroupname());
        System.out.println(CG.getgroupdescription());
    // Step 7: Add the group details to the user's created groups with groupId as the key
    userCreatedGroups.put(CG.getGroupid(), groupDetailsArray);

    // Step 8: Save the updated array back to the file
    gdb.writeinfile(createdgroup, createdGroupsArray);
}



    public void addnewadmin(String groupid,String adminid){
        GroupCommunity GC =new GroupCommunity();
        groupadmins.add(GC.newadmin(adminid, groupid));
        gdp.writeinfile(filegroupadmins,groupadmins);
        
       }
    public void deletegroup(){}
    public void addmember(){}
    
    
}
