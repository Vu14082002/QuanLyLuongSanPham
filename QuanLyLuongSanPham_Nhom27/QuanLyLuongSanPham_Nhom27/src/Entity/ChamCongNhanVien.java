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
public class ChamCongNhanVien {

    private String maChamCong;
    private Date ngayChamCong;
    private String caLam;
    private boolean diLam;
    private boolean phepNghi;
    private NhanVien nhanVien;
    private NhanVien nguoiChamCong;

    public ChamCongNhanVien(String maChamCong, Date ngayChamCong, String caLam, boolean diLam, boolean phepNghi, NhanVien nhanVien, NhanVien nguoiChamCong) {
        try {
            setMaChamCong(maChamCong);
            setNgayChamCong(ngayChamCong);
            setCaLam(caLam);
            setDiLam(diLam);
            setPhepNghi(phepNghi);
            setNhanVien(nhanVien);
            setNguoiChamCong(nguoiChamCong);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getMaChamCong() {
        return maChamCong;
    }

    private void setMaChamCong(String maChamCong) throws Exception {
        if (maChamCong.equals("")) {
            throw new Exception("Mã chấm công nhân viên không được để trống");
        } else if (!maChamCong.matches("^CNV[1-9][0-9]{4}$")) {
            throw new Exception("Mã chấm công nhân viên phải theo dạng CNVxxxxx với x là các kí tự số x đầu tiền từ [1-9] x sau từ [0-9]");
        } else {
            this.maChamCong = maChamCong;
        }
    }

    public Date getNgayChamCong() {
        return ngayChamCong;
    }

    /* Lý do ngày chấm công là ngày hiện tại hoặc ngày trước vì hệ thống có thể gặp trục trặc ngày đó không
        thể chấm được thì ngày sau vẫn có thể chấm bù cho ngày đó
     */
    public void setNgayChamCong(Date ngayChamCong) throws Exception {
        if (ngayChamCong.after(new Date())) {
            throw new Exception("Ngày chấm công phải bằng hoặc trước ngày hiện tại");
        } else {
            this.ngayChamCong = ngayChamCong;
        }
    }

    public String getCaLam() {
        return caLam;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
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

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public NhanVien getNguoiChamCong() {
        return nguoiChamCong;
    }

    public void setNguoiChamCong(NhanVien nguoiChamCong) {
        this.nguoiChamCong = nguoiChamCong;
    }

    @Override
    public String toString() {
        return "ChamCongNhanVien{" + "maChamCong=" + maChamCong + ", ngayChamCong=" + ngayChamCong + ", caLam=" + caLam + ", diLam=" + diLam + ", phepNghi=" + phepNghi + ", nhanVien=" + nhanVien + ", nguoiChamCong=" + nguoiChamCong + '}';
    }

}
