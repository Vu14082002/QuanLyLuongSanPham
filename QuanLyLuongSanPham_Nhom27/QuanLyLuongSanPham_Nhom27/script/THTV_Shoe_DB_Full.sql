--drop database THTV_SHOES
drop  DATABASE THTV_SHOES
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
VALUES ('CN100001', N'Nguyễn Văn Hiếu', '2000-12-10', '111122223333', '0975654628', 'hieurio12@gmail.com', '111111', 1, 'male.png', N'Nghệ An', GETDATE(), 'TN100001')
GO
INSERT CongNhan
VALUES ('CN100002', N'Nguyễn Văn Hậu', '1998-12-10', '111122224444', '0975654622', 'haunguyen@gmail.com', '111111', 1, 'male.png', N'Quãng Ninh', GETDATE(), 'TN100002')
GO
INSERT CongNhan
VALUES ('CN100003', N'Nguyễn Thị Hà', '2002-01-01', '222233334444', '0975654623', 'nguyenha@gmail.com', '111111', 0, 'female.png', N'Quãng Bình', GETDATE(), 'TN100003')
GO
INSERT CongNhan
VALUES ('CN100004', N'Phan Văn Đức', '2001-12-10', '333322221111', '0975222622', 'duckot37@gmail.com', '111111', 1, 'male.png', N'Đồng Tháp', GETDATE(), 'TN100004')
GO
INSERT CongNhan
VALUES ('CN100005', N'Nguyễn Xuân Mạnh', '1990-10-10', '555533332222', '0363121424', 'xuanmanhnguyen@gmail.com', '111111', 1, 'male.png', N'Vĩnh Long', GETDATE(), 'TN100005')
GO
INSERT CongNhan
VALUES ('CN100006', N'Trần Ngọc Hoài', '2001-12-10', '222122231111', '0363214224', 'hoaiflower@gmail.com', '111111', 0, 'female.png', N'Hà Tĩnh', GETDATE(), 'TN100005')
GO
INSERT CongNhan
VALUES ('CN100007', N'Lê Hoài Vũ', '1996-06-10', '231223134444', '0363233924', 'vukhongvu@gmail.com', '111111', 1, 'male.png', N'Hà Nội', GETDATE(), 'TN100004')
GO
INSERT CongNhan
VALUES ('CN100008', N'Nguyễn Đình Dũng', '1992-12-10', '111132321212', '0363213435', 'dinhdungsoccer@gmail.com', '111111', 1, 'male.png', N'Thái Bình', GETDATE(), 'TN100003')
GO
INSERT CongNhan
VALUES ('CN100009', N'Nguyễn Quang Hải', '1990-03-09', '299966653332', '0974262444', 'haileague2@gmail.com', '111111', 1, 'male.png', N'Hà Nội', GETDATE(), 'TN100002')
GO
INSERT CongNhan
VALUES ('CN100010', N'Nguyễn Tiến Linh', '1993-02-10', '222122231111', '0363244224', 'tienwood@gmail.com', '111111', 1, 'male.png', N'Hà Giang', GETDATE(), 'TN100001')
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
VALUES ('LC100001', 'CN100001', GETDATE(), 221, 25, 5, 4, 6132312, 'vnd'),
('LC100002', 'CN100002', GETDATE(), 321, 23, 7, 4, 9132312, 'vnd'),
('LC100003', 'CN100003', GETDATE(), 119, 24, 6, 1, 12132312, 'vnd'),
('LC100004', 'CN100004', GETDATE(), 23, 26, 4, 2, 16138312, 'vnd'),
('LC100005', 'CN100005', GETDATE(), 32, 26, 4, 0, 21312122, 'vnd'),
('LC100006', 'CN100006', GETDATE(), 21, 26, 4, 0, 23123123, 'vnd'),
('LC100007', 'CN100007', GETDATE(), 33, 19, 11, 1, 21121234, 'vnd'),
('LC100008', 'CN100008', GETDATE(), 123, 24, 6, 0, 12314545, 'vnd'),
('LC100009', 'CN100009', GETDATE(), 431, 25, 5, 0, 32123124, 'vnd'),
('LC100010', 'CN100001', GETDATE(), 89, 21, 9, 1, 7876889, 'vnd'),
('LC100011', 'CN100002', '2022-09-10', 321, 15, 15, 5, 3331234, 'vnd'),
('LC100012', 'CN100003', '2022-09-10', 16, 16, 14, 4, 4353451, 'vnd'),
('LC100013', 'CN100004', '2022-09-10', 45, 21, 9, 0, 15124312, 'vnd'),
('LC100014', 'CN100005', '2022-09-10', 65, 21, 9, 2, 12354321, 'vnd'),
('LC100015', 'CN100006', '2022-09-10', 213, 25, 5, 2, 12315431, 'vnd'),
('LC100016', 'CN100007', '2022-09-10', 54, 23, 7, 1, 43534511, 'vnd'),
('LC100017', 'CN100008', '2022-09-10', 55, 23, 7, 4, 45643413, 'vnd'),
('LC100018', 'CN100008', '2022-09-10', 77, 24, 7, 2, 12315831, 'vnd'),
('LC100019', 'CN100009', '2022-09-10', 44, 25, 6, 1, 12431235, 'vnd'),
('LC100020', 'CN100010', '2022-09-10', 83, 27, 3, 1, 21243123, 'vnd')

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
values ('NV100001', N'Nguyễn Văn Vũ', '1995-02-02', '222233334444', '0975123123', 'ngvanvu@gmail.com', '111111', N'Quản Lý', GETDATE(), 4000000, 1, 'male.png', N'Phú Yên', 'PB100001')
GO
INSERT NhanVien 
values ('NV100002', N'Nguyễn Văn Toản', '2001-03-02', '111133334444', '0862170471', 'mmccool12@gmail.com', '111111', N'Quản Lý', GETDATE(), 3600000, 1, 'male.png', N'Cà Mau', 'PB100002')
GO
INSERT NhanVien 
values ('NV100003', N'Chu Hữu Hạnh', '2000-05-01', '213122313124', '0869094448', 'huhuhanhne@gmail.com', '111111', N'Nhân Viên', GETDATE(), 2233000, 1, 'male.png', N'Vĩnh Phúc', 'PB100003')
GO
INSERT NhanVien 
values('NV100004', N'Ưng Trường Phúc', '2002-01-01', '312331234123', '0394710588', 'phucnotalone@gmail.com', '111111', N'Nhân Viên', GETDATE(), 5500000, 1, 'male.png', N'Hội An', 'PB100004')
GO
INSERT NhanVien 
values ('NV100005', N'Đặng Ngọc Dương', '1999-11-02', '431521413514', '0352162139', 'ngoctrongda@gmail.com', '111111', N'Nhân Viên', GETDATE(), 3600000, 1, 'male.png', N'Đà Nẵng', 'PB100005')
GO
INSERT NhanVien 
values ('NV100006', N'Lê Văn Hảo', '1994-02-01', '425176362421', '0384177886', 'dinhaobro@gmail.com', '111111', N'Quản lý', GETDATE(), 3600000, 1, 'male.png', N'Cần Thơ', 'PB100001')
GO
INSERT NhanVien 
values ('NV100007', N'Tạ Hoàng Lan', '2002-05-05', '312515451312', '0364248488', 'hoanlan@gmail.com', '111111', N'Nhân Viên', GETDATE(), 2340000, 1, 'male.png', N'Vũng Tàu', 'PB100001')
GO
INSERT NhanVien 
values ('NV100008', N'Lưu Cát Ly', '2001-12-12', '432514313151', '0325230839', 'catlyluu@gmail.com', '111111', N'Nhân Viên', GETDATE(), 2350000, 0, 'female.png', N'Dak Nông', 'PB100002')
GO
INSERT NhanVien 
values ('NV100009', N'Đặng Mộng Vy', '1998-01-02', '231512355141', '0384910990', 'dangmongvi@gmail.com', '111111', N'Nhân Viên', GETDATE(), 4333222, 0, 'female.png', N'Đồng Tháp', 'PB100003')
go
INSERT NhanVien 
values ('NV100010', N'Lý Tố Quyên', '1998-01-05', '214515571351', '0397872166', 'lytoquyen12@gmail.com', '111111', N'Nhân Viên', GETDATE(), 3700000, 0, 'female.png', N'Bình Dương', 'PB100001')
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
values ('LN100001', 'NV100001', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100002', 'NV100002', 25, 2, 0, GETDATE(), 6000000, 'VND'),
('LN100003', 'NV100003', 23, 4, 0, GETDATE(), 5500000, 'VND'),
('LN100004', 'NV100004', 26, 1, 1, GETDATE(), 6300000, 'VND'),
('LN100005', 'NV100005', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100006', 'NV100006', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100007', 'NV100007', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100008', 'NV100008', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100009', 'NV100009', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100010', 'NV100010', 27, 0, 0, GETDATE(), 7000000, 'VND'),
('LN100011', 'NV100001', 27, 0, 0, '2022-09-10', 7000000, 'VND'),
('LN100012', 'NV100002', 27, 0, 0, '2022-09-10', 7000000, 'VND'),
('LN100013', 'NV100003', 24, 3, 1, '2022-09-10', 6000000, 'VND'),
('LN100014', 'NV100004', 27, 0, 0, '2022-09-10', 7000000, 'VND'),
('LN100015', 'NV100005', 23, 4, 0, '2022-09-10', 5400000, 'VND'),
('LN100016', 'NV100006', 27, 0, 0, '2022-09-10', 7000000, 'VND'),
('LN100017', 'NV100007', 27, 0, 0, '2022-09-10', 7000000, 'VND'),
('LN100018', 'NV100008', 22, 5, 3, '2022-09-10', 4900000, 'VND'),
('LN100019', 'NV100009', 27, 0, 0, '2022-09-10', 7000000, 'VND'),
('LN100020', 'NV100010', 21, 6, 3, '2022-09-10', 4500000, 'VND')
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
INSERT ChamCongNhanVien
VALUES ('NV100001', N'Sáng' , GETDATE(),  N'Đi Làm', '8h00', 'NV100002'),
('NV100002', N'Đêm', GETDATE(),  N'Đi Trễ', '8h30', 'NV100002'),
('NV100003', N'Chiều', GETDATE(), N'Nghỉ Có Phép', '', 'NV100002'),
('NV100004', N'Chiều', GETDATE(), N'Nghỉ Không Phép', '', 'NV100002'),
('NV100005', N'Chiều', GETDATE(), N'Đi Làm', '8h00', 'NV100001'),
('NV100006', N'Sáng', GETDATE(), N'Đi Làm', '8h00', 'NV100001'),
('NV100007', N'Sáng' , GETDATE(), N'Đi Làm', '8h00',  'NV100001'),
('NV100008', N'Chiều' , GETDATE(), N'Nghỉ Có Phép', '',  'NV100001'),
('NV100009', N'Đêm', GETDATE(),  N'Nghỉ Có Phép', '',  'NV100002'),
('NV100010', N'Chiều', GETDATE(), N'Đi Làm', '8h00',  'NV100002')
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
VALUES ('SP100001', N'MLB Boston Red Sox', 1000, N'214, 214, 214', N'Vải cotton', 41, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100002', N'Nike Air Jordan 1 Dior', 670, N'250, 0, 0', N'Vải cotton', 42, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100003', N'Adidas Yeezy 350', 500, N'250, 0, 51', N'Nylon', 44, 'icons8-shoes-64(5).png', 0)
GO
INSERT SanPham
VALUES ('SP100004', N'Gucci Chunky', 400, N'170, 219, 241', N'Vải 210D', 44, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100005', N'Adidas Prophere', 1400, N'42, 23, 241', N'Vải nỉ', 39, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100006', N'Louis Vuitton Archlight', 121, N'42, 111, 11', N'Vải cotton', 41, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100007', N'Adidas NMD R1', 390, N'214, 193, 214', N'Vải cotton', 41, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100008', N'Nike Sb Dunk', 644, N'250, 214, 51', N'Vải kate', 47, 'icons8-shoes-64(3).png', 0)
GO
INSERT SanPham
VALUES ('SP100009', N'Vans Authentic', 1010, N'250, 0, 51', N'Vải len', 38, 'icons8-shoes-64(2).png', 0)
GO
INSERT SanPham
VALUES ('SP100010', N'Puma Suede Classic', 1000, N'200, 214, 214', N'Vải jeans', 40, 'icons8-shoes-64(5).png', 0)
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
	constraint CHK_CongDoan_soLuongCan_hienNhien check (soLuongCan > 0), -- số lượng cần phải >= 0
	constraint CHK_CongDoan_thoiHan_hienNhien check (thoiHan > GETDATE()), -- thời hạn phải sau ngày hiện tại
	constraint CHK_CongDoan_tienLuong_hienNhien check (tienLuong > 0) -- tiền lương phải >= 0
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
VALUES ('CD100001', N'Lên Khuôn', 1000, N'0%', '2022-12-12', 'SP100001', 1231)
GO
INSERT CongDoan
VALUES('CD100002', N'Khâu dập', 1200, N'0%', '2022-12-12', 'SP100001', 1500)
GO
INSERT CongDoan
VALUES ('CD100003', N'May da', 800, N'0%', '2022-12-12', 'SP100001', 1300)
GO
INSERT CongDoan
VALUES ('CD100004', N'Làm mũi giày', 1300, N'0%', '2022-12-12', 'SP100001', 1313)
GO
INSERT CongDoan
VALUES ('CD100005', N'Trang trí', 999, N'0%', '2022-12-12', 'SP100001', 1500)
GO
INSERT CongDoan
VALUES ('CD100006', N'Lên Khuôn', 1000, N'0%', '2022-12-12', 'SP100002', 2000)
GO
INSERT CongDoan
VALUES ('CD100007', N'Khâu dập', 900, N'0%', '2022-12-12', 'SP100002', 3300)
GO
INSERT CongDoan
VALUES ('CD100008', N'May da', 990, N'0%', '2022-12-12', 'SP100002', 1122)
GO
INSERT CongDoan
VALUES ('CD100009', N'Làm mũi giày', 1000, N'0%', '2022-12-12', 'SP100002', 3123)
GO
INSERT CongDoan
VALUES ('CD100010', N'Trang trí', 1110, N'0%', '2022-12-12', 'SP100002', 1234)
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
VALUES ('PC100001', 'CN100001', 'NV100001', 'CD100001' ,GETDATE(), N'Đêm'),
 ('PC100002', 'CN100002', 'NV100002', 'CD100002' ,GETDATE(), N'Sáng'),
 ('PC100003', 'CN100003', 'NV100001', 'CD100001' ,GETDATE(), N'Chiều'),
 ('PC100004', 'CN100004', 'NV100002', 'CD100002' ,GETDATE(), N'Đêm'),
 ('PC100005', 'CN100005', 'NV100001', 'CD100003' ,GETDATE(), N'Sáng'),
 ('PC100006', 'CN100006', 'NV100002', 'CD100004' ,GETDATE(), N'Đêm'),
 ('PC100007', 'CN100007', 'NV100001', 'CD100001' ,GETDATE(), N'Sáng'),
 ('PC100008', 'CN100008', 'NV100002', 'CD100002' ,GETDATE(), N'Đêm'),
 ('PC100009', 'CN100009', 'NV100001', 'CD100003' ,GETDATE(), N'Chiều'),
 ('PC100010', 'CN100010', 'NV100002', 'CD100001' ,GETDATE(), N'Sáng')
GO
CREATE TABLE ChamCongCongNhan(
	maPhanCong char(8)  COLLATE SQL_Latin1_General_CP1_CS_AS not null references PhanCongCongNhan(maPhanCong) ON DELETE CASCADE,
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
VALUES ('PC100001', N'Đêm', GETDATE(), 24,  N'Đi Làm',  '19h00'),
('PC100002', N'Sáng' ,GETDATE(), 32, N'Đi Trễ', '8h30'),
('PC100003',  N'Chiều', GETDATE(), 15,  N'Đi Làm',  '12h00'),
('PC100004', N'Đêm', GETDATE(), 55,  N'Đi Làm',  '19h00'),
('PC100005', N'Đêm', GETDATE(), 33, N'Đi Làm',  '19h00'),
('PC100006', N'Sáng', GETDATE(), 12, N'Đi Trễ',  '8h25'),
('PC100007', N'Sáng', GETDATE(), 43,  N'Đi Làm',  '8h00'),
('PC100008', N'Sáng', GETDATE(), 54, N'Đi Làm',  '8h00'),
('PC100009', N'Sáng', GETDATE(), 24,  N'Đi Trễ',  '8h30'),
('PC100010', N'Sáng', GETDATE(), 54,  N'Đi Làm',  '8h00')
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
 



