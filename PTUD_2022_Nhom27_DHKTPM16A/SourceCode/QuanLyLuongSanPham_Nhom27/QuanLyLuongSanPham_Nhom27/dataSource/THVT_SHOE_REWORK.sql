--drop database THTV_SHOES

-- Tạo database 
CREATE DATABASE THTV_SHOES
GO
USE THTV_SHOES
GO 
CREATE TABLE ToNhom
(
	maToNhom char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenTo nvarchar(50) not null,
	soLuongCongNhan int default 0,
	constraint CHK_ToNhom_maToNhom_dinhDang check (maToNhom like 'TN[1-9][0-9][0-9][0-9][0-9][0-9]') -- Gồm 8 kí tự bắt đầu bằng TN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)
GO
INSERT TONHOM 
VALUES ('TN100001', N'Tổ 1', 0)
GO
INSERT INTO ToNhom(maToNhom, tenTo, soLuongCongNhan) VALUES ('TN100002', N'Tổ 2', 0)

GO
CREATE TABLE CongNhan
(
	maCongNhan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	hoTen nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	ngaySinh date not null,
	maCCCD char(12) not null,
	soDienThoai char(10) not null,
	email varchar(100) not null,
	matKhau varchar(30) not null,
	gioiTinh bit,
	anhDaiDien varchar(100),
	diaChi nvarchar(100),
	ngayVaoLam date,
	toNhom char(8) COLLATE SQL_Latin1_General_CP1_CS_AS references ToNhom(maToNhom) ON DELETE CASCADE,
	constraint CHK_CongNhan_maCongNhan_dinhDang check (maCongNhan like 'CN[1-9][0-9][0-9][0-9][0-9][0-9]'),-- Gồm 8 kí tự bắt đầu bằng CN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_CongNhan_tenCongNhan_hienNhien check (hoTen like '%[^A-Za-z]%'), -- Họ tên chỉ chứa kí tự chữ 
	constraint CHK_CongNhan_ngaySinh_hienNhien check (ngaySinh < getdate()), -- ngày sinh phải sau ngày hiện tại
	constraint CHK_CongNhan_ngaySinh_tu18Tuoi check ((DATEDIFF(DD, ngaySinh, getDate()) / 365.25) >= 18), -- tuổi phải từ >= 18 trở lên
	constraint CHK_CongNhan_cccd_du12KiTu check (len(maCCCD) = 12), -- ma cccd đúng 12 kí tự
	constraint CHK_CongNhan_cccd_tatCaLaSo  check (maCCCD like '%[0-9]%'), -- mã cccd phải là kí tự số
	constraint CHK_CongNhan_soDienThoai_hienNhien check (len(soDienThoai) = 10), -- số điện thoại phải 10 chữ só
	constraint CHK_CongNhan_soDienThoai_tatCaLaSo check (soDienThoai like '%[0-9]%'), -- số điện thoại chỉ chứa kí tự số
	constraint CHK_CongNhan_soDienThoai_batDauLa0 check ( (substring(soDienThoai, 1, 1)) like '0'), -- số điện thoại bắt đầu bằng 0
	constraint CHK_CongNhan_matKhau_itNhat6KiTu check (len(matKhau) >= 6), -- mật khẩu phải từ 6 kí tự trở lên
	constraint CHK_CongNhan_ngayVoaLam_sauHienTai check (ngayVaoLam <= getDate()) -- ngày vào làm phải trước hoặc bằng ngày hiện tại
)
GO
 CREATE TRIGGER capNhatSoLuongCongNhan on CongNhan
 after insert, delete, update
 as
	begin
		declare @maToNhom char(8), @soLuong int = 0
		if exists (select * from inserted)
		begin
			select @maToNhom = toNhom from inserted
			select @soLuong = count(maCongNhan) from ToNhom TN join CongNhan CN on TN.maToNhom = CN.toNhom where TN.maToNhom = @maToNhom group by maToNhom
		end
		if exists (select * from deleted)
		begin
			select @maToNhom = toNhom from deleted
			select @soLuong = count(maCongNhan) from ToNhom TN join CongNhan CN on TN.maToNhom = CN.toNhom where TN.maToNhom = @maToNhom group by maToNhom
		end
		update ToNhom
		set soLuongCongNhan = @soLuong
		where maToNhom = @maToNhom
	end
