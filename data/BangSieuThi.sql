
CREATE DATABASE QLSieuThi
GO
USE QLSieuThi


CREATE TABLE SanPham
(
	maSanPham NVARCHAR(100) PRIMARY KEY,
	tenSanPham NVARCHAR(150) NOT NULL,
	giaTien FLOAT NOT NULL CHECK (giaTien > 0),
	trangThai BIT DEFAULT 1,
)

CREATE TABLE KhachHang 
(
	maKhachHang NVARCHAR(100) PRIMARY KEY,
	hoTen NVARCHAR(100) NOT NULL, 
	soDienThoai NVARCHAR(15)
)

CREATE TABLE NhanVien
(
	maNhanVien NVARCHAR(100) PRIMARY KEY,
	hoTen NVARCHAR(100) NOT NULL,
	username NVARCHAR(100) UNIQUE NOT NULL,
	password NVARCHAR(100) NOT NULL,
	role NVARCHAR(20) DEFAULT 'staff',
	soDienThoai NVARCHAR(15),
	diaChi NVARCHAR(150),
	email NVARCHAR(100),
	tienLuong FLOAT,
	ngaySinh DATE,
	gioiTinh NVARCHAR(10)
)

CREATE TABLE HoaDon
(
	maHoaDon NVARCHAR(100) PRIMARY KEY,
	ngayTao DATETIME DEFAULT GETDATE(),

	maKhachHang NVARCHAR(100),
	maNhanVien NVARCHAR(100),
	tongTien FLOAT NOT NULL,

	FOREIGN KEY (maKhachHang) REFERENCES KhachHang(maKhachHang),
	FOREIGN KEY (maNhanVien) REFERENCES NhanVien(maNhanVien)
)

CREATE TABLE ChiTietHoaDon
(
	maHoaDon NVARCHAR(100),
	maSanPham NVARCHAR(100),
	soLuong INT CHECK (soLuong > 0),
	giaTien FLOAT,
	
	PRIMARY KEY (maHoaDon, maSanPham),
	FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
	FOREIGN KEY (maSanPham) REFERENCES SanPham(maSanPham)
)

-- 1. Chèn dữ liệu bảng SanPham (15 sản phẩm Circle K)
INSERT INTO SanPham (maSanPham, tenSanPham, giaTien, trangThai) VALUES
('SP001', N'Mì trộn Omachi xốt bò hầm', 25000, 1),
('SP002', N'Bánh bao nhân thịt', 15000, 1),
('SP003', N'Xúc xích nướng', 12000, 1),
('SP004', N'Cơm nắm cá ngừ', 25000, 1),
('SP005', N'Sandwich gà teriyaki', 29000, 1),
('SP006', N'Nước suối Aquafina 500ml', 8000, 1),
('SP007', N'Coca Cola lon 330ml', 12000, 1),
('SP008', N'Trà xanh C2 455ml', 10000, 1),
('SP009', N'Cà phê G7 hộp', 35000, 1),
('SP010', N'Kem ốc quế Merino', 15000, 1),
('SP011', N'Snack Oishi tôm cay', 10000, 1),
('SP012', N'Bánh mì que pate', 18000, 1),
('SP013', N'Sữa tươi TH True Milk 180ml', 9000, 1),
('SP014', N'Khăn giấy Pulppy gói', 5000, 1),
('SP015', N'Mì ly Modern lẩu Thái', 15000, 1);

-- 2. Chèn dữ liệu bảng KhachHang (Họ tên đầy đủ)
INSERT INTO KhachHang (maKhachHang, hoTen, soDienThoai) VALUES
('KH001', N'Phạm Minh Hào', '0901234567'),
('KH002', N'Lê Quang Huy', '0912345678'),
('KH003', N'Nguyễn Hoàng Nam', '0988888888'),
('KH004', N'Trần Thị Tuyết Mai', '0977777777'),
('KH005', N'Lê Văn Quốc Anh', '0966666666'),
('KH006', N'Hoàng Thị Ngọc Diệp', '0955555555'),
('KH007', N'Ngô Văn Minh Tú', '0944444444'),
('KH008', N'Lý Thị Thanh Thảo', '0933333333'),
('KH009', N'Vũ Văn Tiến Dũng', '0922222222'),
('KH010', N'Đỗ Thị Thúy Hằng', '0911111111');

