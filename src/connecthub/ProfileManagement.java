/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author malok
 */
   
public class ProfileManagement {
    private String username;
    private String hashpass;
    private String bio;
    private String profilePhoto;
    private String coverPhoto;
     private ProfileManagement(profileBuilder builder) {
            this.username =builder.username;
            this.hashpass = builder.hashpass;
            this.bio = builder.bio;
            this.profilePhoto = builder.profilePhoto;
            this.coverPhoto = builder.coverPhoto;
        }
    
 public static class profileBuilder{
    private String username;
    private String hashpass;
    private String bio;
    private String profilePhoto;
    private String coverPhoto;
   
    public profileBuilder Username(String username){
    this.username=username;
    return this;
    }
     public profileBuilder Hashpass(String hashpass){
    this.hashpass=hashpass;
    return this;
    }
      public profileBuilder Bio(String bio){
    this.bio=bio;
    return this;
    }
       public profileBuilder ProfilePhoto(String profilePhoto){
    this.profilePhoto=profilePhoto;
    return this;
    }
        public profileBuilder CoverPhoto(String coverPhoto){
    this.coverPhoto=coverPhoto;
    return this;
    }
        public  profileBuilder build(){
        
            return this;
        }
        
        
 }


}
