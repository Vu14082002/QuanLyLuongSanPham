/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.BangLuongCongNhan_DAO;
import DAO.ChamCongCongNhan_DAO;
import DAO.CongNhan_DAO;
import Entity.BangLuongCongNhan;
import Entity.CongNhan;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
public class LuongCongNhanView extends javax.swing.JPanel implements ActionListener {

    private DefaultTableModel modelTableChamCong;
    private ChamCongCongNhan_DAO chamCongCN_DAO;
    private BangLuongCongNhan_DAO bangLuongCN_DAO;
    private DecimalFormat nf, df;
    private String fileName;
    private String stTinhLuongThanhCong;
    private String stTinhLuongThatBai;

    /**
     * Creates new form NhanVienView
     */
    public LuongCongNhanView(String fileName) throws IOException {
        this.fileName = fileName;
        initComponents();
        excute();
        nf = new DecimalFormat("#,###.00");
        df = new DecimalFormat("#");
        modelTableChamCong = (DefaultTableModel) tblBangLuong.getModel();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        chamCongCN_DAO = new ChamCongCongNhan_DAO();
        bangLuongCN_DAO = new BangLuongCongNhan_DAO();
        btnGuiThongTin.addActionListener(this);
        btnTinhLuong.addActionListener(this);
        btnXuatBaoCao.addActionListener(this);
        btnXuatBaoCao.setEnabled(false);
        cmbNamTinh.removeAllItems();
        LocalDate lcDate = LocalDate.now();
        for (int i = 2000; i <= lcDate.getYear(); i++) {
            cmbNamTinh.addItem(i + "");
        }
        caiDatNgonNguChoView(fileName);
        cmbThangTinh.addItemListener(this::checkThangChon);
        cmbHienThi.addItemListener(this::hienThiBangLuongTheoNgay);
        taiDuLieuLenBang();
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
        lblHienThi.setText(prop.getProperty("lnv_hienThi"));
        cmbHienThi.removeAllItems();
        cmbHienThi.addItem(prop.getProperty("lnv_cmb0"));
        cmbHienThi.addItem(prop.getProperty("lnv_cmb1"));
        scrBangLuong.setBorder(new TitledBorder(prop.getProperty("lnv_tieuDe")));
        ChangeName(tblBangLuong, 0, prop.getProperty("lnv_stt"));
        ChangeName(tblBangLuong, 1, prop.getProperty("lnv_maLuong"));
        ChangeName(tblBangLuong, 2, prop.getProperty("maCongNhan"));
        ChangeName(tblBangLuong, 3, prop.getProperty("tenCongNhan"));
        ChangeName(tblBangLuong, 4, prop.getProperty("soCCCD"));
        ChangeName(tblBangLuong, 5, prop.getProperty("lnv_soNgayDiLam"));
        ChangeName(tblBangLuong, 6, prop.getProperty("lnv_soPhepNghi"));
        ChangeName(tblBangLuong, 7, prop.getProperty("lcc_nghiKhongPhep"));
        ChangeName(tblBangLuong, 8, prop.getProperty("lnv_donViTien"));
        ChangeName(tblBangLuong, 9, prop.getProperty("lnv_luongThang"));
        ChangeName(tblBangLuong, 10, prop.getProperty("lnv_ngayTinh"));
        ChangeName(tblBangLuong, 11, prop.getProperty("lnv_tongLuong"));
        stTinhLuongThanhCong = prop.getProperty("tinhLuongThanhCong");
        stTinhLuongThatBai = prop.getProperty("tinhLuongThatBai");
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void setNamChoCmbNam() {
        cmbNamTinh.removeAllItems();
        cmbNamTinh.addItem(Calendar.getInstance().get(Calendar.YEAR) + "");
        cmbNamTinh.setEnabled(false);
    }

    public void hienThiBangLuongTheoNgay(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String item = e.getItem().toString();
            btnXuatBaoCao.setEnabled(false);
            if (item.equalsIgnoreCase("Tất cả")) {
                taiDuLieuLenBang();
            } else {
                taiDuLieuLenBangTheoNgayThang();
                if (tblBangLuong.getRowCount() != 0) {
                    btnXuatBaoCao.setEnabled(true);
                }
            }

        }
    }

    public void taiDuLieuLenBangTheoNgayThang() {
        int thang = Integer.parseInt((cmbThangTinh.getSelectedItem().toString()));
        int nam = Integer.parseInt(cmbNamTinh.getSelectedItem().toString());
        while (tblBangLuong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<BangLuongCongNhan> dsBangLuong = bangLuongCN_DAO.layDanhSachBangLuongTheoThangNam(thang, nam);
        for (BangLuongCongNhan bangLuong : dsBangLuong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", bangLuong.getMaBangLuong(), bangLuong.getCongNhan().getMaCongNhan(),
                bangLuong.getCongNhan().getHoTen(), bangLuong.getCongNhan().getMaCCCD(), bangLuong.getSoNgayDiLam() + "",
                bangLuong.getSoNgayNghi() + "", bangLuong.getSoPhepNghi() + "", bangLuong.getDonViTien(), bangLuong.getLuongTheoThang(), bangLuong.getNgayTinh().toString(),
                (bangLuong.getTongLuong() == 0) ? "0" : nf.format(bangLuong.getTongLuong())};
            modelTableChamCong.addRow(data);
        }

    }

