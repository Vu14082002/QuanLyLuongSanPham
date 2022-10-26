/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Acer
 */
public class PhongBan {

    private String maPhongBan;
    private String tenPhongBan;
    private int soLuongNhanVien;

    public PhongBan() {
    }

    public PhongBan(String maPhongBan) {
    }

    public PhongBan(String maPhongBan, String tenPhongBan, int soLuongNhanVien) {
        try {
            setMaPhongBan(maPhongBan);
            setTenPhongBan(tenPhongBan);
            setSoLuongNhanVien(soLuongNhanVien);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getMaPhongBan() {
        return maPhongBan;
    }

    private void setMaPhongBan(String maPhongBan) throws Exception {
        if (maPhongBan.equals("")) {
            throw new Exception("Mã phòng ban không được rỗng");
        } else if (!maPhongBan.matches("^PB[1-9][0-9]{5}$")) {
            throw new Exception("Mã phòng ban phải theo định dạng PBxxxxxx với x là các kí tự số, x đầu tiền từ [1-9][0-9]");
        } else {
            this.maPhongBan = maPhongBan;
        }
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public int getSoLuongNhanVien() {
        return soLuongNhanVien;
    }

    public void setSoLuongNhanVien(int soLuongNhanVien) throws Exception {
        if (soLuongNhanVien < 0) {
            throw new Exception("Số lượng nhân viên không được < 0");
        } else {
            this.soLuongNhanVien = soLuongNhanVien;
        }
    }

    @Override
    public String toString() {
        return "PhongBan{" + "maPhongBan=" + maPhongBan + ", tenPhongBan=" + tenPhongBan + ", soLuongNhanVien=" + soLuongNhanVien + '}';
    }

}
