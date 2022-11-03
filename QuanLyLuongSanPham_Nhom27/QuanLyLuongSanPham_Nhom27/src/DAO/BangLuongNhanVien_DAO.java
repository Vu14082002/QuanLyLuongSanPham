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
import Entity.NhanVien;
import Entity.PhongBan;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BangLuongNhanVien_DAO {

    public BangLuongNhanVien_DAO() {
    }

    public ArrayList<BangLuongNhanVien> danhSachBangLuong() {
        Statement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<BangLuongNhanVien>();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangLuongNhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maNhanVien = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                double tongTien = rs.getBigDecimal("tongTien").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVien);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongTien, donViTien));
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
        return dsBangLuong;
    }

    public ArrayList<BangLuongNhanVien> layDanhSachBangLuongTheoMaNhanVien(String maNhanVien) {
        PreparedStatement stm = null;
        ArrayList<BangLuongNhanVien> dsBangLuong = new ArrayList<BangLuongNhanVien>();
        NhanVien_DAO nhanVien_DAO = new NhanVien_DAO();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select * from BangLuongNhanVien where maNhanVien = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maNhanVienOB = rs.getString("maNhanVien");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                Date ngayTinh = rs.getDate("ngayTinh");
                double tongTien = rs.getBigDecimal("tongTien").doubleValue();
                String donViTien = rs.getString("donViTien");
                NhanVien nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(maNhanVienOB);
                dsBangLuong.add(new BangLuongNhanVien(maBangLuong, nhanVien, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongTien, donViTien));
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
        return dsBangLuong;
    }

    public boolean xoaBangLuongInsert(String thang, String nam, int xoa) {
        System.out.println("Xoa");
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        if (xoa == 0) {
            try {
                ConnectionDB.ConnectDB.getInstance();
                Connection con = ConnectionDB.ConnectDB.getConnection();
                String truyVan = "delete BangLuongNhanVien where MONTH(ngayTinh)= ? and YEAR(ngayTinh)= ?";
                stm = con.prepareStatement(truyVan);
                stm.setString(1, thang);
                stm.setString(2, nam);
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
        }
        return soDongXoaDuoc != 0;
    }

    public boolean themMotBangLuong(BangLuongNhanVien bangLuongNhanVien, String thang, String nam) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            xoaBangLuongInsert(thang, nam,0);
            String truyVan = "INSERT INTO BangLuongNhanVien(maBangLuong, maNhanVien"
                    + " , soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh"
                    + " , tongTien, donViTien)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongNhanVien.getMaBangLuong());
            stm.setString(2, bangLuongNhanVien.getNhanVien().getMaNhanVien());
            stm.setInt(3, bangLuongNhanVien.getSoNgayDiLam());
            stm.setInt(4, bangLuongNhanVien.getSoNgayNghi());
            stm.setInt(5, bangLuongNhanVien.getSoPhepNghi());
            stm.setDate(6, new java.sql.Date(bangLuongNhanVien.getNgayTinh().getTime()));
            stm.setBigDecimal(7, new BigDecimal(bangLuongNhanVien.getTongTien()));
            stm.setString(8, bangLuongNhanVien.getDonViTien());
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

    public boolean suaMotBangLuong(BangLuongNhanVien bangLuongNhanVien) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "UPDATE BangLuongNhanVien"
                    + " set maNhanVien = ?, soNgayDiLam = ?, soNgayNghi = ?"
                    + " , soPhepNghi = ?, ngayTinh = ?, tongTien = ?, donViTien = ?"
                    + " where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongNhanVien.getNhanVien().getMaNhanVien());
            stm.setInt(2, bangLuongNhanVien.getSoNgayDiLam());
            stm.setInt(3, bangLuongNhanVien.getSoNgayNghi());
            stm.setInt(4, bangLuongNhanVien.getSoPhepNghi());
            stm.setDate(5, new java.sql.Date(bangLuongNhanVien.getNgayTinh().getTime()));
            stm.setBigDecimal(6, new BigDecimal(bangLuongNhanVien.getTongTien()));
            stm.setString(7, bangLuongNhanVien.getDonViTien());
            stm.setString(8, bangLuongNhanVien.getMaBangLuong());
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

    public boolean xoaMotBangLuongTheoMaBangLuong(String maBangLuong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "delete from BangLuongNhanVien where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maBangLuong);
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
    }
    // code by vu

    public int laySoNgayDilamCuaNhanVien(String maNhanVien, int thang, int nam) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select DISTINCT ngayChamCong from ChamCongNhanVien where maNhanVien = ? \n"
                    + "and DAY(ngayChamCong) >=1 and DAY(ngayChamCong) <=31 and \n"
                    + "month(ngayChamCong) = ? and YEAR(ngayChamCong) = ? and trangThaiDiLam not like N'%Nghỉ%'";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soDongSuaDuoc++;
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
        return soDongSuaDuoc;
    }

    public int laySoNgayNghiCoPhepCuaNhanVien(String maNhanVien, int thang, int nam) {
        PreparedStatement stm = null;
        int soNgayNghiCoPhep = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select DISTINCT ngayChamCong from ChamCongNhanVien where maNhanVien = ? \n"
                    + "and DAY(ngayChamCong) >=1 and DAY(ngayChamCong) <=31 and \n"
                    + "month(ngayChamCong) =? and YEAR(ngayChamCong) =? and trangThaiDiLam  like N'%Nghỉ Có%'";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maNhanVien);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soNgayNghiCoPhep++;
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
        return soNgayNghiCoPhep;
    }

    public ArrayList<String> layDanhSachMaNhanVienKhongTrung() {
        Statement stm = null;
        ArrayList<String> dsNhanVien = new ArrayList<>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            String truyVan = "select DISTINCT maNhanVien from  ChamCongNhanVien";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                dsNhanVien.add(rs.getString(1));
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

    public boolean themMotBangLuongString(String maLuong, String maNhanVien, int soNgayLam, int soNgayNghi, int soPhepNghi, Date ngayTinh,
            double tongTien, String donViTien, String thang, String nam, int xoa) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectionDB.ConnectDB.getConnection();
            xoaBangLuongInsert(thang, nam, xoa);
            String truyVan = "INSERT INTO BangLuongNhanVien values ( ? , ? , ? , ? , ? , ? , ? , ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maLuong);
            stm.setString(2, maNhanVien);
            stm.setInt(3, soNgayLam);
            stm.setInt(4, soNgayNghi);
            stm.setInt(5, soPhepNghi);
            stm.setDate(6, new java.sql.Date(ngayTinh.getTime()));
            stm.setDouble(7, tongTien);
            stm.setString(8, donViTien);
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
}
