/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.NhanVien_DAO;
import DAO.PhongBan_DAO;
import Entity.NhanVien;
import Entity.PhongBan;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class TimKiemNhanVienView extends javax.swing.JPanel implements ActionListener {

    private NhanVien_DAO daoNhanVien;
    private PhongBan_DAO daoPhongBan;
    private NhanVien nhanvienEntity;
    private DefaultTableModel model;
    private DecimalFormat dcf;

    /**
     * Creates new form NhanVienView
     */
    public TimKiemNhanVienView(String fileName) throws IOException {
        initComponents();
        excute();

        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        daoNhanVien = new NhanVien_DAO();
        daoPhongBan = new PhongBan_DAO();
        dcf = new DecimalFormat("###,###,###,###,###.###");
        model = (DefaultTableModel) tblNhanVien.getModel();
        cmbPhongBan.removeAllItems();
        ArrayList<PhongBan> phongBan = daoPhongBan.layDanhSachPhongBan();
        for (PhongBan pb : phongBan) {
            cmbPhongBan.addItem(pb.getTenPhongBan());

        }
        cmbPhongBan.setSelectedItem("Tất cả");
        taiDuLieuLenBang("all", "all", "all", "all", "all", "all", "all");
        // bắt sự kiện cho btnTimkiem
        btnTimKiem.addActionListener(this);
        // xóa trắng các error field
        lblErrEmail.setText("");
        lblErrHoTen.setText("");
        lblErrSoCCCD.setText("");
        lblErrSoDienThoai.setText("");
        cmbPhongBan.removeItemAt(0);
        caiDatNgonNguChoView(fileName);
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaNhanVien.setText(prop.getProperty("maNhanVien"));
        lblHoTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblPhongBan.setText(prop.getProperty("phongBan"));
        cmbGioiTinh.removeAllItems();
        cmbGioiTinh.addItem(prop.getProperty("nam"));
        cmbGioiTinh.addItem(prop.getProperty("nu"));
        cmbGioiTinh.addItem(prop.getProperty("cmbTatCa"));
        cmbPhongBan.addItem("hello");
        cmbPhongBan.removeItemAt(cmbPhongBan.getItemCount() - 1);
        cmbPhongBan.addItem(prop.getProperty("cmbTatCa"));
        cmbPhongBan.setSelectedIndex(cmbPhongBan.getItemCount() - 1);

        cmbGioiTinh.setSelectedIndex(cmbGioiTinh.getItemCount() - 1);
        btnTimKiem.setText(prop.getProperty("Main_lblTimKiem"));
        ChangeName(tblNhanVien, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblNhanVien, 1, lblMaNhanVien.getText());
        ChangeName(tblNhanVien, 2, lblHoTen.getText());
        ChangeName(tblNhanVien, 3, lblSoCCCD.getText());
        ChangeName(tblNhanVien, 4, lblGioiTinh.getText());
        ChangeName(tblNhanVien, 5, prop.getProperty("ngaySinh"));
        ChangeName(tblNhanVien, 6, lblSoDienThoai.getText());
        ChangeName(tblNhanVien, 7, prop.getProperty("diaChi"));
        ChangeName(tblNhanVien, 8, prop.getProperty("anhDaiDien"));
        ChangeName(tblNhanVien, 9, lblEmail.getText());
        ChangeName(tblNhanVien, 10, lblPhongBan.getText());
        ChangeName(tblNhanVien, 11, prop.getProperty("chucVu"));
        ChangeName(tblNhanVien, 12, prop.getProperty("ngayVaoLam"));

    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void taiDuLieuLenBang(String maNhanVien, String hoTen, String cccd, String email, String soDienThoai, String gioiTinh, String tenPhongBan) {
        while (tblNhanVien.getRowCount() != 0) {
            model.removeRow(0);
        }
        ArrayList<PhongBan> phongBan = daoPhongBan.layDanhSachPhongBan();
        if (phongBan.size() > 0) {

            ArrayList<NhanVien> danhSachNhanVien = daoNhanVien.layDanhSachNhanVien();
            for (NhanVien nv : danhSachNhanVien) {
                String gioiTinhStr = nv.isGioiTinh() ? "Nam" : "Nữ";
                if (!maNhanVien.equalsIgnoreCase("all") || !hoTen.equalsIgnoreCase("all") || !cccd.equalsIgnoreCase("all")
                        || !email.equalsIgnoreCase("all") || !soDienThoai.equalsIgnoreCase("all") || !gioiTinh.equalsIgnoreCase("all")
                        || !tenPhongBan.equalsIgnoreCase("all")) {
                    boolean flag = true;
                    if (!maNhanVien.equalsIgnoreCase("all") && !nv.getMaNhanVien().toLowerCase().contains(maNhanVien.toLowerCase())) {
                        flag = false;
                    }
                    if (!hoTen.equalsIgnoreCase("all") && !nv.getHoTen().toLowerCase().contains(hoTen.toLowerCase())) {
                        flag = false;
                    }
                    if (!cccd.equalsIgnoreCase("all") && !nv.getMaCCCD().toLowerCase().contains(cccd.toLowerCase())) {
                        flag = false;
                    }
                    if (!email.equalsIgnoreCase("all") && !nv.getEmail().toLowerCase().contains(email.toLowerCase())) {
                        flag = false;
                    }
                    if (!soDienThoai.equalsIgnoreCase("all") && !nv.getSoDienThoai().toLowerCase().contains(soDienThoai.toLowerCase())) {
                        flag = false;
                    }
                    if (!gioiTinh.equalsIgnoreCase("all") && !gioiTinhStr.equalsIgnoreCase(gioiTinh)) {
                        flag = false;
                    }
                    if (!tenPhongBan.equalsIgnoreCase("all") && !nv.getPhongBan().getTenPhongBan().equalsIgnoreCase(tenPhongBan)) {
                        flag = false;
                    }
                    if (flag) {
                        String data[] = {(model.getRowCount() + 1) + "", nv.getMaNhanVien(), nv.getHoTen(), nv.getMaCCCD(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getNgaySinh().toString(),
                            nv.getSoDienThoai(), nv.getDiaChi(), nv.getAnhDaiDien(), nv.getEmail(), nv.getPhongBan().getTenPhongBan(),
                            nv.getChucVu(), nv.getNgayVaoLam().toString(), dcf.format(nv.getLuongThoaThuan())};
                        model.addRow(data);
                    }
                } else {
                    String data[] = {(model.getRowCount() + 1) + "", nv.getMaNhanVien(), nv.getHoTen(), nv.getMaCCCD(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getNgaySinh().toString(),
                        nv.getSoDienThoai(), nv.getDiaChi(), nv.getAnhDaiDien(), nv.getEmail(), nv.getPhongBan().getTenPhongBan(),
                        nv.getChucVu(), nv.getNgayVaoLam().toString(), dcf.format(nv.getLuongThoaThuan())};
                    model.addRow(data);
                }
            }
            if (tblNhanVien.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào có yêu cầu này!", "Kết quả tìm kiếm", JOptionPane.INFORMATION_MESSAGE);
            }
            if (tblNhanVien.getRowCount() != 0) {
                tblNhanVien.setRowSelectionInterval(0, 0);
            }
        }
    }

    public void excute() {

        // custom table
        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblNhanVien.setRowHeight(25);
        ButtonGroup btnGroup = new ButtonGroup();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        lblErrHoTen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblErrEmail = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoCCCD = new javax.swing.JTextField();
        lblErrSoCCCD = new javax.swing.JLabel();
        lblSoCCCD = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNhanVien = new javax.swing.JTextField();
        lblMaNhanVien = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        lblSoDienThoai = new javax.swing.JLabel();
        lblErrSoDienThoai = new javax.swing.JLabel();
        lblPhongBan = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        cmbPhongBan = new javax.swing.JComboBox<>();
        scrTableNhanVien = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 400));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblErrHoTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHoTen.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHoTen.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 60, 280, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 290, 20));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoTen.setBorder(null);
        txtHoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHoTenActionPerformed(evt);
            }
        });
        jPanel5.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 270, 50));

        lblHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHoTen.setText("Họ và tên:");
        jPanel5.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 140, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("_______________________________");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 110, 290, 20));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEmail.setBorder(null);
        jPanel5.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 270, 40));

        lblErrEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrEmail.setForeground(new java.awt.Color(204, 0, 0));
        lblErrEmail.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 280, -1));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblEmail.setText("Email:");
        jPanel5.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 100, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("_______________________________");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 290, 20));

        txtSoCCCD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoCCCD.setBorder(null);
        jPanel5.add(txtSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 270, 40));

        lblErrSoCCCD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoCCCD.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoCCCD.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 280, -1));

        lblSoCCCD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoCCCD.setText("Số CCCD:");
        jPanel5.add(lblSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 140, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 290, 20));

        txtMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaNhanVien.setBorder(null);
        jPanel5.add(txtMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 270, 40));

        lblMaNhanVien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMaNhanVien.setText("Mã nhân viên:");
        jPanel5.add(lblMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 140, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________________________");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 290, 20));

        txtSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoDienThoai.setBorder(null);
        jPanel5.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 270, 40));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        jPanel5.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 180, 140, 40));

        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ", " " }));
        cmbGioiTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmbGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 280, 40));

        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");
        jPanel5.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 140, 40));

        lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 280, -1));

        lblPhongBan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblPhongBan.setText("Phòng ban:");
        jPanel5.add(lblPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 130, 40));

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel5.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 230, 40));

        cmbPhongBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmbPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 280, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã nhân viên", "Họ và tên", "Số CCCD", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ảnh đại diện", "Email", "Phòng ban", "Chức vụ", "Ngày vào làm"
            }
        ));
        scrTableNhanVien.setViewportView(tblNhanVien);

        add(scrTableNhanVien, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtHoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHoTenActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbPhongBan;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrEmail;
    private javax.swing.JLabel lblErrHoTen;
    private javax.swing.JLabel lblErrSoCCCD;
    private javax.swing.JLabel lblErrSoDienThoai;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblPhongBan;
    private javax.swing.JLabel lblSoCCCD;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JScrollPane scrTableNhanVien;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSoCCCD;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTimKiem)) {
            String maNhanVien = txtMaNhanVien.getText().trim();
            String hoTen = txtHoTen.getText().trim();
            String soCCCD = txtSoCCCD.getText().trim();
            String email = txtEmail.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
            String phongBan = cmbPhongBan.getSelectedItem().toString();
            if (cmbGioiTinh.getSelectedIndex() == cmbGioiTinh.getItemCount() - 1) {
                System.out.println(cmbGioiTinh.getItemAt(cmbGioiTinh.getItemCount() - 1));
                System.out.println(cmbGioiTinh.getItemAt(cmbGioiTinh.getSelectedIndex()));
                gioiTinh = "all";
            }
            if (cmbPhongBan.getSelectedIndex() == cmbPhongBan.getItemCount() - 1) {
                phongBan = "all";
            }
            if (maNhanVien.equalsIgnoreCase("")) {
                maNhanVien = "all";
            }
            if (hoTen.equals("")) {
                hoTen = "all";
            }
            if (soCCCD.equals("")) {
                soCCCD = "all";
            }
            if (email.equals("")) {
                email = "all";
            }
            if (soDienThoai.equals("")) {
                soDienThoai = "all";
            }
            System.out.println(maNhanVien);
            System.out.println(hoTen);
            System.out.println(soCCCD);
            System.out.println(email);
            System.out.println(soDienThoai);
            System.out.println(gioiTinh);
            System.out.println(phongBan);

            taiDuLieuLenBang(maNhanVien, hoTen, soCCCD, email, soDienThoai, gioiTinh, phongBan);
        }
    }
}
