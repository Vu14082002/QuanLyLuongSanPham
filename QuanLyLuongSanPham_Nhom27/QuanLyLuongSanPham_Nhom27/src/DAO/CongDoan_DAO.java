/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.CongDoan;
import Entity.SanPham;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
    }
}
