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
public class QuanLyThongTinCaNhan extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    public QuanLyThongTinCaNhan() {
        initComponents();
        excute();
        
    }

    public void excute() {
       

        // custom table
    
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdNam);
        btnGroup.add(rdNu);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTenSanPham7 = new javax.swing.JLabel();
        lbTenSanPham5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lbTenSanPham13 = new javax.swing.JLabel();
        lbTenSanPham15 = new javax.swing.JLabel();
        lbTenSanPham3 = new javax.swing.JLabel();
        lbTenSanPham14 = new javax.swing.JLabel();
        lbTenSanPham9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenSanPham5 = new javax.swing.JTextField();
        lbTenSanPham10 = new javax.swing.JLabel();
        lbErrTenSanPham7 = new javax.swing.JLabel();
        jdateNgaySinh = new com.toedter.calendar.JDateChooser();
        lbErrTenSanPham4 = new javax.swing.JLabel();
        lbTenSanPham12 = new javax.swing.JLabel();
        jdateNgaySinh1 = new com.toedter.calendar.JDateChooser();
        lbErrTenSanPham8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        lbTenSanPham6 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        txtTenSanPham6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lbTenSanPham8 = new javax.swing.JLabel();
        lbErrTenSanPham1 = new javax.swing.JLabel();
        lbTenSanPham2 = new javax.swing.JLabel();
        txtTenSanPham2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham1 = new javax.swing.JTextField();
        lbTenSanPham1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbErrTenSanPham = new javax.swing.JLabel();
        lbAnhDaiDien = new javax.swing.JLabel();
        btnAnhSanPham = new javax.swing.JPanel();
        lbAnhSanPhamOfbtn = new javax.swing.JLabel();
        lbTenSanPham16 = new javax.swing.JLabel();
        lbPassword02 = new javax.swing.JLabel();
        txtPassWord = new javax.swing.JPasswordField();
        disable = new javax.swing.JLabel();
        show = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbTenSanPham7.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lbTenSanPham7.setText("Hồ Sơ Của Tôi");
        add(lbTenSanPham7, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 460, 50));

        lbTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham5.setText("Quản lý thông tin hồ sơ để bảo mật tài khoản");
        add(lbTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 460, 50));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1920, 20));

        lbTenSanPham13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham13.setText("Tên đăng nhập:");
        add(lbTenSanPham13, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 140, 40));

        lbTenSanPham15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham15.setText("NV0001");
        add(lbTenSanPham15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 140, 40));

        lbTenSanPham3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham3.setText("Sô CCCD:");
        add(lbTenSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 140, 40));

        lbTenSanPham14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham14.setText("012345678911");
        add(lbTenSanPham14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 190, 40));

        lbTenSanPham9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham9.setText("Số điện thoại:");
        add(lbTenSanPham9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 140, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________________");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 220, 20));

        txtTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham5.setBorder(null);
        add(txtTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 200, 40));

        lbTenSanPham10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham10.setText("Ngày sinh:");
        add(lbTenSanPham10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 140, 40));

        lbErrTenSanPham7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham7.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham7.setText("đây là dòng thông báo lỗi");
        add(lbErrTenSanPham7, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 200, -1));

        jdateNgaySinh.setDateFormatString("yyyy-MM-dd");
        add(jdateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 220, 40));

        lbErrTenSanPham4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham4.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham4.setText("đây là dòng thông báo lỗi");
        add(lbErrTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 200, -1));

        lbTenSanPham12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham12.setText("Ngày vào làm:");
        add(lbTenSanPham12, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 140, 40));

        jdateNgaySinh1.setDateFormatString("yyyy-MM-dd");
        add(jdateNgaySinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 220, 40));

        lbErrTenSanPham8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham8.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham8.setText("đây là dòng thông báo lỗi");
        add(lbErrTenSanPham8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 530, 200, -1));

        btnThem.setBackground(new java.awt.Color(46, 204, 113));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThem.setText("Lưu");
        btnThem.setBorder(null);
        add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 640, 170, 40));

        lbTenSanPham6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham6.setText("Giới tính");
        add(lbTenSanPham6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, 140, 40));

        rdNam.setSelected(true);
        rdNam.setText("Nam");
        rdNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(rdNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, -1, -1));

        rdNu.setText("Nữ");
        rdNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(rdNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        txtTenSanPham6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham6.setBorder(null);
        add(txtTenSanPham6, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 270, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("_______________________________");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 290, 20));

        lbTenSanPham8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham8.setText("Địa chỉ:");
        add(lbTenSanPham8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 330, 140, 40));

        lbErrTenSanPham1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham1.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham1.setText("đây là dòng thông báo lỗi");
        add(lbErrTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 280, -1));

        lbTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham2.setText("Email:");
        add(lbTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 140, 40));

        txtTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham2.setBorder(null);
        txtTenSanPham2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPham2ActionPerformed(evt);
            }
        });
        add(txtTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 260, 270, 40));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 290, 20));

        txtTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham1.setBorder(null);
        add(txtTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 190, 270, 40));

        lbTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham1.setText("Họ và tên:");
        add(lbTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 110, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 290, 20));

        lbErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham.setText("đây là dòng thông báo lỗi");
        add(lbErrTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 280, -1));

        lbAnhDaiDien.setBackground(new java.awt.Color(153, 0, 0));
        lbAnhDaiDien.setForeground(new java.awt.Color(255, 0, 51));
        lbAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/male.png"))); // NOI18N
        add(lbAnhDaiDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 200, -1, -1));

        lbAnhSanPhamOfbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnhSanPhamOfbtn.setText("Ảnh đại diện");
        lbAnhSanPhamOfbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAnhSanPhamOfbtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnAnhSanPhamLayout = new javax.swing.GroupLayout(btnAnhSanPham);
        btnAnhSanPham.setLayout(btnAnhSanPhamLayout);
        btnAnhSanPhamLayout.setHorizontalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbAnhSanPhamOfbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAnhSanPhamLayout.setVerticalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbAnhSanPhamOfbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(btnAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 340, -1, -1));

        lbTenSanPham16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham16.setText("Mật khẩu");
        add(lbTenSanPham16, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 140, 40));

        lbPassword02.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbPassword02.setText("_______________________________");
        add(lbPassword02, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 500, 280, -1));

        txtPassWord.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPassWord.setBorder(null);
        add(txtPassWord, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 480, 270, 40));

        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/disableeyeBlue.png"))); // NOI18N
        disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        add(disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 500, 30, -1));

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/showeyeBlue.png"))); // NOI18N
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 500, 30, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void lbAnhSanPhamOfbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhSanPhamOfbtnMouseClicked
        JFileChooser fileChooser = new JFileChooser("d:");
        //        int respone=fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new File(".\\src\\image\\Anh"));
        int respone=fileChooser.showSaveDialog(null);
        if(respone== JFileChooser.APPROVE_OPTION ){
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            String path = file.toString().split("src")[1].replace('\\', '/');
            System.out.println("path file split: "+file.toString().split("src")[1]);
            this.lbAnhDaiDien.setIcon(new ImageIcon(this.getClass().getResource(path)));
            System.out.println(this.lbAnhDaiDien.getIcon().toString());
        }
    }//GEN-LAST:event_lbAnhSanPhamOfbtnMouseClicked

    private void txtTenSanPham2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPham2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPham2ActionPerformed

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        this.txtPassWord.setEchoChar((char) 8226);
        this.disable.setVisible(true);
        this.disable.setEnabled(true);
        this.show.setVisible(false);
        this.show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        this.txtPassWord.setEchoChar((char) 0);
        this.disable.setVisible(false);
        this.disable.setEnabled(false);
        this.show.setVisible(true);
        this.show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnThem;
    private javax.swing.JLabel disable;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private com.toedter.calendar.JDateChooser jdateNgaySinh;
    private com.toedter.calendar.JDateChooser jdateNgaySinh1;
    private javax.swing.JLabel lbAnhDaiDien;
    private javax.swing.JLabel lbAnhSanPhamOfbtn;
    private javax.swing.JLabel lbErrTenSanPham;
    private javax.swing.JLabel lbErrTenSanPham1;
    private javax.swing.JLabel lbErrTenSanPham4;
    private javax.swing.JLabel lbErrTenSanPham7;
    private javax.swing.JLabel lbErrTenSanPham8;
    private javax.swing.JLabel lbPassword02;
    private javax.swing.JLabel lbTenSanPham1;
    private javax.swing.JLabel lbTenSanPham10;
    private javax.swing.JLabel lbTenSanPham12;
    private javax.swing.JLabel lbTenSanPham13;
    private javax.swing.JLabel lbTenSanPham14;
    private javax.swing.JLabel lbTenSanPham15;
    private javax.swing.JLabel lbTenSanPham16;
    private javax.swing.JLabel lbTenSanPham2;
    private javax.swing.JLabel lbTenSanPham3;
    private javax.swing.JLabel lbTenSanPham5;
    private javax.swing.JLabel lbTenSanPham6;
    private javax.swing.JLabel lbTenSanPham7;
    private javax.swing.JLabel lbTenSanPham8;
    private javax.swing.JLabel lbTenSanPham9;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JLabel show;
    private javax.swing.JPasswordField txtPassWord;
    private javax.swing.JTextField txtTenSanPham1;
    private javax.swing.JTextField txtTenSanPham2;
    private javax.swing.JTextField txtTenSanPham5;
    private javax.swing.JTextField txtTenSanPham6;
    // End of variables declaration//GEN-END:variables
}
