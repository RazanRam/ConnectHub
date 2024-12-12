/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import connecthub.User;

import connecthub.hashedPass;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.Notification;
import javax.swing.JOptionPane;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Raz_RAMADAN
 */

public class UserDatabase{
    private static User currentuser;
     public static UserDatabase u =null;
   private static final String filename="users.json";
 //   private Map<String, User> users =new HashMap<>();
 //  private ObjectMapper objectmapper = new ObjectMapper();
  private List<User> users = new ArrayList<>();

    private UserDatabase() {
        LoadDatabase();
    }
   public static UserDatabase getInstance(){
        if(u==null){
            u= new UserDatabase();
        }
        return u;
    }
   public synchronized boolean addNotificationToUser(String Id,Notifications notification){
   User user=getUserById(Id);
   if(user!=null){
   user.addNotification(notification);
   saveDatabase();
   return true;
   }
   return false;
   }
   public List<Notifications> getUserNotifications(String userId){
   User user=getUserById(userId);
   if(user!=null){
  return user.getNotifications();
   }
   return new ArrayList<>();
   }
    //read json file 
     public void LoadDatabase() {
         File file = new File(filename);
        JSONArray jsonArray=new JSONArray();
         if (file.exists()) 
         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonString = new StringBuilder();

              String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }

