/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

     import static connecthub.UserDatabase.getCurrentuser;
import static connecthub.GroupDatabase.gdb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class GroupManagment {
    User me=getCurrentuser();
     GroupDatabase gdp = GroupDatabase.getinstance();
     private String fileGroupMembers = "GroupMembers.json";
     private String filegroupposts = "Groupposts.json";
     ArrayList<JSONObject> grpMembers = new ArrayList(gdb.getfromfile(fileGroupMembers));
   
    

    ArrayList<JSONObject> groupposts=new ArrayList();

    public void addnewpost(String postid, String Groupid,String postcontent,String imagepath,String userid) {
        GroupCommunity GC = new GroupCommunity();
        groupposts.add(GC.newpost(postid, Groupid,postcontent,imagepath,userid));
        gdp.writeinfile(filegroupposts, groupposts);
    }
     public void removeMember(String groupId, String memberId){
        
        ArrayList<JSONObject> createdGroups = gdp.getfromfile("createdgroups.json");
        // Check if  member being removed is  Primary Admin
        for (JSONObject x : createdGroups) {
        if (x.getString("Userid").equals(memberId) && x.getJSONObject("createdgroups").has(groupId)) 
            return;
            }
        //remove member if found
         boolean memberFound = false;
        for (JSONObject x : grpMembers) {
            if (x.getString("groupid").equals(groupId) 
                && x.getString("memberid").equals(memberId)) {
                memberFound = true;
                grpMembers.remove(x);
            }
        }
         if (memberFound) 
            gdp.writeinfile(fileGroupMembers, grpMembers);

}

    public void removepost(String postid) {
        ArrayList<JSONObject> posts = gdp.getfromfile(filegroupposts);
        Iterator<JSONObject> iterator = posts.iterator();
        while (iterator.hasNext()) {
            JSONObject obj = iterator.next();
            if (obj.get("postid").equals(postid)) {
                iterator.remove();
                break;
            }
            gdp.writeinfile(filegroupposts, posts);

        }

    }

    public void addMemberToGroup(String groupId, String memberId) {
        GroupCommunity GC = new GroupCommunity();
        grpMembers.add(GC.newmember(memberId, groupId));
        gdp.writeinfile(fileGroupMembers, grpMembers);
    }
    
    public ArrayList<HashMap> getGroups(){
        ArrayList<HashMap> Groups=new ArrayList<>();
        
        ArrayList<JSONObject> arr = gdp.getfromfile("createdgroups.json");
        for (JSONObject obj : arr) {
            JSONObject createdGroups = obj.getJSONObject("mycreatedgroups");
            //loop on Groups
            for(String grpID : createdGroups.keySet()){
                //get id of each group & put in a hashmap
                HashMap<String,String> group=new HashMap<>();
                group.put("ID", grpID);
                //get name of group with specified id
                JSONObject GrpDetails=createdGroups.getJSONObject(grpID);
                group.put("ProfileImage", GrpDetails.getString("image"));
                group.put("CoverImage", GrpDetails.getString("coverimage"));
                group.put("name", GrpDetails.getString("name"));
                group.put("desc", GrpDetails.getString("bio"));
                
                //add hash map in array of maps representing groups existing
                Groups.add(group);
                
            }
        }
        return Groups;
    }
    public ArrayList<HashMap> getMyGroups(){
        ArrayList<HashMap> Groups=new ArrayList<>();
        
        ArrayList<JSONObject> arr = gdp.getfromfile("createdgroups.json");
        for (JSONObject obj : arr) {
            if(obj.getString("Userid").equals(me.getUserId())){
            JSONObject createdGroups = obj.getJSONObject("mycreatedgroups");
            //loop on Groups
            for(String grpID : createdGroups.keySet()){
                Object groupData = createdGroups.get(grpID);

               if (groupData instanceof JSONArray) {
                    // If groupData is a JSONArray, iterate through it
                    JSONArray groupDetailsArray = (JSONArray) groupData;

                    HashMap<String, String> group = new HashMap<>();
                    group.put("ID", grpID);

                    for (int i = 0; i < groupDetailsArray.length(); i++) {
                        JSONObject detail = groupDetailsArray.getJSONObject(i);

                        // Extract known keys
                        if (detail.has("name")) {
                            group.put("name", detail.getString("name"));
                        }
                        if (detail.has("bio")) {
                            group.put("desc", detail.getString("bio"));
                        }
                        if (detail.has("image")) {
                            group.put("ProfileImage", detail.getString("image"));
                        }
                        if (detail.has("coverimage")) {
                            group.put("CoverImage", detail.getString("coverimage"));
                        }
                    }

                    Groups.add(group);

                } else {
                    // Log unexpected types
                    System.err.println("Unexpected data type for group ID: " + grpID + ". Data: " + groupData);
                }
            }
        }
    }

    return Groups;
}

    public boolean istheprimaryadmin(String userid, String groupid) {
        ArrayList<JSONObject> arr = new ArrayList(gdp.getfromfile("createdgroups.json"));
        for (JSONObject obj : arr) {
            if (obj.getString("Userid").equals(userid)) {
                JSONObject createdGroups = obj.getJSONObject("mycreatedgroups");
                if (createdGroups.has(groupid)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isanadmin(String userid, String groupid) {
        ArrayList<JSONObject> arr = new ArrayList(gdp.getfromfile("GroupsAdmins.json"));
        for (JSONObject obj : arr) {
            if (obj.getString("adminid").equals(userid) && obj.get("groupid").equals(groupid)) {
                return true;
            }
        }

        return false;
    }
    public boolean ismember(String userid, String groupid) {
        
        for (JSONObject obj : grpMembers) {
            if (obj.getString("memberid").equals(userid) && obj.get("groupid").equals(groupid)) {
                return true;
            }
        }
System.out.println(grpMembers);
        return false;
    }
    
    public JSONArray getposts(String groupid){
    ArrayList<JSONObject> objects = new ArrayList<>(gdb.getfromfile("Groupposts.json"));
            boolean groupExists = false;
            
            JSONArray arr = new JSONArray();
            

            for (JSONObject obj : objects) {
                System.out.print(obj.getString("groupid"));
                if (obj.getString("groupid").equals(groupid)) {
                    groupExists = true;
                    JSONObject post = new JSONObject();
                    post.put("postcontent", obj.getString("postcontent"));
                    post.put("imagepath", obj.getString("imagepath"));
                    post.put("userid", obj.getString("userid"));
                    arr.put(post);
                }
                
            } return arr;
}
   
    

}
 