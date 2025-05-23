/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package connecthub;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class AddStory extends javax.swing.JFrame {
    NewsFeed n;

    /**
     * Creates new form AddStory
     */
    public AddStory(NewsFeed n) {
        this.n=n;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jimage = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Share");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(37, 37, 37)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jimage, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jimage, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
setVisible(false);
        n.setVisible(true);         // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try{ContentFactory F= new ContentFactory();
       
        StoryManagment s = (StoryManagment) F.createpoststory("newstory");
        User user= UserDatabase.getCurrentuser();
        String content = jTextArea1.getText();
        if (content == null || content.trim().isEmpty()) {
     if (content == null || content.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Content cannot be empty" , "Error", JOptionPane.ERROR_MESSAGE);
            return;
            
        }
    
}
        if(user==null)
        {JOptionPane.showMessageDialog(this, "No user is Currently Loggedin ", "Error", JOptionPane.ERROR_MESSAGE);}

        String imagePath=null;
       
        int result =JOptionPane.showConfirmDialog(this, "Do You want to add a photo to this post ?","Add Photo",JOptionPane.YES_NO_OPTION);
       if(result==JOptionPane.YES_OPTION){
         JFileChooser filechooser=new JFileChooser();
            filechooser.setDialogTitle(imagePath);
            filechooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Image Files","jpg","png","jpeg"));
            int fileResult=filechooser.showOpenDialog(this);
            if(fileResult==JFileChooser.APPROVE_OPTION){
                imagePath=filechooser.getSelectedFile().getAbsolutePath();
                ImageIcon imageIcon = new ImageIcon(imagePath);
                Image img = imageIcon.getImage();
            Image scaledImg = img.getScaledInstance(jimage.getWidth(), jimage.getHeight(), Image.SCALE_AREA_AVERAGING);
            imageIcon = new ImageIcon(scaledImg);
                jimage.setIcon(imageIcon);
            

        }}

        s.setContent(content);
        //s.setTimeStamp(LocalDateTime.now());
         s.setAuthorid(user.getUserId());
        //s.setContentid(UUID.randomUUID().toString());
        if(imagePath!=null)
        {s.setimage(imagePath);}
        JOptionPane.showMessageDialog(this, "Story shared successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        s.addstoryinfile();}catch(Exception e){  JOptionPane.showMessageDialog(this, "Process Failed " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();}
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel jimage;
    // End of variables declaration//GEN-END:variables
}
