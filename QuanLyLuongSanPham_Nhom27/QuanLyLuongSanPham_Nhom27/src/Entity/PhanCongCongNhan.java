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
public class PhanCongCongNhan {

    private String maPhanCong;
    private CongNhan congNhan;
    private CongDoan congDoan;
    private NhanVien nguoiPhanCong;
    private Date ngayPhanCong;
    private String caLam;

    public PhanCongCongNhan(String maPhanCong, CongNhan congNhan, CongDoan congDoan, NhanVien nguoiPhanCong, Date ngayPhanCong, String caLam) {
        try {
            setMaPhanCong(maPhanCong);
            setCongNhan(congNhan);
            setCongDoan(congDoan);
            setNguoiPhanCong(nguoiPhanCong);
            setNgayPhanCong(ngayPhanCong);
            setCaLam(caLam);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaPhanCong() {
        return maPhanCong;
    }

    private void setMaPhanCong(String maPhanCong) throws Exception {
        if (maPhanCong.equals("")) {
            throw new Exception("Mã phân công nhân viên không được để trống!");
        } else if (!maPhanCong.matches("^PC[1-9][0-9]{5}$")) {
            throw new Exception("Mã phân công công nhân viên phải theo dạng PCxxxxxx với x là các kí tự số x đầu tiền từ [1-9] x sau từ [0-9]");
        } else {
            this.maPhanCong = maPhanCong;
        }
    }

    public CongNhan getCongNhan() {
        return congNhan;
    }

    public void setCongNhan(CongNhan congNhan) {
        this.congNhan = congNhan;
    }

    public CongDoan getCongDoan() {
        return congDoan;
    }

    public void setCongDoan(CongDoan congDoan) {
        this.congDoan = congDoan;
    }

    public NhanVien getNguoiPhanCong() {
        return nguoiPhanCong;
    }

    public void setNguoiPhanCong(NhanVien nguoiPhanCong) {
        this.nguoiPhanCong = nguoiPhanCong;
    }

    public Date getNgayPhanCong() {
        return ngayPhanCong;
    }

    public void setNgayPhanCong(Date ngayPhanCong) throws Exception {
        if (ngayPhanCong.after(new Date())){
            throw new Exception("Ngày phân công phải là ngày hiện tại hoặc trước hiện tại!");
        } else {
            this.ngayPhanCong = ngayPhanCong;
        }
    }

    public String getCaLam() {
        return caLam;
    }

    public void setCaLam(String caLam) {
        this.caLam = caLam;
    }

    @Override
    public String toString() {
        return "PhanCongCongNhan{" + "maPhanCong=" + maPhanCong + ", congNhan=" + congNhan + ", congDoan=" + congDoan + ", nguoiPhanCong=" + nguoiPhanCong + ", ngayPhanCong=" + ngayPhanCong + ", caLam=" + caLam + '}';
    }

}
