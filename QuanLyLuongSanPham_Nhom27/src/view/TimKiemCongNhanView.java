/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongNhan_DAO;
import DAO.ToNhom_DAO;
import Entity.CongNhan;
import Entity.ToNhom;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
public class TimKiemCongNhanView extends javax.swing.JPanel implements ActionListener{

    /**
     * Creates new form NhanVienView
     */
    private CongNhan_DAO congNhan_DAO;
    private DefaultTableModel modelCongNhan;
    private ToNhom_DAO toNhom_DAO;

    public TimKiemCongNhanView(String fileName) throws IOException {
        initComponents();
        excute();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        congNhan_DAO = new CongNhan_DAO();
        toNhom_DAO = new ToNhom_DAO();
        modelCongNhan = (DefaultTableModel) tblCongNhan.getModel();
        btnTimKiem.addActionListener(this);
        taiDuLieuLenBang("all", "all", "all", "all", "all", "all", "all");
        loadCmbToNhom();
        cmbToNhom.removeItemAt(0);
        caiDatNgonNguChoView(fileName);

    }
    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaCongNhan.setText(prop.getProperty("maCongNhan"));
        lblHoTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblToNhom.setText(prop.getProperty("toNhom"));
        cmbGioiTinh.removeAllItems();
        cmbGioiTinh.addItem(prop.getProperty("nam"));
        cmbGioiTinh.addItem(prop.getProperty("nu"));
        cmbGioiTinh.addItem(prop.getProperty("cmbTatCa"));
        cmbToNhom.addItem("hello");
        
        cmbToNhom.removeItemAt(cmbToNhom.getItemCount() - 1);
        cmbToNhom.addItem(prop.getProperty("cmbTatCa"));
        cmbToNhom.setSelectedIndex(cmbToNhom.getItemCount() - 1);

        cmbGioiTinh.setSelectedIndex(cmbGioiTinh.getItemCount() - 1);
        btnTimKiem.setText(prop.getProperty("Main_lblTimKiem"));
        ChangeName(tblCongNhan, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblCongNhan, 1, lblMaCongNhan.getText());
        ChangeName(tblCongNhan, 2, lblHoTen.getText());
        ChangeName(tblCongNhan, 3, lblSoCCCD.getText());
        ChangeName(tblCongNhan, 4, lblGioiTinh.getText());
        ChangeName(tblCongNhan, 5, prop.getProperty("ngaySinh"));
        ChangeName(tblCongNhan, 6, lblSoDienThoai.getText());
        ChangeName(tblCongNhan, 7, prop.getProperty("diaChi"));
        ChangeName(tblCongNhan, 8, prop.getProperty("anhDaiDien"));
        ChangeName(tblCongNhan, 9, lblEmail.getText());
        ChangeName(tblCongNhan, 10, lblToNhom.getText());
        ChangeName(tblCongNhan, 11, prop.getProperty("ngayVaoLam"));

    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void loadCmbToNhom() {
        cmbToNhom.removeAllItems();
        ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
        cmbToNhom.addItem("Tất cả");
        for (ToNhom toNhom : dsToNhom) {
            cmbToNhom.addItem(toNhom.getTenToNhom());
        }
    }

