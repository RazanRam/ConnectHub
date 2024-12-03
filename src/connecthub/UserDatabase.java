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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.*;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Raz_RAMADAN
 */
public class UserDatabase {
   private static final String filename="users.json";
 //   private Map<String, User> users =new HashMap<>();
 //  private ObjectMapper objectmapper = new ObjectMapper();
  private List<User> users = new ArrayList<>();

    public UserDatabase() {
        loadDatabase();
    }
    
    //read json file 
     private void loadDatabase() {
         File file = new File(filename);
         if (file.exists()) 
         try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder jsonString = new StringBuilder();

              String line;
                while ((line = reader.readLine()) != null) {
                    jsonString.append(line);
                }

                // Parse the JSON array
                JSONArray jsonArray = new JSONArray(jsonString.toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonUser = jsonArray.getJSONObject(i);

                    // Extract user properties
                    String userId = jsonUser.getString("userId");
                    String username = jsonUser.getString("username");
                    String email = jsonUser.getString("email");
                    String password = jsonUser.getString("password");
                    String dateOfBirth = jsonUser.getString("dateOfBirth");
                    boolean isOnline = jsonUser.getBoolean("isOnline");
             User user = new User(userId, username, email, password, dateOfBirth);
                    user.setIsOnline(isOnline);
                    users.add(user);
                }
            } catch (IOException e) {
                System.out.println("Error reading JSON database.");
                e.printStackTrace();

    
          

         //    users = objectmapper.readValue(file, new TypeReference<Map<String, User>>(){} );
             //Map.class
         } else {
             System.out.println("JSON database not found");
         }
    }
     
      public void saveDatabase() {
       //   objectmapper.writeValue(new File(filename), users);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            JSONArray jsonArray = new JSONArray();

            // Convert each user to a JSONObject and add it to the JSON array
            for (User user : users) {
                JSONObject jsonUser = new JSONObject();
                jsonUser.put("userId", user.getUserId());
                jsonUser.put("username", user.getUsername());
                jsonUser.put("email", user.getEmail());
                jsonUser.put("password", user.getHashedPassword());
                jsonUser.put("dateOfBirth", user.getDateOfBirth());
                jsonUser.put("isOnline", user.isIsOnline());

                jsonArray.put(jsonUser);
            }

            // Write the JSON array to the file
            writer.write(jsonArray.toString());
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
        
       for(User x: users){
           if(x.getEmail().equals(email)&&x.getHashedPassword().equals(hashedPass.hashPassword(Password))){
               x.setIsOnline(true);
               saveDatabase();
               return true;
               
           }
           return false;
       }
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
        for(User x : users) {
            if (x.getEmail().equals(email)) {
                return false;
            }
             if (username.isEmpty() || email.isEmpty() || Password.isEmpty() || dateOfBirth.isEmpty()) {
              return false;
       }
        } 
        String hashedPassword = hashedPass.hashPassword(Password);
        User newUser = new User(UUID.randomUUID().toString(), username, email, hashedPassword, dateOfBirth);
        users.add(newUser);
        saveDatabase();
       return true;

    }
    
    public boolean isemailvalid(String email){
           if (email == null) {
            System.out.println("Email is NULL. Please provide a valid email.");
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
           
            return false;
        }

        // Check if there is at least one character before and after '@'
        if (atSymbolIndex <= 0 || atSymbolIndex >= length - 1) {
            
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
           
            return false;
        }

        // Check for a valid domain (at least one '.' after '@')
        if (dotIndex == -1 || dotIndex >= length - 1) {
          
            return false;
        }

        return true;  // Valid email
    }

    }

  
   
    