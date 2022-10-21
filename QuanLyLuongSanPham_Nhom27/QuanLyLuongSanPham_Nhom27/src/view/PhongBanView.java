/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.PhongBan_DAO;
import Entity.PhongBan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class PhongBanView extends javax.swing.JPanel implements MouseListener, ActionListener {

    /**
     * Creates new form TrangChuView
     */
    private PhongBan_DAO phongBan_DAO;
    private DefaultTableModel defaultTablePhongBan;

    public PhongBanView() {

        initComponents();
        tblPhongBan.addMouseListener(this);
        tblPhongBan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPhongBan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblPhongBan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
//        tbTrangChu.getTableHeader().setForeground(new Color(255,255,255));
        tblPhongBan.setRowHeight(25);
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Add sư kiện

        phongBan_DAO = new PhongBan_DAO();
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnLuu.addActionListener(this);

        execute();

    }

    public void execute() {
        this.txtMaPhongBan.setBackground(new Color(0, 0, 0, 1));
        this.txtMaPhongBan.setText("");
        this.txtTenPhongBan.setBackground(new Color(0, 0, 0, 1));
        this.txtTenPhongBan.setText("");
        this.lbErrTenPhongBan.setText("");
        txtMaPhongBan.setEditable(false);
        txtTenPhongBan.setEditable(false);
        txtSoLuongNhanVien.setEditable(false);
        defaultTablePhongBan = (DefaultTableModel) tblPhongBan.getModel();
        taiDuLieuLenBang();
    }

    public void taiDuLieuLenBang() {
        while (tblPhongBan.getRowCount() != 0) {
            defaultTablePhongBan.removeRow(0);
        }
        ArrayList<PhongBan> danhSachPhongBan = phongBan_DAO.layDanhSachPhongBan();

        for (PhongBan phongBan : danhSachPhongBan) {
            String data[] = {(defaultTablePhongBan.getRowCount() + 1) + "", phongBan.getMaPhongBan(), phongBan.getTenPhongBan(), phongBan.getSoLuongNhanVien() + ""};
            defaultTablePhongBan.addRow(data);
        }
        if (tblPhongBan.getRowCount() != 0) {
            tblPhongBan.setRowSelectionInterval(0, 0);
            hienThiDuLieuLenTxt(0);
        }
    }

    public void hienThiDuLieuLenTxt(int dong) {
        txtMaPhongBan.setText(tblPhongBan.getValueAt(dong, 1).toString());
        txtTenPhongBan.setText(tblPhongBan.getValueAt(dong, 2).toString());
        txtSoLuongNhanVien.setText(tblPhongBan.getValueAt(dong, 3).toString());
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
        txtMaPhongBan = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblTenPhongBan = new javax.swing.JLabel();
        txtTenPhongBan = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        lbErrTenPhongBan = new javax.swing.JLabel();
        lblMaPhongBan = new javax.swing.JLabel();
        lblSoLuongNhanVien = new javax.swing.JLabel();
        txtSoLuongNhanVien = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        scrPhongBan = new javax.swing.JScrollPane();
        tblPhongBan = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        pnlPhongBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhongBan.setPreferredSize(new java.awt.Dimension(1250, 300));
        pnlPhongBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaPhongBan.setText("jTextField1");
        txtMaPhongBan.setBorder(null);
        txtMaPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaPhongBanActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtMaPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 200, 30));
        pnlPhongBan.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 60, 290, 10));

        lblTenPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenPhongBan.setText("Tên phòng ban:");
        pnlPhongBan.add(lblTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 170, 20));

        txtTenPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenPhongBan.setText("jTextField1");
        txtTenPhongBan.setBorder(null);
        txtTenPhongBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenPhongBanActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 90, 200, 30));
        pnlPhongBan.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 120, 290, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/PhongBan/PhongBanView.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        pnlPhongBan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 200, 200));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 230, 160, 40));

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
        pnlPhongBan.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 230, 160, 40));

        lbErrTenPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenPhongBan.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenPhongBan.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lbErrTenPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 130, 290, -1));

        lblMaPhongBan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaPhongBan.setText("Mã phòng ban:");
        pnlPhongBan.add(lblMaPhongBan, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 170, 20));

        lblSoLuongNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongNhanVien.setText("Số lượng nhân viên:");
        pnlPhongBan.add(lblSoLuongNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 160, 170, 20));

        txtSoLuongNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtSoLuongNhanVien.setText("0");
        txtSoLuongNhanVien.setBorder(null);
        txtSoLuongNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongNhanVienActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtSoLuongNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 150, 200, 30));
        pnlPhongBan.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 180, 290, 10));

        add(pnlPhongBan, java.awt.BorderLayout.PAGE_START);

        scrPhongBan.setBackground(new java.awt.Color(0, 0, 0));

        tblPhongBan.getTableHeader().setBackground(new Color(32,136,203));
        tblPhongBan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhongBan.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tblPhongBan.setRowHeight(25);
        tblPhongBan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblPhongBan.setShowVerticalLines(false);
        tblPhongBan.getTableHeader().setReorderingAllowed(false);
        scrPhongBan.setViewportView(tblPhongBan);

        add(scrPhongBan, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaPhongBanActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaPhongBanActionPerformed

    private void txtTenPhongBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenPhongBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenPhongBanActionPerformed

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

    private void txtSoLuongNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongNhanVienActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbErrTenPhongBan;
    private javax.swing.JLabel lblMaPhongBan;
    private javax.swing.JLabel lblSoLuongNhanVien;
    private javax.swing.JLabel lblTenPhongBan;
    private javax.swing.JPanel pnlPhongBan;
    private javax.swing.JScrollPane scrPhongBan;
    private javax.swing.JTable tblPhongBan;
    private javax.swing.JTextField txtMaPhongBan;
    private javax.swing.JTextField txtSoLuongNhanVien;
    private javax.swing.JTextField txtTenPhongBan;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblPhongBan)) {

            int row = tblPhongBan.getSelectedRow();
            if (row != -1) {
                hienThiDuLieuLenTxt(row);
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
        txtTenPhongBan.setText("");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            if (btnThem.getText().equalsIgnoreCase("Thêm")) {
                btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/delete.png")));
                btnThem.setText("Hủy Thêm");
                khoaMoTxt(true);
                xoaTrang();
                
                btnCapNhat.setVisible(false);
                btnXoa.setVisible(false);
                txtMaPhongBan.setText(phongBan_DAO.layRaMaPhongBanDeThem());
                txtSoLuongNhanVien.setText("0");

            } else {
                btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png")));
                btnThem.setText("Thêm");
                khoaMoTxt(false); // false là khóa lại, true là mở ra
                btnCapNhat.setVisible(true);
                btnXoa.setVisible(true);
                if (tblPhongBan.getSelectedRow() != -1) {
                    hienThiDuLieuLenTxt(tblPhongBan.getSelectedRow());
                }

            }
        } else if (o.equals(btnLuu)) {
            // Xử lý sự kiện lưu
            // Xử lý Thêm phòng ban
            if (btnThem.getText().equalsIgnoreCase("Hủy Thêm")) {
                String maPhongBan = txtMaPhongBan.getText();
                if (txtTenPhongBan.getText().equals("")) {
                    lbErrTenPhongBan.setText("Tên phòng không được trống!");
                    return;
                } else {
                    lbErrTenPhongBan.setText("");
                }
                String tenPhongBan = txtTenPhongBan.getText().trim();
                boolean coThemDuoc = phongBan_DAO.themMotPhongBan(new PhongBan(maPhongBan, tenPhongBan, 0));
                if (coThemDuoc) {
                    taiDuLieuLenBang();
                    btnCapNhat.setVisible(true);
                    btnXoa.setVisible(true);
                    btnThem.setText("Thêm");
                    btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png")));
                    khoaMoTxt(false); // false là khóa lại, true là mở ra
                    JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (btnCapNhat.getText().equalsIgnoreCase("Hủy cập nhật")){
                String maPhongBan = txtMaPhongBan.getText();
                if (txtTenPhongBan.getText().equals("")) {
                    lbErrTenPhongBan.setText("Tên phòng không được trống!");
                    return;
                } else {
                    lbErrTenPhongBan.setText("");
                }
                int soLuongNhanVien = 0;
                try {
                    soLuongNhanVien = Integer.parseInt(txtSoLuongNhanVien.getText());
                } catch (Exception e2) {
                    JOptionPane.showMessageDialog(null, "Số lượng nhân viên phải là số");
                }
                String tenPhongBan = txtTenPhongBan.getText().trim();
                boolean coSuaDuoc = phongBan_DAO.suaMotPhongBan(new PhongBan(maPhongBan, tenPhongBan, soLuongNhanVien));
                if (coSuaDuoc){
                    btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png")));
                    btnThem.setVisible(true);
                    btnXoa.setVisible(true);
                    btnCapNhat.setText("Cập Nhật");
                    khoaMoTxt(false);
                    taiDuLieuLenBang();
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (o.equals(btnCapNhat)){
            // Xử lý cập nhật phòng ban
            if (btnCapNhat.getText().equalsIgnoreCase("Cập nhật")){
                btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/delete.png")));
                btnCapNhat.setText("Hủy cập nhật");
                khoaMoTxt(true);
                btnThem.setVisible(false);
                btnXoa.setVisible(false);
                
            } else {
                btnCapNhat.setText("Cập nhật");
                khoaMoTxt(false);
                btnThem.setVisible(true);
                btnXoa.setVisible(true);
                btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png")));
                if (tblPhongBan.getSelectedRow() != -1) {
                    hienThiDuLieuLenTxt(tblPhongBan.getSelectedRow());
                }
            }
        } else if (o.equals(btnXoa)){
            
            int rowSelected = tblPhongBan.getSelectedRow();
            if (rowSelected != -1){
                int coXacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa Phòng ban", JOptionPane.ERROR_MESSAGE);
                if (coXacNhanXoa == 0){
                    boolean coXoaDuoc = phongBan_DAO.xoaMotPhongBanTheoMa(tblPhongBan.getValueAt(tblPhongBan.getSelectedRow(), 1).toString());
                    if (coXoaDuoc){
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        taiDuLieuLenBang();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
            
        }
    }

    public void khoaMoTxt(boolean b) {
        txtTenPhongBan.setEditable(b);
        txtTenPhongBan.requestFocus();
        
    }
}