/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.NhanVien_DAO;
import DAO.PhongBan_DAO;
import Entity.NhanVien;
import Entity.PhongBan;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    private DateFormat df = new SimpleDateFormat("yyy-MM-dd");
    private DecimalFormat dcf = new DecimalFormat("###.00");
    private Date date;
    private boolean isThem = false;
    private boolean isCapNhat = false;

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

    public CapNhatNhanVienView(String fileName) throws Exception {
        initComponents();
        caiDatNgonNguChoView(fileName);
        excute();
        taiDuLieuLenBang();
        this.txtMaNhanVien.setEditable(false);

    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        btnThemNhieu.setText(prop.getProperty("btnThemNhieu"));
        btnThem.setText(prop.getProperty("btnThem"));
        btnXoa.setText(prop.getProperty("btnXoa"));
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));

        lblMaNhanVien.setText(prop.getProperty("maNhanVien"));
        lblHoVaTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblDiaChi.setText(prop.getProperty("diaChi"));
        lblNgaySinh.setText(prop.getProperty("ngaySinh"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblPhongBan.setText(prop.getProperty("phongBan"));
        lblChucVu.setText(prop.getProperty("chucVu"));
        lblNgayVaoLam.setText(prop.getProperty("ngayVaoLam"));
        lblLuongThoaThuan.setText(prop.getProperty("luongThoaThuan"));
        lblAnhDaiDien.setText(prop.getProperty("anhDaiDien"));
        rdoNam.setText(prop.getProperty("nam"));
        rdoNu.setText(prop.getProperty("nu"));

        ChangeName(tblNhanVien, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblNhanVien, 1, lblMaNhanVien.getText());
        ChangeName(tblNhanVien, 2, lblHoVaTen.getText());
        ChangeName(tblNhanVien, 3, lblSoCCCD.getText());
        ChangeName(tblNhanVien, 4, lblGioiTinh.getText());
        ChangeName(tblNhanVien, 5, lblNgaySinh.getText());
        ChangeName(tblNhanVien, 6, lblSoDienThoai.getText());
        ChangeName(tblNhanVien, 7, lblDiaChi.getText());
        ChangeName(tblNhanVien, 8, lblAnhDaiDien.getText());
        ChangeName(tblNhanVien, 9, lblEmail.getText());
        ChangeName(tblNhanVien, 10, lblPhongBan.getText());
        ChangeName(tblNhanVien, 11, lblChucVu.getText());
        ChangeName(tblNhanVien, 12, lblNgayVaoLam.getText());
        ChangeName(tblNhanVien, 13, lblLuongThoaThuan.getText());

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

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
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

        setEnableForInput(false);
        setInit();
    }

    public void setInit() {
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
        if (phongBan.size() > 0) {
            for (PhongBan pb : phongBan) {
                cboPhongBan.addItem(pb.getTenPhongBan());
            }
            ArrayList<NhanVien> danhSachNhanVien = daoNhanVien.layDanhSachNhanVien();
            for (NhanVien nv : danhSachNhanVien) {
                String data[] = {(model.getRowCount() + 1) + "", nv.getMaNhanVien(), nv.getHoTen(), nv.getMaCCCD(), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getNgaySinh().toString(),
                    nv.getSoDienThoai(), nv.getDiaChi(), nv.getAnhDaiDien(), nv.getEmail(), nv.getPhongBan().getTenPhongBan(),
                    nv.getChucVu(), nv.getNgayVaoLam().toString(), dcf.format(nv.getLuongThoaThuan()).replaceAll("\\,", ".")};
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
        txtLuongThoaThuan.setText(tblNhanVien.getValueAt(dong, 13).toString().replaceAll("\\,", "."));

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
        lblNgaySinh = new javax.swing.JLabel();
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
        btnThemNhieu = new javax.swing.JButton();
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
        jPanel5.add(lblErrHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 290, -1));

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
        jPanel5.add(lblErrSoCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 290, -1));

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
        jPanel5.add(lblErrDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 280, -1));

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
        dcsNgaySinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dcsNgaySinhMouseExited(evt);
            }
        });
        dcsNgaySinh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dcsNgaySinhPropertyChange(evt);
            }
        });
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

        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNgaySinh.setText("Ngày sinh:");
        jPanel5.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 140, 40));

        lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 300, -1));

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
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 430, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 430, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 430, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 430, 160, 40));

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
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 430, 170, 40));

        btnThemNhieu.setBackground(new java.awt.Color(255, 234, 167));
        btnThemNhieu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThemNhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThemNhieu.setText("Thêm nhiều");
        btnThemNhieu.setBorder(null);
        btnThemNhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhieuActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemNhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 170, 40));

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
    public void setEditTextDateChooser() {
        JTextFieldDateEditor ngaySinh = (JTextFieldDateEditor) dcsNgaySinh.getDateEditor();
        JTextFieldDateEditor ngayVaoLam = (JTextFieldDateEditor) dcsNgayVaoLam.getDateEditor();
        ngaySinh.setEnabled(false);
        ngayVaoLam.setEnabled(false);
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        setHidden(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
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
        setEditTextDateChooser();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int rowSelected = tblNhanVien.getSelectedRow();
        if (rowSelected != -1) {
            if (JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (daoNhanVien.xoaMotNhanVienTheoMa(tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 1).toString())) {
                    JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    try {
                        taiDuLieuLenBang();
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro (T_T)(T_T) ", stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        setHidden(btnThem, btnXoa, btnCapNhat);
        setShow(btnLuu, btnHuy);
        setEnableForInput(true);
        txtLuongThoaThuan.setText(txtLuongThoaThuan.getText().replaceAll(",", ""));
        isCapNhat = true;
        setEditTextDateChooser();
        isThem = false;
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            if (kiemTraNhap()) {
                PhongBan_DAO dao = new PhongBan_DAO();
                PhongBan pb = dao.layMotPhongBanTheoTen(cboPhongBan.getSelectedItem().toString());
                System.out.println(pb.getMaPhongBan());
                String tienLuong = txtLuongThoaThuan.getText();
                if (txtLuongThoaThuan.getText().contains(",")) {
                    tienLuong = txtLuongThoaThuan.getText().replaceAll(",", "");
                }
                nhanvienEntity = new NhanVien(txtMaNhanVien.getText(), txtHoVaTen.getText(), dcsNgaySinh.getDate(),
                        txtSoCCCD.getText(), txtSoDienThoai.getText(), txtEmail.getText(), "111111", cboChucVu.getSelectedItem().toString(),
                        dcsNgayVaoLam.getDate(), Double.parseDouble(tienLuong), rdoNam.isSelected(), lblHinhAnh.getIcon().toString().split("Anh/")[1], txtDiaChi.getText(), pb);
                nhanvienEntity.toString();
                if (isThem) {
                    if (this.daoNhanVien.themMotNhanVien(nhanvienEntity)) {
                        taiDuLieuLenBang();
                        JOptionPane.showMessageDialog(this, stThemThanhCong);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                        isThem = false;
                    } else {
                        JOptionPane.showMessageDialog(null, stThemThatBai);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                    }
                } else {
                    if (this.daoNhanVien.suaThongTinMotNhanVien(nhanvienEntity)) {
                        taiDuLieuLenBang();
                        JOptionPane.showMessageDialog(this, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnCapNhat, btnXoa);
                        setEnableForInput(false);

                    } else {
                        JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setHidden(btnLuu, btnHuy);
                        setShow(btnThem, btnThemNhieu, btnXoa, btnCapNhat);
                        setEnableForInput(false);
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO,please Resset (T_T)(T_T)");
        }

    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        setHidden(btnLuu, btnHuy);
        setShow(btnXoa, btnCapNhat, btnThemNhieu, btnThem);
        tblNhanVien.setRowSelectionInterval(0, 0);
        setEnableForInput(false);
        try {
            hienThiDuLieuLenTxt(0);
        } catch (ParseException ex) {
            Logger.getLogger(CapNhatNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        isThem = false;
        isCapNhat = false;
        setInit();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        lblErrHoVaTen.setText("");
        lblErrSoCCCD.setText("");
        lblErrSoDienThoai.setText("");
        lblErrEmail.setText("");
        lblErrNgaySinh.setText("");
        lblErrNgayVaoLam.setText("");
        lblErrLuongThoaThuan.setText("");
        lblErrDiaChi.setText("");
        try {
            setHidden(btnLuu, btnHuy);
            setShow(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
            hienThiDuLieuLenTxt(tblNhanVien.getSelectedRow());
            setEnableForInput(false);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "ERRO,please Resset (T_T)(T_T)");

        }
        setEditTextDateChooser();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnThemNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhieuActionPerformed
        ArrayList<NhanVien> nhanVienList = new ArrayList<>();
        File file = new File("./excelData");
        JFileChooser openFileChooser = new JFileChooser(file.getAbsolutePath());
        openFileChooser.setDialogTitle("Open file");
        openFileChooser.removeChoosableFileFilter(openFileChooser.getFileFilter());
        FileFilter filter = new FileNameExtensionFilter("Excel FIle(.xlsx)", "xlsx");
        openFileChooser.setFileFilter(filter);
        int count = 0;
        int total;
        if (openFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File inpuFile = openFileChooser.getSelectedFile();
            try {
                FileInputStream in = new FileInputStream(inpuFile);
                try {
                    XSSFWorkbook importFile = new XSSFWorkbook(in);
                    XSSFSheet sheeth = importFile.getSheetAt(0);
                    Iterator<Row> rowIterator = sheeth.iterator();
                    Row row;
                    try {
                        for (int i = 1; i <= sheeth.getLastRowNum(); i++) {
                            try {
                                row = sheeth.getRow(i);
                                String hoTen = row.getCell(0).getStringCellValue();
                                String soCCCD = row.getCell(1).getStringCellValue();
                                String email = row.getCell(2).getStringCellValue();
                                String sdt = row.getCell(3).getStringCellValue();
                                String diaChi = row.getCell(4).getStringCellValue();
                                String ngaySinh = row.getCell(5).getStringCellValue();
                                String gioiTinh = row.getCell(6).getStringCellValue();
                                String phongBan = row.getCell(7).getStringCellValue();
                                String chucVu = row.getCell(8).getStringCellValue();
                                String ngayVaoLam = row.getCell(9).getStringCellValue();
                                String luongThoaThuan = row.getCell(10).getStringCellValue();
                                String maNhanVien = "NV100001";
                                daoNhanVien = new NhanVien_DAO();
                                if (daoNhanVien.layDanhSachNhanVien().size() > 0) {
                                    maNhanVien = "NV" + (Integer.parseInt(daoNhanVien.layDanhSachNhanVien().get(daoNhanVien.layDanhSachNhanVien().size() - 1).getMaNhanVien().split("V")[1]) + 1);
                                }
                                Date ngaySinh1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinh);
                                Date ngayVaoLam1 = new SimpleDateFormat("yyyy-MM-dd").parse(ngayVaoLam);
                                PhongBan_DAO phongBanDao = new PhongBan_DAO();
                                PhongBan phongBan1 = phongBanDao.layMotPhongBanTheoTen(phongBan);
                                NhanVien nhanVienThem = new NhanVien(maNhanVien, hoTen, ngaySinh1, soCCCD, sdt, email,
                                        "111111", chucVu, ngayVaoLam1, Double.parseDouble(luongThoaThuan), "Nam".equals(gioiTinh) ? true : false, "male.png", diaChi, phongBan1);
                                if (nhanVienThem != null) {
                                    if (daoNhanVien.themMotNhanVien(nhanVienThem)) {
                                        count++;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("erro");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Lỗi get data ");
                    } finally {
                        JOptionPane.showMessageDialog(null, stThemThanhCong + " " + count + " " + stTren + " " + sheeth.getLastRowNum());
                        taiDuLieuLenBang();
                    }
//                    JOptionPane.showMessageDialog(null, stThemThanhCong + count + stTren + sheeth.getLastRowNum());
//                    taiDuLieuLenBang();
                } catch (IOException ex) {
                    Logger.getLogger(CapNhatNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(CapNhatNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CapNhatNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnThemNhieuActionPerformed

    private void dcsNgaySinhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dcsNgaySinhPropertyChange
    }//GEN-LAST:event_dcsNgaySinhPropertyChange

    private void dcsNgaySinhMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dcsNgaySinhMouseExited
    }//GEN-LAST:event_dcsNgaySinhMouseExited

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
    }

    public boolean kiemTraNhap() throws ParseException {
        boolean flag = true;
        if (this.txtHoVaTen.getText().equals("")) {
            this.lblErrHoVaTen.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!this.txtHoVaTen.getText().matches("^([A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổ"
                + "ẵẻỡơôưăêâđ]+)((\\s[A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            this.lblErrHoVaTen.setText(stErrHoTen);
            flag = false;
        } else {
            this.lblErrHoVaTen.setText("");
        }
        if (txtSoCCCD.getText().equals("")) {
            lblErrSoCCCD.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!txtSoCCCD.getText().matches("^[0-9]{12}$")) {
            lblErrSoCCCD.setText(stErrSoCCCD);
            flag = false;
        } else {
            lblErrSoCCCD.setText("");
        }
        if (txtEmail.getText().equals("")) {
            lblErrEmail.setText(stErrKhongDeTrong);
        } else if (!txtEmail.getText().matches("^[a-z]\\w*@gmail.com$")) {
            lblErrEmail.setText(stErrEmail);
            flag = false;
        } else {
            lblErrEmail.setText("");
        }
        if (txtDiaChi.getText().equals("")) {
            lblErrDiaChi.setText(stErrKhongDeTrong);
            flag = false;
        } else {
            lblErrDiaChi.setText("");
        }
        if (txtSoDienThoai.getText().equals("")) {
            lblErrSoDienThoai.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!txtSoDienThoai.getText().matches("^\\+84[1-9][0-9]{8}$")) {
            lblErrSoDienThoai.setText(stErrSdt);
            flag = false;
        } else {
            lblErrSoDienThoai.setText("");
        }
        if (new Date().before(dcsNgaySinh.getDate())) {
            lblErrNgaySinh.setText(stErrNgaySinh);
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long khoangCach2Ngay = sdf.parse(sdf.format(dcsNgayVaoLam.getDate())).getTime() - sdf.parse(sdf.format(dcsNgaySinh.getDate())).getTime();
        long getYearDiff = TimeUnit.MILLISECONDS.toDays(khoangCach2Ngay) / 365;
        if (new Date().before(dcsNgayVaoLam.getDate())) {
            lblErrNgayVaoLam.setText(stErrNgayVaoLam);
            flag = false;
        } else if (getYearDiff < 18) {
            lblErrNgayVaoLam.setText(stErrNhanVienKhongDuTuoi);
            flag = false;
        } else {
            lblErrNgayVaoLam.setText("");
        }
        try {
            if (txtLuongThoaThuan.getText().trim().equals("")) {
                this.lblErrLuongThoaThuan.setText(stErrKhongDeTrong);
                flag = false;
            } else if (Double.parseDouble(txtLuongThoaThuan.getText()) <= 0) {
                this.lblErrLuongThoaThuan.setText(stSoTienLonHonKhong);
                flag = false;
            } else {
                this.lblErrLuongThoaThuan.setText("");
            }
        } catch (Exception e) {
            this.lblErrLuongThoaThuan.setText(stErrTienKhongHopLe);
            flag = false;
        }
        return flag;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemNhieu;
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
    private javax.swing.JLabel lblNgaySinh;
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
