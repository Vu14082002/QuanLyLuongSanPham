/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.io.File;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;

/**
 *
 * @author Student
 */
public class CapNhatSanPhamView extends javax.swing.JFrame {

    /**
     * Creates new form modalThemNhanSuView
     */
    public CapNhatSanPhamView() {
        initComponents();
        init();
    }

    public void init() {
        this.txtMaSanPham.setText("");
        this.txtTenSanPham.setText("");
        this.lbErrTenSanPham.setText("");
        this.txtSoLuongSanPham.setText("");
        this.lbErrSoLuongSanPham.setText("");
    
        this.lbErrSoLuongSanPham.setText("");
        

        this.txtMaSanPham.setBackground(new Color(0, 0, 0, 1));
        this.txtTenSanPham.setBackground(new Color(0, 0, 0, 1));
        this.txtSoLuongSanPham.setBackground(new Color(0, 0, 0, 1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panelBorder1 = new CustomView.PanelBorder();
        lbTieuDeThemSanPham = new javax.swing.JLabel();
        lbExitModal = new javax.swing.JLabel();
        lbMaSanPham = new javax.swing.JLabel();
        lbGachChanMaNhanVien = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        lbTenSanPham = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        lbGachChanHoVaTenNhanVien = new javax.swing.JLabel();
        lbAnhSanPham = new javax.swing.JLabel();
        lbErrTenSanPham = new javax.swing.JLabel();
        lbSoLuongSanPham = new javax.swing.JLabel();
        txtSoLuongSanPham = new javax.swing.JTextField();
        lbGachChanSoCCCDNhanVien = new javax.swing.JLabel();
        lbErrSoLuongSanPham = new javax.swing.JLabel();
        lbMauSac = new javax.swing.JLabel();
        lbChatLieuSanPham = new javax.swing.JLabel();
        lbKichThuocSanPham = new javax.swing.JLabel();
        btnLuuSanPham = new javax.swing.JButton();
        btnThoatSanPham = new javax.swing.JButton();
        cbChatLieu = new javax.swing.JComboBox<>();
        cbKichThuocSanPham = new javax.swing.JComboBox<>();
        btnMauSac = new javax.swing.JPanel();
        lbMauSacOfBtn = new javax.swing.JLabel();
        btnAnhSanPham = new javax.swing.JPanel();
        lbAnhSanPhamOfbtn = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBorder1.setBackground(new java.awt.Color(204, 102, 255));

        lbTieuDeThemSanPham.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        lbTieuDeThemSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTieuDeThemSanPham.setText("Cập nhật sản phẩm");
        panelBorder1.add(lbTieuDeThemSanPham);
        lbTieuDeThemSanPham.setBounds(50, 10, 490, 50);

        lbExitModal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        lbExitModal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbExitModal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbExitModalMouseClicked(evt);
            }
        });
        panelBorder1.add(lbExitModal);
        lbExitModal.setBounds(540, 20, 30, 30);

        lbMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbMaSanPham.setText("Mã sản phẩm:");
        panelBorder1.add(lbMaSanPham);
        lbMaSanPham.setBounds(200, 90, 230, 18);

        lbGachChanMaNhanVien.setText("_______________________________________");
        panelBorder1.add(lbGachChanMaNhanVien);
        lbGachChanMaNhanVien.setBounds(200, 120, 234, 14);

        txtMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtMaSanPham.setText("jTextField1");
        txtMaSanPham.setBorder(null);
        txtMaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaSanPhamActionPerformed(evt);
            }
        });
        panelBorder1.add(txtMaSanPham);
        txtMaSanPham.setBounds(200, 110, 270, 20);

        lbTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbTenSanPham.setText("Tên sản phẩm:");
        panelBorder1.add(lbTenSanPham);
        lbTenSanPham.setBounds(200, 160, 230, 20);

        txtTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTenSanPham.setText("jTextField1");
        txtTenSanPham.setBorder(null);
        txtTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenSanPhamActionPerformed(evt);
            }
        });
        panelBorder1.add(txtTenSanPham);
        txtTenSanPham.setBounds(200, 180, 270, 20);

        lbGachChanHoVaTenNhanVien.setText("_______________________________________");
        panelBorder1.add(lbGachChanHoVaTenNhanVien);
        lbGachChanHoVaTenNhanVien.setBounds(200, 190, 234, 14);

        lbAnhSanPham.setBackground(new java.awt.Color(153, 0, 0));
        lbAnhSanPham.setForeground(new java.awt.Color(255, 0, 51));
        lbAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sanPham/giay1.png"))); // NOI18N
        panelBorder1.add(lbAnhSanPham);
        lbAnhSanPham.setBounds(20, 130, 140, 160);

        lbErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lbErrTenSanPham.setText("đây là dòng thông báo lỗi");
        panelBorder1.add(lbErrTenSanPham);
        lbErrTenSanPham.setBounds(200, 210, 230, 18);

        lbSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbSoLuongSanPham.setText("Số lượng:");
        panelBorder1.add(lbSoLuongSanPham);
        lbSoLuongSanPham.setBounds(200, 230, 230, 18);

        txtSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtSoLuongSanPham.setText("jTextField1");
        txtSoLuongSanPham.setBorder(null);
        txtSoLuongSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongSanPhamActionPerformed(evt);
            }
        });
        panelBorder1.add(txtSoLuongSanPham);
        txtSoLuongSanPham.setBounds(200, 250, 270, 20);

        lbGachChanSoCCCDNhanVien.setText("_______________________________________");
        panelBorder1.add(lbGachChanSoCCCDNhanVien);
        lbGachChanSoCCCDNhanVien.setBounds(200, 260, 234, 14);

        lbErrSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbErrSoLuongSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lbErrSoLuongSanPham.setText("đây là dòng thông báo lỗi");
        panelBorder1.add(lbErrSoLuongSanPham);
        lbErrSoLuongSanPham.setBounds(200, 280, 230, 18);

        lbMauSac.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbMauSac.setText("Màu sắc chủ đạo:");
        panelBorder1.add(lbMauSac);
        lbMauSac.setBounds(200, 300, 230, 18);

        lbChatLieuSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbChatLieuSanPham.setText("Chất liệu sản phẩm:");
        panelBorder1.add(lbChatLieuSanPham);
        lbChatLieuSanPham.setBounds(200, 390, 230, 18);

        lbKichThuocSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lbKichThuocSanPham.setText("Kích thước");
        panelBorder1.add(lbKichThuocSanPham);
        lbKichThuocSanPham.setBounds(200, 480, 140, 18);

        btnLuuSanPham.setBackground(new java.awt.Color(39, 174, 96));
        btnLuuSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuuSanPham.setText("Lưu");
        btnLuuSanPham.setBorder(null);
        btnLuuSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBorder1.add(btnLuuSanPham);
        btnLuuSanPham.setBounds(160, 530, 130, 40);

        btnThoatSanPham.setBackground(new java.awt.Color(231, 76, 60));
        btnThoatSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnThoatSanPham.setText("Thoát");
        btnThoatSanPham.setBorder(null);
        btnThoatSanPham.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnThoatSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatSanPhamActionPerformed(evt);
            }
        });
        panelBorder1.add(btnThoatSanPham);
        btnThoatSanPham.setBounds(320, 530, 130, 40);

        cbChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cbChatLieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vải Polyester", "Nylon", "Vải 210D", "Vải 420D", "Vải 600D", "Vải 900D", "Vải canvas", "Vải nỉ", "Vải Siminli", "Vải cotton", "Vải kaki", "Vải jeans", "Vải kate", "Vải len", " " }));
        cbChatLieu.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        panelBorder1.add(cbChatLieu);
        cbChatLieu.setBounds(200, 420, 270, 40);

        cbKichThuocSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46" }));
        panelBorder1.add(cbKichThuocSanPham);
        cbKichThuocSanPham.setBounds(340, 480, 70, 30);

        lbMauSacOfBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMauSacOfBtn.setText("Chọn màu");
        lbMauSacOfBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbMauSacOfBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbMauSacOfBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnMauSacLayout = new javax.swing.GroupLayout(btnMauSac);
        btnMauSac.setLayout(btnMauSacLayout);
        btnMauSacLayout.setHorizontalGroup(
            btnMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMauSacOfBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        btnMauSacLayout.setVerticalGroup(
            btnMauSacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbMauSacOfBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panelBorder1.add(btnMauSac);
        btnMauSac.setBounds(200, 330, 270, 40);

        lbAnhSanPhamOfbtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAnhSanPhamOfbtn.setText("Ảnh sản phẩm");

        javax.swing.GroupLayout btnAnhSanPhamLayout = new javax.swing.GroupLayout(btnAnhSanPham);
        btnAnhSanPham.setLayout(btnAnhSanPhamLayout);
        btnAnhSanPhamLayout.setHorizontalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbAnhSanPhamOfbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        btnAnhSanPhamLayout.setVerticalGroup(
            btnAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbAnhSanPhamOfbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelBorder1.add(btnAnhSanPham);
        btnAnhSanPham.setBounds(30, 290, 120, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(600, 620));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThoatSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatSanPhamActionPerformed
        if(evt.getSource()==this.btnThoatSanPham)
        this.dispose();
    }//GEN-LAST:event_btnThoatSanPhamActionPerformed

    private void txtSoLuongSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongSanPhamActionPerformed

    private void txtTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenSanPhamActionPerformed

    private void txtMaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaSanPhamActionPerformed

    }//GEN-LAST:event_txtMaSanPhamActionPerformed

    private void lbExitModalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbExitModalMouseClicked
        this.dispose();
    }//GEN-LAST:event_lbExitModalMouseClicked

    private void lbMauSacOfBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbMauSacOfBtnMouseClicked
        Color colordefault = new Color(this.btnMauSac.getBackground().getRGB());
        JColorChooser jchooserColor = new JColorChooser();
        Color color = jchooserColor.showDialog(this, "Chọn màu sắc cho sản phẩm", this.getBackground());
