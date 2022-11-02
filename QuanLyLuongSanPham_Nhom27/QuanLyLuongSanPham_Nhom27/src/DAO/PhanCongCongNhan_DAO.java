/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.CongNhan;
import Entity.CongDoan;
import Entity.NhanVien;
import Entity.PhanCongCongNhan;
import Entity.PhongBan;
import Entity.SanPham;
import Entity.ToNhom;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class PhanCongCongNhan_DAO {

    public PhanCongCongNhan_DAO() {
    }

    public ArrayList<PhanCongCongNhan> layDanhSachPhanCongCongNhan() {
        ArrayList<PhanCongCongNhan> dsPhanCongCongNhan = new ArrayList<PhanCongCongNhan>();
        Statement stm = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        CongDoan_DAO congDoan_DAO = new CongDoan_DAO();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from PhanCongCongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maPhanCong = rs.getString("maPhanCong");
                String maCongNhan = rs.getString("maCongNhan");
                String maNguoiPhanCong = rs.getString("maNguoiPhanCong");
                String maCongDoan = rs.getString("maCongDoan");
                Date ngayPhanCong = rs.getDate("ngayPhanCong");
                String maToNhom = rs.getString("maToNhom");
                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(maCongDoan);
                NhanVien nguoiPhanCong = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNguoiPhanCong);
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsPhanCongCongNhan.add(new PhanCongCongNhan(maPhanCong, congNhan, congDoan, nguoiPhanCong, ngayPhanCong, toNhom));
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
        return dsPhanCongCongNhan;
    }

    public PhanCongCongNhan layMotPhanCongCongNhanTheoMaPhanCong(String maPhanCong) {
        PreparedStatement stm = null;
        PhanCongCongNhan phanCongCongNhan = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        CongDoan_DAO congDoan_DAO = new CongDoan_DAO();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM PhanCongCongNhan WHERE maPhanCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maPhanCong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maPhanCongOb = rs.getString("maPhanCong");
                String maCongNhan = rs.getString("maCongNhan");
                String maNguoiPhanCong = rs.getString("maNguoiPhanCong");
                String maCongDoan = rs.getString("maCongDoan");
                Date ngayPhanCong = rs.getDate("ngayPhanCong");
                String maToNhom = rs.getString("maToNhom");
                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(maCongDoan);
                NhanVien nguoiPhanCong = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNguoiPhanCong);
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                phanCongCongNhan = new PhanCongCongNhan(maPhanCongOb, congNhan, congDoan, nguoiPhanCong, ngayPhanCong, toNhom);
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
        return phanCongCongNhan;
    }

    public boolean themMotPhanCongNhan(PhanCongCongNhan phanCongCongNhan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "insert into PhanCongCongNhan(maPhanCong, maCongNhan"
                    + " , maNguoiPhanCong, maCongDoan, ngayPhanCong, maToNhom)"
                    + " values(?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, phanCongCongNhan.getMaPhanCong());
            stm.setString(2, phanCongCongNhan.getCongNhan().getMaCongNhan());
            stm.setString(3, phanCongCongNhan.getNguoiPhanCong().getMaNhanVien());
            stm.setString(4, phanCongCongNhan.getCongDoan().getMaCongDoan());
            stm.setDate(5, new java.sql.Date(phanCongCongNhan.getNgayPhanCong().getTime()));
            stm.setString(6, phanCongCongNhan.getToNhom().getMaToNhom());
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

    public boolean suaMotPhanCongNhan(PhanCongCongNhan phanCongCongNhan) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = " UPDATE PhanCongCongNhan"
                    + "  set maCongNhan = ?, maNguoiPhanCong = ?,"
                    + "  maCongDoan = ?, ngayPhanCong = ?, maToNhom = ?"
                    + "  where maPhanCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, phanCongCongNhan.getCongNhan().getMaCongNhan());
            stm.setString(2, phanCongCongNhan.getNguoiPhanCong().getMaNhanVien());
            stm.setString(3, phanCongCongNhan.getCongDoan().getMaCongDoan());
            stm.setDate(4, new java.sql.Date(phanCongCongNhan.getNgayPhanCong().getTime()));
            stm.setString(5, phanCongCongNhan.getToNhom().getMaToNhom());
            stm.setString(6, phanCongCongNhan.getMaPhanCong());
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
    public boolean xoaMotPhanCongTheoMaPhanCong(String maPhanCong){
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "DELETE FROM PhanCongCongNhan WHERE maPhanCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maPhanCong);
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
    
    public ArrayList<CongNhan> layRaDanhSachCongNhanTheoCongDoanVaCaLam(String maCongDoan){
        PreparedStatement stm = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<>();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectDB.getInstance();
            Connection con  = ConnectDB.getConnection();
            String sql = "select * from PhanCongCongNhan PCCN join CongNhan CN "
                    + " on PCCN.maCongNhan = CN.maCongNhan where maCongDoan = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maCongNhan = rs.getString("maCongNhan");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                String matKhau = rs.getString("matKhau");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
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
        return dsCongNhan;
    }
    public String layRaMaPhanCongTheoMaCongDoanMaCongNhan(String maCongDoan, String maCongNhan){
        String maPhanCong = "";
        PreparedStatement stm = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from PhanCongCongNhan where maCongDoan = ? AND maCongNhan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            stm.setString(2, maCongNhan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                maPhanCong = rs.getString("maPhanCong");
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
        return maPhanCong;
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
//        PhanCongCongNhan_DAO dao = new PhanCongCongNhan_DAO();
//        CongDoan congDoan = new CongDoan("CD123123", "Đánh bóng", 222, "Chưa hoàn thành", 
//        java.sql.Date.valueOf(LocalDate.of(2023, 12, 12)), new SanPham("SP123123", "Giay ISNA Nam", 1000, "Đỏ", "Cotton", 47, "anhsanpham1.png", 0), 2231);
//        CongNhan congNhan = new CongNhan("CN123123", "Nguyễn Văn Vũ"
//        , java.sql.Date.valueOf(LocalDate.of(2000, 12, 12)), "111222333444", "0975123123", "hieurio12@gmail.com"
//        , "123123", new Date(), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0));
//        NhanVien nguoiPhanCong =  new NhanVien("NV123123", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý", 0));
//        PhanCongCongNhan pc = new PhanCongCongNhan("PC111111", congNhan, congDoan, nguoiPhanCong, new Date(), "CN");
//        System.out.println("Thêm: " + dao.themMotPhanCongNhan(pc));
//        System.out.println("\n\n\nDanh sách: " + dao.layDanhSachPhanCongCongNhan());
//        try {
//            pc.setCaLam("Ca tối");
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//        System.out.println("\n\n\nSửa: " + dao.suaMotPhanCongNhan(pc));
//        System.out.println("\n\n\nXóa: " + dao.xoaMotPhanCongTheoMaPhanCong("PC111111"));
//        System.out.println("\n\n\nLấy 1: " + dao.layMotPhanCongCongNhanTheoMaPhanCong("PC123123"));
    }
}
