/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.HopDong_DAO;
import Entity.HopDong;
import Entity.NhanVien;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author December
 */
public class HopDongView extends javax.swing.JPanel {

    /**
     * Creates new form HopDongView1
     */
    private DefaultTableModel model;
    private HopDong_DAO hopDongDao;
    private boolean isThem = false;
    private String stErrKhongDeTrong;
    private String stErrTenKhachHangKhongHopLe;
    private String stErrTien;
    private String stErrTongTien;
    private String stErrNgayKyKet;
    private String stErrHanHopDong;
    private String stThongbao;
    private String stBanXacNhanXoa;
    private String stXoaThanhCong;
    private String stXoaThatBai;
    private String stThemThanhCong;
    private String stThemThatBai;
    private String stTren;
    private String stHopDong;
    private String stKhongTimThayFile;
    private String stKhongDocDuocFile;
    private String stCapNhatThanhCong;
    private String stCapNhatThatBai;
    private String stErrTienKhongHopLe;

    private NhanVien nhanVienDangNhap;
    private String fileName;

    public HopDongView(NhanVien nv, String fileName) throws ParseException, IOException {
        this.nhanVienDangNhap = nv;
        this.fileName = fileName;
        initComponents();
        caiDatNgonNguChoView(fileName);
        excute();
        taiDuLieuLenTable();
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaHopDong.setText(prop.getProperty("HopDong_MahopDong"));
        lbltenKhachHang.setText(prop.getProperty("HopDong_TenKhachHang"));
        lblTenHopDong.setText(prop.getProperty("HopDong_TenHopDong"));
        lblTongTien.setText(prop.getProperty("HopDong_TongTien"));
        lblTienCoc.setText(prop.getProperty("HopDong_TienCoc"));
        lblNgayKyKet.setText(prop.getProperty("HopDong_NgayKyKet"));
        lblHanHopDong.setText(prop.getProperty("HopDong_HanHopDong"));
        lblYeuCau.setText(prop.getProperty("HopDong_YeuCau"));
        this.stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        this.stErrTenKhachHangKhongHopLe = prop.getProperty("HopDong_lblErrTenKhachHangKhongHopLe");
        this.stErrTien = prop.getProperty("HopDong_lblErrTien");
        this.stErrTongTien = prop.getProperty("HopDong_lblErrTongTienLonHonTienCoc");
        this.stErrNgayKyKet = prop.getProperty("HopDong_lblErrNgayKyKet");
        this.stErrHanHopDong = prop.getProperty("HopDong_lblErrHanHopDong");
        btnThemNhieu.setText(prop.getProperty("btnThemNhieu"));
        btnThem.setText(prop.getProperty("btnThem"));
        btnXoa.setText(prop.getProperty("btnXoa"));
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));
        ChangeName(tblHopDong, 0, prop.getProperty("HopDong_tblStt"));
        ChangeName(tblHopDong, 1, prop.getProperty("HopDong_tblMaHopDong"));
        ChangeName(tblHopDong, 2, prop.getProperty("HopDong_tblTenHopDong"));
        ChangeName(tblHopDong, 3, prop.getProperty("HopDong_tblTenKhachHang"));
        ChangeName(tblHopDong, 4, prop.getProperty("HopDong_tblTienCoc"));
        ChangeName(tblHopDong, 5, prop.getProperty("HopDong_tblTongTien"));
        ChangeName(tblHopDong, 6, prop.getProperty("HopDong_tblNgayKy"));
        ChangeName(tblHopDong, 7, prop.getProperty("HopDong_tblHanChot"));
        stThongbao = prop.getProperty("thongBao");
        stBanXacNhanXoa = prop.getProperty("banXacNhanXoa");
        stXoaThanhCong = prop.getProperty("xoaThanhCong");
        stXoaThatBai = prop.getProperty("xoaThatBai");
        stThemThanhCong = prop.getProperty("themThanhCong");
        stThemThatBai = prop.getProperty("themThatBai");
        stTren = prop.getProperty("tren");
        stHopDong = prop.getProperty("hopDong");
        stErrTienKhongHopLe = prop.getProperty("soTienKhongHople");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");

    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void excute() {
        model = (DefaultTableModel) tblHopDong.getModel();
        tblHopDong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblHopDong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblHopDong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblHopDong.setRowHeight(25);
        this.txtTenHopDong.setText("");
        this.txtMaHopDong.setText("");
        this.txtTenKhachHang.setText("");
        txtTienCoc.setText("");
        txtTongTien.setText("");
        dcsNgayKyKet.setDate(new Date());
        dcsHanHopDong.setDate(new Date());
        txtAreaYeuCau.setText("");

        lblErrTenKhachHang.setText("");
        lblErrTenHopDong.setText("");
        lblErrTienCoc.setText("");
        lblErrTongTien.setText("");
        lblErrNgayKiKet.setText("");
        lblErrHanHopDong.setText("");
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
    }

    public void taiDuLieuLenTable() throws ParseException {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        hopDongDao = new HopDong_DAO();
        ArrayList<HopDong> hopDongList = hopDongDao.layDanhSachHopDong();
        if (hopDongList != null) {
            hopDongList.forEach(e -> {
                DecimalFormat dcm = new DecimalFormat("###,###,###,###.### VND");
                String tienCoc = dcm.format(e.getSoTienCoc());
                String tongTien = dcm.format(e.getTongTien());
                model.addRow(new Object[]{model.getRowCount() + 1, e.getMaHopDong(), e.getTenHopDong(), e.getTenKhachHang(), tienCoc, tongTien, e.getNgayKyKet(), e.getHanChot()});
            });
        }
        if (tblHopDong.getRowCount() != 0) {
            tblHopDong.setRowSelectionInterval(0, 0);
            hienThiDuLieuLenTxt(0);
        }
    }

    public void hienThiDuLieuLenTxt(int dong) throws ParseException {
        hopDongDao = new HopDong_DAO();
        HopDong hd = hopDongDao.layRaMotHopDongTheoMaHopDong(tblHopDong.getValueAt(dong, 1).toString());
        txtMaHopDong.setText(tblHopDong.getValueAt(dong, 1).toString());
        txtTenHopDong.setText(tblHopDong.getValueAt(dong, 2).toString());
        txtTenKhachHang.setText(tblHopDong.getValueAt(dong, 3).toString());
        txtTienCoc.setText(tblHopDong.getValueAt(dong, 4).toString().split(" ")[0].replaceAll("\\.", ""));
        txtTongTien.setText(tblHopDong.getValueAt(dong, 5).toString().split(" ")[0].replaceAll("\\.", ""));
        dcsNgayKyKet.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblHopDong.getValueAt(dong, 6).toString()));
        dcsHanHopDong.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblHopDong.getValueAt(dong, 7).toString()));
        txtAreaYeuCau.setText(hd.getYeuCau());
        setEnableForSelected(false);

    }

    public void setEnableForSelected(boolean check) {
        txtTenKhachHang.setEditable(check);

        txtMaHopDong.setEditable(false);
        txtTenHopDong.setEditable(check);
        txtTienCoc.setEditable(check);
        txtTongTien.setEditable(check);
        dcsNgayKyKet.setEnabled(check);
        dcsHanHopDong.setEnabled(check);
        txtAreaYeuCau.setEditable(check);
    }

    public void setShow(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(true);
        }
    }

    public void setHidden(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPhongBan = new javax.swing.JPanel();
        txtTenHopDong = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbltenKhachHang = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        lblErrTongTien = new javax.swing.JLabel();
        lblMaHopDong = new javax.swing.JLabel();
        lblYeuCau = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnHuy = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblTienCoc = new javax.swing.JLabel();
        dcsNgayKyKet = new com.toedter.calendar.JDateChooser();
        lblErrTenKhachHang = new javax.swing.JLabel();
        lblErrNgayKiKet = new javax.swing.JLabel();
        lblHanHopDong = new javax.swing.JLabel();
        dcsHanHopDong = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaYeuCau = new javax.swing.JTextArea();
        lblNgayKyKet = new javax.swing.JLabel();
        lblErrHanHopDong = new javax.swing.JLabel();
        lblErrTienCoc = new javax.swing.JLabel();
        btnThemNhieu = new javax.swing.JButton();
        lblTenHopDong = new javax.swing.JLabel();
        txtMaHopDong = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        lblErrTenHopDong = new javax.swing.JLabel();
        scrHopDong = new javax.swing.JScrollPane();
        tblHopDong = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 725));
        setLayout(new java.awt.BorderLayout());

        pnlPhongBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhongBan.setPreferredSize(new java.awt.Dimension(1250, 450));
        pnlPhongBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTenHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenHopDong.setText("txtMa");
        txtTenHopDong.setBorder(null);
        txtTenHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHopDongActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTenHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 90, 200, 30));
        pnlPhongBan.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 200, 10));

        lbltenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbltenKhachHang.setText("Tên khách hàng:");
        pnlPhongBan.add(lbltenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 170, 20));

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenKhachHang.setText("jTextField1");
        txtTenKhachHang.setBorder(null);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 200, 30));
        pnlPhongBan.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 200, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/IconViewContract.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        pnlPhongBan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 200, 200));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 160, 40));

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
        pnlPhongBan.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 390, 160, 40));

        lblErrTongTien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTongTien.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTongTien.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 210, -1));

        lblMaHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHopDong.setText("Mã hợp đồng:");
        pnlPhongBan.add(lblMaHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 170, 20));

        lblYeuCau.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblYeuCau.setText("Yêu cầu:");
        pnlPhongBan.add(lblYeuCau, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 260, 170, 30));

        txtTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTienCoc.setText("0");
        txtTienCoc.setBorder(null);
        txtTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCocActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 200, 30));
        pnlPhongBan.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 200, 10));

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
        pnlPhongBan.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 390, 170, 40));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTien.setText("Tổng tiền:");
        pnlPhongBan.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 170, 20));

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongTien.setText("0");
        txtTongTien.setBorder(null);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 90, 200, 30));
        pnlPhongBan.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 120, 200, 10));

        lblTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTienCoc.setText("Số tiền cọc:");
        pnlPhongBan.add(lblTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 170, 20));

        dcsNgayKyKet.setDateFormatString("yyyy-MM-dd");
        pnlPhongBan.add(dcsNgayKyKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 150, 200, -1));

        lblErrTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTenKhachHang.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTenKhachHang.setText("lblErrTenKhachHang");
        pnlPhongBan.add(lblErrTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 290, -1));

        lblErrNgayKiKet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgayKiKet.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgayKiKet.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrNgayKiKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 210, -1));

        lblHanHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHanHopDong.setText("Hạn hợp đồng:");
        pnlPhongBan.add(lblHanHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 210, 170, 30));

        dcsHanHopDong.setDateFormatString("yyyy-MM-dd");
        pnlPhongBan.add(dcsHanHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 220, 200, -1));

        jScrollPane1.setAutoscrolls(true);

        txtAreaYeuCau.setColumns(20);
        txtAreaYeuCau.setRows(5);
        jScrollPane1.setViewportView(txtAreaYeuCau);

        pnlPhongBan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 270, 650, 100));

        lblNgayKyKet.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayKyKet.setText("Ngày ký kết:");
        pnlPhongBan.add(lblNgayKyKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 150, 170, 30));

        lblErrHanHopDong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHanHopDong.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHanHopDong.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrHanHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 250, 210, -1));

        lblErrTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTienCoc.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTienCoc.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 180, 210, -1));

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
        pnlPhongBan.add(btnThemNhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 170, 40));

        lblTenHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenHopDong.setText("Tên hợp đồng:");
        pnlPhongBan.add(lblTenHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 170, 20));

        txtMaHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaHopDong.setText("txtMa");
        txtMaHopDong.setBorder(null);
        txtMaHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHopDongActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtMaHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 200, 30));
        pnlPhongBan.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 200, 10));

        lblErrTenHopDong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTenHopDong.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTenHopDong.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrTenHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 120, 210, -1));

        add(pnlPhongBan, java.awt.BorderLayout.PAGE_START);

        scrHopDong.setBackground(new java.awt.Color(0, 0, 0));

        tblHopDong.getTableHeader().setBackground(new Color(32,136,203));
        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hợp đồng", "Tên hợp đồng", "Tên khách hàng", "Số tiền cọc", "Tổng tiền", "Ngày ký kết", "Hạn hợp đồng:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHopDong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblHopDong.getTableHeader().setReorderingAllowed(false);
        tblHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHopDongMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHopDongMousePressed(evt);
            }
        });
        scrHopDong.setViewportView(tblHopDong);

        add(scrHopDong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHopDongActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (isThem) {
            boolean isHopLe = checkInput();
            if (!isHopLe) {
                return;
            }
            String maHopDong = txtMaHopDong.getText().trim();
            String tenHopDong = txtTenHopDong.getText().trim();
            String tenKhachHang = txtTenKhachHang.getText().trim();
            double tienCoc = 0, tongTien = 0;
            try {
                tienCoc = Double.parseDouble(txtTienCoc.getText().trim());
                tongTien = Double.parseDouble(txtTongTien.getText().trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Date ngayKyKet = dcsNgayKyKet.getDate();
            Date hanChot = dcsHanHopDong.getDate();
            String yeuCau = txtAreaYeuCau.getText().trim();
            boolean isThemDuoc = hopDongDao.themMotHopDong(new HopDong(maHopDong, tenHopDong, tenKhachHang, tienCoc, tongTien, ngayKyKet, hanChot, yeuCau));
            if (isThemDuoc) {
                JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                try {
                    taiDuLieuLenTable();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                setEnableForSelected(false);
                setShow(btnThem, btnCapNhat, btnXoa, btnThemNhieu);
                setHidden(btnLuu, btnHuy);
                isThem = false;
            } else {
                JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            boolean isHopLe = checkInput();
            if (!isHopLe) {
                return;
            }
            String maHopDong = txtMaHopDong.getText().trim();
            String tenHopDong = txtTenHopDong.getText().trim();
            String tenKhachHang = txtTenKhachHang.getText().trim();
            double tienCoc = 0, tongTien = 0;
            try {
                tienCoc = Double.parseDouble(txtTienCoc.getText().trim());
                tongTien = Double.parseDouble(txtTongTien.getText().trim());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            Date ngayKyKet = dcsNgayKyKet.getDate();
            Date hanChot = dcsHanHopDong.getDate();
            String yeuCau = txtAreaYeuCau.getText().trim();
            boolean isSuaDuoc = hopDongDao.suaMotHopDong(new HopDong(maHopDong, tenHopDong, tenKhachHang, tienCoc, tongTien, ngayKyKet, hanChot, yeuCau));
            if (isSuaDuoc) {
                JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                try {
                    taiDuLieuLenTable();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                setEnableForSelected(false);
                setShow(btnThem, btnCapNhat, btnXoa, btnThemNhieu);
                setHidden(btnLuu, btnHuy);
            } else {
                JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnLuuActionPerformed
    public void xoaTrangTextField() {
        txtMaHopDong.setText("");
        txtTenHopDong.setText("");
        txtTenKhachHang.setText("");
        txtTongTien.setText("");
        txtTienCoc.setText("");
        dcsNgayKyKet.setDate(new Date());
        dcsHanHopDong.setDate(new Date());
        txtAreaYeuCau.setText("");
        txtTenHopDong.requestFocus();

    }

    public boolean checkInput() {
        boolean check = true;
        if (txtTenKhachHang.getText().equals("")) {
            lblErrTenKhachHang.setText(stErrKhongDeTrong);
            check = false;
        } else if (!txtTenKhachHang.getText().matches("^([a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổ"
                + "ẵẻỡơôưăêâđ]+)((\\s[A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){0,})$")) {
            lblErrTenKhachHang.setText(stErrTenKhachHangKhongHopLe);
            check = false;
        } else {
            lblErrTenKhachHang.setText("");
        }
        boolean checkTien = false;
        try {
            if (this.txtTienCoc.getText().equals("")) {
                this.lblErrTienCoc.setText(stErrKhongDeTrong);
                check = false;
            } else if (Double.parseDouble(txtTienCoc.getText()) <= 0) {
                this.lblErrTienCoc.setText(stErrTien);
                check = false;
            } else {
                this.lblErrTienCoc.setText("");
                checkTien = true;
            }
        } catch (Exception e) {
            this.lblErrTienCoc.setText(stErrTienKhongHopLe);
            check = false;
        }

        if (txtTenHopDong.getText().trim().equals("")) {
            lblErrTenHopDong.setText(stErrKhongDeTrong);
            check = false;
        } else {
            lblErrTenHopDong.setText("");
        }
        try {
            if (txtTongTien.getText().trim().equals("")) {
                this.lblErrTongTien.setText(stErrKhongDeTrong);
                check = false;
            } else if (Double.parseDouble(txtTongTien.getText().trim()) <= 0) {
                this.lblErrTongTien.setText(stErrTien);
                check = false;
            } else {
                try {
                    if (checkTien) {
                        if (Double.parseDouble(txtTienCoc.getText().trim()) >= Double.parseDouble(txtTongTien.getText().trim())) {
                            this.lblErrTongTien.setText(stErrTongTien);
                            check = false;
                        } else {
                            lblErrTongTien.setText("");
                        }
                    } else {
                        lblErrTongTien.setText("");
                    }
                } catch (Exception e) {
                    this.lblErrTienCoc.setText(stErrTienKhongHopLe);
                    check = false;
                }
            }
        } catch (Exception e) {
            this.lblErrTongTien.setText(stErrTienKhongHopLe);
            check = false;
        }
        if (dcsNgayKyKet.getDate().after(new Date())) {
            lblErrNgayKiKet.setText(stErrNgayKyKet);
            check = false;
        } else {
            lblErrNgayKiKet.setText("");
        }
        if (!dcsHanHopDong.getDate().after(new Date())) {
            lblErrHanHopDong.setText(stErrHanHopDong);
            check = false;
        } else {
            lblErrHanHopDong.setText("");
        }
        return check;
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        setShow(btnHuy, btnLuu);
        setHidden(btnThem, btnXoa, btnCapNhat, btnThemNhieu);

        this.isThem = true;
        xoaTrangTextField();
        setEnableForSelected(true);
        if (tblHopDong.getRowHeight() > 0) {
            txtMaHopDong.setText("HD" + (Integer.parseInt(tblHopDong.getValueAt(tblHopDong.getRowCount() - 1, 1).toString().split("D")[1]) + 1));
        } else {
            txtMaHopDong.setText("HD100001");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            hopDongDao = new HopDong_DAO();
            if (hopDongDao.xoaMotHopDong(tblHopDong.getValueAt(tblHopDong.getSelectedRow(), 1).toString())) {
                JOptionPane.showMessageDialog(this, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                try {
                    taiDuLieuLenTable();
                } catch (ParseException ex) {
                    Logger.getLogger(HopDongView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, stXoaThatBai, stThongbao, JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        isThem = false;
        setEnableForSelected(true);
        setShow(btnLuu, btnHuy);
        setHidden(btnThem, btnCapNhat, btnXoa, btnThemNhieu);
        txtTienCoc.setText(txtTienCoc.getText().replaceAll(",", "").trim());
        txtTongTien.setText(txtTongTien.getText().replaceAll(",", "").trim());
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTienCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienCocActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        setShow(btnThem, btnXoa, btnCapNhat, btnThemNhieu);
        setHidden(btnHuy, btnLuu);
        isThem = false;
        setEnableForSelected(false);
        lblErrHanHopDong.setText("");
        lblErrNgayKiKet.setText("");
        lblErrTenHopDong.setText("");
        lblErrTenKhachHang.setText("");
        lblErrTienCoc.setText("");
        lblErrTongTien.setText("");
        if (tblHopDong.getRowCount() != 0) {
            try {
                hienThiDuLieuLenTxt(tblHopDong.getSelectedRow());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void tblHopDongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMousePressed
    }//GEN-LAST:event_tblHopDongMousePressed

    private void tblHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMouseClicked
        try {
            int rowSelect = tblHopDong.getSelectedRow();
            if (rowSelect != -1) {
                hienThiDuLieuLenTxt(tblHopDong.getSelectedRow());
                setEnableForSelected(false);
                setShow(btnThem, btnCapNhat, btnXoa, btnThemNhieu);
                setHidden(btnHuy, btnLuu);
                lblErrTenKhachHang.setText("");
                lblErrTenHopDong.setText("");
                lblErrTongTien.setText("");
                lblErrTienCoc.setText("");
                lblErrHanHopDong.setText("");
                lblErrNgayKiKet.setText("");

            }
            // TODO add your handling code here:
        } catch (ParseException ex) {
            Logger.getLogger(HopDongView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblHopDongMouseClicked

    private void btnThemNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemNhieuActionPerformed

    private void txtMaHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHopDongActionPerformed

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
                    try {
                        String tenHopDong = "", tenKhachHang = "";
                        double tienCoc = 0f;
                        double tongTien = 0f;
                        Date ngayKiKet = new Date(), hanChot = new Date();
                        String yeuCau = "";
                        while (cellItera.hasNext()) {
                            Cell cell = cellItera.next();
                            if (row.getRowNum() == 0) {
                                continue;
                            } else {
                                if (cell.getColumnIndex() == 0) {
                                    tenHopDong = cell.getStringCellValue();
                                    // Tên hợp đồng
                                } else if (cell.getColumnIndex() == 1) {
                                    // Tên khách hàng
                                    tenKhachHang = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 2) {
                                    // Số tiền cọc
                                    tienCoc = cell.getNumericCellValue();
                                } else if (cell.getColumnIndex() == 3) {
                                    // Tổng tiền
                                    tongTien = cell.getNumericCellValue();
                                } else if (cell.getColumnIndex() == 4) {
                                    // Ngày kí kết
                                    String chuoiNgayKiKet = cell.getStringCellValue();
                                    try {
                                        ngayKiKet = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiNgayKiKet);
                                    } catch (ParseException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                } else if (cell.getColumnIndex() == 5) {
                                    // Hạn chót
                                    String chuoiHanChot = cell.getStringCellValue();
                                    try {
                                        hanChot = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiHanChot);
                                    } catch (ParseException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                } else if (cell.getColumnIndex() == 6) {
                                    // Yêu cầu
                                    yeuCau = cell.getStringCellValue();
                                }
                            }

                        }
                        String maHopDongDeThem = hopDongDao.layRaMaHopDongDeThem();
                        boolean coThemDuoc = hopDongDao.themMotHopDong(new HopDong(maHopDongDeThem, tenHopDong, tenKhachHang, tienCoc, tongTien, ngayKiKet, hanChot, yeuCau));
                        if (coThemDuoc) {
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                in.close();
                JOptionPane.showMessageDialog(null, stThemThanhCong +" " + count + " "+stTren + (--total) + stHopDong);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, stKhongTimThayFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, stKhongDocDuocFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            }
            if (count != 0) {
                try {
                    taiDuLieuLenTable();
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
    private com.toedter.calendar.JDateChooser dcsHanHopDong;
    private com.toedter.calendar.JDateChooser dcsNgayKyKet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JLabel lblErrHanHopDong;
    private javax.swing.JLabel lblErrNgayKiKet;
    private javax.swing.JLabel lblErrTenHopDong;
    private javax.swing.JLabel lblErrTenKhachHang;
    private javax.swing.JLabel lblErrTienCoc;
    private javax.swing.JLabel lblErrTongTien;
    private javax.swing.JLabel lblHanHopDong;
    private javax.swing.JLabel lblMaHopDong;
    private javax.swing.JLabel lblNgayKyKet;
    private javax.swing.JLabel lblTenHopDong;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblYeuCau;
    private javax.swing.JLabel lbltenKhachHang;
    private javax.swing.JPanel pnlPhongBan;
    private javax.swing.JScrollPane scrHopDong;
    private javax.swing.JTable tblHopDong;
    private javax.swing.JTextArea txtAreaYeuCau;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JTextField txtTenHopDong;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
