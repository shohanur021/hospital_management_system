package hospital.management.system;

import java.awt.Color;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddDoctor extends javax.swing.JFrame {

    public AddDoctor() throws SQLException {
        initComponents();
        setLocation(9,5); 
        showDoctors();
    }

    String name;
    String mobile;
    String qualification;
    String specialization;
    String department;
    String fee;
    String gender;
    String address;
    String date;
    String username;
    String password;
    int doctorID;
    boolean updateOrDeleteBtn = false;

    private Connection con;
    private PreparedStatement pst;

    public void clearAllFiels() {
        nameD.setText("");
        mobileD.setText("");
        qualificationD.setText("");
        departmentDOC.setText("");
        specializationD.setText("");
        feeD.setText("");
        genderDOC.setText("");
        addressD.setText("");
        ((JTextField) dateD.getDateEditor().getUiComponent()).setText("");
        usernameD.setText("");
        passwordD.setText("");

        name = "";
        mobile = "";
        qualification = "";
        specialization = "";
        department = "";
        fee = "";
        gender = "";
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

    public void updateDoctertoDB() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("UPDATE DOCTOR SET DoctorName=?, MobileNumber=?, Qualification=?, Specialization=?, Department=?, Fee=?, Gender=?, PermanentAddress=?, JoiningDate=?, UserName=?, UserPassword=? WHERE DoctorID=" + doctorID);
                pst.setString(1, name);
                pst.setString(2, mobile);
                pst.setString(3, qualification);
                pst.setString(4, specialization);
                pst.setString(5, department);
                pst.setInt(6, Integer.valueOf(fee));
                pst.setString(7, gender);
                pst.setString(8, address);
                pst.setString(9, date);
                pst.setString(10, username);
                pst.setString(11, password);
                pst.executeUpdate();
                clearAllFiels();
                showDoctors();
                addD.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Doctor successfully Updated");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }

    }

    public void deleteDoctor() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("DELETE FROM DOCTOR WHERE DoctorID=" + doctorID);
                pst.executeUpdate();
                clearAllFiels();
                showDoctors();
                addD.setEnabled(true);
                JOptionPane.showMessageDialog(null, "Doctor successfully deleted");
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }

    public void showDoctors() throws SQLException {
        int c;
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("SELECT * FROM DOCTOR");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                c = rsmd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
                dft.setRowCount(0);

                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= c; i++) {
                        v2.add(rs.getInt("DoctorID"));
                        v2.add(rs.getString("DoctorName"));
                        v2.add(rs.getString("MobileNumber"));
                        v2.add(rs.getString("Qualification"));
                        v2.add(rs.getString("Specialization"));
                        v2.add(rs.getString("Department"));
                        v2.add(rs.getInt("Fee"));
                        v2.add(rs.getString("Gender"));
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

    public void addDoctertoDB() throws SQLException {
        con = DbConnection();
        if (con != null) {
            try {
                pst = con.prepareStatement("INSERT INTO DOCTOR (DoctorName, MobileNumber, Qualification, Specialization, Department, Fee, Gender, PermanentAddress, JoiningDate, UserName, UserPassword) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
                pst.setString(1, name);
                pst.setString(2, mobile);
                pst.setString(3, qualification);
                pst.setString(4, specialization);
                pst.setString(5, department);
                pst.setInt(6, Integer.valueOf(fee));
                pst.setString(7, gender);
                pst.setString(8, address);
                pst.setString(9, date);
                pst.setString(10, username);
                pst.setString(11, password);
                pst.executeUpdate();
                showDoctors();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        doc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressD = new javax.swing.JTextArea();
        usernameD = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        nameD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        mobileD = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        qualificationD = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        genderD = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        specializationD = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        departmentD = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        feeD = new javax.swing.JTextField();
        passwordD = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        deleteD = new javax.swing.JButton();
        addD = new javax.swing.JButton();
        updateD = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        departmentDOC = new javax.swing.JTextField();
        genderDOC = new javax.swing.JTextField();
        dateD = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(192, 244, 244));
        jPanel1.setLayout(null);

        doc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        doc.setText("Doctor  Registration");
        jPanel1.add(doc);
        doc.setBounds(463, 0, 139, 28);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Date");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 410, 60, 27);

        addressD.setColumns(20);
        addressD.setRows(5);
        jScrollPane1.setViewportView(addressD);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(140, 320, 190, 70);

        usernameD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameDActionPerformed(evt);
            }
        });
        jPanel1.add(usernameD);
        usernameD.setBounds(140, 460, 190, 27);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel12.setText("Name");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(30, 40, 50, 27);

        nameD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameDActionPerformed(evt);
            }
        });
        jPanel1.add(nameD);
        nameD.setBounds(140, 40, 190, 27);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Mobile No");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(30, 80, 80, 27);

        mobileD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobileDActionPerformed(evt);
            }
        });
        jPanel1.add(mobileD);
        mobileD.setBounds(140, 80, 190, 27);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel13.setText("Qualification");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(30, 120, 80, 27);

        qualificationD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                qualificationDActionPerformed(evt);
            }
        });
        jPanel1.add(qualificationD);
        qualificationD.setBounds(140, 120, 190, 27);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel14.setText("Specialization");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(30, 160, 90, 27);

        genderD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Male", "Female", "Others" }));
        genderD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderDActionPerformed(evt);
            }
        });
        genderD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                genderDKeyTyped(evt);
            }
        });
        jPanel1.add(genderD);
        genderD.setBounds(240, 280, 90, 27);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel15.setText("Department");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(30, 200, 80, 27);

        specializationD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specializationDActionPerformed(evt);
            }
        });
        jPanel1.add(specializationD);
        specializationD.setBounds(140, 160, 190, 27);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel16.setText("Fee");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(30, 240, 60, 27);

        departmentD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cardiology", "Hematology", "Neurology", "Gynecology", "Oncology", "Dermatology" }));
        departmentD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        departmentD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                departmentDActionPerformed(evt);
            }
        });
        jPanel1.add(departmentD);
        departmentD.setBounds(240, 200, 90, 27);

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel17.setText("Password");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(30, 500, 70, 27);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(jLabel18);
        jLabel18.setBounds(30, 350, 50, 10);

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel19.setText("Address");
        jPanel1.add(jLabel19);
        jLabel19.setBounds(30, 330, 60, 27);

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel20.setText("Gender");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(30, 280, 50, 27);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel22.setText("Username");
        jPanel1.add(jLabel22);
        jLabel22.setBounds(30, 460, 70, 27);

        feeD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feeDActionPerformed(evt);
            }
        });
        feeD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                feeDKeyTyped(evt);
            }
        });
        jPanel1.add(feeD);
        feeD.setBounds(140, 240, 190, 27);

        passwordD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordDActionPerformed(evt);
            }
        });
        jPanel1.add(passwordD);
        passwordD.setBounds(140, 500, 190, 27);

        jPanel2.setBackground(new java.awt.Color(70, 68, 68));

        deleteD.setBackground(new java.awt.Color(200, 199, 199));
        deleteD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        deleteD.setForeground(new java.awt.Color(153, 0, 153));
        deleteD.setText("Delete");
        deleteD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteDActionPerformed(evt);
            }
        });

        addD.setBackground(new java.awt.Color(200, 199, 199));
        addD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        addD.setForeground(new java.awt.Color(153, 0, 153));
        addD.setText("Add");
        addD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDActionPerformed(evt);
            }
        });

        updateD.setBackground(new java.awt.Color(200, 199, 199));
        updateD.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        updateD.setForeground(new java.awt.Color(153, 0, 153));
        updateD.setText("Update");
        updateD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateDActionPerformed(evt);
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
                        .addComponent(addD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(updateD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(deleteD, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 9, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deleteD, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 550, 320, 100);

        jButton3.setBackground(new java.awt.Color(200, 199, 199));
        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(153, 0, 153));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/hospital/management/system/images/arrow-back-icon (4).png"))); // NOI18N
        jButton3.setText("BACK");
        jButton3.setAlignmentY(0.0F);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);
        jButton3.setBounds(1204, 610, 110, 37);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Mobile No", "Qualification", "Specialization", "Department", "Fee", "Gender", "Address", "Date", "Username", "Password"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(65);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(9).setPreferredWidth(60);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(340, 30, 990, 550);

        departmentDOC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                departmentDOCKeyTyped(evt);
            }
        });
        jPanel1.add(departmentDOC);
        departmentDOC.setBounds(140, 200, 100, 27);

        genderDOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderDOCActionPerformed(evt);
            }
        });
        genderDOC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                genderDOCKeyTyped(evt);
            }
        });
        jPanel1.add(genderDOC);
        genderDOC.setBounds(140, 280, 100, 27);
        jPanel1.add(dateD);
        dateD.setBounds(140, 410, 190, 27);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1334, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameDActionPerformed

    private void nameDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameDActionPerformed

    private void mobileDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobileDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobileDActionPerformed

    private void qualificationDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_qualificationDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_qualificationDActionPerformed

    private void specializationDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specializationDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_specializationDActionPerformed

    private void feeDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feeDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feeDActionPerformed

    private void genderDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderDActionPerformed
        genderDOC.setText(genderD.getSelectedItem().toString());
    }//GEN-LAST:event_genderDActionPerformed

    private void passwordDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordDActionPerformed

    private void deleteDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteDActionPerformed
        if (updateOrDeleteBtn == true) {
            try {
                deleteDoctor();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Firstly select a doctor from table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteDActionPerformed

    private void addDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDActionPerformed

        try {
            name = nameD.getText();
            mobile = mobileD.getText();
            qualification = qualificationD.getText();
            specialization = specializationD.getText();
            department = departmentDOC.getText();
            fee = feeD.getText();
            gender = genderDOC.getText();
            address = addressD.getText();
            date = ((JTextField) dateD.getDateEditor().getUiComponent()).getText();
            username = usernameD.getText();
            password = passwordD.getText();
        } catch (Exception e) {
            System.out.println(e);
        }

        if (name.equals("") || mobile.equals("") || qualification.equals("") || specialization.equals("") || department.equals("") || gender.equals("") || address.equals("") || date.equals("") || username.equals("") || password.equals("") || mobile.length() != 11 || fee.equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid information not be allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                addDoctertoDB();
            } catch (SQLException ex) {
                Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addDActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      Admin ad = new Admin();
      this.setVisible(false); 
      ad.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void updateDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateDActionPerformed
        if (updateOrDeleteBtn == true) {
            try {
                name = nameD.getText();
                mobile = mobileD.getText();
                qualification = qualificationD.getText();
                specialization = specializationD.getText();
                department = departmentDOC.getText();
                fee = feeD.getText();
                gender = genderDOC.getText();
                address = addressD.getText();
                date = ((JTextField) dateD.getDateEditor().getUiComponent()).getText();
                username = usernameD.getText();
                password = passwordD.getText();
            } catch (Exception e) {
                System.out.println(e);
            }

            if (name.equals("") || mobile.equals("") || qualification.equals("") || specialization.equals("") || department.equals("") || gender.equals("") || address.equals("") || date.equals("") || username.equals("") || password.equals("") || mobile.length() != 11 || fee.equals("")) {
                JOptionPane.showMessageDialog(this, "Invalid information not be updated", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    updateDoctertoDB();
                } catch (SQLException ex) {
                    Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Firstly select a doctor from table", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateDActionPerformed

    private void feeDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_feeDKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_feeDKeyTyped

    private void departmentDOCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_departmentDOCKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_departmentDOCKeyTyped

    private void departmentDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_departmentDActionPerformed
        departmentDOC.setText(departmentD.getSelectedItem().toString());
    }//GEN-LAST:event_departmentDActionPerformed

    private void genderDOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderDOCActionPerformed

    }//GEN-LAST:event_genderDOCActionPerformed

    private void genderDOCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genderDOCKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_genderDOCKeyTyped

    private void genderDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genderDKeyTyped

    }//GEN-LAST:event_genderDKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        doctorID = (int) (d1.getValueAt(selectIndex, 0));
        nameD.setText((d1.getValueAt(selectIndex, 1).toString()));
        mobileD.setText((d1.getValueAt(selectIndex, 2).toString()));
        qualificationD.setText((d1.getValueAt(selectIndex, 3).toString()));
        specializationD.setText((d1.getValueAt(selectIndex, 4).toString()));
        departmentDOC.setText((d1.getValueAt(selectIndex, 5).toString()));
        feeD.setText((d1.getValueAt(selectIndex, 6).toString()));
        genderDOC.setText((d1.getValueAt(selectIndex, 7).toString()));
        addressD.setText((d1.getValueAt(selectIndex, 8).toString()));
        try {
            java.util.Date forDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)d1.getValueAt(selectIndex, 9));
            dateD.setDate(forDate);
        } catch (ParseException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        usernameD.setText((d1.getValueAt(selectIndex, 10).toString()));
        passwordD.setText((d1.getValueAt(selectIndex, 11).toString()));
        addD.setEnabled(false);
        updateOrDeleteBtn = true;
    }//GEN-LAST:event_jTable1MouseClicked

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new AddDoctor().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addD;
    private javax.swing.JTextArea addressD;
    private com.toedter.calendar.JDateChooser dateD;
    private javax.swing.JButton deleteD;
    private javax.swing.JComboBox<String> departmentD;
    private javax.swing.JTextField departmentDOC;
    private javax.swing.JLabel doc;
    private javax.swing.JTextField feeD;
    private javax.swing.JComboBox<String> genderD;
    private javax.swing.JTextField genderDOC;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mobileD;
    private javax.swing.JTextField nameD;
    private javax.swing.JPasswordField passwordD;
    private javax.swing.JTextField qualificationD;
    private javax.swing.JTextField specializationD;
    private javax.swing.JButton updateD;
    private javax.swing.JTextField usernameD;
    // End of variables declaration//GEN-END:variables

}
