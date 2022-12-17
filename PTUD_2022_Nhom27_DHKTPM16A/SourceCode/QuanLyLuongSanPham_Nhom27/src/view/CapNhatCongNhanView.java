/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongNhan_DAO;
import DAO.ToNhom_DAO;
import Entity.CongNhan;
import Entity.ToNhom;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
public class CapNhatCongNhanView extends javax.swing.JPanel implements ActionListener, MouseListener {

    private ToNhom_DAO toNhom_DAO;
    private CongNhan_DAO congNhan_DAO;
    private Object oFlag;
    private DefaultTableModel modelTableCongNhan;

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
    private String stErrCongNhanKhongDuTuoi;
    private String stErrTienKhongHopLe;
    private String stSoTienLonHonKhong;
    private String stCongNhan;

    /**
     * Creates new form NhanVienView
     */
    public CapNhatCongNhanView(String fileName) throws IOException {
        initComponents();
        excute();

        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        toNhom_DAO = new ToNhom_DAO();
        congNhan_DAO = new CongNhan_DAO();
        modelTableCongNhan = (DefaultTableModel) tblCongNhan.getModel();

        // sét default lúc đàu cho 2 btn hủy, lưu
        dongMoBtnEdit(true);
        dongMoTxt(false);
        lblAnhDaiDienOfPn.setEnabled(false);
        // xóa trắng các err label
        lblErrCCCD.setText("");
        lblErrDiaChi.setText("");
        lblErrEmail.setText("");
        lblErrHoTen.setText("");
        lblErrNgaySinh.setText("");
        lblErrNgayVaoLam.setText("");
        lblErrSoDienThoai.setText("");

        hienThiDuLieuLen();

        // add sự kiện cho btn, table
        tblCongNhan.addMouseListener(this);
        btnCapNhat.addActionListener(this);
        btnThem.addActionListener(this);
        btnHuy.addActionListener(this);
        btnLuu.addActionListener(this);
        btnXoa.addActionListener(this);
        caiDatNgonNguChoView(fileName);

    }
    public void setEditTextDateChooser() {
        JTextFieldDateEditor ngaySinh = (JTextFieldDateEditor) dtcNgaySinh.getDateEditor();
        JTextFieldDateEditor ngayVaoLam = (JTextFieldDateEditor) dtcNgayVaoLam.getDateEditor();
        ngaySinh.setEnabled(false);
        ngayVaoLam.setEnabled(false);
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

        lblMaCongNhan.setText(prop.getProperty("maCongNhan"));
        lblHoVaTen.setText(prop.getProperty("hoTen"));
        lblSoCCCD.setText(prop.getProperty("soCCCD"));
        lblEmail.setText(prop.getProperty("email"));
        lblSoDienThoai.setText(prop.getProperty("sdt"));
        lblDiaChi.setText(prop.getProperty("diaChi"));
        lblNgaySinh.setText(prop.getProperty("ngaySinh"));
        lblGioiTinh.setText(prop.getProperty("gioiTinh"));
        lblToNhom.setText(prop.getProperty("toNhom"));
        lblNgayVaoLam.setText(prop.getProperty("ngayVaoLam"));
        lblAnhDaiDienOfPn.setText(prop.getProperty("anhDaiDien"));
        rdoNam.setText(prop.getProperty("nam"));
        rdoNu.setText(prop.getProperty("nu"));
        stCongNhan = prop.getProperty("congNhan");

        ChangeName(tblCongNhan, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblCongNhan, 1, lblMaCongNhan.getText());
        ChangeName(tblCongNhan, 2, lblHoVaTen.getText());
        ChangeName(tblCongNhan, 3, lblSoCCCD.getText());
        ChangeName(tblCongNhan, 4, lblGioiTinh.getText());
        ChangeName(tblCongNhan, 5, lblNgaySinh.getText());
        ChangeName(tblCongNhan, 6, lblSoDienThoai.getText());
        ChangeName(tblCongNhan, 7, lblDiaChi.getText());
        ChangeName(tblCongNhan, 8, lblAnhDaiDienOfPn.getText());
        ChangeName(tblCongNhan, 9, lblEmail.getText());
        ChangeName(tblCongNhan, 10, lblToNhom.getText());
        ChangeName(tblCongNhan, 11, lblNgayVaoLam.getText());

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
        stErrCongNhanKhongDuTuoi = prop.getProperty("congNhanChuaDuTuoi");
        stErrTienKhongHopLe = prop.getProperty("soTienKhongHople");
        stSoTienLonHonKhong = prop.getProperty("soTienLonHonKhong");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void excute() {

        // custom table
        tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongNhan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongNhan.setRowHeight(25);
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdoNam);
        btnGroup.add(rdoNu);
    }

