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
import Entity.ChamCongNhanVien;
import Entity.NhanVien;
import Entity.PhongBan;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ChamCongNhanVien_DAO {

    public ChamCongNhanVien_DAO() {
    }

    public ArrayList<ChamCongNhanVien> danhSachChamCongNhanVien() {
        Statement stm = null;
        NhanVien_DAO nhanVien_Dao = new NhanVien_DAO();
        ArrayList<ChamCongNhanVien> dsChamCong = new ArrayList<ChamCongNhanVien>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from ChamCongNhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maChamCong = rs.getString("maChamCong");
                String caLam = rs.getString("caLam");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                String maNhanVien = rs.getString("maNhanVien");
                String maNguoiCham = rs.getString("maNguoiCham");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNhanVien);
                NhanVien nguoiChamCong = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiCham);
                dsChamCong.add(new ChamCongNhanVien(maChamCong, ngayChamCong, caLam, trangThaiDiLam, gioDiLam, nhanVien, nguoiChamCong));
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
        return dsChamCong;
    }

    public boolean themMotChamCongNhanVien(ChamCongNhanVien chamCongNhanVien) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO ChamCongNhanVien(maChamCong, caLam, trangThaiDiLam"
                    + " , gioDiLam, maNhanVien, maNguoiCham, ngayChamCong)"
                    + " VALUES(?, ?, ?, ?, ?, ? ,?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongNhanVien.getMaChamCong());
            stm.setString(2, chamCongNhanVien.getCaLam());
            stm.setString(3, chamCongNhanVien.getTrangThaiDiLam());
            stm.setString(4, chamCongNhanVien.getGioDiLam());
            stm.setString(5, chamCongNhanVien.getNhanVien().getMaNhanVien());
            stm.setString(6, chamCongNhanVien.getNguoiChamCong().getMaNhanVien());
            stm.setDate(7, new java.sql.Date(chamCongNhanVien.getNgayChamCong().getTime()));
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

    public boolean suaMotChamCongNhanVien(ChamCongNhanVien chamCongNhanVien) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE ChamCongNhanVien"
                    + "  set caLam = ?, trangThaiDiLam = ?, gioDiLam = ?"
                    + "  , maNhanVien = ?, maNguoiCham = ?, ngayChamCong = ?"
                    + "  where maChamCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongNhanVien.getCaLam());
            stm.setString(2, chamCongNhanVien.getTrangThaiDiLam());
            stm.setString(3, chamCongNhanVien.getGioDiLam());
            stm.setString(4, chamCongNhanVien.getNhanVien().getMaNhanVien());
            stm.setString(5, chamCongNhanVien.getNguoiChamCong().getMaNhanVien());
            stm.setDate(6, new java.sql.Date(chamCongNhanVien.getNgayChamCong().getTime()));
            stm.setString(7, chamCongNhanVien.getMaChamCong());
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
    public boolean xoaMotChamCongNhanVienTheoMaChamCong(String maChamCong){
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from ChamCongNhanVien where maChamCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maChamCong);
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
        ChamCongNhanVien_DAO dao = new ChamCongNhanVien_DAO();
        NhanVien nv = new NhanVien("NV123123", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý"));
        NhanVien nv2 = new NhanVien("NV123456", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý"));
        ChamCongNhanVien cc = new ChamCongNhanVien("CNV11111", new Date(), "CN", "Đi làm", "7:59", nv, nv2);
        System.out.println("Thêm: " + dao.themMotChamCongNhanVien(cc) );
        System.out.println("\n\n\nDanh sách: " + dao.danhSachChamCongNhanVien().toString());
        try {
            cc.setGioDiLam("10:00");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\n\nSửa: " + dao.suaMotChamCongNhanVien(cc));
        System.out.println("\n\n\nDanh sách: " + dao.danhSachChamCongNhanVien().toString());
        System.out.println("\n\n\nXóa: " + dao.xoaMotChamCongNhanVienTheoMaChamCong("CNV11111"));
    }
}
