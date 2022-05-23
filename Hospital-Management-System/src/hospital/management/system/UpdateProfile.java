package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class UpdateProfile extends javax.swing.JFrame {

    private String employee;
    private int id;

    public UpdateProfile(String employee, int id) {
        initComponents();
        setLocation(285, 80);
        this.employee = employee;
        this.id = id;
    }

    String username;
    String password;

    private Connection con;
    private PreparedStatement pst;

    public Connection DbConnection() throws SQLException {
        String url = "jdbc:sqlserver://SAJIB\\SQLEXPRESS:1433;databaseName=HospitalManagement";
        String user = "sm";
        String password = "sm02106";
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Database can not be connected");
            e.printStackTrace();
        }
        return con;
    }

    public void addToDB() throws SQLException {
        con = DbConnection();
        String sql = null;
        if (employee == "receptionist") {
            sql = "UPDATE RECEPTIONIST SET UserName=?, UserPassword=? WHERE ReceptionistID=";
        } else if (employee == "doctor") {
            sql = "UPDATE DOCTOR SET UserName=?, UserPassword=? WHERE DoctorID=";
        } else if (employee == "pharmacist") {
            sql = "UPDATE PHARMACIST SET UserName=?, UserPassword=? WHERE PharmacistID=";
        }

        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement(sql + id);
                pst.setString(1, username);
                pst.setString(2, password);
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Successfully Updated");
                jTextField1.setText("");
                jTextField2.setText("");
                username = "";
                password = "";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }

    }

    private UpdateProfile() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(243, 222, 222));
        jPanel1.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("You can change your username and password");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 30, 320, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel3.setText("username :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(240, 140, 90, 30);
        jPanel1.add(jTextField1);
        jTextField1.setBounds(340, 140, 210, 30);
        jPanel1.add(jTextField2);
        jTextField2.setBounds(340, 190, 210, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel4.setText("password :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(240, 190, 90, 30);

        jButton1.setBackground(new java.awt.Color(191, 246, 246));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 51, 153));
        jButton1.setText("Confirm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(330, 270, 130, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jButton2.setForeground(new java.awt.Color(51, 102, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hospital/management/system/images/arrow-back-icon (4).png"))); // NOI18N
        jButton2.setText("BACK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(630, 380, 110, 30);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 770, 430);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        username = jTextField1.getText();
        password = jTextField2.getText();
        if (username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "try again", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                addToDB();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (employee == "receptionist") {
            Receptionist adm = new Receptionist(id);
            adm.setVisible(true);
            this.dispose();
        } else if (employee == "doctor") {
            try {
                Doctor doc = new Doctor(id);
                doc.setVisible(true);
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(UpdateProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (employee == "pharmacist") {
            Pharmacist ph = new Pharmacist(id);
            ph.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateProfile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
