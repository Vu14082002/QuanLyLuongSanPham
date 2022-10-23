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
public class CapNhatNhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    public CapNhatNhanVienView() {
        initComponents();
        excute();
        
    }

    public void excute() {
       

        // custom table
        tbNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tbNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tbNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tbNhanVien.setRowHeight(25);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lbAnhDaiDien = new javax.swing.JLabel();
        btnAnhSanPham = new javax.swing.JPanel();
        lbAnhSanPhamOfbtn = new javax.swing.JLabel();
        lbErrTenSanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham1 = new javax.swing.JTextField();
        lbTenSanPham1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTenSanPham2 = new javax.swing.JTextField();
        lbErrTenSanPham1 = new javax.swing.JLabel();
        lbTenSanPham2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTenSanPham3 = new javax.swing.JTextField();
        lbErrTenSanPham2 = new javax.swing.JLabel();
        lbTenSanPham3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenSanPham4 = new javax.swing.JTextField();
        lbTenSanPham5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenSanPham5 = new javax.swing.JTextField();
        lbErrTenSanPham4 = new javax.swing.JLabel();
        lbTenSanPham4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbErrTenSanPham5 = new javax.swing.JLabel();
        txtTenSanPham6 = new javax.swing.JTextField();
        lbTenSanPham6 = new javax.swing.JLabel();
        lbErrTenSanPham6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtTenSanPham7 = new javax.swing.JTextField();
        lbTenSanPham7 = new javax.swing.JLabel();
        lbTenSanPham8 = new javax.swing.JLabel();
        rdNam = new javax.swing.JRadioButton();
        rdNu = new javax.swing.JRadioButton();
        jdateNgaySinh = new com.toedter.calendar.JDateChooser();
        cbPhongBanNhanVien = new javax.swing.JComboBox<>();
        cbChucVuNhanVien = new javax.swing.JComboBox<>();
        lbTenSanPham9 = new javax.swing.JLabel();
        lbTenSanPham10 = new javax.swing.JLabel();
        lbErrTenSanPham7 = new javax.swing.JLabel();
        lbTenSanPham11 = new javax.swing.JLabel();
        jdateNgaySinh1 = new com.toedter.calendar.JDateChooser();
        lbTenSanPham12 = new javax.swing.JLabel();
        lbErrTenSanPham8 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoaTrang = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1200, 700));

        jPanel1.setPreferredSize(new java.awt.Dimension(1250, 700));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Họ và tên", "Sô CCCd", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ảnh đại diện", "Email", "Phòng Ban", "Chức vụ", "Ngày vào làm", "Lương thỏa thuận"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(tbNhanVien);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.PAGE_START);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 500));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbAnhDaiDien.setBackground(new java.awt.Color(153, 0, 0));
        lbAnhDaiDien.setForeground(new java.awt.Color(255, 0, 51));
        lbAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/male.png"))); // NOI18N
        jPanel5.add(lbAnhDaiDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

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
                .addComponent(lbAnhSanPhamOfbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAnhSanPhamLayout.setVerticalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbAnhSanPhamOfbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(btnAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        lbErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 290, 20));

        txtTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham1.setBorder(null);
        jPanel5.add(txtTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 270, 40));

        lbTenSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham1.setText("Họ và tên:");
        jPanel5.add(lbTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 140, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("_______________________________");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 290, 20));

        txtTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham2.setBorder(null);
        jPanel5.add(txtTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 270, 40));

        lbErrTenSanPham1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham1.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham1.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 200, -1));

        lbTenSanPham2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham2.setText("Email:");
        jPanel5.add(lbTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("_______________________________");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 290, 20));

        txtTenSanPham3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham3.setBorder(null);
        jPanel5.add(txtTenSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 270, 40));

        lbErrTenSanPham2.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham2.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham2.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 200, -1));

        lbTenSanPham3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham3.setText("Sô CCCD:");
        jPanel5.add(lbTenSanPham3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 140, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 50, 290, 20));

        txtTenSanPham4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham4.setBorder(null);
        jPanel5.add(txtTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 270, 40));

        lbTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham5.setText("Mã nhân viên:");
        jPanel5.add(lbTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 140, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________________________");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 290, 20));

        txtTenSanPham5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham5.setBorder(null);
        jPanel5.add(txtTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 150, 270, 40));

        lbErrTenSanPham4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham4.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham4.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 200, -1));

        lbTenSanPham4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham4.setText("Chức vụ");
        jPanel5.add(lbTenSanPham4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 280, 130, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("_______________________________");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 290, 20));

        lbErrTenSanPham5.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham5.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham5.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham5, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 200, -1));

        txtTenSanPham6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham6.setBorder(null);
        jPanel5.add(txtTenSanPham6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 270, 40));

        lbTenSanPham6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham6.setText("Giới tính");
        jPanel5.add(lbTenSanPham6, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 140, 40));

        lbErrTenSanPham6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham6.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham6.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 380, 200, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("_______________________________");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 360, 290, 20));

        txtTenSanPham7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham7.setBorder(null);
        jPanel5.add(txtTenSanPham7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 340, 270, 40));

        lbTenSanPham7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham7.setText("Lương thỏa thuận:");
        jPanel5.add(lbTenSanPham7, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, 140, 40));

        lbTenSanPham8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham8.setText("Địa chỉ:");
        jPanel5.add(lbTenSanPham8, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 140, 40));

        rdNam.setSelected(true);
        rdNam.setText("Nam");
        rdNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNamActionPerformed(evt);
            }
        });
        jPanel5.add(rdNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, -1, -1));

        rdNu.setText("Nữ");
        rdNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdNuActionPerformed(evt);
            }
        });
        jPanel5.add(rdNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, -1, -1));

        jdateNgaySinh.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(jdateNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 280, 40));

        cbPhongBanNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng tài chính", "Phòng kinh doanh", "Phòng nhân sự" }));
        cbPhongBanNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cbPhongBanNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 280, 40));

        cbChucVuNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
        cbChucVuNhanVien.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbChucVuNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbChucVuNhanVienActionPerformed(evt);
            }
        });
        jPanel5.add(cbChucVuNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 280, 280, 40));

        lbTenSanPham9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham9.setText("Số điện thoại:");
        jPanel5.add(lbTenSanPham9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 140, 40));

        lbTenSanPham10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham10.setText("Ngày sinh:");
        jPanel5.add(lbTenSanPham10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 140, 40));

        lbErrTenSanPham7.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham7.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham7.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 200, -1));

        lbTenSanPham11.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham11.setText("Phòng ban:");
        jPanel5.add(lbTenSanPham11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, 130, 40));

        jdateNgaySinh1.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(jdateNgaySinh1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, 280, 40));

        lbTenSanPham12.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham12.setText("Ngày vào làm:");
        jPanel5.add(lbTenSanPham12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 140, 40));

        lbErrTenSanPham8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham8.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham8.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lbErrTenSanPham8, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 380, 200, -1));

        btnThem.setBackground(new java.awt.Color(46, 204, 113));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.setBorder(null);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 430, 160, 40));

        btnXoaTrang.setBackground(new java.awt.Color(255, 121, 121));
        btnXoaTrang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnXoaTrang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/xoaTrang.png"))); // NOI18N
        btnXoaTrang.setText("Hủy");
        btnXoaTrang.setBorder(null);
        jPanel5.add(btnXoaTrang, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 430, 170, 40));

        jPanel1.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
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

    private void rdNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNamActionPerformed

    private void rdNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdNuActionPerformed

    private void cbChucVuNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbChucVuNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbChucVuNhanVienActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaTrang;
    private javax.swing.JComboBox<String> cbChucVuNhanVien;
    private javax.swing.JComboBox<String> cbPhongBanNhanVien;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdateNgaySinh;
    private com.toedter.calendar.JDateChooser jdateNgaySinh1;
    private javax.swing.JLabel lbAnhDaiDien;
    private javax.swing.JLabel lbAnhSanPhamOfbtn;
    private javax.swing.JLabel lbErrTenSanPham;
    private javax.swing.JLabel lbErrTenSanPham1;
    private javax.swing.JLabel lbErrTenSanPham2;
    private javax.swing.JLabel lbErrTenSanPham4;
    private javax.swing.JLabel lbErrTenSanPham5;
    private javax.swing.JLabel lbErrTenSanPham6;
    private javax.swing.JLabel lbErrTenSanPham7;
    private javax.swing.JLabel lbErrTenSanPham8;
    private javax.swing.JLabel lbTenSanPham1;
    private javax.swing.JLabel lbTenSanPham10;
    private javax.swing.JLabel lbTenSanPham11;
    private javax.swing.JLabel lbTenSanPham12;
    private javax.swing.JLabel lbTenSanPham2;
    private javax.swing.JLabel lbTenSanPham3;
    private javax.swing.JLabel lbTenSanPham4;
    private javax.swing.JLabel lbTenSanPham5;
    private javax.swing.JLabel lbTenSanPham6;
    private javax.swing.JLabel lbTenSanPham7;
    private javax.swing.JLabel lbTenSanPham8;
    private javax.swing.JLabel lbTenSanPham9;
    private javax.swing.JRadioButton rdNam;
    private javax.swing.JRadioButton rdNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtTenSanPham1;
    private javax.swing.JTextField txtTenSanPham2;
    private javax.swing.JTextField txtTenSanPham3;
    private javax.swing.JTextField txtTenSanPham4;
    private javax.swing.JTextField txtTenSanPham5;
    private javax.swing.JTextField txtTenSanPham6;
    private javax.swing.JTextField txtTenSanPham7;
    // End of variables declaration//GEN-END:variables
}
