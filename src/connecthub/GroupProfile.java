/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package connecthub;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
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
 * @author Raz_RAMADAN
 */
public class GroupProfile extends javax.swing.JFrame {
    GroupManagment gm=new GroupManagment();
    HashMap group=new HashMap();
    NewsFeed n;
    private String BIO;
    JSONArray Posts = gm.getposts((String)group.get("ID"));
    /**
     * Creates new form GroupProfile
     */
    public GroupProfile(HashMap group,NewsFeed n) {
        initComponents();
        this.group=group;
        this.n=n;
        this.BIO =(String) group.get("desc");
        jLabel4.setText(BIO);
        String profImg=(String) group.get("ProfileImage");
        String covImg=(String) group.get("CoverImage");
        if (profImg.equals("DefaultProfilePhoto.jpg") && covImg.equals("DefaultCoverPhoto.jpg")) {
            profilephoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/user default.png")));
            coverphoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/background.jpg")));

        } 
        else if (profImg.equals("DefaultProfilePhoto.jpg")) {
            profilephoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/user default.png")));
            coverphoto.setIcon(new ImageIcon(new ImageIcon(covImg).getImage().getScaledInstance(coverphoto.getWidth(), coverphoto.getHeight(), Image.SCALE_SMOOTH)));

        } 
        else if (group.get("CoverImage").equals("DefaultCoverPhoto.jpg")) {
            coverphoto.setIcon(new ImageIcon(getClass().getResource("/connecthub/background.jpg")));
            profilephoto.setIcon(new ImageIcon(new ImageIcon(profImg).getImage().getScaledInstance(profilephoto.getWidth(), profilephoto.getHeight(), Image.SCALE_SMOOTH)));

        } 
        else {
            profilephoto.setIcon(new ImageIcon(new ImageIcon(profImg).getImage().getScaledInstance(profilephoto.getWidth(), profilephoto.getHeight(), Image.SCALE_SMOOTH)));
            coverphoto.setIcon(new ImageIcon(new ImageIcon(covImg).getImage().getScaledInstance(coverphoto.getWidth(), coverphoto.getHeight(), Image.SCALE_SMOOTH)));
        }
        displayPosts();
    }

    private void displayPosts() {
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
                postLabel.setText(post.getString("postcontent"));
                postLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                singlePostPanel.add(postLabel, BorderLayout.NORTH);

                if (post.has("imagepath")) {
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
                 
                 jComboBox1.addItem(post.getString("postcontent"));
                 
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        postPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        profilephoto = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        coverphoto = new javax.swing.JLabel();
        DeletePostButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Description :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 48, Short.MAX_VALUE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
            .addGap(0, 425, Short.MAX_VALUE)
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

        DeletePostButton.setText("Delete Post");
        DeletePostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeletePostButtonActionPerformed(evt);
            }
        });

        jButton6.setText("Group Manage");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton6)
                                .addGap(41, 41, 41)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(DeletePostButton))
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(19, 19, 19))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeletePostButton)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
        n.setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void DeletePostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeletePostButtonActionPerformed
        String postContent=(String) jComboBox1.getSelectedItem();
        if (Posts != null) {
            for (int j = 0; j < Posts.length(); j++) {
                JSONObject post = Posts.getJSONObject(j);
                if(post.getString("postcontent").equals(postContent)){
                    gm.removepost(post.getString("groupid"));
                }
            }
        }
    }//GEN-LAST:event_DeletePostButtonActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        groupmanagmentframe m = new groupmanagmentframe(n);
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeletePostButton;
    private javax.swing.JLabel coverphoto;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel postPanel;
    private javax.swing.JLabel profilephoto;
    // End of variables declaration//GEN-END:variables
}
