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
public class ChamCongNhanVien {
    private NhanVien nhanVien;
    private Date ngayChamCong;
    private String caLam;
    private String trangThaiDiLam;
    private String gioDiLam;
    private NhanVien nguoiChamCong;

    public ChamCongNhanVien(NhanVien nhanVien, Date ngayChamCong, String caLam, String trangThaiDiLam, String gioDiLam, NhanVien nguoiChamCong) {
        try {
            
            setNhanVien(nhanVien);
            setNgayChamCong(ngayChamCong);
            setCaLam(caLam);
            setTrangThaiDiLam(trangThaiDiLam);
            setGioDiLam(gioDiLam);
            setNguoiChamCong(nguoiChamCong);
        } catch (Exception e) {
            e.getMessage();
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
        return "ChamCongNhanVien{" + "nhanVien=" + nhanVien + ", ngayChamCong=" + ngayChamCong + ", caLam=" + caLam + ", trangThaiDiLam=" + trangThaiDiLam + ", gioDiLam=" + gioDiLam + ", nguoiChamCong=" + nguoiChamCong + '}';
    }

    

}
