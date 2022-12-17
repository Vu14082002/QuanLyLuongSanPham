/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.BangLuongCongNhan_DAO;
import DAO.CongNhan_DAO;
import DAO.ToNhom_DAO;
import Entity.BangLuongCongNhan;
import Entity.CongNhan;
import Entity.ToNhom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author December
 */
public class ThongKeCongNhanView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel model;
    private String fileName;

    public ThongKeCongNhanView(String fileName) throws IOException {
        this.fileName = fileName;
        initComponents();
        excute();
        cmbNam.removeAllItems();
        for (int i = 2000; i <= LocalDate.now().getYear(); i++) {
            cmbNam.addItem(i + "");
        }
        cmbNam.setSelectedItem(LocalDate.now().getYear() + "");
        thongKeGioiTinhCongNhan();
        thongKeTongLuongConNhanTheoThang();
        thongKeCongNhanTheoTo();
        model = (DefaultTableModel) tblCongNhan.getModel();
        taiDuLieuLenBangCongNhan();
        caiDatNgonNguChoView(fileName);
    }

    public void excute() {
        ButtonGroup btnGroup = new ButtonGroup();
    }

    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        ChangeName(tblCongNhan, 0, prop.getProperty("pcd_stt"));
        ChangeName(tblCongNhan, 1, prop.getProperty("maCongNhan"));
        ChangeName(tblCongNhan, 2, prop.getProperty("hoTen"));
        ChangeName(tblCongNhan, 3, prop.getProperty("gioiTinh"));
        ChangeName(tblCongNhan, 4, prop.getProperty("toNhom"));
    }

    public void ChangeName(JTable table, int col_index, String col_name) {
        table.getColumnModel().getColumn(col_index).setHeaderValue(col_name);
    }

    public void taiDuLieuLenBangCongNhan() {
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        CongNhan_DAO daoCongNhan = new CongNhan_DAO();
        ArrayList<CongNhan> congNhanList = daoCongNhan.layDanhSachCongNhan();
        if (congNhanList != null) {
            congNhanList.forEach(e -> {
                model.addRow(new Object[]{model.getRowCount() + 1, e.getMaCongNhan(), e.getHoTen(), e.isGioiTinh() ? "Nam" : "Nữ", e.getToNhom().getTenToNhom()});

            });
        }
    }

    public void thongKeCongNhanTheoTo() throws FileNotFoundException, IOException {
        ToNhom_DAO toNhomDao = new ToNhom_DAO();
        ArrayList<ToNhom> toNhomList = toNhomDao.layDanhSachToNhom();
        DefaultPieDataset barDataset = new DefaultPieDataset();
        toNhomList.forEach(e -> {
//            barDataset.setValue(e.getTenToNhom(), new Double(e.getSoLuongCongNhan()));
            barDataset.setValue(e.getTenToNhom(), new BigDecimal(e.getSoLuongCongNhan()));
        });
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        JFreeChart piechart = ChartFactory.createPieChart(prop.getProperty("tk_congNhanTheoToNhom"), barDataset, true, true, true);//explain
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnHitogramChar.removeAll();
        pnHitogramChar.add(barChartPanel, BorderLayout.CENTER);
        pnHitogramChar.validate();
    }

    public void thongKeGioiTinhCongNhan() throws IOException {
        DefaultPieDataset barDataset = new DefaultPieDataset();
        CongNhan_DAO congNhanDao = new CongNhan_DAO();
        ArrayList<CongNhan> congNhanList = congNhanDao.layDanhSachCongNhan();
        int nam = 0;
        int nu = 0;
        if (congNhanList != null) {
            for (CongNhan e : congNhanList) {
                if (e.isGioiTinh()) {
                    nam++;
                } else {
                    nu++;
                }
            }
        }
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        barDataset.setValue(prop.getProperty("nam"), nam);
        barDataset.setValue(prop.getProperty("nu"), nu);
        JFreeChart piechart = ChartFactory.createPieChart(prop.getProperty("gioiTinh"), barDataset, true, true, false);//explain
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnPieChar.removeAll();
        pnPieChar.add(barChartPanel, BorderLayout.CENTER);
        pnPieChar.validate();
    }

    public void thongKeTongLuongConNhanTheoThang() throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        BangLuongCongNhan_DAO bangLuongCongNhanDao = new BangLuongCongNhan_DAO();
        ArrayList<BangLuongCongNhan> bangLuongCongNhanList = bangLuongCongNhanDao.layDanhSachBangLuongCongNhan();
        if (bangLuongCongNhanList != null) {
            for (int i = 1; i < 13; i++) {
                String nam = "2022";
                try {
                    if (cmbNam.getSelectedItem().toString() == null) {
                        nam = "2022";
                    } else {
                        nam = cmbNam.getSelectedItem().toString();
                    }
                } catch (Exception e) {
                }
                String value = i + "-" + nam;
                if (i < 10) {
                    value = "0" + i + "-" + nam;
                }
                double tongLuong = 0;
                for (BangLuongCongNhan e : bangLuongCongNhanList) {
                    String ngayTinhLuong = e.getNgayTinh().toString().split("-")[1] + "-" + e.getNgayTinh().toString().split("-")[0];
                    System.out.println(ngayTinhLuong);
                    if (ngayTinhLuong.equalsIgnoreCase(value)) {
                        tongLuong += e.getTongLuong();
                    }
                }
                dataset.setValue(new BigDecimal(tongLuong), "Amount", i + "");
            }
        }

        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        JFreeChart chart = ChartFactory.createBarChart(prop.getProperty("tk_tieudeThongeKeNhanVien"), prop.getProperty("tk_thang"), prop.getProperty("tk_soTien"),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot categoryPlot = chart.getCategoryPlot();
        //categoryPlot.setRangeGridlinePaint(Color.BLUE);
        categoryPlot.setBackgroundPaint(Color.WHITE);
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color clr3 = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, clr3);

        ChartPanel barpChartPanel = new ChartPanel(chart);
        pnBarChar.removeAll();
        pnBarChar.add(barpChartPanel, BorderLayout.CENTER);
        pnBarChar.validate();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPieChar = new javax.swing.JPanel();
        pnHitogramChar = new javax.swing.JPanel();
        pnBarChar = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblCongNhan = new javax.swing.JTable();
        cmbNam = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnPieChar.setLayout(new java.awt.BorderLayout());
        add(pnPieChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 40, 480, 250));

        pnHitogramChar.setLayout(new java.awt.BorderLayout());
        add(pnHitogramChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 580, 250));

        pnBarChar.setLayout(new java.awt.BorderLayout());
        add(pnBarChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 430, 620, 250));

        tblCongNhan.getTableHeader().setBackground(new Color(32,136,203));
        tblCongNhan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã công nhân", "Họ trên", "Giới tính", "Tổ/Nhóm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCongNhan.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblCongNhan.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblCongNhan);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 530, 260));

        cmbNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000" }));
        cmbNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbNamActionPerformed(evt);
            }
        });
        add(cmbNam, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 160, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void cmbNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbNamActionPerformed
        try {
            thongKeTongLuongConNhanTheoThang();
        } catch (IOException ex) {
            Logger.getLogger(ThongKeCongNhanView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmbNamActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbNam;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnBarChar;
    private javax.swing.JPanel pnHitogramChar;
    private javax.swing.JPanel pnPieChar;
    private javax.swing.JTable tblCongNhan;
    // End of variables declaration//GEN-END:variables
}
