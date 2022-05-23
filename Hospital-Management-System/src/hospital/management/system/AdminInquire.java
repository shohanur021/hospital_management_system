package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdminInquire extends javax.swing.JFrame {

    public AdminInquire() throws SQLException {
        initComponents();
        setLocation(210, 50);
        showHeads();
    }

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

    public void numberOffmployee() throws SQLException {
        if (!jTextField1.getText().equals("")) {
            String sql = null;
            if (jTextField1.getText().equals("doctor")) {
                sql = "SELECT COUNT(DoctorID) as number FROM DOCTOR";
            } else if (jTextField1.getText().equals("receptionist")) {
                sql = "SELECT COUNT(ReceptionistID) as number FROM RECEPTIONIST";
            } else if (jTextField1.getText().equals("pharmacist")) {
                sql = "SELECT COUNT(PharmacistID) as number FROM PHARMACIST";
            } else if (jTextField1.getText().equals("all employee")) {
                sql = "SELECT JoiningDate FROM RECEPTIONIST UNION ALL SELECT JoiningDate FROM PHARMACIST UNION ALL SELECT JoiningDate FROM DOCTOR";
            }

            try {
                con = DbConnection();
                if (con != null) {
                    if (!jTextField1.getText().equals("all employee")) {
                        pst = con.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            jLabel1.setText(" is: " + String.valueOf(rs.getInt("number")));
                        }
                    } else {
                        int c = 0;
                        pst = con.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();
                        while (rs.next()) {
                            c++;
                        }
                        jLabel1.setText(" is: " + String.valueOf(c));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                pst.close();
                con.close();
            }
        }
    }

    public void salariesOfEmployee() throws SQLException {
        if (!jTextField2.getText().equals("")) {
            String sql = null;
            if (jTextField2.getText().equals("pharmacist")) {
                sql = "SELECT SUM(Salary) as salaries FROM PHARMACIST";
            } else if (jTextField2.getText().equals("receptionist")) {
                sql = "SELECT SUM(Salary) as salaries FROM RECEPTIONIST";
            }

            try {
                con = DbConnection();
                if (con != null) {
                    pst = con.prepareStatement(sql);
                    ResultSet rs = pst.executeQuery();
                    while (rs.next()) {
                        jLabel2.setText(" is: " + String.valueOf(rs.getInt("salaries")));
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                pst.close();
                con.close();
            }
        }
    }

    public void showHeads() throws SQLException {
        int c;
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("SELECT DoctorName,Qualification,Department,JoiningDate,Gender,MobileNumber FROM DOCTOR WHERE doctorId IN(SELECT MIN(DoctorID) FROM DOCTOR Group By Department)");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                c = rsmd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
                dft.setRowCount(0);
                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= c; i++) {
                        v2.add(rs.getString("Department"));
                        v2.add(rs.getString("DoctorName"));
                        v2.add(rs.getString("Qualification"));
                        v2.add(rs.getString("Gender"));
                       v2.add(rs.getString("MobileNumber"));
                       v2.add(rs.getString("JoiningDate"));
                    }
                    dft.addRow(v2);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        shiftPHA = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        shiftPHA1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton14 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(210, 140, 70, 20);

        shiftPHA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pharmacist", "doctor", "receptionist", "all employee" }));
        shiftPHA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftPHAActionPerformed(evt);
            }
        });
        shiftPHA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shiftPHAKeyTyped(evt);
            }
        });
        jPanel1.add(shiftPHA);
        shiftPHA.setBounds(120, 120, 100, 20);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(120, 140, 90, 25);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("* Total salary of");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 250, 100, 20);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("* Total Number of ");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(10, 140, 110, 20);

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel1.add(jTextField2);
        jTextField2.setBounds(110, 250, 90, 25);

        shiftPHA1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "pharmacist", "receptionist" }));
        shiftPHA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftPHA1ActionPerformed(evt);
            }
        });
        shiftPHA1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shiftPHA1KeyTyped(evt);
            }
        });
        jPanel1.add(shiftPHA1);
        shiftPHA1.setBounds(110, 230, 100, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(200, 250, 80, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Head of list of all department");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(450, 30, 300, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Department", "Name", "Qualification", "Gender", "JoiningDate", "MobileNo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        jPanel1.add(jScrollPane3);
        jScrollPane3.setBounds(290, 80, 590, 360);

        jButton14.setBackground(new java.awt.Color(121, 121, 249));
        jButton14.setFont(new java.awt.Font("Tw Cen MT", 1, 35)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hospital/management/system/images/arrow-back-icon (4).png"))); // NOI18N
        jButton14.setText("BACK");
        jButton14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton14);
        jButton14.setBounds(30, 470, 115, 41);

        jButton13.setBackground(new java.awt.Color(121, 121, 249));
        jButton13.setFont(new java.awt.Font("Tw Cen MT", 1, 35)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hospital/management/system/images/Button-Close-icon (3).png"))); // NOI18N
        jButton13.setText("EXIT");
        jButton13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton13);
        jButton13.setBounds(770, 470, 103, 41);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void shiftPHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftPHAActionPerformed
        jTextField1.setText(shiftPHA.getSelectedItem().toString());
        try {
            numberOffmployee();
        } catch (SQLException ex) {
            Logger.getLogger(AdminInquire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_shiftPHAActionPerformed

    private void shiftPHAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shiftPHAKeyTyped

    }//GEN-LAST:event_shiftPHAKeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void shiftPHA1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftPHA1ActionPerformed
        jTextField2.setText(shiftPHA1.getSelectedItem().toString());
        try {
            salariesOfEmployee();
        } catch (SQLException ex) {
            Logger.getLogger(AdminInquire.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_shiftPHA1ActionPerformed

    private void shiftPHA1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shiftPHA1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_shiftPHA1KeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Admin ad = new Admin();
        ad.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
         System.exit(0);
    }//GEN-LAST:event_jButton13ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AdminInquire().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminInquire.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JComboBox<String> shiftPHA;
    private javax.swing.JComboBox<String> shiftPHA1;
    // End of variables declaration//GEN-END:variables
}