-- 3. Chèn dữ liệu bảng NhanVien (Role Tiếng Việt: Quản lý/Nhân viên)
INSERT INTO NhanVien (maNhanVien, hoTen, username, password, role, soDienThoai, diaChi, email, tienLuong, ngaySinh, gioiTinh) VALUES
('NV001', N'Nguyễn Tất Thành Toàn', 'admin', '123', N'Quản lý', '0900000001', N'Gò Vấp, HCM', 'toan@gmail.com', 15000000, '1990-01-01', N'Nam'),
('NV002', N'Trần Thị Linh Chi', 'chi123', '123', N'Nhân viên', '0900000002', N'Củ Chi, HCM', 'chi@gmail.com', 7000000, '2000-05-15', N'Nữ'),
('NV003', N'Phan Hoàng Nam', 'nam456', '123', N'Nhân viên', '0900000003', N'Quận 12, HCM', 'nam@gmail.com', 7000000, '1998-10-20', N'Nam'),
('NV004', N'Đặng Thị Ngọc Lan', 'lan789', '123', N'Nhân viên', '0900000004', N'Hóc Môn, HCM', 'lan@gmail.com', 7000000, '2002-02-12', N'Nữ'),
('NV005', N'Bùi Xuân Hùng', 'hung12', '123', N'Nhân viên', '0900000005', N'Gò Vấp, HCM', 'hung@gmail.com', 7200000, '1995-12-30', N'Nam');

-- 4. Chèn dữ liệu bảng HoaDon (Cập nhật lên 20 hóa đơn)
INSERT INTO HoaDon (maHoaDon, ngayTao, maKhachHang, maNhanVien, tongTien) VALUES
('HD001', '2026-04-28 08:00:00', 'KH001', 'NV002', 75000),  -- (2*25k + 1*25k)
('HD002', '2026-04-28 08:15:00', 'KH002', 'NV003', 45000),  -- (1*45k)
('HD003', '2026-04-28 08:30:00', 'KH003', 'NV002', 29000),  -- (1*29k)
('HD004', '2026-04-28 09:00:00', 'KH004', 'NV004', 96000),  -- (3*32k)
('HD005', '2026-04-28 09:30:00', 'KH005', 'NV005', 40000),  -- (1*40k)
('HD006', '2026-04-28 10:00:00', 'KH006', 'NV002', 70000),  -- (2*35k)
('HD007', '2026-04-28 10:15:00', 'KH007', 'NV003', 38000),  -- (1*38k)
('HD008', '2026-04-28 10:45:00', 'KH008', 'NV003', 48000),  -- (1*48k)
('HD009', '2026-04-28 11:00:00', 'KH009', 'NV004', 58000),  -- (2*29k)
('HD010', '2026-04-28 11:30:00', 'KH010', 'NV005', 40000),  -- (2*20k)
('HD011', '2026-04-28 12:00:00', 'KH001', 'NV002', 35000),  -- (1*35k)
('HD012', '2026-04-28 13:00:00', 'KH002', 'NV003', 30000),  -- (1*30k)
('HD013', '2026-04-28 14:00:00', 'KH003', 'NV004', 45000),  -- (1*45k)
('HD014', '2026-04-28 15:00:00', 'KH004', 'NV005', 50000),  -- (2*25k)
('HD015', '2026-04-28 16:00:00', 'KH005', 'NV002', 35000),  -- (1*35k)
('HD016', '2026-04-28 17:00:00', 'KH006', 'NV003', 90000),  -- (2*45k)
('HD017', '2026-04-28 18:00:00', 'KH007', 'NV004', 35000),  -- (1*35k)
('HD018', '2026-04-28 19:00:00', 'KH008', 'NV005', 90000),  -- (3*30k)
('HD019', '2026-04-28 20:00:00', 'KH009', 'NV002', 32000),  -- (1*32k)
('HD020', '2026-04-28 21:00:00', 'KH010', 'NV003', 29000);  -- (1*29k)

