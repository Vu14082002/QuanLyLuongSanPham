/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Student
 */

public class ChamCongCongNhan {

    private String maChamCong;
    private Date ngayChamCong;
    private int soLuongLam;
    private PhanCongCongNhan phanCong;
    private String trangThaiDiLam;
    private String gioDiLam;

    public ChamCongCongNhan(String maChamCong, Date ngayChamCong, int soLuongLam, PhanCongCongNhan phanCong, String trangThaiDiLam, String gioDiLam) {
        try {
            setMaChamCong(maChamCong);
            setNgayChamCong(ngayChamCong);
            setSoLuongLam(soLuongLam);
            setPhanCong(phanCong);
            setTrangThaiDiLam(trangThaiDiLam);
            setGioDiLam(gioDiLam);
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
        if (ngayChamCong.after(new Date())) {
            throw new Exception("Ngày chấm công công nhân phải trước hoặc bằng ngày hiện tại");
        } else {
            this.ngayChamCong = ngayChamCong;
        }
    }

    public int getSoLuongLam() {
        return soLuongLam;
    }

    public void setSoLuongLam(int soLuongLam) throws Exception {
        if (soLuongLam < 0) {
            throw new Exception("Số lượng làm không được < 0");
        } else {
            this.soLuongLam = soLuongLam;
        }
    }

    public PhanCongCongNhan getPhanCong() {
        return phanCong;
    }

    public void setPhanCong(PhanCongCongNhan phanCong) {
        this.phanCong = phanCong;
    }

    private void setTrangThaiDiLam(String trangThaiDiLam) throws Exception {
        if (trangThaiDiLam.equalsIgnoreCase("Đi làm")
                || trangThaiDiLam.equalsIgnoreCase("Đi trễ")
                || trangThaiDiLam.equalsIgnoreCase("Nghỉ Không phép")
                || trangThaiDiLam.equalsIgnoreCase("Nghỉ có phép")) {
            this.trangThaiDiLam = trangThaiDiLam;
        } else {
            throw new Exception("Trạng thái đi làm phải là 1 trong 3: Đi làm, Đi trễ, Nghỉ");
        }
    }

    public String getTrangThaiDiLam() {
        return trangThaiDiLam;
    }

    public void setGioDiLam(String gioDiLam) {
        this.gioDiLam = gioDiLam;
    }

    public String getGioDiLam() {
        return gioDiLam;
    }

    @Override
    public String toString() {
        return "ChamCongCongNhan{" + "maChamCong=" + maChamCong + ", ngayChamCong=" + ngayChamCong + ", soLuongLam=" + soLuongLam + ", phanCong=" + phanCong + ", trangThaiDiLam=" + trangThaiDiLam + ", gioDiLam=" + gioDiLam + '}';
    }

}
