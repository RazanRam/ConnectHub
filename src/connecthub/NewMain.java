/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connecthub;

import static connecthub.UserDatabase.setCurrentuser;

/**
 *
 * @author janaf
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
   /* String u1="10";
        String u2="20";
        String u3="30";
        
        FriendsDatabase f=FriendsDatabase.getInstance();
        f.FriendRequest(u1, u2);
        f.FriendRequest(u2, u3);
        f.FriendRequest(u3, u1);*/
    UserDatabase udb=UserDatabase.getInstance();
    User user = new User.UserBuilder().setUsername("").setUserId("10").setEmail("").setDateOfBirth("").setPassword("").setCoverPhotoPath("DefaultcoverPhoto.jpg").setProfilePhotoPath("DefaultProfilePhoto.jpg").setBio("").build();
    setCurrentuser(user);
    Friends fr=new Friends();
    fr.setVisible(true);
    }
    
}