-- 6. Chèn dữ liệu bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (maHoaDon, maSanPham, soLuong, giaTien) VALUES
('HD001', 'SP001', 2, 25000), -- 50,000
('HD001', 'SP008', 1, 25000), -- 25,000 -> Tổng HD001: 75,000
('HD002', 'SP004', 1, 45000), -- Tổng HD002: 45,000
('HD003', 'SP002', 1, 29000), -- Tổng HD003: 29,000
('HD004', 'SP003', 3, 32000), -- Tổng HD004: 96,000
('HD005', 'SP006', 1, 40000), -- Tổng HD005: 40,000
('HD006', 'SP005', 2, 35000), -- Tổng HD006: 70,000
('HD007', 'SP011', 1, 38000), -- Tổng HD007: 38,000
('HD008', 'SP012', 1, 48000), -- Tổng HD008: 48,000
('HD009', 'SP002', 2, 29000), -- Tổng HD009: 58,000
('HD010', 'SP015', 2, 20000), -- Tổng HD010: 40,000
('HD011', 'SP014', 1, 35000), -- Tổng HD011: 35,000
('HD012', 'SP013', 1, 30000), -- Tổng HD012: 30,000
('HD013', 'SP007', 1, 45000), -- Tổng HD013: 45,000
('HD014', 'SP001', 2, 25000), -- Tổng HD014: 50,000
('HD015', 'SP010', 1, 35000), -- Tổng HD015: 35,000
('HD016', 'SP004', 2, 45000), -- Tổng HD016: 90,000
('HD017', 'SP005', 1, 35000), -- Tổng HD017: 35,000
('HD018', 'SP009', 3, 30000), -- Tổng HD018: 90,000
('HD019', 'SP003', 1, 32000), -- Tổng HD019: 32,000
('HD020', 'SP002', 1, 29000); -- Tổng HD020: 29,000

-- Thêm cột anhSanPham vào bảng đã có
ALTER TABLE SanPham
ADD anhSanPham NVARCHAR(255);

-- Cập nhật đường dẫn ảnh cho từng sản phẩm (khớp với tên sản phẩm thực tế)
UPDATE SanPham SET anhSanPham = 'imgs/omachi.jpg'                                             WHERE maSanPham = 'SP001';
UPDATE SanPham SET anhSanPham = 'imgs/banhbaonhanthit.jpg'                                    WHERE maSanPham = 'SP002';
UPDATE SanPham SET anhSanPham = 'imgs/xucxichnuong.jpg'                                       WHERE maSanPham = 'SP003';
UPDATE SanPham SET anhSanPham = 'imgs/comnamnamngu.jpg'                                       WHERE maSanPham = 'SP004';
UPDATE SanPham SET anhSanPham = 'imgs/sanwichga.jpg'                                          WHERE maSanPham = 'SP005';
UPDATE SanPham SET anhSanPham = 'imgs/nuoc-tinh-khiet-aquafina-500ml-202407121618240191.jpg'  WHERE maSanPham = 'SP006';
UPDATE SanPham SET anhSanPham = 'imgs/cocacola330.jpg'                                        WHERE maSanPham = 'SP007';
UPDATE SanPham SET anhSanPham = 'imgs/c2.jpg'                                                 WHERE maSanPham = 'SP008';
UPDATE SanPham SET anhSanPham = 'imgs/g7.jpg'                                                 WHERE maSanPham = 'SP009';
UPDATE SanPham SET anhSanPham = 'imgs/kem_oc_que_merino.jpg'                                 WHERE maSanPham = 'SP010';
UPDATE SanPham SET anhSanPham = 'imgs/snack_oishi_tom_cay.jpg'                               WHERE maSanPham = 'SP011';
UPDATE SanPham SET anhSanPham = 'imgs/banhmi_que_pate.jpg'                                   WHERE maSanPham = 'SP012';
UPDATE SanPham SET anhSanPham = 'imgs/sua_tuoi_th_true_milk_180ml.jpg'                       WHERE maSanPham = 'SP013';
UPDATE SanPham SET anhSanPham = 'imgs/khan_giay_pulppy_goi.jpg'                              WHERE maSanPham = 'SP014';
UPDATE SanPham SET anhSanPham = 'imgs/mi_ly_modern_lau_thai.jpg'                             WHERE maSanPham = 'SP015';


INSERT INTO HoaDon (maHoaDon, ngayTao, maKhachHang, maNhanVien, tongTien) VALUES
('HD100', '2026-05-01 08:00:00', 'KH001', 'NV002', 75000),  -- (2*25k + 1*25k)
('HD102', '2026-05-03 08:15:00', 'KH002', 'NV003', 45000),  -- (1*45k)
('HD103', '2026-05-02 08:30:00', 'KH003', 'NV002', 29000)  -- (1*29k)

INSERT INTO ChiTietHoaDon (maHoaDon, maSanPham, soLuong, giaTien) VALUES
('HD100', 'SP001', 2, 25000), -- 50,000
('HD100', 'SP008', 1, 25000), -- 25,000 -> Tổng HD001: 75,000
('HD102', 'SP004', 1, 45000), -- Tổng HD002: 45,000
('HD103', 'SP002', 1, 29000) -- Tổng HD003: 29,000
