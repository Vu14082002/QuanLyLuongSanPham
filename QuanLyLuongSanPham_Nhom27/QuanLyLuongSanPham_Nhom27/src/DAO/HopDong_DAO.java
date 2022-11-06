/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.HopDong;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class HopDong_DAO {

    public HopDong_DAO() {
    }

    public ArrayList<HopDong> layDanhSachHopDong() {
        Statement stm = null;
        ArrayList<HopDong> dsHopDong = new ArrayList<HopDong>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from HopDong";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maHopDong = rs.getString("maHopDong");
                String tenKhachHang = rs.getString("tenKhachHang");
                double soTienCoc = rs.getBigDecimal("soTienCoc").doubleValue();
                double tongTien = rs.getBigDecimal("tongTien").doubleValue();
                Date ngayKyKet = rs.getDate("ngayKyKet");
                Date hanChot = rs.getDate("hanChot");
                String yeuCau = rs.getString("yeuCau");
                dsHopDong.add(new HopDong(maHopDong, tenKhachHang, soTienCoc, tongTien, ngayKyKet, hanChot, yeuCau));
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
        return dsHopDong;
    }

    public HopDong layRaMotHopDongTheoMaHopDong(String maHopDong) {
        PreparedStatement stm = null;
        HopDong hopDong = null;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from HopDong where maHopDong = ?";
            stm = con.prepareCall(truyVan);
            stm.setString(1, maHopDong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maHopDongTemp = rs.getString("maHopDong");
                String tenKhachHang = rs.getString("tenKhachHang");
                double soTienCoc = rs.getBigDecimal("soTienCoc").doubleValue();
                double tongTien = rs.getBigDecimal("tongTien").doubleValue();
                Date ngayKyKet = rs.getDate("ngayKyKet");
                Date hanChot = rs.getDate("hanChot");
                String yeuCau = rs.getString("yeuCau");
                hopDong = new HopDong(maHopDongTemp, tenKhachHang, soTienCoc, tongTien, ngayKyKet, hanChot, yeuCau);
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
        return hopDong;
    }

    public boolean themMotHopDong(HopDong hopDong) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "insert into HopDong(maHopDong, tenKhachHang, soTienCoc, tongTien ,ngayKyKet ,hanChot, yeuCau)"
                    + " values(?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, hopDong.getMaHopDong());
            stm.setString(2, hopDong.getTenKhachHang());
            stm.setBigDecimal(3, new BigDecimal(hopDong.getSoTienCoc()));
            stm.setBigDecimal(4, new BigDecimal(hopDong.getTongTien()));
            stm.setDate(5, new java.sql.Date(hopDong.getNgayKyKet().getTime()));
            stm.setDate(6, new java.sql.Date(hopDong.getHanChot().getTime()));
            stm.setString(7, hopDong.getYeuCau());
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

    public boolean suaMotHopDong(HopDong hopDong) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "update HopDong"
                    + " set tenKhachHang = ?, soTienCoc = ?, tongTien = ? ,ngayKyKet = ?"
                    + " , hanChot = ?, yeuCau = ?"
                    + " where maHopDong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, hopDong.getTenKhachHang());
            stm.setBigDecimal(2, new BigDecimal(hopDong.getSoTienCoc()));
            stm.setBigDecimal(3, new BigDecimal(hopDong.getTongTien()));
            stm.setDate(4, new java.sql.Date(hopDong.getNgayKyKet().getTime()));
            stm.setDate(5, new java.sql.Date(hopDong.getHanChot().getTime()));
            stm.setString(6, hopDong.getYeuCau());;
            stm.setString(7, hopDong.getMaHopDong());
            soDongSuaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongSuaDuoc != 0;
    }

    public boolean xoaMotHopDong(String maHopDong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete HopDong"
                    + " where maHopDong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maHopDong);
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
}
