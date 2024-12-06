/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connecthub;

import java.io.File;

/**
 *
 * @author Raz_RAMADAN
 */
public class Connecthub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         ProfileManagement profileManagement = new ProfileManagement();
        UserDatabase userDatabase = UserDatabase.getInstance();

        // Example user ID (ensure this user exists in users.json for the test)
        String testUserId = "12345"; // Replace with an actual userId from your JSON database
       /* User testUser = userDatabase.getUserById(testUserId);
         File newPhotoFile = new File("C:\\Users\\malok\\OneDrive\\Pictures\\Image296.jpg");
         boolean result = profileManagement.editProfilePhoto(testUserId, newPhotoFile);

        if (result) {
            System.out.println("Profile photo updated successfully.");
        } else {
            System.err.println("Failed to update profile photo.");
        }}}*/
         
        // Simulate adding a user for testing if not already present
        User testUser = userDatabase.getUserById(testUserId);
        if (testUser == null) {
            testUser = new User.UserBuilder()
                    .setUserId(testUserId)
                    .setUsername("TestUser")
                    .setEmail("testuser@example.com")
                    .setBio("This is a test bio.")
                    .setProfilePhotoPath("")
                    .setCoverPhotoPath("")
                    .setPassword("password123")
                    .build();
            userDatabase.addUser(testUser);
        }

        // Path to a new profile photo (ensure this file exists for the test)
        File newPhotoFile = new File("C:\\Users\\malok\\OneDrive\\Pictures\\Camera Roll\\WIN_20241115_20_19_40_Pro.jpg"); // Replace with a valid file path on your system
        File newPhotoFile2 = new File("C:\\Users\\malok\\OneDrive\\Pictures\\Camera Roll\\WIN_20241115_20_19_40_Pro.jpg");
        // Test editing the profile photo
        String caption="hi bor3y :) ";
         String pass="123";
        System.out.println("Testing Profile Photo Update...");
        profileManagement.editProfilePhoto(testUserId, newPhotoFile);
        profileManagement.editCoverPhoto(testUserId, newPhotoFile2);
        profileManagement.editBio(testUserId, caption);
        profileManagement.editPassword(testUserId, pass);

        /*if (result) {
            System.out.println("Profile photo updated successfully.");
        } else {
            System.err.println("Failed to update profile photo.");
        }*/

        // Check if the new profile photo path is written to the file
        User updatedUser = userDatabase.getUserById(testUserId);
        if (updatedUser != null) {
            System.out.println("Updated Profile Photo Path: " + updatedUser.getProfilePhotoPath());
        } else {
            System.err.println("User not found after update.");
        }
    }
}
    
    

