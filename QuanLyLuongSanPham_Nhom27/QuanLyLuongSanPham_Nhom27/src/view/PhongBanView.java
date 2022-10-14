/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class PhongBanView extends javax.swing.JPanel {

    /**
     * Creates new form TrangChuView
     */
    public PhongBanView() {
        initComponents();
        tbPhongBan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbPhongBan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tbPhongBan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
//        tbTrangChu.getTableHeader().setForeground(new Color(255,255,255));
        tbPhongBan.setRowHeight(25);
        execute();
      

    }

    public void execute() {
        this.txtManPhongBan.setBackground(new Color(0, 0, 0, 1));
        this.txtManPhongBan.setText("");
        this.txtTenPhongBan.setBackground(new Color(0, 0, 0, 1));
        this.txtTenPhongBan.setText("");
        this.lbErrTenPhongBan.setText("");

//        this.pnPhaiHeader.setBorder(new TitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Tìm kiếm nhân viên"));
        this.pnPhaiHeader.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Tìm kiếm phòng ban "));
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
        txtManPhongBan = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbTenPhongBan = new javax.swing.JLabel();
        txtTenPhongBan = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        pnPhaiHeader = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lbMaPhongBan = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        lbMaPhongBan2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        lbMaPhongBan3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        lbErrTenPhongBan = new javax.swing.JLabel();
        lbMaPhongBan1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbPhongBan = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtManPhongBan.setText("jTextField1");
        txtManPhongBan.setBorder(null);
        txtManPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtManPhongBanActionPerformed(evt);
            }
        });
        jPanel1.add(txtManPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 200, 30));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 290, 10));

        lbTenPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTenPhongBan.setText("Tên phòng ban:");
        jPanel1.add(lbTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 120, 170, 20));

        txtTenPhongBan.setText("jTextField1");
        txtTenPhongBan.setBorder(null);
        txtTenPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPhongBanActionPerformed(evt);
            }
        });
        jPanel1.add(txtTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, 200, 30));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, 290, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PhongBan/PhongBanView.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 200, 200));

        jButton1.setBackground(new java.awt.Color(241, 196, 15));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        jButton1.setText("Lưu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 240, 130, 40));

        jButton2.setBackground(new java.awt.Color(46, 204, 113));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        jButton2.setText("Thêm");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 140, 40));

        jButton3.setBackground(new java.awt.Color(41, 128, 185));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 130, 40));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        jButton4.setText("Cập nhật");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 130, 40));

        pnPhaiHeader.setBackground(new java.awt.Color(204, 204, 255));
        pnPhaiHeader.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm phòng ban"));
        pnPhaiHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton5.setBackground(new java.awt.Color(46, 204, 113));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        jButton5.setText("Tìm kiếm");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        pnPhaiHeader.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 140, 40));

        jButton6.setBackground(new java.awt.Color(41, 128, 185));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/reset.png"))); // NOI18N
        jButton6.setText("Đặt lại");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        pnPhaiHeader.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 230, 130, 40));

        lbMaPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMaPhongBan.setText("Mã phòng ban:");
        pnPhaiHeader.add(lbMaPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 180, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhiều nhất", "Ít nhất", "Tất cả" }));
        pnPhaiHeader.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 140, 40));

        lbMaPhongBan2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMaPhongBan2.setText("Sô lương nhân viên");
        pnPhaiHeader.add(lbMaPhongBan2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 190, 40));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhiều nhất", "Ít nhất", "Tất cả" }));
        pnPhaiHeader.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 140, 40));

        lbMaPhongBan3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMaPhongBan3.setText("Tên phòng ban:");
        pnPhaiHeader.add(lbMaPhongBan3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 190, 40));

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhiều nhất", "Ít nhất", "Tất cả" }));
        pnPhaiHeader.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 140, 40));

        jPanel1.add(pnPhaiHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, 380, 290));

        lbErrTenPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenPhongBan.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenPhongBan.setText("đây là dòng thông báo lỗi");
        jPanel1.add(lbErrTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 290, -1));

        lbMaPhongBan1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbMaPhongBan1.setText("Mã phòng ban:");
        jPanel1.add(lbMaPhongBan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 60, 170, 20));

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jScrollPane3.setBackground(new java.awt.Color(0, 0, 0));

        tbPhongBan.getTableHeader().setBackground(new Color(32,136,203));
        tbPhongBan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã phòng bàn", "Tên phòng ban", "Số lương nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbPhongBan.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tbPhongBan.setRowHeight(25);
        tbPhongBan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tbPhongBan.setShowVerticalLines(false);
        tbPhongBan.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tbPhongBan);

        add(jScrollPane3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtManPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtManPhongBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtManPhongBanActionPerformed

    private void txtTenPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhongBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenPhongBanActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbErrTenPhongBan;
    private javax.swing.JLabel lbMaPhongBan;
    private javax.swing.JLabel lbMaPhongBan1;
    private javax.swing.JLabel lbMaPhongBan2;
    private javax.swing.JLabel lbMaPhongBan3;
    private javax.swing.JLabel lbTenPhongBan;
    private javax.swing.JPanel pnPhaiHeader;
    private javax.swing.JTable tbPhongBan;
    private javax.swing.JTextField txtManPhongBan;
    private javax.swing.JTextField txtTenPhongBan;
    // End of variables declaration//GEN-END:variables
}
