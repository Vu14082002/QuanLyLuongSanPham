/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.ToNhom;
import Entity.CongNhan;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Acer
 */
public class CongNhan_DAO {

    public CongNhan_DAO() {
    }

    public ArrayList<CongNhan> layDanhSachCongNhan() {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        Statement stm = null;
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM CongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
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
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsCongNhan;
    }

    public ArrayList<CongNhan> layDanhSachCongNhanTheoMaTo(String maTo) {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        PreparedStatement stm = null;
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from CongNhan where toNhom = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maTo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dsCongNhan;
    }

    public CongNhan layMotCongNhanTheoMa(String maCongNhan) {
        CongNhan congNhan = null;
        PreparedStatement stm = null;
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "SELECT * FROM CongNhan where maCongNhan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhanOb = rs.getString("maCongNhan");
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
                congNhan = new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom);

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
        return congNhan;
    }

    public boolean themMotCongNhan(CongNhan congNhan) {
        PreparedStatement stm = null;
        int soLuongThemDuoc = 0;

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "INSERT INTO CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, matKhau, gioiTinh, anhDaiDien, diaChi, ngayVaoLam, toNhom) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congNhan.getMaCongNhan());
            stm.setString(2, congNhan.getHoTen());
            stm.setDate(3, new java.sql.Date(congNhan.getNgaySinh().getTime()));
            stm.setString(4, congNhan.getMaCCCD());
            stm.setString(5, congNhan.getSoDienThoai());
            stm.setString(6, congNhan.getEmail());
            stm.setString(7, congNhan.getMatKhau());
            stm.setBoolean(8, congNhan.isGioiTinh());
            stm.setString(9, congNhan.getAnhDaiDien());
            stm.setString(10, congNhan.getDiaChi());
            stm.setDate(11, new java.sql.Date(congNhan.getNgayVaoLam().getTime()));
            stm.setString(12, congNhan.getToNhom().getMaToNhom());

            soLuongThemDuoc = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongThemDuoc != 0;
    }

    public boolean capNhatMotCongNhan(CongNhan congNhan) {
        PreparedStatement stm = null;
        int soLuongDuocCapNhat = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE CongNhan"
                    + " SET hoTen = ?, ngaySinh = ?, maCCCD = ?,"
                    + " soDienThoai = ?, email = ?, matKhau = ?,"
                    + " gioiTinh = ?, anhDaiDien = ?, diaChi = ?,"
                    + " ngayVaoLam = ?, toNhom = ?"
                    + " WHERE maCongNhan = ? ";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congNhan.getHoTen());
            stm.setDate(2, new java.sql.Date(congNhan.getNgaySinh().getTime()));
            stm.setString(3, congNhan.getMaCCCD());
            stm.setString(4, congNhan.getSoDienThoai());
            stm.setString(5, congNhan.getEmail());
            stm.setString(6, congNhan.getMatKhau());
            stm.setBoolean(7, congNhan.isGioiTinh());
            stm.setString(8, congNhan.getAnhDaiDien());
            stm.setString(9, congNhan.getDiaChi());
            stm.setDate(10, new java.sql.Date(congNhan.getNgayVaoLam().getTime()));
            stm.setString(11, congNhan.getToNhom().getMaToNhom());
            stm.setString(12, congNhan.getMaCongNhan());

            soLuongDuocCapNhat = stm.executeUpdate();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongDuocCapNhat != 0;
    }

    public boolean xoaCongNhanTheoMa(String maCongNhan) {
        PreparedStatement stm = null;
        int soLuongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from CongNhan where maCongNhan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
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

    public int layRaSoLuongCongNhan() {
        Statement stm = null;
        int soLuong = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select count(maCongNhan) as soLuongCongNhan from CongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                soLuong = rs.getInt("soLuongCongNhan");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuong;
    }

    public String layRaMaCongNhanDeThem() {
        Statement stm = null;
        String maCongNhan = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from CongNhan order by LEN(maCongNhan), maCongNhan desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                maCongNhan = rs.getString("maCongNhan");

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
        if (maCongNhan == null || maCongNhan.equals("")) {
            return "CN100001";
        }
        String chuoiCanLay = maCongNhan.split("CN")[1];

        try {
            chuoiCanLay = "CN" + (Integer.parseInt(chuoiCanLay) + 1);
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
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        Date ngaySinh = null;
        try {
            ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(LocalDate.of(2000, Month.DECEMBER, 29).toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Lấy 1 công nhân: " + congNhan_DAO.layMotCongNhanTheoMa("CN123123"));
        System.out.println(congNhan_DAO.themMotCongNhan(new CongNhan("CN111111", "Nguyễn Văn Vũ",
                ngaySinh, "111222333444", "0975123123", "hieurio12@gmail.com",
                "123123", new Date(), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0))));
        System.out.println("Hiển thị: " + congNhan_DAO.layDanhSachCongNhan());
        System.out.println("Sửa: " + congNhan_DAO.capNhatMotCongNhan(new CongNhan("CN111111", "Nguyễn Văn Vũ",
                java.sql.Date.valueOf(LocalDate.of(2000, 11, 11)), "111222333444", "0975123123", "hieurio12@gmail.com",
                "123123", java.sql.Date.valueOf(LocalDate.of(1999, 12, 12)), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0))));
        System.out.println("Hiển thị: " + congNhan_DAO.layDanhSachCongNhan());
        System.out.println("Xóa" + congNhan_DAO.xoaCongNhanTheoMa("CN111111"));
    }
}
