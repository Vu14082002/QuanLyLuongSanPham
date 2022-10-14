/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

/**
 *
 * @author Acer
 */
public class ToNhom {

    private String maToNhom;
    private String tenToNhom;

    public ToNhom(String maToNhom, String tenToNhom) {
        try {
            setMaToNhom(maToNhom);
            setTenToNhom(tenToNhom);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public String getMaToNhom() {
        return maToNhom;
    }

    private void setMaToNhom(String maToNhom) throws Exception {
        if (maToNhom.equals("")) {
            throw new Exception("Mã tổ nhóm không được bỏ trống");
        } else if (!maToNhom.matches("^TN[1-9][0-9]{5}$")) {
            throw new Exception("Mã tổ nhóm gồm 8 kí tự dạng TNxxxxxx với x là các kí tự số, x đầu tiền từ 1-9, x sau từ 0-9");
        } else {
            this.maToNhom = maToNhom;
        }
    }

    public String getTenToNhom() {
        return tenToNhom;
    }

    public void setTenToNhom(String tenToNhom) throws Exception {
        if (tenToNhom.equals("")) {
            throw new Exception("Tên tổ nhóm không được bỏ trống");
        } else {
            this.tenToNhom = tenToNhom;
        }
    }

    @Override
    public String toString() {
        return String.format("Mã tổ nhóm: %s, Tên tổ nhóm: %s", maToNhom, tenToNhom);
    }

}
