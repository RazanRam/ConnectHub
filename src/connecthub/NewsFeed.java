/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package connecthub;

import connecthub.UserDatabase;
import glasspopup.DefaultOption;
import glasspopup.GlassPanePopup;
import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import net.miginfocom.layout.ComponentWrapper;
import net.miginfocom.layout.LayoutCallback;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Raz_RAMADAN
 */
public class NewsFeed extends javax.swing.JFrame {

    private UserDatabase database = UserDatabase.getInstance();
    private User user = UserDatabase.getCurrentuser();
    private ProfileManagement profile;
    PostMangmentt pm = new PostMangmentt();
    StoryManagment sm = new StoryManagment();
    FriendsManagment fdb = FriendsManagment.getInstance();
    UserDatabase udb = UserDatabase.getInstance();
    postStoryManagment psm = new postStoryManagment();
    GroupManagment gdb = new GroupManagment();
    NewsfeedService nf = new NewsfeedService();
    HashMap<Integer, HashMap> map=new HashMap<>();


    /**
     * Creates new form NewsFeed
     */
    public NewsFeed() {
        initComponents();
        this.database = database;
        this.user = user;
        profile = new ProfileManagement();
        System.out.println(user);
        showFriends();
        showSuggest();
        showFriendsPosts();
        showStories();
        ShowMyGroups();

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/connecthub/notify.png")); // Replace with your image path
        Image resizedImage = originalIcon.getImage().getScaledInstance(jButton2.getWidth(), jButton2.getHeight(), Image.SCALE_SMOOTH); // Desired size: 50x50
        ImageIcon resizedIcon = new ImageIcon(resizedImage);
        jButton2.setIcon(resizedIcon);
        
        ImageIcon originalIcon2 = new ImageIcon(getClass().getResource("/connecthub/search.png")); // Replace with your image path
        Image resizedImage2 = originalIcon2.getImage().getScaledInstance(jButton4.getWidth(), jButton4.getHeight(), Image.SCALE_SMOOTH); // Desired size: 50x50
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        jButton4.setIcon(resizedIcon2);
        
        ImageIcon originalIcon3 = new ImageIcon(getClass().getResource("/connecthub/groupicon.png")); // Replace with your image path
        Image resizedImage3 = originalIcon3.getImage().getScaledInstance(jButton5.getWidth(), jButton5.getHeight(), Image.SCALE_SMOOTH); // Desired size: 50x50
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        jButton5.setIcon(resizedIcon3);
        
        ImageIcon originalIcon4 = new ImageIcon(getClass().getResource("/connecthub/refreshicon.jpg")); // Replace with your image path
        Image resizedImage4 = originalIcon4.getImage().getScaledInstance(jButton3.getWidth(), jButton3.getHeight(), Image.SCALE_SMOOTH); // Desired size: 50x50
        ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);
        jButton3.setIcon(resizedIcon4);
        
