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
import Entity.BangLuongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BangLuongNhanVien_DAO {

    public BangLuongNhanVien_DAO() {
    }

    public ArrayList<BangLuongNhanVien> danhSachBangLuong() {
        Statement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<BangLuongNhanVien>();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangLuongNhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maNhanVien = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                double tongTien = rs.getBigDecimal("tongTien").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVien);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongTien, donViTien));
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
        return dsBangLuong;
    }

    public ArrayList<BangLuongNhanVien> layDanhSachBangLuongTheoMaNhanVien(String maNhanVien) {
        PreparedStatement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<BangLuongNhanVien>();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangLuongNhanVien where maNhanVien = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maNhanVienOB = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                double tongTien = rs.getBigDecimal("tongTien").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVienOB);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongTien, donViTien));
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
        return dsBangLuong;
    }

    public boolean themMotBangLuong(BangLuongNhanVien bangLuongNhanVien) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO BangLuongNhanVien(maBangLuong, maNhanVien"
                    + " , soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh"
                    + " , tongTien, donViTien)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongNhanVien.getMaBangLuong());
            stm.setString(2, bangLuongNhanVien.getNhanVien().getMaNhanVien());
            stm.setInt(3, bangLuongNhanVien.getSoNgayDiLam());
            stm.setInt(4, bangLuongNhanVien.getSoNgayNghi());
            stm.setInt(5, bangLuongNhanVien.getSoPhepNghi());
            stm.setDate(6, new java.sql.Date(bangLuongNhanVien.getNgayTinh().getTime()));
            stm.setBigDecimal(7, new BigDecimal(bangLuongNhanVien.getTongTien()));
            stm.setString(8, bangLuongNhanVien.getDonViTien());
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

    public boolean suaMotBangLuong(BangLuongNhanVien bangLuongNhanVien) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE BangLuongNhanVien"
                    + " set maNhanVien = ?, soNgayDiLam = ?, soNgayNghi = ?"
                    + " , soPhepNghi = ?, ngayTinh = ?, tongTien = ?, donViTien = ?"
                    + " where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongNhanVien.getNhanVien().getMaNhanVien());
            stm.setInt(2, bangLuongNhanVien.getSoNgayDiLam());
            stm.setInt(3, bangLuongNhanVien.getSoNgayNghi());
            stm.setInt(4, bangLuongNhanVien.getSoPhepNghi());
            stm.setDate(5, new java.sql.Date(bangLuongNhanVien.getNgayTinh().getTime()));
            stm.setBigDecimal(6, new BigDecimal(bangLuongNhanVien.getTongTien()));
            stm.setString(7, bangLuongNhanVien.getDonViTien());
            stm.setString(8, bangLuongNhanVien.getMaBangLuong());
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

    public boolean xoaMotBangLuongTheoMaBangLuong(String maBangLuong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from BangLuongNhanVien where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maBangLuong);
            soDongXoaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
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
        BangLuongNhanVien_DAO dao = new BangLuongNhanVien_DAO();
        NhanVien nv = new NhanVien("NV123123", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý"));
        BangLuongNhanVien bl = new BangLuongNhanVien("LN111111", nv, 22, 12, 3, new Date(), 1231231, "VND");
        System.out.println("Thêm: " + dao.themMotBangLuong(bl));
        try {
            bl.setSoNgayDiLam(24);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\n\nSửa: " + dao.suaMotBangLuong(bl));
        System.out.println("\n\n\n" + dao.layDanhSachBangLuongTheoMaNhanVien("NV123123"));
        System.out.println("\n\n\nXóa" + dao.xoaMotBangLuongTheoMaBangLuong("LN111111"));
        
    }
}
