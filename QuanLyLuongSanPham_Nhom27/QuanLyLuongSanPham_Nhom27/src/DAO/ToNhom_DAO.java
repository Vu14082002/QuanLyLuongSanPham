/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author Nhóm 27
 */
import ConnectionDB.ConnectDB;
import Entity.ToNhom;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ToNhom_DAO {

    public ToNhom_DAO() {
    }

    public ArrayList<ToNhom> layDanhSachToNhom() {
        ArrayList<ToNhom> dsToNhom = new ArrayList<ToNhom>();
        Statement stm = null;

        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String chuoiTruyVan = "select * from ToNhom";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(chuoiTruyVan);
            while (rs.next()) {
                String maTo = rs.getString("maToNhom");
                String tenToNhom = rs.getString("tenTo");

                dsToNhom.add(new ToNhom(maTo, tenToNhom));
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
        return dsToNhom;
    }

    public boolean themToNhom(ToNhom toNhom) {
        PreparedStatement stm = null;
        int soLuongThemDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String cauLenh = "INSERT INTO ToNhom(maToNhom, tenTo) VALUES (?, ?)";
            stm = con.prepareStatement(cauLenh);
            stm.setString(1, toNhom.getMaToNhom());
            stm.setString(2, toNhom.getTenToNhom());
            soLuongThemDuoc = stm.executeUpdate();
            if (soLuongThemDuoc == 0) {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    public boolean suaToNhom(ToNhom toNhom) {
        PreparedStatement stm = null;
        int soLuongSua = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String cauLenh = "UPDATE ToNhom"
                    + " set tenTo = ?"
                    + " where maToNhom = ?";
            stm = con.prepareStatement(cauLenh);
            stm.setString(1, toNhom.getTenToNhom());
            stm.setString(2, toNhom.getMaToNhom());
            soLuongSua = stm.executeUpdate();
            if (soLuongSua == 0) {
                return false;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally{
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return true;
    }

    public boolean xoaMotToNhomTheoMa(String maToNhom) {
        PreparedStatement stm = null;
        int soLuongXoa = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String cauLenh = "DELETE FROM ToNhom"
                    + " where maToNhom = ?";
            stm = con.prepareStatement(cauLenh);
            stm.setString(1, maToNhom);
            soLuongXoa = stm.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        } finally{
            try {
                stm.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soLuongXoa != 0;
    }
    public ToNhom layMotToNhomTheoMa(String maToNhom){
        PreparedStatement stm = null;
        ToNhom toNhom = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM ToNhom WHERE maToNhom = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maToNhom);
            ResultSet rs = stm.executeQuery();
           
            while (rs.next()){
                String maToNhomOb = rs.getString("maToNhom");
                String tenToNhom = rs.getString("tenTo");
                
                toNhom = new ToNhom(maToNhomOb, tenToNhom);
            }
            if (toNhom == null){
                throw new Exception("Không tìm thấy tổ nhóm này!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return toNhom;
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
        ToNhom_DAO dao = new ToNhom_DAO();
        System.out.println("AAA" + dao.layMotToNhomTheoMa("TN123123"));
        System.out.println(dao.themToNhom(new ToNhom("TN111112", "Tổ 2")));
        System.out.println(dao.suaToNhom(new ToNhom("TN111112", "Tổ 4")));
        System.out.println("Xóa " + dao.xoaMotToNhomTheoMa("TN111112"));
        ArrayList<ToNhom> dsToNhom = dao.layDanhSachToNhom();
        System.out.println(dsToNhom.toString());
       
    }
}
