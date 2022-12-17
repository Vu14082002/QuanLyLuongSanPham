/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.BangLuongNhanVien_DAO;
import DAO.ChamCongNhanVien_DAO;
import DAO.NhanVien_DAO;
import Entity.BangLuongNhanVien;
import Entity.NhanVien;
import java.awt.Font;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LuongNhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel model;
    private ChamCongNhanVien_DAO daoChamCong;
    private BangLuongNhanVien_DAO daoLuong;
    private NhanVien_DAO nhanVienDao;
    private BangLuongNhanVien_DAO bangLuongNhanVienDao;
    private ChamCongNhanVien_DAO chamCongNhanVienDao;

    private double tongLuong = 0;
    private String fileName;
    private String stTinhLuongThanhCong;
    private String stTinhLuongThatBai;

    public LuongNhanVienView(String fileName) throws IOException {
        this.fileName = fileName;
        initComponents();
        excute();
        taiDuLieuLenBangLuong();
        caiDatNgonNguChoView(fileName);
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblThang.setText(prop.getProperty("lnv_thang"));
        lblNam.setText(prop.getProperty("lnv_nam"));
        btnTinhLuong.setText(prop.getProperty("lnv_btnTinhLuong"));
        btnGuiThongTin.setText(prop.getProperty("lnv_btnGuiThongTin"));
        btnXuatBaoCao.setText(prop.getProperty("lnv_btnXuatBaoCao"));
        lblHIenThi.setText(prop.getProperty("lnv_hienThi"));
        cmbHienThi.removeAllItems();
        cmbHienThi.addItem(prop.getProperty("lnv_cmb0"));
        cmbHienThi.addItem(prop.getProperty("lnv_cmb1"));
        scrBangLuong.setBorder(new TitledBorder(prop.getProperty("lnv_tieuDe")));

        ChangeName(tblBangLuong, 0, prop.getProperty("lnv_stt"));
        ChangeName(tblBangLuong, 1, prop.getProperty("lnv_maLuong"));
        ChangeName(tblBangLuong, 2, prop.getProperty("lnv_maNhanVien"));
        ChangeName(tblBangLuong, 3, prop.getProperty("lnv_tenNhanVien"));
        ChangeName(tblBangLuong, 4, prop.getProperty("lnv_gioiTinh"));
        ChangeName(tblBangLuong, 5, prop.getProperty("lnv_soDienThoai"));
        ChangeName(tblBangLuong, 6, prop.getProperty("lnv_luongThang"));
        ChangeName(tblBangLuong, 7, prop.getProperty("lnv_soNgayDiLam"));
        ChangeName(tblBangLuong, 8, prop.getProperty("lnv_soNgayNghi"));
        ChangeName(tblBangLuong, 9, prop.getProperty("lnv_soPhepNghi"));
        ChangeName(tblBangLuong, 10, prop.getProperty("lnv_tongLuong"));
        ChangeName(tblBangLuong, 11, prop.getProperty("lnv_donViTien"));
        ChangeName(tblBangLuong, 12, prop.getProperty("lnv_ngayTinh"));
        stTinhLuongThanhCong = prop.getProperty("tinhLuongThanhCong");
        stTinhLuongThatBai = prop.getProperty("tinhLuongThatBai");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void excute() {

        model = (DefaultTableModel) tblBangLuong.getModel();
        // custom table
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        tblBangLuong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblBangLuong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblBangLuong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblBangLuong.setRowHeight(25);
        cmbNam.removeAllItems();

        LocalDate lcDate = LocalDate.now();

        for (int i = 2000; i <= lcDate.getYear(); i++) {
            cmbNam.addItem(i + "");
        }
    }

    public void taiDuLieuLenBangLuong() {
        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }
        daoLuong = new BangLuongNhanVien_DAO();
        DecimalFormat dfm = new DecimalFormat("###,###,###,###,###.###");
        ArrayList<BangLuongNhanVien> bangLuongNhanVienList = daoLuong.danhSachBangLuong();
        if (bangLuongNhanVienList != null) {
            if (cmbHienThi.getSelectedIndex() == 0) {
                for (BangLuongNhanVien l : bangLuongNhanVienList) {
                    model.addRow(new Object[]{model.getRowCount() + 1, l.getMaBangLuong(), l.getNhanVien().getMaNhanVien(), l.getNhanVien().getHoTen(),
                        l.getNhanVien().isGioiTinh() ? "Nam" : "Nữ", l.getNhanVien().getSoDienThoai(), l.getLuongTheoThang(), l.getSoNgayDiLam(), l.getSoNgayNghi(), l.getSoPhepNghi(),
                        dfm.format(l.getTongTien()), l.getDonViTien(), l.getNgayTinh()
                    });
                }
            } else {
                for (BangLuongNhanVien l : bangLuongNhanVienList) {
                    if (l.getLuongTheoThang().equalsIgnoreCase(cmbThang.getSelectedItem().toString() + "-" + cmbNam.getSelectedItem().toString())) {
                        model.addRow(new Object[]{model.getRowCount() + 1, l.getMaBangLuong(), l.getNhanVien().getMaNhanVien(), l.getNhanVien().getHoTen(),
                            l.getNhanVien().isGioiTinh() ? "Nam" : "Nữ", l.getNhanVien().getSoDienThoai(), l.getLuongTheoThang(), l.getSoNgayDiLam(), l.getSoNgayNghi(), l.getSoPhepNghi(),
                            dfm.format(l.getTongTien()), l.getDonViTien(), l.getNgayTinh()
                        });
                    }
                }
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btnTinhLuong = new javax.swing.JButton();
        btnGuiThongTin = new javax.swing.JButton();
        lblHIenThi = new javax.swing.JLabel();
        cmbThang = new javax.swing.JComboBox<>();
        cmbNam = new javax.swing.JComboBox<>();
        lblNam = new javax.swing.JLabel();
        btnXuatBaoCao = new javax.swing.JButton();
        cmbHienThi = new javax.swing.JComboBox<>();
        lblThang = new javax.swing.JLabel();
        scrBangLuong = new javax.swing.JScrollPane();
        tblBangLuong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 250));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTinhLuong.setBackground(new java.awt.Color(46, 204, 113));
        btnTinhLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnTinhLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnTinhLuong.setText("Tính lương");
        btnTinhLuong.setBorder(null);
        btnTinhLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTinhLuongActionPerformed(evt);
            }
        });
        jPanel5.add(btnTinhLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 170, 40));

        btnGuiThongTin.setBackground(new java.awt.Color(156, 136, 255));
        btnGuiThongTin.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnGuiThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnGuiThongTin.setText("Gửi thông tin");
        btnGuiThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiThongTinActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuiThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 120, 160, 40));

        lblHIenThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHIenThi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHIenThi.setText("Hiển thị theo:");
        jPanel5.add(lblHIenThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 120, 40));

        cmbThang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbThangActionPerformed(evt);
            }
        });
        jPanel5.add(cmbThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 70, 40));

        cmbNam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000" }));
        cmbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamActionPerformed(evt);
            }
        });
        jPanel5.add(cmbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 120, 40));

        lblNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNam.setText("Năm");
        jPanel5.add(lblNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 80, 40));

        btnXuatBaoCao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXuatBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnXuatBaoCao.setText("Xuất báo cáo");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });
        jPanel5.add(btnXuatBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 160, 40));

        cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo tháng/năm" }));
        cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHienThiActionPerformed(evt);
            }
        });
        jPanel5.add(cmbHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 140, 40));

        lblThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblThang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThang.setText("Tháng:");
        jPanel5.add(lblThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 90, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scrBangLuong.setBackground(new java.awt.Color(255, 255, 255));
        scrBangLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng lương", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lương", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Số điện thoại", "Lương tháng", "Số ngày đi làm", "Số ngày nghỉ", "Số phép nghỉ", "Tổng lương", "Đơn vị tiên", "Ngày tính lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangLuong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblBangLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBangLuongMousePressed(evt);
            }
        });
        scrBangLuong.setViewportView(tblBangLuong);

        add(scrBangLuong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhLuongActionPerformed
//        LocalDate local = LocalDate.now();
//        if (Integer.parseInt(cmbThang.getSelectedItem().toString()) > local.getMonthValue()) {
//            JOptionPane.showMessageDialog(this, "Thang cham khong duoc sau thang hien tai");
//            return;
//        }
//        daoLuong = new BangLuongNhanVien_DAO();
//        daoChamCong = new ChamCongNhanVien_DAO();
//        NhanVien_DAO daoNhanVien = new NhanVien_DAO();
//        ArrayList<NhanVien> nhanVienList = daoNhanVien.layDanhSachNhanVien();
//        ArrayList<BangLuongNhanVien> listLuong = daoLuong.danhSachBangLuong();
//        ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();
//
//        Date date = new Date();
//        SimpleDateFormat dmyFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String ngayTinhLuong = dmyFormat.format(date);
//
//        int thang = Integer.parseInt(cmbThang.getSelectedItem().toString());
//        int nam = Integer.parseInt(cmbNam.getSelectedItem().toString());
//        System.out.println(thang + " " + nam);
//        ArrayList<String> nhanVienKhongTrungList = daoLuong.layDanhSachMaNhanVienKhongTrung();
//        // lay ngay cuoi thang
//        Calendar date1 = Calendar.getInstance();
//        date1.set(nam, thang, 1);
//
//        int res = date1.getActualMaximum(Calendar.DATE);
//        Calendar date2 = Calendar.getInstance();
//        date2.set(nam, thang, res);
//        int maLuong = 0;
//        BangLuongNhanVien_DAO daoLuong2 = new BangLuongNhanVien_DAO();
//        ArrayList<BangLuongNhanVien> listLuong2 = daoLuong2.danhSachBangLuong();
//        if (!listLuong2.isEmpty()) {
//            maLuong = 1 + Integer.parseInt(listLuong2.get(listLuong.size() - 1).getMaBangLuong().split("N")[1]);
//        } else {
//            maLuong = 100001;
//        }
//        int xoa = 0;
//        for (String nv : nhanVienKhongTrungList) {
//            NhanVien nhanvien = daoNhanVien.layMotNhanVienTheoMaNhanVien(nv);
//            int soNgayDiLam = daoLuong.laySoNgayDilamCuaNhanVien(nv, thang, nam);
//            int soNgayNghiPhep = daoLuong.laySoNgayNghiCoPhepCuaNhanVien(nv, thang, nam);
//            int soNgayNghi = res - sunday(date1.getTime(), date2.getTime()) - soNgayDiLam - soNgayNghiPhep;
//            double luongNhanVien = (nhanvien.getLuongThoaThuan() / 26) * (soNgayDiLam + soNgayNghiPhep);
//            DecimalFormat dfm = new DecimalFormat("###########.##");
//            String tienLuong = dfm.format(luongNhanVien);
//            System.out.println("LN" + maLuong);
//            daoLuong.themMotBangLuongString("LN" + maLuong, nv, soNgayDiLam, soNgayNghi, soNgayNghiPhep, new Date(), luongNhanVien, "VND", cmbThang.getSelectedItem().toString(), cmbNam.getSelectedItem().toString(), xoa);
//            xoa++;
//            System.out.println("Them thanh cong");
//            maLuong++;
//        }
        tinhLuongNhanVien2();
        taiDuLieuLenBangLuong();

    }//GEN-LAST:event_btnTinhLuongActionPerformed
    public void tinhLuongNhanVien2() {
        bangLuongNhanVienDao = new BangLuongNhanVien_DAO();
        nhanVienDao = new NhanVien_DAO();
        chamCongNhanVienDao = new ChamCongNhanVien_DAO();
        NhanVien nhanVienChamCong = nhanVienDao.layMotNhanVienTheoMaNhanVien("NV100001");
        ArrayList<NhanVien> nhanVienList = nhanVienDao.layDanhSachNhanVien();
        bangLuongNhanVienDao.xoaBangLuongInsert(cmbThang.getSelectedItem().toString(), cmbNam.getSelectedItem().toString());
        for (NhanVien nv : nhanVienList) {
            ArrayList<BangLuongNhanVien> bangLuongList = bangLuongNhanVienDao.danhSachBangLuong();
            String maBangLuong = "LN100001";
            if (bangLuongList.size() > 0) {
                maBangLuong = "LN" + (Integer.parseInt(bangLuongList.get(bangLuongList.size() - 1).getMaBangLuong().split("N")[1]) + 1);
            }
            int soNgayDiLam = 0;
            int soNgayNghi = 0;
            int soPhepNghi = 0;
            int soNgayChuNhatDiLam = 0;
            System.out.println(nv.getMaNhanVien() + " " + cmbThang.getSelectedItem().toString() + " " + cmbNam.getSelectedItem().toString());
            ArrayList<String[]> danhSach = chamCongNhanVienDao.layDanhSachChamCongTheoMaNhanVienVaThang(nv.getMaNhanVien(),
                    cmbThang.getSelectedItem().toString(), cmbNam.getSelectedItem().toString());
            for (String[] string : danhSach) {
                System.out.println(string[0] + " " + string[1]);
                if (string[1].contains("Đi")) {
                    soNgayDiLam++;
                    if (string[0].contains("chủ nhật")) {
                        soNgayChuNhatDiLam++;
                    }
                }
                if (string[1].contains("Nghỉ")) {
                    soNgayNghi++;
                }
                if (string[1].contains("Nghỉ có phép")) {
                    soPhepNghi++;
                }
            }
            String luongTheoThang = cmbThang.getSelectedItem().toString() + "-" + cmbNam.getSelectedItem().toString();
            double tongTien = nv.getLuongThoaThuan() / 28 * soNgayDiLam + nv.getLuongThoaThuan() / 28 * soNgayChuNhatDiLam;
            bangLuongNhanVienDao.themMotBangLuongString(maBangLuong, nv.getMaNhanVien(), soNgayDiLam, soNgayNghi, soPhepNghi, new Date(), luongTheoThang, tongTien, "VND");
        }
        JOptionPane.showMessageDialog(this, stTinhLuongThanhCong);
    }

    public int sunday(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        int sundays = 0;
        while (c1.after(c2)) {
            if (c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || c2.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundays++;
            }
            c2.add(Calendar.DATE, 1);
            c2.add(Calendar.DATE, 1);
        }
        System.out.println(sundays);
        return sundays;
    }
    private void btnGuiThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiThongTinActionPerformed
        String luongTHang = tblBangLuong.getValueAt(0, 6).toString();
        try {
            for (int i = 0; i < tblBangLuong.getRowCount(); i++) {
                String username = "admin";
                String password = "123456";
                String to = tblBangLuong.getValueAt(i, 5).toString();
                String message = "Lương tháng " + luongTHang + " của : " + tblBangLuong.getValueAt(i, 2).toString()
                        + " - " + tblBangLuong.getValueAt(i, 3).toString()
                        + " nhận được là " + tblBangLuong.getValueAt(i, 10).toString() + " " + tblBangLuong.getValueAt(i, 11).toString();
                String requestUrl = "http://localhost:9710/http/send-message?"
                        + "username=" + URLEncoder.encode(username, "UTF-8")
                        + "&password=" + URLEncoder.encode(password, "UTF-8")
                        + "&to=" + URLEncoder.encode(to, "UTF-8")
                        + "&message-type=sms.automatic"
                        + "&message=" + URLEncoder.encode(message, "UTF-8");
                URL url = new URL(requestUrl);
                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
                System.out.println(uc.getResponseMessage());
                uc.disconnect();
            }
            JOptionPane.showMessageDialog(null, "Gửi thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Gửi thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnGuiThongTinActionPerformed

    private void cmbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThangActionPerformed
        taiDuLieuLenBangLuong();
        caiDatGuiThongTin();
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (tblBangLuong.getRowCount() > 0) {
                btnGuiThongTin.setEnabled(true);
                btnXuatBaoCao.setEnabled(true);
            } else {
                btnGuiThongTin.setEnabled(false);
                btnXuatBaoCao.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbThangActionPerformed

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed

        MessageFormat header = new MessageFormat("Bang luong nhân viên tháng" + cmbThang.getSelectedItem().toString() + "-" + cmbNam.getSelectedItem().toString());
        MessageFormat footer = new MessageFormat("Nhóm 27");
        try {
            tblBangLuong.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHienThiActionPerformed
        taiDuLieuLenBangLuong();
        if (cmbHienThi.getSelectedIndex() == 0) {
            btnXuatBaoCao.setEnabled(false);
            btnGuiThongTin.setEnabled(false);
        } else {
            if (cmbHienThi.getSelectedIndex() == 1) {
                if (tblBangLuong.getRowCount() > 0) {
                    btnGuiThongTin.setEnabled(true);
                    btnXuatBaoCao.setEnabled(true);
                } else {
                    btnGuiThongTin.setEnabled(false);
                    btnXuatBaoCao.setEnabled(false);
                }
            }
        }

    }//GEN-LAST:event_cmbHienThiActionPerformed
    public void caiDatGuiThongTin() {
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (model.getRowCount() > 0) {
                btnXuatBaoCao.setEnabled(true);
                btnGuiThongTin.setEnabled(true);
            }
        } else {
            btnXuatBaoCao.setEnabled(false);
            btnGuiThongTin.setEnabled(false);
        }

    }
    private void tblBangLuongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangLuongMousePressed
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && tblBangLuong.getSelectedRow() != -1) {
            try {
                new ChiTietLuongNhanVien(fileName, tblBangLuong.getValueAt(row, 2).toString(), tblBangLuong.getValueAt(row, 3).toString(),
                        tblBangLuong.getValueAt(row, 10).toString(), tblBangLuong.getValueAt(row, 6).toString().split("-")[0],
                        tblBangLuong.getValueAt(row, 6).toString().split("-")[1]).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(LuongNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblBangLuongMousePressed
    private void cmbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamActionPerformed
        taiDuLieuLenBangLuong();
        caiDatGuiThongTin();
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (tblBangLuong.getRowCount() > 0) {
                btnGuiThongTin.setEnabled(true);
                btnXuatBaoCao.setEnabled(true);
            } else {
                btnGuiThongTin.setEnabled(false);
                btnXuatBaoCao.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbNamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiThongTin;
    private javax.swing.JButton btnTinhLuong;
    private javax.swing.JButton btnXuatBaoCao;
    private javax.swing.JComboBox<String> cmbHienThi;
    private javax.swing.JComboBox<String> cmbNam;
    private javax.swing.JComboBox<String> cmbThang;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblHIenThi;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblThang;
    private javax.swing.JScrollPane scrBangLuong;
    private javax.swing.JTable tblBangLuong;
    // End of variables declaration//GEN-END:variables
}