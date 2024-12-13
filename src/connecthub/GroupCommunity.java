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
  //newmember.put("groupname",groupname );
  return newmember;
}
public JSONObject newpost(String postid,String Groupid,String postcontent,String imagepath){
  JSONObject newpost=new JSONObject();
  newpost.put("postid", postid);
  newpost.put("groupid", Groupid);
  newpost.put("postcontent",postcontent);
  if(imagepath!=null)
  {newpost.put("imagepath",imagepath);}
  return newpost;
}
public  JSONObject newadmin(String adminid,String Groupid)
{ JSONObject newadmin=new JSONObject();
  newadmin.put("adminid", adminid);
  newadmin.put("groupid", Groupid);
  //newadmin.put("groupname",groupname);
  return newadmin;
  
}
//public JSONObject removeMember(String memberID,String Groupid){
//    JSONObject removedMember=new JSONObject();
//    removedMember.put("MemberID", memberID);
//    removedMember.put("GroupID", Groupid);
//    return removedMember;
//}
//public JSONObject editPodt(String postID,String Groupid, String newEditedPost){
//    JSONObject UpdatedPost=new JSONObject();
//    UpdatedPost.put("PostID", postID);
//    UpdatedPost.put("GroupID", Groupid);
//    UpdatedPost.put("newEditedPost", newEditedPost);
//    return UpdatedPost;
//}

      
}
