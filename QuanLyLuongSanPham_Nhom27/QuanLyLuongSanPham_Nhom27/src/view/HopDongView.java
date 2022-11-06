/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.HopDong_DAO;
import Entity.HopDong;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class HopDongView extends javax.swing.JPanel {

    /**
     * Creates new form HopDongView1
     */
    private DefaultTableModel model;
    private HopDong_DAO hopDongDao;
    private boolean isThem = false;

    public HopDongView() throws ParseException {
        initComponents();
        excute();
        taiDuLieuLenTable();
    }

    public void excute() {
        model = (DefaultTableModel) tblHopDong.getModel();
        tblHopDong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblHopDong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblHopDong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblHopDong.setRowHeight(25);
        this.txtMaHopDong.setText("");
        this.txtTenKhachHang.setText("");
        txtTienCoc.setText("");
        txtTongTien.setText("");
        dcsNgayKyKet.setDate(new Date());
        dcsHanHopDong.setDate(new Date());
        txtAreaYeuCau.setText("");

        lblErrTenKhachHang.setText("");
        lblErrTienCoc.setText("");
        lblErrTongTien.setText("");
        lblErrNgayKiKet.setText("");
        lblErrHanHopDong.setText("");
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);
    }

    public void taiDuLieuLenTable() throws ParseException {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        hopDongDao = new HopDong_DAO();
        ArrayList<HopDong> hopDongList = hopDongDao.layDanhSachHopDong();
        if (hopDongList != null) {
            hopDongList.forEach(e -> {
                DecimalFormat dcm = new DecimalFormat("###,###,###,###.### VND");
                String tienCoc = dcm.format(e.getSoTienCoc());
                String tongTien = dcm.format(e.getTongTien());
                model.addRow(new Object[]{model.getRowCount() + 1, e.getMaHopDong(), e.getTenKhachHang(), tienCoc, tongTien, e.getNgayKyKet(), e.getHanChot()});
            });
        }
        if (tblHopDong.getRowCount() != 0) {
            tblHopDong.setRowSelectionInterval(0, 0);
            hienThiDuLieuLenTxt(0);
        }
    }

    public void hienThiDuLieuLenTxt(int dong) throws ParseException {
        hopDongDao = new HopDong_DAO();
        HopDong hd = hopDongDao.layRaMotHopDongTheoMaHopDong(tblHopDong.getValueAt(dong, 1).toString());
        txtMaHopDong.setText(tblHopDong.getValueAt(dong, 1).toString());
        txtTenKhachHang.setText(tblHopDong.getValueAt(dong, 2).toString());
        txtTienCoc.setText(tblHopDong.getValueAt(dong, 3).toString().split(" ")[0].replaceAll(",", ""));
        txtTongTien.setText(tblHopDong.getValueAt(dong, 4).toString().split(" ")[0].replaceAll(",", ""));
        dcsNgayKyKet.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblHopDong.getValueAt(dong, 5).toString()));
        dcsHanHopDong.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(tblHopDong.getValueAt(dong, 6).toString()));
        txtAreaYeuCau.setText(hd.getYeuCau());
        setEnAbleForSelected(false);

    }

    public void setEnAbleForSelected(boolean check) {
        txtTenKhachHang.setEditable(check);
        txtMaHopDong.setEditable(false);
        txtTienCoc.setEditable(check);
        txtTongTien.setEditable(check);
        dcsNgayKyKet.setEnabled(check);
        dcsHanHopDong.setEnabled(check);
        txtAreaYeuCau.setEditable(check);
    }

    public void setShow(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(true);
        }
    }

    public void setHidden(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPhongBan = new javax.swing.JPanel();
        txtMaHopDong = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lbltenKhachHang = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        btnLuu = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        lblErrTongTien = new javax.swing.JLabel();
        lblMaHopDong = new javax.swing.JLabel();
        lblSoLuongNhanVien = new javax.swing.JLabel();
        txtTienCoc = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnHuy = new javax.swing.JButton();
        lblTongTien = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        lblTienCoc = new javax.swing.JLabel();
        dcsNgayKyKet = new com.toedter.calendar.JDateChooser();
        lblErrTenKhachHang = new javax.swing.JLabel();
        lblErrNgayKiKet = new javax.swing.JLabel();
        lblHanHopDong = new javax.swing.JLabel();
        dcsHanHopDong = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaYeuCau = new javax.swing.JTextArea();
        lblNgayKyKet = new javax.swing.JLabel();
        lblErrHanHopDong = new javax.swing.JLabel();
        lblErrTienCoc = new javax.swing.JLabel();
        scrPhongBan = new javax.swing.JScrollPane();
        tblHopDong = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 725));
        setLayout(new java.awt.BorderLayout());

        pnlPhongBan.setBackground(new java.awt.Color(255, 255, 255));
        pnlPhongBan.setPreferredSize(new java.awt.Dimension(1250, 450));
        pnlPhongBan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtMaHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtMaHopDong.setText("txtMa");
        txtMaHopDong.setBorder(null);
        txtMaHopDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHopDongActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtMaHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 200, 30));
        pnlPhongBan.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 70, 200, 10));

        lbltenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbltenKhachHang.setText("Tên khách hàng:");
        pnlPhongBan.add(lbltenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 50, 170, 20));

        txtTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTenKhachHang.setText("jTextField1");
        txtTenKhachHang.setBorder(null);
        txtTenKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhachHangActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 40, 200, 30));
        pnlPhongBan.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 70, 200, 10));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/IconViewContract.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        pnlPhongBan.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 200, 200));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 390, 160, 40));

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
        pnlPhongBan.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 390, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnlPhongBan.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 160, 40));

        lblErrTongTien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTongTien.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTongTien.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 140, 210, -1));

        lblMaHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaHopDong.setText("Mã hợp đồng:");
        pnlPhongBan.add(lblMaHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 170, 20));

        lblSoLuongNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongNhanVien.setText("Yêu cầu:");
        pnlPhongBan.add(lblSoLuongNhanVien, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 170, 30));

        txtTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTienCoc.setText("0");
        txtTienCoc.setBorder(null);
        txtTienCoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienCocActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 100, 200, 30));
        pnlPhongBan.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 130, 200, 10));

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
        pnlPhongBan.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 390, 170, 40));

        lblTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTongTien.setText("Tổng tiền:");
        pnlPhongBan.add(lblTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 110, 170, 20));

        txtTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTongTien.setText("0");
        txtTongTien.setBorder(null);
        txtTongTien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTongTienActionPerformed(evt);
            }
        });
        pnlPhongBan.add(txtTongTien, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 200, 30));
        pnlPhongBan.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 130, 200, 10));

        lblTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTienCoc.setText("Số tiền cọc:");
        pnlPhongBan.add(lblTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 110, 170, 20));

        dcsNgayKyKet.setDateFormatString("yyyy-MM-dd");
        pnlPhongBan.add(dcsNgayKyKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, 200, -1));

        lblErrTenKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTenKhachHang.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTenKhachHang.setText("lblErrTenKhachHang");
        pnlPhongBan.add(lblErrTenKhachHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 80, 290, -1));

        lblErrNgayKiKet.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrNgayKiKet.setForeground(new java.awt.Color(204, 0, 0));
        lblErrNgayKiKet.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrNgayKiKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 210, -1));

        lblHanHopDong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHanHopDong.setText("Hạn hợp đồng:");
        pnlPhongBan.add(lblHanHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 170, 170, 30));

        dcsHanHopDong.setDateFormatString("yyyy-MM-dd");
        pnlPhongBan.add(dcsHanHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 170, 200, -1));

        txtAreaYeuCau.setColumns(20);
        txtAreaYeuCau.setRows(5);
        jScrollPane1.setViewportView(txtAreaYeuCau);

        pnlPhongBan.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 650, -1));

        lblNgayKyKet.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayKyKet.setText("Ngày ký kết:");
        pnlPhongBan.add(lblNgayKyKet, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, 170, 30));

        lblErrHanHopDong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrHanHopDong.setForeground(new java.awt.Color(204, 0, 0));
        lblErrHanHopDong.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrHanHopDong, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 200, 210, -1));

        lblErrTienCoc.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTienCoc.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTienCoc.setText("đây là dòng thông báo lỗi");
        pnlPhongBan.add(lblErrTienCoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 140, 210, -1));

        add(pnlPhongBan, java.awt.BorderLayout.PAGE_START);

        scrPhongBan.setBackground(new java.awt.Color(0, 0, 0));

        tblHopDong.getTableHeader().setBackground(new Color(32,136,203));
        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hợp đồng", "Tên khách hàng", "Số tiền cọc", "Tổng tiền", "Ngày ký kết", "Hạn hợp đồng:"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHopDong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblHopDong.getTableHeader().setReorderingAllowed(false);
        tblHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHopDongMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblHopDongMousePressed(evt);
            }
        });
        scrPhongBan.setViewportView(tblHopDong);

        add(scrPhongBan, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaHopDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHopDongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHopDongActionPerformed

    private void txtTenKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhachHangActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        try {
            if (isThem) {

            }
        } finally {
            isThem = false;
        }
    }//GEN-LAST:event_btnLuuActionPerformed
    public boolean checkInput() {
        boolean check = true;
        if (txtTenKhachHang.equals("")) {
            lblErrTenKhachHang.setText("Bat buoc nhap");
            check = false;
        } else if (!txtTenKhachHang.getText().matches("^([A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổ"
                + "ẵẻỡơôưăêâđ]+)((\\s[A-ZĐÂÁƯ][a-zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$")) {
            lblErrTenKhachHang.setText("HO ten khong hop le");
            check = false;
        } else {
            lblErrTenKhachHang.setText("");
        }
        boolean checkTien = false;
        if (this.txtTienCoc.getText().equals("")) {
            this.lblErrTienCoc.setText("Bắt buộc nhập");
            check = false;
        } else if (!this.txtTienCoc.getText().replaceAll(",", "").matches("^[1-9][0-9]*$")) {
            this.lblTienCoc.setText("Số tiền nhập không hợp lệ");
            check = false;
        } else {
            this.lblErrTienCoc.setText("");
            checkTien = true;
        }
        if (this.txtTongTien.getText().equals("")) {
            this.lblErrTongTien.setText("Bắt buộc nhập");
            check = false;
        } else if (!this.txtTongTien.getText().replaceAll(",", "").matches("^[1-9][0-9]*$")) {
            this.lblErrTongTien.setText("Số tiền nhập không hợp lệ");
            check = false;
        } else {
            if (checkTien) {
                if(Double.parseDouble(txtTienCoc.getText())>= Double.parseDouble(txtTongTien.getText()))
                this.lblErrTongTien.setText("tong tien >= tien coc");
                check=false;
            }else{
                lblErrTongTien.setText("");
            }
        }
        
        return check;
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        setShow(btnHuy, btnLuu);
        setHidden(btnThem, btnXoa, btnCapNhat);
        if (tblHopDong.getRowHeight() > 0) {
            txtMaHopDong.setText("HD" + (Integer.parseInt(tblHopDong.getValueAt(tblHopDong.getRowCount() - 1, 1).toString().split("D")[1]) + 1));
        } else {
            txtMaHopDong.setText("HD100001");
        }
        this.isThem = true;
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Ban xac nhan xoa", "Xoa du lieu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            hopDongDao = new HopDong_DAO();
            if (hopDongDao.xoaMotHopDong(tblHopDong.getValueAt(tblHopDong.getSelectedRow(), 1).toString())) {
                JOptionPane.showMessageDialog(this, "Xoa thanh cong", "Thong bao", JOptionPane.INFORMATION_MESSAGE);
                try {
                    taiDuLieuLenTable();
                } catch (ParseException ex) {
                    Logger.getLogger(HopDongView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(this, "uii, co van de, ban vui long reset  lai chuong trinh", "Thong bao", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void txtTienCocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienCocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienCocActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        setShow(btnThem, btnXoa, btnCapNhat);
        setHidden(btnHuy, btnXoa);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtTongTienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTongTienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTongTienActionPerformed

    private void tblHopDongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMousePressed
    }//GEN-LAST:event_tblHopDongMousePressed

    private void tblHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMouseClicked
        try {
            hienThiDuLieuLenTxt(tblHopDong.getSelectedRow());
            // TODO add your handling code here:
        } catch (ParseException ex) {
            Logger.getLogger(HopDongView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tblHopDongMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private com.toedter.calendar.JDateChooser dcsHanHopDong;
    private com.toedter.calendar.JDateChooser dcsNgayKyKet;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblErrHanHopDong;
    private javax.swing.JLabel lblErrNgayKiKet;
    private javax.swing.JLabel lblErrTenKhachHang;
    private javax.swing.JLabel lblErrTienCoc;
    private javax.swing.JLabel lblErrTongTien;
    private javax.swing.JLabel lblHanHopDong;
    private javax.swing.JLabel lblMaHopDong;
    private javax.swing.JLabel lblNgayKyKet;
    private javax.swing.JLabel lblSoLuongNhanVien;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JLabel lbltenKhachHang;
    private javax.swing.JPanel pnlPhongBan;
    private javax.swing.JScrollPane scrPhongBan;
    private javax.swing.JTable tblHopDong;
    private javax.swing.JTextArea txtAreaYeuCau;
    private javax.swing.JTextField txtMaHopDong;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables
}
