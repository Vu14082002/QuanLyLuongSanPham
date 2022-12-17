/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import DAO.ChamCongNhanVien_DAO;
import Entity.ChamCongNhanVien;
import java.awt.Color;
import java.awt.Container;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class ChiTietLuongNhanVien extends javax.swing.JFrame {

    /**
     * Creates new form ChiTietLuongCongNhan
     */
    public ChiTietLuongNhanVien(String fileName,String maNhanVien, String hoVaTen, String tongTien, String thang, String nam) throws IOException {
        initComponents();
        execute();
        lblValueMaNhanVien.setText(maNhanVien);
        lblValueHoVaTen.setText(hoVaTen);
        lblValueTongTien.setText(tongTien);
        lblTitle.setText("Chi tiết lương nhân viên trong tháng " + thang + "-" + nam);
        taiDuLieuLenBangChiTietLuong(maNhanVien, thang, nam, tongTien);
        caiDatNgonNguChoView(fileName);
        lblTitle.setText( lblTitle.getText()+" "+ thang + "-" + nam);
    }

    private void execute() {
        Container c = this.getContentPane();
        c.setBackground(Color.white);
    }

    public void taiDuLieuLenBangChiTietLuong(String maNhanVien, String thang, String nam, String tongTien) {
        System.out.println(maNhanVien + "-" + thang + "-" + nam);
        DefaultTableModel model = (DefaultTableModel) tblChiTietLuongNhanVien.getModel();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        ChamCongNhanVien_DAO chamCongDao = new ChamCongNhanVien_DAO();
        ArrayList<ChamCongNhanVien> chamCongList = chamCongDao.layDanhSachChamCongTheoNhanVienThangNam(maNhanVien, thang, nam);
        for (ChamCongNhanVien chamCong : chamCongList) {
            model.addRow(new Object[]{model.getRowCount() + 1, chamCong.getNgayChamCong(), chamCong.getCaLam(), chamCong.getTrangThaiDiLam(), chamCong.getGioDiLam()});
        }

    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblMaNhanVien.setText(prop.getProperty("maNhanVien"));
        lblHoVaTen.setText(prop.getProperty("hoTen"));
        lblTongTienNhan.setText(prop.getProperty("ctlnv_tongTienNhan"));
        lblTitle.setText(prop.getProperty("ctlnv_tieuDe"));
        btnXuatBaoCao.setText(prop.getProperty("ctlnv_btnXuatBaoCao"));
        ChangeName(tblChiTietLuongNhanVien, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblChiTietLuongNhanVien, 1, prop.getProperty("ctlnv_ngayLam"));
        ChangeName(tblChiTietLuongNhanVien, 2, prop.getProperty("ctlnv_caLam"));
        ChangeName(tblChiTietLuongNhanVien, 3, prop.getProperty("ctlnv_trangThai"));
        ChangeName(tblChiTietLuongNhanVien, 4, prop.getProperty("ctlnv_gioDiLam"));
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChiTietLuongNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        lblValueMaNhanVien = new javax.swing.JLabel();
        lblHoVaTen = new javax.swing.JLabel();
        lblValueHoVaTen = new javax.swing.JLabel();
        lblMaNhanVien = new javax.swing.JLabel();
        lblTongTienNhan = new javax.swing.JLabel();
        lblValueTongTien = new javax.swing.JLabel();
        btnXuatBaoCao = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);

        tblChiTietLuongNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChiTietLuongNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Ngày làm", "Ca làm", "Trạng thái", "Giờ đi làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietLuongNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        jScrollPane1.setViewportView(tblChiTietLuongNhanVien);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(153, 153, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1000, 250));
        jPanel2.setRequestFocusEnabled(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblValueMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueMaNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblValueMaNhanVien.setText("NV001");
        jPanel2.add(lblValueMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 270, 40));

        lblHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoVaTen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblHoVaTen.setText("Họ và tên:");
        jPanel2.add(lblHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 130, 40));

        lblValueHoVaTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueHoVaTen.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblValueHoVaTen.setText("Nguyễn Van A");
        jPanel2.add(lblValueHoVaTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 300, 40));

        lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaNhanVien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaNhanVien.setText("Mã nhân viên");
        jPanel2.add(lblMaNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 130, 40));

        lblTongTienNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTienNhan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTongTienNhan.setText("Tổng tiền nhận:");
        jPanel2.add(lblTongTienNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 130, 40));

        lblValueTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueTongTien.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblValueTongTien.setText("1000000000000 vnd");
        jPanel2.add(lblValueTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 300, 40));

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

        lblTitle.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("Chi tiết lương nhân viên trong tháng");
        jPanel2.add(lblTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 70));

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1010, 738));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXuatBaoCaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatBaoCaoActionPerformed
        MessageFormat header = new MessageFormat(lblTitle.getText());
        MessageFormat footer = new MessageFormat("Nhóm 27");
        try {
            tblChiTietLuongNhanVien.print(JTable.PrintMode.FIT_WIDTH, header, footer);
        } catch (Exception e) {
            e.getMessage();
        }

//        btnXuatBaoCao.setVisible(false);
//        PrinterJob job = PrinterJob.getPrinterJob();
//            job.setJobName("Print Data");
//            
//            job.setPrintable(new Printable(){
//            public int print(Graphics pg,PageFormat pf, int pageNum){
//                    pf.setOrientation(PageFormat.LANDSCAPE);
//                 if(pageNum>0){
//                    return Printable.NO_SUCH_PAGE;
//                }
//                
//                Graphics2D g2 = (Graphics2D)pg;
//                g2.translate(pf.getImageableX(), pf.getImageableY());
//                g2.scale(0.24,0.24);
//                
//                jPanel1.paint(g2);
////          
//               
//                return Printable.PAGE_EXISTS;
//                         
//                
//            }
//    });
//         
//        boolean ok = job.printDialog();
//        if(ok){
//        try{
//            
//        job.print();
//        }
//        catch (PrinterException ex){}
//        }
//          btnXuatBaoCao.setVisible(true);
    }//GEN-LAST:event_btnXuatBaoCaoActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

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
            java.util.logging.Logger.getLogger(ChiTietLuongNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChiTietLuongNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChiTietLuongNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChiTietLuongNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new ChiTietLuongNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuatBaoCao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHoVaTen;
    private javax.swing.JLabel lblMaNhanVien;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTongTienNhan;
    private javax.swing.JLabel lblValueHoVaTen;
    private javax.swing.JLabel lblValueMaNhanVien;
    private javax.swing.JLabel lblValueTongTien;
    private javax.swing.JTable tblChiTietLuongNhanVien;
    // End of variables declaration//GEN-END:variables
}