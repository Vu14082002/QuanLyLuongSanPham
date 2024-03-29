/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import CustomView.MenuItem;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
// 1500 - 250 anf 800-75

/**
 *
 * @author December
 */
public class MainView extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    // cong nhan
    private MenuItem capNhatCongNhan = null;
    private MenuItem chamCongCongNhan = null;
    private MenuItem tinhLuongCongNhan = null;
    private MenuItem timKiemCongNhan = null;
    // nhan vien
    private MenuItem capNhatNhanVien = null;
    private MenuItem chamCongNhanVien = null;
    private MenuItem tinhLuongNhanVien = null;
    private MenuItem timKiemNhanVien = null;
    private MenuItem phanCongCongNhan = null;
    /// san pham
    private MenuItem capNhatSanPham = null;
    private MenuItem phanCongDoanSanPham = null;
    private MenuItem timKiemSanPham = null;
    // he thong
    private MenuItem dangXuat = null;
    private MenuItem thongTinCaNhan = null;
    // thong ke
    private MenuItem thongKeNhanVien = null;
    private MenuItem thongKeCongNhan = null;
    private ImageIcon iconSubMenuNonSelect = null;
    private ImageIcon iconSubMenuSelect = null;

    public MainView() {
        initComponents();
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

//        this.setResizable(false);
        execute();
    }

    private void execute() {
        ImageIcon iconHomePage = new ImageIcon(getClass().getResource("/image/icon/homepage.png"));
        ImageIcon iconEmployee = new ImageIcon(getClass().getResource("/image/icon/employee.png"));
        ImageIcon iconWorker = new ImageIcon(getClass().getResource("/image/icon/worker.png"));
        ImageIcon iconDepartment = new ImageIcon(getClass().getResource("/image/icon/department.png"));
        ImageIcon iconHeThong = new ImageIcon(getClass().getResource("/image/icon/system.png"));
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/image/icon/thongke.png"));
        iconSubMenuNonSelect = new ImageIcon(getClass().getResource("/image/icon/moon.png"));
        iconSubMenuSelect = new ImageIcon(getClass().getResource("/image/icon/moonSelect.png"));
        ImageIcon iconProduct = new ImageIcon(getClass().getResource("/image/icon/sanpham.png"));

        // create subMenu
        //sub menuCongNhan
        capNhatCongNhan = new MenuItem(iconSubMenuNonSelect, "Cập nhật", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new CapNhatCongNhanView1(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(capNhatCongNhan);
        }));
        chamCongCongNhan = new MenuItem(iconSubMenuNonSelect, "Chấm công", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new ChamCongCongNhanView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(chamCongCongNhan);
        }));
        timKiemCongNhan = new MenuItem(iconSubMenuNonSelect, "Tìm kiếm", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new TimKiemCongNhanView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(timKiemCongNhan);
        }));
        tinhLuongCongNhan = new MenuItem(iconSubMenuNonSelect, "Lương", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new LuongCongNhanView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((tinhLuongCongNhan));
        }));

        // submenu nhan vien
        capNhatNhanVien = new MenuItem(iconSubMenuNonSelect, "Cập nhật", (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new CapNhatNhanVienView(), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((capNhatNhanVien));
        });
        chamCongNhanVien = new MenuItem(iconSubMenuNonSelect, "Chấm công", (ActionEvent e) -> {
            pnBody.removeAll();
            pnBody.add(new ChamCongNhanVienView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((chamCongNhanVien));
        });
        tinhLuongNhanVien = new MenuItem(iconSubMenuNonSelect, "Lương", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                pnBody.add(new LuongNhanVienView(), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
                macDinh(tinhLuongNhanVien);
            }

        });
        timKiemNhanVien = new MenuItem(iconSubMenuNonSelect, "Tìm kiếm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                pnBody.add(new TimKiemNhanVienView(), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
                macDinh(timKiemNhanVien);
            }

        });
        phanCongCongNhan = new MenuItem(iconSubMenuNonSelect, "Phân công công việc", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new PhanCongCongViecView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(phanCongCongNhan);
        }));

        // San pham
        capNhatSanPham = new MenuItem(iconSubMenuNonSelect, "Cập nhật", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                pnBody.add(new SanPhamView(), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
                macDinh(capNhatSanPham);
            }
        });
        phanCongDoanSanPham = new MenuItem(iconSubMenuNonSelect, "Phân công đoạn", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new PhanCongDoanView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(phanCongDoanSanPham);
        }));
         timKiemSanPham = new MenuItem(iconSubMenuNonSelect, "Tìm kiếm", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new TimKiemSanPhamView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(timKiemSanPham);
        }));
        // he thong
        thongTinCaNhan = new MenuItem(iconSubMenuNonSelect, "Thông tin cá nhân", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new QuanLyThongTinCaNhan(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((thongTinCaNhan));
        }));
        dangXuat = new MenuItem(iconSubMenuNonSelect, "Đăng xuất", ((e) -> {
            macDinh((thongTinCaNhan));
            if (JOptionPane.showConfirmDialog(null, "Ban xác nhận đăng xuát hệ thống", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                new LoginView().setVisible(true);
            } else {
                macDinh((null));
            }
        }));
         thongKeNhanVien = new MenuItem(iconSubMenuNonSelect, "Nhân viên", ((e) -> {
            try {
                pnBody.removeAll();
                pnBody.add(new CapNhatNhanVienView(), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
                macDinh((thongKeNhanVien));
            } catch (Exception ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
         thongKeCongNhan = new MenuItem(iconSubMenuNonSelect, "Công nhân", ((e) -> {
            pnBody.removeAll();
            pnBody.add(new CapNhatCongNhanView1(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((thongKeCongNhan));
        }));
        // thong ke sub menu
        // add trang chu
        pnBody.removeAll();
        pnBody.add(new TrangChuView(), BorderLayout.CENTER);
        pnBody.repaint();
        pnBody.revalidate();
        // menu
        MenuItem menuTrangChu = new MenuItem(iconHomePage, "Trang chủ", (ActionEvent e) -> {
            pnBody.removeAll();
            pnBody.add(new TrangChuView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
        });
        MenuItem menuPhongBan = new MenuItem(iconDepartment, "Phòng ban", (ActionEvent e) -> {
            pnBody.removeAll();
            pnBody.add(new PhongBanView(), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
        });
        MenuItem menuNhanVien = new MenuItem(iconEmployee, "Nhân viên", (ActionEvent e) -> {
            resetSelect();
        }, capNhatNhanVien, chamCongNhanVien, phanCongCongNhan, tinhLuongNhanVien, timKiemNhanVien);
        MenuItem menuCongNhan = new MenuItem(iconWorker, "Công nhân", (ActionEvent e) -> {
            resetSelect();
        }, capNhatCongNhan, chamCongCongNhan, tinhLuongCongNhan, timKiemCongNhan);
        MenuItem menuSanPham = new MenuItem(iconProduct, "Sản phẩm", null, capNhatSanPham, phanCongDoanSanPham,timKiemSanPham);
        MenuItem menuThongKe = new MenuItem(iconThongKe, "Thống kê", null,thongKeNhanVien,thongKeCongNhan);
        MenuItem menuHeThong = new MenuItem(iconHeThong, "Hệ thống", null, thongTinCaNhan, dangXuat);
        addMenu(menuTrangChu, menuPhongBan, menuSanPham, menuNhanVien, menuCongNhan, menuThongKe, menuHeThong);
    }

    public void resetSelect() {
        capNhatCongNhan.setIcon(iconSubMenuNonSelect);
        chamCongCongNhan.setIcon(iconSubMenuNonSelect);
        tinhLuongCongNhan.setIcon(iconSubMenuNonSelect);
        timKiemCongNhan.setIcon(iconSubMenuNonSelect);
        // nhan vien
        capNhatNhanVien.setIcon(iconSubMenuNonSelect);
        chamCongNhanVien.setIcon(iconSubMenuNonSelect);
        tinhLuongNhanVien.setIcon(iconSubMenuNonSelect);
        timKiemNhanVien.setIcon(iconSubMenuNonSelect);
        phanCongCongNhan.setIcon(iconSubMenuNonSelect);
        /// san pham
        capNhatSanPham.setIcon(iconSubMenuNonSelect);
        phanCongDoanSanPham.setIcon(iconSubMenuNonSelect);
        timKiemSanPham.setIcon(iconSubMenuNonSelect);
        // he thong
        dangXuat.setIcon(iconSubMenuNonSelect);
        thongTinCaNhan.setIcon(iconSubMenuNonSelect);
        // thong ke
        thongKeNhanVien.setIcon(iconSubMenuNonSelect);
        thongKeCongNhan.setIcon(iconSubMenuNonSelect);
    }
    public void macDinh(MenuItem menu) {
        resetSelect();
        menu.setIcon(iconSubMenuSelect);
    }
    private void addMenu(MenuItem... menu) {
        for (MenuItem menu1 : menu) {
            menus.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            subMenu.forEach(menuItem -> {
                addMenu(menuItem);
            });
        }
        menus.revalidate();
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        pnMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        pnBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnHeader.setBackground(new java.awt.Color(41, 128, 185));
        pnHeader.setForeground(new java.awt.Color(41, 128, 185));
        pnHeader.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        pnHeader.setPreferredSize(new java.awt.Dimension(1500, 75));
        pnHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ LƯƠNG CÔNG TY THVT_SHOES");
        pnHeader.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1250, 75));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Hello, Admin");
        pnHeader.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 244, 70));

        jComboBox1.setBackground(new java.awt.Color(248, 194, 145));
        jComboBox1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiếng Việt", "Tiếng Anh" }));
        jComboBox1.setBorder(null);
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        pnHeader.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 18, 130, 51));

        getContentPane().add(pnHeader, java.awt.BorderLayout.PAGE_START);

        pnMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnMenu.setPreferredSize(new java.awt.Dimension(250, 725));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(60, 99, 130));
        menus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        getContentPane().add(pnMenu, java.awt.BorderLayout.LINE_START);

        pnBody.setBackground(new java.awt.Color(255, 51, 51));
        pnBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1516, 839));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        JOptionPane.showMessageDialog(this, "Su kien thay odi");        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel pnBody;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JPanel pnMenu;
    // End of variables declaration//GEN-END:variables
}
