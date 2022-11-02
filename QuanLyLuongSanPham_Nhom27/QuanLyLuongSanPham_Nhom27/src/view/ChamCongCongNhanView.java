/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.ChamCongCongNhan_DAO;
import DAO.CongDoan_DAO;
import DAO.PhanCongCongNhan_DAO;
import DAO.SanPham_DAO;
import Entity.ChamCongCongNhan;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.PhanCongCongNhan;
import Entity.SanPham;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author December
 */
public class ChamCongCongNhanView extends javax.swing.JPanel implements ActionListener, MouseListener{

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel modelTableCongNhan;
    private DefaultTableModel modelTableChamCong;
    private SanPham_DAO sanPham_DAO;
    private CongDoan_DAO congDoan_DAO;
    private PhanCongCongNhan_DAO phanCong_DAO;
    private String maCongDoanFlag;
    private String caLamFlag;
    private Date ngayChamCongFlag;
    private ChamCongCongNhan_DAO chamCongCN_DAO;
    public ChamCongCongNhanView() {
        initComponents();
        excute();
        lblErrSoLuongSP.setText("");
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        maCongDoanFlag = "";
        caLamFlag = "";
        ngayChamCongFlag = new Date();
        // add su kien cho cac button, table
        btnLayDanhSach.addActionListener(this);
        btnChamCong.addActionListener(this);
        tblCongNhan.addMouseListener(this);
        tblChamCong.addMouseListener(this);
        cmbHienThi.addItemListener(this::hienThiChamCongTheoYeuCau);
        
        // chinh lai txt hien thi
        lblMaCongNhanHienThi.setText("");
        lblHoVaTenHienThi.setText("");
        lblTenSanPhamOutput.setText("");
        lblTenCongDoanOutput.setText("");
        
        cmbTenSanPham.setEnabled(false);
        cmbTenCongDoan.setEnabled(false);
        sanPham_DAO = new SanPham_DAO();
        congDoan_DAO = new CongDoan_DAO();
        phanCong_DAO = new PhanCongCongNhan_DAO();
        chamCongCN_DAO = new ChamCongCongNhan_DAO();
        modelTableChamCong = (DefaultTableModel) tblChamCong.getModel();
        modelTableCongNhan = (DefaultTableModel) tblCongNhan.getModel();
        dtcNgayChamCong.setDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dtcNgayChamCong.getDate());
        if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
            setComBoBoxToSunDay();
        } else {
            setComBoBoxToReset();
        }
        taiDuLieuLenCmbSanPham();
        taiDuLieuChamCongLenBang();
        cmbMaSanPham.addItemListener(this::comboBoxitemStateChanged);
        cmbMaCongDoan.addItemListener(this::comboBoxitemStateChangedCongDoan);
        cmbCaLam.addItemListener(this::toggleThoiGianLam);
        cmbTrangThai.addItemListener(this::toggleCmbTrangThai);
        setCmbGio("Sáng");
        

    }
    public void hienThiChamCongTheoYeuCau(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED) {     
           String item = e.getItem().toString();
           if (item.equalsIgnoreCase("Tất cả")){
               taiDuLieuChamCongLenBang();
           } else {
               hienThiChamCongTheoNgay();
           }
            
        }
    }
    public void hienThiChamCongTheoNgay(){
        Date ngayChon = dtcNgayChamCong.getDate();
        while(tblChamCong.getRowCount() != 0){
            modelTableChamCong.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCongTheoNgay(ngayChon);
        for (ChamCongCongNhan cccn : dsChamCong){
            String data[] = {(modelTableChamCong.getRowCount()+1) + "", cccn.getPhanCong().getCongNhan().getMaCongNhan(),
            cccn.getPhanCong().getCongNhan().getHoTen(), cccn.getPhanCong().getCongNhan().getToNhom().getTenToNhom(),
            cccn.getNgayChamCong().toString(), cccn.getCaLam(), cccn.getTrangThaiDiLam(), cccn.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
            cccn.getPhanCong().getCongDoan().getSanPham().getTenSanPham(), 
             cccn.getPhanCong().getCongDoan().getMaCongDoan(),  cccn.getPhanCong().getCongDoan().getTenCongDoan(), cccn.getSoLuongLam()+""};
            modelTableChamCong.addRow(data);
        }
        
    }
    public void comboBoxitemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {     
            taiDuLieuLenCmbCongDoan();
            if (cmbMaSanPham.getSelectedIndex() != -1){
                cmbTenSanPham.setSelectedItem(sanPham_DAO.layMotSanPhamTheoMa(cmbMaSanPham.getSelectedItem().toString()).getTenSanPham());
            }
            
        }
    }

    public void comboBoxitemStateChangedCongDoan(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (cmbMaCongDoan.getSelectedIndex() != -1){
                cmbTenCongDoan.setSelectedItem(congDoan_DAO.layMotCongDoanTheoMaCongDoan(cmbMaCongDoan.getSelectedItem().toString()).getTenCongDoan());
            }
        }
    }
    
     public void toggleThoiGianLam(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {     
            if (e.getItem().toString().equalsIgnoreCase("Sáng") || e.getItem().toString().equalsIgnoreCase("Sáng chủ nhật")){
                setCmbGio("Sáng");
            } else if (e.getItem().toString().equalsIgnoreCase("Chiều") || e.getItem().toString().equalsIgnoreCase("Chiều chủ nhật")){
                setCmbGio("Chiều");
            } else {
                setCmbGio("Đêm");
            }
            
        }
    }
    public void setCmbGio(String ca){
        if (ca.equalsIgnoreCase("Sáng")){
            cmbGioDiLam.removeAllItems();
            for (int i = 6; i  <= 14; i++){
                cmbGioDiLam.addItem(i+"");
            }
        } else if (ca.equalsIgnoreCase("Chiều")){
            cmbGioDiLam.removeAllItems();
            for (int i = 14; i <= 22; i++){
                cmbGioDiLam.addItem(i+"");
            }
        } else {
            cmbGioDiLam.removeAllItems();
            cmbGioDiLam.addItem("22");
            cmbGioDiLam.addItem("23");
            cmbGioDiLam.addItem("24");
            cmbGioDiLam.addItem("1");
            cmbGioDiLam.addItem("2");
            cmbGioDiLam.addItem("3");
            cmbGioDiLam.addItem("4");
            cmbGioDiLam.addItem("5");
            cmbGioDiLam.addItem("6");
        }
    }
    public void toggleCmbTrangThai(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED) {     
            String trangThai = e.getItem().toString();
            if (trangThai.equalsIgnoreCase("Nghỉ có phép") || trangThai.equalsIgnoreCase("Nghỉ không phép")){
                cmbGioDiLam.setEnabled(false);
                cmbPhutDiLam.setEnabled(false);
                cmbGioDiLam.addItem("0");
                cmbGioDiLam.setSelectedItem("0");
                cmbPhutDiLam.setSelectedIndex(0);
            } else {
                cmbGioDiLam.removeItem("0");
                cmbGioDiLam.setEnabled(true);
                cmbPhutDiLam.setEnabled(true);
            }
            
        }
    }
    public void excute() {
//        this.txtMaNhanVien.setText("");
//        this.txtMaNhanVien.setBackground(new Color(0, 0, 0, 1));

        // custom table
        tblCongNhan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblCongNhan.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblCongNhan.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblCongNhan.setRowHeight(25);

        tblChamCong.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblChamCong.getTableHeader().setOpaque(false);
        ((DefaultTableCellRenderer) tblChamCong.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        tblChamCong.setRowHeight(25);
        this.txtSoLuongLam.setBackground(new Color(0, 0, 0, 1));
    }

    public void taiDuLieuLenCmbSanPham() {
        cmbMaSanPham.removeAllItems();;
        cmbTenSanPham.removeAllItems();;
        ArrayList<SanPham> dsSanPham = sanPham_DAO.layDanhSachSanPham();
        for (SanPham sanPham : dsSanPham) {
            cmbMaSanPham.addItem(sanPham.getMaSanPham());
            cmbTenSanPham.addItem(sanPham.getTenSanPham());
        }
        if (cmbMaSanPham.getItemCount() != 0) {
            //cmbMaSanPham.setSelectedIndex(0);
            //cmbTenSanPham.setSelectedIndex(0);
            taiDuLieuLenCmbCongDoan();
        }
    }

    public void taiDuLieuLenCmbCongDoan() {
        cmbMaCongDoan.removeAllItems();
        cmbTenCongDoan.removeAllItems();
        String maSanPham = cmbMaSanPham.getSelectedItem().toString();
        ArrayList<CongDoan> dsCongDoan = congDoan_DAO.layDanhSachCongDoanTheoMaSP(maSanPham);
        for (CongDoan congDoan : dsCongDoan) {
            cmbMaCongDoan.addItem(congDoan.getMaCongDoan());
            cmbTenCongDoan.addItem(congDoan.getTenCongDoan());
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

        jPanel5 = new javax.swing.JPanel();
        dtcNgayChamCong = new com.toedter.calendar.JDateChooser();
        scrTableCongNhan = new javax.swing.JScrollPane();
        tblCongNhan = new javax.swing.JTable();
        lblNgayChamCong = new javax.swing.JLabel();
        lblSoLuongSanPham = new javax.swing.JLabel();
        lblMaCongNhanHienThi = new javax.swing.JLabel();
        lblHoVaTenHienThi = new javax.swing.JLabel();
        lblMaCongNhan = new javax.swing.JLabel();
        lblHoTen = new javax.swing.JLabel();
        lblTenCongDoanRight = new javax.swing.JLabel();
        lblTenSanPhamRight = new javax.swing.JLabel();
        lblTenSanPhamOutput = new javax.swing.JLabel();
        lblTenCongDoanOutput = new javax.swing.JLabel();
        txtSoLuongLam = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        lblErrSoLuongSP = new javax.swing.JLabel();
        cmbMaCongDoan = new javax.swing.JComboBox<>();
        lblTrangThai = new javax.swing.JLabel();
        lblMaCongDoan = new javax.swing.JLabel();
        cmbCaLam = new javax.swing.JComboBox<>();
        cmbGioDiLam = new javax.swing.JComboBox<>();
        lblGioDiLam = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        cmbPhutDiLam = new javax.swing.JComboBox<>();
        cmbTrangThai = new javax.swing.JComboBox<>();
        lblCaLam = new javax.swing.JLabel();
        lblMaSanPham = new javax.swing.JLabel();
        cmbMaSanPham = new javax.swing.JComboBox<>();
        btnLayDanhSach = new javax.swing.JButton();
        btnChamCong = new javax.swing.JButton();
        cmbTenSanPham = new javax.swing.JComboBox<>();
        lblTenSanPham = new javax.swing.JLabel();
        cmbTenCongDoan = new javax.swing.JComboBox<>();
        lblTenCongDoan = new javax.swing.JLabel();
        lblTrangThai1 = new javax.swing.JLabel();
        cmbHienThi = new javax.swing.JComboBox<>();
        scrChamCong = new javax.swing.JScrollPane();
        tblChamCong = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setPreferredSize(new java.awt.Dimension(1250, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dtcNgayChamCong.setDateFormatString("yyyy-MM-dd");
        dtcNgayChamCong.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dtcNgayChamCongPropertyChange(evt);
            }
        });
        jPanel5.add(dtcNgayChamCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 150, 40));

        scrTableCongNhan.setBackground(new java.awt.Color(255, 255, 255));
        scrTableCongNhan.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã công nhân", "Họ và tên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCongNhan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrTableCongNhan.setViewportView(tblCongNhan);

        jPanel5.add(scrTableCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 790, 210));

        lblNgayChamCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNgayChamCong.setText("Ngày chấm công:");
        jPanel5.add(lblNgayChamCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 140, 40));

        lblSoLuongSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSoLuongSanPham.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoLuongSanPham.setText("Số lượng sản phẩm:");
        jPanel5.add(lblSoLuongSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 300, 170, 40));

        lblMaCongNhanHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongNhanHienThi.setText("NV001");
        jPanel5.add(lblMaCongNhanHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 10, 180, 40));

        lblHoVaTenHienThi.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoVaTenHienThi.setText("Nguyễn Văn A");
        jPanel5.add(lblHoVaTenHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 50, 180, 40));

        lblMaCongNhan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongNhan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaCongNhan.setText("Mã công nhân");
        jPanel5.add(lblMaCongNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 190, 40));

        lblHoTen.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblHoTen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblHoTen.setText("Họ và tên:");
        jPanel5.add(lblHoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, 190, 40));

        lblTenCongDoanRight.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoanRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenCongDoanRight.setText("Công đoạn làm:");
        jPanel5.add(lblTenCongDoanRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, 170, 40));

        lblTenSanPhamRight.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSanPhamRight.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenSanPhamRight.setText("Sản phẩm:");
        jPanel5.add(lblTenSanPhamRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, 180, 40));

        lblTenSanPhamOutput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSanPhamOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSanPhamOutput.setText("Giày loại 1");
        jPanel5.add(lblTenSanPhamOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 200, 180, 40));

        lblTenCongDoanOutput.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoanOutput.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCongDoanOutput.setText("Sơn màu");
        jPanel5.add(lblTenCongDoanOutput, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 250, 180, 40));

        txtSoLuongLam.setText("0");
        txtSoLuongLam.setBorder(null);
        jPanel5.add(txtSoLuongLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 300, 150, 30));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 330, 180, 10));

        lblErrSoLuongSP.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblErrSoLuongSP.setForeground(new java.awt.Color(204, 0, 0));
        lblErrSoLuongSP.setText("đây là dòng thông báo lỗi");
        jPanel5.add(lblErrSoLuongSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 340, 190, -1));

        cmbMaCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cd001" }));
        jPanel5.add(cmbMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 150, 40));

        lblTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTrangThai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTrangThai.setText("Trạng thái:");
        jPanel5.add(lblTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 130, 40));

        lblMaCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaCongDoan.setText("Mã công đoạn:");
        jPanel5.add(lblMaCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 150, 40));

        cmbCaLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ca 01", "Ca 02", "Ca 03", "Chủ nhật" }));
        jPanel5.add(cmbCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 140, 40));

        cmbGioDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbGioDiLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "24" }));
        jPanel5.add(cmbGioDiLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 150, 60, 40));

        lblGioDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblGioDiLam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblGioDiLam.setText("Giờ đi làm:");
        jPanel5.add(lblGioDiLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 150, 120, 40));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Phút:");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 150, 50, 40));

        cmbPhutDiLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cmbPhutDiLam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        jPanel5.add(cmbPhutDiLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 150, 60, 40));

        cmbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi Làm", "Đi Trễ", "Nghỉ Không Phép", "Nghỉ Có Phép" }));
        jPanel5.add(cmbTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, 180, 40));

        lblCaLam.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCaLam.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblCaLam.setText("Ca làm:");
        jPanel5.add(lblCaLam, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 100, 40));

        lblMaSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblMaSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaSanPham.setText("Sản phẩm");
        jPanel5.add(lblMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 150, 40));

        cmbMaSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SP001", " " }));
        jPanel5.add(cmbMaSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 80, 150, 40));

        btnLayDanhSach.setBackground(new java.awt.Color(46, 204, 113));
        btnLayDanhSach.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnLayDanhSach.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/add.png"))); // NOI18N
        btnLayDanhSach.setText("Lấy danh sách");
        btnLayDanhSach.setBorder(null);
        btnLayDanhSach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLayDanhSachActionPerformed(evt);
            }
        });
        jPanel5.add(btnLayDanhSach, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 140, 180, 40));

        btnChamCong.setBackground(new java.awt.Color(156, 136, 255));
        btnChamCong.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        btnChamCong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/save.png"))); // NOI18N
        btnChamCong.setText("Chấm công");
        btnChamCong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChamCongActionPerformed(evt);
            }
        });
        jPanel5.add(btnChamCong, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 390, 180, 40));

        cmbTenSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giày mới", " " }));
        jPanel5.add(cmbTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 350, 40));

        lblTenSanPham.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenSanPham.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenSanPham.setText("Tên sản phẩm");
        jPanel5.add(lblTenSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 120, 40));

        cmbTenCongDoan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Rap giày" }));
        jPanel5.add(cmbTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, 140, 40));

        lblTenCongDoan.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTenCongDoan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTenCongDoan.setText("Công đoạn:");
        jPanel5.add(lblTenCongDoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 140, 110, 40));

        lblTrangThai1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblTrangThai1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTrangThai1.setText("Hiển thị");
        jPanel5.add(lblTrangThai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 410, 80, 30));

        cmbHienThi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Theo ngày chấm công" }));
        cmbHienThi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbHienThiActionPerformed(evt);
            }
        });
        jPanel5.add(cmbHienThi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 180, 30));

        add(jPanel5, java.awt.BorderLayout.PAGE_START);

        scrChamCong.setBackground(new java.awt.Color(255, 255, 255));
        scrChamCong.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bảng chấm công", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 16))); // NOI18N

        tblChamCong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblChamCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã công nhân", "Họ và tên", "Tổ/Nhóm", "Ngày chấm công", "Ca làm", "Trạng thái", "Mã sản phẩm", "Sản phẩm", "Mã công đoạn", "Công đoạn", "Số lượng làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChamCong.setSelectionBackground(new java.awt.Color(232, 57, 95));
        scrChamCong.setViewportView(tblChamCong);

        add(scrChamCong, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLayDanhSachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLayDanhSachActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLayDanhSachActionPerformed

    private void btnChamCongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChamCongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnChamCongActionPerformed

    private void dtcNgayChamCongPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dtcNgayChamCongPropertyChange
        // TODO add your handling code here:
        if ("date".equals(evt.getPropertyName())) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dtcNgayChamCong.getDate());
            if (calendar.get(Calendar.DAY_OF_WEEK) == 1) {
                setComBoBoxToSunDay();
            } else {
                setComBoBoxToReset();
            }
        }
        Date ngayChon = dtcNgayChamCong.getDate();
        if (ngayChon.after(new Date())){
            JOptionPane.showMessageDialog(null, "Ngày Chấm công phải bằng hoặc trước ngày hiện tại!", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
            dtcNgayChamCong.setDate(new Date());
        }
    }//GEN-LAST:event_dtcNgayChamCongPropertyChange

    private void cmbHienThiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbHienThiActionPerformed
        
    }//GEN-LAST:event_cmbHienThiActionPerformed

    public void setComBoBoxToSunDay() {
        cmbCaLam.removeAllItems();
        cmbCaLam.addItem("Sáng chủ nhật");
        cmbCaLam.addItem("Chiều chủ nhật");
    }

    public void setComBoBoxToReset() {
        cmbCaLam.removeAllItems();
        cmbCaLam.addItem("Sáng");
        cmbCaLam.addItem("Chiều");
        cmbCaLam.addItem("Đêm");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChamCong;
    private javax.swing.JButton btnLayDanhSach;
    private javax.swing.JComboBox<String> cmbCaLam;
    private javax.swing.JComboBox<String> cmbGioDiLam;
    private javax.swing.JComboBox<String> cmbHienThi;
    private javax.swing.JComboBox<String> cmbMaCongDoan;
    private javax.swing.JComboBox<String> cmbMaSanPham;
    private javax.swing.JComboBox<String> cmbPhutDiLam;
    private javax.swing.JComboBox<String> cmbTenCongDoan;
    private javax.swing.JComboBox<String> cmbTenSanPham;
    private javax.swing.JComboBox<String> cmbTrangThai;
    private com.toedter.calendar.JDateChooser dtcNgayChamCong;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblErrSoLuongSP;
    private javax.swing.JLabel lblGioDiLam;
    private javax.swing.JLabel lblHoTen;
    private javax.swing.JLabel lblHoVaTenHienThi;
    private javax.swing.JLabel lblMaCongDoan;
    private javax.swing.JLabel lblMaCongNhan;
    private javax.swing.JLabel lblMaCongNhanHienThi;
    private javax.swing.JLabel lblMaSanPham;
    private javax.swing.JLabel lblNgayChamCong;
    private javax.swing.JLabel lblSoLuongSanPham;
    private javax.swing.JLabel lblTenCongDoan;
    private javax.swing.JLabel lblTenCongDoanOutput;
    private javax.swing.JLabel lblTenCongDoanRight;
    private javax.swing.JLabel lblTenSanPham;
    private javax.swing.JLabel lblTenSanPhamOutput;
    private javax.swing.JLabel lblTenSanPhamRight;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblTrangThai1;
    private javax.swing.JScrollPane scrChamCong;
    private javax.swing.JScrollPane scrTableCongNhan;
    private javax.swing.JTable tblChamCong;
    private javax.swing.JTable tblCongNhan;
    private javax.swing.JTextField txtSoLuongLam;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLayDanhSach)){
            taiDuLieuLenBangCongNhan();
            if (tblCongNhan.getRowCount() == 0){
                lblMaCongNhanHienThi.setText("");
                lblHoVaTenHienThi.setText("");
                lblTenCongDoanOutput.setText("");
                lblTenCongDoanOutput.setText("");
                txtSoLuongLam.setText("");
            }
            maCongDoanFlag = cmbMaCongDoan.getSelectedItem().toString();
            caLamFlag = cmbCaLam.getSelectedItem().toString();
            ngayChamCongFlag = dtcNgayChamCong.getDate();
            
            lblTenCongDoanOutput.setText(cmbTenCongDoan.getSelectedItem().toString());
            lblTenSanPhamOutput.setText(cmbTenSanPham.getSelectedItem().toString());
        } else if (o.equals(btnChamCong)){
            boolean isValid = validateForm();
            if (!isValid){
                return;
            }
            if (lblMaCongNhanHienThi.getText().equals("")){
                return;
            }
            String maPhanCong = phanCong_DAO.layRaMaPhanCongTheoMaCongDoanMaCongNhan(maCongDoanFlag, lblMaCongNhanHienThi.getText().trim());
            PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
            String gioDiLam = cmbGioDiLam.getSelectedItem().toString() + "h" + cmbPhutDiLam.getSelectedItem().toString();
            int soLuongLam = Integer.parseInt(txtSoLuongLam.getText().trim());
            boolean chamDuoc = chamCongCN_DAO
                    .themMotChamCongCongNhan(new ChamCongCongNhan(phanCong, caLamFlag, ngayChamCongFlag, soLuongLam , cmbTrangThai.getSelectedItem().toString(), gioDiLam ));
            if (chamDuoc){
                JOptionPane.showMessageDialog(null, "Chấm công thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                taiDuLieuChamCongLenBang();
            } else {
                JOptionPane.showMessageDialog(null, "Chấm công thất bại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        
        }
    }
    public boolean validateForm(){
        if (txtSoLuongLam.getText().trim().equals("")){
            lblErrSoLuongSP.setText("Không được để trống");
            return false;
        }
        int soLuong = -1;
        try {
            soLuong = Integer.parseInt(txtSoLuongLam.getText().trim());
        } catch (Exception e) {
            lblErrSoLuongSP.setText("Phải là số nguyên!");
            return false;
        }
        if (soLuong < 0){
            lblErrSoLuongSP.setText("Số lượng làm phải >= 0");
            return false;
        }
        lblErrSoLuongSP.setText("");
        return true;
    }
    public void taiDuLieuLenBangCongNhan(){
        while (tblCongNhan.getRowCount() != 0){
            modelTableCongNhan.removeRow(0);
        }
        if (cmbMaCongDoan.getItemCount() == 0 || cmbCaLam.getItemCount() == 0){
            return;
        }
        String maCongDoan = cmbMaCongDoan.getSelectedItem().toString();
 
       ArrayList<CongNhan> dsCongNhan = phanCong_DAO.layRaDanhSachCongNhanTheoCongDoanVaCaLam(maCongDoan);
        for (CongNhan congNhan: dsCongNhan){
            String data[] = {(modelTableCongNhan.getRowCount()+1)+"", congNhan.getMaCongNhan(), congNhan.getHoTen()};
            modelTableCongNhan.addRow(data);
        }
        if (modelTableCongNhan.getRowCount() != 0){
            tblCongNhan.setRowSelectionInterval(0, 0);
            hienThiThongTinCongNhanLenTxt(0);
        }
        
        
    }
    public void hienThiThongTinCongNhanLenTxt(int row){
        lblMaCongNhanHienThi.setText(tblCongNhan.getValueAt(row, 1).toString());
        lblHoVaTenHienThi.setText(tblCongNhan.getValueAt(row, 2).toString());
    }
    public void taiDuLieuChamCongLenBang(){
        while (tblChamCong.getRowCount() != 0){
            modelTableChamCong.removeRow(0);
        }
        ArrayList<ChamCongCongNhan> dsChamCong = chamCongCN_DAO.layDanhSachChamCong();
        for (ChamCongCongNhan cccn : dsChamCong){
            String data[] = {(modelTableChamCong.getRowCount()+1) + "", cccn.getPhanCong().getCongNhan().getMaCongNhan(),
            cccn.getPhanCong().getCongNhan().getHoTen(), cccn.getPhanCong().getCongNhan().getToNhom().getTenToNhom(),
            cccn.getNgayChamCong().toString(), cccn.getCaLam(), cccn.getTrangThaiDiLam(), cccn.getPhanCong().getCongDoan().getSanPham().getMaSanPham(),
            cccn.getPhanCong().getCongDoan().getSanPham().getTenSanPham(), 
             cccn.getPhanCong().getCongDoan().getMaCongDoan(),  cccn.getPhanCong().getCongDoan().getTenCongDoan(), cccn.getSoLuongLam()+""};
            modelTableChamCong.addRow(data);
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tblCongNhan)){
            int row = tblCongNhan.getSelectedRow();
            if (row != -1){
                hienThiThongTinCongNhanLenTxt(row);
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
