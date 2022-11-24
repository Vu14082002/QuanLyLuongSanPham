/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongDoan_DAO;
import DAO.CongNhan_DAO;
import DAO.NhanVien_DAO;
import DAO.PhanCongCongNhan_DAO;
import DAO.SanPham_DAO;
import DAO.ToNhom_DAO;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.NhanVien;
import Entity.PhanCongCongNhan;
import Entity.SanPham;
import Entity.ToNhom;
import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class PhanCongCongViecView extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelDanhSachNhanVienCanChamCong;
    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelPhanCong;
    private SanPham_DAO daoSanPham;
    private CongDoan_DAO daoCongDoan;
    private CongNhan_DAO daoCongNhan;
    private PhanCongCongNhan_DAO daoPhanCong;
    private ToNhom_DAO daoToNhom;
    private NhanVien_DAO daoNhanVien;
    private boolean checkPhanCong = false;

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
    private String stErrChuSo;
    private String stErr1;
    private String stErr2;
    private String stErr3;

    public PhanCongCongViecView(String fileName) throws IOException {
        initComponents();
        caiDatNgonNguChoView(fileName);
        excute();
        taiDuLieuLenBangSanPham();
        dcsNgayPhanCong.setEnabled(false);
        btnPhanCong.setEnabled(false);
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        scrDanhSachSanPham.setBorder(new TitledBorder(prop.getProperty("pc_tieudeSanPham")));
        scrDanhSachPhanCong.setBorder(new TitledBorder(prop.getProperty("pc_tieuDeDanhSachPhanCong")));

        ChangeName(tblSanPham, 0, prop.getProperty("pc_stt"));
        ChangeName(tblSanPham, 1, prop.getProperty("pc_maSanPham"));
        ChangeName(tblSanPham, 2, prop.getProperty("pc_tenSanPham"));
        ChangeName(tblSanPham, 3, prop.getProperty("pc_soLuongCan"));

        ChangeName(tblPhanCong, 0, prop.getProperty("pc_stt"));
        ChangeName(tblPhanCong, 1, prop.getProperty("pc_maSanPham"));
        ChangeName(tblPhanCong, 2, prop.getProperty("pc_tenSanPham"));
        ChangeName(tblPhanCong, 3, prop.getProperty("pc_MaCongDoan"));
        ChangeName(tblPhanCong, 4, prop.getProperty("pc_TenCongDoan"));
        ChangeName(tblPhanCong, 5, prop.getProperty("pc_toNhom"));
        ChangeName(tblPhanCong, 6, prop.getProperty("pc_soLuongCan"));
        ChangeName(tblPhanCong, 7, prop.getProperty("pc_ngayPhanCong"));

        lblNgayPhanCong.setText(prop.getProperty("pc_ngayPhanCong"));
        lblMaPhanCong.setText(prop.getProperty("pc_maPhanCong"));
        lblMaCongDoan.setText(prop.getProperty("pc_MaCongDoan"));
        lblTenCongDoan.setText(prop.getProperty("pc_TenCongDoan"));
        lblSoLuongCanLam1.setText(prop.getProperty("pc_soLuongCan"));
        lblToNhom.setText(prop.getProperty("pc_toNhom"));

        btnPhanCong.setText(prop.getProperty("pc_btnPhanCong"));
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
        stSanPham = prop.getProperty("sp_SanPham");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stChonMauSacChoSanPham = prop.getProperty("sp_chonMauSacChoSanPham");
        stErrSoLuong = prop.getProperty("sp_lblErrSoLuong");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        stErrChuSo = prop.getProperty("pc_errKytuso");
        stErr1 = prop.getProperty("pc_err1");
        stErr2 = prop.getProperty("pc_err2");
        stErr3 = prop.getProperty("pc_err3");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void excute() {
        modelPhanCong = (DefaultTableModel) tblPhanCong.getModel();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        tblSanPham.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblSanPham.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblSanPham.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblSanPham.setRowHeight(25);
        tblPhanCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPhanCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblPhanCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblPhanCong.setRowHeight(25);
        setShow(btnPhanCong);
        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
        while (modelPhanCong.getRowCount() != 0) {
            modelPhanCong.removeRow(0);
        }
        ToNhom_DAO daoToNhom = new ToNhom_DAO();
        ArrayList<ToNhom> toNhomList = daoToNhom.layDanhSachToNhom();
        cmbToNhom.removeAllItems();
        toNhomList.forEach(e -> {
            if (e.getSoLuongCongNhan() > 0) {
                cmbToNhom.addItem(e.getTenToNhom());
            }
        });
        dcsNgayPhanCong.setDate(new Date());
        lblValueMaPhanCong.setText("");
        cmbMaCongDoan.removeAllItems();
        lblValueTenCongDoan.setText("");
        txtSoLuongCanLam.setText("");
        lblErrSoLuongCanLam.setText("");
    }

    public void taiDuLieuLenBangSanPham() {
        while (modelSanPham.getRowCount() != 0) {
            modelSanPham.removeRow(0);
        }
        daoSanPham = new SanPham_DAO();
        ArrayList<SanPham> listSanPham = daoSanPham.layDanhSachSanPham();
        daoCongDoan = new CongDoan_DAO();
        if (listSanPham != null) {
            listSanPham.forEach(e -> {
                ArrayList<CongDoan> cd = daoCongDoan.layDanhSachCongDoanTheoMaSP(e.getMaSanPham());
                if (cd.size() > 1) {
                    modelSanPham.addRow(new Object[]{modelSanPham.getRowCount() + 1, e.getMaSanPham(), e.getTenSanPham(), e.getSoLuongSanPham()});
                }
            });
        }

        setTblClick(false);
    }

    public void setHidden(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(false);
        }
    }

    public void setShow(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(true);
        }
    }

    public void taiDuLieuLenBangPhanCong() {
        while (modelPhanCong.getRowCount() != 0) {
            modelPhanCong.removeRow(0);
        }
        daoCongDoan = new CongDoan_DAO();
        daoPhanCong = new PhanCongCongNhan_DAO();
        daoSanPham = new SanPham_DAO();
        ArrayList<PhanCongCongNhan> listPhanCong = daoPhanCong.layDanhSachPhanCongCongNhan();
        SanPham sp = daoSanPham.layMotSanPhamTheoMa(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString());
        ArrayList<CongDoan> listCongDoan = daoCongDoan.layDanhSachCongDoanTheoMaSP(sp.getMaSanPham());
        cmbMaCongDoan.removeAllItems();
        listCongDoan.forEach(e -> {
            cmbMaCongDoan.addItem(e.getMaCongDoan());
        });
        if (listPhanCong != null) {
            for (PhanCongCongNhan e : listPhanCong) {
                if (e.getCongDoan().getSanPham().getMaSanPham().equals(sp.getMaSanPham())) {
                    modelPhanCong.addRow(new Object[]{e.getMaPhanCong(), e.getCongDoan().getSanPham().getMaSanPham(),
                        e.getCongDoan().getSanPham().getTenSanPham(), e.getCongDoan().getMaCongDoan(), e.getCongDoan().getTenCongDoan(),
                        e.getToNhom().getTenToNhom(), e.getSoLuongCanLam(), e.getNgayPhanCong()});
                }
            }
        }
        for (int i = 0; i < modelPhanCong.getRowCount(); i++) {
            for (int j = i + 1; j < modelPhanCong.getRowCount(); j++) {
                if (tblPhanCong.getValueAt(i, 5).toString().equalsIgnoreCase(tblPhanCong.getValueAt(j, 5).toString())
                        && tblPhanCong.getValueAt(i, 3).toString().equalsIgnoreCase(tblPhanCong.getValueAt(j, 3).toString())) {
                    modelPhanCong.removeRow(j);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        lblValueMaPhanCong = new javax.swing.JLabel();
        lblMaCongDoan = new javax.swing.JLabel();
        lblTenCongDoan = new javax.swing.JLabel();
        lblValueTenCongDoan = new javax.swing.JLabel();
        lblToNhom = new javax.swing.JLabel();
        lblNgayPhanCong = new javax.swing.JLabel();
        scrDanhSachSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cmbToNhom = new javax.swing.JComboBox<>();
        cmbMaCongDoan = new javax.swing.JComboBox<>();
        btnPhanCong = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblSoLuongCanLam1 = new javax.swing.JLabel();
        dcsNgayPhanCong = new com.toedter.calendar.JDateChooser();
        lblMaPhanCong = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtSoLuongCanLam = new javax.swing.JTextField();
        lblErrSoLuongCanLam = new javax.swing.JLabel();
        scrDanhSachPhanCong = new javax.swing.JScrollPane();
        tblPhanCong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblValueMaPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueMaPhanCong.setText("PC001");
        jPanel5.add(lblValueMaPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 180, 40));

        lblMaCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaCongDoan.setText("Mã công đoạn");
        jPanel5.add(lblMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 190, 40));

        lblTenCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenCongDoan.setText("Tên công đoạn");
        jPanel5.add(lblTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 190, 40));

        lblValueTenCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueTenCongDoan.setText("Rap giày");
        jPanel5.add(lblValueTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 160, 180, 40));

        lblToNhom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblToNhom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblToNhom.setText("Tổ/Nhóm thực hiện:");
        jPanel5.add(lblToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 290, 190, 40));

        lblNgayPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayPhanCong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayPhanCong.setText("Ngày phân công:");
        jPanel5.add(lblNgayPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 190, 40));

        scrDanhSachSanPham.setBackground(new java.awt.Color(255, 255, 255));
        scrDanhSachSanPham.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        scrDanhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                scrDanhSachSanPhamMouseClicked(evt);
            }
        });

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng cần"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        scrDanhSachSanPham.setViewportView(tblSanPham);

        jPanel5.add(scrDanhSachSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 660, 270));

        cmbToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Tổ 1", "Tổ 2", "Tổ 3", "Tổ 4", "Tổ 5", "Tổ 6", "Tổ 7", "Tổ 8", "Tổ 9", "Tổ 10"}));
        cmbToNhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbToNhomActionPerformed(evt);
            }
        });
        jPanel5.add(cmbToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 290, 140, 40));

        cmbMaCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"CD001", "CD002", "CD003"}));
        cmbMaCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaCongDoanActionPerformed(evt);
            }
        });
        jPanel5.add(cmbMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 140, 40));

        btnPhanCong.setBackground(new java.awt.Color(46, 204, 113));
        btnPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnPhanCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnPhanCong.setText("Phân công");
        btnPhanCong.setBorder(null);
        btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanCongActionPerformed(evt);
            }
        });
        jPanel5.add(btnPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 160, 40));

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
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 370, 170, 40));

        lblSoLuongCanLam1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongCanLam1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoLuongCanLam1.setText("Số lượng cần làm:");
        jPanel5.add(lblSoLuongCanLam1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 220, 170, 40));

        dcsNgayPhanCong.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcsNgayPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 180, 40));

        lblMaPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaPhanCong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaPhanCong.setText("Mã phân công:");
        jPanel5.add(lblMaPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 190, 40));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 250, 200, 10));

        txtSoLuongCanLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSoLuongCanLam.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel5.add(txtSoLuongCanLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 210, 180, 40));

        lblErrSoLuongCanLam.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoLuongCanLam.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoLuongCanLam.setText("day la dong thon bao loi");
        jPanel5.add(lblErrSoLuongCanLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 260, 200, -1));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scrDanhSachPhanCong.setBackground(new java.awt.Color(255, 255, 255));
        scrDanhSachPhanCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phân công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblPhanCong.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null},
                    {null, null, null, null, null, null, null, null}
                },
                new String[]{
                    "Mã phân công", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên  công đoạn", "Tổ/Nhóm", "Số lượng cần làm", "Ngày phân công"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblPhanCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanCongMouseClicked(evt);
            }
        });
        scrDanhSachPhanCong.setViewportView(tblPhanCong);

        add(scrDanhSachPhanCong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>                        

    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {
        setHidden(btnCapNhat, btnPhanCong, btnXoa);
        setShow(btnLuu, btnHuy);
        checkPhanCong = true;
        daoPhanCong = new PhanCongCongNhan_DAO();
        dcsNgayPhanCong.setEnabled(false);
        setTblClick(true);
        daoPhanCong = new PhanCongCongNhan_DAO();
        String maPhanCong = "";
        if (daoPhanCong.layDanhSachPhanCongCongNhan().size() < 1) {
            maPhanCong = "PC100001";
        } else {
            int maSo = Integer.parseInt(daoPhanCong.layDanhSachPhanCongCongNhan().get(daoPhanCong.layDanhSachPhanCongCongNhan().size() - 1).getMaPhanCong().split("C")[1]) + 1;
            maPhanCong = "PC" + maSo;
        }
        lblValueMaPhanCong.setText(maPhanCong);
        txtSoLuongCanLam.setText("");
    }

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {
        if (JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            daoPhanCong = new PhanCongCongNhan_DAO();
            daoToNhom = new ToNhom_DAO();
            ToNhom toNhom = daoToNhom.layMotToNhomTheoTen(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 5).toString());
            String maCongDoan = tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 3).toString();
            if (daoPhanCong.xoaMotPhanCongTheoMaToNhomVaMaCongDoan(toNhom.getMaToNhom(), maCongDoan)) {
                taiDuLieuLenBangPhanCong();
                if (modelPhanCong.getRowCount() != 0) {
                    tblPhanCong.setRowSelectionInterval(0, 0);
                }
                JOptionPane.showMessageDialog(null, stXoaThanhCong);
            }
        }
        setHidden(btnPhanCong, btnXoa, btnCapNhat, btnLuu, btnPhanCong);
    }

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {
        setTblClick(true);
        setShow(btnHuy, btnLuu);
        setHidden(btnCapNhat, btnXoa, btnPhanCong);
        dcsNgayPhanCong.setEnabled(false);
        cmbMaCongDoan.setEnabled(false);
        cmbToNhom.setEnabled(false);
        checkPhanCong=false;
    }

    public boolean checkSoLuongCanLam() {
        if (txtSoLuongCanLam.getText().trim().equals("")) {
            lblErrSoLuongCanLam.setText(stErrKhongDeTrong);
            return false;
        } else if (!txtSoLuongCanLam.getText().matches("^[1-9][0-9]*$")) {
            lblErrSoLuongCanLam.setText(stErrChuSo);
            return false;
        }
        lblErrSoLuongCanLam.setText("");
        return true;
    }

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {
        daoCongNhan = new CongNhan_DAO();
        daoToNhom = new ToNhom_DAO();
        daoNhanVien = new NhanVien_DAO();
        daoToNhom = new ToNhom_DAO();
        daoSanPham = new SanPham_DAO();
        daoCongDoan = new CongDoan_DAO();
        NhanVien nhanVienChamCong = daoNhanVien.layMotNhanVienTheoMaNhanVien("NV100001");
        ToNhom toNhom = daoToNhom.layMotToNhomTheoTen(cmbToNhom.getSelectedItem().toString());
        if (checkSoLuongCanLam()) {
            if (checkPhanCong) {
                ArrayList<CongNhan> congNhanList = daoCongNhan.layDanhSachCongNhanTheoMaTo(toNhom.getMaToNhom());
                String maCongDoan = cmbMaCongDoan.getSelectedItem().toString();
                CongDoan congDoan = daoCongDoan.layMotCongDoanTheoMaCongDoan(maCongDoan);
                Date ngayPhanCong = dcsNgayPhanCong.getDate();
                boolean check = false;
                if (daoPhanCong.checkDuocPhanCong(maCongDoan, toNhom.getMaToNhom())) {
                    for (CongNhan congNhanTheoToNhom : congNhanList) {
                        String maPhanCong = "";
                        if (daoPhanCong.layDanhSachPhanCongCongNhan().size() < 1) {
                            maPhanCong = "PC100001";
                        } else {
                            int maSo = Integer.parseInt(daoPhanCong.layDanhSachPhanCongCongNhan().get(daoPhanCong.layDanhSachPhanCongCongNhan().size() - 1).getMaPhanCong().split("C")[1]) + 1;
                            maPhanCong = "PC" + maSo;
                        }
                        if (checkSoLuongCanLam()) {
                            int soLuongCanLham = Integer.parseInt(txtSoLuongCanLam.getText());
                            PhanCongCongNhan phanCong = new PhanCongCongNhan(maPhanCong, congNhanTheoToNhom, congDoan, nhanVienChamCong, ngayPhanCong, soLuongCanLham, toNhom);
                            if (daoPhanCong.themMotPhanCongNhan(phanCong)) {
                                check = true;
                            }
                        }
                    }
                    if (check) {
                        taiDuLieuLenBangPhanCong();
                        JOptionPane.showMessageDialog(this, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setShow(btnPhanCong);
                        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
                        checkPhanCong = false;
                        dcsNgayPhanCong.setEnabled(false);
                        cmbMaCongDoan.setEnabled(false);
                        cmbToNhom.setEnabled(false);
                        txtSoLuongCanLam.setEditable(false);
                    } else {
                        JOptionPane.showMessageDialog(this, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        setShow(btnPhanCong);
                        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
                        checkPhanCong = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, stErr1 + congDoan.getMaCongDoan() + stErr2 + toNhom.getTenToNhom() + stErr3);
                }
            } else {
                daoPhanCong.suaMotPhanCongNhanTheoMaCongDoan(toNhom.getMaToNhom(), Integer.parseInt(txtSoLuongCanLam.getText()), cmbMaCongDoan.getSelectedItem().toString());
                taiDuLieuLenBangPhanCong();
                JOptionPane.showMessageDialog(this, stCapNhatThanhCong);
                setHidden(btnPhanCong, btnLuu, btnHuy,btnCapNhat,btnXoa);
            }
        }

    }

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {
        taiDuLieuLenBangPhanCong();
        setShow(btnPhanCong);
        setHidden(btnCapNhat, btnXoa, btnHuy, btnLuu);
        setTblClick(false);
        lblErrSoLuongCanLam.setText("");
    }

    private void cmbMaCongDoanActionPerformed(java.awt.event.ActionEvent evt) {
        daoCongDoan = new CongDoan_DAO();
        try {
            CongDoan cd = daoCongDoan.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());
            if (cd != null) {
                lblValueTenCongDoan.setText(cd.getTenCongDoan());
            }
        } catch (Exception e) {
        }
    }

    private void cmbToNhomActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    public void setTblClick(boolean kq) {
        dcsNgayPhanCong.setEnabled(kq);
        cmbMaCongDoan.setEnabled(kq);
        cmbToNhom.setEnabled(kq);
        txtSoLuongCanLam.setEditable(kq);
    }

    private void tblPhanCongMouseClicked(java.awt.event.MouseEvent evt) {
        lblErrSoLuongCanLam.setText("");
        ToNhom_DAO daoToNhom = new ToNhom_DAO();
        ArrayList<ToNhom> toNhomList = daoToNhom.layDanhSachToNhom();
        cmbToNhom.removeAllItems();
        toNhomList.forEach(e -> {
            if (e.getSoLuongCongNhan() > 0) {
                cmbToNhom.addItem(e.getTenToNhom());
            }
        });
        ArrayList<PhanCongCongNhan> listPhanCong = daoPhanCong.layDanhSachPhanCongCongNhan();
        SanPham sp = daoSanPham.layMotSanPhamTheoMa(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString());
        ArrayList<CongDoan> listCongDoan = daoCongDoan.layDanhSachCongDoanTheoMaSP(sp.getMaSanPham());
        cmbMaCongDoan.removeAllItems();
        for (CongDoan congDoan : listCongDoan) {
            cmbMaCongDoan.addItem(congDoan.getMaCongDoan());
        }
        lblValueMaPhanCong.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 0).toString());
        cmbMaCongDoan.setSelectedItem(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 3).toString());
        lblValueTenCongDoan.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 4).toString());
        txtSoLuongCanLam.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 6).toString());
        cmbToNhom.setSelectedItem(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 5).toString());
        setHidden(btnLuu, btnHuy, btnPhanCong);
        setShow(btnCapNhat, btnXoa);
        setTblClick(false);
    }

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {
        setShow(btnPhanCong);
        setHidden(btnHuy, btnLuu, btnCapNhat, btnXoa, btnPhanCong);
        setTblClick(false);
//        taiDuLieuLenBangSanPham();
        lblErrSoLuongCanLam.setText("");
        dcsNgayPhanCong.setEnabled(false);
        cmbMaCongDoan.setEnabled(false);
        cmbToNhom.setEnabled(false);
        txtSoLuongCanLam.setEditable(false);
        txtSoLuongCanLam.setText("");

    }

    private void scrDanhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {
        setHidden(btnCapNhat, btnHuy, btnLuu, btnCapNhat);
        setShow(btnPhanCong);
        setTblClick(false);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnPhanCong;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbMaCongDoan;
    private javax.swing.JComboBox<String> cmbToNhom;
    private com.toedter.calendar.JDateChooser dcsNgayPhanCong;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblErrSoLuongCanLam;
    private javax.swing.JLabel lblMaCongDoan;
    private javax.swing.JLabel lblMaPhanCong;
    private javax.swing.JLabel lblNgayPhanCong;
    private javax.swing.JLabel lblSoLuongCanLam1;
    private javax.swing.JLabel lblTenCongDoan;
    private javax.swing.JLabel lblToNhom;
    private javax.swing.JLabel lblValueMaPhanCong;
    private javax.swing.JLabel lblValueTenCongDoan;
    private javax.swing.JScrollPane scrDanhSachPhanCong;
    private javax.swing.JScrollPane scrDanhSachSanPham;
    private javax.swing.JTable tblPhanCong;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtSoLuongCanLam;
    // End of variables declaration                   
}
