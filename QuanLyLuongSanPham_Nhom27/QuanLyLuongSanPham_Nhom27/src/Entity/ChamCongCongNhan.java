/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Student
 */

public class ChamCongCongNhan {

    private String maChamCong;
    private Date ngayChamCong;
    private int soLuongLam;
    private PhanCongCongNhan phanCong;
    private boolean diLam;
    private boolean phepNghi;

    public ChamCongCongNhan(String maChamCong, Date ngayChamCong, int soLuongLam, PhanCongCongNhan phanCong, boolean diLam, boolean phepNghi) {
        try {
            setMaChamCong(maChamCong);
            setNgayChamCong(ngayChamCong);
            setSoLuongLam(soLuongLam);
            setPhanCong(phanCong);
            setDiLam(diLam);
            setPhepNghi(phepNghi);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaChamCong() {
        return maChamCong;
    }

    private void setMaChamCong(String maChamCong) throws Exception {
        if (maChamCong.equals("")) {
            throw new Exception("Mã chấm công công nhân không được để trống!");
        } else if (!maChamCong.matches("^CCN[1-9][0-9]{5}$")) {
            throw new Exception("Mã chấm công nhân viên phải theo dạng CCNxxxxx với x là các kí tự số x đầu tiền từ [1-9] x sau từ [0-9]");
        } else {
            this.maChamCong = maChamCong;
        }
    }

    public Date getNgayChamCong() {
        return ngayChamCong;
    }

    public void setNgayChamCong(Date ngayChamCong) throws Exception {
       if (ngayChamCong.after(new Date())){
           throw new Exception("Ngày chấm công công nhân phải trước hoặc bằng ngày hiện tại");
       } else {
           this.ngayChamCong = ngayChamCong;
       }
    }

    public int getSoLuongLam() {
        return soLuongLam;
    }

    public void setSoLuongLam(int soLuongLam) throws Exception {
        if (soLuongLam < 0){
            throw new Exception("Số lượng làm không được < 0"); 
        } else{
            this.soLuongLam = soLuongLam;
        }
    }

    public PhanCongCongNhan getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCongCongNhan phanCong) {
        this.phanCong = phanCong;
    }

    public boolean isDiLam() {
        return diLam;
    }

    public void setDiLam(boolean diLam) {
        this.diLam = diLam;
    }

    public boolean isPhepNghi() {
        return phepNghi;
    }

    public void setPhepNghi(boolean phepNghi) {
        this.phepNghi = phepNghi;
    }

    @Override
    public String toString() {
        return "ChamCongCongNhan{" + "maChamCong=" + maChamCong + ", ngayChamCong=" + ngayChamCong + ", soLuongLam=" + soLuongLam + ", phanCong=" + phanCong + ", diLam=" + diLam + ", phepNghi=" + phepNghi + '}';
    }

}
