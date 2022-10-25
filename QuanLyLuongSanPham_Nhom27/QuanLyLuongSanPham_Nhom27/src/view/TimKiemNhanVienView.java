/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author December
 */
public class TimKiemNhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    public TimKiemNhanVienView() {
        initComponents();
        excute();
        
    }

    public void excute() {
       

        // custom table
        jTable1.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        jTable1.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        jTable1.setRowHeight(25);
        ButtonGroup btnGroup = new ButtonGroup();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        lbErrTenSanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham1 = new javax.swing.JTextField();
        lbTenSanPham1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenSanPham2 = new javax.swing.JTextField();
        lbErrTenSanPham1 = new javax.swing.JLabel();
        lbTenSanPham2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenSanPham3 = new javax.swing.JTextField();
        lbErrTenSanPham2 = new javax.swing.JLabel();
        lbTenSanPham3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSanPham4 = new javax.swing.JTextField();
        lbTenSanPham5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenSanPham5 = new javax.swing.JTextField();
        lbTenSanPham6 = new javax.swing.JLabel();
        cbPhongBanNhanVien = new javax.swing.JComboBox<>();
        lbTenSanPham9 = new javax.swing.JLabel();
        lbErrTenSanPham7 = new javax.swing.JLabel();
        lbTenSanPham11 = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        cbPhongBanNhanVien1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 400));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 280, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 290, 20));

        txtTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham1.setBorder(null);
        jPanel5.add(txtTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 270, 40));

        lbTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham1.setText("Họ và tên:");
        jPanel5.add(lbTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 140, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("_______________________________");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 290, 20));

        txtTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham2.setBorder(null);
        jPanel5.add(txtTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 270, 40));

        lbErrTenSanPham1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham1.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham1.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 280, -1));

        lbTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham2.setText("Email:");
        jPanel5.add(lbTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("_______________________________");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 290, 20));

        txtTenSanPham3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham3.setBorder(null);
        jPanel5.add(txtTenSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 270, 40));

        lbErrTenSanPham2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham2.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham2.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 280, -1));

        lbTenSanPham3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham3.setText("Sô CCCD:");
        jPanel5.add(lbTenSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 140, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 290, 20));

        txtTenSanPham4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham4.setBorder(null);
        jPanel5.add(txtTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 270, 40));

        lbTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham5.setText("Mã nhân viên:");
        jPanel5.add(lbTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 140, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________________________");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 290, 20));

        txtTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham5.setBorder(null);
        jPanel5.add(txtTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 270, 40));

        lbTenSanPham6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham6.setText("Giới tính");
        jPanel5.add(lbTenSanPham6, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 140, 40));

        cbPhongBanNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ", " " }));
        cbPhongBanNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cbPhongBanNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 280, 40));

        lbTenSanPham9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham9.setText("Số điện thoại:");
        jPanel5.add(lbTenSanPham9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 140, 40));

        lbErrTenSanPham7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham7.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham7.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 280, -1));

        lbTenSanPham11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham11.setText("Phòng ban:");
        jPanel5.add(lbTenSanPham11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 130, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        btnCapNhat.setText("Tìm kiếm");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 230, 40));

        cbPhongBanNhanVien1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Phòng tài chính", "Phòng kinh doanh", "Phòng nhân sự" }));
        cbPhongBanNhanVien1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cbPhongBanNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 280, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã nhân viên", "Họ và tên", "Số CCCD", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ảnh đại diện", "Email", "Phòng ban", "Chức vụ", "Ngày vào làm"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JComboBox<String> cbPhongBanNhanVien;
    private javax.swing.JComboBox<String> cbPhongBanNhanVien1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbErrTenSanPham;
    private javax.swing.JLabel lbErrTenSanPham1;
    private javax.swing.JLabel lbErrTenSanPham2;
    private javax.swing.JLabel lbErrTenSanPham7;
    private javax.swing.JLabel lbTenSanPham1;
    private javax.swing.JLabel lbTenSanPham11;
    private javax.swing.JLabel lbTenSanPham2;
    private javax.swing.JLabel lbTenSanPham3;
    private javax.swing.JLabel lbTenSanPham5;
    private javax.swing.JLabel lbTenSanPham6;
    private javax.swing.JLabel lbTenSanPham9;
    private javax.swing.JTextField txtTenSanPham1;
    private javax.swing.JTextField txtTenSanPham2;
    private javax.swing.JTextField txtTenSanPham3;
    private javax.swing.JTextField txtTenSanPham4;
    private javax.swing.JTextField txtTenSanPham5;
    // End of variables declaration//GEN-END:variables
}
