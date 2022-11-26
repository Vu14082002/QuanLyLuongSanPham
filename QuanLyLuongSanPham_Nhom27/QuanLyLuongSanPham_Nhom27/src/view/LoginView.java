/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.CongNhan_DAO;
import DAO.NhanVien_DAO;
import Entity.NhanVien;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.JOptionPane;

/**
 *
 * @author December
 */
public class LoginView extends javax.swing.JFrame {

    /**
     * Creates new form LoginView
     */
    private NhanVien_DAO nhanVien_DAO;
    private CongNhan_DAO congNhan_DAO;
    private ArrayList<String> ngonNguList;

    public LoginView() {
        ngonNguList = new ArrayList<>();
        ngonNguList.add("./config/VietNam.properties");
        ngonNguList.add("./config/English.properties");
        ngonNguList.add("./config/France.properties");
        ngonNguList.add("./config/Malagasy.properties");

        initComponents();
        gui();
        getDataRemember();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        congNhan_DAO = new CongNhan_DAO();
        nhanVien_DAO = new NhanVien_DAO();
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTietDePhanMem.setText(prop.getProperty("Login_lblTietDePhanMem"));
        lblTieuDeDangNhap.setText(prop.getProperty("Login_lblTieuDeDangNhap"));
        lblTenDangNhap.setText(prop.getProperty("Login_lblTenDangNhap"));
        lblMatKhau.setText(prop.getProperty("Login_lblMatKhau"));
        lblQuenMatKhau.setText(prop.getProperty("Login_lblQuenMatKhau"));
        btnDangNhap.setText(prop.getProperty("Login_btnDangNhap"));
        lblNgonNgu.setText(prop.getProperty("Login_lblNgonNgu"));
    }

    public void getDataRemember() {
        String userName = "";
        userName = pref.get("userName", userName);
        String passWord = "";
        passWord = pref.get("password", passWord);
        this.txtDangNhap.setText(userName);
        this.txtMatKhau.setText(passWord);
    }

