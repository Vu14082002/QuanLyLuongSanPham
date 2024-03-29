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
public class TimKiemSanPhamView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    public TimKiemSanPhamView() {
        initComponents();
        excute();
        
    }

    public void excute() {
       

        // custom table
        tbNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tbNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tbNhanVien.setRowHeight(25);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lbErrTenSanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham1 = new javax.swing.JTextField();
        lbTenSanPham1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSanPham4 = new javax.swing.JTextField();
        lbTenSanPham5 = new javax.swing.JLabel();
        btnCapNhat = new javax.swing.JButton();
        lbTenSanPham4 = new javax.swing.JLabel();
        cbChatLieu1 = new javax.swing.JComboBox<>();
        lbTenSanPham2 = new javax.swing.JLabel();
        cbChatLieu = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Màu sắc", "Chất liệu ", "Kích thước", "Ảnh sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(tbNhanVien);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 300));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 280, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 290, 20));

        txtTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham1.setBorder(null);
        jPanel5.add(txtTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 40, 270, 40));

        lbTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham1.setText("Tên sản phẩm");
        jPanel5.add(lbTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 140, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 290, 20));

        txtTenSanPham4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham4.setBorder(null);
        jPanel5.add(txtTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 270, 40));

        lbTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham5.setText("Mã sản phẩm");
        jPanel5.add(lbTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 140, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        btnCapNhat.setText("Tìm kiếm");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 230, 40));

        lbTenSanPham4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham4.setText("Chât liệu:");
        jPanel5.add(lbTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 140, 40));

        cbChatLieu1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbChatLieu1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Vải Polyester", "Nylon", "Vải 210D", "Vải 420D", "Vải 600D", "Vải 900D", "Vải canvas", "Vải nỉ", "Vải Siminli", "Vải cotton", "Vải kaki", "Vải jeans", "Vải kate", "Vải len", " " }));
        cbChatLieu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cbChatLieu1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 200, 40));

        lbTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham2.setText("Kích thước");
        jPanel5.add(lbTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 110, 140, 40));

        cbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", " " }));
        cbChatLieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cbChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 200, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JComboBox<String> cbChatLieu;
    private javax.swing.JComboBox<String> cbChatLieu1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbErrTenSanPham;
    private javax.swing.JLabel lbTenSanPham1;
    private javax.swing.JLabel lbTenSanPham2;
    private javax.swing.JLabel lbTenSanPham4;
    private javax.swing.JLabel lbTenSanPham5;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtTenSanPham1;
    private javax.swing.JTextField txtTenSanPham4;
    // End of variables declaration//GEN-END:variables
}
