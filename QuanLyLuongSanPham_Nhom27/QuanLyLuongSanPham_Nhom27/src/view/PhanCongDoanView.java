/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongDoan_DAO;
import DAO.SanPham_DAO;
import Entity.CongDoan;
import Entity.HopDong;
import Entity.SanPham;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author December
 */
public class PhanCongDoanView extends javax.swing.JPanel implements ActionListener, MouseListener {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel modelTableSanPham;
    private DefaultTableModel modelTableCongDoan;
    private SanPham_DAO sanPham_DAO;
    private CongDoan_DAO congDoan_DAO;
    private NumberFormat nf;
    private DecimalFormat df;
    // oFlag để gắn cờ btnThem | btnCapNhat cái nào là cái nhấn vào cuối để biết thao tác để thực hiện lưu
    private Object oFlag;

    private String stErrKhongDeTrong;
    private String stErrSoLuong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stTren;
    private String stCongDoan;
    private String stKhongTimThayFile;
    private String stKhongDocDuocFile;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stChuaKiTuChu;
    private String stSoLuongPhaiLonHonHoacBang;
    private String stSoNguyen;
    private String stThuTuLam;
    private String stSauNgayHienTai;

    public PhanCongDoanView(String fileName) throws IOException {
        initComponents();
        caiDatNgonNguChoView(fileName);
        nf = new DecimalFormat("#,###.00");
        df = new DecimalFormat("#");
        df.setMaximumFractionDigits(2);

        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // add sự kiện cho các button
        btnThem.addActionListener(this);
        btnHuy.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnLuu.addActionListener(this);
        btnXoa.addActionListener(this);
        // add sự kiện cho jtable
        tblCongDoan.addMouseListener(this);
        tblDanhSachSanPham.addMouseListener(this);
        // set lúc khởi tạo cho 2 button là enable false
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
        // Gán đối tượng cho 2 model table
        modelTableSanPham = (DefaultTableModel) tblDanhSachSanPham.getModel();
        modelTableCongDoan = (DefaultTableModel) tblCongDoan.getModel();
        sanPham_DAO = new SanPham_DAO();
        congDoan_DAO = new CongDoan_DAO();
        // xóa các error msg của textfield
        lblErrLuongSP.setText("");
        lblErrSoLuongCan.setText("");
        lblErrThoiHan.setText("");
        lblErrTenCongDoan1.setText("");
        lblErrThuTuLam.setText("");
        moKhoaTextField(false);

        excute();

    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaSanPham.setText(prop.getProperty("pcd_maSanPham"));
        lblTenSanPham.setText(prop.getProperty("pcd_tenSanPham"));
        lblMaCongDoan.setText(prop.getProperty("pcd_maCongDoan"));
        lblTenCongDoan.setText(prop.getProperty("pcd_tenCongDoan"));
        lblSoLuongCan.setText(prop.getProperty("pcd_soLuongCan"));
        lblGiaTien.setText(prop.getProperty("pcd_luongSanPham"));
        lblThoiHan.setText(prop.getProperty("pcd_thoiHan"));
        lblSoThuTuCongDoan.setText(prop.getProperty("pcd_thuTuLam"));

        ChangeName(tblDanhSachSanPham, 0, prop.getProperty("sp_stt"));
        ChangeName(tblDanhSachSanPham, 1, lblMaSanPham.getText());
        ChangeName(tblDanhSachSanPham, 2, lblMaCongDoan.getText());

        ChangeName(tblCongDoan, 0, lblSoThuTuCongDoan.getText());
        ChangeName(tblCongDoan, 1, lblMaCongDoan.getText());
        ChangeName(tblCongDoan, 2, lblTenCongDoan.getText());
        ChangeName(tblCongDoan, 3, lblSoLuongCan.getText());
        ChangeName(tblCongDoan, 4, prop.getProperty("pcd_soLuongDaLam"));
        ChangeName(tblCongDoan, 5, lblGiaTien.getText());
        ChangeName(tblCongDoan, 6, lblThoiHan.getText());
        ChangeName(tblCongDoan, 7, prop.getProperty("pcd_mucDoHoanThanh"));
        scrTableSanPham.setBorder(new TitledBorder(prop.getProperty("pcd_tieuDeSanPham")));
        scrTableCongDoan.setBorder(new TitledBorder(prop.getProperty("pcd_tieuDeCongDoan")));

        btnThemNhieu.setText(prop.getProperty("btnThemNhieu"));
        btnThem.setText(prop.getProperty("btnThem"));
        btnXoa.setText(prop.getProperty("btnXoa"));
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));

        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        stXoaThatBai = prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("themThanhCong");
        stThemThatBai = prop.getProperty("themThatBai");
        stTren = prop.getProperty("tren");
        stCongDoan = prop.getProperty("pcd_congDoan");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stChuaKiTuChu = prop.getProperty("pcd_ErrChiChua1KyTu");
        stErrSoLuong = prop.getProperty("sp_lblErrSoLuong");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        stSoLuongPhaiLonHonHoacBang = prop.getProperty("pcd_ErrSoLuongPhaiLonHon");
        stSoNguyen = prop.getProperty("pcd_ErrPhaiLaSoNguyen");
        stThuTuLam = prop.getProperty("pcd_ErrThuTuLam");
        stSauNgayHienTai = prop.getProperty("pcd_ErrPhaiBangHoacSauNgayHienTai");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void excute() {
//        this.txtMaNhanVien.setText("");
//        this.txtMaNhanVien.setBackground(new Color(0, 0, 0, 1));

        // custom table
        tblDanhSachSanPham.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblDanhSachSanPham.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblDanhSachSanPham.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblDanhSachSanPham.setRowHeight(25);

        tblCongDoan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongDoan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongDoan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongDoan.setRowHeight(25);
        this.txtTenCongDoan.setBackground(new Color(0, 0, 0, 1));

        //gọi hàm tải dữ liệu tải dự liệu
        taiDuLieuLenBangSanPham();

    }

    public void moKhoaTextField(boolean b) {
        if (b) {
            txtTenCongDoan.requestFocus();
        }
        txtTenCongDoan.setEditable(b);
        txtSoLuongCan.setEditable(b);
        txtGiaTien.setEditable(b);
        dcsThoiHan.setEnabled(b);
    }

    public void taiDuLieuLenBangSanPham() {
        while (tblDanhSachSanPham.getRowCount() != 0) {
            modelTableSanPham.removeRow(0);
        }
        ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPham();
        for (SanPham sanPham : dsSanPham) {
            String data[] = {(modelTableSanPham.getRowCount() + 1) + "", sanPham.getMaSanPham(), sanPham.getTenSanPham()};
            modelTableSanPham.addRow(data);
        }
        if (tblDanhSachSanPham.getRowCount() != 0) {
            tblDanhSachSanPham.setRowSelectionInterval(0, 0);
            hienThiSanPhamLenTxt(0);
            taiDuLieuLenBangCongDoan();
        }
    }

    public void hienThiSanPhamLenTxt(int row) {
        lblHienThiMaSP.setText(tblDanhSachSanPham.getValueAt(row, 1).toString());
        lblHienThiTenSP.setText(tblDanhSachSanPham.getValueAt(row, 2).toString());

    }

    public void taiDuLieuLenBangCongDoan() {
        while (tblCongDoan.getRowCount() != 0) {
            modelTableCongDoan.removeRow(0);
        }

        // cột 1 là cột mã sản phẩm
        String maSanPham = tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 1).toString();
        ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanTheoMaSP(maSanPham);
        for (CongDoan congDoan : dsCongDoan) {
            String maCongDoan = congDoan.getMaCongDoan();
            String data[] = {(modelTableCongDoan.getRowCount() + 1) + "", congDoan.getMaCongDoan(), congDoan.getTenCongDoan(),
                congDoan.getSoLuongCan() + "", congDoan_DAO.laySoLuongLamDuocTheoMaCongDoan(maCongDoan) + "", nf.format(congDoan.getTienLuong()), congDoan.getThoiHan().toString(),
                String.format("%.2f", congDoan_DAO.layMucDoHoanThanhCuaMotCongDoan(maCongDoan)) + "%"};
            modelTableCongDoan.addRow(data);
        }
        if (tblCongDoan.getRowCount() != 0) {
            tblCongDoan.setRowSelectionInterval(0, 0);
            hienThiCongDoanLenTxt(0);
        } else {
            xoaTrangTextField();
        }

    }

    public void hienThiCongDoanLenTxt(int row) {
        if (row != -1) {
            txtSoThuTuLam.setText(tblCongDoan.getValueAt(row, 0).toString());
            double luongTrenSanPham = 0;
            try {
                luongTrenSanPham = nf.parse(tblCongDoan.getValueAt(row, 5).toString()).doubleValue();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            lblHienThiMaCongDoan.setText(tblCongDoan.getValueAt(row, 1).toString());
            txtTenCongDoan.setText(tblCongDoan.getValueAt(row, 2).toString());
            txtSoLuongCan.setText(tblCongDoan.getValueAt(row, 3).toString());
            txtGiaTien.setText(df.format(luongTrenSanPham).replaceAll(",", "."));
            try {
                dcsThoiHan.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblCongDoan.getValueAt(row, 6).toString()));
            } catch (ParseException ex) {
                Logger.getLogger(PhanCongDoanView.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        jPanel5 = new javax.swing.JPanel();
        scrTableSanPham = new javax.swing.JScrollPane();
        tblDanhSachSanPham = new javax.swing.JTable();
        lblTenCongDoan = new javax.swing.JLabel();
        lblHienThiMaSP = new javax.swing.JLabel();
        lblHienThiTenSP = new javax.swing.JLabel();
        lblMaSanPham = new javax.swing.JLabel();
        lblTenSanPham = new javax.swing.JLabel();
        txtTenCongDoan = new javax.swing.JTextField();
        lblErrThoiHan = new javax.swing.JLabel();
        txtSoLuongCan = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        lblErrSoLuongCan = new javax.swing.JLabel();
        lblMaCongDoan = new javax.swing.JLabel();
        lblHienThiMaCongDoan = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblSoLuongCan = new javax.swing.JLabel();
        lblSoThuTuCongDoan = new javax.swing.JLabel();
        txtGiaTien = new javax.swing.JTextField();
        lblErrLuongSP = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblGiaTien = new javax.swing.JLabel();
        dcsThoiHan = new com.toedter.calendar.JDateChooser();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblErrTenCongDoan1 = new javax.swing.JLabel();
        lblThoiHan = new javax.swing.JLabel();
        btnThemNhieu = new javax.swing.JButton();
        txtSoThuTuLam = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblErrThuTuLam = new javax.swing.JLabel();
        scrTableCongDoan = new javax.swing.JScrollPane();
        tblCongDoan = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 475));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrTableSanPham.setBackground(new java.awt.Color(255, 255, 255));
        scrTableSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblDanhSachSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblDanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrTableSanPham.setViewportView(tblDanhSachSanPham);

        jPanel5.add(scrTableSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 720, 230));

        lblTenCongDoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTenCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenCongDoan.setText("Tên công đoạn:");
        jPanel5.add(lblTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 160, 170, 40));

        lblHienThiMaSP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHienThiMaSP.setText("SP001");
        jPanel5.add(lblHienThiMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 20, 180, 40));

        lblHienThiTenSP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblHienThiTenSP.setText("Giày loại 1");
        jPanel5.add(lblHienThiTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 70, 180, 40));

        lblMaSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMaSanPham.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaSanPham.setText("Mã sản phẩm:");
        jPanel5.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 20, 190, 40));

        lblTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTenSanPham.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenSanPham.setText("Tên sản phẩm:");
        jPanel5.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 70, 190, 40));

        txtTenCongDoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenCongDoan.setText("0");
        txtTenCongDoan.setBorder(null);
        txtTenCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCongDoanActionPerformed(evt);
            }
        });
        jPanel5.add(txtTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 170, 150, 20));

        lblErrThoiHan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrThoiHan.setForeground(new java.awt.Color(204, 0, 0));
        lblErrThoiHan.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrThoiHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 350, 190, -1));

        txtSoLuongCan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoLuongCan.setText("0");
        txtSoLuongCan.setBorder(null);
        jPanel5.add(txtSoLuongCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 220, 150, 20));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 240, 180, 20));

        lblErrSoLuongCan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoLuongCan.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoLuongCan.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoLuongCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 240, 190, -1));

        lblMaCongDoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMaCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaCongDoan.setText("Mã công đoạn:");
        jPanel5.add(lblMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 120, 190, 40));

        lblHienThiMaCongDoan.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblHienThiMaCongDoan.setText("CD001");
        jPanel5.add(lblHienThiMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 120, 180, 40));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 190, 180, 10));

        lblSoLuongCan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoLuongCan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoLuongCan.setText("Số lượng cần:");
        jPanel5.add(lblSoLuongCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 170, 40));

        lblSoThuTuCongDoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoThuTuCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoThuTuCongDoan.setText("Thứ tự làm:");
        jPanel5.add(lblSoThuTuCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 370, 170, 40));

        txtGiaTien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtGiaTien.setText("0");
        txtGiaTien.setBorder(null);
        jPanel5.add(txtGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 270, 150, 20));

        lblErrLuongSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrLuongSP.setForeground(new java.awt.Color(204, 0, 0));
        lblErrLuongSP.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrLuongSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 290, 190, -1));
        jPanel5.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 290, 180, 10));

        lblGiaTien.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGiaTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGiaTien.setText("Lương / 1 Sản phẩm:");
        jPanel5.add(lblGiaTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 260, 170, 40));
        jPanel5.add(dcsThoiHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 320, 190, 30));

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
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 130, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.setMaximumSize(new java.awt.Dimension(67, 31));
        btnXoa.setMinimumSize(new java.awt.Dimension(67, 31));
        btnXoa.setPreferredSize(new java.awt.Dimension(67, 31));
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 130, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.setMaximumSize(new java.awt.Dimension(67, 31));
        btnCapNhat.setMinimumSize(new java.awt.Dimension(67, 31));
        btnCapNhat.setPreferredSize(new java.awt.Dimension(67, 31));
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 140, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.setMaximumSize(new java.awt.Dimension(67, 31));
        btnLuu.setMinimumSize(new java.awt.Dimension(67, 31));
        btnLuu.setPreferredSize(new java.awt.Dimension(67, 31));
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 140, 40));

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/delete.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setMaximumSize(new java.awt.Dimension(67, 31));
        btnHuy.setMinimumSize(new java.awt.Dimension(67, 31));
        btnHuy.setPreferredSize(new java.awt.Dimension(67, 31));
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 400, 140, 40));

        lblErrTenCongDoan1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTenCongDoan1.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTenCongDoan1.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrTenCongDoan1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 190, 190, -1));

        lblThoiHan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblThoiHan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThoiHan.setText("Thời hạn:");
        jPanel5.add(lblThoiHan, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 310, 170, 40));

        btnThemNhieu.setBackground(new java.awt.Color(255, 234, 167));
        btnThemNhieu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThemNhieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThemNhieu.setText("Thêm nhiều");
        btnThemNhieu.setBorder(null);
        btnThemNhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemNhieuMouseClicked(evt);
            }
        });
        btnThemNhieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemNhieuActionPerformed(evt);
            }
        });
        jPanel5.add(btnThemNhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 310, 140, 40));

        txtSoThuTuLam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoThuTuLam.setText("0");
        txtSoThuTuLam.setBorder(null);
        jPanel5.add(txtSoThuTuLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 380, 150, 20));
        jPanel5.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 400, 180, 10));

        lblErrThuTuLam.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrThuTuLam.setForeground(new java.awt.Color(204, 0, 0));
        lblErrThuTuLam.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrThuTuLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 400, 190, -1));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scrTableCongDoan.setBackground(new java.awt.Color(255, 255, 255));
        scrTableCongDoan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách công đoạn sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 16))); // NOI18N

        tblCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCongDoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Thứ tự làm", "Mã công đoạn", "Tên  công đoạn", "Số lượng cần làm", "Số lương đã làm", "Mức lương / 1 Sản Phẩm", "Thòi hạn", "Mưc độ hoàn thành (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCongDoan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrTableCongDoan.setViewportView(tblCongDoan);

        add(scrTableCongDoan, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenCongDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenCongDoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenCongDoanActionPerformed

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

    private void btnThemNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemNhieuActionPerformed

    private void btnThemNhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhieuMouseClicked
        // TODO add your handling code here:

        JFileChooser fileChooser = new JFileChooser("d:");
        //        int respone=fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new File(".\\src\\ExcelFile"));
        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File (.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int count = 0, total = 0;
        int respone = fileChooser.showSaveDialog(null);
        if (respone == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();

            try ( FileInputStream in = new FileInputStream(inputFile)) {
                XSSFWorkbook importedFile = new XSSFWorkbook(in);
                XSSFSheet sheet1 = importedFile.getSheetAt(0);
                Iterator<Row> rowIterator = sheet1.iterator();
                while (rowIterator.hasNext()) {
                    total++;
                    Row row = rowIterator.next();
                    Iterator<Cell> cellItera = row.cellIterator();
                    // khai báo biến 
                    int thuTuLam = 0, soLuongCan = 0;
                    String tenCongDoan = "";
                    Date thoiHan = new Date();
                    String maSanPham = "";
                    String tinhTrang = "0%";
                    double tienLuong = 0f;
                    try {

                        while (cellItera.hasNext()) {
                            Cell cell = cellItera.next();
                            if (row.getRowNum() == 0) {
                                continue;
                            } else {
                                if (cell.getColumnIndex() == 0) {
                                    thuTuLam = (int) cell.getNumericCellValue();
                                } else if (cell.getColumnIndex() == 1) {
                                    tenCongDoan = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 2) {
                                    soLuongCan = (int) cell.getNumericCellValue();
                                } else if (cell.getColumnIndex() == 3) {
                                    try {
                                        String chuoiThoiHan = cell.getStringCellValue();
                                        thoiHan = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiThoiHan);
                                    } catch (Exception e) {
                                        System.out.println(e.getMessage());
                                    }
                                } else if (cell.getColumnIndex() == 4) {
                                    maSanPham = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 5) {
                                    tienLuong = cell.getNumericCellValue();
                                }
                            }
                        }

                        SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPham);
                        String maCongDoan = congDoan_DAO.layRaMaCongDoanDeThem();
                        boolean coThemDuoc = congDoan_DAO.themMotCongDoan(new CongDoan(maCongDoan, thuTuLam, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, tienLuong));

                        if (coThemDuoc) {
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                in.close();
                JOptionPane.showMessageDialog(null, stThemThanhCong + count + stTren + (--total) + stCongDoan);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, stKhongTimThayFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, stKhongDocDuocFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            }
            if (count != 0) {
                try {
                    taiDuLieuLenBangCongDoan();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnThemNhieuMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemNhieu;
    private javax.swing.JButton btnXoa;
    private com.toedter.calendar.JDateChooser dcsThoiHan;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblErrLuongSP;
    private javax.swing.JLabel lblErrSoLuongCan;
    private javax.swing.JLabel lblErrTenCongDoan1;
    private javax.swing.JLabel lblErrThoiHan;
    private javax.swing.JLabel lblErrThuTuLam;
    private javax.swing.JLabel lblGiaTien;
    private javax.swing.JLabel lblHienThiMaCongDoan;
    private javax.swing.JLabel lblHienThiMaSP;
    private javax.swing.JLabel lblHienThiTenSP;
    private javax.swing.JLabel lblMaCongDoan;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblSoLuongCan;
    private javax.swing.JLabel lblSoThuTuCongDoan;
    private javax.swing.JLabel lblTenCongDoan;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblThoiHan;
    private javax.swing.JScrollPane scrTableCongDoan;
    private javax.swing.JScrollPane scrTableSanPham;
    private javax.swing.JTable tblCongDoan;
    private javax.swing.JTable tblDanhSachSanPham;
    private javax.swing.JTextField txtGiaTien;
    private javax.swing.JTextField txtSoLuongCan;
    private javax.swing.JTextField txtSoThuTuLam;
    private javax.swing.JTextField txtTenCongDoan;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            lblHienThiMaCongDoan.setText(congDoan_DAO.layRaMaCongDoanDeThem());
            moKhoaTextField(true);
            xoaTrangTextField();
            lblHienThiMaCongDoan.setText(congDoan_DAO.layRaMaCongDoanDeThem());
            oFlag = e.getSource();
            // mở | khóa các btn
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            btnThem.setEnabled(false);
            btnThemNhieu.setEnabled(false);
            btnHuy.setEnabled(true);
            btnLuu.setEnabled(true);

        } else if (o.equals(btnCapNhat)) {
            moKhoaTextField(true);
            oFlag = e.getSource();
            // mở | khóa các btn
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            btnThemNhieu.setEnabled(false);
            btnThem.setEnabled(false);
            btnHuy.setEnabled(true);
            btnLuu.setEnabled(true);
        } else if (o.equals(btnHuy)) {
            moKhoaTextField(false);
            if (tblCongDoan.getRowCount() != 0) {
                hienThiCongDoanLenTxt(0);
                tblCongDoan.setRowSelectionInterval(0, 0);

            } else {
                xoaTrangTextField();
            }
            // mở | khóa các btn
            btnCapNhat.setEnabled(true);
            btnXoa.setEnabled(true);
            btnThem.setEnabled(true);
            btnThemNhieu.setEnabled(true);
            btnHuy.setEnabled(false);
            btnLuu.setEnabled(false);

            lblErrTenCongDoan1.setText("");
            lblErrSoLuongCan.setText("");
            lblErrLuongSP.setText("");
            lblErrThoiHan.setText("");
            lblErrThuTuLam.setText("");

            if (tblCongDoan.getRowCount() == 0) {
                btnCapNhat.setEnabled(false);
                btnXoa.setEnabled(false);
            }
        } else if (o.equals(btnXoa)) {
            int rowSelected = tblCongDoan.getSelectedRow();
            if (rowSelected != -1) {
                int coXacNhanXoa = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.ERROR_MESSAGE);
                if (coXacNhanXoa == 0) {
                    boolean coXoaDuoc = congDoan_DAO.xoaMotCongDoanTheoMa(tblCongDoan.getValueAt(tblCongDoan.getSelectedRow(), 1).toString());
                    if (coXoaDuoc) {
                        JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        taiDuLieuLenBangCongDoan();
                    } else {
                        JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else if (o.equals(btnLuu)) {

            if (oFlag.equals(btnThem)) {
                boolean kiemTra = kiemTraDuLieuHopLe();
                if (!kiemTra) {
                    return;
                }
                lblErrTenCongDoan1.setText("");
                lblErrSoLuongCan.setText("");
                lblErrLuongSP.setText("");
                lblErrThoiHan.setText("");

                int soLuongCan = 0;
                double tienLuong = 0;
                int thuTuLam = 0;
                String maCongDoan = lblHienThiMaCongDoan.getText();
                String tenCongDoan = txtTenCongDoan.getText();
                try {
                    soLuongCan = Integer.parseInt(txtSoLuongCan.getText());
                    tienLuong = Double.parseDouble(txtGiaTien.getText());
                    thuTuLam = Integer.parseInt(txtSoThuTuLam.getText());
                } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(lblHienThiMaSP.getText());
                boolean coThemDuoc = congDoan_DAO.themMotCongDoan(new CongDoan(maCongDoan, thuTuLam, tenCongDoan, soLuongCan, "0%", dcsThoiHan.getDate(), sanPham, tienLuong));
                if (coThemDuoc) {
                    moKhoaTextField(false);
                    taiDuLieuLenBangCongDoan();
                    btnCapNhat.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnThemNhieu.setEnabled(true);
                    btnHuy.setEnabled(false);
                    btnLuu.setEnabled(false);
                    oFlag = null;
                    JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (oFlag.equals(btnCapNhat)) {
                boolean kiemTra = kiemTraDuLieuHopLe();
                if (!kiemTra) {
                    return;
                }
                lblErrTenCongDoan1.setText("");
                lblErrSoLuongCan.setText("");
                lblErrLuongSP.setText("");
                lblErrThoiHan.setText("");
                int soLuongCan = 0;
                int thuTuLam = 0;
                double tienLuong = 0;
                String maCongDoan = lblHienThiMaCongDoan.getText();
                String tenCongDoan = txtTenCongDoan.getText();
                try {
                    soLuongCan = Integer.parseInt(txtSoLuongCan.getText());
                    tienLuong = Double.parseDouble(txtGiaTien.getText());
                    thuTuLam = Integer.parseInt(txtSoThuTuLam.getText());
                } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(lblHienThiMaSP.getText());
                boolean coSuaDuoc = congDoan_DAO.suaMotCongDoan(new CongDoan(maCongDoan, thuTuLam, tenCongDoan, soLuongCan, "0%", dcsThoiHan.getDate(), sanPham, tienLuong));
                if (coSuaDuoc) {
                    moKhoaTextField(false);
                    taiDuLieuLenBangCongDoan();
                    btnCapNhat.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnThemNhieu.setEnabled(true);
                    btnHuy.setEnabled(false);
                    btnLuu.setEnabled(false);
                    oFlag = null;
                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
    }

    public boolean kiemTraDuLieuHopLe() {
        boolean flag = true;
        int soLuongCan = 0;
        double luongSanPham = 0;
        int thuTuLam = 0;
        if (txtTenCongDoan.getText().trim().equals("")) {
            lblErrTenCongDoan1.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!txtTenCongDoan.getText().matches("^[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{2,}(\\s[A-Za-zÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚÝàáâãèéêìíòóôõùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]{1,})*$")) {
            lblErrTenCongDoan1.setText(stChuaKiTuChu);
            flag = false;
        } else {
            lblErrTenCongDoan1.setText("");
        }
        try {
            soLuongCan = Integer.parseInt(txtSoLuongCan.getText());
        } catch (Exception e) {
            lblErrSoLuongCan.setText(stErrSoLuong);
            flag = false;
        }

        if (soLuongCan > 0) {
            lblErrSoLuongCan.setText("");
        }
        if (soLuongCan <= 0) {
            lblErrSoLuongCan.setText(stErrSoLuong);
            flag = false;
        } else {
            lblErrSoLuongCan.setText("");
        }
        SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(lblHienThiMaSP.getText());
        System.out.println(sanPham.getSoLuongSanPham());
        if (soLuongCan < sanPham.getSoLuongSanPham()) {
            lblErrSoLuongCan.setText(stSoLuongPhaiLonHonHoacBang + sanPham.getSoLuongSanPham());
            flag = false;
        } else {
            lblErrSoLuongCan.setText("");
        }
        try {
            thuTuLam = Integer.parseInt(txtSoThuTuLam.getText());
        } catch (Exception e) {
            lblErrThuTuLam.setText(stSoNguyen);
            flag = false;
        }
        if (thuTuLam > 0) {
            lblErrThuTuLam.setText("");
        } else {
            lblErrThuTuLam.setText(stThuTuLam);
            flag = false;
        }
        if (dcsThoiHan.getDate().before(new Date())) {
            lblErrThoiHan.setText(stSauNgayHienTai);
            flag = false;
        } else {
            lblErrThoiHan.setText("");
        }
        try {
            luongSanPham = Double.parseDouble(txtGiaTien.getText());
        } catch (Exception e) {
            lblErrLuongSP.setText(stErrSoLuong);
            flag = false;
        }
        if (luongSanPham > 0) {
            lblErrLuongSP.setText("");
        }
        if (luongSanPham <= 0) {
            lblErrLuongSP.setText(stErrSoLuong);
            flag = false;
        }
        // kiểm tra rỗng
        if (txtTenCongDoan.getText().equals("")) {
            lblErrTenCongDoan1.setText(stErrKhongDeTrong);
            flag = false;
        }
        if (txtGiaTien.getText().equals("")) {
            lblErrLuongSP.setText(stErrKhongDeTrong);
            flag = false;
        }
        if (txtSoLuongCan.getText().equals("")) {
            lblErrSoLuongCan.setText(stErrKhongDeTrong);
            flag = false;
        }
        return flag;
    }

    public void xoaTrangTextField() {
        txtTenCongDoan.setText("");
        txtSoThuTuLam.setText("");
        txtSoLuongCan.setText("");
        txtGiaTien.setText("");
        dcsThoiHan.setDate(new Date());
        lblHienThiMaCongDoan.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblCongDoan)) {
            int rowSelected = tblCongDoan.getSelectedRow();
            if (rowSelected != -1) {
                hienThiCongDoanLenTxt(rowSelected);
                moKhoaTextField(false);
                btnCapNhat.setEnabled(true);
                btnXoa.setEnabled(true);
                btnThem.setEnabled(true);
                btnThemNhieu.setEnabled(true);
                btnHuy.setEnabled(false);
                btnLuu.setEnabled(false);

            }
        } else if (o.equals(tblDanhSachSanPham)) {
            int rowSelected = tblDanhSachSanPham.getSelectedRow();

            if (rowSelected != -1) {
                taiDuLieuLenBangCongDoan();
                hienThiSanPhamLenTxt(rowSelected);
                if (tblCongDoan.getRowCount() != 0) {
                    tblCongDoan.setRowSelectionInterval(0, 0);
                    hienThiCongDoanLenTxt(0);
                } else {
                    xoaTrangTextField();
                }
            }
            if (tblCongDoan.getRowCount() == 0) {
                btnCapNhat.setEnabled(false);
                btnXoa.setEnabled(false);
            } else {
                btnCapNhat.setEnabled(true);
                btnXoa.setEnabled(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
