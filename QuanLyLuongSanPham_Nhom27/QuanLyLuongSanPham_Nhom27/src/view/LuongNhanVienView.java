/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private DefaultTableModel modelDanhSachNhanVienCanChamCong;
    
    public LuongNhanVienView() {
        initComponents();
        excute();
        insertTable();
    }
    
    public void insertTable() {
        modelDanhSachNhanVienCanChamCong = (DefaultTableModel) tblBangLuong.getModel();
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
        modelDanhSachNhanVienCanChamCong.insertRow(modelDanhSachNhanVienCanChamCong.getRowCount(), new Object[]{"1"});
    }
    
    public void excute() {
//        this.txtMaNhanVien.setText("");
//        this.txtMaNhanVien.setBackground(new Color(0, 0, 0, 1));

        // custom table
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
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        btnCapNhat1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangLuong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 250));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnThem.setBackground(new java.awt.Color(46, 204, 113));
        btnThem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnThem.setText("Tính lương");
        btnThem.setBorder(null);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        jPanel5.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("XUất file");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 120, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Gửi thông tin");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 120, 160, 40));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setText("Tháng:");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 90, 40));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel5.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 70, 40));

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        jPanel5.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 120, 40));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setText("Năm");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 80, 40));

        btnCapNhat1.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat1.setText("Xuất báo cáo");
        btnCapNhat1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhat1ActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 120, 160, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng lương", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblBangLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBangLuong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã lương", "Mã nhân viên", "Giới tính", "Số điện thoại", "Số ngày đi làm", "Số ngày nghỉ", "Số phép nghỉ", "Tổng lương", "Đơn vị tiên", "Ngày chấm công"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangLuong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(tblBangLuong);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void btnCapNhat1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhat1ActionPerformed
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(path + "abc.pdf"));
            doc.open();
            PdfPTable tbl = new PdfPTable(11);
            tbl.addCell("STT");
            tbl.addCell("Mã lương");
            tbl.addCell("Mã nhân viên");
            tbl.addCell("Giới tính");
            tbl.addCell("Số điện thoại");
            tbl.addCell("Số ngày đi làm");
            tbl.addCell("Số ngày nghỉ");
            tbl.addCell("Số phép nghỉ");
            tbl.addCell("Tổng lương");
            tbl.addCell("Đơn vị tiền");
            tbl.addCell("Ngày chấm công");
            for (int i = 0; i < tblBangLuong.getRowCount(); i++) {
//                tbl.addCell(tblBangLuong.getValueAt(i, 0).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 1).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 2).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 3).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 4).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 5).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 6).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 7).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 8).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 9).toString());
//                tbl.addCell(tblBangLuong.getValueAt(i, 10).toString());
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
                tbl.addCell("1");
            }
            doc.add(tbl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LuongNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(LuongNhanVienView.class.getName()).log(Level.SEVERE, null, ex);
        }
        doc.close();
        JOptionPane.showMessageDialog(null, "Thanh cong");
    }//GEN-LAST:event_btnCapNhat1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat1;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBangLuong;
    // End of variables declaration//GEN-END:variables
}
