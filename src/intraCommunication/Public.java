/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intraCommunication;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.ListModel;

/**
 *
 * @author alger
 */
public class Public extends javax.swing.JFrame {

    String ID;
    String USER;
    //String NICK;
    Connection conn = null;
    Statement st = null;
    PreparedStatement ps = null;
    ResultSet result = null;
    /**
     * Creates new form Public
     */
    public Public() {
        initComponents();
        setLocationRelativeTo(null);
       
    }
    
    public void NewmsgDisplay(){
        msg_area.setText("");
        String message = "";
        String username = "";
        String messageDisplay = "";
        //String id = "";
        String sql1 = "SELECT * FROM public_chat ";
            try{
                ps = conn.prepareStatement(sql1);
                result = ps.executeQuery();
                while(result.next()){
                    message = result.getString("message");
                    username = result.getString("name");
                    //id = result.getString("id");
                    messageDisplay = username + " Says: " + message +"\n";
                    msg_area.append(messageDisplay);
                    msg_area.setCaretPosition(msg_area.getDocument().getLength());
                }   
            }catch(Exception ex){
                System.out.println(ex);
            }
    }
    public void UsersList(){
        DefaultListModel List = new DefaultListModel();
        String sql = "SELECT * FROM perlin WHERE (status = 'in') AND NOT (name = '"+USER+"') ";
        try{
            ps = conn.prepareStatement(sql);
            result = ps.executeQuery();
            while(result.next()){
                String user = result.getString("name");
                List.addElement(user);
                userList.setModel(List);
            }
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        
    }
    
    public void ListMenu(){
      
        userList.addMouseListener(new MouseAdapter(){
        @Override
        public void mouseClicked(MouseEvent e) {
            int index  = userList.getSelectedIndex();
            String s = (String) userList.getSelectedValue();
            final String selectedUser = userList.getSelectedValue();
            if(e.getButton() ==  MouseEvent.BUTTON1 && e.getClickCount() == 1 && index != -1){
                JPopupMenu pop = new JPopupMenu();
                //JMenuItem menu0 = new JMenuItem("View Information");
                //JMenuItem menu1 = new JMenuItem("Add Friend");
                JMenuItem menu0 = new JMenuItem("Private Message");
                ActionListener menuListener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    if("Private Message".equals(event.getActionCommand())){
                        Private pm = new Private();
                        pm.username = selectedUser;
                        pm.yourUsername = USER;
                        pm.show();
                        
                    }
                 
             
                    
                    else if("Add Friend".equals(event.getActionCommand())){
                        int opt = JOptionPane.showConfirmDialog(null, "Confirm Friend Request?","Confirmation",JOptionPane.YES_NO_OPTION);
                        if(opt != JOptionPane.NO_OPTION){
                            String sql = "INSERT INTO request(request,requestSender,requestReceiver,status)VALUES('Add Friend','"+USER+"','"+selectedUser+"','pending')";
                            try{    
                                st = conn.createStatement();
                                if(st.executeUpdate(sql) == 1){
                                    JOptionPane.showMessageDialog(null, "Friend Request Sent");
                                }
                            
                            }catch(Exception ex){
                                System.out.println(ex);
                            }
                        }
                    }
                }
                    
                };
                ;
                pop.add(menu0);
                
                menu0.addActionListener(menuListener);
               
                userList.setComponentPopupMenu(pop);    
            }   
          }
    });
    }
    
    /*public void Notification(){
        
        String[] info = new String [5];
        String sql = "SELECT * FROM private_chat WHERE status = 'pending' AND recepient = '"+USER+"'";
        try{
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                info[0] = rs.getString("msg_id");
                info[1] = rs.getString("message");
                info[2] = rs.getString("sender");
                info[3] = rs.getString("recepient");
                info[4] = rs.getString("status"); 
                String sql1 = "SELECT * FROM private_chat WHERE status = 'pending' AND recepient = '"+USER+"' ORDER BY sender";
                try{
                    ps = conn.prepareStatement(sql1);
                    rs = ps.executeQuery();
                    while(rs.next()){
                        String user = rs.getString("recepient");
                        String sender = rs.getString("sender");
                        String status = rs.getString("status");
                        String id = rs.getString("msg_id");
                        if("pending".equals(status)){
                            JOptionPane.showMessageDialog(null, sender + " sent you a private message");
                            Private pm = new Private();
                            pm.username = sender;
                            pm.yourUsername = USER;
                            String sqll = "UPDATE private_chat SET status = 'seen' WHERE msg_id = '"+id+"'";
                            try{
                                stmt = conn.createStatement();
                                stmt.executeUpdate(sqll);
                            }catch(Exception ex){
                                System.out.println(ex);
                            }
                            pm.show();
                            
                        }

                    }
                    
                }catch(Exception ex){
                    System.out.println(ex);
                }
            }
        
        }catch(Exception ex){
            System.out.println(ex);
        };
        
    }*/
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msg_area = new javax.swing.JTextArea();
        msg_tf = new javax.swing.JTextField();
        sendb = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        userList = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));

        jPanel3.setBackground(new java.awt.Color(0, 51, 255));

        jPanel2.setBackground(new java.awt.Color(0, 51, 255));

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 250, 250)));

        msg_area.setEditable(false);
        msg_area.setColumns(20);
        msg_area.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        msg_area.setRows(5);
        msg_area.setAutoscrolls(false);
        msg_area.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        msg_area.setDropMode(javax.swing.DropMode.INSERT);
        msg_area.setFocusable(false);
        msg_area.setRequestFocusEnabled(false);
        msg_area.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                msg_areaInputMethodTextChanged(evt);
            }
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                msg_areaCaretPositionChanged(evt);
            }
        });
        jScrollPane1.setViewportView(msg_area);

        msg_tf.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N

        sendb.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        sendb.setText("SEND");
        sendb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbActionPerformed(evt);
            }
        });

        userList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Active User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        userList.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        userList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(userList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
                    .addComponent(msg_tf))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sendb, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(msg_tf))
                .addGap(30, 30, 30))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("public Message");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 24, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(76, 76, 76))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        conn =  DBConnect.getConnection();
        ListMenu();
        msg_area.getCaret().setVisible(true);
        UsersList();
        NewmsgDisplay();
        Runnable helloRunnable = new Runnable() {
        public void run() {
//            Notification();
            NewmsgDisplay();
        }
        };
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(helloRunnable, 0, 1000, TimeUnit.MILLISECONDS);
    
    }//GEN-LAST:event_formWindowOpened

    private void sendbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbActionPerformed
        String msg = msg_tf.getText();
        String sql = "INSERT INTO public_chat(message,name)VALUES('"+msg+"','"+USER+"')";
        try{
            st = conn.createStatement();
            st.executeUpdate(sql);
//                msg_area.append(NICK + " says: "+msg +"\n");
            
        }catch(Exception ex){
            System.out.println(ex);
        }
        msg_tf.setText("");
    }//GEN-LAST:event_sendbActionPerformed

    private void userListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userListMouseClicked

    }//GEN-LAST:event_userListMouseClicked

    private void msg_areaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_msg_areaCaretPositionChanged

    }//GEN-LAST:event_msg_areaCaretPositionChanged

    private void msg_areaInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_msg_areaInputMethodTextChanged

    }//GEN-LAST:event_msg_areaInputMethodTextChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Public.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Public.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Public.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Public.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Public().setVisible(true);
                
            }
            
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea msg_area;
    private javax.swing.JTextField msg_tf;
    private javax.swing.JButton sendb;
    private javax.swing.JList<String> userList;
    // End of variables declaration//GEN-END:variables
}
