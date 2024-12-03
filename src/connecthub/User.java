/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author Raz_RAMADAN
 */
public class User {
    private String userId;
    private String email;
    private String username;
    private String Password;
    private String dateOfBirth;
    private boolean isOnline;

    public User(String userId, String email, String username, String hashedPassword, String dateOfBirth) {
        this.userId = userId;
        this.email = email;
        this.username = username;
        this.Password = hashedPassword;
        this.dateOfBirth = dateOfBirth;
        this.isOnline = false;
    }

    public User() {
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
