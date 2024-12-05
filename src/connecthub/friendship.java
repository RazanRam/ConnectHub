/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

/**
 *
 * @author janaf
 */
public class friendship {
    private String UserID1;
    private String UserID2;

    public friendship(String UserID1, String UserID2) {
        this.UserID1 = UserID1;
        this.UserID2 = UserID2;
    }

    public String getUserID1() {
        return UserID1;
    }

    public void setUserID1(String UserID1) {
        this.UserID1 = UserID1;
    }

    public String getUserID2() {
        return UserID2;
    }

    public void setUserID2(String UserID2) {
        this.UserID2 = UserID2;
    }
    
    public boolean hasUser(String userid){
        if(userid.equals(UserID1)|| userid.equals(UserID2))
            return true;
        return false;
    }

}
