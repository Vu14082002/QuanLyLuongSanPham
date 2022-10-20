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
public class PhanCongDoanView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel modelDanhSachNhanVienCanChamCong;
    public PhanCongDoanView() {
        initComponents();
        excute();
        insertTable();
    }
    public void insertTable(){
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
        aaaa = new javax.swing.JScrollPane();
        tbDanhSachCanChamCong = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtSoLuongLamDuoc = new javax.swing.JTextField();
        lbErrMaNhanVien = new javax.swing.JLabel();
        txtSoLuongLamDuoc1 = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lbErrMaNhanVien1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtSoLuongLamDuoc2 = new javax.swing.JTextField();
        lbErrMaNhanVien2 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel28 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 700));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        aaaa.setBackground(new java.awt.Color(255, 255, 255));
        aaaa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbDanhSachCanChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbDanhSachCanChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbDanhSachCanChamCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        aaaa.setViewportView(tbDanhSachCanChamCong);

        jPanel5.add(aaaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 660, 280));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Tên công đoạn:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, 170, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("SP001");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 180, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Giày loại 1");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 70, 180, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Mã sản phẩm:");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 190, 40));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Tên sản phẩm:");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 190, 40));

        txtSoLuongLamDuoc.setText("0");
        txtSoLuongLamDuoc.setBorder(null);
        txtSoLuongLamDuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongLamDuocActionPerformed(evt);
            }
        });
        jPanel5.add(txtSoLuongLamDuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, 150, 30));

        lbErrMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrMaNhanVien.setForeground(new java.awt.Color(204, 0, 0));
        lbErrMaNhanVien.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 200, 190, -1));

        txtSoLuongLamDuoc1.setText("0");
        txtSoLuongLamDuoc1.setBorder(null);
        jPanel5.add(txtSoLuongLamDuoc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 230, 150, 30));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 260, 180, 10));

        lbErrMaNhanVien1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrMaNhanVien1.setForeground(new java.awt.Color(204, 0, 0));
        lbErrMaNhanVien1.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrMaNhanVien1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 270, 190, -1));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Mã công đoạn:");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 120, 190, 40));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("CD001");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 120, 180, 40));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 190, 180, 10));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Số lượng cần:");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 220, 170, 40));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Thời hạn:");
        jPanel5.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 340, 170, 40));

        txtSoLuongLamDuoc2.setText("0");
        txtSoLuongLamDuoc2.setBorder(null);
        jPanel5.add(txtSoLuongLamDuoc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 290, 150, 30));

        lbErrMaNhanVien2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrMaNhanVien2.setForeground(new java.awt.Color(204, 0, 0));
        lbErrMaNhanVien2.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrMaNhanVien2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 190, -1));
        jPanel5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 180, 10));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Giá tiền:");
        jPanel5.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 170, 40));
        jPanel5.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 350, 190, 30));

        jButton2.setBackground(new java.awt.Color(46, 204, 113));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        jButton2.setText("Thêm");
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 140, 40));

        jButton3.setBackground(new java.awt.Color(41, 128, 185));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        jButton3.setText("Xóa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 130, 40));

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        jButton4.setText("Cập nhật");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, 130, 40));

        jButton5.setBackground(new java.awt.Color(85, 239, 196));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        jButton5.setText("Lưu");
        jButton5.setBorder(null);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 140, 40));

        jButton6.setBackground(new java.awt.Color(46, 204, 113));
        jButton6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        jButton6.setText("Xóa trắng");
        jButton6.setBorder(null);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 140, 40));

        jPanel3.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tbNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên  công đoạn", "Số lượng cần làm", "Mưc độ hoàn thành"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(tbNhanVien);

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

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new ThemNhanVienView().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new CapNhatNhanVien().setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane aaaa;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbErrMaNhanVien;
    private javax.swing.JLabel lbErrMaNhanVien1;
    private javax.swing.JLabel lbErrMaNhanVien2;
    private javax.swing.JTable tbDanhSachCanChamCong;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtSoLuongLamDuoc;
    private javax.swing.JTextField txtSoLuongLamDuoc1;
    private javax.swing.JTextField txtSoLuongLamDuoc2;
    // End of variables declaration//GEN-END:variables
}
