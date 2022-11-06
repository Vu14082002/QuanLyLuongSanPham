/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.SanPham_DAO;
import Entity.HopDong;
import Entity.SanPham;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author December
 */
public class SanPhamView extends javax.swing.JPanel implements ActionListener, MouseListener {

    /**
     * Creates new form NhanVienView
     */
    private SanPham_DAO sanPham_DAO;
    private DefaultTableModel modelSanPham;
    private Object oFlag;

    public SanPhamView() {
        initComponents();
        txtMaSanPham.setText("");
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        sanPham_DAO = new SanPham_DAO();
        modelSanPham = (DefaultTableModel) tblDanhSachSanPham.getModel();
        oFlag = null;

        // Gắn sự kiện
        btnCapNhat.addActionListener(this);
        btnHuy.addActionListener(this);
        btnLuu.addActionListener(this);
        btnThem.addActionListener(this);
        btnXoa.addActionListener(this);
        tblDanhSachSanPham.addMouseListener(this);

        xoaTrangField();
        btnLuu.setEnabled(false);
        btnHuy.setEnabled(false);

        // xoa trang các label báo lỗi
        lblErrSoLuong.setText("");
        lblErrTenSanPham.setText("");

        // 
        excute();
        moKhoaTextField(false);
    }

    public void xoaTrangField() {
        txtTenSanPham.requestFocus();;
        txtSoCongDoan.setText("0");
        txtSoLuong.setText("");
        txtTenSanPham.setText("");
        txtChatLieu.setText("");
        cmbKichThuoc.setSelectedIndex(0);
        lblAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sanPham/icons8-shoes-64(2).png")));
        this.pnlMauSacSanPham.setBackground(new Color(255, 0, 0));

    }

    public void excute() {

        // custom table
        tblDanhSachSanPham.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblDanhSachSanPham.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblDanhSachSanPham.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblDanhSachSanPham.setRowHeight(25);
        this.txtTenSanPham.setBackground(new Color(0, 0, 0, 1));
        this.txtChatLieu.setBackground(new Color(0, 0, 0, 1));
        this.txtMaSanPham.setBackground(new Color(0, 0, 0, 1));
        lblErrChatLieu.setText("");
        taiDuLieuLenBang();
    }

    public void taiDuLieuLenBang() {
        while (tblDanhSachSanPham.getRowCount() != 0) {
            modelSanPham.removeRow(0);
        }
        ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPham();
        for (SanPham sanPham : dsSanPham) {
            String data[] = {(modelSanPham.getRowCount() + 1) + "", sanPham.getMaSanPham(), sanPham.getTenSanPham(), sanPham.getSoLuongSanPham() + "",
                sanPham.getMauSac(), sanPham.getChatLieu(), sanPham.getKichThuoc() + "", sanPham.getAnhSanPham(), sanPham.getSoLuongCongDoan() + ""};
            modelSanPham.addRow(data);
        }
        if (tblDanhSachSanPham.getRowCount() != 0) {
            hienThiLenTxt(0);
            tblDanhSachSanPham.setRowSelectionInterval(0, 0);
        }
    }

    public void hienThiLenTxt(int row) {
        txtMaSanPham.setText(tblDanhSachSanPham.getValueAt(row, 1).toString());
        txtTenSanPham.setText(tblDanhSachSanPham.getValueAt(row, 2).toString());
        txtSoLuong.setText(tblDanhSachSanPham.getValueAt(row, 3).toString());
        // hien thi mau
        String mauStr[] = tblDanhSachSanPham.getValueAt(row, 4).toString().split(",");

        int mau[] = new int[mauStr.length];
        for (int i = 0; i < mauStr.length; i++) {
            mau[i] = Integer.parseInt(mauStr[i].trim());

        }

        this.pnlMauSacSanPham.setBackground(new Color(mau[0], mau[1], mau[2]));
        txtChatLieu.setText(tblDanhSachSanPham.getValueAt(row, 5).toString());
        cmbKichThuoc.setSelectedItem(tblDanhSachSanPham.getValueAt(row, 6).toString());
        System.out.println("/image/sanPham/" + tblDanhSachSanPham.getValueAt(row, 7).toString());
        lblAnhSanPham.
                setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sanPham/" + tblDanhSachSanPham.getValueAt(row, 7).toString())));
        // hien thi anh

        txtSoCongDoan.setText(tblDanhSachSanPham.getValueAt(row, 8).toString());

    }

