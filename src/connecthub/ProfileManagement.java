/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author malok
 */
public class ProfileManagement {

    private final UserDatabase userDatabase;

    public ProfileManagement() {
        this.userDatabase = UserDatabase.getInstance();
    }

    public void editProfilePhoto(String userId, File newPhotoFile) {
        User user = userDatabase.getUserById(userId);

        // Ensure the user exists and the file is valid
        if (user == null || newPhotoFile == null || !newPhotoFile.exists()) {
            System.err.println("User not found or invalid file.");
            return;
        }

        // Define the directory to store profile photos
        String profilePhotoDirectory = "user_PP/profilePhoto_Edit/";
        String newProfilePhotoPath = profilePhotoDirectory + userId + "_PP.jpg";

        try {
            // Ensure the directory exists
            File directory = new File(profilePhotoDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Copy the new photo to the target location
            Files.copy(newPhotoFile.toPath(), new File(newProfilePhotoPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Update the user's profile photo path in the database
            user.setProfilePhotoPath(newProfilePhotoPath);
            userDatabase.saveDatabase();

            System.out.println("Profile photo updated successfully.");
            return;

        } catch (IOException e) {
            System.err.println("Error updating profile photo: " + e.getMessage());
            return;
        }
    }

    public void editCoverPhoto(String userId, File newPhotoFile) {
        User user = userDatabase.getUserById(userId);

        // Ensure the user exists and the file is valid
        if (user == null || newPhotoFile == null || !newPhotoFile.exists()) {
            System.err.println("User not found or invalid file.");
            return;
        }

        // Define the directory to store profile photos
        String coverPhotoDirectory = "user_PP/coverPhoto_Edit/";
        String newCoverPhotoPath = coverPhotoDirectory + userId + "_PP.jpg";

        try {
            // Ensure the directory exists
            File directory = new File(newCoverPhotoPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Copy the new photo to the target location
            Files.copy(newPhotoFile.toPath(), new File(newCoverPhotoPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

            // Update the user's profile photo path in the database
            user.setCoverPhotoPath(newCoverPhotoPath);
            userDatabase.saveDatabase();

            System.out.println("Cover photo updated successfully.");
            return;

        } catch (IOException e) {
            System.err.println("Error updating cover photo: " + e.getMessage());
            return;
        }
    }

    public void editBio(String userId, String caption) {
        User user = userDatabase.getUserById(userId);
        if (user == null) {
            System.err.println("User not found.");
        } else {
            user.setBio(caption);
            userDatabase.saveDatabase();
        }
    }

    public void editPassword(String userId, String password) {

        User user = userDatabase.getUserById(userId);
        if (user == null) {
            System.err.println("User not found.");
        } else {
            user.setHashedPassword(hashedPass.hashPassword(user.getHashedPassword()));
            userDatabase.saveDatabase();
        }

    }

}