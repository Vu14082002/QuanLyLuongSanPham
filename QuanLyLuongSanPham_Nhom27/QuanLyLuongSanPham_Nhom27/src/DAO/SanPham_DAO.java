/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.HopDong;
import java.util.ArrayList;
import Entity.SanPham;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Acer
 */
public class SanPham_DAO {

    public SanPham_DAO() {
    }

    public ArrayList<SanPham> layDanhSachSanPham() {
        ArrayList<SanPham> dsSanPham = new ArrayList<SanPham>();
        Statement stm = null;
        HopDong_DAO hopDong_DAO = new HopDong_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM SanPham";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maSanPham = rs.getString("maSanPham");
                String maHopDong = rs.getString("maHopDong");
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                String mauSac = rs.getString("mauSac");
                String chatLieu = rs.getString("chatLieu");
                int kichThuoc = rs.getInt("kichThuoc");
                String anhSanPham = rs.getString("anhSanPham");
                int soLuongCongDoan = rs.getInt("soLuongCongDoan");
                HopDong hopDong = hopDong_DAO.layRaMotHopDongTheoMaHopDong(maHopDong);
                dsSanPham.add(new SanPham(maSanPham, tenSanPham, soLuongSanPham, mauSac, chatLieu, kichThuoc, anhSanPham, soLuongCongDoan, hopDong));

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
        return dsSanPham;
    }

    public SanPham layMotSanPhamTheoMa(String maSanPham) {
        PreparedStatement stm = null;
        SanPham sanPham = null;
        HopDong_DAO hopDong_DAO = new HopDong_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from SanPham where maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maSanPhamOB = rs.getString("maSanPham");
                String maHopDong = rs.getString("maHopDong");
                String tenSanPham = rs.getString("tenSanPham");
                int soLuongSanPham = rs.getInt("soLuongSanPham");
                String mauSac = rs.getString("mauSac");
                String chatLieu = rs.getString("chatLieu");
                int kichThuoc = rs.getInt("kichThuoc");
                String anhSanPham = rs.getString("anhSanPham");
                int soLuongCongDoan = rs.getInt("soLuongCongDoan");
                HopDong hopDong = hopDong_DAO.layRaMotHopDongTheoMaHopDong(maHopDong);
                sanPham = new SanPham(maSanPham, tenSanPham, soLuongSanPham, mauSac, chatLieu, kichThuoc, anhSanPham, soLuongCongDoan, hopDong);
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
        return sanPham;
    }

    public boolean themMotSanPham(SanPham sanPham) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO SanPham(maSanPham, maHopDong, tenSanPham, soLuongSanPham"
                    + "	, mauSac, chatLieu, kichThuoc, anhSanPham)"
                    + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, sanPham.getMaSanPham());
            stm.setString(2, sanPham.getHopDong().getMaHopDong());
            stm.setString(3, sanPham.getTenSanPham());
            stm.setInt(4, sanPham.getSoLuongSanPham());
            stm.setString(5, sanPham.getMauSac());
            stm.setString(6, sanPham.getChatLieu());
            stm.setInt(7, sanPham.getKichThuoc());
            stm.setString(8, sanPham.getAnhSanPham());
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

    public boolean suaMotSanPham(SanPham sanPham) {
        PreparedStatement stm = null;
        int soLuongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = " UPDATE SanPham"
                    + " set tenSanPham = ?, maHopDong = ?, soLuongSanPham = ?,"
                    + " mauSac = ?, chatLieu = ?, kichThuoc = ?,"
                    + " anhSanPham = ?"
                    + " where maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, sanPham.getTenSanPham());
            stm.setString(2, sanPham.getHopDong().getMaHopDong());
            stm.setInt(3, sanPham.getSoLuongSanPham());
            stm.setString(4, sanPham.getMauSac());
            stm.setString(5, sanPham.getChatLieu());
            stm.setInt(6, sanPham.getKichThuoc());
            stm.setString(7, sanPham.getAnhSanPham());
            stm.setString(8, sanPham.getMaSanPham());

            soLuongSuaDuoc = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongSuaDuoc != 0;
    }

    public boolean xoaMotSanPhamTheoMa(String maSanPham) {
        PreparedStatement stm = null;
        int soLuongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "DELETE FROM SanPham WHERE maSanPham = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maSanPham);
            soLuongXoaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongXoaDuoc != 0;
    }

    public String layMaSanPhamDeThem(){
        Statement stm = null;
        String maSanPham = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from SanPham order by len(maSanPham), maSanPham desc ";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                maSanPham = rs.getString("maSanPham");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (maSanPham == null || maSanPham.equals("")){
            return "SP100001";
        }
        String chuoiCanLay = maSanPham.split("SP")[1];

        try {
            chuoiCanLay = "SP" + (Integer.parseInt(chuoiCanLay) + 1);
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
//        SanPham_DAO dao = new SanPham_DAO();
//        System.out.println("Thêm: " + dao.themMotSanPham(new SanPham("SP111111", "HD100001", "Giay Nika", 321, "Đỏ", "Poly", 12, "anh1.png", 0)));
//        System.out.println("\n\n\n Danh sách: " + dao.layDanhSachSanPham().toString());
//        System.out.println("\n\n\n Sửa: " + dao.suaMotSanPham(new SanPham("SP111111", "Giay Nika", 323, "Đỏ", "Poly", 12, "anh1.png", 0)));
//        System.out.println("\n\n\n Xóa: " + dao.xoaMotSanPhamTheoMa("SP111111"));
//        System.out.println("\n\n\n Danh sách: " + dao.layDanhSachSanPham().toString());
//        System.out.println("\n\n\n Lấy 1: " + dao.layMotSanPhamTheoMa("SP123123").toString());
    }
    
}
