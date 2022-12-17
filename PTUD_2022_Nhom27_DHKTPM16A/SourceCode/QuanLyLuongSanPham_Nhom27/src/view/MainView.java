/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import CustomView.MenuItem;
import DAO.CongNhan_DAO;
import DAO.NhanVien_DAO;
import Entity.CongNhan;
import Entity.NhanVien;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
// 1500 - 250 anf 800-75

/**
 *
 * @author December
 */
public class MainView extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    // cong nhan
    private MenuItem capNhatCongNhan = null;
    private MenuItem chamCongCongNhan = null;
    private MenuItem tinhLuongCongNhan = null;
    private MenuItem timKiemCongNhan = null;
    // nhan vien
    private MenuItem capNhatNhanVien = null;
    private MenuItem chamCongNhanVien = null;
    private MenuItem tinhLuongNhanVien = null;
    private MenuItem timKiemNhanVien = null;
    private MenuItem phanCongCongNhan = null;
    /// san pham
    private MenuItem capNhatSanPham = null;
    private MenuItem phanCongDoanSanPham = null;
    private MenuItem timKiemSanPham = null;
    // he thong
    private MenuItem dangXuat = null;
    private MenuItem thongTinCaNhan = null;
    // thong ke
    private MenuItem thongKeNhanVien = null;
    private MenuItem thongKeCongNhan = null;
    private ImageIcon iconSubMenuNonSelect = null;
    private ImageIcon iconSubMenuSelect = null;

    private CongNhan_DAO congNhan_DAO;
    private NhanVien_DAO nhanVien_DAO;
    private NhanVien nhanVien;
    private CongNhan congNhan;
    private MenuItem menuTrangChu;
    private MenuItem meNuHopDong;
    private MenuItem menuPhongBan;
    private MenuItem menuToNhom;
    private MenuItem menuNhanVien;
    private MenuItem menuCongNhan;
    private MenuItem menuSanPham;
    private MenuItem menuThongKe;
    private MenuItem menuHeThong;
    private String lblTrangChu;
    private String lblPhongBan;
    private String lblToNhom;
    private String lblHopDong;
    private String lblSanPham;
    private String lblCapNhat;
    private String lblPhanCongDoanSanPham;
    private String lblTimKiem;
    private String lblNhanVien;
    private String lblChamCong;
    private String lblPhanCong;
    private String lblLuong;
    private String lblCongNhan;
    private String lblThongKe;
    private String lblHeThong;
    private String lblThongTinCaNhan;
    private String lblDangXuat;
    private String lblXacNhanDangXuat;
    //// dang nhao
    private String userName;
