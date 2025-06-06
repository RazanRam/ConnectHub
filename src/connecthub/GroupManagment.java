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
     private ArrayList<JSONObject> grpMembers = new ArrayList(gdb.getfromfile(fileGroupMembers));
     private ArrayList<JSONObject> groupposts=new ArrayList(gdb.getfromfile(filegroupposts));
     private  UserDatabase udb = UserDatabase.getInstance();
     private String membershipRequestsFile = "MembershipRequests.json";
     private ArrayList<String>groupmembers=new ArrayList();
    

    

    public void addnewpost(String postid, String Groupid,String postcontent,String imagepath,String userid) {
        GroupCommunity GC = new GroupCommunity();
        groupposts.add(GC.newpost(postid, Groupid,postcontent,imagepath,userid));
        gdp.writeinfile(filegroupposts, groupposts);
        ArrayList<String> ids= getmembers(Groupid);
        for(String Ids:ids){
            if(Ids!=userid){
          Notifications newpost = new Notifications("Added", Groupid + " a new Post is added ");
        // UserDatabase udb = UserDatabase.getInstance();
        udb.addNotificationToUser(Ids, newpost);}}

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
         Notifications Grpadd = new Notifications("Group Added", groupId + " you are added to the group ");
        // UserDatabase udb = UserDatabase.getInstance();
        udb.addNotificationToUser(memberId, Grpadd);

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
    public ArrayList<HashMap> getGroups(){
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
    public ArrayList<String> getmembers(String groupid){
         ArrayList<JSONObject> admins = new ArrayList(gdb.getfromfile("GroupsAdmins.json"));
          ArrayList<JSONObject> primaryadmin = new ArrayList(gdb.getfromfile("createdgroups.json"));
        for(JSONObject obj:grpMembers){
            
            String member=obj.getString("memberid");
            if(ismember(member,groupid))
            { groupmembers.add(member);}
        }
        
        for(JSONObject obj:admins){
            String member=obj.getString("adminid");
            if(isanadmin(member,groupid))
            { groupmembers.add(member);}
        }
        
        for(JSONObject obj:primaryadmin){
            String member=obj.getString("Userid");
            if(istheprimaryadmin(member,groupid))
            { groupmembers.add(member);}
        } return groupmembers;
    }
    public void approveRequestOfMembership(String groupId, String memberId) {
        ArrayList<JSONObject> membershipRequests = gdp.getfromfile(membershipRequestsFile);


        boolean requestFound = false;
        for (JSONObject x : membershipRequests) {
            if (x.getString("groupid").equals(groupId) && x.getString("memberid").equals(memberId)) {
                requestFound = true;
                membershipRequests.add(x);
                addMemberToGroup(groupId, memberId);
                
            }
        }

        if (requestFound) 
            gdp.writeinfile(membershipRequestsFile, membershipRequests);
         
    }

    
    public void declineRequestOfMembership(String groupId, String memberId) {
        ArrayList<JSONObject> membershipRequests = gdp.getfromfile(membershipRequestsFile);

        //   decline  membership request if found
        boolean requestFound = false;
        for (JSONObject x : membershipRequests) {
            if (x.getString("groupid").equals(groupId) && x.getString("memberid").equals(memberId)) {
                requestFound = true;
                membershipRequests.remove(x);
                removeMember(groupId, memberId);
            }
        }

        if (requestFound) 
            gdp.writeinfile(membershipRequestsFile, membershipRequests);
    
    }
    

}
 