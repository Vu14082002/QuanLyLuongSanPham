/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.ToNhom_DAO;
import Entity.ToNhom;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class ToNhomView extends javax.swing.JPanel implements MouseListener, ActionListener {

    /**
     * Creates new form TrangChuView
     */
    private ToNhom_DAO toNhom_DAO;
    private DefaultTableModel defaultTablePhongBan;
    private Object oFlag;
    private String khongDeTrong;
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
    private String stTenToTheoMau;
    private String stDaTonTai;

    public ToNhomView(String fileName) throws IOException {
        oFlag = null;
        initComponents();
        caiDatNgonNguChoView(fileName);
        tblToNhom.addMouseListener(this);
        tblToNhom.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblToNhom.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblToNhom.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
//        tbTrangChu.getTableHeader().setForeground(new Color(255,255,255));
        tblToNhom.setRowHeight(25);
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Add sư kiện

        toNhom_DAO = new ToNhom_DAO();
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuy.addActionListener(this);
        btnLuu.addActionListener(this);
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);

        execute();

    }

    public void execute() {
        this.txtMaToNhom.setBackground(new Color(0, 0, 0, 1));
        this.txtMaToNhom.setText("");
        this.txtTenTo.setBackground(new Color(0, 0, 0, 1));
        this.txtTenTo.setText("");
        this.lbErrTenPhongBan.setText("");
        txtMaToNhom.setEditable(false);
        txtTenTo.setEditable(false);
        txtSoLuongCongNhan.setEditable(false);
        defaultTablePhongBan = (DefaultTableModel) tblToNhom.getModel();
        taiDuLieuLenBang();
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        this.khongDeTrong = prop.getProperty("toNhom_khongDeTrong");
        lblMaToNhom.setText(prop.getProperty("toNhom_maToNhom"));
        lblTenTo.setText(prop.getProperty("toNhom_tenToNhom"));
        lblSoLuongCongNhan.setText(prop.getProperty("toNhom_soLuongCongNhan"));
        ChangeName(tblToNhom, 0, prop.getProperty("toNhom_STT"));
        ChangeName(tblToNhom, 1, lblMaToNhom.getText());
        ChangeName(tblToNhom, 2, lblTenTo.getText());
        ChangeName(tblToNhom, 3, lblSoLuongCongNhan.getText());
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
        stSanPham = prop.getProperty("sp_SanPham");
        stKhongDocDuocFile = prop.getProperty("khongDocDuocFile");
        stKhongTimThayFile = prop.getProperty("khongTimThayFile");
        stCapNhatThanhCong = prop.getProperty("capNhatThanhCong");
        stCapNhatThatBai = prop.getProperty("capNhatThatBai");
        stChonMauSacChoSanPham = prop.getProperty("sp_chonMauSacChoSanPham");
        stErrSoLuong = prop.getProperty("sp_lblErrSoLuong");
        stErrKhongDeTrong = prop.getProperty("KhongDeTrong");
        stTenToTheoMau = prop.getProperty("toNhom_tenTheoMau");
        stDaTonTai = prop.getProperty("toNhom_tenDaTonTai");

    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void taiDuLieuLenBang() {
        while (tblToNhom.getRowCount() != 0) {
            defaultTablePhongBan.removeRow(0);
        }
        ArrayList<ToNhom> toNhomList = toNhom_DAO.layDanhSachToNhom();

        if (toNhomList != null) {
            for (ToNhom toNhom : toNhomList) {
                defaultTablePhongBan.addRow(new Object[]{defaultTablePhongBan.getRowCount() + 1, toNhom.getMaToNhom(), toNhom.getTenToNhom(), toNhom.getSoLuongCongNhan()});
            }
            if (tblToNhom.getRowCount() != 0) {
                tblToNhom.setRowSelectionInterval(0, 0);
                hienThiDuLieuLenTxt(0);
            }
        }
    }

    public void hienThiDuLieuLenTxt(int dong) {
        txtMaToNhom.setText(tblToNhom.getValueAt(dong, 1).toString());
        txtTenTo.setText(tblToNhom.getValueAt(dong, 2).toString());
        txtSoLuongCongNhan.setText(tblToNhom.getValueAt(dong, 3).toString());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPhongBan = new javax.swing.JPanel();
        txtMaToNhom = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblTenTo = new javax.swing.JLabel();
        txtTenTo = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        lbErrTenPhongBan = new javax.swing.JLabel();
        lblMaToNhom = new javax.swing.JLabel();
        lblSoLuongCongNhan = new javax.swing.JLabel();
        txtSoLuongCongNhan = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnHuy = new javax.swing.JButton();
        scrPhongBan = new javax.swing.JScrollPane();
        tblToNhom = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        pnlPhongBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhongBan.setPreferredSize(new java.awt.Dimension(1250, 300));
        pnlPhongBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaToNhom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaToNhom.setBorder(null);
        txtMaToNhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaToNhomActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtMaToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 30, 200, 30));
        pnlPhongBan.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 290, 10));

        lblTenTo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenTo.setText("Tên tổ:");
        pnlPhongBan.add(lblTenTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 100, 170, 20));

        txtTenTo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenTo.setText("jTextField1");
        txtTenTo.setBorder(null);
        txtTenTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenToActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTenTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 90, 200, 30));
        pnlPhongBan.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 120, 290, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/group.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        pnlPhongBan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 200, 200));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 240, 160, 40));

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
        pnlPhongBan.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 240, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 160, 40));

        lbErrTenPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenPhongBan.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenPhongBan.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lbErrTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 290, -1));

        lblMaToNhom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaToNhom.setText("Mã tổ:");
        pnlPhongBan.add(lblMaToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 170, 20));

        lblSoLuongCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongCongNhan.setText("Số lượng công nhân:");
        pnlPhongBan.add(lblSoLuongCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 170, 20));

        txtSoLuongCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSoLuongCongNhan.setText("0");
        txtSoLuongCongNhan.setBorder(null);
        txtSoLuongCongNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongCongNhanActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtSoLuongCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 150, 200, 30));
        pnlPhongBan.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 290, 10));

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
        pnlPhongBan.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 240, 170, 40));

        add(pnlPhongBan, java.awt.BorderLayout.PAGE_START);

        scrPhongBan.setBackground(new java.awt.Color(0, 0, 0));

        tblToNhom.getTableHeader().setBackground(new Color(32,136,203));
        tblToNhom.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã phòng bàn", "Tên phòng ban", "Số lương nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblToNhom.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblToNhom.getTableHeader().setReorderingAllowed(false);
        scrPhongBan.setViewportView(tblToNhom);

        add(scrPhongBan, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaToNhomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaToNhomActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaToNhomActionPerformed

    private void txtTenToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenToActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void txtSoLuongCongNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongCongNhanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongCongNhanActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
        txtTenTo.setEditable(false);
    }//GEN-LAST:event_btnHuyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbErrTenPhongBan;
    private javax.swing.JLabel lblMaToNhom;
    private javax.swing.JLabel lblSoLuongCongNhan;
    private javax.swing.JLabel lblTenTo;
    private javax.swing.JPanel pnlPhongBan;
    private javax.swing.JScrollPane scrPhongBan;
    private javax.swing.JTable tblToNhom;
    private javax.swing.JTextField txtMaToNhom;
    private javax.swing.JTextField txtSoLuongCongNhan;
    private javax.swing.JTextField txtTenTo;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblToNhom)) {
            int row = tblToNhom.getSelectedRow();
            if (row != -1) {
                hienThiDuLieuLenTxt(row);
                if (!btnThem.isEnabled()) {
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    btnLuu.setEnabled(false);
                    btnHuy.setEnabled(false);
                    txtTenTo.setEditable(false);
                    lbErrTenPhongBan.setText("");
                }
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

    public void xoaTrang() {
        txtTenTo.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object o = e.getSource();
        if (o.equals(btnThem)) {
            // gán cờ 
            oFlag = e.getSource();
            khoaMoTxt(true);
            xoaTrang();
            // đóng | mở button
            btnCapNhat.setEnabled(false);
            btnXoa.setEnabled(false);
            btnThem.setEnabled(false);
            btnHuy.setEnabled(true);
            btnLuu.setEnabled(true);
//            txtMaToNhom.setText(phongBan_DAO.layRaMaPhongBanDeThem());
            toNhom_DAO = new ToNhom_DAO();
            ArrayList<Entity.ToNhom> list = toNhom_DAO.layDanhSachToNhom();
            if (list != null) {
                txtMaToNhom.setText("TN" + (Integer.parseInt(list.get(list.size() - 1).getMaToNhom().split("N")[1]) + 1));
            } else {
                txtMaToNhom.setText("TN100001");
            }
            txtSoLuongCongNhan.setText("0");

        } else if (o.equals(btnLuu)) {
            System.out.println(oFlag.equals(btnThem));
            if (oFlag.equals(btnThem)) {
                String maPhongBan = txtMaToNhom.getText();
                if (txtTenTo.getText().equals("")) {
                    lbErrTenPhongBan.setText(stErrKhongDeTrong);
                    return;
                } else if (!txtTenTo.getText().toLowerCase().matches("^tổ [1-9][0-9]*$")) {
                    lbErrTenPhongBan.setText(stTenToTheoMau);
                    return;
                } else {
                    lbErrTenPhongBan.setText("");
                }
                ToNhom_DAO toNhomDao = new ToNhom_DAO();
                ArrayList<ToNhom> toNhomList = toNhomDao.layDanhSachToNhom();
                for (ToNhom toNhom : toNhomList) {
                    if (toNhom.getTenToNhom().equalsIgnoreCase(txtTenTo.getText())) {
                        JOptionPane.showMessageDialog(this, stDaTonTai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                boolean coThemDuoc = toNhom_DAO.themToNhom(new ToNhom(maPhongBan, txtTenTo.getText(), 0));
                if (coThemDuoc) {
                    // tải dữ liệu lại vào jtable
                    taiDuLieuLenBang();
                    // đóng mở các button
                    btnCapNhat.setEnabled(true);
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnLuu.setEnabled(false);
                    btnHuy.setEnabled(false);
                    oFlag = null;
                    khoaMoTxt(false); // false là khóa lại, true là mở ra
                    JOptionPane.showMessageDialog(null, stThemThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, stThemThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (oFlag.equals(btnCapNhat)) {
                String maToNhom = txtMaToNhom.getText();
                if (txtTenTo.getText().equals("")) {
                    lbErrTenPhongBan.setText(stErrKhongDeTrong);
                    return;
                } else if (!txtTenTo.getText().toLowerCase().matches("^tổ [1-9][0-9]*$")) {
                    lbErrTenPhongBan.setText(stTenToTheoMau);
                    return;
                } else {
                    lbErrTenPhongBan.setText("");
                }
                int soLuongCongNhan = 0;
                try {
                    soLuongCongNhan = Integer.parseInt(txtSoLuongCongNhan.getText());
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Số lượng nhân viên phải là số");
                }
                String tenTo = txtTenTo.getText().trim();
                boolean coSuaDuoc = toNhom_DAO.suaToNhom(new ToNhom(maToNhom, tenTo, soLuongCongNhan));
                if (coSuaDuoc) {
                    // đóng mở button
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    btnHuy.setEnabled(false);
                    btnLuu.setEnabled(false);
                    oFlag = null;
                    khoaMoTxt(false);
                    taiDuLieuLenBang();
                    JOptionPane.showMessageDialog(null, stCapNhatThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, stCapNhatThatBai, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (o.equals(btnCapNhat)) {
            // Xử lý cập nhật phòng ban
            khoaMoTxt(true);
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnHuy.setEnabled(true);
            btnLuu.setEnabled(true);
            // gán cờ
            oFlag = btnCapNhat;
        } else if (o.equals(btnXoa)) {
            int rowSelected = tblToNhom.getSelectedRow();
            if (rowSelected != -1) {
                int coXacNhanXoa = JOptionPane.showConfirmDialog(null, stBanXacNhanXoa, stThongbao, JOptionPane.ERROR_MESSAGE);
                if (coXacNhanXoa == 0) {
                    boolean coXoaDuoc = toNhom_DAO.xoaMotToNhomTheoMa(tblToNhom.getValueAt(tblToNhom.getSelectedRow(), 1).toString());
                    if (coXoaDuoc) {
                        JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                        taiDuLieuLenBang();
                    } else {
                        JOptionPane.showMessageDialog(null, stXoaThanhCong, stThongbao, JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else if (o.equals(btnHuy)) {
            btnThem.setEnabled(true);
            btnCapNhat.setEnabled(true);
            btnXoa.setEnabled(true);
            btnHuy.setEnabled(false);
            btnLuu.setEnabled(false);
            lbErrTenPhongBan.setText("");
            if (tblToNhom.getSelectedRow() != -1) {
                hienThiDuLieuLenTxt(tblToNhom.getSelectedRow());
            }
        }
    }
    public void khoaMoTxt(boolean b) {
        txtTenTo.setEditable(b);
        txtTenTo.requestFocus();
    }
}