    public void gui() {
        this.txtDangNhap.setBackground(new Color(0, 0, 0, 1));
        this.txtMatKhau.setBackground(new Color(0, 0, 0, 1));
        //    this.txtOTP.setBackground(new Color(0, 0, 0, 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblLogo = new javax.swing.JLabel();
        lblTietDePhanMem = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblTieuDeDangNhap = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblExit = new javax.swing.JLabel();
        lblTenDangNhap = new javax.swing.JLabel();
        lbIcon = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lbUserName2 = new javax.swing.JLabel();
        disable = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        chkRemerberPassword = new javax.swing.JCheckBox();
        lblNgonNgu = new javax.swing.JLabel();
        btnDangNhap = new javax.swing.JButton();
        txtDangNhap = new javax.swing.JTextField();
        lbPassword02 = new javax.swing.JLabel();
        txtMatKhau = new javax.swing.JPasswordField();
        lblQuenMatKhau = new javax.swing.JLabel();
        cmbNgonNgu = new javax.swing.JComboBox<>();

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Tên tài khoản:");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/icons8_user_20px_1.png"))); // NOI18N

        jLabel8.setText("_______________________________________________________");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1000, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(124, 137, 251));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/loginImage.png"))); // NOI18N
        lblLogo.setPreferredSize(new java.awt.Dimension(400, 400));
        jPanel1.add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 60, 520, 380));

        lblTietDePhanMem.setBackground(new java.awt.Color(255, 255, 255));
        lblTietDePhanMem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTietDePhanMem.setForeground(new java.awt.Color(255, 255, 255));
        lblTietDePhanMem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTietDePhanMem.setText("PHẦN MỀM QUẢN LÝ LƯƠNG THVT_SHOES");
        jPanel1.add(lblTietDePhanMem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 500));

        jPanel2.setBackground(new java.awt.Color(52, 152, 219));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTieuDeDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblTieuDeDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTieuDeDangNhap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDeDangNhap.setText("ĐĂNG NHẬP");
        lblTieuDeDangNhap.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel2.add(lblTieuDeDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 470, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hello! Let's get started");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 70, 500, -1));

        lblExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        lblExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblExitMouseClicked(evt);
            }
        });
        jPanel2.add(lblExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, -1, -1));

        lblTenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        lblTenDangNhap.setText("Tên đăng nhập");
        jPanel2.add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 360, -1));

        lbIcon.setForeground(new java.awt.Color(255, 255, 255));
        lbIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/icons8_user_20px_1.png"))); // NOI18N
        lbIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel2.add(lbIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, -1, 36));

        lblMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblMatKhau.setText("Mật khẩu");
        jPanel2.add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 360, -1));

        lbUserName2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        lbUserName2.setForeground(new java.awt.Color(255, 255, 255));
        lbUserName2.setText("______________________________________________");
        jPanel2.add(lbUserName2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, -1));

        disable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/icons8_invisible_20px_1.png"))); // NOI18N
        disable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        disable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disableMouseClicked(evt);
            }
        });
        jPanel2.add(disable, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 30, -1));

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/icons8_eye_20px_1.png"))); // NOI18N
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        jPanel2.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 260, 31, -1));

        chkRemerberPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        chkRemerberPassword.setForeground(new java.awt.Color(255, 255, 255));
        chkRemerberPassword.setText("Remember password?");
        chkRemerberPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkRemerberPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chkRemerberPasswordMouseClicked(evt);
            }
        });
        chkRemerberPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRemerberPasswordActionPerformed(evt);
            }
        });
        jPanel2.add(chkRemerberPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        lblNgonNgu.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgonNgu.setForeground(new java.awt.Color(255, 255, 255));
        lblNgonNgu.setText("Chọn ngôn ngữ:");
        lblNgonNgu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblNgonNgu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblNgonNguMouseClicked(evt);
            }
        });
        jPanel2.add(lblNgonNgu, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 170, -1));

        btnDangNhap.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDangNhap.setForeground(new java.awt.Color(52, 152, 219));
        btnDangNhap.setText("ĐĂNG NHẬP");
        btnDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangNhapActionPerformed(evt);
            }
        });
        jPanel2.add(btnDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 405, 47));

        txtDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDangNhap.setForeground(new java.awt.Color(255, 255, 255));
        txtDangNhap.setBorder(null);
        txtDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDangNhapActionPerformed(evt);
            }
        });
        jPanel2.add(txtDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 360, 30));

        lbPassword02.setForeground(new java.awt.Color(255, 255, 255));
        lbPassword02.setText("_____________________________________________________");
        jPanel2.add(lbPassword02, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 378, -1));

        txtMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setBorder(null);
        jPanel2.add(txtMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 360, 30));

        lblQuenMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblQuenMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        lblQuenMatKhau.setText("Quên mật khẩu?");
        lblQuenMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblQuenMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblQuenMatKhauMouseClicked(evt);
            }
        });
        jPanel2.add(lblQuenMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 323, 190, -1));

        cmbNgonNgu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "VietNam", "English" }));
        cmbNgonNgu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNgonNguActionPerformed(evt);
            }
        });
        jPanel2.add(cmbNgonNgu, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 440, 190, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 550, 500));

        setSize(new java.awt.Dimension(1058, 496));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void chkRemerberPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRemerberPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chkRemerberPasswordActionPerformed

    private void btnDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangNhapActionPerformed
        System.out.println(this.txtDangNhap.getText());
        System.out.println(new String(this.txtMatKhau.getPassword()));
        String userName = txtDangNhap.getText();
        String password = new String(txtMatKhau.getPassword());
        if (userName.length() != 8 || password.length() < 6) {
            JOptionPane.showMessageDialog(null, "Tài khoản hoặc Mật khẩu không chính xác!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String loai = userName.substring(0, 2);
        if (loai.equals("NV")) {
            NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(userName);
            if (nhanVien != null && nhanVien.getMatKhau().equals(password)) {
                try {
                    new MainView(nhanVien.getMaNhanVien(), ngonNguList.get(cmbNgonNgu.getSelectedIndex())).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản hoặc Mật khẩu không chính xác!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "chính xác!", "Thông Báo Đăng nhập", JOptionPane.ERROR_MESSAGE);
            return;

        }

    }//GEN-LAST:event_btnDangNhapActionPerformed

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        this.txtMatKhau.setEchoChar((char) 8226);
        this.disable.setVisible(true);
        this.disable.setEnabled(true);
        this.show.setVisible(false);
        this.show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        this.txtMatKhau.setEchoChar((char) 0);
        this.disable.setVisible(false);
        this.disable.setEnabled(false);
        this.show.setVisible(true);
        this.show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked

    private void txtDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDangNhapActionPerformed

    private void lblExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblExitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblExitMouseClicked
    public Preferences pref = Preferences.userRoot().node("Remember");

    public void saveAccount(String userName, String password) {
        if (userName != null || password != null) {
            pref.put("userName", userName);
            pref.put("password", password);
        }
    }
    private void chkRemerberPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chkRemerberPasswordMouseClicked
        if (chkRemerberPassword.isSelected()) {
            saveAccount(this.txtDangNhap.getText(), new String(this.txtMatKhau.getPassword()));
        }
    }//GEN-LAST:event_chkRemerberPasswordMouseClicked

    private void lblNgonNguMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblNgonNguMouseClicked
        JOptionPane.showMessageDialog(this, "Mat khau moi chung toi da gui qua sdt ban dang ky :)");
    }//GEN-LAST:event_lblNgonNguMouseClicked

    private void lblQuenMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblQuenMatKhauMouseClicked
        new QuenMatKhauView().setVisible(true);
    }//GEN-LAST:event_lblQuenMatKhauMouseClicked

    private void cmbNgonNguActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNgonNguActionPerformed
        try {
            caiDatNgonNguChoView(ngonNguList.get(cmbNgonNgu.getSelectedIndex()));
        } catch (IOException ex) {
            Logger.getLogger(LoginView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbNgonNguActionPerformed

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
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangNhap;
    private javax.swing.JCheckBox chkRemerberPassword;
    private javax.swing.JComboBox<String> cmbNgonNgu;
    private javax.swing.JLabel disable;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbIcon;
    private javax.swing.JLabel lbPassword02;
    private javax.swing.JLabel lbUserName2;
    private javax.swing.JLabel lblExit;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNgonNgu;
    private javax.swing.JLabel lblQuenMatKhau;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JLabel lblTietDePhanMem;
    private javax.swing.JLabel lblTieuDeDangNhap;
    private javax.swing.JLabel show;
    private javax.swing.JTextField txtDangNhap;
    private javax.swing.JPasswordField txtMatKhau;
    // End of variables declaration//GEN-END:variables
}
