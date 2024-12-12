/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import static connecthub.GroupDatabase.gdb;
import static connecthub.PrimaryAdmin.pa;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Raz_RAMADAN
 */
public class OtherAdmin extends GroupManagment{
     public static OtherAdmin oa=null;
    private String adminId;
   private String fileGroupMembers = "GroupMembers.json";
    private String fileGroupPosts = "GroupPosts.json";
    private String membershipRequestsFile = "MembershipRequests.json";
   
    GroupCommunity GC = new GroupCommunity();

    GroupDatabase gdp = GroupDatabase.getinstance();

    private OtherAdmin() {
    }
    public static OtherAdmin getInstance(){
        if(oa==null){
            oa=new OtherAdmin();
        }
        return oa;
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
     
