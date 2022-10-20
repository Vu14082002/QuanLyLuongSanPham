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

public class NhanVien_DAO {

    public NhanVien_DAO() {
    }

    public ArrayList<NhanVien> layDanhSachNhanVien() {
        PhongBan_DAO phongBan_DAO = new PhongBan_DAO();
        Statement stm = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM NhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongThoaThuan = rs.getBigDecimal("luongThoaThuan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBan = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBan);
                dsNhanVien.add(new NhanVien(maNhanVien, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongThoaThuan, gioiTinh, anhDaiDien, diaChi, phongBan));

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
        return dsNhanVien;
    }

    public ArrayList<NhanVien> layDanhSachNhanVienTheoMaPhongBan(String maPhongBan) {
        PreparedStatement stm = null;
        ArrayList<NhanVien> dsNhanVien = new ArrayList<NhanVien>();
        PhongBan_DAO phongBan_DAO = new PhongBan_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM NhanVien where maPhongBan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maPhongBan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maNhanVien = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongThoaThuan = rs.getBigDecimal("luongThoaThuan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBanOB = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBanOB);
                dsNhanVien.add(new NhanVien(maNhanVien, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongThoaThuan, gioiTinh, anhDaiDien, diaChi, phongBan));

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
        return dsNhanVien;
    }

    public NhanVien layMotNhanVienTheoMaNhanVien(String maNhanVien) {
        PreparedStatement stm = null;
        NhanVien nhanVien = null;
        PhongBan_DAO phongBan_DAO = new PhongBan_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM NhanVien where maNhanVien = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maNhanVienOB = rs.getString("maNhanVien");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                String chucVu = rs.getString("chucVu");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                double luongThoaThuan = rs.getBigDecimal("luongThoaThuan").doubleValue();
                boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                String maPhongBanOB = rs.getString("maPhongBan");
                PhongBan phongBan = phongBan_DAO.layMotPhongBanTheoMa(maPhongBanOB);
                nhanVien = new NhanVien(maNhanVienOB, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, chucVu, ngayVaoLam, luongThoaThuan, gioiTinh, anhDaiDien, diaChi, phongBan);
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
        return nhanVien;
    }

    public boolean themMotNhanVien(NhanVien nhanVien) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO NhanVien(maNhanVien, hoTen, ngaySinh"
                    + " ,maCCCD, soDienThoai, email, matKhau, chucVu"
                    + " , ngayVaoLam, luongThoaThuan, gioiTinh, anhDaiDien, diaChi, maPhongBan)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, nhanVien.getMaNhanVien());
            stm.setString(2, nhanVien.getHoTen());
            stm.setDate(3, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            stm.setString(4, nhanVien.getMaCCCD());
            stm.setString(5, nhanVien.getSoDienThoai());
            stm.setString(6, nhanVien.getEmail());
            stm.setString(7, nhanVien.getMatKhau());
            stm.setString(8, nhanVien.getChucVu());
            stm.setDate(9, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
            stm.setBigDecimal(10, new BigDecimal(nhanVien.getLuongThoaThuan()));
            stm.setBoolean(11, nhanVien.isGioiTinh());
            stm.setString(12, nhanVien.getAnhDaiDien());
            stm.setString(13, nhanVien.getDiaChi());
            stm.setString(14, nhanVien.getPhongBan().getMaPhongBan());
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

    public boolean suaThongTinMotNhanVien(NhanVien nhanVien) {
        PreparedStatement stm = null;
        int soDongDuocSua = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE NhanVien"
                    + " SET hoten = ?, ngaySinh = ?, maCCCD = ?"
                    + " , soDienThoai = ?, email = ?, matKhau = ?"
                    + " , chucVu = ?, ngayVaoLam = ?, luongThoaThuan = ?"
                    + " , gioiTinh = ?, anhDaiDien = ?"
                    + " , diaChi = ?, maPhongBan = ?"
                    + " where maNhanVien = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, nhanVien.getHoTen());
            stm.setDate(2, new java.sql.Date(nhanVien.getNgaySinh().getTime()));
            stm.setString(3, nhanVien.getMaCCCD());
            stm.setString(4, nhanVien.getSoDienThoai());
            stm.setString(5, nhanVien.getEmail());
            stm.setString(6, nhanVien.getMatKhau());
            stm.setString(7, nhanVien.getChucVu());
            stm.setDate(8, new java.sql.Date(nhanVien.getNgayVaoLam().getTime()));
            stm.setBigDecimal(9, new BigDecimal(nhanVien.getLuongThoaThuan()));
            stm.setBoolean(10, nhanVien.isGioiTinh());
            stm.setString(11, nhanVien.getAnhDaiDien());
            stm.setString(12, nhanVien.getDiaChi());
            stm.setString(13, nhanVien.getPhongBan().getMaPhongBan());
            stm.setString(14, nhanVien.getMaNhanVien());
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

    public boolean xoaMotNhanVienTheoMa(String maNhanVien) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "DELETE FROM NhanVien where maNhanVien = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
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
        NhanVien_DAO dao = new NhanVien_DAO();
        NhanVien nv = new NhanVien("NV111111", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý"));
        System.out.println("Thêm : " + dao.themMotNhanVien(nv));
        System.out.println("\n\n\nDanh sách: " + dao.layDanhSachNhanVien());
        try {
            nv.setAnhDaiDien("anh2.png");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("\n\n\nSửa: " + dao.suaThongTinMotNhanVien(nv));
        System.out.println("\n\n\nXóa: " + dao.xoaMotNhanVienTheoMa("NV111111"));
        System.out.println("\n\n\nLấy theo PB: " + dao.layDanhSachNhanVienTheoMaPhongBan("PB123123"));
        System.out.println("\n\n\nLấy 1: " + dao.layMotNhanVienTheoMaNhanVien("NV123123"));
        
    }
}
