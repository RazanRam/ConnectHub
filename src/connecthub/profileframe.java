/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package connecthub;

import connecthub.UserDatabase;
import connecthub.Post;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author malok
 */
public class profileframe extends javax.swing.JFrame {

    private postStoryManagment pst = new postStoryManagment();
    private UserDatabase database;
    private User user;
    private ProfileManagement profile;
    private NewsFeed frame;
    private String BIO;

    PostMangmentt pm = new PostMangmentt();

    /**
     * Creates new form profileframe
     */
    public profileframe(UserDatabase database, User user, ProfileManagement profile, NewsFeed frame) {
        initComponents();

        this.database = database;
        this.user = user;
        this.profile = profile;
        this.frame = frame;
        this.BIO = user.getBio();
        jLabel4.setText(BIO);
        if (user.getProfilePhotoPath().equals("DefaultProfilePhoto.jpg") && user.getCoverPhotoPath().equals("DefaultCoverPhoto.jpg")) {
            profilephoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/user default.png")));
            coverphoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/background.jpg")));

        } 
        else if (user.getProfilePhotoPath().equals("DefaultProfilePhoto.jpg")) {
            profilephoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/user default.png")));
            coverphoto.setIcon(new ImageIcon(new ImageIcon(user.getCoverPhotoPath()).getImage().getScaledInstance(coverphoto.getWidth(), coverphoto.getHeight(), Image.SCALE_SMOOTH)));

        } 
        else if (user.getCoverPhotoPath().equals("DefaultCoverPhoto.jpg")) {
            coverphoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/background.jpg")));
            profilephoto.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhotoPath()).getImage().getScaledInstance(profilephoto.getWidth(), profilephoto.getHeight(), Image.SCALE_SMOOTH)));

        } 
        else {
            profilephoto.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhotoPath()).getImage().getScaledInstance(profilephoto.getWidth(), profilephoto.getHeight(), Image.SCALE_SMOOTH)));
            coverphoto.setIcon(new ImageIcon(new ImageIcon(user.getCoverPhotoPath()).getImage().getScaledInstance(coverphoto.getWidth(), coverphoto.getHeight(), Image.SCALE_SMOOTH)));
        }
        userLabel.setText(user.getUsername());
        displayPosts();
    }

    private void displayPosts() {
        JSONArray Posts = pm.getpostbyuserid(user.getUserId());
        if (Posts != null) {
            for (int j = 0; j < Posts.length(); j++) {
                JSONObject post = Posts.getJSONObject(j);
                JPanel singlePostPanel = new JPanel();
                singlePostPanel.setLayout(new BorderLayout());
                singlePostPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                singlePostPanel.setPreferredSize(new Dimension(150, 200));
                singlePostPanel.setBackground(Color.WHITE);

                // Create a new label for the post
                JLabel postLabel = new JLabel();
                postLabel.setText(post.getString("content"));
                postLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                singlePostPanel.add(postLabel, BorderLayout.NORTH);

                if (post.has("imagePath")) {
                    ImageIcon imageIcon = resizeImage(post.getString("imagePath"), 120, 150); // Resize for a smaller image
                    JLabel imageLabel = new JLabel(imageIcon);
                    singlePostPanel.add(imageLabel, BorderLayout.CENTER);
                } else {
                    JLabel noImageLabel = new JLabel("No image available.");
                    noImageLabel.setHorizontalAlignment(JLabel.CENTER);
                    singlePostPanel.add(noImageLabel, BorderLayout.CENTER);
                }

                postPanel.add(singlePostPanel);
                 postPanel.add(Box.createRigidArea(new Dimension(10, 0)));
            }

        }
        postPanel.setBackground(Color.WHITE);

        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
        postPanel.revalidate();
        postPanel.repaint();

    }

    private ImageIcon resizeImage(String path, int width, int height) {
        // Create ImageIcon from file path
        ImageIcon imageIcon = new ImageIcon(path);
        // Get the Image from the ImageIcon
        Image image = imageIcon.getImage();
        // Scale the image to fit the specified width and height
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // Return the new ImageIcon with the resized image
        return new ImageIcon(newImage);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        editButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        viewButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        postPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        profilephoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        coverphoto = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Profile Management");
        setBackground(new java.awt.Color(204, 204, 255));

        editButton.setText("Edit Profile");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Bio :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 70, Short.MAX_VALUE)))
                .addContainerGap())
        );

        viewButton.setText("View Friends");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Home Page");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        postPanel.setBackground(new java.awt.Color(255, 255, 255));
        postPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout postPanelLayout = new javax.swing.GroupLayout(postPanel);
        postPanel.setLayout(postPanelLayout);
        postPanelLayout.setHorizontalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );
        postPanelLayout.setVerticalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 249, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(postPanel);

        profilephoto.setBackground(new java.awt.Color(255, 255, 255));
        profilephoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connecthub/user default.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 158, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(profilephoto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 153, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(profilephoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))
        );

        coverphoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/connecthub/background.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(coverphoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(coverphoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
        );

        userLabel.setFont(new java.awt.Font("Candara", 1, 16)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 27, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(viewButton)
                        .addContainerGap(238, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(editButton)
                            .addComponent(viewButton)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        Editprofilelist frame1 = new Editprofilelist(database, user, this, profilephoto, coverphoto, profile);
        frame1.setVisible(true);
        this.dispose();

        if (user.getProfilePhotoPath().equals("DefaultProfilePhoto.jpg") && user.getCoverPhotoPath().equals("DefaultCoverPhoto.jpg")) {
            profilephoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/user default.png")));
            coverphoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/background.jpg")));

        } 
        else if (user.getProfilePhotoPath().equals("DefaultProfilePhoto.jpg")) {
            profilephoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/user default.png")));
                    coverphoto.setIcon(new ImageIcon(new ImageIcon(user.getCoverPhotoPath()).getImage().getScaledInstance(coverphoto.getWidth(), coverphoto.getHeight(), Image.SCALE_SMOOTH)));

        } 
        else if (user.getCoverPhotoPath().equals("DefaultCoverPhoto.jpg")) {
            coverphoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/background.jpg")));
            profilephoto.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhotoPath()).getImage().getScaledInstance(profilephoto.getWidth(), profilephoto.getHeight(), Image.SCALE_SMOOTH)));

        } 
        else {
            profilephoto.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhotoPath()).getImage().getScaledInstance(profilephoto.getWidth(), profilephoto.getHeight(), Image.SCALE_SMOOTH)));
            coverphoto.setIcon(new ImageIcon(new ImageIcon(user.getCoverPhotoPath()).getImage().getScaledInstance(coverphoto.getWidth(), coverphoto.getHeight(), Image.SCALE_SMOOTH)));
        }
        jLabel4.setText(user.getBio());


    }//GEN-LAST:event_editButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        // TODO add your handling code here:
        ViewFriends frame2 = new ViewFriends(database, user, this);
        frame2.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_viewButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        frame.setVisible(true);


    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel coverphoto;
    private javax.swing.JButton editButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel postPanel;
    private javax.swing.JLabel profilephoto;
    private javax.swing.JLabel userLabel;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
