/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import DAO.NhanVien_DAO;
import Entity.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
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
public class ThongKeNhanVienView extends javax.swing.JPanel {

    /**
     * Creates new form NhanVienView
     */
    private DefaultTableModel model;

    public ThongKeNhanVienView() {
        initComponents();
        excute();
        showPieChart();
        showLineChart();
        showHistogram();
        showBarChart();
        taiDuLieuLenBangNhanVien();
    }

    public void excute() {
        ButtonGroup btnGroup = new ButtonGroup();
        model = (DefaultTableModel) tblNhanVien.getModel();
    }

    public void showPieChart() {
        NhanVien_DAO daoNhanVien = new NhanVien_DAO();
        ArrayList<NhanVien> nhanVienList = daoNhanVien.layDanhSachNhanVien();
        double soLuongNam = 0;
        double soLuongNu = 0;
        for (NhanVien nhanVien : nhanVienList) {
            if (nhanVien.isGioiTinh()) {
                soLuongNam++;
            } else {
                soLuongNu++;
            }
        }
        DefaultPieDataset barDataset = new DefaultPieDataset();
        barDataset.setValue("Nam", new Double(soLuongNam));
        barDataset.setValue("Nữ", new Double(soLuongNu));
        JFreeChart piechart = ChartFactory.createPieChart("Giới tính", barDataset, false, true, false);//explain
        PiePlot piePlot = (PiePlot) piechart.getPlot();
        piePlot.setBackgroundPaint(Color.white);
        ChartPanel barChartPanel = new ChartPanel(piechart);
        pnPieChar.removeAll();
        pnPieChar.add(barChartPanel, BorderLayout.CENTER);
        pnPieChar.validate();
    }

    public void taiDuLieuLenBangNhanVien() {
        NhanVien_DAO daoNhanVien = new NhanVien_DAO();
        ArrayList<NhanVien> nhanVienList = daoNhanVien.layDanhSachNhanVien();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        if (nhanVienList != null) {
            nhanVienList.forEach(e -> {
                model.addRow(new Object[]{model.getRowCount() + 1, e.getMaNhanVien(), e.getHoTen(), e.isGioiTinh() ? "Nam" : "Nữ", e.getPhongBan().getTenPhongBan(), e.getChucVu()});
            });
        }
    }

    public void showLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "1");
        dataset.setValue(150, "Amount", "2");
        dataset.setValue(18, "Amount", "3");
        dataset.setValue(100, "Amount", "4");
        dataset.setValue(80, "Amount", "5");
        dataset.setValue(250, "Amount", "6");
        dataset.setValue(250, "Amount", "7");
        dataset.setValue(250, "Amount", "8");
        dataset.setValue(250, "Amount", "9");
        dataset.setValue(250, "Amount", "10");
        dataset.setValue(250, "Amount", "11");
        dataset.setValue(250, "Amount", "12");
        JFreeChart linechart = ChartFactory.createLineChart("Line char", "Tháng", "Số tiền",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot lineCategoryPlot = linechart.getCategoryPlot();
        // lineCategoryPlot.setRangeGridlinePaint(Color.BLUE);
        lineCategoryPlot.setBackgroundPaint(Color.white);

        //create render object to change the moficy the line properties like color
        LineAndShapeRenderer lineRenderer = (LineAndShapeRenderer) lineCategoryPlot.getRenderer();
        Color lineChartColor = new Color(204, 0, 51);
        lineRenderer.setSeriesPaint(0, lineChartColor);

        //create chartPanel to display chart(graph)
        ChartPanel lineChartPanel = new ChartPanel(linechart);
        pnLineChar.removeAll();
        pnLineChar.add(lineChartPanel, BorderLayout.CENTER);
        pnLineChar.validate();
    }

    /*========================================================================================*/
    public void showHistogram() {

        double[] values = {95, 49, 14, 59, 50, 66, 47, 40, 1, 67,
            12, 58, 28, 63, 14, 9, 31, 17, 94, 71,
            49, 64, 73, 97, 15, 63, 10, 12, 31, 62,
            93, 49, 74, 90, 59, 14, 15, 88, 26, 57,
            77, 44, 58, 91, 10, 67, 57, 19, 88, 84
        };

        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("key", values, 20);

        JFreeChart chart = ChartFactory.createHistogram("Thống kê nhân viên theo phòng ban", "Phòng Ban", "Số lượng nhân viên", dataset, PlotOrientation.VERTICAL, false, true, false);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.WHITE);

        ChartPanel barpChartPanel2 = new ChartPanel(chart);
        pnHitogramChar.removeAll();
        pnHitogramChar.add(barpChartPanel2, BorderLayout.CENTER);
        pnHitogramChar.validate();
    }

    /*========================================================================================*/
    public void showBarChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(200, "Amount", "1");
        dataset.setValue(150, "Amount", "2");
        dataset.setValue(18, "Amount", "3");
        dataset.setValue(100, "Amount", "4");
        dataset.setValue(80, "Amount", "5");
        dataset.setValue(250, "Amount", "6");
        dataset.setValue(250, "Amount", "7");
        dataset.setValue(250, "Amount", "8");
        dataset.setValue(250, "Amount", "9");
        dataset.setValue(250, "Amount", "10");
        dataset.setValue(250, "Amount", "11");
        dataset.setValue(250, "Amount", "12");

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
        pnLineChar = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 700));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnPieChar.setLayout(new java.awt.BorderLayout());
        add(pnPieChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 40, 330, 250));

        pnHitogramChar.setLayout(new java.awt.BorderLayout());
        add(pnHitogramChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 330, 250));

        pnBarChar.setLayout(new java.awt.BorderLayout());
        add(pnBarChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 620, 250));

        pnLineChar.setLayout(new java.awt.BorderLayout());
        add(pnLineChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 500, 250));

        tblNhanVien.getTableHeader().setBackground(new Color(32,136,203));
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã nhân viên", "Họ trên", "Giới tính", "Phòng ban", "Chức vụ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setSelectionBackground(new java.awt.Color(232, 57, 95));
        tblNhanVien.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblNhanVien);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 530, 230));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnBarChar;
    private javax.swing.JPanel pnHitogramChar;
    private javax.swing.JPanel pnLineChar;
    private javax.swing.JPanel pnPieChar;
    private javax.swing.JTable tblNhanVien;
    // End of variables declaration//GEN-END:variables
}