GO
INSERT CongNhan
VALUES ('CN123123', N'Nguyễn Văn Hiếu', '2000-12-10', '111122223333', '0975654628', 'hieurio12@gmail.com', '123456', 1, 'anh1.png', N'Nghệ An', GETDATE(), 'TN100001')
GO
CREATE table BangLuongCongNhan 
(
	maBangLuong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maCongNhan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references CongNhan(maCongNhan) ON DELETE CASCADE,
	ngayTinh date not null,
	soLuongSanPhamLam int not null,
	soNgayDiLam int not null,
	soNgayNghi int not null,
	soPhepNghi int not null,
	tongLuong money,
	donViTien nvarchar(50),
	constraint CHK_BangLuongCongNhan_maBangLuong_dinhDang check (maBangLuong like 'LC[1-9][0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng CN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_BangLuongCongNhan_soLuongSanPhamLam_hienNhien check (soLuongSanPhamLam >= 0), -- số lượng sản phẩm làm được phải >= 0
	constraint CHK_BangLuongCongNhan_soNgayDiLam_hienNhien check (soNgayDiLam >= 0), -- số ngày đi làm phải >= 0
	constraint CHK_BangLuongCongNhan_soNgayNghi_hienNhien check (soNgayNghi >= 0), -- số ngày nghỉ phải >= 0
	constraint CHK_BangLuongCongNhan_soPhepNghi_hienNhien check (soPhepNghi <= soNgayNghi), -- số  phép phải <= số ngày nghỉ
	constraint CHK_BangLuongCongNhan_tongLuong_hienNhien check (tongLuong >= 0), -- tổng lương phải >= 0
	constraint CHK_BangLuongCongNhan_donViTien_hienNhien check (donViTien in ('VND', 'vnd', 'USD', 'usd')) -- chỉ chấp nhận dạng tiền usd, vnd
)
GO
INSERT BangLuongCongNhan
VALUES ('LC123123', 'CN123123', GETDATE(), 12312, 23, 5, 4, 1231231, 'vnd')
GO
CREATE TABLE PhongBan
(
	maPhongBan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenPhongBan nvarchar(50) not null,
	soLuongNhanVien int default 0,
	constraint CHK_PhongBan_maPhongBan_dinhDang check (maPhongBan like 'PB[1-9][0-9][0-9][0-9][0-9][0-9]') -- Gồm 8 kí tự bắt đầu bằng PB 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)
GO
INSERT PhongBan
VALUES ('PB100001', N'Phòng quản lý', 0), ('PB100002', N'Phòng kế toán', 0)

GO
CREATE TABLE NhanVien
(
	maNhanVien char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	hoTen nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	ngaySinh date not null,
	maCCCD char(12) not null,
	soDienThoai char(10) not null,
	email varchar(100) not null,
	matKhau varchar(30) not null,
	chucVu nvarchar(50) not null,
	ngayVaoLam date not null,
	luongThoaThuan money,
	gioiTinh bit,
	anhDaiDien varchar(100),
	diaChi nvarchar(100),
	maPhongBan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references PhongBan(maPhongBan),
	constraint CHK_NhanVien_maNhanVien_dinhDang check (maNhanVien like 'NV[1-9][0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng NV 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_NhanVien_tenNhanVien_hienNhien check (hoTen like '%[^A-Za-z]%'), -- họ tên chỉ chứa kí tự chữ
	constraint CHK_NhanVien_ngaySinh_sauHienTai check (ngaySinh < getdate()), -- ngày sinh phải trước ngày hiện tại
	constraint CHK_NhanVien_ngaySinh_tu18Tuoi check ((DATEDIFF(DD, ngaySinh, getDate()) / 365.25) >= 18), -- phải từ >= 18 tuổi trở lên
	constraint CHK_NhanVien_maCCCD_hienNhien check (len(maCCCD) = 12), -- mã cccd phải đúng 12 kí tự
	constraint CHK_NhanVien_maCCCD_toanSo check (maCCCD like '%[0-9]%'), -- các kí tự của cccd phải là số
	constraint CHK_NhanVien_soDienThoai_hienNhien check (len(soDienThoai) = 10), -- số điện thoại phải đúng 10 kí tự
	constraint CHK_NhanVien_soDienThoai_tatCaLaSo check (soDienThoai like '%[0-9]%'), -- số điện thoại chỉ chứa kí tự số
	constraint CHK_NhanVien_soDienThoai_batDauLa0 check ( (substring(soDienThoai, 1, 1)) like '0'), -- số bắt đầu bằng 0
	constraint CHK_NhanVien_matKhau_itNhat6KiTu check (len(matKhau) >= 6), -- mật khẩu >= 6 kí tự
	constraint CHK_NhanVien_ngayVaoLam_sauHienTai check (ngayVaoLam <= getDate()), -- ngày vào làm phải trước hoặc bằng ngày hiện tại
	constraint CHK_NhanVien_luongThoaThuan_hienNhien check (luongThoaThuan >= 0) -- lương thảo thuận phải >= 0
)
GO
CREATE TRIGGER capNhatSoLuongKhiInsertNV on NhanVien
after INSERT, DELETE, UPDATE
as
	begin
		declare @maPhongBan char(8), @soLuong int = 0
		if exists (select * from inserted)
		begin
			select @maPhongBan = maPhongBan from inserted
			select @soLuong =  count(maNhanVien) from PhongBan PB join NhanVien NV on PB.maPhongBan = NV.maPhongBan where PB.maPhongBan = @maPhongBan group by PB.maPhongBan
		end
		if exists (select * from deleted)
		begin
			select @maPhongBan = maPhongBan from deleted
			select @soLuong =  count(maNhanVien) from PhongBan PB join NhanVien NV on PB.maPhongBan = NV.maPhongBan where PB.maPhongBan = @maPhongBan group by PB.maPhongBan
		end
		
		update PhongBan
		set soLuongNhanVien = @soLuong
		where maPhongBan = @maPhongBan
	end

GO
INSERT NhanVien 
values ('NV123123', N'Nguyễn Văn Vũ', '2002-02-02', '222233334444', '0975123123', 'ngvanvu@gmail.com', '123456', N'Quản lý', GETDATE(), 4000000, 1, 'anh2.png', N'Phú Yên', 'PB100001')
GO
INSERT NhanVien 
values ('NV123456', N'Nguyễn Văn Toản', '2002-03-02', '222233334444', '0975123123', 'ngvanvu@gmail.com', '123456', N'Quản lý', GETDATE(), 4000000, 1, 'anh2.png', N'Phú Yên', 'PB100002')
GO
CREATE TABLE BangLuongNhanVien
(
	maBangLuong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maNhanVien char(8) COLLATE SQL_Latin1_General_CP1_CS_AS  not null references NhanVien(maNhanVien) ON DELETE CASCADE,
	soNgayDiLam int not null,
	soNgayNghi int not null,
	soPhepNghi int not null,
	ngayTinh date not null,
	tongTien money,
	donViTien nvarchar(50),
	constraint CHK_BangLuongNhanVien_maBangLuong_dinhDang check (maBangLuong like 'LN[1-9][0-9][0-9][0-9][0-9][0-9]'),  -- Gồm 8 kí tự bắt đầu bằng LN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_BangLuongNhanVien_soNgayDiLam_hienNhien check (soNgayDiLam >= 0), -- só ngày đi làm >= 0
	constraint CHK_BangLuongNhanVien_soNgayNghi_hienNhien check (soNgayNghi >= 0), -- số ngày nghỉ >= 0
	constraint CHK_BangLuongNhanVien_soPhepNghi_hienNhien check (soPhepNghi <= soNgayNghi), -- số phép nghỉ phải <= số ngày nghỉ
	constraint CHK_BangLuongNhanVien_tongLuong_hienNhien check (tongTien >= 0), -- tổng tiền phải >= 0
	constraint CHK_BangLuongNhanVien_donViTien_hienNhien check (donViTien in ('VND', 'vnd', 'USD', 'usd')) -- chỉ chấp nhận dạng tiền usd, vnd
)
GO
INSERT BangLuongNhanVien
values ('LN123123', 'NV123123', 24, 5, 3, GETDATE(), 12312, 'VND')
GO
CREATE TABLE ChamCongNhanVien
(
	maChamCong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	caLam nvarchar(50),
	trangThaiDiLam nvarchar(30),
	gioDiLam nvarchar(30),
	maNhanVien char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references NhanVien(maNhanVien),
	maNguoiCham char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references NhanVien(maNhanVien),
	ngayChamCong date not null,
	constraint CHK_ChamCongNhanVien_maChamCong_dinhDang check (maChamCong like 'CNV[0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng CNV 5 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)
INSERT ChamCongNhanVien
VALUES ('CNV12312', 'CN', N'Đi Trễ', '8h30', 'NV123123', 'NV123456', GETDATE())
GO
CREATE TABLE SanPham
(
	maSanPham char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenSanPham nvarchar(50)  COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	soLuongSanPham int not null, 
	mauSac nvarchar(50) not null,
	chatLieu nvarchar(50) not null,
	kichThuoc int,
	anhSanPham varchar(50),
	soLuongCongDoan int default 0,
	constraint CHK_SanPham_maSanPham_dinhDang check (maSanPham like 'SP[1-9][0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng SP 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_SanPham_soLuongSanPham check (soLuongSanPham > 0), -- số lượng sản phẩm >= 0
	constraint CHK_SanPham_kichThuoc check (kichThuoc >= 0) -- kích thước >= 0
)
GO
INSERT SanPham
VALUES ('SP123123', N'Giay ISNA Nam', 1000, N'Đỏ', 'Cotton', 47, 'anhsanpham1.png', 0)
GO
CREATE TABLE CongDoan
(
	maCongDoan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenCongDoan nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	soLuongCan int not null,
	tinhTrang nvarchar(50),
	thoiHan date not null,
	maSanPham char(8) COLLATE SQL_Latin1_General_CP1_CS_AS  not null references SanPham(maSanPham) ON DELETE CASCADE,
	tienLuong money,
	constraint CHK_CongDoan_maCongDoan_dinhDang check (maCongDoan like 'CD[1-9][0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng CD 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_CongDoan_soLuongCan_hienNhien check (soLuongCan >= 0), -- số lượng cần phải >= 0
	constraint CHK_CongDoan_thoiHan_hienNhien check (thoiHan > GETDATE()), -- thời hạn phải sau ngày hiện tại
	constraint CHK_CongDoan_tienLuong_hienNhien check (tienLuong >= 0) -- tiền lương phải >= 0
)
GO
CREATE TRIGGER capNhatSoLuongCongDoan on CongDoan
after insert, delete, update
as
	begin
		declare @maSanPham char(8), @soLuongCongDoan int = 0
		if exists (select * from inserted)
		begin
			select @maSanPham = maSanPham from inserted
			select @soLuongCongDoan = count(maCongDoan) from  SanPham SP join CongDoan CD on SP.maSanPham = CD.maSanPham where SP.maSanPham = @maSanPham group by SP.maSanPham
		end
		else 
		begin
			select @maSanPham = maSanPham from deleted
			select @soLuongCongDoan = count(maCongDoan) from  SanPham SP join CongDoan CD on SP.maSanPham = CD.maSanPham where SP.maSanPham = @maSanPham group by SP.maSanPham
		end
		update SanPham
		set soLuongCongDoan = @soLuongCongDoan
		where maSanPham = @maSanPham
	end
GO
INSERT CongDoan
VALUES ('CD123123', N'May', 114, N'Chưa hoàn thành', '2022-12-12', 'SP123123', 1231)
GO
INSERT CongDoan
VALUES ('CD123456', N'Đan', 115, N'Chưa hoàn thành', '2022-12-12', 'SP123123', 1231)
GO
CREATE TABLE PhanCongCongNhan
(
	maPhanCong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maCongNhan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references CongNhan(maCongNhan) ON DELETE CASCADE,
	maNguoiPhanCong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references NhanVien(maNhanVien) ON DELETE CASCADE,
	maCongDoan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references CongDoan(maCongDoan) ON DELETE CASCADE,
	ngayPhanCong date,
	caLam nvarchar(50),
	constraint CHK_PhanCongCongNhan_maPhanCong check (maPhanCong like 'PC[1-9][0-9][0-9][0-9][0-9][0-9]') -- Gồm 8 kí tự bắt đầu bằng PC 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)

GO
INSERT PhanCongCongNhan
VALUES ('PC123123', 'CN123123', 'NV123123', 'CD123123' ,GETDATE(), 'CN')
GO
CREATE TABLE ChamCongCongNhan(
	maChamCong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	ngayChamCong date not null,
	soLuongLam int not null,
	maPhanCong char(8)  COLLATE SQL_Latin1_General_CP1_CS_AS not null references PhanCongCongNhan(maPhanCong) ON DELETE CASCADE,
	trangThaiDiLam nvarchar(50),
	gioDiLam nvarchar(50),
	constraint CHK_ChamCongCongNhan_maChamCong_dinhDang check (maChamCong like 'CCN[0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng CCN 5 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_ChamCongCongNhan_soLuongLam_hienNhien check (soLuongLam >= 0) -- số lượng làm phải >= 0
)
GO
INSERT ChamCongCongNhan
VALUES ('CCN12312', GETDATE(), 123, 'PC123123', 'Đi Làm',  null)
GO

CREATE TRIGGER DELETE_NHANVIEN ON NhanVien
instead of DELETE
as
 begin
	 SET NOCOUNT ON;
	 DELETE ChamCongNhanVien WHERE maNhanVien in (SELECT maNhanVien from deleted)
	 DELETE ChamCongNhanVien WHERE maNguoiCham in (SELECT maNguoiCham from deleted)
	 DELETE NhanVien where maNhanVien in (SELECT maNhanVien from deleted)
 end

GO

CREATE TRIGGER DELETE_PHONGBAN ON PHONGBAN
instead of DELETE
as
 begin
	 DELETE NhanVien WHERE maPhongBan in (SELECT maPhongBan from deleted)
	 DELETE PhongBan where maPhongBan in (SELECT maPhongBan FROM DELETED)
 end
 
