/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.BangLuongCongNhan_DAO;
import DAO.ChamCongCongNhan_DAO;
import DAO.CongNhan_DAO;
import Entity.ChamCongCongNhan;
import Entity.CongNhan;
import java.awt.Color;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class ChiTietLuongCongNhan extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietLuongCongNhan
     */
    private String maCongNhan;
    private int thang;
    private int nam;
    private CongNhan_DAO congNhan_DAO;
    private BangLuongCongNhan_DAO bangLuongCN_DAO;
    private ChamCongCongNhan_DAO chamCongCN_DAO;
    private DecimalFormat nf, df;
    private DefaultTableModel modelTableChiTiet;

    public ChiTietLuongCongNhan(String fileName,String maCongNhan, int thang, int nam) throws IOException {
        this.maCongNhan = maCongNhan;
        this.thang = thang;
        this.nam = nam;
        nf = new DecimalFormat("#,###.00");
        df = new DecimalFormat("#");
        initComponents();
        execute();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        congNhan_DAO = new CongNhan_DAO();
        bangLuongCN_DAO = new BangLuongCongNhan_DAO();
        chamCongCN_DAO = new ChamCongCongNhan_DAO();
        modelTableChiTiet = (DefaultTableModel) tblBangLuongChiTiet.getModel();
        lblTitle.setText("Chi tiết Lương trong tháng " + thang + " năm " + nam);
        hienThiLenLabel();
        taiDuLieuLenBang();
        caiDatNgonNguChoView(fileName);
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaCongNhan.setText(prop.getProperty("maCongNhan"));
        lblHoTen.setText(prop.getProperty("hoTen"));
        lblTongTien.setText(prop.getProperty("ctlnv_tongTienNhan"));
        lblTitle.setText(prop.getProperty("ctlnv_tieuDeCongNhan"));
        btnXuatBaoCao.setText(prop.getProperty("ctlnv_btnXuatBaoCao"));
        ChangeName(tblBangLuongChiTiet, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblBangLuongChiTiet, 1, prop.getProperty("ctlnv_ngayLam"));
        ChangeName(tblBangLuongChiTiet, 2, prop.getProperty("ctlnv_trangThai"));
        ChangeName(tblBangLuongChiTiet, 3, prop.getProperty("ctlcn_maSanPham"));
        ChangeName(tblBangLuongChiTiet, 4, prop.getProperty("ctlcn_tenSanPham"));
        ChangeName(tblBangLuongChiTiet, 5, prop.getProperty("ctlcn_maCongDoan"));
        ChangeName(tblBangLuongChiTiet, 6, prop.getProperty("ctlcn_tenCongDoan"));
        ChangeName(tblBangLuongChiTiet, 7, prop.getProperty("ctlnv_caLam"));
        ChangeName(tblBangLuongChiTiet, 8, prop.getProperty("ctlcn_soLuong"));
        ChangeName(tblBangLuongChiTiet, 9, prop.getProperty("ctlcn_thanhTien"));
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void hienThiLenLabel() {
        CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
        Double tongLuong = bangLuongCN_DAO.layRaTongTienTheoMaCongNhan(maCongNhan, thang, nam);
        lblMaCongNhanOutput.setText(congNhan.getMaCongNhan());
        lblHoTenOutPut.setText(congNhan.getHoTen());
        String tienLuong = nf.format(tongLuong) + " VND";
        lblTongTienNhan.setText(tienLuong);
    }

    private void execute() {
        Container c = this.getContentPane();
        c.setBackground(Color.white);
    }

    public void taiDuLieuLenBang() {
        while (tblBangLuongChiTiet.getRowCount() != 0) {
            modelTableChiTiet.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCongTheoMaCongNhan(maCongNhan, thang, nam);
        for (ChamCongCongNhan chamCong : dsChamCong) {
            double tongTien = chamCong.getSoLuongLam() * chamCong.getPhanCong().getCongDoan().getTienLuong();
            String data[] = {(modelTableChiTiet.getRowCount() + 1) + "", chamCong.getNgayChamCong().toString(),
                chamCong.getTrangThaiDiLam(), chamCong.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
                chamCong.getPhanCong().getCongDoan().getSanPham().getTenSanPham(),
                chamCong.getPhanCong().getCongDoan().getMaCongDoan(),
                chamCong.getPhanCong().getCongDoan().getTenCongDoan(),
                chamCong.getCaLam(), chamCong.getSoLuongLam() + "", nf.format(tongTien)};
            modelTableChiTiet.addRow(data);
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

        jPanel1 = new javax.swing.JPanel();
        scrChiTietBangLuong = new javax.swing.JScrollPane();
        tblBangLuongChiTiet = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblMaCongNhanOutput = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblHoTenOutPut = new javax.swing.JLabel();
        lblMaCongNhan = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblTongTienNhan = new javax.swing.JLabel();
        btnXuatBaoCao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        scrChiTietBangLuong.setBackground(new java.awt.Color(255, 255, 255));
        scrChiTietBangLuong.setBorder(null);

        tblBangLuongChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblBangLuongChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Ngày làm", "Trạng thái", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên công đoạn", "Ca làm", "Số lượng", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangLuongChiTiet.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrChiTietBangLuong.setViewportView(tblBangLuongChiTiet);

        jPanel1.add(scrChiTietBangLuong, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(153, 153, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 250));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMaCongNhanOutput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongNhanOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaCongNhanOutput.setText("CN001");
        jPanel2.add(lblMaCongNhanOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 270, 40));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Chi tiết lương công nhân trong tháng");
        jPanel2.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 860, 70));

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoTen.setText("Họ và tên:");
        jPanel2.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 130, 40));

        lblHoTenOutPut.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTenOutPut.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoTenOutPut.setText("Nguyễn Van A");
        jPanel2.add(lblHoTenOutPut, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 300, 40));

        lblMaCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaCongNhan.setText("Mã công nhân:");
        jPanel2.add(lblMaCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 130, 40));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongTien.setText("Tổng tiền nhận:");
        jPanel2.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 130, 40));

        lblTongTienNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTienNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongTienNhan.setText("1000000000000 vnd");
        jPanel2.add(lblTongTienNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 300, 40));

        btnXuatBaoCao.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXuatBaoCao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnXuatBaoCao.setText("Xuất báo cáo");
        btnXuatBaoCao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatBaoCaoActionPerformed(evt);
            }
        });
        jPanel2.add(btnXuatBaoCao, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 160, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 10, 60, 50));

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1010, 738));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        // TODO add your handling code here:
        MessageFormat header = new MessageFormat("Lương " + lblHoTenOutPut.getText() + "(" + lblMaCongNhanOutput.getText() + ") " + thang + "/" + nam);
        MessageFormat footer = new MessageFormat("Tổng lương: " + lblTongTienNhan.getText());
        try {
            tblBangLuongChiTiet.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            e.getMessage();
        }
//             MessageFormat header = new MessageFormat("Lương " + lblHoTenOutPut.getText()+ "(" + lblMaCongNhanOutput.getText() + ") " +  thang + "/" + nam);
//        MessageFormat footer = new MessageFormat("Tổng lương: " + lblTongTienNhan.getText());
//        try {
//            tblBangLuongChiTiet.print(JTable.PrintMode.FIT_WIDTH, header, footer);
//        } catch (Exception e) {
//            e.getMessage();
//        }
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ChiTietLuongCongNhan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
////                new ChiTietLuongCongNhan("NV100001", 10, 2022).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatBaoCao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblHoTenOutPut;
    private javax.swing.JLabel lblMaCongNhan;
    private javax.swing.JLabel lblMaCongNhanOutput;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lblTongTienNhan;
    private javax.swing.JScrollPane scrChiTietBangLuong;
    private javax.swing.JTable tblBangLuongChiTiet;
    // End of variables declaration//GEN-END:variables
}
