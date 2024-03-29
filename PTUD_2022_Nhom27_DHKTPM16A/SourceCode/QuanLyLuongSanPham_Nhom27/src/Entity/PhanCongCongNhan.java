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
    private ToNhom toNhom;
    private int soLuongCanLam;

    public PhanCongCongNhan(String maPhanCong, CongNhan congNhan, CongDoan congDoan, NhanVien nguoiPhanCong, Date ngayPhanCong, int soLuongCanLam, ToNhom toNhom) {
        try {
            setMaPhanCong(maPhanCong);
            setCongNhan(congNhan);
            setCongDoan(congDoan);
            setNguoiPhanCong(nguoiPhanCong);
            setNgayPhanCong(ngayPhanCong);
            setToNhom(toNhom);
            setSoLuongCanLam(soLuongCanLam);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaPhanCong() {
        return maPhanCong;
    }

    public int getSoLuongCanLam() {
        return soLuongCanLam;
    }

    public void setSoLuongCanLam(int soLuongCanLam) throws Exception {
        if (soLuongCanLam <= 0) {
            throw new Exception("So luong can lamm > 0");
        } else {
            this.soLuongCanLam = soLuongCanLam;
        }
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
        if (ngayPhanCong.after(new Date())) {
            throw new Exception("Ngày phân công phải là ngày hiện tại hoặc trước hiện tại!");
        } else {
            this.ngayPhanCong = ngayPhanCong;
        }
    }

    public ToNhom getToNhom() {
        return toNhom;
    }

    public void setToNhom(ToNhom toNhom) {
        this.toNhom = toNhom;
    }

    @Override
    public String toString() {
        return "PhanCongCongNhan{" + "maPhanCong=" + maPhanCong + ", congNhan=" + congNhan + ", congDoan=" + congDoan + ", nguoiPhanCong=" + nguoiPhanCong + ", ngayPhanCong=" + ngayPhanCong + ", toNhom=" + toNhom + '}';
    }

}