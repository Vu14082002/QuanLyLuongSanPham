/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.BangLuongCongNhan_DAO;
import DAO.ChamCongCongNhan_DAO;
import Entity.BangLuongCongNhan;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class LuongCongNhanView extends javax.swing.JPanel implements ActionListener {

    private DefaultTableModel modelTableChamCong;
    private ChamCongCongNhan_DAO chamCongCN_DAO;
    private BangLuongCongNhan_DAO bangLuongCN_DAO;
    private DecimalFormat nf, df;

    /**
     * Creates new form NhanVienView
     */
    public LuongCongNhanView() {
        initComponents();
        excute();
        nf = new DecimalFormat("#,###.00");
        df = new DecimalFormat("#");
        modelTableChamCong = (DefaultTableModel) tblPhanCong.getModel();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        chamCongCN_DAO = new ChamCongCongNhan_DAO();
        bangLuongCN_DAO = new BangLuongCongNhan_DAO();
        btnLuuThongTin.addActionListener(this);
        btnTinhLuong.addActionListener(this);
        btnXuatBaoCao.addActionListener(this);
        btnXuatBaoCao.setEnabled(false);
        setNamChoCmbNam();
        cmbThangTinh.addItemListener(this::checkThangChon);
        cmbHienThi.addItemListener(this::hienThiBangLuongTheoNgay);
        taiDuLieuLenBang();
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
                if(tblPhanCong.getRowCount() != 0){
                    btnXuatBaoCao.setEnabled(true);
                } 
            }
            
        }
    }

    public void taiDuLieuLenBangTheoNgayThang() {
        int thang = Integer.parseInt((cmbThangTinh.getSelectedItem().toString()));
        int nam = Integer.parseInt(cmbNamTinh.getSelectedItem().toString());
        while (tblPhanCong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<BangLuongCongNhan> dsBangLuong = bangLuongCN_DAO.layDanhSachBangLuongTheoThangNam(thang, nam);
        for (BangLuongCongNhan bangLuong : dsBangLuong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", bangLuong.getMaBangLuong(), bangLuong.getCongNhan().getMaCongNhan(),
                bangLuong.getCongNhan().getHoTen(), bangLuong.getCongNhan().getMaCCCD(), bangLuong.getSoNgayDiLam() + "",
                bangLuong.getSoNgayNghi() + "", bangLuong.getSoPhepNghi() + "", bangLuong.getDonViTien(), bangLuong.getNgayTinh().toString(),
                nf.format(bangLuong.getTongLuong())};
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
        while (tblPhanCong.getRowCount() != 0) {
            modelTableChamCong.removeRow(0);
        }
        ArrayList<BangLuongCongNhan> dsBangLuong = bangLuongCN_DAO.layDanhSachBangLuongCongNhan();
        for (BangLuongCongNhan bangLuong : dsBangLuong) {
            String data[] = {(modelTableChamCong.getRowCount() + 1) + "", bangLuong.getMaBangLuong(), bangLuong.getCongNhan().getMaCongNhan(),
                bangLuong.getCongNhan().getHoTen(), bangLuong.getCongNhan().getMaCCCD(), bangLuong.getSoNgayDiLam() + "",
                bangLuong.getSoNgayNghi() + "", bangLuong.getSoPhepNghi() + "", bangLuong.getDonViTien(), bangLuong.getNgayTinh().toString(),
                nf.format(bangLuong.getTongLuong())};
            modelTableChamCong.addRow(data);
        }
    }

    public void excute() {
//        this.txtMaNhanVien.setText("");
//        this.txtMaNhanVien.setBackground(new Color(0, 0, 0, 1));

        // custom table
//        tbDanhSachCanChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
//        tbDanhSachCanChamCong.getTableHeader().setOpaque(false);
//        ((DefaultTableCellRenderer) tbDanhSachCanChamCong.getTableHeader().getDefaultRenderer())
//                .setHorizontalAlignment(JLabel.CENTER);
//        tbDanhSachCanChamCong.setRowHeight(25);
        tblPhanCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPhanCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblPhanCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblPhanCong.setRowHeight(25);
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
        btnLuuThongTin = new javax.swing.JButton();
        lblThang = new javax.swing.JLabel();
        cmbThangTinh = new javax.swing.JComboBox<>();
        cmbNamTinh = new javax.swing.JComboBox<>();
        lblNam = new javax.swing.JLabel();
        btnXuatBaoCao = new javax.swing.JButton();
        lblHienThi = new javax.swing.JLabel();
        cmbHienThi = new javax.swing.JComboBox<>();
        scrBangLuong = new javax.swing.JScrollPane();
        tblPhanCong = new javax.swing.JTable();

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

        btnLuuThongTin.setBackground(new java.awt.Color(156, 136, 255));
        btnLuuThongTin.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuuThongTin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuuThongTin.setText("Gửi thông tin");
        btnLuuThongTin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuThongTinActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuuThongTin, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, 160, 40));

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

        tblPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblPhanCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lương", "Mã công nhân", "Tên công Nhân", "Mã CCCD", "Số ngày đi làm", "Số ngày có phép", "Số ngày nghỉ không phép", "Đơn vị tiền", "Ngày tính lương", "Tổng Lương"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhanCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhanCongMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblPhanCongMouseReleased(evt);
            }
        });
        scrBangLuong.setViewportView(tblPhanCong);

        add(scrBangLuong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTinhLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTinhLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTinhLuongActionPerformed

    private void btnLuuThongTinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuThongTinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuThongTinActionPerformed

    private void cmbThangTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbThangTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbThangTinhActionPerformed

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Bảng Lương Công Nhân trong tháng " + cmbThangTinh.getSelectedItem()
        + " năm " + cmbNamTinh.getSelectedItem().toString());
        MessageFormat footer = new MessageFormat("Nhóm 27");
        try {
            tblPhanCong.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void tblPhanCongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanCongMousePressed
        JTable table = (JTable) evt.getSource();
        Point point = evt.getPoint();
        int row = table.rowAtPoint(point);
        if (evt.getClickCount() == 2 && tblPhanCong.getSelectedRow() != -1) {
            int rowSelected = tblPhanCong.getSelectedRow();
            LocalDate date = LocalDate.parse(tblPhanCong.getValueAt(row, 9).toString());
            int thang = date.getMonthValue();
            int nam = date.getYear();

            new ChiTietLuongCongNhan(tblPhanCong.getValueAt(row, 2).toString(), thang, nam).setVisible(true);
        }
    }//GEN-LAST:event_tblPhanCongMousePressed

    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHienThiActionPerformed

    }//GEN-LAST:event_cmbHienThiActionPerformed

    private void tblPhanCongMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanCongMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tblPhanCongMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuThongTin;
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
    private javax.swing.JTable tblPhanCong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTinhLuong)) {
            int thang = Integer.parseInt(cmbThangTinh.getSelectedItem().toString());
            int nam = Integer.parseInt(cmbNamTinh.getSelectedItem().toString());
            boolean coTinhDuoc = bangLuongCN_DAO.kiemTraKhaThiChoTinhThangNay(thang, nam);
            if (!coTinhDuoc) {
                JOptionPane.showMessageDialog(null, "Đã tính lương cho tháng " + cmbThangTinh.getSelectedItem().toString()
                        + ", năm " + cmbNamTinh.getSelectedItem().toString() + " rồi!");
                return;
            }
            boolean tinhLuong = bangLuongCN_DAO.tinhLuongCongNhan(thang, nam);
            if (tinhLuong) {
                JOptionPane.showMessageDialog(null, "Tính lương cho tháng " + cmbThangTinh.getSelectedItem().toString()
                        + ", năm " + cmbNamTinh.getSelectedItem().toString() + " thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                taiDuLieuLenBang();
            } else {
                JOptionPane.showMessageDialog(null, "Tính lương cho tháng " + cmbThangTinh.getSelectedItem().toString()
                        + ", năm " + cmbNamTinh.getSelectedItem().toString() + " thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }

        }
    }
}