    public void moKhoaTextField(Boolean b) {
        txtMaSanPham.setEditable(false);
        txtSoCongDoan.setEditable(false);
        txtTenSanPham.setEditable(b);
        txtSoLuong.setEditable(b);
        pnlMauSacSanPham.setEnabled(false);
        txtChatLieu.setEditable(b);
        cmbKichThuoc.setEnabled(b);
        pnlAnhSanPham.setEnabled(b);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrTableSanPham = new javax.swing.JScrollPane();
        tblDanhSachSanPham = new javax.swing.JTable();
        pnHead = new javax.swing.JPanel();
        lblAnhSanPham = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        lblTenSanPham = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JTextField();
        lblSoLuongCongDoan = new javax.swing.JLabel();
        lblErrTenSanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSoCongDoan = new javax.swing.JTextField();
        lblMauSac = new javax.swing.JLabel();
        lblErrSoLuong = new javax.swing.JLabel();
        lblKichThuoc = new javax.swing.JLabel();
        pnlMauSacSanPham = new javax.swing.JPanel();
        lblSoLuong = new javax.swing.JLabel();
        cmbKichThuoc = new javax.swing.JComboBox<>();
        lblChatLieu = new javax.swing.JLabel();
        pnlAnhSanPham = new javax.swing.JPanel();
        lblAnhSanPhamOfPnl = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblMaSanPham1 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtChatLieu = new javax.swing.JTextField();
        lblErrChatLieu = new javax.swing.JLabel();
        scrTableSanPham1 = new javax.swing.JScrollPane();
        tblHopDong = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        scrTableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N

        tblDanhSachSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hợp đồng", "Mã sản phẩm", "Tên sản phẩm", "Só lượng", "Màu sắc", "Chất liệu", "Kích thước", "Ảnh sản phẩm", "Số lượng công đoạn"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDanhSachSanPham.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblDanhSachSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachSanPhamMouseClicked(evt);
            }
        });
        scrTableSanPham.setViewportView(tblDanhSachSanPham);

        add(scrTableSanPham, java.awt.BorderLayout.CENTER);

        pnHead.setBackground(new java.awt.Color(255, 255, 255));
        pnHead.setPreferredSize(new java.awt.Dimension(1250, 400));
        pnHead.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblAnhSanPham.setBackground(new java.awt.Color(153, 0, 0));
        lblAnhSanPham.setForeground(new java.awt.Color(255, 0, 51));
        lblAnhSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/sanPham/icons8-shoes-64(2).png"))); // NOI18N
        pnHead.add(lblAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 180, 120));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("______________________");
        pnHead.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 120, 220, 20));

        txtTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtTenSanPham.setText("jTextField1");
        txtTenSanPham.setBorder(null);
        pnHead.add(txtTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 190, 40));

        lblTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTenSanPham.setText("Tên sản phẩm:");
        pnHead.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 120, 40));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel4.setText("_______________");
        pnHead.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 60, 140, 20));

        txtMaSanPham.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtMaSanPham.setText("jTextField1");
        txtMaSanPham.setBorder(null);
        pnHead.add(txtMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 40, 140, 40));

        lblSoLuongCongDoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoLuongCongDoan.setText("Số công đoạn:");
        pnHead.add(lblSoLuongCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 240, 120, 40));

        lblErrTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrTenSanPham.setForeground(new java.awt.Color(204, 0, 0));
        lblErrTenSanPham.setText("đây là dòng thông báo lỗi");
        pnHead.add(lblErrTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 140, 200, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setText("_______________");
        pnHead.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 60, 150, 20));

        txtSoCongDoan.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoCongDoan.setText("jTextField1");
        txtSoCongDoan.setBorder(null);
        txtSoCongDoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoCongDoanActionPerformed(evt);
            }
        });
        pnHead.add(txtSoCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 240, 130, 40));

        lblMauSac.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMauSac.setText("Màu sắc");
        pnHead.add(lblMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 120, 90, 40));

        lblErrSoLuong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoLuong.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoLuong.setText("đây là dòng thông báo lỗi");
        pnHead.add(lblErrSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 80, 170, -1));

        lblKichThuoc.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblKichThuoc.setText("Kích thước");
        pnHead.add(lblKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 190, 110, 40));

        pnlMauSacSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMauSacSanPhamMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlMauSacSanPhamLayout = new javax.swing.GroupLayout(pnlMauSacSanPham);
        pnlMauSacSanPham.setLayout(pnlMauSacSanPhamLayout);
        pnlMauSacSanPhamLayout.setHorizontalGroup(
            pnlMauSacSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlMauSacSanPhamLayout.setVerticalGroup(
            pnlMauSacSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        pnHead.add(pnlMauSacSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 120, 150, 40));

        lblSoLuong.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblSoLuong.setText("Số lượng:");
        pnHead.add(lblSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 50, 80, 40));

        cmbKichThuoc.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbKichThuoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", " " }));
        cmbKichThuoc.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnHead.add(cmbKichThuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 190, 150, 40));

        lblChatLieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblChatLieu.setText("Chât liệu:");
        pnHead.add(lblChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, 140, 40));

        lblAnhSanPhamOfPnl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAnhSanPhamOfPnl.setText("Ảnh sản phẩm");
        lblAnhSanPhamOfPnl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhSanPhamOfPnlMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlAnhSanPhamLayout = new javax.swing.GroupLayout(pnlAnhSanPham);
        pnlAnhSanPham.setLayout(pnlAnhSanPhamLayout);
        pnlAnhSanPhamLayout.setHorizontalGroup(
            pnlAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhSanPhamOfPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlAnhSanPhamLayout.setVerticalGroup(
            pnlAnhSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAnhSanPhamLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblAnhSanPhamOfPnl, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnHead.add(pnlAnhSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, -1, -1));

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
        pnHead.add(btnThem, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 150, 40));

        btnXoa.setBackground(new java.awt.Color(41, 128, 185));
        btnXoa.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/close.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });
        pnHead.add(btnXoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 330, 140, 40));

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });
        pnHead.add(btnCapNhat, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 330, 140, 40));

        btnLuu.setBackground(new java.awt.Color(156, 136, 255));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });
        pnHead.add(btnLuu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 330, 140, 40));

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
        pnHead.add(btnHuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 330, 140, 40));

        lblMaSanPham1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblMaSanPham1.setText("Mã sản phẩm:");
        pnHead.add(lblMaSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 40, 140, 40));

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtSoLuong.setText("jTextField1");
        txtSoLuong.setBorder(null);
        txtSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSoLuongActionPerformed(evt);
            }
        });
        pnHead.add(txtSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 40, 130, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("_______________");
        pnHead.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, 140, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("_______________");
        pnHead.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 190, 150, 20));

        txtChatLieu.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtChatLieu.setText("jTextField1");
        txtChatLieu.setBorder(null);
        txtChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtChatLieuActionPerformed(evt);
            }
        });
        pnHead.add(txtChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 170, 130, 40));

        lblErrChatLieu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrChatLieu.setForeground(new java.awt.Color(204, 0, 0));
        lblErrChatLieu.setText("đây là dòng thông báo lỗi");
        pnHead.add(lblErrChatLieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 210, 170, -1));

        scrTableSanPham1.setBackground(new java.awt.Color(255, 255, 255));
        scrTableSanPham1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách hợp đồng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblHopDong.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        tblHopDong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã hợp đồng", "Tên hợp đồng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHopDong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblHopDong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHopDongMouseClicked(evt);
            }
        });
        scrTableSanPham1.setViewportView(tblHopDong);

        pnHead.add(scrTableSanPham1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 450, 260));

        jLabel1.setText("Hiển thị theo");
        pnHead.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 90, 40));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Hợp đồng" }));
        pnHead.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 120, 40));

        add(pnHead, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSoCongDoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoCongDoanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoCongDoanActionPerformed

    private void pnlMauSacSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMauSacSanPhamMouseClicked
        Color colordefault = new Color(240, 240, 240);
        JColorChooser jchooserColor = new JColorChooser();
        Color color = jchooserColor.showDialog(this, "Chọn màu sắc cho sản phẩm", this.getBackground());
        //        System.out.println(panelBorder1.getColorModel());
        if (color != null && color.getRGB() != pnlAnhSanPham.getBackground().getRGB())
            this.pnlMauSacSanPham.setBackground(color);
        else
            this.pnlMauSacSanPham.setBackground(colordefault);        // TODO add your handling code here:
    }//GEN-LAST:event_pnlMauSacSanPhamMouseClicked

    private void lblAnhSanPhamOfPnlMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhSanPhamOfPnlMouseClicked
        JFileChooser fileChooser = new JFileChooser("d:");
        //        int respone=fileChooser.showOpenDialog(null);
        fileChooser.setCurrentDirectory(new File(".\\src\\image\\sanPham"));
        int respone = fileChooser.showSaveDialog(null);
        if (respone == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            System.out.println(file);
            String path = file.toString().split("src")[1].replace('\\', '/');
            System.out.println("path file split: " + file.toString().split("src")[1]);
            this.lblAnhSanPham.setIcon(new ImageIcon(this.getClass().getResource(path)));
            System.out.println(this.lblAnhSanPham.getIcon().toString());
        }
    }//GEN-LAST:event_lblAnhSanPhamOfPnlMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHuyActionPerformed

    private void txtSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSoLuongActionPerformed

    private void txtChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtChatLieuActionPerformed

    private void tblHopDongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHopDongMouseClicked
        btnThem.setEnabled(true);
        btnXoa.setEnabled(false);
        btnCapNhat.setEnabled(false);
        btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);
    }//GEN-LAST:event_tblHopDongMouseClicked

    private void tblDanhSachSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachSanPhamMouseClicked
        btnThem.setEnabled(false);
        btnXoa.setEnabled(true);
        btnCapNhat.setEnabled(true);
        btnHuy.setEnabled(false);
        btnLuu.setEnabled(false);        // TODO add your handling code here:
    }//GEN-LAST:event_tblDanhSachSanPhamMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cmbKichThuoc;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblAnhSanPham;
    private javax.swing.JLabel lblAnhSanPhamOfPnl;
    private javax.swing.JLabel lblChatLieu;
    private javax.swing.JLabel lblErrChatLieu;
    private javax.swing.JLabel lblErrSoLuong;
    private javax.swing.JLabel lblErrTenSanPham;
    private javax.swing.JLabel lblKichThuoc;
    private javax.swing.JLabel lblMaSanPham1;
    private javax.swing.JLabel lblMauSac;
    private javax.swing.JLabel lblSoLuong;
    private javax.swing.JLabel lblSoLuongCongDoan;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JPanel pnHead;
    private javax.swing.JPanel pnlAnhSanPham;
    private javax.swing.JPanel pnlMauSacSanPham;
    private javax.swing.JScrollPane scrTableSanPham;
    private javax.swing.JScrollPane scrTableSanPham1;
    private javax.swing.JTable tblDanhSachSanPham;
    private javax.swing.JTable tblHopDong;
    private javax.swing.JTextField txtChatLieu;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextField txtSoCongDoan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnThem)) {
            oFlag = e.getSource();
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
            xoaTrangField();
            txtMaSanPham.setText(sanPham_DAO.layMaSanPhamDeThem());
            moKhoaTextField(true);
        } else if (o.equals(btnCapNhat)) {
            oFlag = e.getSource();
            btnThem.setEnabled(false);
            btnXoa.setEnabled(false);
            btnCapNhat.setEnabled(false);
            btnLuu.setEnabled(true);
            btnHuy.setEnabled(true);
            txtTenSanPham.requestFocus();
            moKhoaTextField(true);

        } else if (o.equals(btnXoa)) {
            int rowSelected = tblDanhSachSanPham.getSelectedRow();
            if (rowSelected != -1) {
                int coXacNhanXoa = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xóa Phòng ban", JOptionPane.ERROR_MESSAGE);
                if (coXacNhanXoa == 0) {
                    boolean coXoaDuoc = sanPham_DAO.xoaMotSanPhamTheoMa(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 1).toString());
                    if (coXoaDuoc) {
                        JOptionPane.showMessageDialog(null, "Xóa thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        taiDuLieuLenBang();
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        } else if (o.equals(btnLuu)) {

            if (oFlag.equals(btnThem)) {

                boolean hopLe = validateForm();
                int soLuongSanPham = 0, soCongDoan = 0;
                if (!hopLe) {
                    return;
                }
                String maSanPham = txtMaSanPham.getText().trim();
                String tenSanPham = txtTenSanPham.getText().trim();
                String kichThuocStr = cmbKichThuoc.getSelectedItem().toString();
                int kichThuoc = 0;
                try {
                    soLuongSanPham = Integer.parseInt(txtSoLuong.getText().trim());
                    soCongDoan = Integer.parseInt(txtSoCongDoan.getText().trim());
                    kichThuoc = Integer.parseInt(kichThuocStr);
                } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
                String chatLieu = txtChatLieu.getText();

                String iconfilename = lblAnhSanPham.getIcon().toString();
                String anhSanPham = iconfilename.substring(iconfilename.lastIndexOf("/") + 1);
                int red = pnlMauSacSanPham.getBackground().getRed();
                int blue = pnlMauSacSanPham.getBackground().getBlue();
                int green = pnlMauSacSanPham.getBackground().getGreen();
                String mauSac = red + ", " + green + ", " + blue;
                HopDong hd = new HopDong(tblHopDong.getValueAt(tblHopDong.getSelectedRow(), 1).toString());
                boolean coThemDuoc = sanPham_DAO.themMotSanPham(new SanPham(maSanPham, tenSanPham, soLuongSanPham, mauSac, chatLieu, kichThuoc, anhSanPham, soCongDoan, hd));
                if (coThemDuoc) {
                    taiDuLieuLenBang();
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    btnLuu.setEnabled(false);
                    btnHuy.setEnabled(false);
                    moKhoaTextField(false);
                    JOptionPane.showMessageDialog(null, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    oFlag = null;
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (oFlag.equals(btnCapNhat)) {
                boolean hopLe = validateForm();
                int soLuongSanPham = 0, soCongDoan = 0;
                if (!hopLe) {
                    return;
                }
                String maSanPham = txtMaSanPham.getText().trim();
                String tenSanPham = txtTenSanPham.getText().trim();
                String kichThuocStr = cmbKichThuoc.getSelectedItem().toString();
                int kichThuoc = 0;
                try {
                    soLuongSanPham = Integer.parseInt(txtSoLuong.getText().trim());
                    soCongDoan = Integer.parseInt(txtSoCongDoan.getText().trim());
                    kichThuoc = Integer.parseInt(kichThuocStr);
                } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
                String chatLieu = txtChatLieu.getText();
                String iconfilename = lblAnhSanPham.getIcon().toString();
                String anhSanPham = iconfilename.substring(iconfilename.lastIndexOf("/") + 1);
                int red = pnlMauSacSanPham.getBackground().getRed();
                int blue = pnlMauSacSanPham.getBackground().getBlue();
                int green = pnlMauSacSanPham.getBackground().getGreen();
                String mauSac = red + ", " + green + ", " + blue;
                boolean coSuaDuoc = sanPham_DAO.suaMotSanPham(new SanPham(maSanPham, tenSanPham, soLuongSanPham, mauSac, chatLieu, kichThuoc, anhSanPham, soCongDoan,
                        new HopDong(tblDanhSachSanPham.getValueAt(tblDanhSachSanPham.getSelectedRow(), 1).toString())));
                if (coSuaDuoc) {
                    taiDuLieuLenBang();
                    btnThem.setEnabled(true);
                    btnXoa.setEnabled(true);
                    btnCapNhat.setEnabled(true);
                    btnLuu.setEnabled(false);
                    btnHuy.setEnabled(false);
                    moKhoaTextField(false);
                    oFlag = null;
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (o.equals(btnHuy)) {
            moKhoaTextField(false);
            lblErrSoLuong.setText("");
            lblErrTenSanPham.setText("");
            if (tblDanhSachSanPham.getRowCount() != 0) {
                hienThiLenTxt(0);
                tblDanhSachSanPham.setRowSelectionInterval(0, 0);
            }
            btnThem.setEnabled(true);
            btnXoa.setEnabled(true);
            btnCapNhat.setEnabled(true);
            btnLuu.setEnabled(false);
            btnHuy.setEnabled(false);
        }
    }

    public boolean validateForm() {
        boolean flag = true;
        int soLuong = 0;
        if (txtTenSanPham.getText().trim().equals("")) {
            lblErrTenSanPham.setText("Tên không được để trống!");
            flag = false;
        } else {
            lblErrTenSanPham.setText("");
        }
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            lblErrSoLuong.setText("Số lượng là số nguyên");
            flag = false;
        }
        if (soLuong < 0) {
            lblErrSoLuong.setText("Số lượng phải >= 0");
            flag = false;
        }
        if (flag) {
            lblErrSoLuong.setText("");
        }
        return flag;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblDanhSachSanPham)) {
            int row = tblDanhSachSanPham.getSelectedRow();
            if (row != -1) {
                hienThiLenTxt(row);
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
}