    public void hienThiDuLieuLen() {
        cmbToNhom.removeAllItems();
        ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
        for (ToNhom toNhom : dsToNhom) {
            cmbToNhom.addItem(toNhom.getTenToNhom());
        }
        while (tblCongNhan.getRowCount() != 0) {
            modelTableCongNhan.removeRow(0);
        }
        ArrayList<CongNhan> dsCongNhan = congNhan_DAO.layDanhSachCongNhan();
        for (CongNhan congNhan : dsCongNhan) {
            String data[] = {(modelTableCongNhan.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen(),
                congNhan.getMaCCCD(), congNhan.isGioiTinh() ? "Nam" : "Nữ", congNhan.getNgaySinh().toString(), congNhan.getSoDienThoai(),
                congNhan.getDiaChi(), congNhan.getAnhDaiDien(), congNhan.getEmail(), congNhan.getToNhom().getTenToNhom(),
                congNhan.getNgayVaoLam().toString()};
            modelTableCongNhan.addRow(data);
        }
        if (tblCongNhan.getRowCount() != 0) {
            tblCongNhan.setRowSelectionInterval(0, 0);
            hienThiLenTxt(0);
        }
    }

    public void hienThiLenTxt(int row) {
        txtMaCongNhan.setText(tblCongNhan.getValueAt(row, 1).toString());
        txtHoTen.setText(tblCongNhan.getValueAt(row, 2).toString());
        txtCCCD.setText(tblCongNhan.getValueAt(row, 3).toString());
        if (tblCongNhan.getValueAt(row, 4).toString().equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        try {
            dtcNgaySinh.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblCongNhan.getValueAt(row, 5).toString()));
            dtcNgayVaoLam.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblCongNhan.getValueAt(row, 11).toString()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        txtSoDienThoai.setText(tblCongNhan.getValueAt(row, 6).toString());
        txtDiaChi.setText(tblCongNhan.getValueAt(row, 7).toString());
        lblAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/" + tblCongNhan.getValueAt(row, 8))));
        txtEmail.setText(tblCongNhan.getValueAt(row, 9).toString());
        cmbToNhom.setSelectedItem(tblCongNhan.getValueAt(row, 10));

    }

    public void dongMoTxt(Boolean b) {
        txtMaCongNhan.setEditable(false);
        txtHoTen.setEditable(b);
        txtCCCD.setEditable(b);
        txtEmail.setEditable(b);
        txtSoDienThoai.setEditable(b);
        txtDiaChi.setEditable(b);
        dtcNgaySinh.setEnabled(b);
        rdoNam.setEnabled(b);
        rdoNu.setEnabled(b);
        cmbToNhom.setEnabled(b);
        dtcNgayVaoLam.setEnabled(b);

    }

    public void dongMoBtnEdit(Boolean b) {
        btnAnhSanPham.setEnabled(b);
        btnCapNhat.setEnabled(b);
        btnThem.setEnabled(b);
        btnXoa.setEnabled(b);
        btnHuy.setEnabled(!b);
        btnLuu.setEnabled(!b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrTableCongNhan = new javax.swing.JScrollPane();
        tblCongNhan = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblAnhDaiDien = new javax.swing.JLabel();
        btnAnhSanPham = new javax.swing.JPanel();
        lblAnhDaiDienOfPn = new javax.swing.JLabel();
        lblErrHoTen = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        lblHoVaTen = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblErrEmail = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        lblErrCCCD = new javax.swing.JLabel();
        lblSoCCCD = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaCongNhan = new javax.swing.JTextField();
        lblMaCongNhan = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSoDienThoai = new javax.swing.JTextField();
        lblErrNgaySinh = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblErrDiaChi = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        lblGioiTinh = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        dtcNgaySinh = new com.toedter.calendar.JDateChooser();
        cmbToNhom = new javax.swing.JComboBox<>();
        lblSoDienThoai = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblErrSoDienThoai = new javax.swing.JLabel();
        lblToNhom = new javax.swing.JLabel();
        dtcNgayVaoLam = new com.toedter.calendar.JDateChooser();
        lblNgayVaoLam = new javax.swing.JLabel();
        lblErrNgayVaoLam = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        btnThemNhieu = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        scrTableCongNhan.setBackground(new java.awt.Color(255, 255, 255));
        scrTableCongNhan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã công nhân", "Họ và tên", "Sô CCCd", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Ảnh đại diện", "Email", "Tổ/Nhóm", "Ngày vào làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCongNhan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrTableCongNhan.setViewportView(tblCongNhan);

        add(scrTableCongNhan, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 500));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnhDaiDien.setBackground(new java.awt.Color(153, 0, 0));
        lblAnhDaiDien.setForeground(new java.awt.Color(255, 0, 51));
        lblAnhDaiDien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Anh/male.png"))); // NOI18N
        jPanel5.add(lblAnhDaiDien, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        lblAnhDaiDienOfPn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhDaiDienOfPn.setText("Ảnh đại diện");
        lblAnhDaiDienOfPn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhDaiDienOfPnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnAnhSanPhamLayout = new javax.swing.GroupLayout(btnAnhSanPham);
        btnAnhSanPham.setLayout(btnAnhSanPhamLayout);
        btnAnhSanPhamLayout.setHorizontalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhDaiDienOfPn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAnhSanPhamLayout.setVerticalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhDaiDienOfPn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.add(btnAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, -1, -1));

        lblErrHoTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHoTen.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHoTen.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 330, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________________________");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, 290, 20));

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtHoTen.setBorder(null);
        jPanel5.add(txtHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 270, 40));

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
        jPanel5.add(lblErrEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 330, -1));

