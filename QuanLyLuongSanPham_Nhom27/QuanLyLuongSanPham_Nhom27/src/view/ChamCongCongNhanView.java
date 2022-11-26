/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.ChamCongCongNhan_DAO;
import DAO.CongDoan_DAO;
import DAO.PhanCongCongNhan_DAO;
import DAO.SanPham_DAO;
import DAO.ToNhom_DAO;
import Entity.ChamCongCongNhan;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.PhanCongCongNhan;
import Entity.SanPham;
import Entity.ToNhom;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
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
public class ChamCongCongNhanView extends javax.swing.JPanel implements ActionListener, MouseListener {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel modelTableCongNhan;
    private DefaultTableModel modelTableChamCong;
    private SanPham_DAO sanPham_DAO;
    private CongDoan_DAO congDoan_DAO;
    private ToNhom_DAO toNhom_DAO;
    private PhanCongCongNhan_DAO phanCong_DAO;
    private String maCongDoanFlag;
    private String caLamFlag;
    private Date ngayChamCongFlag;
    private ChamCongCongNhan_DAO chamCongCN_DAO;
    private ItemListener itemMaCongDoan, itemTenCongDoan, itemMaSanPham, itemTenSanPham;

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
    private String stChamCongThanhCong;
    private String stErrNgayChamCong;
    private String stErrBeHonTongSanPhamCongDoan;

