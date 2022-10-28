/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongNhan_DAO;
import DAO.NhanVien_DAO;
import DAO.PhongBan_DAO;
import Entity.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class TrangChuView extends javax.swing.JPanel {

    private NhanVien_DAO nhanVien_DAO;
    private DefaultTableModel modelTableTrangChu;
    private CongNhan_DAO congNhan_DAO;
    private PhongBan_DAO phongBan_DAO;

    /**
     * Creates new form TrangChuView
     */
    public TrangChuView() {
        initComponents();
        tblTrangChu.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblTrangChu.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblTrangChu.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
//        tbTrangChu.getTableHeader().setForeground(new Color(255,255,255));
        tblTrangChu.setRowHeight(25);
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        nhanVien_DAO = new NhanVien_DAO();
        phongBan_DAO = new PhongBan_DAO();
        congNhan_DAO = new CongNhan_DAO();
        modelTableTrangChu = (DefaultTableModel) tblTrangChu.getModel();
        excute();
        hienThiDuLieuLenBang();
        lblTongCongNhan.setText(congNhan_DAO.layRaSoLuongCongNhan()+"");
        lblTongNhanVien.setText(nhanVien_DAO.laySoLuongNhanVien()+"");
        lblTongPhongBan.setText(phongBan_DAO.layRaSoLuongPhongBan()+"");
    }

    public void excute() {

    }

    public void hienThiDuLieuLenBang() {
        while (tblTrangChu.getRowCount() != 0) {
            modelTableTrangChu.removeRow(0);
        }
        ArrayList<NhanVien> dsNhanVien = nhanVien_DAO.layDanhSachNhanVien();
        for (NhanVien nhanVien : dsNhanVien) {
            String data[] = {(modelTableTrangChu.getRowCount() + 1) + "", nhanVien.getMaNhanVien(), nhanVien.getHoTen(),
                 nhanVien.getSoDienThoai(), nhanVien.getEmail(), (nhanVien.isGioiTinh()) ? "Nam" : "Nữ",
                 nhanVien.getPhongBan().getTenPhongBan(), nhanVien.getChucVu()};
            modelTableTrangChu.addRow(data);
        }
        
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
        panelBorder5 = new CustomView.PanelBorder();
        lblPhongBan = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        lblTongPhongBan = new javax.swing.JLabel();
        panelBorder6 = new CustomView.PanelBorder();
        lblCongNhan = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        lblTongCongNhan = new javax.swing.JLabel();
        panelBorder7 = new CustomView.PanelBorder();
        lblTongNhanVien = new javax.swing.JLabel();
        lblNhanVien = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        scrTableTrangChu = new javax.swing.JScrollPane();
        tblTrangChu = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 300));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBorder5.setBackground(new java.awt.Color(0, 102, 102));
        panelBorder5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPhongBan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblPhongBan.setForeground(new java.awt.Color(255, 255, 255));
        lblPhongBan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPhongBan.setText("Tổng phòng ban");
        panelBorder5.add(lblPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TrangChu/phongban.png"))); // NOI18N
        panelBorder5.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 120));

        lblTongPhongBan.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblTongPhongBan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongPhongBan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongPhongBan.setText("1000");
        panelBorder5.add(lblTongPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 120, 60));

        jPanel1.add(panelBorder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 40, 300, 210));

        panelBorder6.setBackground(new java.awt.Color(0, 255, 204));
        panelBorder6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCongNhan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCongNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblCongNhan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCongNhan.setText("Tổng công nhân");
        panelBorder6.add(lblCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TrangChu/congnhan.png"))); // NOI18N
        panelBorder6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 120));

        lblTongCongNhan.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblTongCongNhan.setForeground(new java.awt.Color(255, 255, 255));
        lblTongCongNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongCongNhan.setText("1000");
        panelBorder6.add(lblTongCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 120, 60));

        jPanel1.add(panelBorder6, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 40, 300, 210));

        panelBorder7.setBackground(new java.awt.Color(255, 177, 66));
        panelBorder7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTongNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        lblTongNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblTongNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongNhanVien.setText("1000");
        panelBorder7.add(lblTongNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 120, 60));

        lblNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        lblNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNhanVien.setText("Tổng nhân viên");
        panelBorder7.add(lblNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 60));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/TrangChu/nhanvien.png"))); // NOI18N
        panelBorder7.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 120));

        jPanel1.add(panelBorder7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 300, 210));

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        tblTrangChu.getTableHeader().setBackground(new Color(32,136,203));
        tblTrangChu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Họ Tên", "Số điện thoai", "Email", "Giới tính", "Phòng ban", "Chức vụ"
            }
        ));
        tblTrangChu.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblTrangChu.setRowHeight(25);
        tblTrangChu.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblTrangChu.setShowVerticalLines(false);
        tblTrangChu.getTableHeader().setReorderingAllowed(false);
        scrTableTrangChu.setViewportView(tblTrangChu);

        add(scrTableTrangChu, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCongNhan;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblPhongBan;
    private javax.swing.JLabel lblTongCongNhan;
    private javax.swing.JLabel lblTongNhanVien;
    private javax.swing.JLabel lblTongPhongBan;
    private CustomView.PanelBorder panelBorder5;
    private CustomView.PanelBorder panelBorder6;
    private CustomView.PanelBorder panelBorder7;
    private javax.swing.JScrollPane scrTableTrangChu;
    private javax.swing.JTable tblTrangChu;
    // End of variables declaration//GEN-END:variables
}
