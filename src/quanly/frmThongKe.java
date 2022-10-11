/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanly;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import quanly.ConnectDB;

/**
 *
 * @author Admin
 */
public class frmThongKe extends javax.swing.JFrame {

    /**
     * Creates new form frmThongKe
     */
    public frmThongKe() {
        initComponents();
        LoadData();
    }
    private void LoadData() {
        try {
            ////// HOA DON//////////////
            Connection Conn = ConnectDB.getSQLServerConnection();
            Statement st = Conn.createStatement();
            cbbYear.removeAllItems();
            String sql1 = String.format("SELECT MIN(YEAR(dThoiGian)) FROM tbl_HoaDon");
            ResultSet rs1 = st.executeQuery(sql1);
            int yearMin = 0;
            while (rs1.next()) {
                yearMin=rs1.getInt(1);
            }
            String sql2 = String.format("SELECT Max(YEAR(dThoiGian)) FROM tbl_HoaDon");
            ResultSet rs2 = st.executeQuery(sql2);
            int yearMax = 0;
            while (rs2.next()) {
                yearMax=rs2.getInt(1);
            }
            for (int i = yearMin; i <= yearMax; i++) {
                cbbYear.addItem(String.format("%s",i));
            }
            ///////////////LUONG/////////////////
            Statement st1 = Conn.createStatement();
            cbbYearL.removeAllItems();
            String sqll = String.format("SELECT MIN(YEAR(dThoiGian)) FROM tbl_HoaDon");
            ResultSet rsl = st1.executeQuery(sqll);
            int yearMinl = 0;
            while (rsl.next()) {
                yearMinl=rsl.getInt(1);
            }
            String sqll1 = String.format("SELECT Max(YEAR(dThoiGian)) FROM tbl_HoaDon");
            ResultSet rsl1 = st1.executeQuery(sqll1);
            int yearMaxl = 0;
            while (rsl1.next()) {
                yearMaxl=rsl1.getInt(1);
            }
            for (int i = yearMinl; i <= yearMaxl; i++) {
                cbbYearL.addItem(String.format("%s",i));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void openFile(String file){
        try {
            File filePath =new  File(file);
            Desktop.getDesktop().open(filePath);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    private void exportExcel(JTable jt){
        try {
            JFileChooser chooser =new JFileChooser();
            chooser.showSaveDialog(jt);
            File saveFile = chooser.getSelectedFile();
            if (saveFile !=null) {
                saveFile = new File(saveFile.toString()+".xlsx");
                Workbook  wb = new XSSFWorkbook();
                Sheet sheet = wb.createSheet("test");
                Row rowColumn = sheet.createRow(0);
                for(int i=0;i<jt.getColumnCount();i++){
                    Cell cell = rowColumn.createCell(i);
                    cell.setCellValue(jt.getColumnName(i));
                }
                for (int j = 0; j < jt.getRowCount(); j++) {
                    Row row = sheet.createRow(j+1);
                    for (int k = 0; k < jt.getColumnCount(); k++) {
                        Cell cell = row.createCell(k);
                        if (jt.getValueAt(j, k)!=null) {
                            cell.setCellValue(jt.getValueAt(j, k).toString());
                        }
                    }
                }
                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());
            }
            else{
                JOptionPane.showMessageDialog(null, "Chưa nhập tên file!");
            }
        } 
        catch(FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException io) {
            System.out.println(io);
        }
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        cbbMonth = new javax.swing.JComboBox<>();
        cbbYear = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnTKDT = new javax.swing.JButton();
        btnEXDT = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTKDT = new javax.swing.JTable();
        lbDT = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        cbbMonthL = new javax.swing.JComboBox<>();
        cbbYearL = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnTKL = new javax.swing.JButton();
        btnEXL = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTKL = new javax.swing.JTable();
        lbL = new javax.swing.JLabel();
        btnV = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Consolas", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 51));
        jLabel1.setText("Thống Kê");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Tháng:");

        cbbMonth.setEditable(true);
        cbbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        cbbYear.setEditable(true);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Năm:");

        btnTKDT.setText("Thống kê");
        btnTKDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKDTActionPerformed(evt);
            }
        });

        btnEXDT.setText("Xuất dữ liệu");
        btnEXDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXDTActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(tbTKDT);

        lbDT.setForeground(new java.awt.Color(255, 0, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTKDT)
                        .addGap(18, 18, 18)
                        .addComponent(btnEXDT)
                        .addGap(46, 46, 46)
                        .addComponent(lbDT, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(cbbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTKDT)
                    .addComponent(btnEXDT)
                    .addComponent(lbDT))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Doanh Thu", jPanel1);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Tháng:");

        cbbMonthL.setEditable(true);
        cbbMonthL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        cbbYearL.setEditable(true);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Năm:");

        btnTKL.setText("Thống kê");
        btnTKL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKLActionPerformed(evt);
            }
        });

        btnEXL.setText("Xuất dữ liệu");
        btnEXL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEXLActionPerformed(evt);
            }
        });

        tbTKL.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane2.setViewportView(tbTKL);

        lbL.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbL.setForeground(new java.awt.Color(255, 0, 51));
        lbL.setText("a");

        btnV.setText("Xác nhận đã thanh toán");
        btnV.setEnabled(false);
        btnV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbMonthL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbbYearL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTKL)
                .addGap(18, 18, 18)
                .addComponent(btnEXL)
                .addGap(18, 18, 18)
                .addComponent(btnV, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(179, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbL, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane2)
                    .addGap(3, 3, 3)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbbMonthL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(cbbYearL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTKL)
                    .addComponent(btnEXL)
                    .addComponent(btnV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 422, Short.MAX_VALUE)
                .addComponent(lbL)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(37, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Lương", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(328, 328, 328)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEXDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXDTActionPerformed
        exportExcel(tbTKDT);
    }//GEN-LAST:event_btnEXDTActionPerformed

    private void btnTKDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKDTActionPerformed
        int a = 0;
        try {

            Connection c = ConnectDB.getSQLServerConnection();
            Statement s = c.createStatement();
            s = c.createStatement();
            String dayString="";
            ResultSet rs1 = s.executeQuery("select DAY(EOMONTH(convert(datetime,'" + cbbYear.getSelectedItem()+"/1/"+cbbMonth.getSelectedItem()+"',103)))");
            while (rs1.next()) {
                dayString=rs1.getString(1);
            }
            System.out.println("SELECT * FROM tbl_HoaDon WHERE dThoiGian BETWEEN '" + cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/1' AND '"+ cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/"+dayString+"' AND sStatus=N'Đã Thanh Toán'");
            ResultSet rs = s.executeQuery("SELECT * FROM tbl_HoaDon WHERE dThoiGian BETWEEN '" + cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/1' AND '"+ cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/"+dayString+"' AND sStatus=N'Ðã thanh toán'");
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            String[] ab = {"STT","Mã Hoá Đơn", "Tên Khách Hàng", "SDT", "Địa Chỉ", "Ghi Chú", "Thời Gian", "Phương Thức Thanh Toán", "Trạng Thái","Tổng Tiền"};
            model.setColumnIdentifiers(ab);
            int i=1;
            long tongtien=0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(i);
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                tongtien+=rs.getInt(10);
                model.addRow(v);
                i++;
                a++;
            }
            lbDT.setText("Tổng doanh thu tháng "+cbbMonth.getSelectedItem()+", năm "+cbbYear.getSelectedItem()+" là: "+tongtien);
            tbTKDT.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a > 0) {
            JOptionPane.showMessageDialog(null, "Xử lý thành công", "Thông báo", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu", "Thông báo", 1);
        }
    }//GEN-LAST:event_btnTKDTActionPerformed
    private void LoadLuong(){
        int a = 0;
        try {

            Connection c = ConnectDB.getSQLServerConnection();
            Statement s = c.createStatement();
            s = c.createStatement();
            String dayString="";
            ResultSet rs1 = s.executeQuery("select DAY(EOMONTH(convert(datetime,'" + cbbYearL.getSelectedItem()+"/1/"+cbbMonthL.getSelectedItem()+"',103)))");
            while (rs1.next()) {
                dayString=rs1.getString(1);
            }
            //System.out.println("SELECT * FROM tbl_HoaDon WHERE dThoiGian BETWEEN '" + cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/1' AND '"+ cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/"+dayString+"' AND sStatus=N'Đã Thanh Toán'");
             String sql = String.format("SELECT dbo.tbl_NhanVien.sMaNV, dbo.tbl_NhanVien.sHoTen, dbo.tbl_ChucVu.sTenCV, dbo.tbl_ChucVu.bLuongCB, dbo.tbl_Luong.iNgayCong, dbo.tbl_Luong.bLuongThuong, dbo.tbl_Luong.bTongLuong, dbo.tbl_Luong.ThangTT, \n" +
"                  dbo.tbl_TrangThai.sTenTrangThai\n" +
"FROM     dbo.tbl_ChucVu INNER JOIN\n" +
"                  dbo.tbl_NhanVien ON dbo.tbl_ChucVu.sMaCV = dbo.tbl_NhanVien.sMaCV INNER JOIN\n" +
"                  dbo.tbl_Luong ON dbo.tbl_NhanVien.sMaNV = dbo.tbl_Luong.FK_sMaNV INNER JOIN\n" +
"                  dbo.tbl_TrangThai ON dbo.tbl_Luong.FK_iMaTT = dbo.tbl_TrangThai.iMaTT WHERE dbo.tbl_Luong.ThangTT BETWEEN '" + cbbYearL.getSelectedItem()+"/"+cbbMonthL.getSelectedItem()+"/1' AND '"+ cbbYearL.getSelectedItem()+"/"+cbbMonthL.getSelectedItem()+"/"+dayString+"'");
            ResultSet rs = s.executeQuery(sql);
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            String[] ab = {"STT", "Mã Nhân Viên", "Họ Tên", "Chức Vụ", "Lương CB", "Ngày Công", "Lương Thưởng", "Tổng Lương", "Tháng TT","Trạng Thái"};
            model.setColumnIdentifiers(ab);
            int i=1;
            long tongtien=0;
            while (rs.next()) {
                Vector v = new Vector();
                v.add(i);
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                tongtien+=rs.getInt(7);
                model.addRow(v);
                i++;
                a++;
            }
            lbL.setText("Tổng lương nhân viên tháng "+cbbMonth.getSelectedItem()+", năm "+cbbYear.getSelectedItem()+" là: "+tongtien);
            tbTKL.setModel(model);
        } catch (Exception ex) {
            Logger.getLogger(frmNhanVien.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (a > 0) {
            JOptionPane.showMessageDialog(null, "Xử lý thành công", "Thông báo", 1);
        } else {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu", "Thông báo", 1);
        }
    }
    private void btnTKLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKLActionPerformed
        LoadLuong();
        btnV.setEnabled(true);
    }//GEN-LAST:event_btnTKLActionPerformed

    private void btnEXLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEXLActionPerformed
         exportExcel(tbTKL);
    }//GEN-LAST:event_btnEXLActionPerformed

    private void btnVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xác nhận đã thanh toán cho nhân viên tháng " + cbbMonthL.getSelectedItem() + " năm " + cbbYearL.getSelectedItem(), "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0) {
            try {
                int a=0;
                Connection c = ConnectDB.getSQLServerConnection();
                Statement s = c.createStatement();
                s = c.createStatement();
                String dayString = "";
                ResultSet rs1 = s.executeQuery("select DAY(EOMONTH(convert(datetime,'" + cbbYearL.getSelectedItem() + "/1/" + cbbMonthL.getSelectedItem() + "',103)))");
                while (rs1.next()) {
                    dayString = rs1.getString(1);
                }
                //System.out.println("SELECT * FROM tbl_HoaDon WHERE dThoiGian BETWEEN '" + cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/1' AND '"+ cbbYear.getSelectedItem()+"/"+cbbMonth.getSelectedItem()+"/"+dayString+"' AND sStatus=N'Đã Thanh Toán'");
                String sql = String.format("UPDATE tbl_Luong SET FK_iMaTT='5' WHERE dbo.tbl_Luong.ThangTT BETWEEN '" + cbbYearL.getSelectedItem() + "/" + cbbMonthL.getSelectedItem() + "/1' AND '" + cbbYearL.getSelectedItem() + "/" + cbbMonthL.getSelectedItem() + "/" + dayString + "'");
                a = s.executeUpdate(sql);
                if (a > 0) {
                    JOptionPane.showMessageDialog(null, "Xác nhận thanh toán thành công", "Thông báo", 1);
                    LoadLuong();
                } else
                    JOptionPane.showMessageDialog(null, "Xác nhận thanh toán thất bại", "Thông báo", 1);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_btnVActionPerformed
    
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
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEXDT;
    private javax.swing.JButton btnEXL;
    private javax.swing.JButton btnTKDT;
    private javax.swing.JButton btnTKL;
    private javax.swing.JButton btnV;
    private javax.swing.JComboBox<String> cbbMonth;
    private javax.swing.JComboBox<String> cbbMonthL;
    private javax.swing.JComboBox<String> cbbYear;
    private javax.swing.JComboBox<String> cbbYearL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDT;
    private javax.swing.JLabel lbL;
    private javax.swing.JTable tbTKDT;
    private javax.swing.JTable tbTKL;
    // End of variables declaration//GEN-END:variables
}
