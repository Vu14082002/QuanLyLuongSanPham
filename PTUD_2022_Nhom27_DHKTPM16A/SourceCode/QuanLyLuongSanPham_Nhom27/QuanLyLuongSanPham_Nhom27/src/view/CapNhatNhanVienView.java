/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.NhanVien_DAO;
import DAO.PhongBan_DAO;
import Entity.NhanVien;
import Entity.PhongBan;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.io.File;
import java.sql.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class CapNhatNhanVienView extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;

    /**
     * Creates new form NhanVienView
     */
    private NhanVien_DAO daoNhanVien = new NhanVien_DAO();
    private NhanVien nhanvienEntity;
    private DefaultTableModel model;
    private DecimalFormat dcf;
    private DateFormat df = new SimpleDateFormat("yyy-MM-dd");
    private Date date;
    private boolean isThem = false;
    private boolean isCapNhat = false;

    public CapNhatNhanVienView() throws Exception {
        initComponents();
        excute();
        taiDuLieuLenBang();
        this.txtMaNhanVien.setEditable(false);

    }

    public void excute() throws Exception {
        // custom table
        model = (DefaultTableModel) tblNhanVien.getModel();
        tblNhanVien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblNhanVien.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblNhanVien.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblNhanVien.setRowHeight(25);
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdoNam);
        btnGroup.add(rdoNu);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
        ConnectionDB.ConnectDB.getInstance().connect();
        dcf = new DecimalFormat("###,###,###,###,###.###");
        setEnableForInput(false);
        lblErrHoVaTen.setText("");
        lblErrSoCCCD.setText("");
        lblErrEmail.setText("");
        lblErrSoDienThoai.setText("");
        lblErrDiaChi.setText("");
        lblErrNgaySinh.setText("");
        lblErrNgayVaoLam.setText("");
        lblErrLuongThoaThuan.setText("");

    }

    public void taiDuLieuLenBang() throws ParseException {
        while (tblNhanVien.getRowCount() != 0) {
            model.removeRow(0);
        }
        cboPhongBan.removeAllItems();
        PhongBan_DAO daoPhongBan = new PhongBan_DAO();
        ArrayList<PhongBan> phongBan = daoPhongBan.layDanhSachPhongBan();
       if(phongBan.size()>0){
            for (PhongBan pb : phongBan) {
            cboPhongBan.addItem(pb.getTenPhongBan());
        }
        ArrayList<NhanVien> danhSachNhanVien = daoNhanVien.layDanhSachNhanVien();
        for (NhanVien nv : danhSachNhanVien) {
            String data[] = {(model.getRowCount() + 1) + "", nv.getMaNhanVien(), nv.getHoTen(), nv.getMaCCCD(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getNgaySinh().toString(),
                nv.getSoDienThoai(), nv.getDiaChi(), nv.getAnhDaiDien(), nv.getEmail(), nv.getPhongBan().getTenPhongBan(),
                nv.getChucVu(), nv.getNgayVaoLam().toString(), dcf.format(nv.getLuongThoaThuan())};
            model.addRow(data);
        }
        if (tblNhanVien.getRowCount() != 0) {
            tblNhanVien.setRowSelectionInterval(0, 0);
            hienThiDuLieuLenTxt(0);
        }
       }
    }

    @SuppressWarnings("deprecation")
    public void hienThiDuLieuLenTxt(int dong) throws ParseException {
        txtMaNhanVien.setText(tblNhanVien.getValueAt(dong, 1).toString());
        txtHoVaTen.setText(tblNhanVien.getValueAt(dong, 2).toString());
        txtSoCCCD.setText(tblNhanVien.getValueAt(dong, 3).toString());
        if (rdoNam.getText() == tblNhanVien.getValueAt(dong, 4).toString()) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        dcsNgaySinh.setDate(df.parse(tblNhanVien.getValueAt(dong, 5).toString()));
        txtSoDienThoai.setText(tblNhanVien.getValueAt(dong, 6).toString());
        txtDiaChi.setText(tblNhanVien.getValueAt(dong, 7).toString());
        txtEmail.setText(tblNhanVien.getValueAt(dong, 9).toString());
        cboPhongBan.setSelectedItem(tblNhanVien.getValueAt(dong, 10).toString());
        cboChucVu.setSelectedItem(tblNhanVien.getValueAt(dong, 11).toString());
        dcsNgayVaoLam.setDate(df.parse(tblNhanVien.getValueAt(dong, 12).toString()));
        txtLuongThoaThuan.setText(tblNhanVien.getValueAt(dong, 13).toString());

        lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/" + tblNhanVien.getValueAt(dong, 8))));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        lblHinhAnh = new javax.swing.JLabel();
        btnAnhSanPham = new javax.swing.JPanel();
        lblAnhDaiDien = new javax.swing.JLabel();
        lblErrHoVaTen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHoVaTen = new javax.swing.JTextField();
        lblHoVaTen = new javax.swing.JLabel();
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
        lblErrNgaySinh = new javax.swing.JLabel();
        lblChucVu = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblErrDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        lblErrLuongThoaThuan = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLuongThoaThuan = new javax.swing.JTextField();
        lblLuongThoaThuan = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        dcsNgaySinh = new com.toedter.calendar.JDateChooser();
        cboPhongBan = new javax.swing.JComboBox<>();
        cboChucVu = new javax.swing.JComboBox<>();
        lblSoDienThoai = new javax.swing.JLabel();
        lbTenSanPham10 = new javax.swing.JLabel();
        lblErrSoDienThoai = new javax.swing.JLabel();
        lblPhongBan = new javax.swing.JLabel();
        dcsNgayVaoLam = new com.toedter.calendar.JDateChooser();
        lblNgayVaoLam = new javax.swing.JLabel();
        lblErrNgayVaoLam = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        scroll = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 500));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHinhAnh.setBackground(new java.awt.Color(153, 0, 0));
        lblHinhAnh.setForeground(new java.awt.Color(255, 0, 51));
        lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/male.png"))); // NOI18N
        jPanel5.add(lblHinhAnh, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        lblAnhDaiDien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhDaiDien.setText("Ảnh đại diện");
        lblAnhDaiDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhDaiDienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnAnhSanPhamLayout = new javax.swing.GroupLayout(btnAnhSanPham);
        btnAnhSanPham.setLayout(btnAnhSanPhamLayout);
        btnAnhSanPhamLayout.setHorizontalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAnhSanPhamLayout.setVerticalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(btnAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        lblErrHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHoVaTen.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHoVaTen.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 290, 20));

        txtHoVaTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoVaTen.setBorder(null);
        jPanel5.add(txtHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 270, 40));

        lblHoVaTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHoVaTen.setText("Họ và tên:");
        jPanel5.add(lblHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 40, 140, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("_______________________________");
        jPanel5.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 290, 20));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEmail.setBorder(null);
        jPanel5.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 270, 40));

        lblErrEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrEmail.setForeground(new java.awt.Color(204, 0, 0));
        lblErrEmail.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 280, -1));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblEmail.setText("Email:");
        jPanel5.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 140, 40));

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
        lblSoCCCD.setText("Sô CCCD:");
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

        lblErrNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgaySinh.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgaySinh.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 300, -1));

        lblChucVu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblChucVu.setText("Chức vụ");
        jPanel5.add(lblChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 130, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("_______________________________");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 290, 20));

        lblErrDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrDiaChi.setForeground(new java.awt.Color(204, 0, 0));
        lblErrDiaChi.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 200, -1));

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDiaChi.setBorder(null);
        jPanel5.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 270, 40));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        jPanel5.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 140, 40));

        lblErrLuongThoaThuan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrLuongThoaThuan.setForeground(new java.awt.Color(204, 0, 0));
        lblErrLuongThoaThuan.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrLuongThoaThuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 390, 280, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("_______________________________");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 370, 290, 20));

        txtLuongThoaThuan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtLuongThoaThuan.setBorder(null);
        jPanel5.add(txtLuongThoaThuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 350, 270, 40));

        lblLuongThoaThuan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblLuongThoaThuan.setText("Lương thỏa thuận:");
        jPanel5.add(lblLuongThoaThuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 350, 140, 40));

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");
        jPanel5.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 140, 40));

        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });
        jPanel5.add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, -1, -1));

        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        rdoNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNuActionPerformed(evt);
            }
        });
        jPanel5.add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, -1, -1));

        dcsNgaySinh.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcsNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 280, 40));

        cboPhongBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Phòng tài chính", "Phòng kinh doanh", "Phòng nhân sự" }));
        cboPhongBan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cboPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 280, 40));

        cboChucVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Quản lý", "Nhân viên" }));
        cboChucVu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboChucVuActionPerformed(evt);
            }
        });
        jPanel5.add(cboChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, 280, 40));

        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");
        jPanel5.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 140, 40));

        lbTenSanPham10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbTenSanPham10.setText("Ngày sinh:");
        jPanel5.add(lbTenSanPham10, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 140, 40));

        lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 200, -1));

        lblPhongBan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblPhongBan.setText("Phòng ban:");
        jPanel5.add(lblPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 130, 40));

        dcsNgayVaoLam.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcsNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 280, 40));

        lblNgayVaoLam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNgayVaoLam.setText("Ngày vào làm:");
        jPanel5.add(lblNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 140, 40));

        lblErrNgayVaoLam.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgayVaoLam.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgayVaoLam.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 390, 320, -1));

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

        btnHuy.setBackground(new java.awt.Color(255, 121, 121));
        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/xoaTrang.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setBorder(null);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 430, 170, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scroll.setBackground(new java.awt.Color(255, 255, 255));
        scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tblNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        scroll.setViewportView(tblNhanVien);

        add(scroll, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienMouseClicked
        JFileChooser fileChooser = new JFileChooser("d:");
        //        int respone=fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new File(".\\src\\image\\Anh"));
        int respone = fileChooser.showSaveDialog(null);
        if (respone == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            String path = file.toString().split("src")[1].replace('\\', '/');
            System.out.println("path file split: " + file.toString().split("src")[1]);
            this.lblHinhAnh.setIcon(new ImageIcon(this.getClass().getResource(path)));
            System.out.println(this.lblHinhAnh.getIcon().toString());
        }
    }//GEN-LAST:event_lblAnhDaiDienMouseClicked

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void cboChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboChucVuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        setHidden(btnThem, btnXoa, btnCapNhat);
        setShow(btnLuu, btnHuy);
        int maNhaNVien = Integer.parseInt(tblNhanVien.getValueAt(tblNhanVien.getRowCount() - 1, 1).toString().split("V")[1]);
        //        String maNhaNVien=tblNhanVien.getValueAt(tblNhanVien.getRowCount()-1, 1).toString().split("V")[1];
        //        System.out.println(maNhaNVien);
        this.txtMaNhanVien.setText("NV" + (maNhaNVien + 1));
        this.txtHoVaTen.setText("");
        this.txtSoCCCD.setText("");
        this.txtEmail.setText("");
        this.txtSoDienThoai.setText("");
        this.txtDiaChi.setText("");
        this.dcsNgaySinh.setDate(new Date());
        this.rdoNam.setSelected(true);
        this.cboPhongBan.setSelectedIndex(0);
        this.cboChucVu.setSelectedIndex(0);
        this.dcsNgayVaoLam.setDate(new Date());
        this.txtLuongThoaThuan.setText("");
        lblHinhAnh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/male.png")));
        setEnableForInput(true);
        isThem = true;
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int rowSelected = tblNhanVien.getSelectedRow();
        if (rowSelected != -1) {
            //                int coXacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa nhân viên", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa nhân viên", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (daoNhanVien.xoaMotNhanVienTheoMa(tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 1).toString())) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        taiDuLieuLenBang();
                    } catch (ParseException ex) {
                        Logger.getLogger(CapNhatNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Hệ thống gặp lỗi ^.^. Bạn vui lòng khởi dộng lại chương trình!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        setHidden(btnThem, btnXoa, btnCapNhat);
        setShow(btnLuu, btnHuy);
        setEnableForInput(true);
        isCapNhat = true;
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            String them = isThem ? "Thêm" : "Không thêm";
            String capNhat = isCapNhat ? "Cập nhật" : "Không cập nhật";
            System.out.println("Ban đang " + them + " Và " + capNhat);
            System.out.println(cboChucVu.getSelectedItem());
            System.out.println(cboPhongBan.getSelectedItem());
            if (kiemTraNhap()) {
                PhongBan_DAO dao = new PhongBan_DAO();
                PhongBan pb = dao.layMotPhongBanTheoTen(cboPhongBan.getSelectedItem().toString());
                System.out.println(pb.getMaPhongBan());
                String tienLuong = txtLuongThoaThuan.getText();
                if (txtLuongThoaThuan.getText().contains(",")) {
                    tienLuong = txtLuongThoaThuan.getText().replaceAll(",", "");
                }
                System.out.println(tienLuong);
                nhanvienEntity = new NhanVien(txtMaNhanVien.getText(), txtHoVaTen.getText(), dcsNgaySinh.getDate(),
                    txtSoCCCD.getText(), txtSoDienThoai.getText(), txtEmail.getText(), "111111", cboChucVu.getSelectedItem().toString(),
                    dcsNgayVaoLam.getDate(), Double.parseDouble(tienLuong), rdoNam.isSelected(), lblHinhAnh.getIcon().toString().split("Anh/")[1], txtDiaChi.getText(), pb);
                nhanvienEntity.toString();
                if (isThem) {
                    if (this.daoNhanVien.themMotNhanVien(nhanvienEntity)) {
                        taiDuLieuLenBang();
                        JOptionPane.showMessageDialog(this, "Thêm thành công");
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                        isThem = false;
                        isCapNhat = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi vui thêm nhân viên lại lòng thử lại sau");
                        isThem = false;
                        isCapNhat = false;
                    }
                } else {
                    if (this.daoNhanVien.suaThongTinMotNhanVien(nhanvienEntity)) {
                        taiDuLieuLenBang();
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                        isThem = false;
                        isCapNhat = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi vui lòng cập nhật nhân viên thử lại sau");
                        isThem = false;
                        isCapNhat = false;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Hệ thống đang bị lỗi, quý khách làm phiền thoát chương tình");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        setHidden(btnLuu, btnHuy);
        setShow(btnThem, btnXoa, btnCapNhat);
        tblNhanVien.setRowSelectionInterval(0, 0);
        setEnableForInput(false);
        try {
            hienThiDuLieuLenTxt(0);
        } catch (ParseException ex) {
            Logger.getLogger(CapNhatNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        isThem = false;
        isCapNhat = false;
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        try {
            setHidden(btnLuu, btnHuy);
            setShow(btnThem, btnXoa, btnCapNhat);
            hienThiDuLieuLenTxt(tblNhanVien.getSelectedRow());
            setEnableForInput(false);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Hệ thống đang bị lỗi, quý khách làm phiền thoát chương tình");

        }
    }//GEN-LAST:event_tblNhanVienMouseClicked

    public void setHidden(JButton... btnHidden) {
        for (JButton jButton : btnHidden) {
            jButton.setEnabled(false);
        }
    }

    public void setEnableForInput(boolean kq) {
        this.txtHoVaTen.setEditable(kq);
        this.txtSoCCCD.setEditable(kq);
        this.txtEmail.setEditable(kq);
        this.txtSoDienThoai.setEditable(kq);
        this.txtDiaChi.setEditable(kq);
        this.dcsNgaySinh.setEnabled(kq);
        this.rdoNam.setEnabled(kq);
        this.rdoNu.setEnabled(kq);
        this.cboPhongBan.setEnabled(kq);
        this.cboChucVu.setEnabled(kq);
        this.dcsNgayVaoLam.setEnabled(kq);
        this.txtLuongThoaThuan.setEditable(kq);
        this.lblAnhDaiDien.setEnabled(kq);
    }

    public void setShow(JButton... btnHidden) {
        for (JButton jButton : btnHidden) {
            jButton.setEnabled(true);
        }
    }    public boolean kiemTraNhap() throws ParseException {
        boolean flag = true;
        if (this.txtHoVaTen.getText().equals("")) {
            this.lblErrHoVaTen.setText("Bắt buộc nhập");
            flag = false;
        } else if (!this.txtHoVaTen.getText().matches("^([A-Z]{1}[a-zvxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)"
                + "((\\s{1}[A-Z][{1}a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            this.lblErrHoVaTen.setText("Họ tên không hợp lệ");
            flag = false;
        } else {
            this.lblErrHoVaTen.setText("");
        }
        if (txtSoCCCD.getText().equals("")) {
            lblErrSoCCCD.setText("Bắt buộc nhập");
            flag = false;
        } else if (!txtSoCCCD.getText().matches("^[0-9]{12}$")) {
            lblErrSoCCCD.setText("Số CCCD không hợp lệ");
            flag = false;
        } else {
            lblErrSoCCCD.setText("");
        }
        if (txtEmail.getText().equals("")) {
            lblErrEmail.setText("");
        } else if (!txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            lblErrEmail.setText("Email không hợp lệ");
            flag = false;
        } else {
            lblErrEmail.setText("");
        }

        if (txtSoDienThoai.getText().equals("")) {
            lblErrSoDienThoai.setText("Bắt buộc nhập");
            flag = false;
        } else if (!txtSoDienThoai.getText().matches("^[0][0-9]{9}$")) {
            lblErrSoDienThoai.setText("Số điện thoại không hợp lệ");
            flag = false;
        } else {
            lblErrSoDienThoai.setText("");
        }

        if (new Date().before(dcsNgaySinh.getDate())) {
            lblErrNgaySinh.setText("ngày sinh không hợp lệ");
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }
        System.out.println(flag);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long khoangCach2Ngay = sdf.parse(sdf.format(dcsNgayVaoLam.getDate())).getTime() - sdf.parse(sdf.format(dcsNgaySinh.getDate())).getTime();
        long getYearDiff = TimeUnit.MILLISECONDS.toDays(khoangCach2Ngay) / 365;
        if (new Date().before(dcsNgayVaoLam.getDate())) {
            lblErrNgayVaoLam.setText("ngày vào làm không hợp lệ");
            flag = false;
        } else if (getYearDiff < 18) {
            lblErrNgayVaoLam.setText("Nhân viên chưa đủ tuổi để làm việc");
            flag = false;
        } else {
            lblErrNgayVaoLam.setText("");
        }
//        this.txtLuongThoaThuan.getText().replaceAll(",", "");
        if (this.txtLuongThoaThuan.getText().replaceAll(",", "").equals("")) {
            this.lblErrLuongThoaThuan.setText("Bắt buộc nhập");
            flag = false;
        } else if (!this.txtLuongThoaThuan.getText().replaceAll(",", "").matches("^[1-9][0-9]*$")) {
            this.lblErrLuongThoaThuan.setText("Số tiền nhập không hợp lệ");
            flag = false;
        } else {
            this.lblErrLuongThoaThuan.setText("");
        }
        return flag;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboChucVu;
    private javax.swing.JComboBox<String> cboPhongBan;
    private com.toedter.calendar.JDateChooser dcsNgaySinh;
    private com.toedter.calendar.JDateChooser dcsNgayVaoLam;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lbTenSanPham10;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblChucVu;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrDiaChi;
    private javax.swing.JLabel lblErrEmail;
    private javax.swing.JLabel lblErrHoVaTen;
    private javax.swing.JLabel lblErrLuongThoaThuan;
    private javax.swing.JLabel lblErrNgaySinh;
    private javax.swing.JLabel lblErrNgayVaoLam;
    private javax.swing.JLabel lblErrSoCCCD;
    private javax.swing.JLabel lblErrSoDienThoai;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHinhAnh;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblLuongThoaThuan;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblNgayVaoLam;
    private javax.swing.JLabel lblPhongBan;
    private javax.swing.JLabel lblSoCCCD;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoVaTen;
    private javax.swing.JTextField txtLuongThoaThuan;
    private javax.swing.JTextField txtMaNhanVien;
    private javax.swing.JTextField txtSoCCCD;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables
}
