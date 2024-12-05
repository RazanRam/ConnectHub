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
    private final UserDatabase userDatabase;

    public ProfileManagement() {
        this.userDatabase = UserDatabase.getInstance();
    }
public void editProfilePhoto(String ID,File newFileForPhotos){
User user=userDatabase.getUserById(ID);
if(user == null){
System.err.println("The user is not found.");
return;
}
String profilePhotoPath="user_PP/profilePhoto_Edit/";
String newProfilePhotoPath=profilePhotoPath+ID+"_PP.jpg";
try{
File Editable=new File(profilePhotoPath);
if(!Editable.exists){
Editable.mkdirs();
}
Files.copy(newFileForPhotos.toPath(), new File(newProfilePhotoPath).toPath(), StandardCopyOption.REPLACE_EXISTING);

}


}
public void editCoverPhoto()
public void editBio()
public void editPassword()

}