//        System.out.println(panelBorder1.getColorModel());
        if(color!= null && color.getRGB()!= panelBorder1.getBackground().getRGB())
            this.btnMauSac.setBackground(color);
        else
            this.btnMauSac.setBackground(colordefault);
    }//GEN-LAST:event_lbMauSacOfBtnMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CapNhatSanPhamView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CapNhatSanPhamView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnAnhSanPham;
    private javax.swing.JButton btnLuuSanPham;
    private javax.swing.JPanel btnMauSac;
    private javax.swing.JButton btnThoatSanPham;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbChatLieu;
    private javax.swing.JComboBox<String> cbKichThuocSanPham;
    private javax.swing.JLabel lbAnhSanPham;
    private javax.swing.JLabel lbAnhSanPhamOfbtn;
    private javax.swing.JLabel lbChatLieuSanPham;
    private javax.swing.JLabel lbErrSoLuongSanPham;
    private javax.swing.JLabel lbErrTenSanPham;
    private javax.swing.JLabel lbExitModal;
    private javax.swing.JLabel lbGachChanHoVaTenNhanVien;
    private javax.swing.JLabel lbGachChanMaNhanVien;
    private javax.swing.JLabel lbGachChanSoCCCDNhanVien;
    private javax.swing.JLabel lbKichThuocSanPham;
    private javax.swing.JLabel lbMaSanPham;
    private javax.swing.JLabel lbMauSac;
    private javax.swing.JLabel lbMauSacOfBtn;
    private javax.swing.JLabel lbSoLuongSanPham;
    private javax.swing.JLabel lbTenSanPham;
    private javax.swing.JLabel lbTieuDeThemSanPham;
    private CustomView.PanelBorder panelBorder1;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoLuongSanPham;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}