                // Parse the JSON array
                JSONArray jsoNArray = new JSONArray(jsonString.toString());
                jsonArray=jsoNArray;
    reader.close();
}        catch (FileNotFoundException ex) {
             Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(UserDatabase.class.getName()).log(Level.SEVERE, null, ex);
         }
                // Parse the JSON array
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonUser = jsonArray.getJSONObject(i);

                    // Extract user properties
                    
                    String Bio= jsonUser.getString("Bio");
                    String userId = jsonUser.getString("userId");
                    String username = jsonUser.getString("username");
                    String email = jsonUser.getString("email");
                    String password = jsonUser.getString("password");
                    String dateOfBirth = jsonUser.getString("dateOfBirth");
                    boolean isOnline = jsonUser.getBoolean("isOnline");
                    String ProfilePhotoPath = jsonUser.getString("ProfilePhotoPath");
                    String CoverPhotoPath = jsonUser.getString("CoverPhotoPath");
                    
                      User user = new User.UserBuilder().setUsername(username).setUserId(userId).setEmail(email).setDateOfBirth(dateOfBirth).setPassword(password).setCoverPhotoPath(CoverPhotoPath).setProfilePhotoPath(ProfilePhotoPath).setBio(Bio).build();
            // User user = new User(userId, username, email, password, dateOfBirth);
                JSONArray jsonNotifications=jsonUser.optJSONArray("notifications");
                if(jsonNotifications!=null){
                for(int j=0;j<jsonNotifications.length();j++){
                JSONObject jsonNotification = jsonNotifications.getJSONObject(j);
                Notifications notify=new Notifications(jsonNotification.getString("type"),jsonNotification.getString("description"));
                //notify.Read();
                user.addNotification(notify);
                }}
                    user.setIsOnline(isOnline);
                    users.add(user);
                    
                    System.out.println("email: "+email);
                }
         //    users = objectmapper.readValue(file, new TypeReference<Map<String, User>>(){} );
             //Map.class
         
    }
     
      public void saveDatabase() {
       //   objectmapper.writeValue(new File(filename), users);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            JSONArray jsonArray = new JSONArray();

            // Convert each user to a JSONObject and add it to the JSON array
            for (User user : users) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("Bio", user.getBio());
                jsonUser.put("userId", user.getUserId());
                jsonUser.put("username", user.getUsername());
                jsonUser.put("email", user.getEmail());
                jsonUser.put("password", user.getHashedPassword());
                jsonUser.put("dateOfBirth", user.getDateOfBirth());
                jsonUser.put("isOnline", user.isIsOnline());
                jsonUser.put("ProfilePhotoPath", user.getProfilePhotoPath());
                jsonUser.put("CoverPhotoPath", user.getCoverPhotoPath());
                
                JSONArray jsonNotifications = new JSONArray();
                for(Notifications notify:user.getNotifications()){
                JSONObject jsonNotification = new JSONObject();
                jsonNotification.put("type", notify.getType());
                jsonNotification.put("sender", notify.getSender());
                jsonNotification.put("description", notify.getDescription());
                jsonNotification.put("timestamp", notify.getTimestamp());
                jsonNotification.put("isRead", notify.isRead());
                jsonNotifications.put(jsonNotification);
                }
                jsonUser.put("notifications", jsonNotifications);
                jsonArray.put(jsonUser);
            }

            // Write the JSON array to the file
            writer.write(jsonArray.toString(4));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving to JSON database.");
            e.printStackTrace();
        }


    }

   public boolean addUser(User user){
       for(User x: users){
        if(x.getEmail().equals(user.getEmail()))   
            return false;
       }
       
           user.setHashedPassword(hashedPass.hashPassword(user.getHashedPassword()));
               users.add(user);
           saveDatabase();
       return true;
               
   }
    public boolean login(String email,String Password){
        if (email == null || email.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Email cannot be empty!", "Login Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    if (Password == null || Password.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Password cannot be empty!", "Login Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }
        
       for(User x: users){
           System.out.println("checking user: "+x.getEmail());
           if(x.getEmail().equalsIgnoreCase(email)){
               System.out.println("Entered password(hashed): "+hashedPass.hashPassword(Password));
               System.out.println("Stored password(hashed): "+x.getHashedPassword());
               if(x.getHashedPassword().equals(hashedPass.hashPassword(Password))){
                   x.setIsOnline(true);
                   setCurrentuser(x);
                   saveDatabase();
                   return true;
               }
           }
       }
        JOptionPane.showMessageDialog(null, "Incorrect password!", "Login Error", JOptionPane.ERROR_MESSAGE);
       return false;
    }
    
    public boolean logout(String email){
       for(User x : users) {
            if (x.getEmail().equals(email)) {
                x.setIsOnline(false);
                saveDatabase();
                return true; // Logout successful
            }
        }
        return false;
    }
    public boolean signin(String username,String email,String Password,String dateOfBirth){
       if (username.isEmpty() || email.isEmpty() || Password.isEmpty() || dateOfBirth.isEmpty()) {
              return false;
       }
        
        for(User x : users) {
            if (x.getEmail().equals(email)) {
                return false;
            }
             
        } 
        String hashedPassword = hashedPass.hashPassword(Password);
        User newUser = new User.UserBuilder().setUsername(username).setUserId(UUID.randomUUID().toString()).setEmail(email).setIsOnline(true).setDateOfBirth(dateOfBirth).setPassword(hashedPassword).build();
      // User newUser = new User(UUID.randomUUID().toString(), username, email, hashedPassword, dateOfBirth);
     
        users.add(newUser);
        //System.out.println(users.getLast().getEmail());
        setCurrentuser(newUser);
        saveDatabase();
       return true;

    }
   
    
    public boolean isemailvalid(String email){
           if (email == null) {
            //System.out.println("Email is NULL. Please provide a valid email.");
              JOptionPane.showMessageDialog(null, "Email is NULL. Please provide a valid email.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;  // Invalid if the email is null
        }

        int length = email.length();
        int atSymbolIndex = -1;
        int dotIndex = -1;

        // Find the index of the '@' symbol
        for (int i = 0; i < length ; i++) {
            if (email.charAt(i) == '@') {
                atSymbolIndex = i;
                break;
            }
        }

        // Check if the '@' symbol is present
        if (atSymbolIndex == -1) {
        JOptionPane.showMessageDialog(null, "Please Enter a valid e-mail format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check if there is at least one character before and after '@'
        if (atSymbolIndex <= 0 || atSymbolIndex >= length - 1) {
        JOptionPane.showMessageDialog(null, "Please Enter a valid e-mail format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Find the index of the '.' symbol after '@'
        for (int i = atSymbolIndex + 1; i < length; i++) {
            if (email.charAt(i) == '.') {
                dotIndex = i;
                break;
            }
        }

        // Check if the dot is directly after the '@' symbol
        if (dotIndex == atSymbolIndex + 1) {
           JOptionPane.showMessageDialog(null, "Please Enter a valid e-mail format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Check for a valid domain (at least one '.' after '@')
        if (dotIndex == -1 || dotIndex >= length - 1) {
          
            return false;
        }
         return true;  // Valid email
    }
        public User getUserByEmail(String email){
            for (User user : users) {
                if(user.getEmail().equals(email))
                    return user;
            }
            return null;
        }

    public static void setCurrentuser(User user) {
        currentuser = user;
    }

    public static User getCurrentuser() {
        return currentuser;
    }
    
    public User getUserById(String id){
            for (User user : users) {
                if(user.getUserId().equals(id))
                    return user;
            }
            return null;
        }
    public User getUserByUsername(String username){
            for (User user : users) {
                if(user.getUsername().equals(username))
                    return user;
            }
            return null;
        }

    public List<User> getUsers() {
        return users;
    }
    
    
    
       
    }
         
    

  
   
    