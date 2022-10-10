/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import CustomView.MenuItem;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author December
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    public MainView() {
        initComponents();
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
           
//        this.setResizable(false);
        execute();
    }

    private void execute() {
//        this.pnMenu.setSize(200,725);
        ImageIcon iconHomePage = new ImageIcon(getClass().getResource("/image/icon/homepage.png"));
        ImageIcon iconEmployee = new ImageIcon(getClass().getResource("/image/icon/employee.png"));
        ImageIcon iconWorker = new ImageIcon(getClass().getResource("/image/icon/worker.png"));
        ImageIcon iconDepartment = new ImageIcon(getClass().getResource("/image/icon/department.png"));
        ImageIcon iconFee = new ImageIcon(getClass().getResource("/image/icon/fee.png"));
        ImageIcon iconMoney = new ImageIcon(getClass().getResource("/image/icon/money.png"));
        ImageIcon iconLogout = new ImageIcon(getClass().getResource("/image/icon/logout.png"));
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/image/icon/thongke.png"));
        ImageIcon iconSubMenu = new ImageIcon(getClass().getResource("/image/icon/moon.png"));
        ImageIcon iconProduct = new ImageIcon(getClass().getResource("/image/icon/sanpham.png"));
        ImageIcon iconPhanCong = new ImageIcon(getClass().getResource("/image/icon/phancong.png"));

        // create subMenu
        MenuItem chamCongCongNhan = new MenuItem(iconSubMenu, "Công nhân", null);
        MenuItem chamCongNhanVien = new MenuItem(iconSubMenu, "Nhân viên", null);
        MenuItem tinhLuongCongNhan = new MenuItem(iconSubMenu, "Nhân viên", null);
        MenuItem tinhLuongNhanVien = new MenuItem(iconSubMenu, "Nhân viên", null);
        // add trang chu
        pnBody.removeAll();
        pnBody.add(new TrangChuView(), BorderLayout.CENTER);
        pnBody.repaint();
        pnBody.revalidate();
        // menu
        MenuItem menuTrangChu = new MenuItem(iconHomePage, "Trang chủ", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                pnBody.add(new TrangChuView(), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
            }
        });
        MenuItem menuPhongBan = new MenuItem(iconDepartment, "Phòng ban", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                pnBody.add(new PhongBan(), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
            }
        });
        MenuItem menuNhanVien = new MenuItem(iconEmployee, "Nhân viên", null);
        MenuItem menuCongNhan = new MenuItem(iconWorker, "Công nhân", null);
        MenuItem menuSanPham = new MenuItem(iconProduct, "Sản phẩm", null);
        MenuItem menuPhanCong = new MenuItem(iconPhanCong, "Phân công", null);
        MenuItem menuChamCong = new MenuItem(iconFee, "Chấm công", null, chamCongNhanVien, chamCongCongNhan);
        MenuItem menuTinhLuong = new MenuItem(iconMoney, "Tính lương", null, tinhLuongNhanVien, tinhLuongCongNhan);
        MenuItem menuThongKe = new MenuItem(iconThongKe, "Thống kê", null);
        MenuItem menuLogout = new MenuItem(iconLogout, "Đăng xuất", null);
        addMenu(menuTrangChu, menuPhongBan, menuNhanVien, menuNhanVien, menuCongNhan, menuSanPham, menuPhanCong, menuChamCong, menuTinhLuong, menuThongKe, menuLogout);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem menuItem : subMenu) {
                addMenu(menuItem);
//                menu[i].setVisible(false);
            }
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
        pnMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        pnBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pnHeader.setBackground(new java.awt.Color(248, 194, 145));
        pnHeader.setForeground(new java.awt.Color(153, 153, 255));
        pnHeader.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        pnHeader.setPreferredSize(new java.awt.Dimension(1500, 75));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ LƯƠNG CÔNG TY THVT_SHOES");

        javax.swing.GroupLayout pnHeaderLayout = new javax.swing.GroupLayout(pnHeader);
        pnHeader.setLayout(pnHeaderLayout);
        pnHeaderLayout.setHorizontalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnHeaderLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(272, Short.MAX_VALUE))
        );
        pnHeaderLayout.setVerticalGroup(
            pnHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
        );

        getContentPane().add(pnHeader, java.awt.BorderLayout.PAGE_START);

        pnMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnMenu.setPreferredSize(new java.awt.Dimension(250, 650));

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

        setSize(new java.awt.Dimension(1518, 847));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel pnBody;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JPanel pnMenu;
    // End of variables declaration//GEN-END:variables
}
