
package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class AddPharmacist extends javax.swing.JFrame {

    public AddPharmacist() throws SQLException {
        initComponents();
        setLocation(13, 5);
        showPharmacists();
    }
    
    String name;
    String mobile;
    String salary;
    String shift;
    String address;
    String date;
    String username;
    String password;
    int pharmacistID;
    boolean updateOrDeleteBtn = false;

    private Connection con;
    private PreparedStatement pst;
    
    public void clearAllFiels() {
        nameP.setText("");
        mobileP.setText("");
        shiftP.setText("");
        salaryP.setText("");
        addressP.setText("");
        ((JTextField) dateP.getDateEditor().getUiComponent()).setText("");
        usernameP.setText("");
        passwordP.setText("");

        name = "";
        mobile = "";
        salary = "";
        shift = "";
        address = "";
        date = "";
        username = "";
        password = "";
        updateOrDeleteBtn = false;
    }
    
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
    
    public void deletePharmacist() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("DELETE FROM PHARMACIST WHERE PharmacistID=" + pharmacistID);
                pst.executeUpdate();
                clearAllFiels();
                showPharmacists();
                addP.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Pharmacist successfully deleted");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }
    
    public void updatePharmacisttoDB() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("UPDATE PHARMACIST SET PharmacistName=?, MobileNumber=?, ShiftingTime=?, Salary=?, PermanentAddress=?, JoiningDate=?, UserName=?, UserPassword=? WHERE PharmacistID=" + pharmacistID);
                pst.setString(1, name);
                pst.setString(2, mobile);
                pst.setString(3, shift);
                pst.setString(4, salary);
                pst.setString(5, address);
                pst.setString(6, date);
                pst.setString(7, username);
                pst.setString(8, password);
                pst.executeUpdate();
                clearAllFiels();
                showPharmacists();
                addP.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Pharmacist successfully Updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }
    
    public void addPharmacisttoDB() throws SQLException {
        con = DbConnection();
        if (con != null) {
            try {
                pst = con.prepareStatement("INSERT INTO PHARMACIST (PharmacistName, MobileNumber, ShiftingTime, Salary, PermanentAddress, JoiningDate, UserName, UserPassword) VALUES (?,?,?,?,?,?,?,?)");
                pst.setString(1, name);
                pst.setString(2, mobile);
                pst.setString(3, shift);
                pst.setInt(4, Integer.valueOf(salary));
                pst.setString(5, address);
                pst.setString(6, date);
                pst.setString(7, username);
                pst.setString(8, password);
                pst.executeUpdate();
                showPharmacists();
                clearAllFiels();
                JOptionPane.showMessageDialog(this, "Successfully added", "Info", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pst.close();
                con.close();
            }
        }
    }
    
    public void showPharmacists() throws SQLException {
        int c;
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("SELECT * FROM PHARMACIST");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                c = rsmd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
                dft.setRowCount(0);
                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= c; i++) {
                        v2.add(rs.getInt("PharmacistID"));
                        v2.add(rs.getString("PharmacistName"));
                        v2.add(rs.getString("MobileNumber"));
                        v2.add(rs.getString("ShiftingTime"));
                        v2.add(rs.getInt("Salary"));
                        v2.add(rs.getString("PermanentAddress"));
                        v2.add(rs.getString("JoiningDate"));
                        v2.add(rs.getString("UserName"));
                        v2.add(rs.getString("UserPassword"));
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
        jLabel12 = new javax.swing.JLabel();
        nameP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mobileP = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        salaryP = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressP = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        dateP = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        usernameP = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        passwordP = new javax.swing.JPasswordField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        shiftP = new javax.swing.JTextField();
        shiftPHA = new javax.swing.JComboBox<>();
        doc = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        deleteP = new javax.swing.JButton();
        addP = new javax.swing.JButton();
        updateP = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(223, 247, 189));
        jPanel1.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("Name");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 100, 50, 27);

        nameP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namePActionPerformed(evt);
            }
        });
        jPanel1.add(nameP);
        nameP.setBounds(140, 100, 190, 27);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Mobile No");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 150, 80, 27);

        mobileP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobilePActionPerformed(evt);
            }
        });
        jPanel1.add(mobileP);
        mobileP.setBounds(140, 150, 190, 27);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setText("Salary");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 200, 60, 27);

        salaryP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryPActionPerformed(evt);
            }
        });
        salaryP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                salaryPKeyTyped(evt);
            }
        });
        jPanel1.add(salaryP);
        salaryP.setBounds(140, 200, 190, 27);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setText("Address");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(30, 320, 60, 27);

        addressP.setColumns(20);
        addressP.setRows(5);
        jScrollPane1.setViewportView(addressP);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(140, 300, 190, 70);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Date");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 390, 60, 27);
        jPanel1.add(dateP);
        dateP.setBounds(140, 390, 190, 27);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel22.setText("Username");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(30, 440, 70, 27);

        usernameP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernamePActionPerformed(evt);
            }
        });
        jPanel1.add(usernameP);
        usernameP.setBounds(140, 440, 190, 27);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Password");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(30, 490, 70, 27);

        passwordP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordPActionPerformed(evt);
            }
        });
        jPanel1.add(passwordP);
        passwordP.setBounds(140, 490, 190, 27);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Mobile No", "Shift", "Salary", "Address", "Date", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane3.setBounds(350, 100, 950, 420);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setText("Shift");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(30, 250, 50, 27);

        shiftP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftPActionPerformed(evt);
            }
        });
        shiftP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shiftPKeyTyped(evt);
            }
        });
        jPanel1.add(shiftP);
        shiftP.setBounds(140, 250, 100, 27);

        shiftPHA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "day", "evening", "night" }));
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
        shiftPHA.setBounds(240, 250, 90, 27);

        doc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        doc.setText("Receptionist Registration ");
        jPanel1.add(doc);
        doc.setBounds(480, 30, 320, 40);

        jPanel2.setBackground(new java.awt.Color(70, 68, 68));

        deleteP.setBackground(new java.awt.Color(200, 199, 199));
        deleteP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteP.setForeground(new java.awt.Color(153, 0, 153));
        deleteP.setText("Delete");
        deleteP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePActionPerformed(evt);
            }
        });

        addP.setBackground(new java.awt.Color(200, 199, 199));
        addP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addP.setForeground(new java.awt.Color(153, 0, 153));
        addP.setText("Add");
        addP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPActionPerformed(evt);
            }
        });

        updateP.setBackground(new java.awt.Color(200, 199, 199));
        updateP.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateP.setForeground(new java.awt.Color(153, 0, 153));
        updateP.setText("Update");
        updateP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatePActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addP, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateP, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(deleteP, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteP, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 530, 310, 100);

        backBtn.setBackground(new java.awt.Color(200, 199, 199));
        backBtn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        backBtn.setForeground(new java.awt.Color(153, 0, 153));
        backBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hospital/management/system/images/arrow-back-icon (4).png"))); // NOI18N
        backBtn.setText("BACK");
        backBtn.setAlignmentY(0.0F);
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });
        jPanel1.add(backBtn);
        backBtn.setBounds(1170, 580, 120, 37);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void namePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namePActionPerformed

    private void mobilePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobilePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobilePActionPerformed

    private void salaryPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salaryPActionPerformed

    private void salaryPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salaryPKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_salaryPKeyTyped

    private void usernamePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernamePActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernamePActionPerformed

    private void passwordPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordPActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        pharmacistID = (int) (d1.getValueAt(selectIndex, 0));
        nameP.setText((d1.getValueAt(selectIndex, 1).toString()));
        mobileP.setText((d1.getValueAt(selectIndex, 2).toString()));
        shiftP.setText((d1.getValueAt(selectIndex, 3).toString()));
        salaryP.setText((d1.getValueAt(selectIndex, 4).toString()));
        addressP.setText((d1.getValueAt(selectIndex, 5).toString()));
        try {
            java.util.Date forDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) d1.getValueAt(selectIndex, 6));
            dateP.setDate(forDate);
        } catch (ParseException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        usernameP.setText((d1.getValueAt(selectIndex, 7).toString()));
        passwordP.setText((d1.getValueAt(selectIndex, 8).toString()));
        addP.setEnabled(false);
        updateOrDeleteBtn = true;     
    }//GEN-LAST:event_jTable1MouseClicked

    private void shiftPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftPActionPerformed

    }//GEN-LAST:event_shiftPActionPerformed

    private void shiftPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shiftPKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_shiftPKeyTyped

    private void shiftPHAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftPHAActionPerformed
        shiftP.setText(shiftPHA.getSelectedItem().toString());
    }//GEN-LAST:event_shiftPHAActionPerformed

    private void shiftPHAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shiftPHAKeyTyped

    }//GEN-LAST:event_shiftPHAKeyTyped

    private void deletePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePActionPerformed
     if (updateOrDeleteBtn == true) {
            try {
                deletePharmacist();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Firstly select a pharmacist from table", "Error", JOptionPane.ERROR_MESSAGE);
        }    
    }//GEN-LAST:event_deletePActionPerformed

    private void addPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPActionPerformed
      try {
            name = nameP.getText();
            mobile = mobileP.getText();
            shift = shiftP.getText();
            salary = salaryP.getText();
            address = addressP.getText();
            date = ((JTextField) dateP.getDateEditor().getUiComponent()).getText();
            username = usernameP.getText();
            password = passwordP.getText();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (name.equals("") || mobile.equals("") || mobile.length() != 11 || shift.equals("") || salary.equals("") || address.equals("") || date.equals("") || username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid information not be allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                addPharmacisttoDB();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }       
    }//GEN-LAST:event_addPActionPerformed

    private void updatePActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatePActionPerformed
    if (updateOrDeleteBtn == true) {
            try {
                name = nameP.getText();
                mobile = mobileP.getText();
                shift = shiftP.getText();
                salary = salaryP.getText();
                address = addressP.getText();
                date = ((JTextField) dateP.getDateEditor().getUiComponent()).getText();
                username = usernameP.getText();
                password = passwordP.getText();
            } catch (Exception e) {
                System.out.println(e);
            }

            if (name.equals("") || mobile.equals("") || mobile.length() != 11 || shift.equals("") || salary.equals("") || address.equals("") || date.equals("") || username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid information not be updated", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    updatePharmacisttoDB();
                } catch (SQLException ex) {
                    Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Firstly select a pharmacist from table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updatePActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
       Admin ad = new Admin();
      this.setVisible(false); 
      ad.setVisible(true);
    }//GEN-LAST:event_backBtnActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddPharmacist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddPharmacist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddPharmacist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddPharmacist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AddPharmacist().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddPharmacist.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addP;
    private javax.swing.JTextArea addressP;
    private javax.swing.JButton backBtn;
    private com.toedter.calendar.JDateChooser dateP;
    private javax.swing.JButton deleteP;
    private javax.swing.JLabel doc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mobileP;
    private javax.swing.JTextField nameP;
    private javax.swing.JPasswordField passwordP;
    private javax.swing.JTextField salaryP;
    private javax.swing.JTextField shiftP;
    private javax.swing.JComboBox<String> shiftPHA;
    private javax.swing.JButton updateP;
    private javax.swing.JTextField usernameP;
    // End of variables declaration//GEN-END:variables
}
