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
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);

                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));

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
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                congNhan = new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom);

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
            String truyVan = "INSERT INTO CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, gioiTinh, anhDaiDien, diaChi, ngayVaoLam, toNhom) "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congNhan.getMaCongNhan());
            stm.setString(2, congNhan.getHoTen());
            stm.setDate(3, new java.sql.Date(congNhan.getNgaySinh().getTime()));
            stm.setString(4, congNhan.getMaCCCD());
            stm.setString(5, congNhan.getSoDienThoai());
            stm.setString(6, congNhan.getEmail());
            stm.setBoolean(7, congNhan.isGioiTinh());
            stm.setString(8, congNhan.getAnhDaiDien());
            stm.setString(9, congNhan.getDiaChi());
            stm.setDate(10, new java.sql.Date(congNhan.getNgayVaoLam().getTime()));
            stm.setString(11, congNhan.getToNhom().getMaToNhom());

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
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
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

    public boolean capNhatMotCongNhan(CongNhan congNhan) {
        PreparedStatement stm = null;
        int soLuongDuocCapNhat = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE CongNhan"
                    + " SET hoTen = ?, ngaySinh = ?, maCCCD = ?,"
                    + " soDienThoai = ?, email = ?,"
                    + " gioiTinh = ?, anhDaiDien = ?, diaChi = ?,"
                    + " ngayVaoLam = ?, toNhom = ?"
                    + " WHERE maCongNhan = ? ";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, congNhan.getHoTen());
            stm.setDate(2, new java.sql.Date(congNhan.getNgaySinh().getTime()));
            stm.setString(3, congNhan.getMaCCCD());
            stm.setString(4, congNhan.getSoDienThoai());
            stm.setString(5, congNhan.getEmail());
            stm.setBoolean(6, congNhan.isGioiTinh());
            stm.setString(7, congNhan.getAnhDaiDien());
            stm.setString(8, congNhan.getDiaChi());
            stm.setDate(9, new java.sql.Date(congNhan.getNgayVaoLam().getTime()));
            stm.setString(10, congNhan.getToNhom().getMaToNhom());
            stm.setString(11, congNhan.getMaCongNhan());

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
    public ArrayList<CongNhan> layDanhSachCongNhanLamTrongThang(int thang, int nam) {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        PreparedStatement stm = null;
        
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan from ChamCongCongNhan CCCN JOIN PhanCongCongNhan PCCC on CCCN.maPhanCong = PCCC.maPhanCong"
                    + " join CongNhan CN on CN.maCongNhan = PCCC.maCongNhan"
                    + " where MONTH(ngayChamCong) = ? and year(ngayChamCong) = ? group by CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhan = rs.getString("maCongNhan");
                CongNhan congNhan = layMotCongNhanTheoMa(maCongNhan);
                dsCongNhan.add(congNhan);
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

    public ArrayList<CongNhan> layDanhSachCongNhanKhongDiLamTrongThang(int thang, int nam) {
        PreparedStatement stm = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from CongNhan CN"
                    + " where CN.maCongNhan not in (select distinct maCongNhan from PhanCongCongNhan PCCN "
                    + " join ChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " where month(ngayChamCong) = ? and year(ngayChamCong) = ?)";
            stm = con.prepareCall(truyVan);
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maCongNhan = rs.getString("maCongNhan");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String maCCCD = rs.getString("maCCCD");
                String soDienThoai = rs.getString("soDienThoai");
                String email = rs.getString("email");
                Boolean gioiTinh = rs.getBoolean("gioiTinh");
                String anhDaiDien = rs.getString("anhDaiDien");
                String diaChi = rs.getString("diaChi");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String maToNhom = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsCongNhan.add(new CongNhan(maCongNhan, hoTen, ngaySinh, maCCCD, soDienThoai, email, ngayVaoLam, gioiTinh, anhDaiDien, diaChi, toNhom));
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
        return dsCongNhan;
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
        
    }
}
