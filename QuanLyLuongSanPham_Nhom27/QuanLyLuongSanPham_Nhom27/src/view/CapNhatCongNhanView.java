/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;


import DAO.CongNhan_DAO;
import DAO.ToNhom_DAO;
import Entity.CongNhan;
import Entity.HopDong;
import Entity.ToNhom;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

    /**
     * Creates new form NhanVienView
     */
    public CapNhatCongNhanView() {
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

    }

    public void excute() {

        // custom table
        tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongNhan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongNhan.setRowHeight(25);
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radNam);
        btnGroup.add(radNu);
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
            radNam.setSelected(true);
        } else {
            radNu.setSelected(true);
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
        radNam.setEnabled(b);
        radNu.setEnabled(b);
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
        lbAnhSanPhamOfbtn = new javax.swing.JLabel();
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
        radNam = new javax.swing.JRadioButton();
        radNu = new javax.swing.JRadioButton();
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
        btnThem1 = new javax.swing.JButton();

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

        lblErrHoTen.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHoTen.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHoTen.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 200, -1));

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
        jPanel5.add(lblErrEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 200, -1));

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
        jPanel5.add(lblErrCCCD, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 130, 200, -1));

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
        jPanel5.add(lblErrNgaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 200, -1));

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

        lblDiaChi.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblDiaChi.setText("Địa chỉ:");
        jPanel5.add(lblDiaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 160, 140, 40));

        radNam.setSelected(true);
        radNam.setText("Nam");
        radNam.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        radNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNamActionPerformed(evt);
            }
        });
        jPanel5.add(radNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 230, -1, -1));

        radNu.setText("Nữ");
        radNu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        radNu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radNuActionPerformed(evt);
            }
        });
        jPanel5.add(radNu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 230, -1, -1));

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
        jPanel5.add(lblErrSoDienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 200, -1));

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
        jPanel5.add(lblErrNgayVaoLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 330, 200, -1));

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
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 410, 170, 40));

        btnThem1.setBackground(new java.awt.Color(255, 234, 167));
        btnThem1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThem1.setText("Thêm nhiều");
        btnThem1.setBorder(null);
        btnThem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThem1MouseClicked(evt);
            }
        });
        btnThem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 170, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void lbAnhSanPhamOfbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAnhSanPhamOfbtnMouseClicked
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
    }//GEN-LAST:event_lbAnhSanPhamOfbtnMouseClicked

    private void radNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radNamActionPerformed

    private void radNuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radNuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radNuActionPerformed

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

    private void btnThem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThem1ActionPerformed

    private void btnThem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThem1MouseClicked
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
                        String hoTen = "", maCCCD = "", soDienThoai = "", email = "", matKhau = "111111", anhDaiDien = "", diaChi="";
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
                                    System.out.println("HoTen" + hoTen);
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
                                    System.out.println("Ma cccd" + maCCCD);
                                } else if (cell.getColumnIndex() == 3) {
                                   soDienThoai = cell.getStringCellValue();
                                    System.out.println("sdt" + soDienThoai);
                                } else if (cell.getColumnIndex() == 4) {
                                   email = cell.getStringCellValue();
                                    System.out.println("email" + email);
                                } else if (cell.getColumnIndex() == 5) {
                                    int matKhauInt = (int)cell.getNumericCellValue();
                                    matKhau = matKhauInt +"";
                                    System.out.println("mat khau" + matKhau);
                                } else if (cell.getColumnIndex() == 6) {
                                    String gioiTinhStr = cell.getStringCellValue();
                                    if (gioiTinhStr.equalsIgnoreCase("Nữ") || gioiTinhStr.equalsIgnoreCase("Nu")){
                                        gioiTinh = false;
                                    } else {
                                        gioiTinh = true;
                                    }
                                    System.out.println("gt" + gioiTinh);
                                } else if (cell.getColumnIndex() == 7) {
                                    anhDaiDien = cell.getStringCellValue();
                                    System.out.println("anh d d" + anhDaiDien);
                                } else if (cell.getColumnIndex() == 8) {
                                    diaChi = cell.getStringCellValue();
                                    System.out.println("dia chi" + diaChi);
                                } else if (cell.getColumnIndex() == 9) {
                                    String chuoiNgayVaoLam = cell.getStringCellValue();
                                    try {
                                        ngayVaoLam = new SimpleDateFormat("yyyy-MM-dd").parse(chuoiNgayVaoLam);
                                    } catch (ParseException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                    System.out.println("nvl " + ngayVaoLam);
                                } else if (cell.getColumnIndex()== 10){
                                    maToNhom = cell.getStringCellValue();
                                }
                            }

                        }
                        
                        String maCongNhan = congNhan_DAO.layRaMaCongNhanDeThem();
                        ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                        boolean coThemDuoc = 
                                congNhan_DAO.themMotCongNhan(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, 
                                        soDienThoai, email, matKhau, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
                        if (coThemDuoc) {
                            count++;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
                in.close();
                JOptionPane.showMessageDialog(null, "Thêm thành công " + count + " trên " + (--total) + " công nhân!");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi không tìm thấy file", "Thông báo", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi không đọc được file", "Thông báo", JOptionPane.ERROR_MESSAGE);
            }
            if (count != 0){
                try {
                    hienThiDuLieuLen();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnThem1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem1;
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
    private javax.swing.JLabel lbAnhSanPhamOfbtn;
    private javax.swing.JLabel lblAnhDaiDien;
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
    private javax.swing.JRadioButton radNam;
    private javax.swing.JRadioButton radNu;
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
            txtHoTen.requestFocus();
            txtMaCongNhan.setText(congNhan_DAO.layRaMaCongNhanDeThem());
            xoaTrangTxt();
            oFlag = e.getSource();

        } else if (o.equals(btnCapNhat)) {
            oFlag = e.getSource();
            dongMoBtnEdit(false);
            dongMoTxt(true);
            txtHoTen.requestFocus();
            
        } else if (o.equals(btnHuy)) {
            dongMoBtnEdit(true);
            dongMoTxt(false);
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
                String matKhau = "111111";
                boolean isGioiTinh = radNam.isSelected() ? true : false;
                ToNhom toNhom = null;
                ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
                for (ToNhom toNhomTemp : dsToNhom) {
                    if (toNhomTemp.getTenToNhom().equalsIgnoreCase(cmbToNhom.getSelectedItem().toString())) {
                        toNhom = toNhomTemp;
                    }
                }
                boolean coThemDuoc = congNhan_DAO.themMotCongNhan(new CongNhan(txtMaCongNhan.getText(), hoTen, ngaySinh, soCCCD,
                         soDienThoai, email, matKhau, ngayVaoLam, isGioiTinh, lblAnhDaiDien.getIcon().toString().split("Anh/")[1], diaChi, toNhom));
                if (coThemDuoc) {
                    JOptionPane.showMessageDialog(null, "Thêm công nhân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    hienThiDuLieuLen();
                    dongMoBtnEdit(true);
                    dongMoTxt(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm công nhân thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (oFlag.equals(btnCapNhat)){
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
                String matKhau = "111111";
                boolean isGioiTinh = radNam.isSelected() ? true : false;
                ToNhom toNhom = null;
                ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
                for (ToNhom toNhomTemp : dsToNhom) {
                    if (toNhomTemp.getTenToNhom().equalsIgnoreCase(cmbToNhom.getSelectedItem().toString())) {
                        toNhom = toNhomTemp;
                    }
                }
                boolean coSuaDuoc = congNhan_DAO.capNhatMotCongNhan(new CongNhan(txtMaCongNhan.getText(), hoTen, ngaySinh, soCCCD,
                         soDienThoai, email, matKhau, ngayVaoLam, isGioiTinh, lblAnhDaiDien.getIcon().toString().split("Anh/")[1], diaChi, toNhom));
                if (coSuaDuoc) {
                    JOptionPane.showMessageDialog(null, "Cập nhật công nhân thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    hienThiDuLieuLen();
                    dongMoBtnEdit(true);
                    dongMoTxt(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật công nhân thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        } else if (o.equals(btnXoa)) {
            int row = tblCongNhan.getSelectedRow();
            if (row != -1){
                 int coXacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa Phòng ban", JOptionPane.ERROR_MESSAGE);
                 if (coXacNhanXoa == 0){
                     boolean coXoaDuoc = congNhan_DAO.xoaCongNhanTheoMa(tblCongNhan.getValueAt(row, 1).toString());
                     if (coXoaDuoc){
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        hienThiDuLieuLen();
                     } else {
                          JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
            lblErrHoTen.setText("Họ Tên không được để trống!");
            flag = false;
        } else if (!hoTen.matches("^([A-ZĐÂÁƯ]{1}[a-zvxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)"
                + "((\\s{1}[A-ZĐÂÁƯ][{1}a-vxyỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            lblErrHoTen.setText("Bắt đầu là In hoa, chỉ chứa kí tự chữ");
            flag = false;
        } else {
            lblErrHoTen.setText("");
        }
        if (soCCCD.equals("")) {
            lblErrCCCD.setText("CCCD không được để trống!");
            flag = false;
        } else if (!soCCCD.matches("^[0-9]{12}$")) {
            lblErrCCCD.setText("Phải đúng 12 kí tự số");
            flag = false;
        } else {
            lblErrCCCD.setText("");
        }
        if (email.equals("")) {
            lblErrEmail.setText("Email không được để trống!");
            flag = false;
        } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            lblErrEmail.setText("Email không đúng chuẩn");
            flag = false;
        } else {
            lblErrEmail.setText("");
        }
        if (soDienThoai.equals("")) {
            lblErrSoDienThoai.setText("Số điện thoại không được trông!");
            flag = false;
        } else if (!soDienThoai.matches("^\\+84[1-9][0-9]{8}$")) {
            lblErrSoDienThoai.setText("Bắt đầu: +84, theo sau là 9 kí số");
            flag = false;
        } else {
            lblErrSoDienThoai.setText("");
        }
        if (diaChi.equals("")) {
            lblErrDiaChi.setText("Địa chỉ không được trống!");
            flag = false;
        } else {
            lblErrDiaChi.setText("");
        }
        if (!ngaySinh.before(new Date())) {
            lblErrNgaySinh.setText("Ngày sinh phải trước hiện tại");
            flag = false;
        } else if (calculateAgeWithJava7(ngaySinh, new Date()) < 18) {
            lblErrNgaySinh.setText("Phải từ 18 tuổi trở lên!");
            flag = false;
        } else {
            lblErrNgaySinh.setText("");
        }
        if (ngayVaoLam.after(new Date())) {
            lblErrNgayVaoLam.setText("Phải <= Ngày hiện tại");
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
        radNam.setSelected(true);
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
