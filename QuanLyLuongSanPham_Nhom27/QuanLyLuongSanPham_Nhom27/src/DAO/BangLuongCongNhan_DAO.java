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
import Entity.BangLuongCongNhan;
import Entity.ChamCongCongNhan;
import Entity.CongNhan;
import Entity.ToNhom;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class BangLuongCongNhan_DAO {

    public BangLuongCongNhan_DAO() {
    }

    public ArrayList<BangLuongCongNhan> layDanhSachBangLuongCongNhan() {
        Statement stm = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        ArrayList<BangLuongCongNhan> dsBangLuongCongNhan = new ArrayList<BangLuongCongNhan>();
        try {
            ConnectionDB.ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongCongNhan";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maCongNhan = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                String luongTheoThang = rs.getString("luongTheoThang");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                dsBangLuongCongNhan.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang));
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
        return dsBangLuongCongNhan;
    }

    public ArrayList<BangLuongCongNhan> layDanhSachBangLuongTheoMaCongNhan(String maCongNhan) {
        ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<BangLuongCongNhan>();
        PreparedStatement stm = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongCongNhan where maCongNhan = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuong = rs.getString("maBangLuong");
                String maCongNhanOb = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                 String luongTheoThang = rs.getString("luongTheoThang");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhanOb);
                dsBangLuong.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang));
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

    public BangLuongCongNhan layMotBangLuongCongNhanTheoMa(String maBangLuong) {
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        PreparedStatement stm = null;
        BangLuongCongNhan bangLuongCongNhan = null;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "SELECT * FROM BangLuongCongNhan where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maBangLuong);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                String maBangLuongOB = rs.getString("maBangLuong");
                String maCongNhan = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                 String luongTheoThang = rs.getString("luongTheoThang");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
                bangLuongCongNhan = new BangLuongCongNhan(maBangLuongOB, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang);

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
        return bangLuongCongNhan;
    }

    public boolean themMotBangLuong(BangLuongCongNhan bangLuongCongNhan) {
        PreparedStatement stm = null;
        int soDongThemDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "INSERT INTO BangLuongCongNhan(maBangLuong, maCongNhan, ngayTinh"
                    + ", soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, tongLuong, donViTien, luongTheoThang)"
                    + " VALUES (?, ?, ?, ?, ?, ? ,? ,? ,?, ?)";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongCongNhan.getMaBangLuong());
            stm.setString(2, bangLuongCongNhan.getCongNhan().getMaCongNhan());
            stm.setDate(3, new java.sql.Date(bangLuongCongNhan.getNgayTinh().getTime()));
            stm.setInt(4, bangLuongCongNhan.getSoLuongSanPhamLam());
            stm.setInt(5, bangLuongCongNhan.getSoNgayDiLam());
            stm.setInt(6, bangLuongCongNhan.getSoNgayNghi());
            stm.setInt(7, bangLuongCongNhan.getSoPhepNghi());
            stm.setBigDecimal(8, new BigDecimal(bangLuongCongNhan.getTongLuong()));
            stm.setString(9, bangLuongCongNhan.getDonViTien());
            stm.setString(10, bangLuongCongNhan.getLuongTheoThang());
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

    public boolean suaMotBangLuongCongNhan(BangLuongCongNhan bangLuongCongNhan) {
        PreparedStatement stm = null;
        int soDongSuaDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "UPDATE BangLuongCongNhan"
                    + " set maCongNhan = ?, ngayTinh = ?,"
                    + " soLuongSanPhamLam = ?, soNgayDiLam = ?,"
                    + " soNgayNghi = ?, soPhepNghi = ?,"
                    + " tongLuong = ?, donViTien = ?, luongTheoThang = ?"
                    + " where maBangLuong = ?";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, bangLuongCongNhan.getCongNhan().getMaCongNhan());
            stm.setDate(2, new java.sql.Date(bangLuongCongNhan.getNgayTinh().getTime()));
            stm.setInt(3, bangLuongCongNhan.getSoLuongSanPhamLam());
            stm.setInt(4, bangLuongCongNhan.getSoNgayDiLam());
            stm.setInt(5, bangLuongCongNhan.getSoNgayNghi());
            stm.setInt(6, bangLuongCongNhan.getSoPhepNghi());
            stm.setBigDecimal(7, new BigDecimal(bangLuongCongNhan.getTongLuong()));
            stm.setString(8, bangLuongCongNhan.getDonViTien());
            stm.setString(9, bangLuongCongNhan.getLuongTheoThang());
            stm.setString(10, bangLuongCongNhan.getMaBangLuong());
            
            soDongSuaDuoc = stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stm.close();;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return soDongSuaDuoc != 0;
    }

    public boolean xoaBangLuongCongNhanTheoMa(String maBangLuong) {
        PreparedStatement stm = null;
        int soDongXoaDuoc = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "DELETE BangLuongCongNhan"
                    + "  where maBangLuong = ?";
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

    public ArrayList<CongNhan> layDanhSachCongNhanLamTrongThang(int thang, int nam) {
        ArrayList<CongNhan> dsCongNhan = new ArrayList<CongNhan>();
        PreparedStatement stm = null;
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
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
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhan);
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

    public double layRaTongTienTheoMaCongNhan(String maCongNhan, int thang, int nam) {
        PreparedStatement stm = null;
        double tongTien = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan, sum(CCCN.soLuongLam*CD.tienLuong) as tongTienLuong from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
                    + " JOIN ChamCongCongNhan CCCN ON CCCN.maPhanCong = PCCN.maPhanCong"
                    + " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan"
                    + " where CN.maCongNhan = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
                    + " GROUP BY CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tongTien = rs.getDouble("tongTienLuong");
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
        return tongTien;
    }

    public int laySoNgayDiLam(String maCongNhan, int thang, int nam) {
        PreparedStatement stm = null;
        int soNgay = 0;
        String diLam = "Đi Làm";
        String diTre = "Đi Trễ";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan, count(trangThaiDiLam) as soNgayDiLam from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
                    + " join ChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
                    + " where CN.maCongNhan = ? and (trangThaiDiLam = ? or trangThaiDiLam = ?) "
                    + " and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
                    + " GROUP BY CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            stm.setString(2, diLam);
            stm.setString(3, diTre);
            stm.setInt(4, thang);
            stm.setInt(5, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soNgay = rs.getInt("soNgayDiLam");
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
        return soNgay;
    }

    public int layRaSoNgayNghiCoPhep(String maCongNhan, int thang, int nam) {
        PreparedStatement stm = null;
        int soNgay = 0;
        String nghiCoPhep = "Nghỉ Có Phép";

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan, count(trangThaiDiLam) as soNgayPhep from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
                    + " join ChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
                    + " where CN.maCongNhan = ? and trangThaiDiLam = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
                    + " GROUP BY CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            stm.setString(2, nghiCoPhep);
            stm.setInt(3, thang);
            stm.setInt(4, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soNgay = rs.getInt("soNgayPhep");
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
        return soNgay;
    }

    public int layRaSoNgayNghiKhongPhep(String maCongNhan, int thang, int nam) {
        PreparedStatement stm = null;
        int soNgay = 0;
        String nghiKoCoPhep = "Nghỉ Không Phép";

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan, count(trangThaiDiLam) as soNgayKhongPhep from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
                    + " join ChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
                    + " where CN.maCongNhan = ? and trangThaiDiLam = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
                    + " GROUP BY CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            stm.setString(2, nghiKoCoPhep);
            stm.setInt(3, thang);
            stm.setInt(4, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                soNgay = rs.getInt("soNgayKhongPhep");
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
        return soNgay;
    }

    public int layRaTongSoLuongSPLam(String maCongNhan, int thang, int nam) {
        PreparedStatement stm = null;
        int tongSoLuong = 0;

        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select CN.maCongNhan, sum(soLuongLam) as tongSoLuongLam from CongNhan CN join PhanCongCongNhan PCCN on CN.maCongNhan = PCCN.maCongNhan"
                    + " join ChamCongCongNhan CCCN on PCCN.maPhanCong = CCCN.maPhanCong"
                    + " JOIN CongDoan CD on CD.maCongDoan = PCCN.maCongDoan "
                    + " where CN.maCongNhan = ? and  MONTH(ngayChamCong) = ? and year(ngayChamCong) = ?"
                    + " GROUP BY CN.maCongNhan";
            stm = con.prepareStatement(truyVan);
            stm.setString(1, maCongNhan);
            stm.setInt(2, thang);
            stm.setInt(3, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                tongSoLuong = rs.getInt("tongSoLuongLam");
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
        return tongSoLuong;
    }

    public boolean tinhLuongCongNhan(int thang, int nam) {
        ArrayList<CongNhan> dsCongNhanDiLamTrongThang = layDanhSachCongNhanLamTrongThang(thang, nam);
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        boolean flag = false;
        for (CongNhan congNhan : dsCongNhanDiLamTrongThang) {
            double tongLuong = layRaTongTienTheoMaCongNhan(congNhan.getMaCongNhan(), thang, nam);
            int soNgayDiLam = laySoNgayDiLam(congNhan.getMaCongNhan(), thang, nam);
            int soNgayNghi = layRaSoNgayNghiCoPhep(congNhan.getMaCongNhan(), thang, nam);
            int soPhepNghi = layRaSoNgayNghiKhongPhep(congNhan.getMaCongNhan(), thang, nam);
            int soLuongSanPhamLam = layRaTongSoLuongSPLam(congNhan.getMaCongNhan(), thang, nam);

            flag = themMotBangLuong(new BangLuongCongNhan(layRaMotMaBangLuongDeThem(), congNhan, soLuongSanPhamLam,
                    soNgayDiLam, soNgayNghi, soPhepNghi, new Date(), tongLuong, "VND", thang+"-"+nam));
        }
        ArrayList<CongNhan> dsCongNhanKhongDuocChamCong = congNhan_DAO.layDanhSachCongNhanKhongDiLamTrongThang(thang, nam);
        for (CongNhan congNhan : dsCongNhanKhongDuocChamCong){
            flag = themMotBangLuong(new BangLuongCongNhan(layRaMotMaBangLuongDeThem(), congNhan, 0, 0, 0, 0, new Date(), 0, "VND", thang+"-"+nam));
        }
        return flag;
    }

    public String layRaMotMaBangLuongDeThem() {
        Statement stm = null;
        String maBangLuong = "";
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select top 1 * from BangLuongCongNhan order by LEN(maBangLuong), maBangLuong desc";
            stm = con.createStatement();
            ResultSet rs = stm.executeQuery(truyVan);
            while (rs.next()) {
                maBangLuong = rs.getString("maBangLuong");

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
        if (maBangLuong == null || maBangLuong.equals("")){
            return "LC100001";
        }
        String chuoiCanLay = maBangLuong.split("LC")[1];

        try {
            chuoiCanLay = "LC" + (Integer.parseInt(chuoiCanLay) + 1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chuoiCanLay;
    }

    public boolean kiemTraKhaThiChoTinhThangNay(int thang, int nam) {
        PreparedStatement stm = null;
        int soLuongDongTimThay = 0;
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from BangLuongCongNhan"
                    + " where month(ngayTinh) = ? and year(ngayTinh) = ?";
            stm = con.prepareStatement(truyVan);
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                ++soLuongDongTimThay;
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
        return soLuongDongTimThay == 0;
    }
    public ArrayList<BangLuongCongNhan> layDanhSachBangLuongTheoThangNam(int thang, int nam){
        PreparedStatement stm = null;
        ArrayList<BangLuongCongNhan> dsBangLuong = new ArrayList<BangLuongCongNhan>();
        CongNhan_DAO congNhan_DAO = new CongNhan_DAO();
        try {
            ConnectDB.getInstance();
            Connection con = ConnectDB.getConnection();
            String truyVan = "select * from BangLuongCongNhan where MONTH(ngayTinh) = ? and YEAR(ngayTinh) = ?";
            stm = con.prepareStatement(truyVan);
            stm.setInt(1, thang);
            stm.setInt(2, nam);
            ResultSet rs = stm.executeQuery();
            while (rs.next()){
               String maBangLuong = rs.getString("maBangLuong");
                String maCongNhanOb = rs.getString("maCongNhan");
                Date ngayTinh = rs.getDate("ngayTinh");
                int soLuongSanPhamLam = rs.getInt("soLuongSanPhamLam");
                int soNgayDiLam = rs.getInt("soNgayDiLam");
                int soNgayNghi = rs.getInt("soNgayNghi");
                int soPhepNghi = rs.getInt("soPhepNghi");
                double tongLuong = rs.getBigDecimal("tongLuong").doubleValue();
                String donViTien = rs.getString("donViTien");
                String luongTheoThang = rs.getString("luongTheoThang");
                CongNhan congNhan = congNhan_DAO.layMotCongNhanTheoMa(maCongNhanOb);
                dsBangLuong.add(new BangLuongCongNhan(maBangLuong, congNhan,
                        soLuongSanPhamLam, soNgayDiLam, soNgayNghi, soPhepNghi, ngayTinh, tongLuong, donViTien, luongTheoThang));
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
        return dsBangLuong;
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
//        BangLuongCongNhan_DAO dao = new BangLuongCongNhan_DAO();
//        System.out.println("Thêm một: "
//                + dao.themMotBangLuong(new BangLuongCongNhan("LC111111", new CongNhan("CN123123", "Nguyễn Văn Vũ",
//                        java.sql.Date.valueOf(LocalDate.of(2000, 11, 11)), "111222333444", "0975123123", "hieurio12@gmail.com",
//                        "123123", java.sql.Date.valueOf(LocalDate.of(1999, 12, 12)), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0)), 123,
//                        22, 12, 2, new Date(), 123121, "VND")));
//        System.out.println(dao.layDanhSachBangLuongCongNhan());
//        System.err.println("Sửa:" + dao.suaMotBangLuongCongNhan(new BangLuongCongNhan("LC111111", new CongNhan("CN123123", "Nguyễn Văn Vũ",
//                java.sql.Date.valueOf(LocalDate.of(2000, 11, 11)), "111222333444", "0975123123", "hieurio12@gmail.com",
//                "123123", java.sql.Date.valueOf(LocalDate.of(1999, 12, 12)), false, "anhDaiDien1.png", "Yên bái", new ToNhom("TN123123", "1", 0)), 123,
//                22, 12, 2, new Date(), 123121, "VND")));
//        System.out.println("Xóa: " + dao.xoaBangLuongCongNhanTheoMa("LC111111"));
//        System.out.println(dao.layDanhSachBangLuongCongNhan());
//        System.out.println("\n\nLay mot: " + dao.layMotBangLuongCongNhanTheoMa("LC123123"));
//        System.out.println(dao.layRaMotMaBangLuongDeThem());
    }
}
