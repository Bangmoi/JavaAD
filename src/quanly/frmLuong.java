/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package quanly;

import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Admin
 */
public class frmLuong extends javax.swing.JFrame {
    private String text;
    /**
     * Creates new form frmLuong
     */
    public frmLuong() {
        initComponents();
        LoadData();
        LoadStart();
        //AutoCompleteDecorator.decorate(cbbMNV);
    }
    private void LoadStart(){
        try {
            Connection Conn = ConnectDB.getSQLServerConnection();
            Statement st = Conn.createStatement();
            Statement st1 = Conn.createStatement();
            tbL.removeAll();
            String sql = String.format("SELECT dbo.tbl_NhanVien.sMaNV, dbo.tbl_NhanVien.sHoTen, dbo.tbl_ChucVu.sTenCV, dbo.tbl_ChucVu.bLuongCB, dbo.tbl_Luong.iNgayCong, dbo.tbl_Luong.bLuongThuong, dbo.tbl_Luong.bTongLuong, dbo.tbl_Luong.ThangTT\n" +
"FROM     dbo.tbl_ChucVu INNER JOIN\n" +
"                  dbo.tbl_NhanVien ON dbo.tbl_ChucVu.sMaCV = dbo.tbl_NhanVien.sMaCV INNER JOIN\n" +
"                  dbo.tbl_Luong ON dbo.tbl_NhanVien.sMaNV = dbo.tbl_Luong.FK_sMaNV WHERE dbo.tbl_Luong.ThangTT BETWEEN '2022/1/1' AND '2022/1/31'");
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            String[] a = {"Mã Nhân Viên", "Họ Tên", "Chức Vụ", "Lương CB", "Ngày Công", "Lương Thưởng", "Tổng Lương", "Tháng TT"};
            model.setColumnIdentifiers(a);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                model.addRow(v);
            }
            tbL.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void LoadLuong(){
        try {
            Connection Conn = ConnectDB.getSQLServerConnection();
            Statement st = Conn.createStatement();
            Statement st1 = Conn.createStatement();
            tbL.removeAll();
            String dayString="";
            ResultSet rs1 = st.executeQuery("select DAY(EOMONTH(convert(datetime,'2022/1/"+(dateThangTT.getDate().getMonth()+1)+"',103)))");
            while (rs1.next()) {                
                dayString=rs1.getString(1);
            }
            String sql = String.format("SELECT dbo.tbl_NhanVien.sMaNV, dbo.tbl_NhanVien.sHoTen, dbo.tbl_ChucVu.sTenCV, dbo.tbl_ChucVu.bLuongCB, dbo.tbl_Luong.iNgayCong, dbo.tbl_Luong.bLuongThuong, dbo.tbl_Luong.bTongLuong, dbo.tbl_Luong.ThangTT\n" +
"FROM     dbo.tbl_ChucVu INNER JOIN\n" +
"                  dbo.tbl_NhanVien ON dbo.tbl_ChucVu.sMaCV = dbo.tbl_NhanVien.sMaCV INNER JOIN\n" +
"                  dbo.tbl_Luong ON dbo.tbl_NhanVien.sMaNV = dbo.tbl_Luong.FK_sMaNV WHERE dbo.tbl_Luong.ThangTT BETWEEN '2022/"+(dateThangTT.getDate().getMonth()+1)+"/1' AND '2022/"+(dateThangTT.getDate().getMonth()+1)+"/"+dayString+"'");
            ResultSet rs = st.executeQuery(sql);
            System.out.println(sql);
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            String[] a = {"Mã Nhân Viên", "Họ Tên", "Chức Vụ", "Lương CB", "Ngày Công", "Lương Thưởng", "Tổng Lương", "Tháng TT"};
            model.setColumnIdentifiers(a);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                model.addRow(v);
            }
            tbL.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void LoadData(){
         try {
                Connection Conn = ConnectDB.getSQLServerConnection();
                Statement st = Conn.createStatement();
                Statement st1 = Conn.createStatement();
                cbbMNV.removeAll();
                cbbMNV.removeAllItems();
                cbbCV.removeAll();
                cbbCV.removeAllItems();
                String sql1 = String.format("SELECT sMaNV FROM tbl_NhanVien WHERE FK_iMaTT ='3'");
                ResultSet rs1 = st.executeQuery(sql1);
                while (rs1.next()) {
                    cbbMNV.addItem(rs1.getString(1));
                }
                String sql2 = String.format("SELECT sTenCV FROM tbl_ChucVu");
                ResultSet rs2 = st1.executeQuery(sql2);
                while (rs2.next()) {
                    cbbCV.addItem(rs2.getString(1));
                }
            } catch (Exception e) {
                System.out.println(e);
            } 
    }
    private boolean CheckData(){
        boolean check=true;
        text="";
        int mouth=0;
        int year=0;
        if(dateThangTT.getDate()==null){
            check= false;
            text+="Chưa chọn ngày, ";
        }
        else{
            mouth= dateThangTT.getDate().getMonth()+1;
            year= dateThangTT.getDate().getYear();
        }
        int day=0;
        switch(mouth){
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:day=31;
            break;
            case 4:
            case 6:
            case 9:
            case 11:day=30;
            break;
            case 2:
                    if (year%100==0) {
                        if (year%400==0) {
                            day=29;
                        }
                        else{
                            day=28;
                        }
                    }
                    else if(year%4==0){
                        day=29;
                    }
                    else day=28;
                break;
                
        }
        if (txtNC.getText().equals("") || !(Pattern.matches("^[0-9]+$", txtNC.getText()))) {
            check = false;
            text += "Ngày công, ";
        } else {
            if (Integer.parseInt(txtNC.getText()) > day) {
                check = false;
                text += "Số ngày công không phù hợp trong tháng "+mouth+" ";
            }
            if (Integer.parseInt(txtNC.getText()) == 0) {
                check = false;
                text += "Số ngày công không được bằng 0";
            }
        }
        if (txtLT.getText().equals("") || !(Pattern.matches("^[0-9]+$", txtLT.getText()))) {
            check = false;
            text += "Lương thưởng, ";
        }
        return check;
    }
    private void Them(Connection c,Statement s){
        int a=0;
        try {
            s = c.createStatement();
            long tien = Long.parseLong(txtLCB.getText())*Long.parseLong(txtNC.getText())+Long.parseLong(txtLT.getText());
            txtTL.setText(""+tien);
            String date=null;
            try {
            SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
            date = dcn.format(dateThangTT.getDate() );
            } catch (Exception e) {
            }
            String sqlString = String.format("INSERT INTO tbl_Luong(FK_sMaNV, iNgayCong, bLuongThuong, bTongLuong, ThangTT, FK_iMaTT) VALUES(N'%s',N'%s',N'%s',N'%s',N'%s','6')", cbbMNV.getSelectedItem(), txtNC.getText(), txtLT.getText(), txtTL.getText(), date);
            a = s.executeUpdate(sqlString);

        } catch (Exception e) {
            System.out.println(e);
        }
        if(a>0){
            JOptionPane.showMessageDialog(null, "Thêm bản ghi thành công","Thông báo", 1);
        }
        else JOptionPane.showMessageDialog(null, "Thêm bản ghi thất bại","Thông báo", 1);
    }
    private void Sua(Connection c,Statement s){
        int a=0;
        try {
            s = c.createStatement();
            long tien = Long.parseLong(txtLCB.getText())*Long.parseLong(txtNC.getText())+Long.parseLong(txtLT.getText());
            txtTL.setText(""+tien);
            String date=null;
            try {
            SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
            date = dcn.format(dateThangTT.getDate() );
            } catch (Exception e) {
            }
                    String sqlString=String.format("UPDATE tbl_Luong SET iNgayCong = N'%s', bLuongThuong = N'%s', bTongLuong = N'%s', ThangTT = N'%s' WHERE FK_sMaNV='%s'",txtNC.getText(), txtLT.getText(), txtTL.getText(), date, cbbMNV.getSelectedItem());
                    a= s.executeUpdate(sqlString);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(a);
        if(a>0){
            JOptionPane.showMessageDialog(null, "Cập nhật bản ghi thành công","Thông báo", 1);
        }
        else JOptionPane.showMessageDialog(null, "Cập nhật bản ghi thất bại","Thông báo", 1);
    }
    private void Xoa(Connection c,Statement s){
        int a=0;
        try {
            s = c.createStatement();
            long tien = Long.parseLong(txtLCB.getText())*Long.parseLong(txtNC.getText())+Long.parseLong(txtLT.getText());
            txtTL.setText(""+tien);
            String sqlString=String.format("DELETE tbl_Luong WHERE FK_sMaNV = '%s' AND FK_iMaTT='6'",cbbMNV.getSelectedItem());
            a= s.executeUpdate(sqlString);
            LoadData();
        } catch (Exception e) {
            System.out.println(e);
        }
        if(a>0){
            JOptionPane.showMessageDialog(null, "Xoá thành công","Thông báo", 1);
            LoadData();
        }
        else JOptionPane.showMessageDialog(null, "Xoá thất bại","Thông báo", 1);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cbbMNV = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbbCV = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtLCB = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNC = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtLT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtTL = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dateThangTT = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbL = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jSlider1 = new javax.swing.JSlider();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Consolas", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 51));
        jLabel1.setText("Quản Lý Lương");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Mã NV:");

        cbbMNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbMNVActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Họ Tên: ");

        txtName.setEditable(false);
        txtName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtName.setEnabled(false);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Chức Vụ:");

        cbbCV.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Lương CB: ");

        txtLCB.setEditable(false);
        txtLCB.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtLCB.setEnabled(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Ngày Công: ");

        txtNC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtNC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNCActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Lương Thưởng:");

        txtLT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtLT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLTActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tổng Lương:");

        txtTL.setEditable(false);
        txtTL.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtTL.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Ngày TT:");

        tbL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbL.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbLMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbL);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xoá");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Xem danh sách lương tháng:");

        jSlider1.setMajorTickSpacing(1);
        jSlider1.setMaximum(12);
        jSlider1.setMinimum(1);
        jSlider1.setMinorTickSpacing(1);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setValue(1);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel8)
                                    .addGap(53, 53, 53))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(41, 41, 41)))
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbMNV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbCV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNC, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTL)
                                .addComponent(dateThangTT, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addGap(28, 28, 28)
                        .addComponent(btnUpdate)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 42, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(368, 368, 368)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbbMNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cbbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtLCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtNC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtLT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtTL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(dateThangTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAdd)
                            .addComponent(btnUpdate)
                            .addComponent(btnDelete))
                        .addContainerGap(112, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        try {
            if(CheckData()){
                try {
                    Connection c = ConnectDB.getSQLServerConnection();
                    Statement s = c.createStatement();
                    Them(c, s);
                    LoadLuong();
                    c.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, text+" chưa chính xác!","Thông báo", 1);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        try {
            int row = tbL.getSelectedRow();
            if (row!=-1) {
                if(CheckData()){
                    try {
                        Connection c = ConnectDB.getSQLServerConnection();
                        Statement s = c.createStatement();
                        Sua(c, s);
                        LoadLuong();
                        c.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, text+" chưa chính xác!","Thông báo", 1);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Chưa chọn bản ghi","Thông báo", 1);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            int row = tbL.getSelectedRow();
            if (row!=-1) {
                if(JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xoá bản ghi có mã: "+cbbMNV.getSelectedItem()+" \nChỉ có thể xoá bản ghi chưa thanh toán", "Thông báo", JOptionPane.OK_CANCEL_OPTION) == 0){
                    try {
                        Connection c = ConnectDB.getSQLServerConnection();
                        Statement s = c.createStatement();
                        Xoa(c, s);
                        LoadLuong();
                        c.close();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Chưa chọn bản ghi muốn xoá","Thông báo", 3);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged
        try {
            Connection Conn = ConnectDB.getSQLServerConnection();
            Statement st = Conn.createStatement();
            Statement st1 = Conn.createStatement();
            tbL.removeAll();
            String dayString="";
            ResultSet rs1 = st.executeQuery("select DAY(EOMONTH(convert(datetime,'2022/1/"+jSlider1.getValue()+"',103)))");
            while (rs1.next()) {                
                dayString=rs1.getString(1);
            }
            String sql = String.format("SELECT dbo.tbl_NhanVien.sMaNV, dbo.tbl_NhanVien.sHoTen, dbo.tbl_ChucVu.sTenCV, dbo.tbl_ChucVu.bLuongCB, dbo.tbl_Luong.iNgayCong, dbo.tbl_Luong.bLuongThuong, dbo.tbl_Luong.bTongLuong, dbo.tbl_Luong.ThangTT\n" +
"FROM     dbo.tbl_ChucVu INNER JOIN\n" +
"                  dbo.tbl_NhanVien ON dbo.tbl_ChucVu.sMaCV = dbo.tbl_NhanVien.sMaCV INNER JOIN\n" +
"                  dbo.tbl_Luong ON dbo.tbl_NhanVien.sMaNV = dbo.tbl_Luong.FK_sMaNV WHERE dbo.tbl_Luong.ThangTT BETWEEN '2022/"+jSlider1.getValue()+"/1' AND '2022/"+jSlider1.getValue()+"/"+dayString+"'");
            ResultSet rs = st.executeQuery(sql);
            //System.out.println(sql);
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnCount(0);
            model.setRowCount(0);
            String[] a = {"Mã Nhân Viên", "Họ Tên", "Chức Vụ", "Lương CB", "Ngày Công", "Lương Thưởng", "Tổng Lương", "Tháng TT"};
            model.setColumnIdentifiers(a);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                model.addRow(v);
            }
            tbL.setModel(model);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_jSlider1StateChanged

    private void cbbMNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbMNVActionPerformed
        try {
            Connection Conn = ConnectDB.getSQLServerConnection();
            Statement st = Conn.createStatement();
            String sql1 = String.format("SELECT dbo.tbl_NhanVien.sHoTen, dbo.tbl_ChucVu.sTenCV, dbo.tbl_ChucVu.bLuongCB\n" +
"FROM     dbo.tbl_ChucVu INNER JOIN\n" +
"                  dbo.tbl_NhanVien ON dbo.tbl_ChucVu.sMaCV = dbo.tbl_NhanVien.sMaCV "
                    + "WHERE  tbl_NhanVien.sMaNV='%s'  AND  tbl_NhanVien.FK_iMaTT ='3'", cbbMNV.getSelectedItem());
            //System.out.println(sql1);
            ResultSet rs1 = st.executeQuery(sql1);
            while (rs1.next()) {
                txtName.setText(rs1.getString(1));
                cbbCV.setSelectedItem(rs1.getString(2));
                txtLCB.setText(rs1.getString(3));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_cbbMNVActionPerformed

    private void txtNCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNCActionPerformed
        if(CheckData()){
            long tien = Long.parseLong(txtLCB.getText())*Long.parseLong(txtNC.getText())+Long.parseLong(txtLT.getText());
            txtTL.setText(""+tien);
        }
         else{
                JOptionPane.showMessageDialog(null, text+" chưa chính xác!","Thông báo", 1);
            }
    }//GEN-LAST:event_txtNCActionPerformed

    private void txtLTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLTActionPerformed
        if(CheckData()){
            long tien = Long.parseLong(txtLCB.getText())*Long.parseLong(txtNC.getText())+Long.parseLong(txtLT.getText());
            txtTL.setText(""+tien);
        }
        else{
                JOptionPane.showMessageDialog(null, text+" chưa chính xác!","Thông báo", 1);
            }
    }//GEN-LAST:event_txtLTActionPerformed

    private void tbLMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbLMouseClicked
        DefaultTableModel model = (DefaultTableModel)tbL.getModel();
        int row = tbL.getSelectedRow();
        cbbMNV.setSelectedItem(tbL.getModel().getValueAt(row, 0).toString());
        txtName.setText(tbL.getModel().getValueAt(row, 1).toString());
        cbbCV.setSelectedItem(tbL.getModel().getValueAt(row, 2).toString());
        txtLCB.setText(tbL.getModel().getValueAt(row, 3).toString());
        txtNC.setText(tbL.getModel().getValueAt(row, 4).toString());
        txtLT.setText(tbL.getModel().getValueAt(row, 5).toString());
        txtTL.setText(tbL.getModel().getValueAt(row, 6).toString());
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)model.getValueAt(row, 7));
            dateThangTT.setDate(date);
        } catch (ParseException ex) {
            //dateBirthday.setDate('1/1/2001');
            Logger.getLogger(frmLuong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tbLMouseClicked

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
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbCV;
    private javax.swing.JComboBox<String> cbbMNV;
    private com.toedter.calendar.JDateChooser dateThangTT;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JTable tbL;
    private javax.swing.JTextField txtLCB;
    private javax.swing.JTextField txtLT;
    private javax.swing.JTextField txtNC;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtTL;
    // End of variables declaration//GEN-END:variables
}
