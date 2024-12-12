/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package connecthub.search;

import connecthub.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
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
 * @author janaf
 */
public class Search_ViewProfile extends javax.swing.JFrame {
    User user;
    SearchFrame sf;
    PostMangmentt pm = new PostMangmentt();
    private String BIO;
    /**
     * Creates new form Search_ViewProfile
     */
    public Search_ViewProfile(User user,SearchFrame sf) {
        initComponents();
        this.user=user;
        this.sf=sf;
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

        jPanel3 = new javax.swing.JPanel();
        coverphoto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        userLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        postPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        profilephoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(639, 461));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setText("Bio :");

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
            .addGap(0, 32, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(userLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        postPanel.setBackground(new java.awt.Color(255, 255, 255));
        postPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout postPanelLayout = new javax.swing.GroupLayout(postPanel);
        postPanel.setLayout(postPanelLayout);
        postPanelLayout.setHorizontalGroup(
            postPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(29, 29, 29))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        setVisible(false);
        sf.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel coverphoto;
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
    // End of variables declaration//GEN-END:variables
}
