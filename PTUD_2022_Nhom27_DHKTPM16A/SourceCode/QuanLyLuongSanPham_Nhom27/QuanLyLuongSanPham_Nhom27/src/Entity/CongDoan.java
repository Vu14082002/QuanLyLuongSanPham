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
public class CongDoan {

    private String maCongDoan;
    private String tenCongDoan;
    private int soLuongCan;
    private String tinhTrang;
    private Date thoiHan;
    private SanPham sanPham;
    private double tienLuong;

    public CongDoan(String maCongDoan, String tenCongDoan, int soLuongCan, String tinhTrang, Date thoiHan, SanPham sanPham, double tienLuong) {
        try {
            setMaCongDoan(maCongDoan);
            setTenCongDoan(tenCongDoan);
            setSoLuongCan(soLuongCan);
            setTinhTrang(tinhTrang);
            setThoiHan(thoiHan);
            setSanPham(sanPham);
            setTienLuong(tienLuong);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaCongDoan() {
        return maCongDoan;
    }

    private void setMaCongDoan(String maCongDoan) throws Exception {
        if (maCongDoan.equals("")) {
            throw new Exception("Mã công đoạn không được để trống!");
        } else if (!maCongDoan.matches("^CD[1-9][0-9]{5}$")) {
            throw new Exception("Mã Công đoạn phải theo dạng CDxxxxxx với x là các kí tự số x đầu tiền từ [1-9] x sau từ [0-9]");
        } else {
            this.maCongDoan = maCongDoan;
        }
    }

    public String getTenCongDoan() {
        return tenCongDoan;
    }

    public void setTenCongDoan(String tenCongDoan) throws Exception {
        if (tenCongDoan.equals("")){
            throw new Exception("Tên công đoạn không được để trống!");
        } else {
            this.tenCongDoan = tenCongDoan;
        }
    }

    public int getSoLuongCan() {
        return soLuongCan;
    }

    public void setSoLuongCan(int soLuongCan) throws Exception {
        if (soLuongCan <= 0) {
            throw new Exception("Số lượng cần không được <= 0");
        } else {
            this.soLuongCan = soLuongCan;
        }
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public Date getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(Date thoiHan) throws Exception {
        this.thoiHan = thoiHan;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public double getTienLuong() {
        return tienLuong;
    }

    public void setTienLuong(double tienLuong) throws Exception {
        if (tienLuong <= 0) {
            throw new Exception("Tiền lương không được <= 0");
        } else {
            this.tienLuong = tienLuong;
        }
    }

    @Override
    public String toString() {
        return "CongDoan{" + "maCongDoan=" + maCongDoan + ", tenCongDoan=" + tenCongDoan + ", soLuongCan=" + soLuongCan + ", tinhTrang=" + tinhTrang + ", thoiHan=" + thoiHan + ", sanPham=" + sanPham + ", tienLuong=" + tienLuong + '}';
    }

}
