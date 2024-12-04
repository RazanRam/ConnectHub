/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import static connecthub.UserDatabase.u;

/**
 *
 * @author Raz_RAMADAN
 */
public class User {
    private String Bio;
    private String userId;
    private String email;
    private String username;
    private String Password;
    private String dateOfBirth;
    private boolean isOnline;
    private String ProfilePhotoPath;
    private String CoverPhotoPath;

    private User(UserBuilder builder) {
        this.Bio = builder.Bio;
       this.Bio = builder.Bio;
        this.userId = builder.userId;
        this.email = builder.email;
        this.username = builder.username;
        this.Password = builder.Password;
        this.dateOfBirth = builder.dateOfBirth;
        this.isOnline = builder.isOnline;
        this.ProfilePhotoPath = builder.ProfilePhotoPath;
        this.CoverPhotoPath = builder.CoverPhotoPath;
    }

//    public User(String Bio, String Password, String ProfilePhotoPath, String CoverPhotoPath) {
//        this.Bio = Bio;
//        this.Password = Password;
//        this.ProfilePhotoPath = ProfilePhotoPath;
//        this.CoverPhotoPath = CoverPhotoPath;
//    }
    public static class UserBuilder {
        
    private String Bio;
    private String userId;
    private String email;
    private String username;
    private String Password;
    private String dateOfBirth;
    private boolean isOnline;
    private String ProfilePhotoPath;
    private String CoverPhotoPath;
   
    public UserBuilder setBio(String Bio) {
        this.Bio = Bio;
        return this;
    }

    public UserBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String Password) {
        this.Password = Password;
        return this;
    }

    public UserBuilder setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserBuilder setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
        return this;
    }

    public UserBuilder setProfilePhotoPath(String ProfilePhotoPath) {
        this.ProfilePhotoPath = ProfilePhotoPath;
        return this;
    }

    public UserBuilder setCoverPhotoPath(String CoverPhotoPath) {
        this.CoverPhotoPath = CoverPhotoPath;
        return this;
    }
    
    public User build() {
        return new User(this);
    }

        
    }

    public String getBio() {
        return Bio;
    }

    public String getPassword() {
        return Password;
    }

    public String getProfilePhotoPath() {
        return ProfilePhotoPath;
    }

    public String getCoverPhotoPath() {
        return CoverPhotoPath;
    }

    public void setBio(String Bio) {
        this.Bio = Bio;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setProfilePhotoPath(String ProfilePhotoPath) {
        this.ProfilePhotoPath = ProfilePhotoPath;
    }

    public void setCoverPhotoPath(String CoverPhotoPath) {
        this.CoverPhotoPath = CoverPhotoPath;
    }

    

     
    

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return Password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHashedPassword(String hashedPassword) {
        this.Password = hashedPassword;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
}