    public void checkThangChon(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            String thangString = e.getItem().toString();

            int thang = Integer.parseInt(thangString);
            int nam = Calendar.getInstance().get(Calendar.YEAR);
            if (LocalDate.of(nam, thang, 1).isAfter(LocalDate.now())) {
                JOptionPane.showMessageDialog(null, "Tháng tinh phải là tháng trước hoặc bằng tháng hiện tại");
                System.out.println(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue());
                cmbThangTinh.setSelectedItem(new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue() + "");
            }
        }
    }

    public void taiDuLieuLenBang() {
        while (tblBangLuong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<BangLuongCongNhan> dsBangLuong = bangLuongCN_DAO.layDanhSachBangLuongCongNhan();
        for (BangLuongCongNhan bangLuong : dsBangLuong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", bangLuong.getMaBangLuong(), bangLuong.getCongNhan().getMaCongNhan(),
                bangLuong.getCongNhan().getHoTen(), bangLuong.getCongNhan().getMaCCCD(), bangLuong.getSoNgayDiLam() + "",
                bangLuong.getSoNgayNghi() + "", bangLuong.getSoPhepNghi() + "", bangLuong.getDonViTien(), bangLuong.getLuongTheoThang(), bangLuong.getNgayTinh().toString(),
                (bangLuong.getTongLuong() == 0) ? "0" : nf.format(bangLuong.getTongLuong())};
            modelTableChamCong.addRow(data);
        }
    }

    public void excute() {
        tblBangLuong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblBangLuong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblBangLuong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblBangLuong.setRowHeight(25);

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
        btnTinhLuong = new javax.swing.JButton();
        btnGuiThongTin = new javax.swing.JButton();
        lblThang = new javax.swing.JLabel();
        cmbThangTinh = new javax.swing.JComboBox<>();
        cmbNamTinh = new javax.swing.JComboBox<>();
        lblNam = new javax.swing.JLabel();
        btnXuatBaoCao = new javax.swing.JButton();
        lblHienThi = new javax.swing.JLabel();
        cmbHienThi = new javax.swing.JComboBox<>();
        scrBangLuong = new javax.swing.JScrollPane();
        tblBangLuong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 200));
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
        jPanel5.add(btnTinhLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 170, 40));

        btnGuiThongTin.setBackground(new java.awt.Color(156, 136, 255));
        btnGuiThongTin.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnGuiThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnGuiThongTin.setText("Gửi thông tin");
        btnGuiThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiThongTinActionPerformed(evt);
            }
        });
        jPanel5.add(btnGuiThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 160, 40));

        lblThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblThang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThang.setText("Tháng:");
        jPanel5.add(lblThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 90, 40));

        cmbThangTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbThangTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbThangTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbThangTinhActionPerformed(evt);
            }
        });
        jPanel5.add(cmbThangTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, 80, 40));

        cmbNamTinh.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbNamTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cmbNamTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamTinhActionPerformed(evt);
            }
        });
        jPanel5.add(cmbNamTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 120, 40));

        lblNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNam.setText("Năm");
        jPanel5.add(lblNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 80, 40));

        btnXuatBaoCao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXuatBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnXuatBaoCao.setText("Xuất báo cáo");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });
        jPanel5.add(btnXuatBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, 160, 40));

        lblHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHienThi.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHienThi.setText("Hiển thị theo:");
        jPanel5.add(lblHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 120, 40));

        cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo tháng/năm" }));
        cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHienThiActionPerformed(evt);
            }
        });
        jPanel5.add(cmbHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 140, 30));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scrBangLuong.setBackground(new java.awt.Color(255, 255, 255));
        scrBangLuong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng lương", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lương", "Mã công nhân", "Tên công Nhân", "Mã CCCD", "Số ngày đi làm", "Số ngày có phép", "Số ngày nghỉ không phép", "Đơn vị tiền", "Ngày tính lương", "Lương tháng", "Tổng Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
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
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblBangLuongMouseReleased(evt);
            }
        });
        scrBangLuong.setViewportView(tblBangLuong);

        add(scrBangLuong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhLuongActionPerformed
    }//GEN-LAST:event_btnTinhLuongActionPerformed

    private void btnGuiThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiThongTinActionPerformed
        String luongTHang = tblBangLuong.getValueAt(0, 9).toString();
        try {
            for (int i = 0; i < tblBangLuong.getRowCount(); i++) {
                CongNhan_DAO congNhanDao = new CongNhan_DAO();
                CongNhan congNhan = congNhanDao.layMotCongNhanTheoMa(tblBangLuong.getValueAt(i, 2).toString());
                String username = "admin";
                String password = "123456";
                String to = congNhan.getSoDienThoai();
                System.out.println(to);
                String message = "Lương tháng " + luongTHang + " của : " + tblBangLuong.getValueAt(i, 2).toString()
                        + " - " + tblBangLuong.getValueAt(i, 3).toString()
                        + " nhận được là " + tblBangLuong.getValueAt(i, 11).toString() + " " + tblBangLuong.getValueAt(i, 8).toString();
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

    private void cmbThangTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThangTinhActionPerformed
        if (cmbHienThi.getSelectedIndex() == 0) {
            taiDuLieuLenBang();
        } else {
            taiDuLieuLenBangTheoNgayThang();
            caiDatGuiThongTin();
        }
        if (tblBangLuong.getRowCount() < 0) {
            btnTinhLuong.setEnabled(false);
        }
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (tblBangLuong.getRowCount() > 0) {
                btnGuiThongTin.setEnabled(true);
                btnXuatBaoCao.setEnabled(true);
            } else {
                btnGuiThongTin.setEnabled(false);
                btnXuatBaoCao.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbThangTinhActionPerformed

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Bảng Lương Công Nhân trong tháng " + cmbThangTinh.getSelectedItem()
                + " năm " + cmbNamTinh.getSelectedItem().toString());
        MessageFormat footer = new MessageFormat("Nhóm 27");
        try {
            tblBangLuong.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void tblBangLuongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangLuongMousePressed
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && tblBangLuong.getSelectedRow() != -1) {
            int rowSelected = tblBangLuong.getSelectedRow();
            LocalDate date = LocalDate.parse(tblBangLuong.getValueAt(row, 10).toString());
            int thang = date.getMonthValue();
            int nam = date.getYear();
            try {
                new ChiTietLuongCongNhan(this.fileName, tblBangLuong.getValueAt(row, 2).toString(), thang, nam).setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(LuongCongNhanView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblBangLuongMousePressed

    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHienThiActionPerformed
        if (cmbHienThi.getSelectedIndex() == 0) {
            taiDuLieuLenBang();
        } else {
            taiDuLieuLenBangTheoNgayThang();
        }
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (tblBangLuong.getRowCount() > 0) {
                btnGuiThongTin.setEnabled(true);
                btnXuatBaoCao.setEnabled(true);
            }
        } else {
            btnGuiThongTin.setEnabled(false);
            btnXuatBaoCao.setEnabled(false);
        }
    }//GEN-LAST:event_cmbHienThiActionPerformed

    private void tblBangLuongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangLuongMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblBangLuongMouseReleased

    private void cmbNamTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamTinhActionPerformed
        if (cmbHienThi.getSelectedIndex() == 0) {
            taiDuLieuLenBang();
            btnGuiThongTin.setEnabled(false);
            btnXuatBaoCao.setEnabled(false);
        } else {
            taiDuLieuLenBangTheoNgayThang();
            caiDatGuiThongTin();
        }
        if (tblBangLuong.getRowCount() < 0) {
            btnTinhLuong.setEnabled(false);
        }
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (tblBangLuong.getRowCount() > 0) {
                btnGuiThongTin.setEnabled(true);
                btnXuatBaoCao.setEnabled(true);
            } else {
                btnGuiThongTin.setEnabled(false);
                btnXuatBaoCao.setEnabled(false);
            }
        }
    }//GEN-LAST:event_cmbNamTinhActionPerformed

    public void caiDatGuiThongTin() {
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (modelTableChamCong.getRowCount() > 0) {
                btnXuatBaoCao.setEnabled(true);
                btnGuiThongTin.setEnabled(true);
            }
        } else {
            btnXuatBaoCao.setEnabled(false);
            btnGuiThongTin.setEnabled(false);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiThongTin;
    private javax.swing.JButton btnTinhLuong;
    private javax.swing.JButton btnXuatBaoCao;
    private javax.swing.JComboBox<String> cmbHienThi;
    private javax.swing.JComboBox<String> cmbNamTinh;
    private javax.swing.JComboBox<String> cmbThangTinh;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblHienThi;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblThang;
    private javax.swing.JScrollPane scrBangLuong;
    private javax.swing.JTable tblBangLuong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTinhLuong)) {
            int thang = Integer.parseInt(cmbThangTinh.getSelectedItem().toString());
            int nam = Integer.parseInt(cmbNamTinh.getSelectedItem().toString());
            boolean coXoaDuoc = bangLuongCN_DAO.xoaDiNhungThangDaTinh(thang, nam);
            boolean tinhLuong = bangLuongCN_DAO.tinhLuongCongNhan(thang, nam);
            if (tinhLuong) {
//                JOptionPane.showMessageDialog(null, "Tính lương cho tháng " + cmbThangTinh.getSelectedItem().toString()
//                        + ", năm " + cmbNamTinh.getSelectedItem().toString() + " thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this, stTinhLuongThanhCong);
                taiDuLieuLenBang();
            } else {
//                JOptionPane.showMessageDialog(null, "Tính lương cho tháng " + cmbThangTinh.getSelectedItem().toString()
//                        + ", năm " + cmbNamTinh.getSelectedItem().toString() + " thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(this, stTinhLuongThatBai);
            }

        }
    }
}
