/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongNhan_DAO;
import DAO.NhanVien_DAO;
import Entity.CongNhan;
import Entity.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author December
 */
public class QuanLyThongTinCaNhan extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form NhanVienView
     */
    private CongNhan_DAO congNhan_DAO;
    private NhanVien_DAO nhanVien_DAO;
    private boolean isNhanVien;
    private String username;

    private String stErrKhongDeTrong;
    private String stErrSoLuong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stTren;
    private String stSanPham;
    private String stKhongTimThayFile;
    private String stKhongDocDuocFile;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stChonMauSacChoSanPham;
    private String stErrHoTen;
    private String stErrSoCCCD;
    private String stErrEmail;
    private String stErrSdt;
    private String stErrNgaySinh;
    private String stErrNgayVaoLam;
    private String stErrNhanVienKhongDuTuoi;
    private String stErrTienKhongHopLe;
    private String stSoTienLonHonKhong;
    private String stErrBanChuaDu18Tuoi;
    private String stErrMatKhau;

    public QuanLyThongTinCaNhan(String fileName,String username) throws IOException {
        initComponents();
        excute();
        this.username = username;
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        congNhan_DAO = new CongNhan_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        String loai = username.substring(0, 2);
        if (loai.equals("NV")) {
            isNhanVien = true;
        } else {
            isNhanVien = false;
        }
        dtcNgayVaoLam.setEnabled(false);
        btnLuu.addActionListener(this);
        // làm trống các label error
        lblErrEmail.setText("");
        lblErrHoTen.setText("");
        lblErrDiaChi.setText("");
        lblErrNgaySinh.setText("");
        lblErrSoDienThoai.setText("");;
        lblErrNgayVaoLam.setText("");
        lblErrMatKhau.setText("");
        taiDuLieuLenTrang();
        caiDatNgonNguChoView(fileName);

    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);

        btnLuu.setText(prop.getProperty("btnLuu"));

        lblTenDangNhap.setText(prop.getProperty("qlttcn_tenDangNhap"));
        lblMatKhau.setText(prop.getProperty("qlttcn_matKhau"));
        lblHoTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblDiaChi.setText(prop.getProperty("diaChi"));
        lblNgaySinh.setText(prop.getProperty("ngaySinh"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblNgayVaoLam.setText(prop.getProperty("ngayVaoLam"));
        btnAnhDaiDien.setText(prop.getProperty("anhDaiDien"));
        lblHoSoCuaToi.setText(prop.getProperty("qlttcn_hoSo"));
        lblTitle.setText(prop.getProperty("qlttcn_quanlythongtindebaomat"));
        
        rdoNam.setText(prop.getProperty("nam"));
        rdoNu.setText(prop.getProperty("nu"));
        
        stErrBanChuaDu18Tuoi=prop.getProperty("qlttcn_khongDuTuoi");
        stErrMatKhau=prop.getProperty("qlttcn_errMatKhau");
        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        stXoaThatBai = prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("themThanhCong");
        stThemThatBai = prop.getProperty("themThatBai");
        stTren = prop.getProperty("tren");
        stSanPham = prop.getProperty("sp_SanPham");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stChonMauSacChoSanPham = prop.getProperty("sp_chonMauSacChoSanPham");
        stErrSoLuong = prop.getProperty("sp_lblErrSoLuong");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        stErrHoTen = prop.getProperty("hoTenKhongHopLe");
        stErrSoCCCD = prop.getProperty("soCCCDKhongHopLe");
        stErrEmail = prop.getProperty("emailKhongHopLe");
        stErrSdt = prop.getProperty("sdtKhongHopLe");
        stErrNgaySinh = prop.getProperty("ngaySinhKhongHopLe");
        stErrNgayVaoLam = prop.getProperty("ngayVaoLamKhongHopLe");
        stErrNhanVienKhongDuTuoi = prop.getProperty("nhanVienChuaDuTuoi");
        stErrTienKhongHopLe = prop.getProperty("soTienKhongHople");
        stSoTienLonHonKhong = prop.getProperty("soTienLonHonKhong");
    }



    public void taiDuLieuLenTrang() {
        if (isNhanVien) {
            NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(username);
            lblTenDangNhapOutput.setText(nhanVien.getMaNhanVien());
            lblCCCDOutput.setText(nhanVien.getMaCCCD());
            txtHoTen.setText(nhanVien.getHoTen());
            txtEmail.setText(nhanVien.getEmail());
            txtSoDienThoai.setText(nhanVien.getSoDienThoai());
            txtDiaChi.setText(nhanVien.getDiaChi());
            dtcNgaySinh.setDate(nhanVien.getNgaySinh());
            dtcNgayVaoLam.setDate(nhanVien.getNgayVaoLam());
            if (nhanVien.isGioiTinh()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtPassword.setText(nhanVien.getMatKhau());
            lblAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/" + nhanVien.getAnhDaiDien())));
        } else {
            CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(username);
            lblTenDangNhapOutput.setText(congNhan.getMaCongNhan());
            lblCCCDOutput.setText(congNhan.getMaCCCD());
            txtHoTen.setText(congNhan.getHoTen());
            txtEmail.setText(congNhan.getEmail());
            txtSoDienThoai.setText(congNhan.getSoDienThoai());
            txtDiaChi.setText(congNhan.getDiaChi());
            dtcNgaySinh.setDate(congNhan.getNgaySinh());
            dtcNgayVaoLam.setDate(congNhan.getNgayVaoLam());
            if (congNhan.isGioiTinh()) {
                rdoNam.setSelected(true);
            } else {
                rdoNu.setSelected(true);
            }
            txtPassword.setText(congNhan.getMatKhau());
            lblAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/" + congNhan.getAnhDaiDien())));
        }
    }

    public void excute() {

        // custom table
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdoNam);
        btnGroup.add(rdoNu);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblHoSoCuaToi = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblTenDangNhap = new javax.swing.JLabel();
        lblTenDangNhapOutput = new javax.swing.JLabel();
        lblSoCCCD = new javax.swing.JLabel();
        lblCCCDOutput = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblNgaySinh = new javax.swing.JLabel();
        lblErrSoDienThoai = new javax.swing.JLabel();
        dtcNgaySinh = new com.toedter.calendar.JDateChooser();
        lblErrNgaySinh = new javax.swing.JLabel();
        lblNgayVaoLam = new javax.swing.JLabel();
        dtcNgayVaoLam = new com.toedter.calendar.JDateChooser();
        lblErrNgayVaoLam = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        lblGioiTinh = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtDiaChi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        lblErrDiaChi = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblHoTen = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblErrHoTen = new javax.swing.JLabel();
        lblAnhDaiDien = new javax.swing.JLabel();
        btnAnhSanPham = new javax.swing.JPanel();
        btnAnhDaiDien = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        lbPassword02 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        disable = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        lblErrEmail = new javax.swing.JLabel();
        lblErrMatKhau = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblHoSoCuaToi.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        lblHoSoCuaToi.setText("Hồ Sơ Của Tôi");
        add(lblHoSoCuaToi, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 460, 50));

        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTitle.setText("Quản lý thông tin hồ sơ để bảo mật tài khoản");
        add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 460, 50));
        add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1920, 20));

        lblTenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTenDangNhap.setText("Tên đăng nhập:");
        add(lblTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 140, 40));

        lblTenDangNhapOutput.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTenDangNhapOutput.setText("NV0001");
        add(lblTenDangNhapOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 190, 140, 40));

        lblSoCCCD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoCCCD.setText("Số CCCD:");
        add(lblSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, 140, 40));

        lblCCCDOutput.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblCCCDOutput.setText("012345678911");
        add(lblCCCDOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 260, 190, 40));

        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");
        add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 140, 40));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________________");
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 350, 240, 20));

        txtSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoDienThoai.setBorder(null);
        add(txtSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 330, 210, 40));

        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNgaySinh.setText("Ngày sinh:");
        add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 400, 140, 40));

        lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
        add(lblErrSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 200, -1));

        dtcNgaySinh.setDateFormatString("yyyy-MM-dd");
        add(dtcNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 400, 220, 40));

        lblErrNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgaySinh.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgaySinh.setText("đây là dòng thông báo lỗi");
        add(lblErrNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 440, 200, -1));

        lblNgayVaoLam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNgayVaoLam.setText("Ngày vào làm:");
        add(lblNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 480, 140, 40));

        dtcNgayVaoLam.setDateFormatString("yyyy-MM-dd");
        add(dtcNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 220, 40));

        lblErrNgayVaoLam.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgayVaoLam.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgayVaoLam.setText("đây là dòng thông báo lỗi");
        add(lblErrNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 520, 200, -1));

        btnLuu.setBackground(new java.awt.Color(46, 204, 113));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setBorder(null);
        add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 640, 170, 40));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 400, 140, 40));

        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(rdoNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 410, -1, -1));

        rdoNu.setText("Nữ");
        rdoNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add(rdoNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 410, -1, -1));

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDiaChi.setBorder(null);
        add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 270, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("_______________________________");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 360, 290, 20));

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");
        add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 340, 140, 40));

        lblErrDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrDiaChi.setForeground(new java.awt.Color(204, 0, 0));
        lblErrDiaChi.setText("đây là dòng thông báo lỗi");
        add(lblErrDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 280, 20));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblEmail.setText("Email:");
        add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 270, 140, 40));

        txtEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtEmail.setBorder(null);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 270, 270, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 290, 290, 20));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoTen.setBorder(null);
        add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 270, 40));

        lblHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHoTen.setText("Họ và tên:");
        add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 190, 110, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("_______________________________");
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 220, 290, 20));

        lblErrHoTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHoTen.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHoTen.setText("đây là dòng thông báo lỗi");
        add(lblErrHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 240, 280, -1));

        lblAnhDaiDien.setBackground(new java.awt.Color(153, 0, 0));
        lblAnhDaiDien.setForeground(new java.awt.Color(255, 0, 51));
        lblAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/male.png"))); // NOI18N
        add(lblAnhDaiDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 200, -1, -1));

        btnAnhDaiDien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAnhDaiDien.setText("Ảnh đại diện");
        btnAnhDaiDien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAnhDaiDienMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnAnhSanPhamLayout = new javax.swing.GroupLayout(btnAnhSanPham);
        btnAnhSanPham.setLayout(btnAnhSanPhamLayout);
        btnAnhSanPhamLayout.setHorizontalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAnhSanPhamLayout.setVerticalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAnhDaiDien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(btnAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 340, -1, -1));

        lblMatKhau.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMatKhau.setText("Mật khẩu");
        add(lblMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 480, 140, 40));

        lbPassword02.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lbPassword02.setText("_______________________________");
        add(lbPassword02, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 280, -1));

        txtPassword.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtPassword.setBorder(null);
        add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 460, 270, 40));

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

        lblErrEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrEmail.setForeground(new java.awt.Color(204, 0, 0));
        lblErrEmail.setText("đây là dòng thông báo lỗi");
        add(lblErrEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 310, 280, -1));

        lblErrMatKhau.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrMatKhau.setForeground(new java.awt.Color(204, 0, 0));
        lblErrMatKhau.setText("đây là dòng thông báo lỗi");
        add(lblErrMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 510, 280, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnhDaiDienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAnhDaiDienMouseClicked
        JFileChooser fileChooser = new JFileChooser("d:");
        //        int respone=fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new File(".\\src\\image\\Anh"));
        int respone = fileChooser.showSaveDialog(null);
        if (respone == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            String path = file.toString().split("src")[1].replace('\\', '/');
            System.out.println("path file split: " + file.toString().split("src")[1]);
            this.lblAnhDaiDien.setIcon(new ImageIcon(this.getClass().getResource(path)));
            System.out.println(this.lblAnhDaiDien.getIcon().toString());
        }
    }//GEN-LAST:event_btnAnhDaiDienMouseClicked

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        this.txtPassword.setEchoChar((char) 8226);
        this.disable.setVisible(true);
        this.disable.setEnabled(true);
        this.show.setVisible(false);
        this.show.setEnabled(false);
    }//GEN-LAST:event_showMouseClicked

    private void disableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disableMouseClicked
        this.txtPassword.setEchoChar((char) 0);
        this.disable.setVisible(false);
        this.disable.setEnabled(false);
        this.show.setVisible(true);
        this.show.setEnabled(true);
    }//GEN-LAST:event_disableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAnhDaiDien;
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnLuu;
    private javax.swing.JLabel disable;
    private com.toedter.calendar.JDateChooser dtcNgaySinh;
    private com.toedter.calendar.JDateChooser dtcNgayVaoLam;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbPassword02;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblCCCDOutput;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrDiaChi;
    private javax.swing.JLabel lblErrEmail;
    private javax.swing.JLabel lblErrHoTen;
    private javax.swing.JLabel lblErrMatKhau;
    private javax.swing.JLabel lblErrNgaySinh;
    private javax.swing.JLabel lblErrNgayVaoLam;
    private javax.swing.JLabel lblErrSoDienThoai;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoSoCuaToi;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNgayVaoLam;
    private javax.swing.JLabel lblSoCCCD;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTenDangNhap;
    private javax.swing.JLabel lblTenDangNhapOutput;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JLabel show;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLuu)) {
            boolean isValid = validateForm();
            if (!isValid) {
                return;
            }
            if (isNhanVien) {
                NhanVien nhanVienOld = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(username);
                boolean coSuaDuoc = nhanVien_DAO.suaThongTinMotNhanVien(new NhanVien(nhanVienOld.getMaNhanVien(),
                        txtHoTen.getText(), dtcNgaySinh.getDate(), lblCCCDOutput.getText(), txtSoDienThoai.getText(),
                        txtEmail.getText(), new String(txtPassword.getPassword()), nhanVienOld.getChucVu(), nhanVienOld.getNgayVaoLam(),
                        nhanVienOld.getLuongThoaThuan(), rdoNam.isSelected() ? true : false,
                        lblAnhDaiDien.getIcon().toString().split("Anh/")[1], txtDiaChi.getText(), nhanVienOld.getPhongBan()));
                if (coSuaDuoc) {
                    JOptionPane.showMessageDialog(null,stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    taiDuLieuLenTrang();
                } else {
                    JOptionPane.showMessageDialog(null,stCapNhatThatBai,stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                CongNhan congNhanOld = congNhan_DAO.layMotCongNhanTheoMa(username);
                boolean coSuaDuoc = congNhan_DAO.capNhatMotCongNhan(new CongNhan(congNhanOld.getMaCongNhan(),
                        txtHoTen.getText(), dtcNgaySinh.getDate(), lblCCCDOutput.getText(), txtSoDienThoai.getText(),
                        txtEmail.getText(), new String(txtPassword.getPassword()), congNhanOld.getNgayVaoLam(), rdoNam.isSelected() ? true : false,
                        lblAnhDaiDien.getIcon().toString().split("Anh/")[1], txtDiaChi.getText(), congNhanOld.getToNhom()));
                if (coSuaDuoc) {
                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    taiDuLieuLenTrang();
                } else {
                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong,stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }

    public boolean validateForm() {
        Boolean flag = true;
        String hoTen = txtHoTen.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        String matKhau = new String(txtPassword.getPassword());
        Date ngaySinh = dtcNgaySinh.getDate();

        if (hoTen.equals("")) {
            lblErrHoTen.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!hoTen.matches("^([A-ZĐÂÁƯ]{1}[a-zvxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)"
                + "((\\s{1}[A-ZĐÂÁƯ][{1}a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            lblErrHoTen.setText(stErrHoTen);
            flag = false;
        } else {
            lblErrHoTen.setText("");
        }
        if (email.equals("")) {
            lblErrEmail.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            lblErrEmail.setText(stErrEmail);
            flag = false;
        } else {
            lblErrEmail.setText("");
        }
        if (soDienThoai.equals("")) {
            lblErrSoDienThoai.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!soDienThoai.matches("^\\+84[1-9][0-9]{8}$")) {
            lblErrSoDienThoai.setText(stErrSdt);
            flag = false;
        } else {
            lblErrSoDienThoai.setText("");
        }
        if (diaChi.equals("")) {
            lblErrDiaChi.setText(stErrKhongDeTrong);
            flag = false;
        } else {
            lblErrDiaChi.setText("");
        }
        if (!ngaySinh.before(new Date())) {
            lblErrNgaySinh.setText(stErrNgaySinh);
            flag = false;
        } else if (calculateAgeWithJava7(ngaySinh, new Date()) < 18) {
            lblErrNgaySinh.setText(stErrBanChuaDu18Tuoi);
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }

        if (matKhau.equals("")) {
            lblErrMatKhau.setText(stErrKhongDeTrong);
            flag = false;
        } else if (matKhau.length() < 6) {
            lblErrMatKhau.setText(stErrMatKhau);
            flag = false;
        } else {
            lblErrMatKhau.setText("");
        }
        return flag;
    }

    public int calculateAgeWithJava7(Date birthDate, Date currentDate) {
        // validate inputs ...                                                                               
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }
}