    public ChamCongCongNhanView(String fileName) throws IOException {
        initComponents();
        excute();
        lblErrSoLuongSP.setText("");
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        maCongDoanFlag = "";
        caLamFlag = "";
        ngayChamCongFlag = new Date();
        btnChamCong.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnXoa.setEnabled(false);
        btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
        // add su kien cho cac button, table
        btnLayDanhSach.addActionListener(this);
        btnChamCong.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnLuu.addActionListener(this);
        btnXoa.addActionListener(this);
        btnHuy.addActionListener(this);
        tblCongNhan.addMouseListener(this);
        tblChamCong.addMouseListener(this);
        cmbHienThi.addItemListener(this::hienThiChamCongTheoYeuCau);

        // chinh lai txt hien thi
        lblMaCongNhanHienThi.setText("");
        lblHoVaTenHienThi.setText("");
        lblTenSanPhamOutput.setText("");
        lblTenCongDoanOutput.setText("");

        sanPham_DAO = new SanPham_DAO();
        congDoan_DAO = new CongDoan_DAO();
        phanCong_DAO = new PhanCongCongNhan_DAO();
        chamCongCN_DAO = new ChamCongCongNhan_DAO();
        toNhom_DAO = new ToNhom_DAO();
        modelTableChamCong = (DefaultTableModel) tblChamCong.getModel();
        modelTableCongNhan = (DefaultTableModel) tblCongNhan.getModel();
        dtcNgayChamCong.setDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dtcNgayChamCong.getDate());
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            setComBoBoxToSunDay();
        } else {
            setComBoBoxToReset();
        }

        taiDuLieuChamCongLenBang();
        itemMaSanPham = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cmbMaSanPham.getItemCount() != 0 && cmbTenSanPham.getItemCount() != 0) {
                        cmbTenSanPham.removeItemListener(itemTenSanPham);
                        cmbTenSanPham.setSelectedIndex(cmbMaSanPham.getSelectedIndex());
                        cmbTenSanPham.addItemListener(itemTenSanPham);
                        taiDuLieuLenCmbCongDoan();
                    }
                }
            }
        };
        itemTenSanPham = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (cmbMaSanPham.getItemCount() != 0 && cmbTenSanPham.getItemCount() != 0) {
                        cmbMaSanPham.removeItemListener(itemMaSanPham);
                        cmbMaSanPham.setSelectedIndex(cmbTenSanPham.getSelectedIndex());
                        cmbMaSanPham.addItemListener(itemMaSanPham);
                        taiDuLieuLenCmbCongDoan();
                    }
                }
            }
        };
        itemMaCongDoan = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {

                    if (cmbTenCongDoan.getItemCount() != 0) {
                        cmbTenCongDoan.removeItemListener(itemTenCongDoan);
                        cmbTenCongDoan.setSelectedIndex(cmbMaCongDoan.getSelectedIndex());
                        cmbTenCongDoan.addItemListener(itemTenCongDoan);
                    }

                }
            }
        };
        itemTenCongDoan = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    System.out.println("Ma" + cmbMaCongDoan.getItemCount());
                    if (cmbMaCongDoan.getItemCount() != 0) {
                        cmbMaCongDoan.removeItemListener(itemMaCongDoan);
                        cmbMaCongDoan.setSelectedIndex(cmbTenCongDoan.getSelectedIndex());
                        cmbMaCongDoan.addItemListener(itemMaCongDoan);
                    }

                }
            }
        };
        cmbMaCongDoan.addItemListener(itemMaCongDoan);
        cmbTenCongDoan.addItemListener(itemTenCongDoan);
        taiDuLieuLenCmbToNhom();
        //cmbTenCongDoan.addItemListener(this::comboBoxitemStateChangedTenCongDoan);
        cmbTenToNhom.addItemListener(this::comboBoxItemStateChangedToNhom);
        cmbMaSanPham.addItemListener(itemMaSanPham);
        cmbTenSanPham.addItemListener(itemTenSanPham);
        cmbCaLam.addItemListener(this::toggleThoiGianLam);
        cmbTrangThai.addItemListener(this::toggleCmbTrangThai);

        setCmbGio("Sáng");
        caiDatNgonNguChoView(fileName);

    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        btnCapNhat.setText(prop.getProperty("btnCapNhat"));
        btnLuu.setText(prop.getProperty("btnLuu"));
        btnHuy.setText(prop.getProperty("btnHuy"));
        btnXoa.setText(prop.getProperty("btnXoa"));
        btnChamCong.setText(prop.getProperty("chamCong_btnChamCong"));
        btnLayDanhSach.setText(prop.getProperty("cccc_layDanhSach"));

        lblToNhom.setText(prop.getProperty("cccc_toNhom"));
        lblNgayChamCong.setText(prop.getProperty("cccc_ngayCham"));
        lblMaSanPham.setText(prop.getProperty("cccc_maSanPham"));
        lblTenSanPham.setText(prop.getProperty("cccc_tenSanPham"));
        lblMaCongDoan.setText(prop.getProperty("cccc_maCongDoan"));
        lblTenCongDoan.setText(prop.getProperty("cccc_tenCongDoan"));
        lblMaCongNhan.setText(prop.getProperty("cccc_maCongNhan"));
        lblHoTen.setText(prop.getProperty("cccc_hoVaTen"));
        lblTrangThai.setText(prop.getProperty("cccc_trangThai"));
        lblCaLam.setText(prop.getProperty("cccc_caLam"));
        lblGioDiLam.setText(prop.getProperty("cccc_gioLam"));
        lblPhut.setText(prop.getProperty("cccc_phut"));
        lblTenSanPhamRight.setText(prop.getProperty("cccc_sanPham"));
        lblTenCongDoanRight.setText(prop.getProperty("cccc_congDoanLam"));
        lblSoLuongSanPham.setText(prop.getProperty("cccc_soLuongLam"));
        lblHienThi.setText(prop.getProperty("chamCong_hienThi"));

        scrTableCongNhan.setBorder(new TitledBorder(prop.getProperty("cccc_tieuDe1")));
        scrChamCong.setBorder(new TitledBorder(prop.getProperty("cccc_tieuDe2")));

        cmbHienThi.removeAllItems();
        cmbHienThi.addItem(prop.getProperty("cmbHienThiAll"));
        cmbHienThi.addItem(prop.getProperty("cmbHienThiLoc"));

        ChangeName(tblCongNhan, 0, prop.getProperty("cccc_stt"));
        ChangeName(tblCongNhan, 1, lblMaCongNhan.getText());
        ChangeName(tblCongNhan, 2, lblHoTen.getText());

        ChangeName(tblChamCong, 0, prop.getProperty("cccc_stt"));
        ChangeName(tblChamCong, 1, prop.getProperty("cccc_maPhanCong"));
        ChangeName(tblChamCong, 2, lblMaCongNhan.getText());
        ChangeName(tblChamCong, 3, lblHoTen.getText());
        ChangeName(tblChamCong, 4, lblToNhom.getText());
        ChangeName(tblChamCong, 5, lblNgayChamCong.getText());
        ChangeName(tblChamCong, 6, lblCaLam.getText());
        ChangeName(tblChamCong, 7, lblTrangThai.getText());
        ChangeName(tblChamCong, 8, lblGioDiLam.getText());
        ChangeName(tblChamCong, 9, lblMaSanPham.getText());
        ChangeName(tblChamCong, 10, lblTenSanPham.getText());
        ChangeName(tblChamCong, 11, lblMaCongDoan.getText());
        ChangeName(tblChamCong, 12, lblTenCongDoan.getText());
        ChangeName(tblChamCong, 13, lblSoLuongSanPham.getText());

        stErrBeHonTongSanPhamCongDoan = prop.getProperty("lblErrSoLuongLam");
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

    public void hienThiChamCongTheoYeuCau(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String item = e.getItem().toString();
            if (cmbHienThi.getSelectedIndex() == 0) {
                taiDuLieuChamCongLenBang();

            } else {
                hienThiChamCongTheoNgay();

            }
//            if (tblChamCong.getRowCount() != 0) {
//                if (tblCongNhan.getSelectedRow() != -1) {
//                    tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
//                }
//                tblChamCong.setRowSelectionInterval(0, 0);
//            }
        }
    }

    public void hienThiChamCongTheoNgay() {
        Date ngayChon = dtcNgayChamCong.getDate();
        while (tblChamCong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCongTheoNgay(ngayChon);
        for (ChamCongCongNhan cccn : dsChamCong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", cccn.getPhanCong().getMaPhanCong(), cccn.getPhanCong().getCongNhan().getMaCongNhan(),
                cccn.getPhanCong().getCongNhan().getHoTen(), cccn.getPhanCong().getCongNhan().getToNhom().getTenToNhom(),
                cccn.getNgayChamCong().toString(), cccn.getCaLam(), cccn.getTrangThaiDiLam(), cccn.getGioDiLam(), cccn.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
                cccn.getPhanCong().getCongDoan().getSanPham().getTenSanPham(),
                cccn.getPhanCong().getCongDoan().getMaCongDoan(), cccn.getPhanCong().getCongDoan().getTenCongDoan(), cccn.getSoLuongLam() + ""};
            modelTableChamCong.addRow(data);
        }

    }

    public void comboBoxItemStateChangedToNhom(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
//            cmbMaCongDoan.removeItemListener(itemMaCongDoan);
//            cmbTenCongDoan.removeItemListener(itemTenCongDoan);
//            String tenToNhom = e.getItem().toString();
//            ToNhom toNhom = toNhom_DAO.layMotToNhomTheoTen(tenToNhom);
//            taiDuLieuLenCmbCongDoan(toNhom.getMaToNhom());
//            cmbMaCongDoan.addItemListener(itemMaCongDoan);
//            cmbTenCongDoan.addItemListener(itemTenCongDoan);
            // String tenToNhom = e.getItem().toString();

            taiDuLieuLenCmbSanPham();

        }

    }

    public void taiDuLieuLenCmbSanPham() {
        cmbMaSanPham.removeItemListener(itemMaSanPham);
        cmbTenSanPham.removeItemListener(itemTenSanPham);
        String tenToNhom = cmbTenToNhom.getSelectedItem().toString();
        ToNhom toNhom = toNhom_DAO.layMotToNhomTheoTen(tenToNhom);
        CongDoan_DAO congDoanDao = new CongDoan_DAO();
        if (cmbMaSanPham.getItemCount() != 0 || cmbTenSanPham.getItemCount() != 0) {
            cmbMaSanPham.removeAllItems();
            cmbTenSanPham.removeAllItems();
        }
        ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPhamDuocPhanCongChoTo(toNhom.getMaToNhom());
        for (SanPham sp : dsSanPham) {
            cmbMaSanPham.addItem(sp.getMaSanPham());
            cmbTenSanPham.addItem(sp.getTenSanPham());
        }

        if (dsSanPham.size() == 0) {
            cmbMaCongDoan.removeAllItems();
            cmbTenCongDoan.removeAllItems();
            setEnAbleForCmb(false);
        } else {
            setEnAbleForCmb(true);
        }
        if (cmbMaSanPham.getItemCount() != 0) {
            taiDuLieuLenCmbCongDoan();
        }
        cmbMaSanPham.addItemListener(itemMaSanPham);
        cmbTenSanPham.addItemListener(itemTenSanPham);
    }

    public void setEnAbleForCmb(boolean kq) {
        cmbMaCongDoan.setEnabled(kq);
        cmbTenCongDoan.setEnabled(kq);
        cmbTenSanPham.setEnabled(kq);
        cmbMaSanPham.setEnabled(kq);
        cmbMaCongDoan.setEnabled(kq);
        cmbTenCongDoan.setEnabled(kq);
        btnLayDanhSach.setEnabled(kq);
    }

    public void comboBoxitemStateChangedTenCongDoan(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (cmbMaCongDoan.getItemCount() != 0) {
                cmbMaCongDoan.setSelectedIndex(cmbTenCongDoan.getSelectedIndex());
                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());

            }
        }

    }

