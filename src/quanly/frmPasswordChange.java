/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanly;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class frmPasswordChange extends javax.swing.JFrame {
    private String tkString="admin";
    private String mkString;
    private String text;
    /**
     * Creates new form frmPasswordChange
     */
    public frmPasswordChange() {
        initComponents();
        LoadData();
    }
    private void LoadData(){
        try {
            Connection c = ConnectDB.getSQLServerConnection();
            Statement s = c.createStatement();
            s = c.createStatement();
            //System.out.println("SELECT * FROM tbl_HoaDon WHERE dThoiGian BETWEEN '" + cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/1' AND '"+ cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/"+dayString+"' AND sStatus=N'Đã Thanh Toán'");
             String sql = String.format("SELECT dbo.tbl_TaiKhoan.sMatKhau, dbo.tbl_Quyen.sTenQuyen, dbo.tbl_TrangThai.sTenTrangThai\n" +
"FROM     dbo.tbl_TaiKhoan INNER JOIN\n" +
"                  dbo.tbl_Quyen ON dbo.tbl_TaiKhoan.FK_sMaQuyen = dbo.tbl_Quyen.sMaQuyen INNER JOIN\n" +
"                  dbo.tbl_TrangThai ON dbo.tbl_TaiKhoan.FK_iMaTT = dbo.tbl_TrangThai.iMaTT WHERE dbo.tbl_TaiKhoan.sTaiKhoan='%s'",tkString);
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                lbTK.setText(lbTK.getText()+tkString);
                mkString=rs.getString(1);
                lbQ.setText(lbQ.getText()+rs.getString(2));
                lbTT.setText(lbTT.getText()+rs.getString(3));
            }
        } catch (Exception ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private boolean CheckData(){
        boolean check=true;
        if (pwold.getText().length()<8) {
            text= "Mật khẩu cũ không được dưới 8 kí tự!";
            check=false;
        }
        if (pwnew.getText().length()<8) {
            text= "Mật khẩu mới không được dưới 8 kí tự!";
            check=false;
        }
        if (pwNewconfirm.getText().length()<8) {
            text= "Mật khẩu xác nhận không được dưới 8 kí tự!";
            check=false;
        }
        if (!pwNewconfirm.getText().equals(pwnew.getText())) {
            text= "Mật khẩu mới và mật khẩu xác nhận không giống nhau!";
            check=false;
        }
        return check;
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbTK = new javax.swing.JLabel();
        lbQ = new javax.swing.JLabel();
        lbTT = new javax.swing.JLabel();
        pwold = new javax.swing.JPasswordField();
        pwnew = new javax.swing.JPasswordField();
        pwNewconfirm = new javax.swing.JPasswordField();
        lbtb = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Consolas", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 51));
        jLabel1.setText("Thay Đổi Mật Khẩu");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Mật khẩu cũ: ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Mật khẩu mới:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Xác nhận mật khẩu: ");

        lbTK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTK.setText("Tài khoản: ");

        lbQ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbQ.setText("Quyền hạn: ");

        lbTT.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTT.setText("Trạng thái: ");

        lbtb.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbtb.setForeground(new java.awt.Color(255, 51, 51));

        jButton1.setText("Xác nhận đổi mật khẩu");
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
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbQ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbTK, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbtb, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pwnew, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(57, 57, 57)
                                    .addComponent(pwold, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pwNewconfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))))
                        .addGap(0, 50, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(lbTK)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbQ)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pwold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pwnew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(pwNewconfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbtb)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn thay đổi mật khẩu?", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
            if (CheckData()) {
                if (pwold.getText().equals(mkString)) {
                    int a = 0;
                    try {
                        Connection c = ConnectDB.getSQLServerConnection();
                        Statement s = c.createStatement();
                        s = c.createStatement();
                        String sql = String.format("UPDATE tbl_TaiKhoan SET sMatKhau='%s' WHERE sTaiKhoan='%s'", pwnew.getText(), tkString);
                        a = s.executeUpdate(sql);
                    } catch (Exception ex) {
                        Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (a > 0) {
                        JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thành công!", "Thông báo", 1);
                        LoadData();
                        lbtb.setText("Thay đổi mật khẩu thành công!");
                        setTimeout(() -> lbtb.setText(" "), 10000);
                    } else {
                        JOptionPane.showMessageDialog(null, "Thay đổi mật khẩu thất bại!", "Thông báo", 1);
                        setTimeout(() -> lbtb.setText(" "), 10000);
                    }
                }
                else{
                    lbtb.setText("Mật khẩu hoặc tài khoản chưa chính xác vui lòng kiểm tra lại!");
                    setTimeout(() -> lbtb.setText(" "), 10000);
                }
            }
            else {
                lbtb.setText(text);
                setTimeout(() -> lbtb.setText(" "), 10000);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed
    public static void setTimeout(Runnable runnable, int delay) {
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
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
            java.util.logging.Logger.getLogger(frmPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPasswordChange.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmPasswordChange().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbQ;
    private javax.swing.JLabel lbTK;
    private javax.swing.JLabel lbTT;
    private javax.swing.JLabel lbtb;
    private javax.swing.JPasswordField pwNewconfirm;
    private javax.swing.JPasswordField pwnew;
    private javax.swing.JPasswordField pwold;
    // End of variables declaration//GEN-END:variables
}