/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import static connecthub.GroupDatabase.gdb;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class GroupManagment {

     
     GroupDatabase gdp = GroupDatabase.getinstance();
     private String fileGroupMembers = "GroupMembers.json";
     private String filegroupposts = "Groupposts.json";
     ArrayList<JSONObject> grpMembers = new ArrayList(gdb.getfromfile(fileGroupMembers));
   
    

    ArrayList<JSONObject> groupposts=new ArrayList();

    public void addnewpost(String postid, String Groupid,String postcontent,String imagepath) {
        GroupCommunity GC = new GroupCommunity();
        groupposts.add(GC.newpost(postid, Groupid,postcontent,imagepath));
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
   
    

}
 