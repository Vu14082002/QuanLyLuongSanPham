/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.CongDoan;
import Entity.PhongBan;
import Entity.SanPham;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class CongDoan_DAO {

    public CongDoan_DAO() {
    }

    public ArrayList<CongDoan> layDanhSachCongDoanTheoMaSP(String maSanPham) {
        PreparedStatement stm = null;
        ArrayList<CongDoan> dsCongDoan = new ArrayList<CongDoan>();
        SanPham_DAO sanPham_DAO = new SanPham_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM CongDoan WHERE maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongDoan = rs.getString("maCongDoan");
                String tenCongDoan = rs.getString("tenCongDoan");
                int soLuongCan = rs.getInt("soLuongCan");
                String tinhTrang = rs.getString("tinhTrang");
                Date thoiHan = rs.getDate("thoiHan");
                String maSanPhamOb = rs.getString("maSanPham");
                double tienLuong = rs.getBigDecimal("tienLuong").doubleValue();
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPhamOb);
                dsCongDoan.add(new CongDoan(maCongDoan, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, tienLuong));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsCongDoan;
    }

    public CongDoan layMotCongDoanTheoMaCongDoan(String maCongDoan) {
        PreparedStatement stm = null;
        CongDoan congDoan = null;
        SanPham_DAO sanPham_DAO = new SanPham_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from CongDoan where maCongDoan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongDoanOb = rs.getString("maCongDoan");
                String tenCongDoan = rs.getString("tenCongDoan");
                int soLuongCan = rs.getInt("soLuongCan");
                String tinhTrang = rs.getString("tinhTrang");
                Date thoiHan = rs.getDate("thoiHan");
                String maSanPhamOb = rs.getString("maSanPham");
                double tienLuong = rs.getBigDecimal("tienLuong").doubleValue();
                SanPham sanPham = sanPham_DAO.layMotSanPhamTheoMa(maSanPhamOb);
                congDoan = new CongDoan(maCongDoanOb, tenCongDoan, soLuongCan, tinhTrang, thoiHan, sanPham, tienLuong);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return congDoan;
    }

    public boolean themMotCongDoan(CongDoan congDoan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO CongDoan(maCongDoan, tenCongDoan, soLuongCan, tinhTrang, "
                    + " thoiHan, maSanPham, tienLuong)"
                    + " VALUES(?, ?, ?, ?, ? ,? ,?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congDoan.getMaCongDoan());
            stm.setString(2, congDoan.getTenCongDoan());
            stm.setInt(3, congDoan.getSoLuongCan());
            stm.setString(4, congDoan.getTinhTrang());
            stm.setDate(5, new java.sql.Date(congDoan.getThoiHan().getTime()));
            stm.setString(6, congDoan.getSanPham().getMaSanPham());
            stm.setBigDecimal(7, new BigDecimal(congDoan.getTienLuong()));
            soDongThemDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongThemDuoc != 0;
    }

    public boolean suaMotCongDoan(CongDoan congDoan) {
        PreparedStatement stm = null;
        int soDongDuocSua = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "update CongDoan "
                    + " set tenCongDoan = ?, soLuongCan = ?, tinhTrang = ?"
                    + " , thoiHan = ?, maSanPham = ?, tienLuong = ?"
                    + " where maCongDoan = ? ";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congDoan.getTenCongDoan());
            stm.setInt(2, congDoan.getSoLuongCan());
            stm.setString(3, congDoan.getTinhTrang());
            stm.setDate(4, new java.sql.Date(congDoan.getThoiHan().getTime()));
            stm.setString(5, congDoan.getSanPham().getMaSanPham());
            stm.setBigDecimal(6, new BigDecimal(congDoan.getTienLuong()));
            stm.setString(7, congDoan.getMaCongDoan());
            soDongDuocSua = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongDuocSua != 0;
    }

    public boolean xoaMotCongDoanTheoMa(String maCongDoan) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = " DELETE FROM CongDoan"
                    + " WHERE maCongDoan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            soDongXoaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongXoaDuoc != 0;
    }

    public boolean updateThoiHan(String maCongDoan, String tienDo) {
        PreparedStatement stm = null;
        int soLuongUpdate = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "update CongDoan set tinhTrang = ? where maCongDoan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, tienDo);
            stm.setString(2, maCongDoan);
            soLuongUpdate = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongUpdate != 0;
    }

    public float layMucDoHoanThanhCuaMotCongDoan(String maCongDoan) {

        PreparedStatement stm = null;
        float mucDoHoanThanh = 0;
        int soLuongCan = 0, soLuongLam = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select soLuongCan, sum(soLuongLam) as tongSoLuongLam from CongDoan CD join PhanCongCongNhan PCCN on CD.maCongDoan = PCCN.maCongDoan"
                    + " join ChamCongCongNhan CCCN on CCCN.maPhanCong = PCCN.maPhanCong where CD.maCongDoan = ? group by CD.maCongDoan, CD.soLuongCan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soLuongCan = rs.getInt("soLuongCan");
                soLuongLam = rs.getInt("tongSoLuongLam");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        mucDoHoanThanh = (soLuongLam / (float) soLuongCan) * 100;
        if (mucDoHoanThanh > 100) {
            mucDoHoanThanh = 100;
        }
        System.out.println("Mức độ hoàn thành: " + mucDoHoanThanh);
        if (Double.isNaN(mucDoHoanThanh)) {
            updateThoiHan(maCongDoan, "0%");
            return 0;
        } 
        updateThoiHan(maCongDoan, String.format("%.2f", mucDoHoanThanh) + "%");
        return mucDoHoanThanh;
    }

    public int laySoLuongLamDuocTheoMaCongDoan(String maCongDoan) {
        PreparedStatement stm = null;
        int soLuongLamDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select soLuongCan, sum(soLuongLam) as tongSoLuongLam from CongDoan CD join PhanCongCongNhan PCCN on CD.maCongDoan = PCCN.maCongDoan"
                    + " join ChamCongCongNhan CCCN on CCCN.maPhanCong = PCCN.maPhanCong where CD.maCongDoan = ? group by CD.maCongDoan, CD.soLuongCan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soLuongLamDuoc = rs.getInt("tongSoLuongLam");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongLamDuoc;
    }

    public String layRaMaCongDoanDeThem() {
        Statement stm = null;
        String maCongDoan = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from CongDoan order by len(maCongDoan), maCongDoan desc ";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                maCongDoan = rs.getString("maCongDoan");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String chuoiCanLay = maCongDoan.split("CD")[1];

        try {
            chuoiCanLay = "CD" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chuoiCanLay;
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF8"));
        } catch (UnsupportedEncodingException ex) {
            System.out.println(ex.getMessage());
        }

        try {
            ConnectDB.getInstance().connect();
            System.out.println("Yes");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        CongDoan_DAO dao = new CongDoan_DAO();
        System.out.println(dao.themMotCongDoan(new CongDoan("CD111111", "Đánh bóng", 222, "Chưa hoàn thành",
                java.sql.Date.valueOf(LocalDate.of(2023, 12, 12)), new SanPham("SP123123", "Giay ISNA Nam", 1000, "Đỏ", "Cotton", 47, "anhsanpham1.png", 0), 2231)));
        System.out.println("\n\n\nLấy ds: " + dao.layDanhSachCongDoanTheoMaSP("SP123123").toString());
        System.out.println("\n\n\nSửa: " + dao.suaMotCongDoan(new CongDoan("CD111111", "Đánh bóng", 223, "Chưa hoàn thành",
                java.sql.Date.valueOf(LocalDate.of(2023, 12, 12)), new SanPham("SP123123", "Giay ISNA Nam", 1000, "Đỏ", "Cotton", 47, "anhsanpham1.png", 0), 2231)));
        System.out.println("\n\n\nXóa 1: " + dao.xoaMotCongDoanTheoMa("CD111111"));
        System.out.println("\n\n\nLấy 1: " + dao.layMotCongDoanTheoMaCongDoan("CD123123"));
        System.out.println("Mức độ hoàn thành" + dao.layMucDoHoanThanhCuaMotCongDoan("CD100001"));

        NumberFormat nf = new DecimalFormat("#,###.00");
        System.out.println("Tiền string: " + nf.format(22332232.30));
        try {
            System.out.println(String.format("Tiền doble: %f", nf.parse("22.332.232,30").doubleValue()));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
