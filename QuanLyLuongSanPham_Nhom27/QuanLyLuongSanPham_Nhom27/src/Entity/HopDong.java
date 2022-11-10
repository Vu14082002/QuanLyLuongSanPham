/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Acer
 */
public class HopDong {
    private String maHopDong;
    private String tenHopDong;
    private String tenKhachHang;
    private double soTienCoc;
    private double tongTien;
    private Date ngayKyKet;
    private Date hanChot;
    private String yeuCau;
    
    public HopDong(){
        
    }
    public HopDong(String maHopDong){
        this.maHopDong=maHopDong;
    }

    public HopDong(String maHopDong, String tenHopDong, String tenKhachHang, double soTienCoc, double tongTien, Date ngayKyKet, Date hanChot, String yeuCau) {
        try {
            setMaHopDong(maHopDong);
            setTenHopDong(tenHopDong);
            setTenKhachHang(tenKhachHang);
            setSoTienCoc(soTienCoc);
            setTongTien(tongTien);
            setNgayKyKet(ngayKyKet);
            setHanChot(hanChot);
            setYeuCau(yeuCau);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) throws Exception {
        if (maHopDong.equals("")){
            throw new Exception("Mã hợp đồng không được để trống!");
        } else if (!maHopDong.matches("^HD[1-9][0-9]{5}$")) {
            throw new Exception("Mã hợp đồng phải theo dạng HDxxxxxx với x là các kí tự số từ 0-9 x đầu tiên từ 1-9");
        } else {
            this.maHopDong = maHopDong;
        }
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) throws Exception {
        if (tenKhachHang.equals("")){
            throw new Exception("Tên khách hàng không được trống!");
        } else {
            this.tenKhachHang = tenKhachHang;
        }
    }

    public double getSoTienCoc() {
        return soTienCoc;
    }

    public void setSoTienCoc(double soTienCoc) throws Exception {
        if (soTienCoc < 0){
            throw new Exception("Số tiền cọc phải >= 0");
        } else {
            this.soTienCoc = soTienCoc;
        }
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) throws Exception {
        if (tongTien < 0){
             throw new Exception("Tổng tiền cọc phải >= 0");
        } else {
            this.tongTien = tongTien;
        }
    }

    public Date getNgayKyKet() {
        return ngayKyKet;
    }

    public void setNgayKyKet(Date ngayKyKet) throws Exception {
        if (ngayKyKet.after(new Date())){
            throw new Exception("Ngày kí kết không được sau ngày hiện tại");
        } else {
            this.ngayKyKet = ngayKyKet;
        }
    }

    public Date getHanChot() {
        return hanChot;
    }

    public void setHanChot(Date hanChot) throws Exception {
        if (!hanChot.after(new Date())){
            throw new Exception("Hạn chót phải sau ngày hiện tại!");
        } else {
            this.hanChot = hanChot;
        }
    }

    public String getYeuCau() {
        return yeuCau;
    }

    public void setYeuCau(String yeuCau) {
        this.yeuCau = yeuCau;
    }

    public String getTenHopDong() {
        return tenHopDong;
    }

    public void setTenHopDong(String tenHopDong) throws Exception {
        if (tenHopDong.equals("")){
            throw new Exception("Tên hợp đồng không được trống!");
        } else {
            this.tenHopDong = tenHopDong;
        }
    }

    @Override
    public String toString() {
        return "HopDong{" + "maHopDong=" + maHopDong + ", tenHopDong=" + tenHopDong + ", tenKhachHang=" + tenKhachHang + ", soTienCoc=" + soTienCoc + ", tongTien=" + tongTien + ", ngayKyKet=" + ngayKyKet + ", hanChot=" + hanChot + ", yeuCau=" + yeuCau + '}';
    }

    
    
    
    
}
