package hospital.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

public class Receptionist extends javax.swing.JFrame {

    private int id;

    public Receptionist(int id) {
        initComponents();
        setLocation(130, 28);
        this.id = id;
        jPanel2.setVisible(false);
    }

    private Receptionist() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    String firstName;
    String lastName;
    String mobile;
    String gender;
    String dob;
    String age;
    String diseage;
    String address;

    String visitingDate;
    String doctorId;

    String specialist;
    String genderDoc;
    String feeDoc;

    private Connection con;
    private Statement stmt;
    private PreparedStatement pst;
    private PreparedStatement pst_2;

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

    public void addPatient() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("INSERT INTO PATIENT (FirstName, LastName, MobileNumber, Gender, DateOfBirth, Age, DiseageName, PermanentAddress ) VALUES (?,?,?,?,?,?,?,?)");
                pst.setString(1, firstName);
                pst.setString(2, lastName);
                pst.setString(3, mobile);
                pst.setString(4, gender);
                pst.setString(5, dob);
                pst.setInt(6, Integer.valueOf(age));
                pst.setString(7, diseage);
                pst.setString(8, address);
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            con.close();
        }
    }

    public void addAppoinment() throws SQLException {
        try {
            con = DbConnection();
            int patientId = 0;
            if (con != null) {
                pst = con.prepareStatement("SELECT MAX(PatientID)as patientId FROM PATIENT");
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    patientId = Integer.parseInt(rs.getString("patientId"));
                }

                pst_2 = con.prepareStatement("INSERT INTO APPOINTMENT (VisitingDate, PatientID,  DoctorID, ViewStatus) VALUES (?,?,?,?)");
                pst_2.setString(1, visitingDate);
                pst_2.setInt(2, patientId);
                pst_2.setInt(3, Integer.parseInt(doctorId));
                pst_2.setString(4, "pending");
                pst_2.executeUpdate();

                clearAllField();
                jPanel2.setVisible(false);
                jPanel3.setVisible(true);
                JOptionPane.showMessageDialog(this, "Successfully added", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database problem", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pst.close();
            pst_2.close();
            con.close();
        }
    }

    public void searchDoctor() throws SQLException {
        try {
            con = DbConnection();
            String query = null;
            if (!specialist.equals("") && genderDoc.equals("") && feeDoc.equals("")) {
                query = "SELECT DoctorID, DoctorName, MobileNumber, Qualification, Specialization, Gender, Fee FROM DOCTOR WHERE Specialization='" + specialist + "'";
            } else if (!specialist.equals("") && !genderDoc.equals("") && feeDoc.equals("")) {
                query = "SELECT DoctorID, DoctorName, MobileNumber, Qualification, Specialization, Gender, Fee FROM DOCTOR WHERE Specialization='" + specialist + "' AND Gender='" + genderDoc + "'";
            } else if (!specialist.equals("") && genderDoc.equals("") && !feeDoc.equals("")) {
                query = "SELECT DoctorID, DoctorName, MobileNumber, Qualification, Specialization, Gender, Fee FROM DOCTOR WHERE Specialization='" + specialist + "' AND  Fee<=" + feeDoc + "ORDER BY Fee desc";
            } else if (!specialist.equals("") && !genderDoc.equals("") && !feeDoc.equals("")) {
                query = "SELECT DoctorID, DoctorName, MobileNumber, Qualification, Specialization, Gender, Fee FROM DOCTOR WHERE Specialization='" + specialist + "' AND Gender='" + genderDoc + "' AND  Fee<=" + feeDoc+ "ORDER BY Fee desc";
            }

            if (con != null) {
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData rsmd = rs.getMetaData();
                int c = rsmd.getColumnCount();
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
                        v2.add(rs.getString("Gender"));
                        v2.add(rs.getInt("Fee"));
                    }
                    dft.addRow(v2);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database problem", "Error", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(AddDoctor.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            stmt.close();
            con.close();
        }
    }

    public void clearAllField() {
        firstP.setText("");
        lastP.setText("");
        mobileP.setText("");
        genderP.setText("");
        ((JTextField) dobP.getDateEditor().getUiComponent()).setText("");
        ageP.setText("");
        diseaseP.setText("");
        addressP.setText("");

        firstName = "";
        lastName = "";
        mobile = "";
        gender = "";
        dob = "";
        age = "";
        diseage = "";
        address = "";

        ((JTextField) dateA.getDateEditor().getUiComponent()).setText("");
        doctorIdA.setText("");

        visitingDate = "";
        doctorId = "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        genderP = new javax.swing.JTextField();
        genderSelect = new javax.swing.JComboBox<>();
        dobP = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        addressP = new javax.swing.JTextArea();
        diseaseP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        firstP = new javax.swing.JTextField();
        lastP = new javax.swing.JTextField();
        mobileP = new javax.swing.JTextField();
        ageP = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        timeA = new javax.swing.JSpinner(sm);
        dateA = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        doctorIdA = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        specializationD = new javax.swing.JComboBox<>();
        genderD = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        speD = new javax.swing.JTextField();
        feD = new javax.swing.JTextField();
        genD = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        search = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(219, 236, 253));

        jPanel3.setBackground(new java.awt.Color(189, 218, 247));
        jPanel3.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 51));
        jLabel2.setText("Patient Record ");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(90, 10, 140, 22);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("First Name:");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(30, 50, 70, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Gender:");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(30, 200, 49, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("D.O.B:");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(30, 250, 41, 17);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Age:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(30, 290, 28, 20);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Diseage:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(30, 340, 52, 20);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Address:");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(30, 410, 53, 17);

        genderP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                genderPKeyTyped(evt);
            }
        });
        jPanel3.add(genderP);
        genderP.setBounds(140, 190, 84, 27);

        genderSelect.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        genderSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female", "others" }));
        genderSelect.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        genderSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderSelectActionPerformed(evt);
            }
        });
        jPanel3.add(genderSelect);
        genderSelect.setBounds(230, 190, 83, 27);
        jPanel3.add(dobP);
        dobP.setBounds(140, 240, 173, 27);

        addressP.setColumns(20);
        addressP.setRows(5);
        jScrollPane1.setViewportView(addressP);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(140, 390, 173, 80);
        jPanel3.add(diseaseP);
        diseaseP.setBounds(140, 340, 173, 27);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mobile No:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(30, 150, 63, 17);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Last Name:");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(30, 100, 70, 17);
        jPanel3.add(firstP);
        firstP.setBounds(138, 49, 173, 27);
        jPanel3.add(lastP);
        lastP.setBounds(140, 90, 173, 27);
        jPanel3.add(mobileP);
        mobileP.setBounds(140, 140, 173, 27);

        ageP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agePMouseClicked(evt);
            }
        });
        ageP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                agePKeyTyped(evt);
            }
        });
        jPanel3.add(ageP);
        ageP.setBounds(140, 290, 173, 27);

        jButton2.setBackground(new java.awt.Color(251, 219, 156));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(100, 510, 120, 30);

        jPanel2.setBackground(new java.awt.Color(185, 185, 246));
        jPanel2.setLayout(null);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 51));
        jLabel12.setText("Appointment");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(100, 10, 130, 22);

        JSpinner.DateEditor de = new JSpinner.DateEditor(timeA, "HH:mm:ss");
        timeA.setEditor(de);
        timeA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timeA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                timeAKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                timeAKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                timeAKeyTyped(evt);
            }
        });
        jPanel2.add(timeA);
        timeA.setBounds(141, 132, 156, 28);
        jPanel2.add(dateA);
        dateA.setBounds(140, 70, 160, 27);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Time :");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(40, 130, 59, 28);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Date :");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(40, 70, 53, 27);

        doctorIdA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        doctorIdA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doctorIdAActionPerformed(evt);
            }
        });
        doctorIdA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                doctorIdAKeyTyped(evt);
            }
        });
        jPanel2.add(doctorIdA);
        doctorIdA.setBounds(140, 200, 150, 28);

        jButton3.setBackground(new java.awt.Color(251, 219, 156));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Confirm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(114, 372, 112, 32);

        jButton4.setBackground(new java.awt.Color(251, 219, 156));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Cancel");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4);
        jButton4.setBounds(114, 454, 112, 32);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("DoctorId:");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(40, 200, 60, 28);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Mobile No", "Qualification", "Specialization", "gender", "Fee"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(23);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(43);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 0, 51));
        jLabel14.setText("Doctor Selection");

        jLabel16.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel16.setText("Specialization");

        jLabel17.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel17.setText("Gender");

        jLabel18.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        jLabel18.setText("Fee ( <= )");

        specializationD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        specializationD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cardiologist", "Hematologist", "Neurologist", "Gynecologist", "Oncologist", "Dermatologist" }));
        specializationD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        specializationD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                specializationDActionPerformed(evt);
            }
        });

        genderD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        genderD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female", "others" }));
        genderD.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        genderD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genderDActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 33)); // NOI18N
        jLabel1.setText("Welcome as a Receptionist");

        feD.setVerifyInputWhenFocusTarget(false);
        feD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feDActionPerformed(evt);
            }
        });
        feD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                feDKeyTyped(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Update Profile");
        jButton1.setToolTipText("");
        jButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        search.setBackground(new java.awt.Color(204, 255, 102));
        search.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        search.setText("Search");
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(speD, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(specializationD, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(93, 93, 93)
                                                .addComponent(genD, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(genderD, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(16, 16, 16)))
                                        .addGap(119, 119, 119)
                                        .addComponent(feD, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 328, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(271, 271, 271))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(genderD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(speD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(specializationD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(genD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(feD, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(search)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        UpdateProfile rp = new UpdateProfile("receptionist", id);
        rp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void genderSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderSelectActionPerformed
        genderP.setText(genderSelect.getSelectedItem().toString());
    }//GEN-LAST:event_genderSelectActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        firstName = firstP.getText();
        lastName = lastP.getText();
        mobile = mobileP.getText();
        gender = genderP.getText();
        dob = ((JTextField) dobP.getDateEditor().getUiComponent()).getText();
        age = ageP.getText();
        diseage = diseaseP.getText();
        address = addressP.getText();
        if (firstName.equals("") || lastName.equals("") || mobile.equals("") || gender.equals("") || dob.equals("") || age.equals("") || diseage.equals("") || address.equals("") || mobile.length() != 11) {
            JOptionPane.showMessageDialog(this, "Invalid information not be allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            jPanel3.setVisible(false);
            jPanel2.setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (((JTextField) dateA.getDateEditor().getUiComponent()).getText().equals("") || doctorIdA.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Invalid information not be allowed", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String[] s = timeA.getValue().toString().split(" ");
            visitingDate = ((JTextField) dateA.getDateEditor().getUiComponent()).getText() + " " + s[3];
            doctorId = doctorIdA.getText();
            try {
                addPatient();
                addAppoinment();
            } catch (SQLException ex) {
                Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        jPanel2.setVisible(false);
        jPanel3.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void timeAKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeAKeyReleased

    }//GEN-LAST:event_timeAKeyReleased

    private void doctorIdAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doctorIdAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_doctorIdAActionPerformed

    private void timeAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeAKeyTyped

    }//GEN-LAST:event_timeAKeyTyped

    private void timeAKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_timeAKeyPressed

    }//GEN-LAST:event_timeAKeyPressed

    private void genderPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_genderPKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_genderPKeyTyped

    private void agePKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_agePKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_agePKeyTyped

    private void doctorIdAKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doctorIdAKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_doctorIdAKeyTyped

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void specializationDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_specializationDActionPerformed
        speD.setText(specializationD.getSelectedItem().toString());
    }//GEN-LAST:event_specializationDActionPerformed

    private void genderDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genderDActionPerformed
        genD.setText(genderD.getSelectedItem().toString());
    }//GEN-LAST:event_genderDActionPerformed

    private void feDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_feDActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        specialist = speD.getText();
        genderDoc = genD.getText();
        feeDoc = feD.getText();

        if (specialist.equals("")) {
            JOptionPane.showMessageDialog(this, "Plz Select a Specialist First", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                searchDoctor();
            } catch (SQLException ex) {
                Logger.getLogger(Receptionist.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_searchActionPerformed

    private void feDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_feDKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_feDKeyTyped

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void agePMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agePMouseClicked
        String[] s = ((JTextField) dobP.getDateEditor().getUiComponent()).getText().split(" ");
        LocalDate d = LocalDate.now();
        int diff = d.getYear() - Integer.parseInt(s[2]);
        ageP.setText(String.valueOf(diff));
    }//GEN-LAST:event_agePMouseClicked

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Receptionist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea addressP;
    private javax.swing.JTextField ageP;
    private com.toedter.calendar.JDateChooser dateA;
    private javax.swing.JTextField diseaseP;
    private com.toedter.calendar.JDateChooser dobP;
    private javax.swing.JTextField doctorIdA;
    private javax.swing.JTextField feD;
    private javax.swing.JTextField firstP;
    private javax.swing.JTextField genD;
    private javax.swing.JComboBox<String> genderD;
    private javax.swing.JTextField genderP;
    private javax.swing.JComboBox<String> genderSelect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField lastP;
    private javax.swing.JTextField mobileP;
    private javax.swing.JButton search;
    private javax.swing.JTextField speD;
    private javax.swing.JComboBox<String> specializationD;
    private javax.swing.JSpinner timeA;
    // End of variables declaration//GEN-END:variables

    private int getMonthDifference(Date parse, Date parse0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