// Param
    private String fileName;
    private NhanVien nhanVienDangNhap;

    public MainView(String userName, String fileName) throws IOException {
        this.fileName = fileName;
        this.userName = userName;
        System.out.println(fileName);
        congNhan_DAO = new CongNhan_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        nhanVienDangNhap = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(userName);
        initComponents();
        caiDatNgonNguChoView(fileName);
        execute();
        try {
            ConnectionDB.ConnectDB.getInstance().connect();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        String loai = userName.substring(0, 2);
        if (loai.equals("CN")) {
            congNhan = congNhan_DAO.layMotCongNhanTheoMa(userName);

        } else if (loai.equals("NV")) {
            nhanVien = nhanVien_DAO.layMotNhanVienTheoMaNhanVien(userName);
        }
        String hello = "Hello, ";

        if (congNhan != null) {
            hello += congNhan.getHoTen();
        }
        if (nhanVien != null) {
            hello += nhanVien.getHoTen();
        }
        lblXinChao.setText(hello);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }

    private void execute() throws IOException {
        ImageIcon iconHomePage = new ImageIcon(getClass().getResource("/image/icon/homepage.png"));
        ImageIcon iconEmployee = new ImageIcon(getClass().getResource("/image/icon/employee.png"));
        ImageIcon iconWorker = new ImageIcon(getClass().getResource("/image/icon/worker.png"));
        ImageIcon iconDepartment = new ImageIcon(getClass().getResource("/image/icon/department.png"));
        ImageIcon iconGroup = new ImageIcon(getClass().getResource("/image/icon/toNhom.png"));
        ImageIcon iconHeThong = new ImageIcon(getClass().getResource("/image/icon/system.png"));
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/image/icon/thongke.png"));
        ImageIcon iconContract = new ImageIcon(getClass().getResource("/image/icon/contract.png"));
        iconSubMenuNonSelect = new ImageIcon(getClass().getResource("/image/icon/moon.png"));
        iconSubMenuSelect = new ImageIcon(getClass().getResource("/image/icon/moonSelect.png"));
        ImageIcon iconProduct = new ImageIcon(getClass().getResource("/image/icon/sanpham.png"));

        // create subMenu
        //sub menuCongNhan
        capNhatCongNhan = new MenuItem(iconSubMenuNonSelect, lblCapNhat, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new CapNhatCongNhanView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(capNhatCongNhan);
        }));
        chamCongCongNhan = new MenuItem(iconSubMenuNonSelect, lblChamCong, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ChamCongCongNhanView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(chamCongCongNhan);
        }));
        timKiemCongNhan = new MenuItem(iconSubMenuNonSelect, lblTimKiem, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new TimKiemCongNhanView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(timKiemCongNhan);
        }));
        tinhLuongCongNhan = new MenuItem(iconSubMenuNonSelect, lblLuong, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new LuongCongNhanView(fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((tinhLuongCongNhan));
        }));

        // submenu nhan vien
        capNhatNhanVien = new MenuItem(iconSubMenuNonSelect, lblCapNhat, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new CapNhatNhanVienView(fileName), BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                macDinh((capNhatNhanVien));
            }
        });
        chamCongNhanVien = new MenuItem(iconSubMenuNonSelect, lblChamCong, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ChamCongNhanVienView(nhanVienDangNhap, fileName), BorderLayout.CENTER);
            } catch (Exception ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((chamCongNhanVien));
        });
        tinhLuongNhanVien = new MenuItem(iconSubMenuNonSelect, lblLuong, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new LuongNhanVienView(fileName), BorderLayout.CENTER);
                } catch (Exception ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                macDinh(tinhLuongNhanVien);
            }

        });
        timKiemNhanVien = new MenuItem(iconSubMenuNonSelect, lblTimKiem, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new TimKiemNhanVienView(fileName), BorderLayout.CENTER);
                } catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                macDinh(timKiemNhanVien);
            }

        });
        phanCongCongNhan = new MenuItem(iconSubMenuNonSelect, lblPhanCong, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new PhanCongCongViecView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(phanCongCongNhan);
        }));

        // San pham
        capNhatSanPham = new MenuItem(iconSubMenuNonSelect, lblCapNhat, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pnBody.removeAll();
                try {
                    pnBody.add(new SanPhamView(fileName), BorderLayout.CENTER);
                } catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
                pnBody.repaint();
                pnBody.revalidate();
                macDinh(capNhatSanPham);
            }
        });
        phanCongDoanSanPham = new MenuItem(iconSubMenuNonSelect, lblPhanCongDoanSanPham, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new PhanCongDoanView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(phanCongDoanSanPham);
        }));
        timKiemSanPham = new MenuItem(iconSubMenuNonSelect, lblTimKiem, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new TimKiemSanPhamView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh(timKiemSanPham);
        }));
        // he thong
        thongTinCaNhan = new MenuItem(iconSubMenuNonSelect, lblThongTinCaNhan, ((e) -> {
            pnBody.removeAll();
            String username = "";
            if (congNhan != null) {
                username = congNhan.getMaCongNhan();

            } else if (nhanVien != null) {
                username = nhanVien.getMaNhanVien();
            }
            try {
                pnBody.add(new QuanLyThongTinCaNhan(this.fileName,username), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((thongTinCaNhan));
        }));
        dangXuat = new MenuItem(iconSubMenuNonSelect, lblDangXuat, ((e) -> {
            macDinh((dangXuat));
            if (JOptionPane.showConfirmDialog(null, lblXacNhanDangXuat, null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                this.setVisible(false);
                new LoginView().setVisible(true);
            } else {
                dangXuat.setIcon(iconSubMenuNonSelect);
            }
        }));
        thongKeNhanVien = new MenuItem(iconSubMenuNonSelect, lblNhanVien, ((e) -> {
            try {
                pnBody.removeAll();
                pnBody.add(new ThongKeNhanVienView(fileName), BorderLayout.CENTER);
                pnBody.repaint();
                pnBody.revalidate();
                macDinh((thongKeNhanVien));
            } catch (Exception ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }));
        thongKeCongNhan = new MenuItem(iconSubMenuNonSelect, lblCongNhan, ((e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ThongKeCongNhanView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            macDinh((thongKeCongNhan));
        }));
        // thong ke sub menu
        // add trang chu

        // menu
        menuTrangChu = new MenuItem(iconHomePage, lblTrangChu, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new TrangChuView(nhanVienDangNhap, fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuTrangChu);

        });
        meNuHopDong = new MenuItem(iconContract, lblHopDong, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new HopDongView(nhanVienDangNhap, fileName), BorderLayout.CENTER);
            } catch (ParseException | IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(meNuHopDong);
        });
        menuPhongBan = new MenuItem(iconDepartment, lblPhongBan, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new PhongBanView(nhanVienDangNhap, fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuPhongBan);
        });
        menuToNhom = new MenuItem(iconGroup, lblToNhom, (ActionEvent e) -> {
            pnBody.removeAll();
            try {
                pnBody.add(new ToNhomView(fileName), BorderLayout.CENTER);
            } catch (IOException ex) {
                Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
            }
            pnBody.repaint();
            pnBody.revalidate();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuToNhom);
        });
        menuNhanVien = new MenuItem(iconEmployee, lblNhanVien, (ActionEvent e) -> {
            resetSelect();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuNhanVien);
        }, capNhatNhanVien, chamCongNhanVien, phanCongCongNhan, tinhLuongNhanVien, timKiemNhanVien);
        menuCongNhan = new MenuItem(iconWorker, lblCongNhan, (ActionEvent e) -> {
            resetSelect();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuCongNhan);
        }, capNhatCongNhan, chamCongCongNhan, tinhLuongCongNhan, timKiemCongNhan);
        menuSanPham = new MenuItem(iconProduct, lblSanPham, (ActionEvent e) -> {
            resetSelect();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuSanPham);
        }, capNhatSanPham, phanCongDoanSanPham, timKiemSanPham);
        menuThongKe = new MenuItem(iconThongKe, lblThongKe, (ActionEvent e) -> {
            resetSelect();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuThongKe);
        }, thongKeNhanVien, thongKeCongNhan);
        menuHeThong = new MenuItem(iconHeThong, lblHeThong, (ActionEvent e) -> {
            resetSelect();
            setNonSelectMenu(menuTrangChu, meNuHopDong, menuPhongBan, menuToNhom, menuNhanVien, menuCongNhan, menuSanPham, menuThongKe, menuHeThong);
            setSelectMenu(menuHeThong);
        }, thongTinCaNhan, dangXuat);
        if (this.userName.contains("NV")) {
            pnBody.removeAll();
            pnBody.add(new TrangChuView(nhanVienDangNhap, fileName), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            setSelectMenu(menuTrangChu);
            NhanVien_DAO nhanVienDao = new NhanVien_DAO();
            NhanVien nhanVien = nhanVienDao.layMotNhanVienTheoMaNhanVien(userName);
            if (nhanVien.getChucVu().equalsIgnoreCase("Quản lý")) {
                addMenu(menuTrangChu, menuPhongBan, menuToNhom, meNuHopDong, menuSanPham, menuNhanVien, menuCongNhan, menuThongKe, menuHeThong);
            } else {
                addMenu(menuTrangChu, menuSanPham, menuHeThong);
            }
        } else {
            iconSubMenuNonSelect = new ImageIcon(getClass().getResource("/image/icon/worker.png"));
            thongTinCaNhan = new MenuItem(iconSubMenuNonSelect, lblThongTinCaNhan, ((e) -> {
                try {
                    pnBody.removeAll();
                    String username = "";
                    if (congNhan != null) {
                        username = congNhan.getMaCongNhan();
                    } else if (nhanVien != null) {
                        username = nhanVien.getMaNhanVien();
                    }
                    pnBody.add(new QuanLyThongTinCaNhan(this.fileName,username), BorderLayout.CENTER);
                    pnBody.repaint();
                    pnBody.revalidate();
                } catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }));
            iconSubMenuNonSelect = new ImageIcon(getClass().getResource("/image/icon/logout.png"));
            dangXuat = new MenuItem(iconSubMenuNonSelect, lblDangXuat, ((e) -> {
                if (JOptionPane.showConfirmDialog(null, lblXacNhanDangXuat, null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                    this.setVisible(false);
                    new LoginView().setVisible(true);
                }
            }));
            pnBody.removeAll();
            pnBody.add(new QuanLyThongTinCaNhan(this.fileName,userName), BorderLayout.CENTER);
            pnBody.repaint();
            pnBody.revalidate();
            setSelectMenu(thongTinCaNhan);
            addMenu(thongTinCaNhan, dangXuat);
        }
    }

    public void setSelectMenu(JPanel pnelSelect) {
        pnelSelect.setBackground(new Color(0, 206, 201));
        for (Component jc : pnelSelect.getComponents()) {
            if (jc instanceof JLabel) {
                JLabel label = (JLabel) jc;
                label.setForeground(new Color(255, 255, 255));
                label.setFont(new Font("Segoe UI", Font.BOLD, 15));
            }
        }
    }

    public void setNonSelectMenu(JPanel... jPanels) {
        for (JPanel jPanel : jPanels) {
            jPanel.setBackground(new Color(255, 255, 255));
            for (Component jc : jPanel.getComponents()) {
                if (jc instanceof JLabel) {
                    JLabel label = (JLabel) jc;
                    label.setForeground(new Color(12, 12, 12));
                    label.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                }
            }
        }

    }
    public void caiDatNgonNguChoView(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        Properties prop = new Properties();
        prop.load(fis);
        lblTieuDe.setText(prop.getProperty("Main_lblTieuDe"));
        lblTrangChu = prop.getProperty("Main_lblTrangChu");
        lblPhongBan = prop.getProperty("Main_lblPhongBan");
        lblToNhom = prop.getProperty("Main_lblToNhom");
        lblHopDong = prop.getProperty("Main_lblHopDong");
        lblSanPham = prop.getProperty("Main_lblSanPham");
        lblCapNhat = prop.getProperty("Main_lblCapNhat");
        lblPhanCongDoanSanPham = prop.getProperty("Main_lblPhanCongDoanSanPham");
        lblTimKiem = prop.getProperty("Main_lblTimKiem");
        lblNhanVien = prop.getProperty("Main_lblNhanVien");
        lblChamCong = prop.getProperty("Main_lblChamCong");
        lblPhanCong = prop.getProperty("Main_lblPhanCongViec");
        lblLuong = prop.getProperty("Main_lblLuong");
        lblCongNhan = prop.getProperty("Main_lblCongNhan");
        lblThongKe = prop.getProperty("Main_lblThongKe");
        lblHeThong = prop.getProperty("Main_lblHeThong");
        lblThongTinCaNhan = prop.getProperty("Main_lblThongTinCaNhan");
        lblDangXuat = prop.getProperty("Main_lblDangXuat");
        lblXacNhanDangXuat = prop.getProperty("Main_lblXacNhanDangXuat");
    }

    public void resetSelect() {
        capNhatCongNhan.setIcon(iconSubMenuNonSelect);
        chamCongCongNhan.setIcon(iconSubMenuNonSelect);
        tinhLuongCongNhan.setIcon(iconSubMenuNonSelect);
        timKiemCongNhan.setIcon(iconSubMenuNonSelect);
        // nhan vien
        capNhatNhanVien.setIcon(iconSubMenuNonSelect);
        chamCongNhanVien.setIcon(iconSubMenuNonSelect);
        tinhLuongNhanVien.setIcon(iconSubMenuNonSelect);
        timKiemNhanVien.setIcon(iconSubMenuNonSelect);
        phanCongCongNhan.setIcon(iconSubMenuNonSelect);
        /// san pham
        capNhatSanPham.setIcon(iconSubMenuNonSelect);
        phanCongDoanSanPham.setIcon(iconSubMenuNonSelect);
        timKiemSanPham.setIcon(iconSubMenuNonSelect);
        // he thong
        dangXuat.setIcon(iconSubMenuNonSelect);
        thongTinCaNhan.setIcon(iconSubMenuNonSelect);
        // thong ke
        thongKeNhanVien.setIcon(iconSubMenuNonSelect);
        thongKeCongNhan.setIcon(iconSubMenuNonSelect);
    }

    public void macDinh(MenuItem menu) {
        resetSelect();
        menu.setIcon(iconSubMenuSelect);
    }

    private void addMenu(MenuItem... menu) {
        for (MenuItem menu1 : menu) {
            menus.add(menu1);
            ArrayList<MenuItem> subMenu = menu1.getSubMenu();
            subMenu.forEach(menuItem -> {
                addMenu(menuItem);
            });
        }
        menus.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnHeader = new javax.swing.JPanel();
        lblTieuDe = new javax.swing.JLabel();
        lblXinChao = new javax.swing.JLabel();
        pnMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        pnBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnHeader.setBackground(new java.awt.Color(41, 128, 185));
        pnHeader.setForeground(new java.awt.Color(41, 128, 185));
        pnHeader.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        pnHeader.setPreferredSize(new java.awt.Dimension(1500, 75));
        pnHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTieuDe.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        lblTieuDe.setForeground(new java.awt.Color(255, 255, 255));
        lblTieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTieuDe.setText("QUẢN LÝ LƯƠNG CÔNG TY THVT_SHOES");
        pnHeader.add(lblTieuDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 1250, 75));

        lblXinChao.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblXinChao.setForeground(new java.awt.Color(255, 255, 255));
        lblXinChao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblXinChao.setText("Hello, Admin");
        pnHeader.add(lblXinChao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, 244, 70));

        getContentPane().add(pnHeader, java.awt.BorderLayout.PAGE_START);

        pnMenu.setBackground(new java.awt.Color(255, 255, 255));
        pnMenu.setPreferredSize(new java.awt.Dimension(250, 725));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(60, 99, 130));
        menus.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout pnMenuLayout = new javax.swing.GroupLayout(pnMenu);
        pnMenu.setLayout(pnMenuLayout);
        pnMenuLayout.setHorizontalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        pnMenuLayout.setVerticalGroup(
            pnMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
        );

        getContentPane().add(pnMenu, java.awt.BorderLayout.LINE_START);

        pnBody.setBackground(new java.awt.Color(255, 51, 51));
        pnBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(pnBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1516, 839));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainView("NV100001").setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTieuDe;
    private javax.swing.JLabel lblXinChao;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel pnBody;
    private javax.swing.JPanel pnHeader;
    private javax.swing.JPanel pnMenu;
    // End of variables declaration//GEN-END:variables
}