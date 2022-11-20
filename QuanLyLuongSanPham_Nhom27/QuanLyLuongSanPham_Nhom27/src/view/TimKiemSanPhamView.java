/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.SanPham_DAO;
import Entity.SanPham;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class TimKiemSanPhamView extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form NhanVienView
     */
    private SanPham_DAO sanPham_DAO;
    private DefaultTableModel modelTableSanPham;
    private String stThongBao;
    private String stTimKiemKhongThay;

    public TimKiemSanPhamView(String fileName) throws IOException {
        initComponents();
        caiDatNgonNguChoView(fileName);
        modelTableSanPham = (DefaultTableModel) tblSanPham.getModel();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sanPham_DAO = new SanPham_DAO();
        btnTimKiem.addActionListener(this);
        excute();
        lblErrTenSanPham.setText("");
        taiDuLieuLenBang("all", "all", "all", "all");
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaSanPham.setText(prop.getProperty("sp_maSanPham"));
        lblTenSanPham.setText(prop.getProperty("sp_tenSanPham"));
        lblChatLieu.setText(prop.getProperty("sp_chatLieu"));
        lblKichThuoc.setText(prop.getProperty("sp_kichThuoc"));
        ChangeName(tblSanPham, 0, prop.getProperty("sp_stt"));
        ChangeName(tblSanPham, 1, prop.getProperty("sp_maSanPham"));
        ChangeName(tblSanPham, 2, prop.getProperty("sp_tenSanPham"));
        ChangeName(tblSanPham, 3, prop.getProperty("sp_soLuong"));
        ChangeName(tblSanPham, 4, prop.getProperty("sp_mauSac"));
        ChangeName(tblSanPham, 5, prop.getProperty("sp_chatLieu"));
        ChangeName(tblSanPham, 6, prop.getProperty("sp_kichThuoc"));
        ChangeName(tblSanPham, 7, prop.getProperty("sp_anhSanPham"));
        btnTimKiem.setText(prop.getProperty("Main_lblTimKiem"));
        stThongBao = prop.getProperty("thongBao");
        stTimKiemKhongThay=prop.getProperty("timKiem_KhongThay");
        cmbKichThuoc.removeItemAt(cmbKichThuoc.getItemCount()-1);
        cmbKichThuoc.addItem(prop.getProperty("cmbTatCa"));
        cmbKichThuoc.setSelectedIndex(cmbKichThuoc.getItemCount()-1);
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void excute() {

        // custom table
        tblSanPham.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblSanPham.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblSanPham.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblSanPham.setRowHeight(25);
        ButtonGroup btnGroup = new ButtonGroup();

    }

    public void taiDuLieuLenBang(String ma, String ten, String kichThuoc, String chatLieu) {
        while (tblSanPham.getRowCount() != 0) {
            modelTableSanPham.removeRow(0);
        }
        ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPham();

        for (SanPham sanPham : dsSanPham) {
            if (!ma.equalsIgnoreCase("all") || !ten.equalsIgnoreCase("all")
                    || !kichThuoc.equalsIgnoreCase("all") || !chatLieu.equalsIgnoreCase("all")) {
                boolean flag = true;
                if (!ma.equalsIgnoreCase("all") && !sanPham.getMaSanPham().equalsIgnoreCase(ma)) {
                    flag = false;
                }
                if (!ten.equalsIgnoreCase("all") && !sanPham.getTenSanPham().equalsIgnoreCase(ten)) {
                    flag = false;
                }
                if (!chatLieu.equalsIgnoreCase("all") && !(sanPham.getChatLieu() + "").equalsIgnoreCase(chatLieu)) {
                    flag = false;

                }
                if (!kichThuoc.equalsIgnoreCase("all") && !(sanPham.getKichThuoc() + "").equalsIgnoreCase(kichThuoc)) {
                    flag = false;
                }
                if (flag) {
                    String data[] = {(modelTableSanPham.getRowCount() + 1) + "", sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getSoLuongSanPham() + "",
                        sanPham.getMauSac(), sanPham.getChatLieu(), sanPham.getKichThuoc() + "", sanPham.getAnhSanPham(), sanPham.getSoLuongCongDoan() + ""};
                    modelTableSanPham.addRow(data);
                }
            } else {
                String data[] = {(modelTableSanPham.getRowCount() + 1) + "", sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getSoLuongSanPham() + "",
                    sanPham.getMauSac(), sanPham.getChatLieu(), sanPham.getKichThuoc() + "", sanPham.getAnhSanPham(), sanPham.getSoLuongCongDoan() + ""};
                modelTableSanPham.addRow(data);
            }
        }
        if (tblSanPham.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, stTimKiemKhongThay, stThongBao, JOptionPane.INFORMATION_MESSAGE);
        }
        if (tblSanPham.getRowCount() != 0) {

            tblSanPham.setRowSelectionInterval(0, 0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        srcTableSanPham = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        lblErrTenSanPham = new javax.swing.JLabel();
        lblBorderTenSp = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        lblTenSanPham = new javax.swing.JLabel();
        lblBorderMaSP = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        lblMaSanPham = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();
        lblChatLieu = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        cmbKichThuoc = new javax.swing.JComboBox<>();
        lblBorderMaSP2 = new javax.swing.JLabel();
        txtChatLieu = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        srcTableSanPham.setBackground(new java.awt.Color(255, 255, 255));
        srcTableSanPham.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Màu sắc", "Chất liệu ", "Kích thước", "Ảnh sản phẩm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        srcTableSanPham.setViewportView(tblSanPham);

        add(srcTableSanPham, java.awt.BorderLayout.CENTER);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 300));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTenSanPham.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 80, 280, -1));

        lblBorderTenSp.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblBorderTenSp.setText("_______________________________");
        jPanel5.add(lblBorderTenSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 60, 290, 20));

        txtTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham.setBorder(null);
        jPanel5.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 40, 270, 40));

        lblTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTenSanPham.setText("Tên sản phẩm:");
        jPanel5.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 140, 40));

        lblBorderMaSP.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblBorderMaSP.setText("_______________________________");
        jPanel5.add(lblBorderMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 290, 30));

        txtMaSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaSanPham.setBorder(null);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });
        jPanel5.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 270, 40));

        lblMaSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMaSanPham.setText("Mã sản phẩm:");
        jPanel5.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 140, 40));

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });
        jPanel5.add(btnTimKiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 230, 40));

        lblChatLieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblChatLieu.setText("Chất liệu:");
        jPanel5.add(lblChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 110, 140, 40));

        lblKichThuoc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblKichThuoc.setText("Kích thước");
        jPanel5.add(lblKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 110, 140, 40));

        cmbKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "Tất cả" }));
        cmbKichThuoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel5.add(cmbKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 110, 200, 40));

        lblBorderMaSP2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblBorderMaSP2.setText("_______________________________");
        jPanel5.add(lblBorderMaSP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 290, 20));

        txtChatLieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtChatLieu.setBorder(null);
        jPanel5.add(txtChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 270, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaSanPhamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblBorderMaSP;
    private javax.swing.JLabel lblBorderMaSP2;
    private javax.swing.JLabel lblBorderTenSp;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblErrTenSanPham;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JScrollPane srcTableSanPham;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtChatLieu;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnTimKiem)) {
            String maSanPham = txtMaSanPham.getText().trim();
            String tenSanPham = txtTenSanPham.getText().trim();
            String chatLieu = txtChatLieu.getText().trim();
            String kichThuoc = cmbKichThuoc.getSelectedItem().toString();
            if (maSanPham.trim().equalsIgnoreCase("")) {
                maSanPham = "all";
            }
            if (tenSanPham.equalsIgnoreCase("")) {
                tenSanPham = "all";
            }
            if (chatLieu.equalsIgnoreCase("")) {
                chatLieu = "all";
            }
            if (cmbKichThuoc.getSelectedIndex()==cmbKichThuoc.getItemCount()-1) {
                kichThuoc = "all";
            }
            taiDuLieuLenBang(maSanPham, tenSanPham, kichThuoc, chatLieu);
        }
    }
}