        lblEmail.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblEmail.setText("Email:");
        jPanel5.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 100, 140, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("_______________________________");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 290, 20));

        txtCCCD.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtCCCD.setBorder(null);
        jPanel5.add(txtCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 270, 40));

        lblErrCCCD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrCCCD.setForeground(new java.awt.Color(204, 0, 0));
        lblErrCCCD.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 320, -1));

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

        lblErrNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgaySinh.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgaySinh.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 320, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("_______________________________");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 180, 290, 20));

        lblErrDiaChi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrDiaChi.setForeground(new java.awt.Color(204, 0, 0));
        lblErrDiaChi.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 330, -1));

        txtDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtDiaChi.setBorder(null);
        jPanel5.add(txtDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 160, 270, 40));

        lblGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblGioiTinh.setText("Giới tính");
        jPanel5.add(lblGioiTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 220, 140, 40));

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

        dtcNgaySinh.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dtcNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, 280, 40));

        cmbToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        cmbToNhom.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmbToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, 280, 40));

        lblSoDienThoai.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoDienThoai.setText("Số điện thoại:");
        jPanel5.add(lblSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 140, 40));

        lblNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNgaySinh.setText("Ngày sinh:");
        jPanel5.add(lblNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 140, 40));

        lblErrSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoDienThoai.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoDienThoai.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 320, -1));

        lblToNhom.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblToNhom.setText("Tổ/Nhóm");
        jPanel5.add(lblToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 130, 40));

        dtcNgayVaoLam.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dtcNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 290, 280, 40));

        lblNgayVaoLam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblNgayVaoLam.setText("Ngày vào làm:");
        jPanel5.add(lblNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 290, 140, 40));

        lblErrNgayVaoLam.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgayVaoLam.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgayVaoLam.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 330, 320, -1));

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
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 410, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 410, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 410, 160, 40));

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
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 410, 170, 40));

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
        jPanel5.add(btnThemNhieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 170, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void lblAnhDaiDienOfPnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhDaiDienOfPnMouseClicked
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
    }//GEN-LAST:event_lblAnhDaiDienOfPnMouseClicked

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void rdoNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNuActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        setEditTextDateChooser();      
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        setEditTextDateChooser();      
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnThemNhieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemNhieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemNhieuActionPerformed

    private void btnThemNhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemNhieuMouseClicked
        // TODO add your handling code here:
        File file = new File("./excelData");
        JFileChooser fileChooser = new JFileChooser(file.getAbsolutePath());
