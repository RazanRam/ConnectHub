/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author hp
 */
public class PrimaryAdmin extends GroupManagment {
    private String filegroupadmins="GroupsAdmins.json";
    private ArrayList<JSONObject> groupadmins;
    
    GroupDatabase gdp= GroupDatabase.getinstance();
    public void creatnewgroup(String groubname,String groubdescribtion,String groupBio,String groupphoto){
        CreateGroup CG =new CreateGroup();
        
       
        CG.setgroupname(groubname);
        CG.setgroupdescription(groubdescribtion);
        CG.setgroupimage(groupphoto);
        
        
    }
    public void addnewadmin(String groupid,String adminid){
        GroupCommunity GC =new GroupCommunity();
        groupadmins.add(GC.newadmin(adminid, groupid));
        gdp.writeinfile(filegroupadmins,groupadmins);
        
       }
    public void deletegroup(){}
    
    
}
