/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.BangLuongCongNhan_DAO;
import DAO.CongDoan_DAO;
import DAO.CongNhan_DAO;
import DAO.ToNhom_DAO;
import Entity.BangLuongCongNhan;
import Entity.CongNhan;
import Entity.ToNhom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.statistics.HistogramDataset;

/**
 *
 * @author December
 */
public class ThongKeCongNhanView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel model;

    public ThongKeCongNhanView() {
        initComponents();
        excute();
        cmbNam.removeAllItems();
        for (int i = 2000; i <= LocalDate.now().getYear(); i++) {
            cmbNam.addItem(i+"");
        }
        cmbNam.setSelectedItem(LocalDate.now().getYear()+"");
        thongKeGioiTinhCongNhan();
        thongKeTongLuongConNhanTheoThang();
        thongKeCongNhanTheoTo();
        model = (DefaultTableModel) tblCongNhan.getModel();
        taiDuLieuLenBangCongNhan();
    }

    public void excute() {
        ButtonGroup btnGroup = new ButtonGroup();
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

    public void thongKeCongNhanTheoTo() {
        ToNhom_DAO toNhomDao = new ToNhom_DAO();
        ArrayList<ToNhom> toNhomList = toNhomDao.layDanhSachToNhom();
        DefaultPieDataset barDataset = new DefaultPieDataset();
        toNhomList.forEach(e -> {
            barDataset.setValue(e.getTenToNhom(), new Double(e.getSoLuongCongNhan()));
        });
        JFreeChart piechart = ChartFactory.createPieChart("Thống kê công nhân theo tô nhóm", barDataset, true, true, true);//explain
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnHitogramChar.removeAll();
        pnHitogramChar.add(barChartPanel, BorderLayout.CENTER);
        pnHitogramChar.validate();
    }

    public void thongKeGioiTinhCongNhan() {
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
        barDataset.setValue("Nam", nam);
        barDataset.setValue("Nữ", nu);
        JFreeChart piechart = ChartFactory.createPieChart("Giới tính", barDataset, true, true, false);//explain
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnPieChar.removeAll();
        pnPieChar.add(barChartPanel, BorderLayout.CENTER);
        pnPieChar.validate();
    }

    public void thongKeTongLuongConNhanTheoThang() {
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
                dataset.setValue(tongLuong, "Amount", i + "");
            }
        }
//        dataset.setValue(200, "Amount", "1");
//        dataset.setValue(150, "Amount", "2");
//        dataset.setValue(18, "Amount", "3");
//        dataset.setValue(100, "Amount", "4");
//        dataset.setValue(80, "Amount", "5");
//        dataset.setValue(250, "Amount", "6");
//        dataset.setValue(250, "Amount", "7");
//        dataset.setValue(250, "Amount", "8");
//        dataset.setValue(250, "Amount", "9");
//        dataset.setValue(250, "Amount", "10");
//        dataset.setValue(250, "Amount", "11");
//        dataset.setValue(250, "Amount", "12");

        JFreeChart chart = ChartFactory.createBarChart("TỔng lương theo tháng", "Tháng", "Số tiền",
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
        thongKeTongLuongConNhanTheoThang();
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