//        int respone = fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new File("../excelData"));
        fileChooser.removeChoosableFileFilter(fileChooser.getFileFilter());
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel File (.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int count = 0, total = 0;
        int respone = fileChooser.showSaveDialog(null);
        if (respone == JFileChooser.APPROVE_OPTION) {
            File inputFile = fileChooser.getSelectedFile();

            try (FileInputStream in = new FileInputStream(inputFile)) {
                XSSFWorkbook importedFile = new XSSFWorkbook(in);
                XSSFSheet sheet1 = importedFile.getSheetAt(0);
                Iterator<Row> rowIterator = sheet1.iterator();
                while (rowIterator.hasNext()) {
                    total++;
                    Row row = rowIterator.next();
                    Iterator<Cell> cellItera = row.cellIterator();
                    // khai báo biến 
                    try {
                        String hoTen = "", maCCCD = "", soDienThoai = "", email = "", anhDaiDien = "", diaChi = "";
                        String maToNhom = "";
                        Date ngaySinh = new Date(), ngayVaoLam = new Date();
                        Boolean gioiTinh = false;
                        while (cellItera.hasNext()) {
                            Cell cell = cellItera.next();
                            if (row.getRowNum() == 0) {
                                continue;
                            } else {
                                if (cell.getColumnIndex() == 0) {
                                    hoTen = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 1) {
                                    String chuoiNgaySinh = cell.getStringCellValue();
                                    try {
                                        ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiNgaySinh);
                                        System.out.println("Ngay Sinh" + ngaySinh);
                                    } catch (ParseException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                } else if (cell.getColumnIndex() == 2) {
                                    maCCCD = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 3) {
                                    soDienThoai = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 4) {
                                    email = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 5) {
                                    String gioiTinhStr = cell.getStringCellValue();
                                    if (gioiTinhStr.equalsIgnoreCase("Nữ") || gioiTinhStr.equalsIgnoreCase("Nu")) {
                                        gioiTinh = false;
                                    } else {
                                        gioiTinh = true;
                                    }
                                } else if (cell.getColumnIndex() == 6) {
                                    anhDaiDien = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 7) {
                                    diaChi = cell.getStringCellValue();
                                } else if (cell.getColumnIndex() == 8) {
                                    String chuoiNgayVaoLam = cell.getStringCellValue();
                                    try {
                                        ngayVaoLam = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiNgayVaoLam);
                                    } catch (ParseException ex) {
                                        System.out.println(ex.getMessage());
                                    }

                                } else if (cell.getColumnIndex() == 9) {
                                    maToNhom = cell.getStringCellValue();
                                }
                            }

                        }

                        String maCongNhan = congNhan_DAO.layRaMaCongNhanDeThem();
                        ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                        boolean coThemDuoc
                                = congNhan_DAO.themMotCongNhan(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD,
                                        soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
                        if (coThemDuoc) {
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                in.close();
                JOptionPane.showMessageDialog(null, stThemThanhCong + " " + count + " " + stTren + (--total) + stCongNhan);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, stKhongTimThayFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, stKhongDocDuocFile, stThongbao, JOptionPane.ERROR_MESSAGE);
            }
            if (count != 0) {
                try {
                    hienThiDuLieuLen();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnThemNhieuMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
       setEditTextDateChooser();      
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemNhieu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbToNhom;
    private com.toedter.calendar.JDateChooser dtcNgaySinh;
    private com.toedter.calendar.JDateChooser dtcNgayVaoLam;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblAnhDaiDien;
    private javax.swing.JLabel lblAnhDaiDienOfPn;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblErrCCCD;
    private javax.swing.JLabel lblErrDiaChi;
    private javax.swing.JLabel lblErrEmail;
    private javax.swing.JLabel lblErrHoTen;
    private javax.swing.JLabel lblErrNgaySinh;
    private javax.swing.JLabel lblErrNgayVaoLam;
    private javax.swing.JLabel lblErrSoDienThoai;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblMaCongNhan;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblNgayVaoLam;
    private javax.swing.JLabel lblSoCCCD;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblToNhom;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JScrollPane scrTableCongNhan;
    private javax.swing.JTable tblCongNhan;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaCongNhan;
    private javax.swing.JTextField txtSoDienThoai;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            dongMoBtnEdit(false);
            dongMoTxt(true);
            lblAnhDaiDienOfPn.setEnabled(true);
            txtHoTen.requestFocus();
            txtMaCongNhan.setText(congNhan_DAO.layRaMaCongNhanDeThem());
            xoaTrangTxt();
            oFlag = e.getSource();

        } else if (o.equals(btnCapNhat)) {
            oFlag = e.getSource();
            dongMoBtnEdit(false);
            dongMoTxt(true);
            lblAnhDaiDienOfPn.setEnabled(true);
            txtHoTen.requestFocus();

        } else if (o.equals(btnHuy)) {
            dongMoBtnEdit(true);
            dongMoTxt(false);
            lblAnhDaiDienOfPn.setEnabled(false);
            if (tblCongNhan.getRowCount() != 0) {
                hienThiLenTxt(0);
            }
            lblErrCCCD.setText("");
            lblErrDiaChi.setText("");
            lblErrEmail.setText("");
            lblErrHoTen.setText("");
            lblErrNgaySinh.setText("");
            lblErrNgayVaoLam.setText("");
            lblErrSoDienThoai.setText("");

        } else if (o.equals(btnLuu)) {
            if (oFlag.equals(btnThem)) {
                boolean coHopLe = validateForm();
                if (!coHopLe) {
                    return;
                }
                String hoTen = txtHoTen.getText().trim();
                String soCCCD = txtCCCD.getText().trim();
                String email = txtEmail.getText().trim();
                String soDienThoai = txtSoDienThoai.getText().trim();
                String diaChi = txtDiaChi.getText().trim();
                Date ngaySinh = dtcNgaySinh.getDate();
                Date ngayVaoLam = dtcNgayVaoLam.getDate();

                boolean isGioiTinh = rdoNam.isSelected() ? true : false;
                ToNhom toNhom = null;
                ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
                for (ToNhom toNhomTemp : dsToNhom) {
                    if (toNhomTemp.getTenToNhom().equalsIgnoreCase(cmbToNhom.getSelectedItem().toString())) {
                        toNhom = toNhomTemp;
                    }
                }
                boolean coThemDuoc = congNhan_DAO.themMotCongNhan(new CongNhan(txtMaCongNhan.getText(), hoTen, ngaySinh, soCCCD,
                        soDienThoai, email, ngayVaoLam, isGioiTinh, lblAnhDaiDien.getIcon().toString().split("Anh/")[1], diaChi, toNhom));
                if (coThemDuoc) {
                    JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    hienThiDuLieuLen();
                    dongMoBtnEdit(true);
                    dongMoTxt(false);
                    lblAnhDaiDienOfPn.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (oFlag.equals(btnCapNhat)) {
                boolean coHopLe = validateForm();
                if (!coHopLe) {
                    return;
                }
                String hoTen = txtHoTen.getText().trim();
                String soCCCD = txtCCCD.getText().trim();
                String email = txtEmail.getText().trim();
                String soDienThoai = txtSoDienThoai.getText().trim();
                String diaChi = txtDiaChi.getText().trim();
                Date ngaySinh = dtcNgaySinh.getDate();
                Date ngayVaoLam = dtcNgayVaoLam.getDate();
                boolean isGioiTinh = rdoNam.isSelected() ? true : false;
                ToNhom toNhom = null;
                ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
                for (ToNhom toNhomTemp : dsToNhom) {
                    if (toNhomTemp.getTenToNhom().equalsIgnoreCase(cmbToNhom.getSelectedItem().toString())) {
                        toNhom = toNhomTemp;
                    }
                }
                boolean coSuaDuoc = congNhan_DAO.capNhatMotCongNhan(new CongNhan(txtMaCongNhan.getText(), hoTen, ngaySinh, soCCCD,
                        soDienThoai, email, ngayVaoLam, isGioiTinh, lblAnhDaiDien.getIcon().toString().split("Anh/")[1], diaChi, toNhom));
                if (coSuaDuoc) {
                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    hienThiDuLieuLen();
                    dongMoBtnEdit(true);
                    dongMoTxt(false);
                    lblAnhDaiDienOfPn.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else if (o.equals(btnXoa)) {
            int row = tblCongNhan.getSelectedRow();
            if (row != -1) {
                int coXacNhanXoa = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.ERROR_MESSAGE);
                if (coXacNhanXoa == 0) {
                    boolean coXoaDuoc = congNhan_DAO.xoaCongNhanTheoMa(tblCongNhan.getValueAt(row, 1).toString());
                    if (coXoaDuoc) {
                        JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        hienThiDuLieuLen();
                    } else {
                        JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    public boolean validateForm() {
        boolean flag = true;
        String hoTen = txtHoTen.getText().trim();
        String soCCCD = txtCCCD.getText().trim();
        String email = txtEmail.getText().trim();
        String soDienThoai = txtSoDienThoai.getText().trim();
        String diaChi = txtDiaChi.getText().trim();
        Date ngaySinh = dtcNgaySinh.getDate();
        Date ngayVaoLam = dtcNgayVaoLam.getDate();

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
        if (soCCCD.equals("")) {
            lblErrCCCD.setText(stErrKhongDeTrong);
            flag = false;
        } else if (!soCCCD.matches("^[0-9]{12}$")) {
            lblErrCCCD.setText(stErrSoCCCD);
            flag = false;
        } else {
            lblErrCCCD.setText("");
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
            lblErrNgaySinh.setText(stErrCongNhanKhongDuTuoi);
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }
        if (ngayVaoLam.after(new Date())) {
            lblErrNgayVaoLam.setText(stErrNgayVaoLam);
            flag = false;
        } else {
            lblErrNgayVaoLam.setText("");
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

    public void xoaTrangTxt() {
        txtHoTen.setText("");
        txtCCCD.setText("");
        txtEmail.setText("");
        txtSoDienThoai.setText("");
        txtDiaChi.setText("");
        dtcNgaySinh.setDate(new Date());
        rdoNam.setSelected(true);
        if (cmbToNhom.getItemCount() != 0) {
            cmbToNhom.setSelectedIndex(0);
        }
        dtcNgayVaoLam.setDate(new Date());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblCongNhan)) {
            int row = tblCongNhan.getSelectedRow();
            if (row != -1) {
                hienThiLenTxt(row);
                dongMoTxt(false);
                dongMoBtnEdit(true);
                lblErrCCCD.setText("");
                lblErrDiaChi.setText("");
                lblErrEmail.setText("");
                lblErrHoTen.setText("");
                lblErrNgaySinh.setText("");
                lblErrNgayVaoLam.setText("");
                lblErrSoDienThoai.setText("");
                lblAnhDaiDienOfPn.setEnabled(false);
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
