/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Acer
 */
import ConnectionDB.ConnectDB;
import Entity.BangLuongCongNhan;
import Entity.CongNhan;
import Entity.ToNhom;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BangLuongCongNhan_DAO {

    public BangLuongCongNhan_DAO() {
    }


    public ArrayList<BangLuongCongNhan> layDanhSachBangLuongCongNhan() {
        Statement stm = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        ArrayList<BangLuongCongNhan> dsBangLuongCongNhan = new ArrayList<BangLuongCongNhan>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongCongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maCongNhan = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                dsBangLuongCongNhan.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien));
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
        return dsBangLuongCongNhan;
    }
    public ArrayList<BangLuongCongNhan> layDanhSachBangLuongTheoMaCongNhan(String maCongNhan){
        ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<BangLuongCongNhan>();
        PreparedStatement stm = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongCongNhan where maCongNhan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maBangLuong = rs.getString("maBangLuong");
                String maCongNhanOb = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhanOb);
                dsBangLuong.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsBangLuong;
    }
    public BangLuongCongNhan layMotBangLuongCongNhanTheoMa(String maBangLuong) {
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        PreparedStatement stm = null;
        BangLuongCongNhan bangLuongCongNhan = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongCongNhan where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maBangLuong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuongOB = rs.getString("maBangLuong");
                String maCongNhan = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                bangLuongCongNhan = new BangLuongCongNhan(maBangLuongOB, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien);

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
        return bangLuongCongNhan;
    }

    public boolean themMotBangLuong(BangLuongCongNhan bangLuongCongNhan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "INSERT INTO BangLuongCongNhan(maBangLuong, maCongNhan, ngayTinh"
                    + ", soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, tongLuong, donViTien)"
                    + " VALUES (?, ?, ?, ?, ?, ? ,? ,? ,?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongCongNhan.getMaBangLuong());
            stm.setString(2, bangLuongCongNhan.getCongNhan().getMaCongNhan());
            stm.setDate(3, new java.sql.Date(bangLuongCongNhan.getNgayTinh().getTime()));
            stm.setInt(4, bangLuongCongNhan.getSoLuongSanPhamLam());
            stm.setInt(5, bangLuongCongNhan.getSoNgayDiLam());
            stm.setInt(6, bangLuongCongNhan.getSoNgayNghi());
            stm.setInt(7, bangLuongCongNhan.getSoPhepNghi());
            stm.setBigDecimal(8, new BigDecimal(bangLuongCongNhan.getTongLuong()));
            stm.setString(9, bangLuongCongNhan.getDonViTien());
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

    public boolean suaMotBangLuongCongNhan(BangLuongCongNhan bangLuongCongNhan) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "UPDATE BangLuongCongNhan"
                    + " set maCongNhan = ?, ngayTinh = ?,"
                    + " soLuongSanPhamLam = ?, soNgayDiLam = ?,"
                    + " soNgayNghi = ?, soPhepNghi = ?,"
                    + " tongLuong = ?, donViTien = ?"
                    + " where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongCongNhan.getCongNhan().getMaCongNhan());
            stm.setDate(2, new java.sql.Date(bangLuongCongNhan.getNgayTinh().getTime()));
            stm.setInt(3, bangLuongCongNhan.getSoLuongSanPhamLam());
            stm.setInt(4, bangLuongCongNhan.getSoNgayDiLam());
            stm.setInt(5, bangLuongCongNhan.getSoNgayNghi());
            stm.setInt(6, bangLuongCongNhan.getSoPhepNghi());
            stm.setBigDecimal(7, new BigDecimal(bangLuongCongNhan.getTongLuong()));
            stm.setString(8, bangLuongCongNhan.getDonViTien());
            stm.setString(9, bangLuongCongNhan.getMaBangLuong());
            soDongSuaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongSuaDuoc != 0;
    }

    public boolean xoaBangLuongCongNhanTheoMa(String maBangLuong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "DELETE BangLuongCongNhan"
                    + "  where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maBangLuong);
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
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        try {
            ConnectDB.getInstance().connect();
            System.out.println("Yes");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        BangLuongCongNhan_DAO dao = new BangLuongCongNhan_DAO();
        System.out.println("Thêm một: " 
                + dao.themMotBangLuong(new BangLuongCongNhan("LC111111", new CongNhan("CN123123", "Nguyễn Văn Vũ"
        , java.sql.Date.valueOf(LocalDate.of(2000, 11, 11)), "111222333444", "0975123123", "hieurio12@gmail.com"
        , "123123", java.sql.Date.valueOf(LocalDate.of(1999, 12, 12)), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0)), 123
                , 22, 12, 2, new Date(), 123121, "VND")));
        System.out.println(dao.layDanhSachBangLuongCongNhan());
        System.err.println("Sửa:" + dao.suaMotBangLuongCongNhan(new BangLuongCongNhan("LC111111", new CongNhan("CN123123", "Nguyễn Văn Vũ"
        , java.sql.Date.valueOf(LocalDate.of(2000, 11, 11)), "111222333444", "0975123123", "hieurio12@gmail.com"
        , "123123", java.sql.Date.valueOf(LocalDate.of(1999, 12, 12)), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0)), 123
                , 22, 12, 2, new Date(), 123121, "VND")));
        System.out.println("Xóa: " + dao.xoaBangLuongCongNhanTheoMa("LC111111"));
        System.out.println(dao.layDanhSachBangLuongCongNhan());
        System.out.println("\n\nLay mot: " + dao.layMotBangLuongCongNhanTheoMa("LC123123"));
    }
}
