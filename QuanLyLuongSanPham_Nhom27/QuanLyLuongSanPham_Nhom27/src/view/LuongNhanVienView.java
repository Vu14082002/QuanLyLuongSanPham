/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.BangLuongNhanVien_DAO;
import DAO.ChamCongNhanVien_DAO;
import Entity.BangLuongNhanVien;
import Entity.ChamCongNhanVien;
import Entity.NhanVien;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfAcroForm;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
    private int soNghiKhongPhep = 0;
    private int soNgayNghiCoPhep = 0;
    private int soNgayLam = 0;

    private double tongLuong = 0;

    public LuongNhanVienView() {
        initComponents();
        excute();
        taiDuLieuLenBangLuong();
    }

    public void excute() {
        // custom table
        model = (DefaultTableModel) tblBangLuong.getModel();
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

    @SuppressWarnings("deprecation")
    public void taiDuLieuLenBangLuong() {
        while (model.getRowCount() != 0) {
            model.removeRow(0);
        }
        daoLuong = new BangLuongNhanVien_DAO();
        daoChamCong = new ChamCongNhanVien_DAO();
        ArrayList<BangLuongNhanVien> listLuong = daoLuong.danhSachBangLuong();
        ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();
        if (cmbHienThi.getSelectedIndex() == 1) {
            if (listLuong != null) {
                listLuong.forEach(e -> {
                    listChamCong.forEach(j -> {
                        SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MM");
                        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
                        String month = Integer.parseInt(dateFormatMonth.format(j.getNgayChamCong())) + "";
                        String year = Integer.parseInt(dateFormatYear.format(j.getNgayChamCong())) + "";
                        if (e.getNhanVien().getMaNhanVien().equalsIgnoreCase(j.getNhanVien().getMaNhanVien())
                                && month.equalsIgnoreCase(cmbThang.getSelectedItem().toString()) && year.equalsIgnoreCase(cmbNam.getSelectedItem().toString())) {
                            System.out.println("true");
                            model.addRow(new Object[]{model.getRowCount() + 1, e.getMaBangLuong(), e.getNhanVien().getMaNhanVien(), e.getNhanVien().getHoTen(),
                                e.getNhanVien().getSoDienThoai(), e.getSoNgayDiLam(), e.getSoNgayNghi(), e.getSoPhepNghi(), e.getTongTien(), e.getDonViTien(), e.getNgayTinh()
                            });
                        }
                    });
                });
            }
        } else {
            if (listLuong != null) {
                listLuong.forEach(e -> {
                    model.addRow(new Object[]{model.getRowCount() + 1, e.getMaBangLuong(), e.getNhanVien().getMaNhanVien(), e.getNhanVien().getHoTen(),
                        e.getNhanVien().isGioiTinh() ? "Nam" : "Nữ", e.getNhanVien().getSoDienThoai(), e.getSoNgayDiLam(), e.getSoNgayNghi(), e.getSoPhepNghi(), e.getTongTien(), e.getDonViTien(), e.getNgayTinh()
                    });
                });
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
        jScrollPane1 = new javax.swing.JScrollPane();
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
        jPanel5.add(cmbThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 70, 40));

        cmbNam.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jPanel5.add(cmbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 120, 40));

        lblNam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNam.setText("Năm");
        jPanel5.add(lblNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 80, 40));

        btnXuatBaoCao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXuatBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnXuatBaoCao.setText("Xuất báo cáo");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });
        jPanel5.add(btnXuatBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 120, 160, 40));

        cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Theo tháng/năm" }));
        cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHienThiActionPerformed(evt);
            }
        });
        jPanel5.add(cmbHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 140, 40));

        lblThang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblThang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblThang.setText("Tháng:");
        jPanel5.add(lblThang, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 90, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng lương", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lương", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Số điện thoại", "Số ngày đi làm", "Số ngày nghỉ", "Số phép nghỉ", "Tổng lương", "Đơn vị tiên", "Ngày tính lương"
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
        jScrollPane1.setViewportView(tblBangLuong);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhLuongActionPerformed
        daoLuong = new BangLuongNhanVien_DAO();
        daoChamCong = new ChamCongNhanVien_DAO();
        ArrayList<BangLuongNhanVien> listLuong = daoLuong.danhSachBangLuong();
        ArrayList<ChamCongNhanVien> listChamCong = daoChamCong.danhSachChamCongNhanVien();

        if (cmbHienThi.getSelectedIndex() == 0) {
            if (listLuong != null) {
                listLuong.forEach(e -> {
                    listChamCong.forEach(j -> {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-đ");
                            Date date = sdf.parse(j.getNgayChamCong().toString());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            if (e.getNhanVien().getMaNhanVien().equalsIgnoreCase(j.getNhanVien().getMaNhanVien())
                                    && calendar.MONTH + "" == cmbThang.getSelectedItem() && calendar.YEAR + "" == cmbNam.getSelectedItem()) {
                                if (j.getTrangThaiDiLam().contains("Nghỉ")) {
                                    if (j.getTrangThaiDiLam().contains("có")) {
                                        soNgayNghiCoPhep++;
                                        tongLuong += 0;
                                    } else {
                                        soNghiKhongPhep++;
                                        tongLuong = -300000;
                                    }
                                } else {
                                    if (j.getTrangThaiDiLam().contentEquals("Trễ")) {
                                        soNgayLam++;
                                        tongLuong = -50000;
                                    } else {
                                        soNgayLam++;
                                        tongLuong = j.getNhanVien().getLuongThoaThuan() / 26;
                                    }
                                }
                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(LuongNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    model.addRow(new Object[]{model.getRowCount() + 1, e.getMaBangLuong(), e.getNhanVien().getMaNhanVien(),
                        e.getNhanVien().getHoTen(), e.getNhanVien().isGioiTinh() ? "Nam" : "Nữ", e.getNhanVien().getSoDienThoai(), soNgayLam, soNgayNghiCoPhep + soNgayNghiCoPhep});
                });
            }
        }
    }//GEN-LAST:event_btnTinhLuongActionPerformed

    private void btnGuiThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiThongTinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGuiThongTinActionPerformed

    private void cmbThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbThangActionPerformed

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed

        MessageFormat header = new MessageFormat("Bang luong nhân viên theo tháng...");
        MessageFormat footer = new MessageFormat("Nhóm 27");
        try {
            tblBangLuong.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            e.getMessage();
        }

    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHienThiActionPerformed
        taiDuLieuLenBangLuong();
//    
    }//GEN-LAST:event_cmbHienThiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuiThongTin;
    private javax.swing.JButton btnTinhLuong;
    private javax.swing.JButton btnXuatBaoCao;
    private javax.swing.JComboBox<String> cmbHienThi;
    private javax.swing.JComboBox<String> cmbNam;
    private javax.swing.JComboBox<String> cmbThang;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHIenThi;
    private javax.swing.JLabel lblNam;
    private javax.swing.JLabel lblThang;
    private javax.swing.JTable tblBangLuong;
    // End of variables declaration//GEN-END:variables
}
