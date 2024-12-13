/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package connecthub;

import connecthub.User;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author malok
 */
public class NotificationPanel extends javax.swing.JPanel {

    private final UserDatabase udb = UserDatabase.getInstance();
    private GroupManagment gm = new GroupManagment();

    /**
     * Creates new form NotificationPanel
     */
    public NotificationPanel() {
        initComponents();
        setOpaque(false);
        jScrollPane1.getViewport().setOpaque(false);
        jScrollPane1.setViewportBorder(null);
        jPanel1.setLayout(new MigLayout("inset 0,fillx,wrap", "[fill]"));
        loadNotification();
    }

    private void loadNotification() {
        User user = udb.getCurrentuser();
        ImageIcon icon;
        if (user == null) {
            return;
        }
        /* FriendsManagment fm=FriendsManagment.getInstance();
       ArrayList<String> friendrequests=fm.getFriendRequestsof(user.getUserId());*/

        ArrayList<Notifications> userNotifications = (ArrayList<Notifications>) udb.getUserNotifications(user.getUserId());
        for (Notifications notification : userNotifications) {

            if (!notification.isRead()) {
                NotificationType(notification);

            }
        }

        // jPanel1.add(new notificationFR(new ImageIcon(getClass().getResource("/connecthub/user default.png")),"hala","10-10-2024"));
    }

    private void NotificationType(Notifications notification) {
        String sender = extractsSenderFromDescription(notification.getDescription());
        User user2 = udb.getUserById(sender);
        ImageIcon icon;
        ImageIcon icon2;
        HashMap group = new HashMap();
        ArrayList<HashMap> groups = gm.getGroups();
        if (user2.getProfilePhotoPath().equals("DefaultProfilePhoto.jpg")) {

            icon = new ImageIcon(getClass().getResource("/connecthub/user default.png"));
        } else {
            icon = new ImageIcon(user2.getProfilePhotoPath());
        }

        String time = formatTimestamp(notification.getTimestamp());
        switch (notification.getType()) {
            case "Friend Request":
                addNotification(icon, user2.getUsername(), time, notification);
                break;
            case "Accepted":
                jPanel1.add(new notificationType(icon, user2.getUsername(), time, " Accepted your friend request"));
                jPanel1.revalidate();
                jPanel1.repaint();
                notification.Read();
                udb.saveDatabase();

                break;
            case "Declined":
                jPanel1.add(new notificationType(icon, user2.getUsername(), time, " Declined your friend request"));
                jPanel1.revalidate();
                jPanel1.repaint();
                notification.Read();
                udb.saveDatabase();

                break;
            case "Friends Posts":
                jPanel1.add(new notificationType(icon, user2.getUsername(), time, " Added a new Post"));
                jPanel1.revalidate();
                jPanel1.repaint();
                notification.Read();
                udb.saveDatabase();
                break;
            case "Group Added":
                for (HashMap grp : groups) {
                    if (grp.get("ID").equals(sender)) {
                        group = grp;
                    }

                }
                 if (group.get("").equals("DefaultProfilePhoto.jpg")) {

            icon = new ImageIcon(getClass().getResource("/connecthub/user default.png"));
        } else {
            icon = new ImageIcon((String) group.get(""));
        }
                jPanel1.add(new notificationType(icon2, (String) group.get("name"), time, " You are Added to a new Group "));
                jPanel1.revalidate();
                jPanel1.repaint();
                notification.Read();
                udb.saveDatabase();
                break;
        }

    }

    private String extractsSenderFromDescription(String description) {

        String[] userId = description.split(" ");
        return userId[0];
    }

    private String formatTimestamp(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm");
        return sdf.format(new Date(timestamp));
    }

    private void addNotification(Icon icon, String username, String time, Notifications notify) {
        notificationFR notification = new notificationFR(icon, username, time);
        notification.getjButton1().addActionListener(e -> {
            FriendsManagment fm = FriendsManagment.getInstance();
            User user = udb.getCurrentuser();
            User user2 = udb.getUserByUsername(username);
            fm.AcceptRrquest(user.getUserId(), user2.getUserId());
            notify.Read();
            jPanel1.remove(notification);
            jPanel1.revalidate();
            jPanel1.repaint();
        });
        notification.getjButton2().addActionListener(e -> {
            FriendsManagment fm = FriendsManagment.getInstance();
            User user = udb.getCurrentuser();
            User user2 = udb.getUserByUsername(username);
            fm.DeclineRequest(user.getUserId(), user2.getUserId());
            notify.Read();
            jPanel1.remove(notification);
            jPanel1.revalidate();
            jPanel1.repaint();
        });
        jPanel1.add(notification);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    @Override
    protected void paintComponent(Graphics graphic) {
        Graphics2D g2 = (Graphics2D) graphic.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int header = 10;
        AffineTransform trans = new AffineTransform();
        trans.translate(getWidth() - 25, 5);
        trans.rotate(Math.toRadians(45));
        Path2D p = new Path2D.Double(new RoundRectangle2D.Double(0, 0, 20, 20, 5, 5), trans);
        Area area = new Area(p);
        area.add(new Area(new RoundRectangle2D.Double(0, header, getWidth(), getHeight() - header, 10, 10)));
        g2.fill(area);
        g2.dispose();
        super.paintComponent(graphic);
    }

    public void refreshNotifications() {
        jPanel1.removeAll();
        loadNotification();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 10, 10, 10));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(78, 78, 78));
        jLabel1.setText("Notifications");

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 491, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        jButton1.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 153, 255));
        jButton1.setText("Show All");
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
