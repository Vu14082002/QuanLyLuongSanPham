/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class ChamCongNhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel modelDanhSachNhanVienCanChamCong;

    public ChamCongNhanVienView() {
        initComponents();
        excute();
        insertTable();
       
    }

    public void insertTable() {
        modelDanhSachNhanVienCanChamCong = (DefaultTableModel) tbNhanVien.getModel();
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

        tbNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tbNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tbNhanVien.setRowHeight(25);
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        aaaa = new javax.swing.JScrollPane();
        tbDanhSachCanChamCong = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jtbDanhSachNhanVienChamCong = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 700));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 400));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 160, 40));

        aaaa.setBackground(new java.awt.Color(255, 255, 255));
        aaaa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên cần chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbDanhSachCanChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbDanhSachCanChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Họ và tên", "Sđt", "Phòng ban", "Chức vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDanhSachCanChamCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        aaaa.setViewportView(tbDanhSachCanChamCong);

        jPanel5.add(aaaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 750, 210));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Ngày chấm công:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Giờ đi làm:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 270, 120, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("NV001");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, 240, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Nguyễn Văn A");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 90, 240, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Mã nhân viên:");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 140, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Họ và tên:");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 140, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Làm trễ", "Nghỉ Không phép", "Nghỉ có Phép" }));
        jPanel5.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, 200, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Trạng thái:");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 130, 40));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("Ca làm:");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, 120, 40));

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "24" }));
        jPanel5.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 60, 40));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 01", "Ca 02", "Ca 03", "Chủ nhật" }));
        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 200, 40));

        jButton3.setBackground(new java.awt.Color(46, 204, 113));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        jButton3.setText("Lấy danh sách");
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 210, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Phút:");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 270, 50, 40));

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        jPanel5.add(jComboBox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 270, 60, 40));

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
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 340, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 340, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 160, 40));

        btnXoaTrang.setBackground(new java.awt.Color(255, 121, 121));
        btnXoaTrang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/xoaTrang.png"))); // NOI18N
        btnXoaTrang.setText("Xóa trắng");
        btnXoaTrang.setBorder(null);
        jPanel5.add(btnXoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 340, 170, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Ca làm:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 40));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 01", "Ca 02", "Ca 03", "Chủ nhật" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 80, 40));

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jtbDanhSachNhanVienChamCong.setBackground(new java.awt.Color(255, 255, 255));
        jtbDanhSachNhanVienChamCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên chấm công", "Mã nhân viên", "Họ và tên", "Sđt", "Phòng ban", "Chức vụ", "Ngày chấm công", "Ca làm", "Trạng thái", "Phép"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jtbDanhSachNhanVienChamCong.setViewportView(tbNhanVien);

        jPanel3.add(jtbDanhSachNhanVienChamCong, java.awt.BorderLayout.CENTER);

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

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jtbDanhSachNhanVienChamCong;
    private javax.swing.JTable tbDanhSachCanChamCong;
    private javax.swing.JTable tbNhanVien;
    // End of variables declaration//GEN-END:variables
}
