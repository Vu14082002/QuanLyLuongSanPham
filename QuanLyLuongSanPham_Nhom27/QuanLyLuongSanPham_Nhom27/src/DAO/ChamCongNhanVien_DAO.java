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
                String maNhanVien = rs.getString("maNhanVien");
                String caLam = rs.getString("caLam");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                String maNguoiCham = rs.getString("maNguoiCham");
                NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNhanVien);
                NhanVien nguoiChamCong = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiCham);
                dsChamCong.add(new ChamCongNhanVien(nhanVien, ngayChamCong, caLam, trangThaiDiLam, gioDiLam, nguoiChamCong));
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
            String truyVan = "INSERT INTO ChamCongNhanVien(maNhanVien, caLam, ngayChamCong, trangThaiDiLam"
                    + " , gioDiLam, maNguoiCham)"
                    + " VALUES(?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongNhanVien.getNhanVien().getMaNhanVien());
            stm.setString(2, chamCongNhanVien.getCaLam());
            stm.setDate(3, new java.sql.Date(chamCongNhanVien.getNgayChamCong().getTime()));
            stm.setString(4, chamCongNhanVien.getTrangThaiDiLam());
            stm.setString(5, chamCongNhanVien.getGioDiLam());
            stm.setString(6, chamCongNhanVien.getNguoiChamCong().getMaNhanVien());

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

            String truyVan = "UPDATE ChamCongNhanVien set trangThaiDiLam =?, gioDiLam = ? , maNguoiCham = ?\n"
                    + " where maNhanVien =? and caLam=? and ngayChamCong=?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongNhanVien.getTrangThaiDiLam());
            stm.setString(2, chamCongNhanVien.getGioDiLam());
            stm.setString(3, chamCongNhanVien.getNguoiChamCong().getMaNhanVien());
            stm.setString(4, chamCongNhanVien.getNhanVien().getMaNhanVien());
            stm.setString(5, chamCongNhanVien.getCaLam());
            stm.setDate(6, new java.sql.Date(chamCongNhanVien.getNgayChamCong().getTime()));
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

    public boolean xoaMotChamCongNhanVienTheoMaChamCong(String maNhanVien, String caLam, Date ngayChamCong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from ChamCongNhanVien where maNhanVien = ?,caLam=?,ngayChamCong=?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            stm.setString(2, maNhanVien);
            stm.setDate(3, new java.sql.Date(ngayChamCong.getTime()));
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

    public ArrayList<ChamCongNhanVien> layDanhSachCHamCongTheoNgay(String ngayChamCong) {
        PreparedStatement stm = null;
        NhanVien_DAO nhanVien_Dao = new NhanVien_DAO();
        ArrayList<ChamCongNhanVien> dsChamCong = new ArrayList<ChamCongNhanVien>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from ChamCongNhanVien where ngayChamCong=?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, ngayChamCong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String caLam = rs.getString("caLam");
                Date ngayCham = rs.getDate("ngayChamCong");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                String maNguoiCham = rs.getString("maNguoiCham");
                NhanVien nhanVien = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNhanVien);
                NhanVien nguoiChamCong = nhanVien_Dao.layMotNhanVienTheoMaNhanVien(maNguoiCham);
                dsChamCong.add(new ChamCongNhanVien(nhanVien, ngayCham, caLam, trangThaiDiLam, gioDiLam, nguoiChamCong));
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
        NhanVien nv = new NhanVien("NV123123", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý", 0));
        NhanVien nv2 = new NhanVien("NV123456", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý", 0));

    }
}
