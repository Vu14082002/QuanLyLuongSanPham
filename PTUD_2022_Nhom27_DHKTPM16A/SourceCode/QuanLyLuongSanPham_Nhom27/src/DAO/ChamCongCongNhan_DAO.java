/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.ChamCongCongNhan;
import Entity.PhanCongCongNhan;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
            String truyVan = "select * from ChamCongCongNhan order by ngayChamCong desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maPhanCong = rs.getString("maPhanCong");
                String caLam = rs.getString("caLam");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                int soLuongLam = rs.getInt("soLuongLam");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                PhanCongCongNhan phanCong = phanCongCongNhan_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
                dsChamCong.add(new ChamCongCongNhan(phanCong, caLam, ngayChamCong, soLuongLam, trangThaiDiLam, gioDiLam));
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
            String truyVan = "insert into ChamCongCongNhan(maPhanCong, caLam, ngayChamCong"
                    + " , soLuongLam, trangThaiDiLam, gioDiLam)"
                    + " values(?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, chamCongCongNhan.getPhanCong().getMaPhanCong());
            stm.setString(2, chamCongCongNhan.getCaLam());
            stm.setDate(3, new java.sql.Date(chamCongCongNhan.getNgayChamCong().getTime()));
            stm.setInt(4, chamCongCongNhan.getSoLuongLam());
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
                    + " set soLuongLam = ?"
                    + " ,trangThaiDiLam = ?, gioDiLam = ?"
                    + " where maPhanCong = ? AND caLam = ? AND ngayChamCong = ?";
            stm = con.prepareStatement(truyVan);

            stm.setInt(1, chamCongCongNhan.getSoLuongLam());
            stm.setString(2, chamCongCongNhan.getTrangThaiDiLam());
            stm.setString(3, chamCongCongNhan.getGioDiLam());
            stm.setString(4, chamCongCongNhan.getPhanCong().getMaPhanCong());
            stm.setString(5, chamCongCongNhan.getCaLam());
            stm.setDate(6, new java.sql.Date(chamCongCongNhan.getNgayChamCong().getTime()));

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

    public boolean xoaChamCongCongNhanTheoMa(String maPhanCong, String caLam, Date ngayChamCong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from ChamCongCongNhan where maPhanCong = ? and caLam = ? and ngayChamCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maPhanCong);
            stm.setString(2, caLam);
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
    public ChamCongCongNhan layMotChamCongTheoMaPhanCongCaLamNgayLam(String maPhanCong, Date ngayLam, String caLam){
        PreparedStatement stm = null;
        ChamCongCongNhan cccn = null;
        PhanCongCongNhan_DAO phanCong_DAO = new PhanCongCongNhan_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from ChamCongCongNhan where maPhanCong = ? AND caLam = ? AND ngayChamCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maPhanCong);
            stm.setString(2, caLam);
            stm.setDate(3, new java.sql.Date(ngayLam.getTime()));
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maPhanCong2 = rs.getString("maPhanCong");
                String caLam2 = rs.getString("caLam");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                int soLuongLam = rs.getInt("soLuongLam");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
                cccn = new ChamCongCongNhan(phanCong, caLam, ngayChamCong, soLuongLam, trangThaiDiLam, gioDiLam);
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
        return cccn;
    }
    public ArrayList<ChamCongCongNhan> layDanhSachChamCongTheoMaCongNhan(String maCongNhan, int thang, int nam) {
        PreparedStatement stm = null;
        ArrayList<ChamCongCongNhan> dsChamCong = new ArrayList<ChamCongCongNhan>();
        PhanCongCongNhan_DAO phanCong_DAO = new PhanCongCongNhan_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from PhanCongCongNhan PCCN"
                    + " join ChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " where maCongNhan = ? and month(ngayChamCong) = ? and YEAR(ngayChamCong) = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maPhanCong = rs.getString("maPhanCong");
                String caLam = rs.getString("caLam");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                int soLuongLam = rs.getInt("soLuongLam");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
                dsChamCong.add(new ChamCongCongNhan(phanCong, caLam, ngayChamCong, soLuongLam, trangThaiDiLam, gioDiLam));
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

    public ArrayList<ChamCongCongNhan> layDanhSachChamCongTheoNgay(Date date){
        PreparedStatement stm = null;
        ArrayList<ChamCongCongNhan> dsChamCong = new ArrayList<ChamCongCongNhan>();
        PhanCongCongNhan_DAO phanCong_DAO = new PhanCongCongNhan_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from ChamCongCongNhan where ngayChamCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
                String maPhanCong = rs.getString("maPhanCong");
                String caLam = rs.getString("caLam");
                Date ngayChamCong = rs.getDate("ngayChamCong");
                int soLuongLam = rs.getInt("soLuongLam");
                String trangThaiDiLam = rs.getString("trangThaiDiLam");
                String gioDiLam = rs.getString("gioDiLam");
                PhanCongCongNhan phanCong = phanCong_DAO.layMotPhanCongCongNhanTheoMaPhanCong(maPhanCong);
                dsChamCong.add(new ChamCongCongNhan(phanCong, caLam, ngayChamCong, soLuongLam, trangThaiDiLam, gioDiLam));
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
    }
}