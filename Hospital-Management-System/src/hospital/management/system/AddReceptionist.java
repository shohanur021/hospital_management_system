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

public class AddReceptionist extends javax.swing.JFrame {

    public AddReceptionist() throws SQLException {
        initComponents();
        setLocation(13, 5);
        showReceptionists();
    }

    String name;
    String mobile;
    String salary;
    String shift;
    String address;
    String date;
    String username;
    String password;
    int receptionistID;
    boolean updateOrDeleteBtn = false;

    private Connection con;
    private PreparedStatement pst;

    public void clearAllFiels() {
        nameR.setText("");
        mobileR.setText("");
        shiftR.setText("");
        salaryR.setText("");
        addressR.setText("");
        ((JTextField) dateR.getDateEditor().getUiComponent()).setText("");
        usernameR.setText("");
        passwordR.setText("");

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

    public void deleteReceptionist() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("DELETE FROM RECEPTIONIST WHERE ReceptionistID=" + receptionistID);
                pst.executeUpdate();
                clearAllFiels();
                showReceptionists();
                addR.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Receptionist successfully deleted");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }

    public void updateReceptionisttoDB() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("UPDATE RECEPTIONIST SET ReceptionistName=?, MobileNumber=?, ShiftingTime=?, Salary=?, PermanentAddress=?, JoiningDate=?, UserName=?, UserPassword=? WHERE ReceptionistID=" + receptionistID);
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
                showReceptionists();
                addR.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Receptionist successfully Updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }

