/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import org.json.JSONObject;

/**
 *
 * @author hp
 */

public class GroupCommunity {
private String Groupid;
private String newmember;
private String newpost;


    

public JSONObject newmember(String memberid,String Groupid){
  JSONObject newmember=new JSONObject();
  newmember.put("memberid", memberid);
  newmember.put("groupid", Groupid);
  return newmember;
}
public JSONObject newpost(String postid,String Groupid){
    JSONObject newpost=new JSONObject();
  newpost.put("postid", postid);
  newpost.put("groupid", Groupid);
  return newpost;
}
public  JSONObject newadmin(String adminid,String Groupid)
{ JSONObject newadmin=new JSONObject();
  newadmin.put("adminid", adminid);
  newadmin.put("groupid", Groupid);
  return newadmin;
  
}
    
    
}
