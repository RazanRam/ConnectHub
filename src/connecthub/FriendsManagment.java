/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connecthub;

import java.util.ArrayList;

/**
 *
 * @author janaf
 */
public class FriendsManagment {

    public static FriendsManagment fm = null;

    friendDataBase fdb = friendDataBase.getInstance();
    UserDatabase udb = UserDatabase.getInstance();

    private static String friendsFILE = "friends.json";
    private static String friendsrequestsFILE = "friendsrequests.json";
    private static String blocksFILE = "blocks.json";

    private ArrayList<friendship> Friends;
    private ArrayList<friendship> FriendRqustes;
    private ArrayList<friendship> Blocks;

    private FriendsManagment() {
        Friends = fdb.LoadDATAbase(friendsFILE);
        FriendRqustes = fdb.LoadDATAbase(friendsrequestsFILE);
        Blocks = fdb.LoadDATAbase(blocksFILE);
    }

    public static FriendsManagment getInstance() {
        if (fm == null) {
            fm = new FriendsManagment();
        }
        return fm;
    }

    //user1 sent a friend request to user2
    //user1 sender
    //user2 reciever
    public void FriendRequest(String user1, String user2) {

        friendship f = new friendship(user1, user2);
        FriendRqustes.add(f);
        Notifications notify = new Notifications("Friend Request", user1 + " sent a friend request");
        UserDatabase udb = UserDatabase.getInstance();
        udb.addNotificationToUser(user2, notify);
        fdb.saveDatabase(friendsrequestsFILE, FriendRqustes);
    }

    //user1 accept a friend request from user2
    public void AcceptRrquest(String user1, String user2) {
        for (friendship f : FriendRqustes) {
            if (f.getUserID1().equals(user2) && f.getUserID2().equals(user1)) {
                FriendRqustes.remove(f);
                break;
            }
        }
        friendship f = new friendship(user2, user1);
        Friends.add(f);
        Notifications notifyAccepter = new Notifications("Accepted", user1 + " accepted your friend request");
        UserDatabase udb = UserDatabase.getInstance();
        udb.addNotificationToUser(user2, notifyAccepter);

        fdb.saveDatabase(friendsrequestsFILE, FriendRqustes);
        fdb.saveDatabase(friendsFILE, Friends);
    }

    //user1 decline a friend request from user2
    public void DeclineRequest(String user1, String user2) {
        for (friendship f : FriendRqustes) {
            if (f.getUserID1().equals(user2) && f.getUserID2().equals(user1)) {
                FriendRqustes.remove(f);
                break;
            }
        }
        Notifications notifyDeclined = new Notifications("Declined", user1 + " declined your friend request");
        UserDatabase udb = UserDatabase.getInstance();
        udb.addNotificationToUser(user2, notifyDeclined);

        fdb.saveDatabase(friendsrequestsFILE, FriendRqustes);

    }

    //user1 blocks user2
    public void Block(String user1, String user2) {
        for (friendship f : Friends) {
            if (f.hasUser(user2) && f.hasUser(user1)) {
                Friends.remove(f);
                break;
            }
        }
        friendship f = new friendship(user1, user2);
        Blocks.add(f);

        fdb.saveDatabase(blocksFILE, Blocks);
        fdb.saveDatabase(friendsFILE, Friends);
    }

    //user1 removes user2
    public void Remove(String user1, String user2) {
        for (friendship f : Friends) {
            if (f.hasUser(user2) && f.hasUser(user1)) {
                Friends.remove(f);
                break;
            }
        }

        fdb.saveDatabase(friendsFILE, Friends);
    }

    public ArrayList<String> getFriendsof(String user1) {
        ArrayList<String> U1Friends = new ArrayList<>();
        for (friendship f : Friends) {
            if (f.getUserID1().equals(user1)) {
                U1Friends.add(f.getUserID2());
            } else if (f.getUserID2().equals(user1)) {
                U1Friends.add(f.getUserID1());
            }
        }
        return U1Friends;
    }

    public ArrayList<String> getFriendRequestsof(String user1) {
        ArrayList<String> U1Friendreq = new ArrayList<>();
        for (friendship f : FriendRqustes) {
            if (f.getUserID2().equals(user1)) {
                U1Friendreq.add(f.getUserID1());
            }
        }
        return U1Friendreq;
    }

    //List of people who user1 blocked
    public ArrayList<String> getBlocksof(String user1) {
        ArrayList<String> U1Blocks = new ArrayList<>();
        for (friendship f : Blocks) {
            if (f.getUserID1().equals(user1)) {
                U1Blocks.add(f.getUserID2());
            } else if (f.getUserID2().equals(user1)) {
                U1Blocks.add(f.getUserID1());
            }
        }
        return U1Blocks;
    }

    /*get friends of user1
    for each friend of user1 --> get their friends
    suggest these friends to user1 on condition:
    1)not already a friend
    2)not Blocked by user1
    3)not already in suggestions list
    4)not in friendRequests list
    5)not the current user 
     */
    public ArrayList<String> getSuggestedTo(String user1) {
        ArrayList<String> U1SuggestedFriends = new ArrayList<>();
        ArrayList<String> U1Blocks = getBlocksof(user1);
        ArrayList<String> U1req = getFriendRequestsof(user1);

        ArrayList<String> U1Friends = getFriendsof(user1);
        for (String Friend : U1Friends) {

            ArrayList<String> FriendsofFriend = getFriendsof(Friend);
            for (String person : FriendsofFriend) {
                if (!U1Friends.contains(person) && !U1Blocks.contains(person) && !U1SuggestedFriends.contains(person) && !U1req.contains(person) && !person.contains(user1)) {
                    U1SuggestedFriends.add(person);
                }
            }
        }
        return U1SuggestedFriends;
    }

}