    public void addReceptionisttoDB() throws SQLException {
        con = DbConnection();
        if (con != null) {
            try {
                pst = con.prepareStatement("INSERT INTO RECEPTIONIST (ReceptionistName, MobileNumber, ShiftingTime, Salary, PermanentAddress, JoiningDate, UserName, UserPassword) VALUES (?,?,?,?,?,?,?,?)");
                pst.setString(1, name);
                pst.setString(2, mobile);
                pst.setString(3, shift);
                pst.setInt(4, Integer.valueOf(salary));
                pst.setString(5, address);
                pst.setString(6, date);
                pst.setString(7, username);
                pst.setString(8, password);
                pst.executeUpdate();
                showReceptionists();
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

    public void showReceptionists() throws SQLException {
        int c;
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("SELECT * FROM RECEPTIONIST");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                c = rsmd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
                dft.setRowCount(0);

                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= c; i++) {
                        v2.add(rs.getInt("ReceptionistID"));
                        v2.add(rs.getString("ReceptionistName"));
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
        nameR = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mobileR = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        salaryR = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressR = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        dateR = new com.toedter.calendar.JDateChooser();
        jLabel22 = new javax.swing.JLabel();
        usernameR = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        passwordR = new javax.swing.JPasswordField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        shiftR = new javax.swing.JTextField();
        shiftREG = new javax.swing.JComboBox<>();
        doc = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        deleteR = new javax.swing.JButton();
        addR = new javax.swing.JButton();
        updateR = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(209, 209, 244));
        jPanel1.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("Name");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 100, 50, 27);

        nameR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameRActionPerformed(evt);
            }
        });
        jPanel1.add(nameR);
        nameR.setBounds(140, 100, 190, 27);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Mobile No");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 150, 80, 27);

        mobileR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileRActionPerformed(evt);
            }
        });
        jPanel1.add(mobileR);
        mobileR.setBounds(140, 150, 190, 27);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setText("Salary");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 200, 60, 27);

        salaryR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryRActionPerformed(evt);
            }
        });
        salaryR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                salaryRKeyTyped(evt);
            }
        });
        jPanel1.add(salaryR);
        salaryR.setBounds(140, 200, 190, 27);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setText("Address");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(30, 320, 60, 27);

        addressR.setColumns(20);
        addressR.setRows(5);
        jScrollPane1.setViewportView(addressR);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(140, 300, 190, 70);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Date");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 390, 60, 27);
        jPanel1.add(dateR);
        dateR.setBounds(140, 390, 190, 27);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel22.setText("Username");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(30, 440, 70, 27);

        usernameR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameRActionPerformed(evt);
            }
        });
        jPanel1.add(usernameR);
        usernameR.setBounds(140, 440, 190, 27);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Password");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(30, 490, 70, 27);

        passwordR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordRActionPerformed(evt);
            }
        });
        jPanel1.add(passwordR);
        passwordR.setBounds(140, 490, 190, 27);

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

        shiftR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftRActionPerformed(evt);
            }
        });
        shiftR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shiftRKeyTyped(evt);
            }
        });
        jPanel1.add(shiftR);
        shiftR.setBounds(140, 250, 100, 27);

        shiftREG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "day", "evening", "night" }));
        shiftREG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiftREGActionPerformed(evt);
            }
        });
        shiftREG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                shiftREGKeyTyped(evt);
            }
        });
        jPanel1.add(shiftREG);
        shiftREG.setBounds(240, 250, 90, 27);

        doc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        doc.setText("Receptionist Registration ");
        jPanel1.add(doc);
        doc.setBounds(480, 30, 320, 40);

        jPanel2.setBackground(new java.awt.Color(70, 68, 68));

        deleteR.setBackground(new java.awt.Color(200, 199, 199));
        deleteR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteR.setForeground(new java.awt.Color(153, 0, 153));
        deleteR.setText("Delete");
        deleteR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRActionPerformed(evt);
            }
        });

        addR.setBackground(new java.awt.Color(200, 199, 199));
        addR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addR.setForeground(new java.awt.Color(153, 0, 153));
        addR.setText("Add");
        addR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRActionPerformed(evt);
            }
        });

        updateR.setBackground(new java.awt.Color(200, 199, 199));
        updateR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateR.setForeground(new java.awt.Color(153, 0, 153));
        updateR.setText("Update");
        updateR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRActionPerformed(evt);
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
                        .addComponent(addR, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateR, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(deleteR, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        backBtn.setBounds(1184, 580, 110, 37);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1322, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nameRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameRActionPerformed

    private void mobileRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileRActionPerformed

    private void salaryRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salaryRActionPerformed

    private void salaryRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salaryRKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_salaryRKeyTyped

    private void usernameRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameRActionPerformed

    private void passwordRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordRActionPerformed

    private void shiftRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftRActionPerformed

    }//GEN-LAST:event_shiftRActionPerformed

    private void shiftRKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shiftRKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_shiftRKeyTyped

    private void shiftREGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiftREGActionPerformed
        shiftR.setText(shiftREG.getSelectedItem().toString());
    }//GEN-LAST:event_shiftREGActionPerformed

    private void shiftREGKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_shiftREGKeyTyped

    }//GEN-LAST:event_shiftREGKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        receptionistID = (int) (d1.getValueAt(selectIndex, 0));
        nameR.setText((d1.getValueAt(selectIndex, 1).toString()));
        mobileR.setText((d1.getValueAt(selectIndex, 2).toString()));
        shiftR.setText((d1.getValueAt(selectIndex, 3).toString()));
        salaryR.setText((d1.getValueAt(selectIndex, 4).toString()));
        addressR.setText((d1.getValueAt(selectIndex, 5).toString()));
        try {
            java.util.Date forDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) d1.getValueAt(selectIndex, 6));
            dateR.setDate(forDate);
        } catch (ParseException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        usernameR.setText((d1.getValueAt(selectIndex, 7).toString()));
        passwordR.setText((d1.getValueAt(selectIndex, 8).toString()));
        addR.setEnabled(false);
        updateOrDeleteBtn = true;
    }//GEN-LAST:event_jTable1MouseClicked

    private void deleteRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRActionPerformed
        if (updateOrDeleteBtn == true) {
            try {
                deleteReceptionist();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Firstly select a receptionist from table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteRActionPerformed

    private void addRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRActionPerformed
        try {
            name = nameR.getText();
            mobile = mobileR.getText();
            shift = shiftR.getText();
            salary = salaryR.getText();
            address = addressR.getText();
            date = ((JTextField) dateR.getDateEditor().getUiComponent()).getText();
            username = usernameR.getText();
            password = passwordR.getText();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (name.equals("") || mobile.equals("") || mobile.length() != 11 || shift.equals("") || salary.equals("") || address.equals("") || date.equals("") || username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid information not be allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

//            System.out.println(name);
//            System.out.println( mobile);
//            System.out.println(shift);
//            System.out.println(salary);
//            System.out.println(address);
//            System.out.println(date);
//            System.out.println(username);
//            System.out.println(password);
            try {
                addReceptionisttoDB();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_addRActionPerformed

    private void updateRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRActionPerformed
        if (updateOrDeleteBtn == true) {
            try {
                name = nameR.getText();
                mobile = mobileR.getText();
                shift = shiftR.getText();
                salary = salaryR.getText();
                address = addressR.getText();
                date = ((JTextField) dateR.getDateEditor().getUiComponent()).getText();
                username = usernameR.getText();
                password = passwordR.getText();
            } catch (Exception e) {
                System.out.println(e);
            }

            if (name.equals("") || mobile.equals("") || mobile.length() != 11 || shift.equals("") || salary.equals("") || address.equals("") || date.equals("") || username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid information not be updated", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    updateReceptionisttoDB();
                } catch (SQLException ex) {
                    Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Firstly select a receptionist from table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateRActionPerformed

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
            java.util.logging.Logger.getLogger(AddReceptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddReceptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddReceptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddReceptionist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AddReceptionist().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddReceptionist.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addR;
    private javax.swing.JTextArea addressR;
    private javax.swing.JButton backBtn;
    private com.toedter.calendar.JDateChooser dateR;
    private javax.swing.JButton deleteR;
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
    private javax.swing.JTextField mobileR;
    private javax.swing.JTextField nameR;
    private javax.swing.JPasswordField passwordR;
    private javax.swing.JTextField salaryR;
    private javax.swing.JTextField shiftR;
    private javax.swing.JComboBox<String> shiftREG;
    private javax.swing.JButton updateR;
    private javax.swing.JTextField usernameR;
    // End of variables declaration//GEN-END:variables
}
