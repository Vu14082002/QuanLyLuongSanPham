/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.ChamCongCongNhan;
import Entity.CongDoan;
import Entity.CongNhan;
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
public class ChamCongCongNhan_DAO {

    public ChamCongCongNhan_DAO() {
    }

    public ArrayList<ChamCongCongNhan> layDanhSachChamCong() {
        Statement stm = null;
        ArrayList<ChamCongCongNhan> dsChamCong = new ArrayList<ChamCongCongNhan>();
        PhanCongCongNhan_DAO phanCongCongNhan_DAO = new PhanCongCongNhan_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from ChamCongCongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maChamCong = rs.getString("maChamCong");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                int soLuongLam = rs.getInt("soLuongLam");
                String maPhanCong = rs.getString("maPhanCong");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                PhanCongCongNhan phanCong = phanCongCongNhan_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
                dsChamCong.add(new ChamCongCongNhan(maChamCong, ngayChamCong, soLuongLam, phanCong, trangThaiDiLam, gioDiLam));
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

    public boolean themMotChamCongCongNhan(ChamCongCongNhan chamCongCongNhan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "insert into ChamCongCongNhan(maChamCong, ngayChamCong"
                    + " , soLuongLam, maPhanCong, trangThaiDiLam, gioDiLam)"
                    + " values(?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongCongNhan.getMaChamCong());
            stm.setDate(2, new java.sql.Date(chamCongCongNhan.getNgayChamCong().getTime()));
            stm.setInt(3, chamCongCongNhan.getSoLuongLam());
            stm.setString(4, chamCongCongNhan.getPhanCong().getMaPhanCong());
            stm.setString(5, chamCongCongNhan.getTrangThaiDiLam());
            stm.setString(6, chamCongCongNhan.getGioDiLam());
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

    public boolean suaMotChamCongCongNhan(ChamCongCongNhan chamCongCongNhan) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE ChamCongCongNhan"
                    + " set ngayChamCong = ?, soLuongLam = ?"
                    + " , maPhanCong = ?, trangThaiDiLam = ?, gioDiLam = ?"
                    + " where maChamCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setDate(1, new java.sql.Date(chamCongCongNhan.getNgayChamCong().getTime()));
            stm.setInt(2, chamCongCongNhan.getSoLuongLam());
            stm.setString(3, chamCongCongNhan.getPhanCong().getMaPhanCong());
            stm.setString(4, chamCongCongNhan.getTrangThaiDiLam());
            stm.setString(5, chamCongCongNhan.getGioDiLam());
            stm.setString(6, chamCongCongNhan.getMaChamCong());
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
    public boolean xoaChamCongCongNhanTheoMa(String maChamCong){
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from ChamCongCongNhan where maChamCong = ?";
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
        PhanCongCongNhan_DAO dao = new PhanCongCongNhan_DAO();
        CongDoan congDoan = new CongDoan("CD123123", "Đánh bóng", 222, "Chưa hoàn thành", 
        java.sql.Date.valueOf(LocalDate.of(2023, 12, 12)), new SanPham("SP123123", "Giay ISNA Nam", 1000, "Đỏ", "Cotton", 47, "anhsanpham1.png", 0), 2231);
        CongNhan congNhan = new CongNhan("CN123123", "Nguyễn Văn Vũ"
        , java.sql.Date.valueOf(LocalDate.of(2000, 12, 12)), "111222333444", "0975123123", "hieurio12@gmail.com"
        , "123123", new Date(), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0));
        NhanVien nguoiPhanCong =  new NhanVien("NV123123", "Ngọc Thụ Lâm Phong", java.sql.Date.valueOf(LocalDate.of(2001, 12, 12)), "222333444555", "0976123321", "mailmail@gmail.com", "123123", "Quản lý", new Date(), 2000000, true, "anh1.png", "Lạng sơn", new PhongBan("PB123123", "Phòng quản lý", 0));
        PhanCongCongNhan pc = new PhanCongCongNhan("PC123123", congNhan, congDoan, nguoiPhanCong, new Date(), "CN");
        ChamCongCongNhan_DAO daoCC = new ChamCongCongNhan_DAO();
        ChamCongCongNhan cc = new ChamCongCongNhan("CCN11111", new Date(), 342, pc, "Đi làm", "8:00");
        
        System.out.println("Thêm: " + daoCC.themMotChamCongCongNhan(cc));
        System.out.println("\n\n\nSửa: " + daoCC.suaMotChamCongCongNhan(cc));
        System.out.println("\n\n\nDanh sách: " + daoCC.layDanhSachChamCong());
        System.out.println("\n\n\nXóa: " + daoCC.xoaChamCongCongNhanTheoMa("CCN11111"));
        
    }
}
