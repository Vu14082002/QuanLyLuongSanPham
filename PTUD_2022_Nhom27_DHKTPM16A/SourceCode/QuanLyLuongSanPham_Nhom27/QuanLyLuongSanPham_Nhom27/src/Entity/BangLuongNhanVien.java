/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Acer
 */
public class BangLuongNhanVien {

    private String maBangLuong;
    private NhanVien nhanVien;
    private int soNgayDiLam;
    private int soNgayNghi;
    private int soPhepNghi;
    private Date ngayTinh;
    private double tongTien;
    private String donViTien;

    public BangLuongNhanVien(String maBangLuong, NhanVien nhanVien, int soNgayDiLam, int soNgayNghi, int soPhepNghi, Date ngayTinh, double tongTien, String donViTien) {
        try {
            setMaBangLuong(maBangLuong);
            setNhanVien(nhanVien);
            setSoNgayDiLam(soNgayDiLam);
            setSoNgayNghi(soNgayNghi);
            setSoPhepNghi(soPhepNghi);
            setNgayTinh(ngayTinh);
            setTongTien(tongTien);
            setDonViTien(donViTien);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaBangLuong() {
        return maBangLuong;
    }

    private void setMaBangLuong(String maBangLuong) throws Exception {
        if (maBangLuong.equals("")) {
            throw new Exception("Mã bảng lương nhân viên không được để trống!");
        } else if (!maBangLuong.matches("^LN[1-9][0-9]{5}$")) {
            throw new Exception("Mã bảng lương phải theo dạng LNxxxxxx với x là các kí tự số x đầu tiền từ [1-9] x sau từ [0-9]");
        } else {
            this.maBangLuong = maBangLuong;
        }
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public int getSoNgayDiLam() {
        return soNgayDiLam;
    }

    public void setSoNgayDiLam(int soNgayDiLam) throws Exception {
        if (soNgayDiLam < 0) {
            throw new Exception("Số ngày đi làm không được < 0");
        } else {
            this.soNgayDiLam = soNgayDiLam;
        }
    }

    public int getSoNgayNghi() {
        return soNgayNghi;
    }

    public void setSoNgayNghi(int soNgayNghi) throws Exception {
        if (soNgayNghi < 0) {
            throw new Exception("Số ngày nghỉ không được < 0");
        } else {
            this.soNgayNghi = soNgayNghi;
        }
    }

    public int getSoPhepNghi() {
        return soPhepNghi;
    }

    public void setSoPhepNghi(int soPhepNghi) throws Exception {
        if (soPhepNghi > soNgayNghi) {
            throw new Exception("Số phép nghỉ không được nhiều hơn số ngày nghỉ");
        } else if (soPhepNghi < 0) {
            throw new Exception("Số phép nghỉ không được < 0");
        } else {
            this.soPhepNghi = soPhepNghi;
        }
    }

    public Date getNgayTinh() {
        return ngayTinh;
    }

    public void setNgayTinh(Date ngayTinh) throws Exception {
        if (ngayTinh.after(new Date())) {
            throw new Exception("Ngày tính không được sau ngày hiện tại");
        } else {
            this.ngayTinh = ngayTinh;
        }
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) throws Exception {
        if (tongTien < 0) {
            throw new Exception("Tổng tiền lương của nhân viên không được < 0");
        } else {
            this.tongTien = tongTien;
        }
    }

    public String getDonViTien() {
        return donViTien;
    }

    public void setDonViTien(String donViTien) throws Exception {
        if (!donViTien.equalsIgnoreCase("VND") && !donViTien.equalsIgnoreCase("USD")) {
            throw new Exception("Chỉ chấp nhận tiền VND hoặc USD");
        } else {
            this.donViTien = donViTien;
        }
    }

    @Override
    public String toString() {
        return "BangLuongNhanVien{" + "maBangLuong=" + maBangLuong + ", nhanVien=" + nhanVien + ", soNgayDiLam=" + soNgayDiLam + ", soNgayNghi=" + soNgayNghi + ", soPhepNghi=" + soPhepNghi + ", ngayTinh=" + ngayTinh + ", tongTien=" + tongTien + ", donViTien=" + donViTien + '}';
    }

}
