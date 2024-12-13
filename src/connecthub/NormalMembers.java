/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Raz_RAMADAN
 */
public class NormalMembers extends GroupManagment {
     GroupDatabase gdp = GroupDatabase.getinstance();
     private String userId;
    private String fileGroupMembers = "GroupMembers.json";
    private String fileGroupPosts = "GroupPosts.json";
    ArrayList<JSONObject> groupMembers = gdp.getfromfile(fileGroupMembers);
    
   public void leaveGroup(String groupId){
        boolean memberFound = false;
        for (JSONObject x : groupMembers) {
            if (x.getString("groupid").equals(groupId)&& x.getString("memberid").equals(userId)) {
                memberFound = true;
                groupMembers.remove(x);
              
            }
        }

        if (memberFound) 
            gdp.writeinfile(fileGroupMembers, groupMembers);
       
   }
   
}
