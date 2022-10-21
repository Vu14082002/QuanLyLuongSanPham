/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class PhanCongCongViecView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel modelDanhSachNhanVienCanChamCong;
    public PhanCongCongViecView() {
        initComponents();
        excute();
        insertTable();
    }
    public void insertTable(){
        modelDanhSachNhanVienCanChamCong = (DefaultTableModel) tbPhanCong.getModel();
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
    }
    public void excute() {
//        this.txtMaNhanVien.setText("");
//        this.txtMaNhanVien.setBackground(new Color(0, 0, 0, 1));

        // custom table
        tbDanhSachCanChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbDanhSachCanChamCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tbDanhSachCanChamCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tbDanhSachCanChamCong.setRowHeight(25);
        
        tbPhanCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbPhanCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tbPhanCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tbPhanCong.setRowHeight(25);
        this.txtSoLuongLamDuoc.setBackground(new Color(0,0,0,1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSoLuongLamDuoc = new javax.swing.JTextField();
        lbErrMaNhanVien = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        aaaa = new javax.swing.JScrollPane();
        tbDanhSachCanChamCong = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbPhanCong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 700));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Số lượng cần làm:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 170, 170, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("PC001");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 20, 180, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Mã công đoạn");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 70, 190, 40));

        txtSoLuongLamDuoc.setText("0");
        txtSoLuongLamDuoc.setBorder(null);
        txtSoLuongLamDuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongLamDuocActionPerformed(evt);
            }
        });
        jPanel5.add(txtSoLuongLamDuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 180, 150, 30));

        lbErrMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrMaNhanVien.setForeground(new java.awt.Color(204, 0, 0));
        lbErrMaNhanVien.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 220, 190, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Tên công đoạn");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 120, 190, 40));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Rap giày");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 120, 180, 40));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 180, 10));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Tổ/Nhóm thực hiện:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 190, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Mã phân công:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 20, 190, 40));

        aaaa.setBackground(new java.awt.Color(255, 255, 255));
        aaaa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbDanhSachCanChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbDanhSachCanChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDanhSachCanChamCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        aaaa.setViewportView(tbDanhSachCanChamCong);

        jPanel5.add(aaaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 660, 270));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 260, 140, 40));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CD001", "CD002", "CD003" }));
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 70, 140, 40));

        btnThem.setBackground(new java.awt.Color(46, 204, 113));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 160, 40));

        btnXoaTrang.setBackground(new java.awt.Color(255, 121, 121));
        btnXoaTrang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/xoaTrang.png"))); // NOI18N
        btnXoaTrang.setText("Xóa trắng");
        btnXoaTrang.setBorder(null);
        jPanel5.add(btnXoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 370, 170, 40));

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phân công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbPhanCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã phân công", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên  công đoạn", "Tổ/Nhóm", "Ngày phân công"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPhanCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(tbPhanCong);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoLuongLamDuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongLamDuocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongLamDuocActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane aaaa;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbErrMaNhanVien;
    private javax.swing.JTable tbDanhSachCanChamCong;
    private javax.swing.JTable tbPhanCong;
    private javax.swing.JTextField txtSoLuongLamDuoc;
    // End of variables declaration//GEN-END:variables
}
