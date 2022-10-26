/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.ChamCongNhanVien_DAO;
import DAO.NhanVien_DAO;
import DAO.PhongBan_DAO;
import Entity.ChamCongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class ChamCongNhanVienView extends javax.swing.JPanel {

    private DefaultTableModel modelNhanVien;
    private DefaultTableModel modelChamCong;
    private NhanVien_DAO daoNhanVien;
    private NhanVien entityNhanVien;
    private ChamCongNhanVien_DAO daoChamCong;
    private ChamCongNhanVien entityChamCongNhanVien;
    private PhongBan entityPhongBan;
    private PhongBan_DAO daoPhongBan;

    public ChamCongNhanVienView() throws ParseException, Exception {
        initComponents();
        excute();
        taiDuLieuLenBangNhanVien();
        ConnectionDB.ConnectDB.getInstance().connect();
    }

    public void excute() throws ParseException {
        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblNhanVien.setRowHeight(25);

        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblNhanVien.setRowHeight(25);

        tblDanhSachCanChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblDanhSachCanChamCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblDanhSachCanChamCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblDanhSachCanChamCong.setRowHeight(25);

        tblDanhSachCanChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblDanhSachCanChamCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblDanhSachCanChamCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblDanhSachCanChamCong.setRowHeight(25);

        modelNhanVien = (DefaultTableModel) tblNhanVien.getModel();
        modelChamCong = (DefaultTableModel) tblDanhSachCanChamCong.getModel();
    }

    public void taiDuLieuLenBangNhanVien() throws ParseException {
        while (modelNhanVien.getRowCount() != 0) {
            modelNhanVien.removeRow(0);
        }
        daoChamCong = new ChamCongNhanVien_DAO();
        System.out.println("Thanh cong");
        ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();
        System.out.println(listChamCong.size());
        listChamCong.forEach(e -> {
            System.out.println(e.getMaChamCong());
        });
        for (ChamCongNhanVien chamCong : listChamCong) {
            String data[] = {(modelNhanVien.getRowCount()+1)+"",chamCong.getMaChamCong(), chamCong.getNguoiChamCong().getMaNhanVien(),
                chamCong.getNhanVien().getMaNhanVien(), chamCong.getNhanVien().getHoTen(), chamCong.getNhanVien().getSoDienThoai(),
                chamCong.getNhanVien().getPhongBan().getTenPhongBan(), chamCong.getNhanVien().getChucVu(), chamCong.getNgayChamCong().toString(),
                chamCong.getCaLam(), chamCong.getTrangThaiDiLam(), chamCong.getGioDiLam()};
            modelNhanVien.addRow(data);
        }
        if (tblNhanVien.getRowCount() > 0) {
            tblNhanVien.setRowSelectionInterval(0, 0);
            hienThiDuLieuLenTxt(0);
        }
    }
    public void taiDuLieuLenBangChamCong() throws ParseException {
        while (modelChamCong.getRowCount() != 0) {
            modelChamCong.removeRow(0);
        }
        daoChamCong = new ChamCongNhanVien_DAO();
        System.out.println("Thanh cong");
        ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();
        System.out.println(listChamCong.size());
        listChamCong.forEach(e -> {
            System.out.println(e.getMaChamCong());
        });
        for (ChamCongNhanVien chamCong : listChamCong) {
            String data[] = {(modelNhanVien.getRowCount()+1)+"",chamCong.getMaChamCong(), chamCong.getNguoiChamCong().getMaNhanVien(),
                chamCong.getNhanVien().getMaNhanVien(), chamCong.getNhanVien().getHoTen(), chamCong.getNhanVien().getSoDienThoai(),
                chamCong.getNhanVien().getPhongBan().getTenPhongBan(), chamCong.getNhanVien().getChucVu(), chamCong.getNgayChamCong().toString(),
                chamCong.getCaLam(), chamCong.getTrangThaiDiLam(), chamCong.getGioDiLam()};
            modelNhanVien.addRow(data);
        }
        if (tblNhanVien.getRowCount() > 0) {
            tblNhanVien.setRowSelectionInterval(0, 0);
            hienThiDuLieuLenTxt(0);
        }
    }

    public void hienThiDuLieuLenTxt(int dong) throws ParseException {
        lblValueMaNhanVien.setText(tblNhanVien.getValueAt(dong, 3).toString());
        lblValueHoVaTen.setText(tblNhanVien.getValueAt(dong, 4).toString());
        cmbTrangThai.setSelectedItem(tblNhanVien.getValueAt(dong, 10).toString());
        cmbCaLam.setSelectedItem(tblNhanVien.getValueAt(dong, 9).toString());
        cmbGio.setSelectedItem(tblNhanVien.getValueAt(dong, 11).toString().split("h")[0]);
        cmbPhut.setSelectedItem(tblNhanVien.getValueAt(dong, 11).toString().split("h")[1]);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jtbDanhSachNhanVienChamCong = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        aaaa = new javax.swing.JScrollPane();
        tblDanhSachCanChamCong = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblValueMaNhanVien = new javax.swing.JLabel();
        lblValueHoVaTen = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblHoVaTen = new javax.swing.JLabel();
        cmbTrangThai = new javax.swing.JComboBox<>();
        lblTrangThai = new javax.swing.JLabel();
        lblCaLam = new javax.swing.JLabel();
        cmbGio = new javax.swing.JComboBox<>();
        cmbCaLam = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        cmbPhut = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        lblTrangThai1 = new javax.swing.JLabel();
        cmbTrangThai1 = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jtbDanhSachNhanVienChamCong.setBackground(new java.awt.Color(255, 255, 255));
        jtbDanhSachNhanVienChamCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã chấm công", "Mã người chấm", "Mã nhân viên", "Họ và tên", "Sđt", "Phòng ban", "Chức vụ", "Ngày chấm công", "Ca làm", "Trạng thái", "Giờ đi làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jtbDanhSachNhanVienChamCong.setViewportView(tblNhanVien);

        add(jtbDanhSachNhanVienChamCong, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 400));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 160, 40));

        aaaa.setBackground(new java.awt.Color(255, 255, 255));
        aaaa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách nhân viên cần chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblDanhSachCanChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblDanhSachCanChamCong.setModel(new javax.swing.table.DefaultTableModel(
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
        tblDanhSachCanChamCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        aaaa.setViewportView(tblDanhSachCanChamCong);

        jPanel5.add(aaaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 750, 210));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Ngày chấm công:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Giờ đi làm:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 270, 120, 40));

        lblValueMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueMaNhanVien.setText("NV001");
        jPanel5.add(lblValueMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 40, 240, 40));

        lblValueHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueHoVaTen.setText("Nguyễn Văn A");
        jPanel5.add(lblValueHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 90, 240, 40));

        lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaNhanVien.setText("Mã nhân viên:");
        jPanel5.add(lblMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 40, 140, 40));

        lblHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoVaTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHoVaTen.setText("Họ và tên:");
        jPanel5.add(lblHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 90, 140, 40));

        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi Làm", "Đi Trễ", "Nghỉ Có Phép", "Nghỉ Không Phép" }));
        jPanel5.add(cmbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 150, 200, 40));

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTrangThai.setText("Trạng thái:");
        jPanel5.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 150, 130, 40));

        lblCaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCaLam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaLam.setText("Ca làm:");
        jPanel5.add(lblCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 210, 120, 40));

        cmbGio.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbGio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "7", "8", "9", "10", "11" }));
        jPanel5.add(cmbGio, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 270, 60, 40));

        cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sáng ", "Chiều", "Đêm" }));
        jPanel5.add(cmbCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 210, 200, 40));

        jButton3.setBackground(new java.awt.Color(46, 204, 113));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        jButton3.setText("Lấy danh sách");
        jButton3.setBorder(null);
        jPanel5.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 210, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Phút:");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 270, 50, 40));

        cmbPhut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbPhut.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        jPanel5.add(cmbPhut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 270, 60, 40));

        btnThem.setBackground(new java.awt.Color(46, 204, 113));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThem.setText("Chấm công tất cả");
        btnThem.setBorder(null);
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 340, 220, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 340, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 160, 40));

        btnXoaTrang.setBackground(new java.awt.Color(255, 121, 121));
        btnXoaTrang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/xoaTrang.png"))); // NOI18N
        btnXoaTrang.setText("Hủy");
        btnXoaTrang.setBorder(null);
        jPanel5.add(btnXoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 340, 170, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Ca làm:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, 90, 40));

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sáng", "Chiều", "Đêm" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 50, 80, 40));

        lblTrangThai1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTrangThai1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTrangThai1.setText("Hiển thị");
        jPanel5.add(lblTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 100, 40));

        cmbTrangThai1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Theo ngày chấm công" }));
        cmbTrangThai1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTrangThai1ActionPerformed(evt);
            }
        });
        jPanel5.add(cmbTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 170, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
         try {
            hienThiDuLieuLenTxt(tblNhanVien.getSelectedRow());
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang bị lỗi, quý khách làm phiền thoát chương tình");

        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void cmbTrangThai1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTrangThai1ActionPerformed
        JOptionPane.showMessageDialog(null, "Event");
    }//GEN-LAST:event_cmbTrangThai1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane aaaa;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cmbCaLam;
    private javax.swing.JComboBox<String> cmbGio;
    private javax.swing.JComboBox<String> cmbPhut;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private javax.swing.JComboBox<String> cmbTrangThai1;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jtbDanhSachNhanVienChamCong;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai1;
    private javax.swing.JLabel lblValueHoVaTen;
    private javax.swing.JLabel lblValueMaNhanVien;
    private javax.swing.JTable tblDanhSachCanChamCong;
    private javax.swing.JTable tblNhanVien;
    // End of variables declaration//GEN-END:variables
}
