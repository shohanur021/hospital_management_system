package hospital.management.system;

import java.awt.print.PrinterException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Doctor extends javax.swing.JFrame {

    private int id;

    public Doctor(int id) throws SQLException {
        initComponents();
        setLocation(54,60);
        this.id = id;
        patientList();
    }

    private Doctor() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private int PatientID;
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

    public void patientList() throws SQLException {
        int c;
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("SELECT  DoctorName,Qualification,Specialization,a.PatientID,FirstName+' '+LastName as Name,Age,p.Gender,DiseageName,VisitingDate FROM APPOINTMENT a INNER JOIN DOCTOR d ON a.DoctorID=d.DoctorID INNER JOIN PATIENT p ON p.PatientID=a.PatientID  WHERE d.DoctorID="+id+" AND a.ViewStatus='pending'");
                ResultSet rs = pst.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                c = rsmd.getColumnCount();
                DefaultTableModel dft = (DefaultTableModel) jTable1.getModel();
                dft.setRowCount(0);

                boolean parseDoctor = true;
                while (rs.next()) {
                    if (parseDoctor == true) {
                        jTextArea1.append("\t\t\tPrescription\n\n");
                        jTextArea1.append("========================== Doctor ============================\n\n");
                        jTextArea1.append(rs.getString("DoctorName") + "\n");
                        jTextArea1.append(rs.getString("Qualification") + "\n");
                        jTextArea1.append(rs.getString("Specialization") + "\n\n\n");
                        parseDoctor = false;
                    }

                    Vector v2 = new Vector();
                    for (int i = 1; i <= c; i++) {
                        v2.add(rs.getString("PatientID"));
                        v2.add(rs.getString("Name"));
                        v2.add(rs.getInt("Age"));
                        v2.add(rs.getString("Gender"));
                        v2.add(rs.getString("DiseageName"));
                        v2.add(rs.getString("VisitingDate"));
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

    public void removePatient() throws SQLException {
        try {
            con = DbConnection();
            if (con != null) {
                pst = con.prepareStatement("UPDATE APPOINTMENT SET ViewStatus='visited' WHERE PatientID=" + PatientID);
                pst.executeUpdate();
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        Clear = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setLayout(null);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 33)); // NOI18N
        jLabel1.setText("Welcome as a Doctor");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(450, 10, 360, 40);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Piteant Id", "Patient Name", "Age", "Gender", "Symptom", "visitingTime"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(10, 70, 640, 410);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(670, 70, 560, 410);

        jButton1.setBackground(new java.awt.Color(0, 204, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(970, 490, 110, 30);

        jButton2.setBackground(new java.awt.Color(204, 255, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton2.setText("Update Profile");
        jButton2.setToolTipText("");
        jButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(280, 500, 140, 40);

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
        jButton13.setBounds(1130, 500, 103, 41);

        Clear.setBackground(new java.awt.Color(0, 204, 102));
        Clear.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Clear.setText("Clear");
        Clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearActionPerformed(evt);
            }
        });
        jPanel1.add(Clear);
        Clear.setBounds(840, 490, 110, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        jTextArea1.append("========================== Patient ============================\n\n");
        DefaultTableModel d1 = (DefaultTableModel) jTable1.getModel();
        int selectIndex = jTable1.getSelectedRow();
        PatientID = parseInt(d1.getValueAt(selectIndex, 0).toString());
        jTextArea1.append("name        :\t " + d1.getValueAt(selectIndex, 1).toString() + "\n");
        jTextArea1.append("age          :\t " + d1.getValueAt(selectIndex, 2).toString() + "\n");
        jTextArea1.append("gender      :\t " + d1.getValueAt(selectIndex, 3).toString() + "\n");
        jTextArea1.append("VisitingDate:\t " + d1.getValueAt(selectIndex, 4).toString() + "\n");
        jTextArea1.append("exhortation :\n");
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            jTextArea1.print();
            jTextArea1.setText("");
            if (PatientID > 0) {
                try {
                    removePatient();
                } catch (SQLException ex) {
                    Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                patientList();
            } catch (SQLException ex) {
                Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (PrinterException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        UpdateProfile rp = new UpdateProfile("doctor", id);
        rp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void ClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearActionPerformed
        jTextArea1.setText("");
        try {
            patientList();
        } catch (SQLException ex) {
            Logger.getLogger(Doctor.class.getName()).log(Level.SEVERE, null, ex);
        }
        PatientID = 0;
    }//GEN-LAST:event_ClearActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Doctor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Clear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
