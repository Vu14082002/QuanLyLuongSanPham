/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import ConnectionDB.ConnectDB;
import Entity.CongDoan;
import Entity.CongNhan;
import Entity.NhanVien;
import Entity.PhanCongCongNhan;
import Entity.ToNhom;
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
                int soLuongCanLam = rs.getInt("soLuongCanLam");
                String maToNhom = rs.getString("maToNhom");
                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(maCongDoan);
                NhanVien nguoiPhanCong = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNguoiPhanCong);
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsPhanCongCongNhan.add(new PhanCongCongNhan(maPhanCong, congNhan, congDoan, nguoiPhanCong, ngayPhanCong, soLuongCanLam, toNhom));
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
                int soLuongCanLam = rs.getInt("soLuongCanLam");
                String maToNhom = rs.getString("maToNhom");
                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(maCongDoan);
                NhanVien nguoiPhanCong = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNguoiPhanCong);
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                phanCongCongNhan = new PhanCongCongNhan(maPhanCongOb, congNhan, congDoan, nguoiPhanCong, ngayPhanCong, soLuongCanLam, toNhom);
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

    public ArrayList<PhanCongCongNhan> layDanhSachPhanCongTheoMaCongDoan(String maCongDoanTemp, String maCongNhanTemp) {
        PreparedStatement stm = null;
        ArrayList<PhanCongCongNhan> dsPhanCong = new ArrayList<PhanCongCongNhan>();
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        CongDoan_DAO congDoan_DAO = new CongDoan_DAO();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM PhanCongCongNhan WHERE maCongDoan = ? and maCongNhan = ? ";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoanTemp);
            stm.setString(2, maCongNhanTemp);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maPhanCongOb = rs.getString("maPhanCong");
                String maCongNhan = rs.getString("maCongNhan");
                String maNguoiPhanCong = rs.getString("maNguoiPhanCong");
                String maCongDoan = rs.getString("maCongDoan");
                Date ngayPhanCong = rs.getDate("ngayPhanCong");
                int soLuongCanLam = rs.getInt("soLuongCanLam");
                String maToNhom = rs.getString("maToNhom");
                CongDoan congDoan = congDoan_DAO.layMotCongDoanTheoMaCongDoan(maCongDoan);
                NhanVien nguoiPhanCong = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNguoiPhanCong);
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhom);
                dsPhanCong.add(new PhanCongCongNhan(maPhanCongOb, congNhan, congDoan, nguoiPhanCong, ngayPhanCong, soLuongCanLam, toNhom));
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
        return dsPhanCong;
    }

    public boolean themMotPhanCongNhan(PhanCongCongNhan phanCongCongNhan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "insert into PhanCongCongNhan(maPhanCong, maCongNhan"
                    + " , maNguoiPhanCong, maCongDoan, ngayPhanCong, soLuongCanLam, maToNhom)"
                    + " values(? , ? , ?, ? , ?, ? , ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, phanCongCongNhan.getMaPhanCong());
            stm.setString(2, phanCongCongNhan.getCongNhan().getMaCongNhan());
            stm.setString(3, phanCongCongNhan.getNguoiPhanCong().getMaNhanVien());
            stm.setString(4, phanCongCongNhan.getCongDoan().getMaCongDoan());
            stm.setDate(5, new java.sql.Date(phanCongCongNhan.getNgayPhanCong().getTime()));
            stm.setInt(6, phanCongCongNhan.getSoLuongCanLam());
            stm.setString(7, phanCongCongNhan.getToNhom().getMaToNhom());
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
                    + "  maCongDoan = ?, ngayPhanCong = ?, soLuongCanLam = ?,maToNhom = ?"
                    + "  where maPhanCong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, phanCongCongNhan.getCongNhan().getMaCongNhan());
            stm.setString(2, phanCongCongNhan.getNguoiPhanCong().getMaNhanVien());
            stm.setString(3, phanCongCongNhan.getCongDoan().getMaCongDoan());
            stm.setDate(4, new java.sql.Date(phanCongCongNhan.getNgayPhanCong().getTime()));
            stm.setInt(5, phanCongCongNhan.getSoLuongCanLam());
            stm.setString(6, phanCongCongNhan.getToNhom().getMaToNhom());
            stm.setString(7, phanCongCongNhan.getMaPhanCong());
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

    public boolean suaMotPhanCongNhanTheoMaCongDoan(String maToNhom, int soLuongCanLam, String maCongDoan, String maCongNhan) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "update PhanCongCongNhan set maToNhom =? , soLuongCanLam = ? where maCongDoan=? and maCongNhan= ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maToNhom);
            stm.setInt(2, soLuongCanLam);
            stm.setString(3, maCongDoan);
            stm.setString(4, maCongNhan);
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
        return soDongSuaDuoc > 0;
    }

    public boolean xoaMotPhanCongTheoMaPhanCong(String maPhanCong) {
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

    public boolean xoaMotPhanCongTheoMaToNhomVaMaCongDoan(String maToNhom, String maCongDoan) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "DELETE FROM PhanCongCongNhan WHERE maToNhom = ? and maCongDoan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maToNhom);
            stm.setString(2, maCongDoan);
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

    public ArrayList<CongNhan> layRaDanhSachCongNhanTheoCongDoanVaToNhom(String maCongDoan, String maToNhom, String ngayChamCong, String caLam) {
        PreparedStatement stm = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<>();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
//            String sql = "select * from PhanCongCongNhan PCCN join CongNhan CN"
//                    + " on PCCN.maCongNhan = CN.maCongNhan where maCongDoan = ? and maToNhom = ?";
            String sql = "select * from PhanCongCongNhan PCCN join CongNhan CN\n"
                    + "on PCCN.maCongNhan = CN.maCongNhan where maCongDoan = ? and maToNhom = ? \n"
                    + "and PCCN.maPhanCong not in (\n"
                    + "select maPhanCong from ChamCongCongNhan where ngayChamCong = ? and  caLam = ? \n"
                    + ")";
            stm = con.prepareStatement(sql);
            stm.setString(1, maCongDoan);
            stm.setString(2, maToNhom);
            stm.setString(3,ngayChamCong);
            stm.setString(4,caLam);
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
                String maToNhomTemp = rs.getString("toNhom");
                ToNhom toNhom = toNhom_DAO.layMotToNhomTheoMa(maToNhomTemp);
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

    public ArrayList<CongNhan> layRaDanhSachCongNhanTheoCongDoanVaCaLam(String maCongDoan) {
        PreparedStatement stm = null;
        ArrayList<CongNhan> dsCongNhan = new ArrayList<>();
        ToNhom_DAO toNhom_DAO = new ToNhom_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from PhanCongCongNhan PCCN join CongNhan CN "
                    + " on PCCN.maCongNhan = CN.maCongNhan where maCongDoan = ?";
            stm = con.prepareStatement(sql);
            stm.setString(1, maCongDoan);
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

    public String layRaMaPhanCongTheoMaCongDoanMaCongNhan(String maCongDoan, String maCongNhan) {
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
            while (rs.next()) {
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

    public ArrayList<String> layDanhSachToNhomTrongCongDoan(String maCongDoan) {
        PreparedStatement stm = null;
        int soDongKiemTra = 0;
        ArrayList<String> maToNhomList = new ArrayList<>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select  DISTINCT  maToNhom from PhanCongCongNhan where maCongDoan = ? ";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongDoan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                maToNhomList.add(rs.getString(1));
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
        return maToNhomList;
    }

    public boolean checkDuocPhanCong(String maCongDoan, String maToNhom) {
        PreparedStatement stm = null;
        int sl = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String sql = "select * from PhanCongCongNhan where  maCongDoan = ? and maToNhom= ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, maCongDoan);
            stm.setString(2, maToNhom);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                sl++;
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
        // return true if sl == 0(duoc phan cong) else false
        return sl == 0;
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
            System.out.println(e);
        }
    }
}