        GlassPanePopup.install(this);

    }

    public void showFriends() {
        ArrayList<String> friendsUserIDs = fdb.getFriendsof(user.getUserId());
        ArrayList<String> friendsUsernames = new ArrayList<>();
        int i = 0;
        for (String id : friendsUserIDs) {
            User u = udb.getUserById(id);
            friendsUsernames.add(u.getUsername());
        }
        String[] arr1 = new String[friendsUsernames.size()];
        String[] arr2 = friendsUsernames.toArray(arr1);
        MyFriendsList.setListData(arr2);
    }

    public void showSuggest() {
        ArrayList<String> friendsUserIDs = fdb.getSuggestedTo(user.getUserId());
        ArrayList<String> friendsUsernames = new ArrayList<>();
        for (String id : friendsUserIDs) {
            User u = udb.getUserById(id);
            friendsUsernames.add(u.getUsername());
        }
        String[] arr1 = new String[friendsUsernames.size()];
        String[] arr2 = friendsUsernames.toArray(arr1);
        suggList.setListData(arr2);
    }
    
    public void ShowMyGroups(){
        ArrayList<HashMap> mygroups=gdb.getMyGroups();
        ArrayList<String> grpNAME=new ArrayList<>();
        int i=0;
        for(HashMap g:mygroups){
            String grpName=(String) g.get("name");
            grpNAME.add(grpName);
            map.put(i, g);
            i++;
        }
        String[] arr1 = new String[grpNAME.size()];
        String[] arr2 = grpNAME.toArray(arr1);
        myGroups1.setListData(arr2);
    }

    public void showStories() {
        System.out.println("1234");
        ArrayList<String> friendsUserIDs = fdb.getFriendsof(user.getUserId());
        for (String friendId : friendsUserIDs) {
            JSONArray friendstories = sm.getstorytbyuserid(friendId);
            System.out.println(friendstories);
            System.out.println("1234");
            long currenttime = Instant.now().toEpochMilli();

            if (friendstories != null) {
                for (int j = 0; j < friendstories.length(); j++) {
                    JSONObject story = friendstories.getJSONObject(j);
                    long timestamp = story.optLong("timestamp", -1);
                    if (timestamp != -1) {
                        long difference = (currenttime - timestamp) / (1000 * 60 * 60);
                        System.out.println("Time difference (hours): " + difference);

                        // Create a new label for the post
                        if (difference <= 24) {
                            JLabel storyLabel = new JLabel();
                            storyLabel.setText(story.getString("content"));

                            if (story.has("imagePath")) {
                                ImageIcon postImage = new ImageIcon(story.getString("imagePath"));
                                storyLabel.setIcon(postImage); // Add image
                            }

                            storiespanel.add(storyLabel);// Add label to the panel
                        }
                    }
                }
            }
        }
        storiespanel.setLayout(new BoxLayout(storiespanel, BoxLayout.Y_AXIS));
        storiespanel.revalidate();
        storiespanel.repaint();

        // Add the panel to the frame with scrolling
    }

    public void showFriendsPosts() {
        ArrayList<String> friendsUserIDs = fdb.getFriendsof(user.getUserId());

        for (String friendId : friendsUserIDs) {
            JSONArray friendPosts = pm.getpostbyuserid(friendId);
           
            System.out.println(friendPosts);
            if (friendPosts != null) {
                
                for (int j = 0; j < friendPosts.length(); j++) {
                    JSONObject post = friendPosts.getJSONObject(j);

                    // Create a new label for the post
                    JLabel postLabel = new JLabel();
                    postLabel.setText(post.getString("content"));

                    if (post.has("imagePath")) {
                        ImageIcon postImage = new ImageIcon(post.getString("imagePath"));
                        postLabel.setIcon(postImage); // Add image
                    }

                    postspanel.add(postLabel); // Add label to the panel
                }
            }
        }
        postspanel.setLayout(new BoxLayout(postspanel, BoxLayout.Y_AXIS));
        postspanel.revalidate();
        postspanel.repaint();

        // Add the panel to the frame with scrolling
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        myGroups = new javax.swing.JList<>();
        LogOutButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        friendspage = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MyFriendsList = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        suggList = new javax.swing.JList<>();
        addpost = new javax.swing.JButton();
        addstory = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        postspanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        storiespanel = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        myGroups1 = new javax.swing.JList<>();
        jButton7 = new javax.swing.JButton();

        jScrollPane4.setViewportView(myGroups);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("NewsFeed Frame");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        LogOutButton.setBackground(new java.awt.Color(204, 204, 255));
        LogOutButton.setText("LogOut");
        LogOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogOutButtonActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(204, 204, 255));
        jLabel1.setText("NewsFeed Page");
        jLabel1.setOpaque(true);

        jButton1.setText("My Profile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        friendspage.setText("Friends");
        friendspage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendspageActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(MyFriendsList);

        jScrollPane2.setViewportView(suggList);

        addpost.setText("Add Post");
        addpost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addpostActionPerformed(evt);
            }
        });

        addstory.setText("Add Story");
        addstory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addstoryActionPerformed(evt);
            }
        });

        postspanel.setBackground(new java.awt.Color(255, 255, 255));
        postspanel.setToolTipText("");

        javax.swing.GroupLayout postspanelLayout = new javax.swing.GroupLayout(postspanel);
        postspanel.setLayout(postspanelLayout);
        postspanelLayout.setHorizontalGroup(
            postspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1125, Short.MAX_VALUE)
        );
        postspanelLayout.setVerticalGroup(
            postspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 222, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(postspanel);

        storiespanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout storiespanelLayout = new javax.swing.GroupLayout(storiespanel);
        storiespanel.setLayout(storiespanelLayout);
        storiespanelLayout.setHorizontalGroup(
            storiespanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1134, Short.MAX_VALUE)
        );
        storiespanelLayout.setVerticalGroup(
            storiespanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 146, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(storiespanel);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connecthub/notify.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connecthub/refreshicon.jpg"))); // NOI18N
        jButton3.setOpaque(true);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connecthub/search.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connecthub/groupicon.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jScrollPane6.setViewportView(myGroups1);

        jButton7.setText("Show Group");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addstory)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(addpost)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(friendspage, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton7)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(LogOutButton)
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addpost)
                            .addComponent(friendspage)
                            .addComponent(jButton7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addstory)
                            .addComponent(jButton1)))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LogOutButton))
                .addGap(12, 88, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LogOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogOutButtonActionPerformed
        // TODO add your handling code here:
        database.logout(user.getEmail());
        MainWindow frame = MainWindow.getInstance();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LogOutButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        profileframe proframe = new profileframe(database, user, profile, this);
        proframe.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void friendspageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendspageActionPerformed
        Friends f = new Friends(this);
        f.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_friendspageActionPerformed

    private void addpostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addpostActionPerformed
        AddPost a = new AddPost(this);
        a.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_addpostActionPerformed

    private void addstoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addstoryActionPerformed
        AddStory a = new AddStory(this);
        a.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_addstoryActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        database.logout(user.getEmail());
    }//GEN-LAST:event_formWindowClosed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

       NotificationPanel notificationPanel = new NotificationPanel();
        GlassPanePopup.showPopup(notificationPanel);
// When a friend request is sent or received
//notificationPanel.refreshNotifications();

// In your main frame or wherever notifications are displayed
//add(notificationPanel);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //NotificationPanel notificationPanel = new NotificationPanel();
        //GlassPanePopup.showPopup(notificationPanel);

        user.deleteNotifications();
        
     
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         creategroupframe f = new creategroupframe(this);
        f.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        if(myGroups1.getSelectedIndex()==-1){
            JOptionPane.showMessageDialog(this, "you need to select", "warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            int i=myGroups1.getSelectedIndex();
            HashMap grp=map.get(i);
            System.out.println(grp);
            System.out.println(grp.get("ID"));
            GroupProfile gp=new GroupProfile(grp, this);
            setVisible(false);
            gp.setVisible(true);
        } catch (Exception o) {
            JOptionPane.showMessageDialog(this, "An error occurred 122: " + o.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         SearchFrame sf=new SearchFrame(this);
        sf.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LogOutButton;
    private javax.swing.JList<String> MyFriendsList;
    private javax.swing.JButton addpost;
    private javax.swing.JButton addstory;
    private javax.swing.JButton friendspage;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JList<String> myGroups;
    private javax.swing.JList<String> myGroups1;
    private javax.swing.JPanel postspanel;
    private javax.swing.JPanel storiespanel;
    private javax.swing.JList<String> suggList;
    // End of variables declaration//GEN-END:variables
}