//    public void comboBoxitemStateChangedCongDoan(ItemEvent e) {
//        if (e.getStateChange() == ItemEvent.SELECTED) {
//            System.out.println(cmbTenCongDoan.getItemCount());
//            if (cmbTenCongDoan.getItemCount() != 0) {
//                cmbTenCongDoan.setSelectedIndex(cmbMaCongDoan.getSelectedIndex());
//                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());
//                lblMaSanPhamTrai.setText(congDoan.getSanPham().getMaSanPham());
//                lblTenSanPhamTrai.setText(congDoan.getSanPham().getTenSanPham());
//            }
//        }
//    }
    public void toggleThoiGianLam(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getItem().toString().equalsIgnoreCase("Sáng") || e.getItem().toString().equalsIgnoreCase("Sáng chủ nhật")) {
                setCmbGio("Sáng");
            } else if (e.getItem().toString().equalsIgnoreCase("Chiều") || e.getItem().toString().equalsIgnoreCase("Chiều chủ nhật")) {
                setCmbGio("Chiều");
            } else {
                setCmbGio("Đêm");
            }

        }
    }

    public void taiDuLieuLenCmbToNhom() {
        cmbTenToNhom.removeItemListener(this::comboBoxItemStateChangedToNhom);
        cmbTenToNhom.removeAllItems();
        ArrayList<ToNhom> dsToNhom = toNhom_DAO.layDanhSachToNhom();
        for (ToNhom toNhom : dsToNhom) {
            if (toNhom.getSoLuongCongNhan() > 0) {
                cmbTenToNhom.addItem(toNhom.getTenToNhom());
            }
        }
        if (cmbTenToNhom.getItemCount() != 0) {
            ToNhom toNhom = toNhom_DAO.layMotToNhomTheoTen(cmbTenToNhom.getSelectedItem().toString());
            if (toNhom != null) {
                taiDuLieuLenCmbSanPham();
                //taiDuLieuLenCmbCongDoan(toNhom.getMaToNhom());
            }
        }
        cmbTenToNhom.addItemListener(this::comboBoxItemStateChangedToNhom);
    }

    public void setCmbGio(String ca) {
        if (ca.equalsIgnoreCase("Sáng")) {
            cmbGioDiLam.removeAllItems();
            for (int i = 6; i <= 14; i++) {
                cmbGioDiLam.addItem(i + "");
            }
        } else if (ca.equalsIgnoreCase("Chiều")) {
            cmbGioDiLam.removeAllItems();
            for (int i = 14; i <= 22; i++) {
                cmbGioDiLam.addItem(i + "");
            }
        } else {
            cmbGioDiLam.removeAllItems();
            cmbGioDiLam.addItem("22");
            cmbGioDiLam.addItem("23");
            cmbGioDiLam.addItem("24");
            cmbGioDiLam.addItem("1");
            cmbGioDiLam.addItem("2");
            cmbGioDiLam.addItem("3");
            cmbGioDiLam.addItem("4");
            cmbGioDiLam.addItem("5");
            cmbGioDiLam.addItem("6");
        }
    }

    public void toggleCmbTrangThai(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String trangThai = e.getItem().toString();
            if (trangThai.equalsIgnoreCase("Nghỉ có phép") || trangThai.equalsIgnoreCase("Nghỉ không phép")) {
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setText("0");
                txtSoLuongLam.setEditable(false);
                cmbGioDiLam.addItem("0");
                cmbGioDiLam.setSelectedItem("0");
                cmbPhutDiLam.setSelectedIndex(0);
            } else {
                cmbGioDiLam.removeItem("0");
                if (tblChamCong.getSelectedRow() != -1 && btnCapNhat.isEnabled()) {
                    cmbGioDiLam.setEnabled(false);
                    cmbPhutDiLam.setEnabled(false);
                    txtSoLuongLam.setEditable(false);
                } else if (!btnCapNhat.isEnabled() || tblCongNhan.getSelectedRow() != -1) {
                    cmbGioDiLam.setEnabled(true);
                    cmbPhutDiLam.setEnabled(true);
                    txtSoLuongLam.setEditable(true);
                }
            }

        }
    }

    public void excute() {
//        this.txtMaNhanVien.setText("");
//        this.txtMaNhanVien.setBackground(new Color(0, 0, 0, 1));

        // custom table
        tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongNhan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongNhan.setRowHeight(25);

        tblChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblChamCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblChamCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblChamCong.setRowHeight(25);
        this.txtSoLuongLam.setBackground(new Color(0, 0, 0, 1));
    }

    public void taiDuLieuLenCmbCongDoan() {
        cmbMaCongDoan.removeItemListener(itemMaCongDoan);
        cmbTenCongDoan.removeItemListener(itemTenCongDoan);
        cmbMaCongDoan.removeAllItems();
        cmbTenCongDoan.removeAllItems();
        String maToNhom = toNhom_DAO.layMotToNhomTheoTen(cmbTenToNhom.getSelectedItem().toString()).getMaToNhom();
        String maSanPham = cmbMaSanPham.getSelectedItem().toString();
        ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanDuocPhanCongTheoMaSpMaTN(maToNhom, maSanPham);
        for (CongDoan cd : dsCongDoan) {
            cmbMaCongDoan.addItem(cd.getMaCongDoan());
            cmbTenCongDoan.addItem(cd.getTenCongDoan());
        }
        if (cmbMaCongDoan.getItemCount() == 0 || cmbTenCongDoan.getItemCount() == 0){
            btnLayDanhSach.setEnabled(false);
            cmbMaCongDoan.setEnabled(false);
            cmbTenCongDoan.setEnabled(false);
        } else {
            btnLayDanhSach.setEnabled(true);
            cmbMaCongDoan.setEnabled(true);
            cmbTenCongDoan.setEnabled(true);
        }
//        ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanTheoToNhomLam(maToNhom);
//
//        for (CongDoan congDoan : dsCongDoan) {
//            cmbMaCongDoan.addItem(congDoan.getMaCongDoan());
//            cmbTenCongDoan.addItem(congDoan.getTenCongDoan());
//        }
//
//        if (cmbMaCongDoan.getItemCount() != 0) {
//            btnLayDanhSach.setEnabled(true);
//            CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());
//            lblMaSanPhamTrai.setText(congDoan.getSanPham().getMaSanPham());
//            lblTenSanPhamTrai.setText(congDoan.getSanPham().getTenSanPham());
//        } else {
//            btnLayDanhSach.setEnabled(false);
//            lblMaSanPhamTrai.setText("");
//            lblTenSanPhamTrai.setText("");
//        }
        cmbMaCongDoan.addItemListener(itemMaCongDoan);
        cmbTenCongDoan.addItemListener(itemTenCongDoan);
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
        dtcNgayChamCong = new com.toedter.calendar.JDateChooser();
        scrTableCongNhan = new javax.swing.JScrollPane();
        tblCongNhan = new javax.swing.JTable();
        lblNgayChamCong = new javax.swing.JLabel();
        lblSoLuongSanPham = new javax.swing.JLabel();
        lblMaCongNhanHienThi = new javax.swing.JLabel();
        lblHoVaTenHienThi = new javax.swing.JLabel();
        lblMaCongNhan = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblTenCongDoanRight = new javax.swing.JLabel();
        lblTenSanPhamRight = new javax.swing.JLabel();
        lblTenSanPhamOutput = new javax.swing.JLabel();
        lblTenCongDoanOutput = new javax.swing.JLabel();
        txtSoLuongLam = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblErrSoLuongSP = new javax.swing.JLabel();
        cmbMaCongDoan = new javax.swing.JComboBox<>();
        lblTrangThai = new javax.swing.JLabel();
        lblMaCongDoan = new javax.swing.JLabel();
        cmbCaLam = new javax.swing.JComboBox<>();
        cmbGioDiLam = new javax.swing.JComboBox<>();
        lblGioDiLam = new javax.swing.JLabel();
        lblPhut = new javax.swing.JLabel();
        cmbPhutDiLam = new javax.swing.JComboBox<>();
        cmbTrangThai = new javax.swing.JComboBox<>();
        lblCaLam = new javax.swing.JLabel();
        lblMaSanPham = new javax.swing.JLabel();
        btnLayDanhSach = new javax.swing.JButton();
        lblTenSanPham = new javax.swing.JLabel();
        cmbTenCongDoan = new javax.swing.JComboBox<>();
        lblTenCongDoan = new javax.swing.JLabel();
        lblHienThi = new javax.swing.JLabel();
        cmbHienThi = new javax.swing.JComboBox<>();
        cmbTenToNhom = new javax.swing.JComboBox<>();
        lblToNhom = new javax.swing.JLabel();
        btnChamCong = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        cmbMaSanPham = new javax.swing.JComboBox<>();
        cmbTenSanPham = new javax.swing.JComboBox<>();
        scrChamCong = new javax.swing.JScrollPane();
        tblChamCong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dtcNgayChamCong.setDateFormatString("yyyy-MM-dd");
        dtcNgayChamCong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcNgayChamCongPropertyChange(evt);
            }
        });
        jPanel5.add(dtcNgayChamCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 330, 40));

        scrTableCongNhan.setBackground(new java.awt.Color(255, 255, 255));
        scrTableCongNhan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã công nhân", "Họ và tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCongNhan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrTableCongNhan.setViewportView(tblCongNhan);

        jPanel5.add(scrTableCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 750, 180));

        lblNgayChamCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayChamCong.setText("Ngày chấm công:");
        jPanel5.add(lblNgayChamCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 130, 40));

        lblSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongSanPham.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoLuongSanPham.setText("Số lượng sản phẩm:");
        jPanel5.add(lblSoLuongSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 300, 210, 40));

        lblMaCongNhanHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongNhanHienThi.setText("NV001");
        jPanel5.add(lblMaCongNhanHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 180, 40));

        lblHoVaTenHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoVaTenHienThi.setText("Nguyễn Văn A");
        jPanel5.add(lblHoVaTenHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 50, 180, 30));

        lblMaCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaCongNhan.setText("Mã công nhân");
        jPanel5.add(lblMaCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 200, 40));

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHoTen.setText("Họ và tên:");
        jPanel5.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 190, 30));

        lblTenCongDoanRight.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoanRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenCongDoanRight.setText("Công đoạn làm:");
        jPanel5.add(lblTenCongDoanRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 260, 190, 30));

        lblTenSanPhamRight.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSanPhamRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenSanPhamRight.setText("Sản phẩm:");
        jPanel5.add(lblTenSanPhamRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 220, 170, 30));

        lblTenSanPhamOutput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSanPhamOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSanPhamOutput.setText("Giày loại 1");
        jPanel5.add(lblTenSanPhamOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 220, 180, 30));

        lblTenCongDoanOutput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoanOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCongDoanOutput.setText("Sơn màu");
        jPanel5.add(lblTenCongDoanOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 260, 180, 30));

        txtSoLuongLam.setText("0");
        txtSoLuongLam.setBorder(null);
        jPanel5.add(txtSoLuongLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 310, 180, 20));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 330, 180, 10));

        lblErrSoLuongSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoLuongSP.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoLuongSP.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoLuongSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 330, 190, -1));

        cmbMaCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cd001" }));
        cmbMaCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaCongDoanActionPerformed(evt);
            }
        });
        jPanel5.add(cmbMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 140, 120, 40));

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTrangThai.setText("Trạng thái:");
        jPanel5.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 200, 40));

        lblMaCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaCongDoan.setText("Mã công đoạn:");
        jPanel5.add(lblMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 40));

        cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 01", "Ca 02", "Ca 03", "Chủ nhật" }));
        jPanel5.add(cmbCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 130, 180, 30));

        cmbGioDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbGioDiLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "24" }));
        jPanel5.add(cmbGioDiLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 180, 60, 30));

        lblGioDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGioDiLam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGioDiLam.setText("Giờ đi làm:");
        jPanel5.add(lblGioDiLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 160, 30));

        lblPhut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPhut.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPhut.setText("Phút:");
        jPanel5.add(lblPhut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 180, 40, 30));

        cmbPhutDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbPhutDiLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        jPanel5.add(cmbPhutDiLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 180, 60, -1));

        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi Làm", "Đi Trễ", "Nghỉ Không Phép", "Nghỉ Có Phép" }));
        jPanel5.add(cmbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 90, 180, 30));

        lblCaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCaLam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCaLam.setText("Ca làm:");
        lblCaLam.setToolTipText("");
        jPanel5.add(lblCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 130, 190, 30));

        lblMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaSanPham.setText("Mã sản phẩm:");
        jPanel5.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 30));

        btnLayDanhSach.setBackground(new java.awt.Color(46, 204, 113));
        btnLayDanhSach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLayDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnLayDanhSach.setText("Lấy danh sách");
        btnLayDanhSach.setBorder(null);
        btnLayDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayDanhSachActionPerformed(evt);
            }
        });
        jPanel5.add(btnLayDanhSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 140, 150, 40));

        lblTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSanPham.setText("Tên sản phẩm:");
        jPanel5.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 120, 30));

        cmbTenCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rap giày" }));
        jPanel5.add(cmbTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 140, 160, 40));

        lblTenCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCongDoan.setText("Công đoạn:");
        jPanel5.add(lblTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 120, 40));

        lblHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHienThi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHienThi.setText("Hiển thị");
        jPanel5.add(lblHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 60, 30));

        cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo ngày chấm công" }));
        cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHienThiActionPerformed(evt);
            }
        });
        jPanel5.add(cmbHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 400, 170, 40));

        cmbTenToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP001", " " }));
        cmbTenToNhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTenToNhomActionPerformed(evt);
            }
        });
        jPanel5.add(cmbTenToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 120, 40));

        lblToNhom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblToNhom.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblToNhom.setText("Tổ/nhóm:");
        jPanel5.add(lblToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, 40));

        btnChamCong.setBackground(new java.awt.Color(46, 204, 113));
        btnChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnChamCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnChamCong.setText("Chấm công");
        btnChamCong.setBorder(null);
        btnChamCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongActionPerformed(evt);
            }
        });
        jPanel5.add(btnChamCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 400, 160, 40));

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
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 400, 150, 40));

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
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 400, 150, 40));

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
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 400, 140, 40));

        btnHuy.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnHuy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/delete.png"))); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.setMaximumSize(new java.awt.Dimension(67, 31));
        btnHuy.setMinimumSize(new java.awt.Dimension(67, 31));
        btnHuy.setPreferredSize(new java.awt.Dimension(67, 31));
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 400, 150, 40));

        cmbMaSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cd001" }));
        cmbMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaSanPhamActionPerformed(evt);
            }
        });
        jPanel5.add(cmbMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 120, 40));

        cmbTenSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rap giày" }));
        cmbTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTenSanPhamActionPerformed(evt);
            }
        });
        jPanel5.add(cmbTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 330, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scrChamCong.setBackground(new java.awt.Color(255, 255, 255));
        scrChamCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Phân công", "Mã công nhân", "Họ và tên", "Tổ/Nhóm", "Ngày chấm công", "Ca làm", "Trạng thái", "Giờ đi làm", "Mã sản phẩm", "Sản phẩm", "Mã công đoạn", "Công đoạn", "Số lượng làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChamCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrChamCong.setViewportView(tblChamCong);

        add(scrChamCong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLayDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayDanhSachActionPerformed
        btnChamCong.setEnabled(true);
        lblTenSanPhamOutput.setText(cmbTenSanPham.getSelectedItem().toString());
        lblTenCongDoanOutput.setText(cmbTenCongDoan.getSelectedItem().toString());
    }//GEN-LAST:event_btnLayDanhSachActionPerformed

    private void dtcNgayChamCongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcNgayChamCongPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtcNgayChamCong.getDate());
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                setComBoBoxToSunDay();
            } else {
                setComBoBoxToReset();
            }
        }
        Date ngayChon = dtcNgayChamCong.getDate();
        if (ngayChon.after(new Date())) {
            JOptionPane.showMessageDialog(null, stErrNgayChamCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            dtcNgayChamCong.setDate(new Date());
        }
    }//GEN-LAST:event_dtcNgayChamCongPropertyChange

    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHienThiActionPerformed

    }//GEN-LAST:event_cmbHienThiActionPerformed

    private void btnChamCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChamCongActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void cmbTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTenSanPhamActionPerformed

    private void cmbTenToNhomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTenToNhomActionPerformed
        btnChamCong.setEnabled(false);
    }//GEN-LAST:event_cmbTenToNhomActionPerformed

    private void cmbMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaSanPhamActionPerformed
        btnChamCong.setEnabled(false);

    }//GEN-LAST:event_cmbMaSanPhamActionPerformed

    private void cmbMaCongDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaCongDoanActionPerformed
        btnChamCong.setEnabled(false);

    }//GEN-LAST:event_cmbMaCongDoanActionPerformed

    public void setComBoBoxToSunDay() {
        cmbCaLam.removeAllItems();
        cmbCaLam.addItem("Sáng chủ nhật");
        cmbCaLam.addItem("Chiều chủ nhật");
    }

    public void setComBoBoxToReset() {
        cmbCaLam.removeAllItems();
        cmbCaLam.addItem("Sáng");
        cmbCaLam.addItem("Chiều");
        cmbCaLam.addItem("Đêm");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChamCong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLayDanhSach;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbCaLam;
    private javax.swing.JComboBox<String> cmbGioDiLam;
    private javax.swing.JComboBox<String> cmbHienThi;
    private javax.swing.JComboBox<String> cmbMaCongDoan;
    private javax.swing.JComboBox<String> cmbMaSanPham;
    private javax.swing.JComboBox<String> cmbPhutDiLam;
    private javax.swing.JComboBox<String> cmbTenCongDoan;
    private javax.swing.JComboBox<String> cmbTenSanPham;
    private javax.swing.JComboBox<String> cmbTenToNhom;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private com.toedter.calendar.JDateChooser dtcNgayChamCong;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblErrSoLuongSP;
    private javax.swing.JLabel lblGioDiLam;
    private javax.swing.JLabel lblHienThi;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblHoVaTenHienThi;
    private javax.swing.JLabel lblMaCongDoan;
    private javax.swing.JLabel lblMaCongNhan;
    private javax.swing.JLabel lblMaCongNhanHienThi;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblNgayChamCong;
    private javax.swing.JLabel lblPhut;
    private javax.swing.JLabel lblSoLuongSanPham;
    private javax.swing.JLabel lblTenCongDoan;
    private javax.swing.JLabel lblTenCongDoanOutput;
    private javax.swing.JLabel lblTenCongDoanRight;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTenSanPhamOutput;
    private javax.swing.JLabel lblTenSanPhamRight;
    private javax.swing.JLabel lblToNhom;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JScrollPane scrChamCong;
    private javax.swing.JScrollPane scrTableCongNhan;
    private javax.swing.JTable tblChamCong;
    private javax.swing.JTable tblCongNhan;
    private javax.swing.JTextField txtSoLuongLam;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLayDanhSach)) {
            taiDuLieuLenBangCongNhan();
            cmbCaLam.setEnabled(true);
            cmbTrangThai.setEnabled(true);
            cmbGioDiLam.setEnabled(true);
            cmbPhutDiLam.setEnabled(true);
            txtSoLuongLam.setEditable(true);
            btnCapNhat.setEnabled(false);
            btnHuy.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(false);
            cmbTrangThai.setSelectedIndex(0);
            if (tblCongNhan.getRowCount() == 0) {
                lblMaCongNhanHienThi.setText("");
                lblHoVaTenHienThi.setText("");
                lblTenCongDoanOutput.setText("");
                lblTenCongDoanOutput.setText("");
                txtSoLuongLam.setText("");
                btnChamCong.setEnabled(true);
            }
            maCongDoanFlag = cmbMaCongDoan.getSelectedItem().toString();
            caLamFlag = cmbCaLam.getSelectedItem().toString();
            ngayChamCongFlag = dtcNgayChamCong.getDate();

            lblTenCongDoanOutput.setText(cmbTenCongDoan.getSelectedItem().toString());
        } else if (o.equals(btnChamCong)) {
            boolean isValid = validateForm();
            if (!isValid) {
                return;
            }
            if (lblMaCongNhanHienThi.getText().equals("")) {
                return;
            }
            String maPhanCong = phanCong_DAO.layRaMaPhanCongTheoMaCongDoanMaCongNhan(maCongDoanFlag, lblMaCongNhanHienThi.getText().trim());
            PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
            String gioDiLam = cmbGioDiLam.getSelectedItem().toString() + "h" + cmbPhutDiLam.getSelectedItem().toString();
            if (cmbTrangThai.getSelectedIndex() >= 2) {
                gioDiLam = "";
            }
            int soLuongLam = Integer.parseInt(txtSoLuongLam.getText().trim());
            boolean chamDuoc = chamCongCN_DAO
                    .themMotChamCongCongNhan(new ChamCongCongNhan(phanCong, cmbCaLam.getSelectedItem().toString(), ngayChamCongFlag, soLuongLam, cmbTrangThai.getSelectedItem().toString(), gioDiLam));
            if (chamDuoc) {
                JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                taiDuLieuChamCongLenBang();
                if (tblChamCong.getRowCount() != 0) {
                    if (tblCongNhan.getSelectedRow() != -1) {
                        tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                    }
                    tblChamCong.setRowSelectionInterval(0, 0);
                }
                congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();
            } else {
                JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (o.equals(btnCapNhat)) {
            cmbCaLam.setEnabled(false);
            cmbTrangThai.setEnabled(true);
            if (cmbTrangThai.getSelectedIndex() >= 2) {
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setEditable(false);
            } else {
                cmbGioDiLam.setEnabled(true);
                cmbPhutDiLam.setEnabled(true);
                txtSoLuongLam.setEditable(true);
            }
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
        } else if (o.equals(btnHuy)) {
            cmbCaLam.setEnabled(false);
            cmbTrangThai.setEnabled(false);
            cmbGioDiLam.setEnabled(false);
            cmbPhutDiLam.setEnabled(false);
            txtSoLuongLam.setEditable(false);
            btnCapNhat.setEnabled(true);
            btnXoa.setEnabled(true);
            btnLuu.setEnabled(false);
            btnHuy.setEnabled(false);
            lblErrSoLuongSP.setText("");
        } else if (o.equals(btnLuu)) {
            boolean isHople = validateForm();
            if (!isHople) {
                return;
            }
            // 1, 6, 5
            String maPhanCong = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 1).toString();
            PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
            String caLam = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 6).toString();
            String ngayChamCongStr = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 5).toString();
            Date ngayChamCong = new Date();
            try {
                ngayChamCong = new SimpleDateFormat("yyyy-MM-dd").parse(ngayChamCongStr);
            } catch (ParseException ex) {
                System.out.println("đổi chuỗi ngày sang object date ngày lỗi tại view cham cong nhan vien");
            }
            int soLuongLam = Integer.parseInt(txtSoLuongLam.getText().trim());
            String trangThaiDiLam = cmbTrangThai.getSelectedItem().toString();
            String gioDiLam = cmbGioDiLam.getSelectedItem().toString() + "h" + cmbPhutDiLam.getSelectedItem().toString();
            if (cmbTrangThai.getSelectedIndex() >= 2) {
                gioDiLam = "";
            }
            ChamCongCongNhan cccn = new ChamCongCongNhan(phanCong, caLam, ngayChamCong, soLuongLam, trangThaiDiLam, gioDiLam);
            boolean isCapNhat = chamCongCN_DAO.suaMotChamCongCongNhan(cccn);
            if (isCapNhat) {
                taiDuLieuChamCongLenBang();
                if (tblChamCong.getRowCount() != 0) {
                    if (tblCongNhan.getSelectedRow() != -1) {
                        tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                    }
                    tblChamCong.setRowSelectionInterval(0, 0);
                }
                JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();
                cmbCaLam.setEnabled(false);
                cmbTrangThai.setEnabled(false);
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                txtSoLuongLam.setEditable(false);
                btnCapNhat.setEnabled(true);
                btnXoa.setEnabled(true);
                btnLuu.setEnabled(false);
                btnHuy.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (o.equals(btnXoa)) {
            int rowChamCongSelect = tblChamCong.getSelectedRow();
            if (rowChamCongSelect != -1) {
                int isAccept = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                if (isAccept == 0) {
                    String maPhanCong = tblChamCong.getValueAt(rowChamCongSelect, 1).toString();
                    String caLam = tblChamCong.getValueAt(rowChamCongSelect, 6).toString();
                    String ngayChamCongStr = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 5).toString();
                    Date ngayChamCong = new Date();
                    try {
                        ngayChamCong = new SimpleDateFormat("yyyy-MM-dd").parse(ngayChamCongStr);
                    } catch (ParseException ex) {
                        System.out.println("đổi chuỗi ngày sang object date ngày lỗi tại view cham cong nhan vien");
                    }
                    boolean coXoaDuoc = chamCongCN_DAO.xoaChamCongCongNhanTheoMa(maPhanCong, caLam, ngayChamCong);
                    if (coXoaDuoc) {
                        JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        taiDuLieuChamCongLenBang();
                        congDoan_DAO.updateTinhTrangHoanThanhCuaCacCongDoan();
                        if (tblChamCong.getRowCount() != 0) {
                            if (tblCongNhan.getSelectedRow() != -1) {
                                tblCongNhan.removeRowSelectionInterval(tblCongNhan.getSelectedRow(), tblCongNhan.getSelectedRow());
                            }
                            tblChamCong.setRowSelectionInterval(0, 0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, stXoaThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
    }

    public boolean validateForm() {
        if (txtSoLuongLam.getText().trim().equals("")) {
            lblErrSoLuongSP.setText(stErrKhongDeTrong);
            return false;
        } 
        int soLuong = -1;
        try {
            soLuong = Integer.parseInt(txtSoLuongLam.getText().trim());
        } catch (Exception e) {
            lblErrSoLuongSP.setText(stErrSoLuong);
            return false;
        }
        if (soLuong < 0) {
            lblErrSoLuongSP.setText(stErrSoLuong);
            return false;
        }
        ArrayList<PhanCongCongNhan> phanCongDS = phanCong_DAO.layDanhSachPhanCongTheoMaCongDoan(maCongDoanFlag);
        if (phanCongDS.size() > 0){
            int soLuongLamCuaPhanCong = phanCongDS.get(0).getSoLuongCanLam();
            if (soLuong > soLuongLamCuaPhanCong){
                lblErrSoLuongSP.setText(stErrBeHonTongSanPhamCongDoan);
                return false;
            }
        }
        lblErrSoLuongSP.setText("");
        return true;
    }

    public void taiDuLieuLenBangCongNhan() {
        while (tblCongNhan.getRowCount() != 0) {
            modelTableCongNhan.removeRow(0);
        }
        if (cmbMaCongDoan.getItemCount() == 0 || cmbCaLam.getItemCount() == 0) {
            return;
        }
        String maCongDoan = cmbMaCongDoan.getSelectedItem().toString();
        String maToNhom = toNhom_DAO.layMotToNhomTheoTen(cmbTenToNhom.getSelectedItem().toString()).getMaToNhom();
        ArrayList<CongNhan> dsCongNhan = phanCong_DAO.layRaDanhSachCongNhanTheoCongDoanVaToNhom(maCongDoan, maToNhom);
        for (CongNhan congNhan : dsCongNhan) {
            String data[] = {(modelTableCongNhan.getRowCount() + 1) + "", congNhan.getMaCongNhan(), congNhan.getHoTen()};
            modelTableCongNhan.addRow(data);
        }
        if (modelTableCongNhan.getRowCount() != 0) {
            tblCongNhan.setRowSelectionInterval(0, 0);
            btnChamCong.setEnabled(true);
            int rowSelectTblChamCong = tblChamCong.getSelectedRow();
            if (rowSelectTblChamCong != -1) {
                tblChamCong.removeRowSelectionInterval(rowSelectTblChamCong, rowSelectTblChamCong);
            }
            hienThiThongTinCongNhanLenTxt(0);
        }

    }

    public void hienThiThongTinCongNhanLenTxt(int row) {
        lblMaCongNhanHienThi.setText(tblCongNhan.getValueAt(row, 1).toString());
        lblHoVaTenHienThi.setText(tblCongNhan.getValueAt(row, 2).toString());
    }

    public void taiDuLieuChamCongLenBang() {
        while (tblChamCong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCong();
        for (ChamCongCongNhan cccn : dsChamCong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", cccn.getPhanCong().getMaPhanCong(), cccn.getPhanCong().getCongNhan().getMaCongNhan(),
                cccn.getPhanCong().getCongNhan().getHoTen(), cccn.getPhanCong().getCongNhan().getToNhom().getTenToNhom(),
                cccn.getNgayChamCong().toString(), cccn.getCaLam(), cccn.getTrangThaiDiLam(), cccn.getGioDiLam(), cccn.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
                cccn.getPhanCong().getCongDoan().getSanPham().getTenSanPham(),
                cccn.getPhanCong().getCongDoan().getMaCongDoan(), cccn.getPhanCong().getCongDoan().getTenCongDoan(), cccn.getSoLuongLam() + ""};

            modelTableChamCong.addRow(data);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblCongNhan)) {
            int rowSelectOfChamCong = tblChamCong.getSelectedRow();
            if (rowSelectOfChamCong != -1) {
                tblChamCong.removeRowSelectionInterval(rowSelectOfChamCong, rowSelectOfChamCong);

            }
            int row = tblCongNhan.getSelectedRow();
            if (row != -1) {
                hienThiThongTinCongNhanLenTxt(row);
                txtSoLuongLam.setText("0");
                txtSoLuongLam.requestFocus();
                btnCapNhat.setEnabled(false);
                btnHuy.setEnabled(false);
                btnLuu.setEnabled(false);
                btnXoa.setEnabled(false);
                btnChamCong.setEnabled(true);

                cmbCaLam.setEnabled(true);
                cmbTrangThai.setEnabled(true);
                cmbGioDiLam.setEnabled(true);
                cmbPhutDiLam.setEnabled(true);
                txtSoLuongLam.setEditable(true);
            }
        } else if (o.equals(tblChamCong)) {
            int rowSelectCongNhan = tblCongNhan.getSelectedRow();
            if (rowSelectCongNhan != -1) {
                tblCongNhan.removeRowSelectionInterval(rowSelectCongNhan, rowSelectCongNhan);

            }
            int rowSelectChamCong = tblChamCong.getSelectedRow();
            if (rowSelectChamCong != -1) {
                btnChamCong.setEnabled(false);
                btnCapNhat.setEnabled(true);
                btnHuy.setEnabled(false);
                btnLuu.setEnabled(false);
                btnXoa.setEnabled(true);
                lblErrSoLuongSP.setText("");

                if (btnCapNhat.isEnabled()) {
                    System.out.println("view.ChamCongCongNhanView.mouseClicked()");
                    cmbCaLam.setEnabled(false);
                    cmbTrangThai.setEnabled(false);
                    cmbGioDiLam.setEnabled(false);
                    cmbPhutDiLam.setEnabled(false);
                    txtSoLuongLam.setEditable(false);
                } else {
                    cmbCaLam.setEnabled(false);
                    cmbTrangThai.setEnabled(true);
                    cmbGioDiLam.setEnabled(true);
                    cmbPhutDiLam.setEnabled(true);
                    txtSoLuongLam.setEditable(true);
                }
                taiDuLieuTuBangChamCongLenTxt();
            }
        }
    }

    public void taiDuLieuTuBangChamCongLenTxt() {
        lblMaCongNhanHienThi.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 2).toString());
        lblHoVaTenHienThi.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 3).toString());
        cmbTrangThai.setSelectedItem(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 7).toString());
        cmbCaLam.setSelectedItem(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 6).toString());
        String times[] = tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 8).toString().split("h");
        if (times.length > 1) {
            cmbGioDiLam.setSelectedItem(times[0]);
            cmbPhutDiLam.setSelectedItem(times[1]);
        }
        lblTenSanPhamOutput.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 10).toString());
        lblTenCongDoanOutput.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 12).toString());
        txtSoLuongLam.setText(tblChamCong.getValueAt(tblChamCong.getSelectedRow(), 13).toString());
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
