/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.CongDoan_DAO;
import DAO.CongNhan_DAO;
import DAO.NhanVien_DAO;
import DAO.PhanCongCongNhan_DAO;
import DAO.SanPham_DAO;
import DAO.ToNhom_DAO;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.NhanVien;
import Entity.PhanCongCongNhan;
import Entity.SanPham;
import Entity.ToNhom;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class PhanCongCongViecView extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private DefaultTableModel modelDanhSachNhanVienCanChamCong;
    private DefaultTableModel modelSanPham;
    private DefaultTableModel modelPhanCong;
    private SanPham_DAO daoSanPham;
    private SanPham entitySanPham;
    private CongDoan_DAO daoCongDoan;
    private CongDoan entityCongDoan;
    private CongNhan entityCongNhan;
    private CongNhan_DAO daoCongNhan;
    private PhanCongCongNhan_DAO daoPhanCong;
    private boolean checkPhanCong = false;

    public PhanCongCongViecView() {
        initComponents();
        excute();
        taiDuLieuLenBangSanPham();
    }

    public void excute() {
        modelPhanCong = (DefaultTableModel) tblPhanCong.getModel();
        modelSanPham = (DefaultTableModel) tblSanPham.getModel();
        tblSanPham.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblSanPham.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblSanPham.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblSanPham.setRowHeight(25);

        tblPhanCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPhanCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblPhanCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblPhanCong.setRowHeight(25);
        setShow(btnPhanCong);
        setHidden(btnLuu, btnHuy, btnXoa, btnCapNhat);
        while (modelPhanCong.getRowCount() != 0) {
            modelPhanCong.removeRow(0);
        }
        ToNhom_DAO daoToNhom = new ToNhom_DAO();
        ArrayList<ToNhom> toNhomList = daoToNhom.layDanhSachToNhom();
        cmbToNhom.removeAllItems();
        toNhomList.forEach(e -> {
            if (e.getSoLuongCongNhan() > 0) {
                cmbToNhom.addItem(e.getMaToNhom());
            }
        });
        dcsNgayPhanCong.setDate(new Date());
    }

    public void taiDuLieuLenBangSanPham() {
        while (modelSanPham.getRowCount() != 0) {
            modelSanPham.removeRow(0);
        }
        daoSanPham = new SanPham_DAO();
        ArrayList<SanPham> listSanPham = daoSanPham.layDanhSachSanPham();
        daoCongDoan = new CongDoan_DAO();
        if (listSanPham != null) {
            listSanPham.forEach(e -> {
                ArrayList<CongDoan> cd = daoCongDoan.layDanhSachCongDoanTheoMaSP(e.getMaSanPham());
                if (cd.size() > 1) {
                    modelSanPham.addRow(new Object[]{modelSanPham.getRowCount() + 1, e.getMaSanPham(), e.getTenSanPham(), e.getSoLuongSanPham()});
                }
            });
        }
        if (tblSanPham.getRowCount() != 0) {
            tblSanPham.setRowSelectionInterval(0, 0);
        }
    }

    public void setHidden(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(false);
        }
    }

    public void setShow(JButton... btn) {
        for (JButton jButton : btn) {
            jButton.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        lblValueSoLuongCanLam = new javax.swing.JLabel();
        lblValueMaPhanCong = new javax.swing.JLabel();
        lblMaCongDoan = new javax.swing.JLabel();
        lblTenCongDoan = new javax.swing.JLabel();
        lblValueTenCongDoan = new javax.swing.JLabel();
        lblToNhom = new javax.swing.JLabel();
        lblNgayPhanCong = new javax.swing.JLabel();
        aaaa = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        cmbToNhom = new javax.swing.JComboBox<>();
        cmbMaCongDoan = new javax.swing.JComboBox<>();
        btnPhanCong = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblSoLuongCanLam1 = new javax.swing.JLabel();
        dcsNgayPhanCong = new com.toedter.calendar.JDateChooser();
        lblMaPhanCong = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhanCong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblValueSoLuongCanLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueSoLuongCanLam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblValueSoLuongCanLam.setText("1000");
        jPanel5.add(lblValueSoLuongCanLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 220, 170, 40));

        lblValueMaPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueMaPhanCong.setText("PC001");
        jPanel5.add(lblValueMaPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 180, 40));

        lblMaCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaCongDoan.setText("Mã công đoạn");
        jPanel5.add(lblMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 190, 40));

        lblTenCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenCongDoan.setText("Tên công đoạn");
        jPanel5.add(lblTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 160, 190, 40));

        lblValueTenCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblValueTenCongDoan.setText("Rap giày");
        jPanel5.add(lblValueTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 160, 180, 40));

        lblToNhom.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblToNhom.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblToNhom.setText("Tổ/Nhóm thực hiện:");
        jPanel5.add(lblToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 280, 190, 40));

        lblNgayPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayPhanCong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNgayPhanCong.setText("Ngày phân công:");
        jPanel5.add(lblNgayPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, 190, 40));

        aaaa.setBackground(new java.awt.Color(255, 255, 255));
        aaaa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N
        aaaa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aaaaMouseClicked(evt);
            }
        });

        tblSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Tên sản phẩm", "Số lượng cần"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        aaaa.setViewportView(tblSanPham);

        jPanel5.add(aaaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 660, 270));

        cmbToNhom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tổ 1", "Tổ 2", "Tổ 3", "Tổ 4", "Tổ 5", "Tổ 6", "Tổ 7", "Tổ 8", "Tổ 9", "Tổ 10" }));
        cmbToNhom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbToNhomActionPerformed(evt);
            }
        });
        jPanel5.add(cmbToNhom, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 280, 140, 40));

        cmbMaCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CD001", "CD002", "CD003" }));
        cmbMaCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaCongDoanActionPerformed(evt);
            }
        });
        jPanel5.add(cmbMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 110, 140, 40));

        btnPhanCong.setBackground(new java.awt.Color(46, 204, 113));
        btnPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnPhanCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnPhanCong.setText("Phân công");
        btnPhanCong.setBorder(null);
        btnPhanCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanCongActionPerformed(evt);
            }
        });
        jPanel5.add(btnPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 170, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        jPanel5.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 370, 160, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        jPanel5.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 160, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        jPanel5.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 370, 160, 40));

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
        jPanel5.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 370, 170, 40));

        lblSoLuongCanLam1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongCanLam1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoLuongCanLam1.setText("Số lượng cần làm:");
        jPanel5.add(lblSoLuongCanLam1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 220, 170, 40));

        dcsNgayPhanCong.setDateFormatString("yyyy-MM-dd");
        jPanel5.add(dcsNgayPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 180, 40));

        lblMaPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaPhanCong.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaPhanCong.setText("Mã phân công:");
        jPanel5.add(lblMaPhanCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 60, 190, 40));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách phân công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblPhanCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblPhanCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã phân công", "Mã sản phẩm", "Tên sản phẩm", "Mã công đoạn", "Tên  công đoạn", "Tổ/Nhóm", "Số lượng cần", "Ngày phân công"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhanCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblPhanCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhanCongMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhanCong);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPhanCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanCongActionPerformed
        setHidden(btnCapNhat, btnPhanCong, btnXoa);
        setShow(btnLuu, btnHuy);
        checkPhanCong = true;
        daoPhanCong = new PhanCongCongNhan_DAO();
        ArrayList<PhanCongCongNhan> pc = daoPhanCong.layDanhSachPhanCongCongNhan();
        if (pc.size() > 0) {
            int maSo = Integer.parseInt(pc.get(pc.size() - 1).getMaPhanCong().split("C")[1]) + 1;
            lblValueMaPhanCong.setText("PC" + maSo);
        } else {
            lblValueMaPhanCong.setText("PC100001");
        }
    }//GEN-LAST:event_btnPhanCongActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn xác nhận muốn xóa", null, JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            daoPhanCong = new PhanCongCongNhan_DAO();
            if (daoPhanCong.xoaMotPhanCongTheoMaPhanCong(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 1).toString())) {
                taiDuLieuLanBangPhanCong();
                if (modelPhanCong.getRowCount() != 0) {
                    tblPhanCong.setRowSelectionInterval(0, 0);
                }
                JOptionPane.showMessageDialog(null, "Thành công");

            }
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (checkPhanCong) {
            daoCongNhan = new CongNhan_DAO();
            daoCongDoan = new CongDoan_DAO();
            CongDoan congDoan = daoCongDoan.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());
            NhanVien_DAO daoNhanVien = new NhanVien_DAO();
            NhanVien nv = daoNhanVien.layMotNhanVienTheoMaNhanVien("NV100001");
            ArrayList<CongNhan> congNhanList = daoCongNhan.layDanhSachCongNhan();
            try {
                if (congNhanList != null) {
                    congNhanList.forEach(e -> {
                        if (e.getToNhom().getMaToNhom().equalsIgnoreCase(cmbToNhom.getSelectedItem().toString())) {
                            ToNhom toNhom = new ToNhom(e.getToNhom().getMaToNhom(), e.getToNhom().getTenToNhom(), e.getToNhom().getSoLuongCongNhan());
                            String maPhanCong = "";
                            ArrayList<PhanCongCongNhan> pc = daoPhanCong.layDanhSachPhanCongCongNhan();
                            if (pc.size() > 0) {
                                int maSo = Integer.parseInt(pc.get(pc.size() - 1).getMaPhanCong().split("C")[1]) + 1;
                                maPhanCong = "PC" + maSo;
                            } else {
                                maPhanCong = "PC100001";
                            }

                            PhanCongCongNhan phanCong = new PhanCongCongNhan(maPhanCong, e, congDoan, nv, dcsNgayPhanCong.getDate(), toNhom);
                            daoPhanCong.themMotPhanCongNhan(phanCong);
                        }
                    });
                }
            } finally {
                taiDuLieuLanBangPhanCong();
            }

        } else {
            daoPhanCong = new PhanCongCongNhan_DAO();
            ArrayList<PhanCongCongNhan> phanCongList = daoPhanCong.layDanhSachPhanCongCongNhan();
            phanCongList.forEach(e -> {

            });
        }
        checkPhanCong = false;
        setShow(btnPhanCong);
        setHidden(btnCapNhat, btnXoa, btnHuy, btnLuu);
    }//GEN-LAST:event_btnLuuActionPerformed
    public void taiDuLieuLanBangPhanCong() {
        while (modelPhanCong.getRowCount() != 0) {
            modelPhanCong.removeRow(0);
        }
        daoCongDoan = new CongDoan_DAO();
        daoPhanCong = new PhanCongCongNhan_DAO();
        daoSanPham = new SanPham_DAO();
        ArrayList<PhanCongCongNhan> listPhanCong = daoPhanCong.layDanhSachPhanCongCongNhan();
        SanPham sp = daoSanPham.layMotSanPhamTheoMa(tblSanPham.getValueAt(tblSanPham.getSelectedRow(), 1).toString());
        ArrayList<CongDoan> listCongDoan = daoCongDoan.layDanhSachCongDoanTheoMaSP(sp.getMaSanPham());
        cmbMaCongDoan.removeAllItems();
        boolean check = false;
        for (CongDoan congDoan : listCongDoan) {
            for (PhanCongCongNhan phanCong : listPhanCong) {
                if (congDoan.getMaCongDoan().equalsIgnoreCase(phanCong.getCongDoan().getMaCongDoan())) {
                    check = true;
                }
            }
            if (!check) {
                cmbMaCongDoan.addItem(congDoan.getMaCongDoan());
            }
            check = false;
        }

        boolean check2 = false;
        if (listPhanCong != null) {
            for (PhanCongCongNhan e : listPhanCong) {
                if (e.getCongDoan().getSanPham().getMaSanPham().equals(sp.getMaSanPham())) {
                    modelPhanCong.addRow(new Object[]{e.getMaPhanCong(), e.getCongDoan().getSanPham().getMaSanPham(),
                        e.getCongDoan().getSanPham().getTenSanPham(), e.getCongDoan().getMaCongDoan(), e.getCongDoan().getTenCongDoan(),
                        e.getCongNhan().getToNhom().getMaToNhom(), e.getCongDoan().getSoLuongCan(), e.getNgayPhanCong()});
                }
            }
        }
        for (int i = 0; i < modelPhanCong.getRowCount(); i++) {
            for (int j = i+1; j < modelPhanCong.getRowCount(); j++) {
                if(tblPhanCong.getValueAt(i, 6).toString().equalsIgnoreCase(tblPhanCong.getValueAt(j, 6).toString())){
                    modelPhanCong.removeRow(j);
                }
            }
        }
    }
    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        taiDuLieuLanBangPhanCong();
        setShow(btnPhanCong);
        setHidden(btnCapNhat, btnXoa, btnHuy, btnLuu);
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void cmbMaCongDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaCongDoanActionPerformed
        daoCongDoan = new CongDoan_DAO();
        try {
            CongDoan cd = daoCongDoan.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString());
            if (cd != null) {
                lblValueTenCongDoan.setText(cd.getTenCongDoan());
            }
        } catch (Exception e) {
        }

    }//GEN-LAST:event_cmbMaCongDoanActionPerformed

    private void cmbToNhomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbToNhomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbToNhomActionPerformed

    private void tblPhanCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhanCongMouseClicked
        lblValueMaPhanCong.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 1).toString());
        cmbMaCongDoan.setSelectedItem(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 4).toString());
        lblValueTenCongDoan.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 5).toString());
        lblValueSoLuongCanLam.setText(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 7).toString());
        cmbToNhom.setSelectedItem(tblPhanCong.getValueAt(tblPhanCong.getSelectedRow(), 6).toString());
        setHidden(btnLuu, btnHuy, btnPhanCong);
        setShow(btnCapNhat, btnXoa);

    }//GEN-LAST:event_tblPhanCongMouseClicked

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        setShow(btnPhanCong);
        setHidden(btnHuy, btnLuu, btnCapNhat, btnXoa);
    }//GEN-LAST:event_btnHuyActionPerformed

    private void aaaaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aaaaMouseClicked
        setHidden(btnCapNhat, btnHuy, btnLuu, btnCapNhat);
        setShow(btnPhanCong);
        checkPhanCong = false;
    }//GEN-LAST:event_aaaaMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane aaaa;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnPhanCong;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbMaCongDoan;
    private javax.swing.JComboBox<String> cmbToNhom;
    private com.toedter.calendar.JDateChooser dcsNgayPhanCong;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMaCongDoan;
    private javax.swing.JLabel lblMaPhanCong;
    private javax.swing.JLabel lblNgayPhanCong;
    private javax.swing.JLabel lblSoLuongCanLam1;
    private javax.swing.JLabel lblTenCongDoan;
    private javax.swing.JLabel lblToNhom;
    private javax.swing.JLabel lblValueMaPhanCong;
    private javax.swing.JLabel lblValueSoLuongCanLam;
    private javax.swing.JLabel lblValueTenCongDoan;
    private javax.swing.JTable tblPhanCong;
    private javax.swing.JTable tblSanPham;
    // End of variables declaration//GEN-END:variables
}
