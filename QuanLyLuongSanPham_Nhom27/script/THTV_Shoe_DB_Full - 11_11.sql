--drop database THTV_SHOES
drop DATABASE THTV_SHOES
-- Tạo database CREATE DATABASE THTV_SHOES
CREATE DATABASE THTV_SHOES
GO
USE THTV_SHOES
GO 
CREATE TABLE ToNhom
(
	maToNhom char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenTo nvarchar(50) not null unique,
	soLuongCongNhan int default 0,
	constraint CHK_ToNhom_maToNhom_dinhDang check (maToNhom like 'TN[1-9][0-9][0-9][0-9][0-9][0-9]') -- Gồm 8 kí tự bắt đầu bằng TN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)
GO
INSERT TONHOM 
VALUES 
('TN100001', N'Tổ 1', 0), ('TN100002', N'Tổ 2', 0), ('TN100003', N'Tổ 3', 0), ('TN100004', N'Tổ 4', 0), ('TN100005', N'Tổ 5', 0), ('TN100006', N'Tổ 6', 0), ('TN100007', N'Tổ 7', 0)
, ('TN100008', N'Tổ 8', 0), ('TN100009', N'Tổ 9', 0), ('TN100010', N'Tổ 10', 0)
GO
CREATE TABLE CongNhan
(
	maCongNhan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	hoTen nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	ngaySinh date not null,
	maCCCD char(12) not null,
	soDienThoai char(12) not null,
	email varchar(100) not null,
	gioiTinh bit,
	anhDaiDien varchar(100),
	diaChi nvarchar(100),
	ngayVaoLam date,
	toNhom char(8) COLLATE SQL_Latin1_General_CP1_CS_AS references ToNhom(maToNhom) , -- đã xóa cascade delete
	constraint CHK_CongNhan_maCongNhan_dinhDang check (maCongNhan like 'CN[1-9][0-9][0-9][0-9][0-9][0-9]'),-- Gồm 8 kí tự bắt đầu bằng CN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_CongNhan_tenCongNhan_hienNhien check (hoTen like '%[^A-Za-z]%'), -- Họ tên chỉ chứa kí tự chữ 
	constraint CHK_CongNhan_ngaySinh_hienNhien check (ngaySinh < getdate()), -- ngày sinh phải sau ngày hiện tại
	constraint CHK_CongNhan_ngaySinh_tu18Tuoi check ((DATEDIFF(DD, ngaySinh, getDate()) / 365.25) >= 18), -- tuổi phải từ >= 18 trở lên
	constraint CHK_CongNhan_cccd_du12KiTu check (len(maCCCD) = 12), -- ma cccd đúng 12 kí tự
	constraint CHK_CongNhan_cccd_tatCaLaSo  check (maCCCD like '%[0-9]%'), -- mã cccd phải là kí tự số
	constraint CHK_CongDoan_soDienThoai_doDai check(len(soDienThoai) = 12), -- gồm 12 kí tự
	constraint CHK_CongNhan_soDienThoai_batDauLa0 check ( soDienThoai like '+84%'), -- số điện thoại bắt đầu bằng 0
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
VALUES ('CN100001', N'Nguyễn Văn Hiếu', '2000-12-10', '111122223333', '+84975654628', 'hieurio12@gmail.com', 1, 'male.png', N'Nghệ An', '2022-03-03', 'TN100001')
GO
INSERT CongNhan
VALUES ('CN100002', N'Nguyễn Văn Hậu', '1998-12-10', '111122224444', '+84975654622', 'haunguyen@gmail.com', 1, 'male.png', N'Quãng Ninh', '2022-03-03', 'TN100002')
GO
INSERT CongNhan
VALUES ('CN100003', N'Nguyễn Thị Hà', '2002-01-01', '222233334444', '+84975654623', 'nguyenha@gmail.com', 0, 'female.png', N'Quãng Bình', '2022-04-01', 'TN100003')
GO
INSERT CongNhan
VALUES ('CN100004', N'Phan Văn Đức', '2001-12-10', '333322221111', '+84975222622', 'duckot37@gmail.com', 1, 'male.png', N'Đồng Tháp', '2022-03-02', 'TN100004')
GO
INSERT CongNhan
VALUES ('CN100005', N'Nguyễn Xuân Mạnh', '1990-10-10', '555533332222', '+84363121424', 'xuanmanhnguyen@gmail.com', 1, 'male.png', N'Vĩnh Long', '2022-04-10', 'TN100005')
GO
INSERT CongNhan
VALUES ('CN100006', N'Trần Ngọc Hoài', '2001-12-10', '222122231111', '+84363214224', 'hoaiflower@gmail.com',  0, 'female.png', N'Hà Tĩnh', '2022-02-05', 'TN100005')
GO
INSERT CongNhan
VALUES ('CN100007', N'Lê Hoài Vũ', '1996-06-10', '231223134444', '+84363233924', 'vukhongvu@gmail.com',  1, 'male.png', N'Hà Nội', '2022-01-01', 'TN100004')
GO
INSERT CongNhan
VALUES ('CN100008', N'Nguyễn Đình Dũng', '1992-12-10', '111132321212', '+84363213435', 'dinhdungsoccer@gmail.com', 1, 'male.png', N'Thái Bình', '2022-03-03', 'TN100003')
GO
INSERT CongNhan
VALUES ('CN100009', N'Nguyễn Quang Hải', '1990-03-09', '299966653332', '+84974262444', 'haileague2@gmail.com', 1, 'male.png', N'Hà Nội', '2022-03-02', 'TN100002')
GO
INSERT CongNhan
VALUES ('CN100010', N'Nguyễn Tiến Linh', '1993-02-10', '222122231111', '+84363244224', 'tienwood@gmail.com', 1, 'male.png', N'Hà Giang', '2022-06-03', 'TN100001')
GO
INSERT CongNhan
VALUES ('CN100011', N'Nguyễn Tiến Minh', '1991-03-10', '222122231114', '+84363244222', 'minhcui@gmail.com', 1, 'male.png', N'Hà Giang', '2022-04-03', 'TN100009')
GO
CREATE table BangLuongCongNhan 
(
	maBangLuong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maCongNhan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references CongNhan(maCongNhan), -- đã xóa cascade delete
	ngayTinh date not null,
	soLuongSanPhamLam int not null,
	soNgayDiLam int not null,
	soNgayNghi int not null,
	soPhepNghi int not null,
	tongLuong money,
	donViTien nvarchar(50),
	luongTheoThang nvarchar(50),
	constraint CHK_BangLuongCongNhan_maBangLuong_dinhDang check (maBangLuong like 'LC[1-9][0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng CN 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_BangLuongCongNhan_soLuongSanPhamLam_hienNhien check (soLuongSanPhamLam >= 0), -- số lượng sản phẩm làm được phải >= 0
	constraint CHK_BangLuongCongNhan_soNgayDiLam_hienNhien check (soNgayDiLam >= 0), -- số ngày đi làm phải >= 0
	constraint CHK_BangLuongCongNhan_soNgayNghi_hienNhien check (soNgayNghi >= 0), -- số ngày nghỉ phải >= 0
	constraint CHK_BangLuongCongNhan_soPhepNghi_hienNhien check (soPhepNghi <= soNgayNghi), -- số  phép phải <= số ngày nghỉ
	constraint CHK_BangLuongCongNhan_tongLuong_hienNhien check (tongLuong >= 0), -- tổng lương phải >= 0
	constraint CHK_BangLuongCongNhan_donViTien_hienNhien check (donViTien in ('VND', 'vnd')) -- chỉ chấp nhận dạng tiền usd, vnd
)
GO
INSERT BangLuongCongNhan
VALUES ('LC100001', 'CN100001', '2022-05-05', 221, 25, 5, 4, 6132312, 'vnd', '05-2022'),
('LC100002', 'CN100002', '2022-05-05', 321, 23, 7, 4, 9132312, 'vnd', '05-2022'),
('LC100003', 'CN100003', '2022-05-05', 119, 24, 6, 1, 12132312, 'vnd', '05-2022'),
('LC100004', 'CN100004', '2022-05-05', 23, 26, 4, 2, 16138312, 'vnd', '05-2022'),
('LC100005', 'CN100005', '2022-05-05', 32, 26, 4, 0, 21312122, 'vnd', '05-2022'),
('LC100006', 'CN100006', '2022-05-05', 21, 26, 4, 0, 23123123, 'vnd', '05-2022'),
('LC100007', 'CN100007', '2022-05-05', 33, 19, 11, 1, 21121234, 'vnd', '05-2022'),
('LC100008', 'CN100008', '2022-05-05', 123, 24, 6, 0, 12314545, 'vnd', '05-2022'),
('LC100009', 'CN100009', '2022-05-05', 431, 25, 5, 0, 32123124, 'vnd', '05-2022'),
('LC100010', 'CN100001', '2022-05-05', 89, 21, 9, 1, 7876889, 'vnd', '05-2022'),
('LC100011', 'CN100002', '2022-09-10', 321, 15, 15, 5, 3331234, 'vnd', '09-2022'),
('LC100012', 'CN100003', '2022-09-10', 16, 16, 14, 4, 4353451, 'vnd', '09-2022'),
('LC100013', 'CN100004', '2022-09-10', 45, 21, 9, 0, 15124312, 'vnd', '09-2022'),
('LC100014', 'CN100005', '2022-09-10', 65, 21, 9, 2, 12354321, 'vnd', '09-2022'),
('LC100015', 'CN100006', '2022-09-10', 213, 25, 5, 2, 12315431, 'vnd', '09-2022'),
('LC100016', 'CN100007', '2022-09-10', 54, 23, 7, 1, 43534511, 'vnd', '09-2022'),
('LC100017', 'CN100008', '2022-09-10', 55, 23, 7, 4, 45643413, 'vnd', '09-2022'),
('LC100018', 'CN100008', '2022-09-10', 77, 24, 7, 2, 12315831, 'vnd', '09-2022'),
('LC100019', 'CN100009', '2022-09-10', 44, 25, 6, 1, 12431235, 'vnd', '09-2022'),
('LC100020', 'CN100010', '2022-09-10', 83, 27, 3, 1, 21243123, 'vnd', '09-2022')

GO
CREATE TABLE PhongBan
(
	maPhongBan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenPhongBan nvarchar(50) not null unique,
	soLuongNhanVien int default 0,
	constraint CHK_PhongBan_maPhongBan_dinhDang check (maPhongBan like 'PB[1-9][0-9][0-9][0-9][0-9][0-9]') -- Gồm 8 kí tự bắt đầu bằng PB 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)
GO
INSERT PhongBan
VALUES ('PB100001', N'Phòng quản lý', 0), 
('PB100002', N'Phòng kiểm toán', 0),
('PB100003', N'Phòng hành chính', 0),
('PB100004', N'Phòng kế toán', 0),
('PB100005', N'Phòng nhân sự', 0),
('PB100006', N'Phòng chăm sóc khách hàng', 0),
('PB100007', N'Phòng nghiên cứu', 0), 
('PB100008', N'Phòng phát triển', 0),
('PB100009', N'Phòng tài chính', 0), 
('PB100010', N'Phòng đào tạo', 0)
GO
CREATE TABLE NhanVien
(
	maNhanVien char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	hoTen nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	ngaySinh date not null,
	maCCCD char(12) not null,
	soDienThoai char(12) not null,
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
	constraint CHK_NhanVien_soDienThoai_doDai check(len(soDienThoai) = 12), -- gồm 12 kí tự
	constraint CHK_NhanVien_soDienThoai_batDauLa0 check ( soDienThoai like '+84%'), -- số bắt đầu bằng 0
	constraint CHK_NhanVien_matKhau_itNhat6KiTu check (len(matKhau) >= 6), -- mật khẩu >= 6 kí tự
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
values ('NV100001', N'Nguyễn Văn Vũ', '1995-02-02', '222233334444', '+84975123123', 'ngvanvu@gmail.com', '111111', N'Quản Lý', '2022-03-03', 4000000, 1, 'male.png', N'Phú Yên', 'PB100001')
GO
INSERT NhanVien 
values ('NV100002', N'Nguyễn Văn Toản', '2001-03-02', '111133334444', '+84862170471', 'mmccool12@gmail.com', '111111', N'Quản Lý', '2022-03-03', 3600000, 1, 'male.png', N'Cà Mau', 'PB100002')
GO
INSERT NhanVien 
values ('NV100003', N'Chu Hữu Hạnh', '2000-05-01', '213122313124', '+84869094448', 'huhuhanhne@gmail.com', '111111', N'Nhân Viên', '2022-03-03', 2233000, 1, 'male.png', N'Vĩnh Phúc', 'PB100003')
GO
INSERT NhanVien 
values('NV100004', N'Ưng Trường Phúc', '2002-01-01', '312331234123', '+84394710588', 'phucnotalone@gmail.com', '111111', N'Nhân Viên', '2022-03-03', 5500000, 1, 'male.png', N'Hội An', 'PB100004')
GO
INSERT NhanVien 
values ('NV100005', N'Đặng Ngọc Dương', '1999-11-02', '431521413514', '+84352162139', 'ngoctrongda@gmail.com', '111111', N'Nhân Viên', '2022-03-03', 3600000, 1, 'male.png', N'Đà Nẵng', 'PB100005')
GO
INSERT NhanVien 
values ('NV100006', N'Lê Văn Hảo', '1994-02-01', '425176362421', '+84384177886', 'dinhaobro@gmail.com', '111111', N'Quản lý', '2022-03-05', 3600000, 1, 'male.png', N'Cần Thơ', 'PB100001')
GO
INSERT NhanVien 
values ('NV100007', N'Tạ Hoàng Lan', '2002-05-05', '312515451312', '+84364248488', 'hoanlan@gmail.com', '111111', N'Nhân Viên', '2022-03-06', 2340000, 1, 'male.png', N'Vũng Tàu', 'PB100001')
GO
INSERT NhanVien 
values ('NV100008', N'Lưu Cát Ly', '2001-12-12', '432514313151', '+84325230839', 'catlyluu@gmail.com', '111111', N'Nhân Viên', '2022-03-02', 2350000, 0, 'female.png', N'Dak Nông', 'PB100002')
GO
INSERT NhanVien 
values ('NV100009', N'Đặng Mộng Vy', '1998-01-02', '231512355141', '+84384910990', 'dangmongvi@gmail.com', '111111', N'Nhân Viên', '2022-03-01', 4333222, 0, 'female.png', N'Đồng Tháp', 'PB100003')
go
INSERT NhanVien 
values ('NV100010', N'Lý Tố Quyên', '1998-01-05', '214515571351', '+84397872166', 'lytoquyen12@gmail.com', '111111', N'Nhân Viên', '2022-03-01', 3700000, 0, 'female.png', N'Bình Dương', 'PB100001')
GO
CREATE TABLE BangLuongNhanVien
(
	maBangLuong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maNhanVien char(8) COLLATE SQL_Latin1_General_CP1_CS_AS  not null references NhanVien(maNhanVien) , -- đã xóa cascade delete
	soNgayDiLam int not null,
	soNgayNghi int not null,
	soPhepNghi int not null,
	ngayTinh date not null,
	luongTheoThang NVARCHAR(50),
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

GO
CREATE TABLE ChamCongNhanVien
(
	maNhanVien char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references NhanVien(maNhanVien),
	caLam nvarchar(50) not null,
	ngayChamCong date not null,
	trangThaiDiLam nvarchar(30),
	gioDiLam nvarchar(30),
	maNguoiCham char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references NhanVien(maNhanVien),
)
GO
	alter table ChamCongNhanVien add primary key(maNhanVien, caLam, ngayChamCong)
GO
CREATE TABLE HopDong
(
	maHopDong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	tenHopDong nvarchar(100) not null,
	tenKhachHang nvarchar(50) not null,
	soTienCoc money not null,
	tongTien money not null,
	ngayKyKet date not null, 
	hanChot date not null,
	yeuCau nvarchar(1000),
	constraint CHK_HopDong_maHopDong_dingDang check (maHopDong like 'HD[1-9][0-9][0-9][0-9][0-9][0-9]'),
	constraint CHK_HopDong_tienCoc_hienNhien check (soTienCoc > 0),
	constraint CHK_HopDong_tongTien_hienNhien check (tongTien > 0)
)
GO
INSERT HopDong
values 
('HD100001', N'Hợp đồng gia công Adidas quý 1 2022', 'Adidas', 120000000, 1200000000, '2022-01-01', '2022-12-12', N'3 sản phẩm là: Adidas Yeezy 350 size: 44, chất liệu nilon, số lượng 500; Adidas Prophere size: 39 số lượng: 1400, chất liệu: vải nỉ; Adidas NMD R1 size: vải cotton, size: 41, số lượng 390'),
('HD100002', N'Hợp đồng sản xuất Adidas quý 4 2022', 'Nike', 100000000, 1000000000, '2022-10-01', '2022-12-29', N'2 sản phầm là: Nike Air Jordan 1 Dior size: 42, chất liệu: cottonm, số lượng 670; Nike Sb Dunk size: 47, chất liệu: vải kate, số lượng 644'),
('HD100003', N'Hợp đồng sản xuất Puma quý 3 2022', 'Puma', 50000000, 500000000, '2022-09-10', '2023-01-01', N'3 sản phẩm là: Puma Suede Classic size: 40, chất liệu vãi jeans, số lượng 1000; Vans Authentic size: 38, chất liệu: vãi len số lượng 1010; Louis Vuitton Archlight size: 41, vãi cotton, số lượng: 121'),
('HD100004', N'Hợp đồng sản xuất Balen quý 3 2022', 'Balenciaga', 300000000, 3000000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400'),
('HD100005', N'Hợp đồng sản xuất Converse quý 3 2022', 'Converse', 15000000, 150000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400'),
('HD100006', N'Hợp đồng sản xuất Balen quý 1 2022', 'New Balance', 50000000, 500000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400'),
('HD100007', N'Hợp đồng sản xuất Fila quý 3 2022', 'Fila', 120000000, 1200000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400'),
('HD100008', N'Hợp đồng sản xuất Reebok quý 1', 'Reebok', 300000000, 3000000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400'),
('HD100009', N'Hợp đồng sản xuất Chris quý 1', 'Christian Louboutin', 100000000, 1000000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400'),
('HD100010', N'Hợp đồng sản xuất Asics quý 1', 'Asics', 300000000, 3000000000, '2022-09-01', '2023-04-10', N'2 sản phẩm là: MLB Boston Red Sox size: 41, chất liệu vãi cotton, số lượng 1000; Gucci Chunky size: 44, Vải 210D, số lượng 400')


GO
CREATE TABLE SanPham
(
	maSanPham char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maHopDong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references HopDong(maHopDong),
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
VALUES ('SP100001','HD100004', N'MLB Boston Red Sox', 1000, N'214, 214, 214', N'Vải cotton', 41, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100002', 'HD100002', N'Nike Air Jordan 1 Dior', 670, N'250, 0, 0', N'Vải cotton', 42, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100003', 'HD100001' ,N'Adidas Yeezy 350', 500, N'250, 0, 51', N'Nylon', 44, 'icons8-shoes-64(5).png', 0)
GO
INSERT SanPham
VALUES ('SP100004','HD100004', N'Gucci Chunky', 400, N'170, 219, 241', N'Vải 210D', 44, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100005', 'HD100001', N'Adidas Prophere', 1400, N'42, 23, 241', N'Vải nỉ', 39, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100006','HD100003', N'Louis Vuitton Archlight', 121, N'42, 111, 11', N'Vải cotton', 41, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100007', 'HD100001', N'Adidas NMD R1', 390, N'214, 193, 214', N'Vải cotton', 41, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100008', 'HD100002' , N'Nike Sb Dunk', 644, N'250, 214, 51', N'Vải kate', 47, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100009', 'HD100003', N'Vans Authentic', 1010, N'250, 0, 51', N'Vải len', 38, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100010', 'HD100003', N'Puma Suede Classic', 1000, N'200, 214, 214', N'Vải jeans', 40, 'icons8-shoes-64(5).png', 0)
GO
CREATE TABLE CongDoan
(
	maCongDoan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	thuTu int not null,
	tenCongDoan nvarchar(50) COLLATE SQL_Latin1_General_CP1_CS_AS not null,
	soLuongCan int not null,
	tinhTrang nvarchar(50),
	thoiHan date not null,
	maSanPham char(8) COLLATE SQL_Latin1_General_CP1_CS_AS  not null references SanPham(maSanPham), -- đã xóa delete cascade
	tienLuong money,
	constraint CHK_CongDoan_maCongDoan_dinhDang check (maCongDoan like 'CD[1-9][0-9][0-9][0-9][0-9][0-9]'), -- Gồm 8 kí tự bắt đầu bằng CD 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
	constraint CHK_CongDoan_soLuongCan_hienNhien check (soLuongCan > 0), -- số lượng cần phải >= 0
	constraint CHK_CongDoan_thoiHan_hienNhien check (thoiHan > GETDATE()), -- thời hạn phải sau ngày hiện tại
	constraint CHK_CongDoan_tienLuong_hienNhien check (tienLuong > 0), -- tiền lương phải >= 0
	constraint CHK_CongDoan_thuThu check (thuTu > 0)
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
VALUES ('CD100001', 1 ,N'Lên Khuôn', 1000, N'0%', '2022-12-12', 'SP100001', 1231)
GO
INSERT CongDoan
VALUES('CD100002', 2,N'Khâu dập', 1200, N'0%', '2022-12-12', 'SP100001', 1500)
GO
INSERT CongDoan
VALUES ('CD100003', 3, N'May da', 800, N'0%', '2022-12-12', 'SP100001', 1300)
GO
INSERT CongDoan
VALUES ('CD100004', 4, N'Làm mũi giày', 1300, N'0%', '2022-12-12', 'SP100001', 1313)
GO
INSERT CongDoan
VALUES ('CD100005', 5, N'Trang trí', 999, N'0%', '2022-12-12', 'SP100001', 1500)
GO
INSERT CongDoan
VALUES ('CD100006',1, N'Lên Khuôn', 1000, N'0%', '2022-12-12', 'SP100002', 2000)
GO
INSERT CongDoan
VALUES ('CD100007',2, N'Khâu dập', 900, N'0%', '2022-12-12', 'SP100002', 3300)
GO
INSERT CongDoan
VALUES ('CD100008',3, N'May da', 990, N'0%', '2022-12-12', 'SP100002', 1122)
GO
INSERT CongDoan
VALUES ('CD100009',4, N'Làm mũi giày', 1000, N'0%', '2022-12-12', 'SP100002', 3123)
GO
INSERT CongDoan
VALUES ('CD100010',1, N'Trang trí', 1110, N'0%', '2022-12-12', 'SP100003', 1234)
GO
INSERT CongDoan
VALUES ('CD100011',2, N'Lên Khuôn', 1000, N'0%', '2022-12-12', 'SP100003', 2000)
GO
INSERT CongDoan
VALUES ('CD100012',3, N'Khâu dập', 900, N'0%', '2022-12-12', 'SP100003', 3300)
GO
INSERT CongDoan
VALUES ('CD100013',4, N'May da', 990, N'0%', '2022-12-12', 'SP100003', 1122)
GO
INSERT CongDoan
VALUES ('CD100014',5, N'Làm mũi giày', 1000, N'0%', '2022-12-12', 'SP100003', 3123)
GO
INSERT CongDoan
VALUES ('CD100015',6, N'Trang trí', 1110, N'0%', '2022-12-12', 'SP100003', 1234)
GO
INSERT CongDoan
VALUES ('CD100016',1, N'Lên Khuôn', 1000, N'0%', '2022-12-12', 'SP100004', 2000)
GO
INSERT CongDoan
VALUES ('CD100017',1, N'Khâu dập', 900, N'0%', '2022-12-12', 'SP100004', 3300)
GO
INSERT CongDoan
VALUES ('CD100018',1, N'May da', 990, N'0%', '2022-12-12', 'SP100004', 1122)
GO
INSERT CongDoan
VALUES ('CD100019',1, N'Làm mũi giày', 1000, N'0%', '2022-12-12', 'SP100004', 3123)
GO
INSERT CongDoan
VALUES ('CD100020',1, N'Trang trí', 1110, N'0%', '2022-12-12', 'SP100004', 1234)
GO
CREATE TABLE PhanCongCongNhan
(
	maPhanCong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS primary key,
	maCongNhan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references CongNhan(maCongNhan) , -- đã xóa cascade delete
	maNguoiPhanCong char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references NhanVien(maNhanVien) , -- đã xóa cascade delete
	maCongDoan char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references CongDoan(maCongDoan), -- đã xóa cascade delete
	ngayPhanCong date,
	soLuongCanLam int,
	maToNhom char(8) COLLATE SQL_Latin1_General_CP1_CS_AS not null references ToNhom(maToNhom) ,
	constraint CHK_PhanCongCongNhan_maPhanCong check (maPhanCong like 'PC[1-9][0-9][0-9][0-9][0-9][0-9]') -- Gồm 8 kí tự bắt đầu bằng PC 6 kí tự sau là số từ 0-9 riêng số đầu tiên bắt đầu bằng 1
)
GO

INSERT PhanCongCongNhan
VALUES ('PC100001', 'CN100001', 'NV100001', 'CD100001' , '2022-05-09', 10,'TN100001'),
 ('PC100002', 'CN100002', 'NV100002', 'CD100002' ,'2022-05-09', 9,'TN100002'),
 ('PC100003', 'CN100003', 'NV100001', 'CD100001' ,'2022-05-09',9 ,'TN100003'),
 ('PC100004', 'CN100004', 'NV100002', 'CD100002' ,'2022-05-09',9 ,'TN100004'),
 ('PC100005', 'CN100005', 'NV100001', 'CD100003' ,'2022-05-09',9 ,'TN100005'),
 ('PC100006', 'CN100006', 'NV100002', 'CD100004' ,'2022-05-09',9 ,'TN100005'),
 ('PC100007', 'CN100007', 'NV100001', 'CD100001' ,'2022-05-09',9 ,'TN100004'),
 ('PC100008', 'CN100008', 'NV100002', 'CD100002' ,'2022-05-09',9 ,'TN100003'),
 ('PC100009', 'CN100009', 'NV100001', 'CD100003' ,'2022-05-09',9 ,'TN100002'),
 ('PC100010', 'CN100010', 'NV100002', 'CD100001' ,'2022-05-09', 9,'TN100001')
GO
CREATE TABLE ChamCongCongNhan(
	maPhanCong char(8)  COLLATE SQL_Latin1_General_CP1_CS_AS not null references PhanCongCongNhan(maPhanCong) , -- đã xóa cascade delete
	caLam nvarchar(50) not null,
	ngayChamCong date not null,
	soLuongLam int not null,
	trangThaiDiLam nvarchar(50),
	gioDiLam nvarchar(50),
	constraint CHK_ChamCongCongNhan_soLuongLam_hienNhien check (soLuongLam >= 0) -- số lượng làm phải >= 0
)
GO
	ALTER TABLE ChamCongCongNhan add primary key(maPhanCong, caLam, ngayChamCong)
GO
INSERT ChamCongCongNhan
VALUES ('PC100001', N'Đêm', '2022-11-02', 24,  N'Đi Làm',  '19h00'),
('PC100002', N'Sáng' , '2022-11-02', 32, N'Đi Trễ', '8h30'),
('PC100003',  N'Chiều',  '2022-11-02', 15,  N'Đi Làm',  '12h00'),
('PC100004', N'Đêm',  '2022-11-02', 55,  N'Đi Làm',  '19h00'),
('PC100005', N'Đêm',  '2022-11-02', 33, N'Đi Làm',  '19h00'),
('PC100006', N'Sáng',  '2022-11-02', 12, N'Đi Trễ',  '8h25'),
('PC100007', N'Sáng',  '2022-11-02', 43,  N'Đi Làm',  '8h00'),
('PC100008', N'Sáng',  '2022-11-02', 54, N'Đi Làm',  '8h00'),
('PC100009', N'Sáng',  '2022-11-02', 24,  N'Đi Trễ',  '8h30'),
('PC100010', N'Sáng',  '2022-11-02', 54,  N'Đi Làm',  '8h00'),
('PC100001', N'Đêm', '2022-11-01', 25,  N'Đi Làm',  '19h00'),
('PC100002', N'Sáng' ,'2022-11-01', 31, N'Đi Trễ', '8h30'),
('PC100003',  N'Chiều', '2022-11-01', 11,  N'Đi Làm',  '12h00'),
('PC100004', N'Đêm', '2022-11-01', 22,  N'Đi Làm',  '19h00'),
('PC100005', N'Đêm', '2022-11-01', 53, N'Đi Làm',  '19h00'),
('PC100006', N'Sáng', '2022-11-01', 15, N'Đi Trễ',  '8h25'),
('PC100007', N'Sáng', '2022-11-01', 54,  N'Đi Làm',  '8h00'),
('PC100008', N'Sáng', '2022-11-01', 32, N'Đi Làm',  '8h00'),
('PC100009', N'Sáng', '2022-11-01', 13,  N'Đi Trễ',  '8h30'),
('PC100010', N'Sáng', '2022-11-01', 53,  N'Đi Làm',  '8h00')

 -- Tạo các trigger instead of xóa sản phẩm 
GO
CREATE TRIGGER trigger_Xoa_SanPham on SanPham
	INSTEAD OF DELETE
	AS 
		BEGIN
			SET NOCOUNT ON;
			 DELETE FROM CongDoan WHERE maSanPham IN (SELECT maSanPham FROM DELETED)
			  DELETE SanPham where maSanPham in (SELECT maSanPham from deleted)
		END
GO
CREATE TRIGGER trigger_Xoa_CongDoan on CongDoan
	INSTEAD OF DELETE
	AS 
		BEGIN
			SET NOCOUNT ON;
			 DELETE FROM PhanCongCongNhan WHERE maCongDoan IN (SELECT maCongDoan FROM DELETED)
			  DELETE CongDoan where maCongDoan in (SELECT maCongDoan from deleted)
		END
GO
CREATE TRIGGER trigger_Xoa_PCCN on PhanCongCongNhan
	INSTEAD OF DELETE
	AS 
		BEGIN
			SET NOCOUNT ON;
			 DELETE FROM ChamCongCongNhan WHERE maPhanCong IN (SELECT maPhanCong FROM DELETED)
			  DELETE PhanCongCongNhan where maPhanCong in (SELECT maPhanCong from deleted)
		END
GO

CREATE TRIGGER trigger_Xoa_NhanVien ON NhanVien
instead of DELETE
as
 begin
	 SET NOCOUNT ON;
	 DELETE ChamCongNhanVien WHERE maNhanVien in (SELECT maNhanVien from deleted)
	 DELETE ChamCongNhanVien WHERE maNguoiCham in (SELECT maNguoiCham from deleted)
	 DELETE BangLuongNhanVien WHERE maNhanVien IN (SELECT maNhanVien from deleted)
	 DELETE PhanCongCongNhan WHERE maNguoiPhanCong IN (SELECT maNhanVien from deleted)
	 DELETE NhanVien where maNhanVien in (SELECT maNhanVien from deleted)
	 
 end

GO
CREATE TRIGGER trigger_Xoa_PhongBan ON PHONGBAN
instead of DELETE
as
 begin
	 DELETE NhanVien WHERE maPhongBan in (SELECT maPhongBan from deleted)
	 DELETE PhongBan where maPhongBan in (SELECT maPhongBan FROM DELETED)
 end
GO
CREATE TRIGGER trigger_Xoa_ToNhom on ToNhom
	INSTEAD OF DELETE
	AS 
		BEGIN
			SET NOCOUNT ON;
			 DELETE FROM CongNhan WHERE toNhom IN (SELECT maToNhom FROM DELETED)
			 DELETE FROM PhanCongCongNhan WHERE maToNhom IN (SELECT maToNhom FROM DELETED)
			  DELETE ToNhom where maToNhom in (SELECT maToNhom from deleted)
			 
		END
GO
	CREATE TRIGGER trigger_Xoa_CN on CongNhan
	INSTEAD OF DELETE
	AS 
		BEGIN
			SET NOCOUNT ON;
			 DELETE FROM BangLuongCongNhan WHERE maCongNhan IN (SELECT maCongNhan FROM DELETED)
			 DELETE FROM PhanCongCongNhan WHERE maCongNhan IN (SELECT maCongNhan FROM DELETED)
			 DELETE CongNhan where maCongNhan in (select maCongNhan from deleted)
		END
GO
	CREATE TRIGGER trigger_XoaP_HD on HopDong
	INSTEAD OF DELETE
	AS
		BEGIN
			SET NOCOUNT ON;
			DELETE FROM SanPham WHERE maHopDong IN (SELECT maHopDong FROM deleted)
			DELETE FROM HopDong WHERE maHopDong IN (SELECT maHopDong from deleted)
		END
GO
delete from PhanCongCongNhan
delete from ChamCongCongNhan
delete from BangLuongCongNhan
delete from ChamCongNhanVien
delete from BangLuongNhanVien