    public void taiDuLieuLenBang(String maCongNhan, String hoTen, String soCCCD, String email, String soDienThoai, String gioiTinh,
             String toNhom) {
        while (tblCongNhan.getRowCount() != 0) {
            modelCongNhan.removeRow(0);
        }
        ArrayList<CongNhan> dsCongNhan = congNhan_DAO.layDanhSachCongNhan();
        for (CongNhan congNhan : dsCongNhan) {
            if (!maCongNhan.equalsIgnoreCase("all") || !soCCCD.equalsIgnoreCase("all")
                    || !email.equalsIgnoreCase("all") || !hoTen.equalsIgnoreCase("all")
                    || !soDienThoai.equalsIgnoreCase("all") || !gioiTinh.equalsIgnoreCase("all") || !toNhom.equalsIgnoreCase("all")) {
                boolean flag = true;
                String gioiTinhStr = congNhan.isGioiTinh() ? "Nam" : "Nữ";
                if (!maCongNhan.equalsIgnoreCase("all") && !congNhan.getMaCongNhan().toLowerCase().contains(maCongNhan.toLowerCase())) {
                    flag = false;
                }
                if (!hoTen.equalsIgnoreCase("all") && !congNhan.getHoTen().toLowerCase().contains(hoTen.toLowerCase())) {
                    flag = false;
                }
                if (!soCCCD.equalsIgnoreCase("all") && !congNhan.getMaCCCD().toLowerCase().contains(soCCCD.toLowerCase())) {
                    flag = false;
                }
                if (!email.equalsIgnoreCase("all") && !congNhan.getEmail().toLowerCase().contains(email.toLowerCase())) {
                    flag = false;
                }
                if (!soDienThoai.equalsIgnoreCase("all") && !congNhan.getSoDienThoai().toLowerCase().contains(soDienThoai.toLowerCase())) {
                    flag = false;
                }
                if (!gioiTinh.equalsIgnoreCase("all") && !gioiTinhStr.equalsIgnoreCase(gioiTinh)) {
                    flag = false;
                }
                if (!toNhom.equalsIgnoreCase("all") && !congNhan.getToNhom().getTenToNhom().equalsIgnoreCase(toNhom)) {
                    flag = false;
                }
                if (flag) {
                    String data[] = {(modelCongNhan.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen(), congNhan.getMaCCCD(),
                         congNhan.isGioiTinh() ? "Nam" : "Nữ", congNhan.getNgaySinh().toString(), congNhan.getSoDienThoai(), congNhan.getDiaChi(),
                        congNhan.getAnhDaiDien(), congNhan.getEmail(), congNhan.getToNhom().getTenToNhom(), congNhan.getNgayVaoLam().toString()};
                    modelCongNhan.addRow(data);
                }

            } else {
                String data[] = {(modelCongNhan.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen(), congNhan.getMaCCCD(),
                     congNhan.isGioiTinh() ? "Nam" : "Nữ", congNhan.getNgaySinh().toString(), congNhan.getSoDienThoai(), congNhan.getDiaChi(),
                    congNhan.getAnhDaiDien(), congNhan.getEmail(), congNhan.getToNhom().getTenToNhom(), congNhan.getNgayVaoLam().toString()};
                modelCongNhan.addRow(data);
            }
        }
        if (tblCongNhan.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Không có công nhân nào thỏa các tiêu chí trên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void excute() {

        // custom table
        tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongNhan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongNhan.setRowHeight(25);
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
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblEmail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtSoCCCD = new javax.swing.JTextField();
        lblSoCCCD = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaCongNhan = new javax.swing.JTextField();
        lblMaCongNhan = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        lblSoDienThoai = new javax.swing.JLabel();
        lblToNhom = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        cmbToNhom = new javax.swing.JComboBox<>();
        scrCongNhan = new javax.swing.JScrollPane();
        tblCongNhan = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 400));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 290, 20));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoTen.setBorder(null);
        jPanel5.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 270, 40));

        lblHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHoTen.setText("Họ và tên:");
        jPanel5.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 40, 140, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("_______________________________");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 290, 20));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEmail.setBorder(null);
        jPanel5.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 270, 40));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblEmail.setText("Email:");
        jPanel5.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("_______________________________");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 290, 20));

        txtSoCCCD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoCCCD.setBorder(null);
        jPanel5.add(txtSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 270, 40));

        lblSoCCCD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoCCCD.setText("Số CCCD:");
        jPanel5.add(lblSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 140, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 290, 20));

        txtMaCongNhan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaCongNhan.setBorder(null);
        jPanel5.add(txtMaCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 270, 40));

        lblMaCongNhan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMaCongNhan.setText("Mã công nhân");
        jPanel5.add(lblMaCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 140, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________________________");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 290, 20));

        txtSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoDienThoai.setBorder(null);
        jPanel5.add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 270, 40));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        jPanel5.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 180, 140, 40));

        cmbGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Nam", "Nữ", " " }));
        cmbGioiTinh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmbGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 280, 40));

        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");
        jPanel5.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 140, 40));

        lblToNhom.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblToNhom.setText("Tổ/Nhóm");
        jPanel5.add(lblToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 130, 40));

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel5.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 290, 230, 40));

        cmbToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Phòng tài chính", "Phòng kinh doanh", "Phòng nhân sự" }));
        cmbToNhom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmbToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 280, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        tblCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Stt", "Mã công nhân", "Họ và tên", "Số CCCD", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ảnh đại diện", "Email", "Tổ/Nhóm", "Ngày vào làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrCongNhan.setViewportView(tblCongNhan);

        add(scrCongNhan, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbToNhom;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMaCongNhan;
    private javax.swing.JLabel lblSoCCCD;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblToNhom;
    private javax.swing.JScrollPane scrCongNhan;
    private javax.swing.JTable tblCongNhan;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaCongNhan;
    private javax.swing.JTextField txtSoCCCD;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTimKiem)){
            String maCongNhan = txtMaCongNhan.getText().trim();
            String cccd = txtSoCCCD.getText().trim();
            String soDienThoai = txtSoDienThoai.getText().trim();
            String hoTen = txtHoTen.getText().trim();
            String email = txtEmail.getText().trim();
            String gioiTinh = cmbGioiTinh.getSelectedItem().toString();
            String toNhom = cmbToNhom.getSelectedItem().toString();
            if (maCongNhan.equals("")){
                maCongNhan = "all";
            }
            if (cccd.equals("")){
                cccd = "all";
            }
            if (soDienThoai.equals("")){
                soDienThoai = "all";
            }
            if (hoTen.equals("")){
                hoTen = "all";
            }
            if (email.equals("")){
                email = "all";
            }
            if (gioiTinh.equalsIgnoreCase("Tất cả")){
                gioiTinh = "all";
            }
            if (toNhom.equalsIgnoreCase("Tất cả")){
                toNhom = "all";
            }
            taiDuLieuLenBang(maCongNhan, hoTen, cccd, email, soDienThoai, gioiTinh, toNhom);
        }
    }
}
