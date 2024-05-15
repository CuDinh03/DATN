-- CREATE DATABASE DATN_V1

USE DATN_V1
--Chuc vu
CREATE TABLE chuc_vu(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Danh muc
CREATE TABLE danh_muc(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Chat lieu
CREATE TABLE chat_lieu(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Hinh anh
CREATE TABLE hinh_anh(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Mau sac
CREATE TABLE mau_sac(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--thuong hieu
CREATE TABLE thuong_hieu(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Kich thuoc
CREATE TABLE kich_thuoc(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--San Pham
CREATE TABLE san_pham(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Phuong thuc thanh toan
CREATE TABLE phuong_thuc_thanh_toan(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(50),
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Chi tiet san pham
CREATE TABLE chi_tiet_san_pham(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_san_pham UNIQUEIDENTIFIER,
    id_chat_lieu UNIQUEIDENTIFIER,
    id_mau_sac UNIQUEIDENTIFIER,
    id_danh_muc UNIQUEIDENTIFIER,
    id_kich_thuoc UNIQUEIDENTIFIER,
    id_thuong_hieu UNIQUEIDENTIFIER,
    id_hinh_anh UNIQUEIDENTIFIER,
    so_luong INT,
    gia_nhap DECIMAL(10,3),
    gia_ban DECIMAL(10,3),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Thanh toan
CREATE TABLE thanh_toan(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    id_hoa_don UNIQUEIDENTIFIER,
    id_phuong_thuc UNIQUEIDENTIFIER,
    tien_thanh_toan DECIMAL(10,3),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)


--Hoa Don
CREATE TABLE hoa_don(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_nhan_vien UNIQUEIDENTIFIER,
    id_khach_hang UNIQUEIDENTIFIER,
    id_voucher UNIQUEIDENTIFIER,
    tong_tien DECIMAL(10,3),
    tong_tien_gian DECIMAL(10,3),
    ghi_chu NVARCHAR(50),
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Hoa don chi tiet
CREATE TABLE hoa_don_chi_tiet(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_san_pham UNIQUEIDENTIFIER,
    id_hoa_don UNIQUEIDENTIFIER,
    so_luong INT,
    gia_ban DECIMAL(10,3),
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Khach hang
CREATE TABLE khach_hang(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_tai_khoan UNIQUEIDENTIFIER,
    ten NVARCHAR(50),
    sdt VARCHAR(10),
    email VARCHAR(50),
    gioi_tinh BIT,
    ngay_sinh DATE,
    dia_chi NVARCHAR(MAX),
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Gio hang
CREATE TABLE gio_hang(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_khach_hang UNIQUEIDENTIFIER,
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Gio hang chi tiet
CREATE TABLE gio_hang_chi_tiet(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_gio_hang UNIQUEIDENTIFIER,
    id_san_pham UNIQUEIDENTIFIER,
    so_luong INT,
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Nhan vien
CREATE TABLE nhan_vien(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    id_tai_khoan UNIQUEIDENTIFIER,
    ten NVARCHAR(50),
    sdt VARCHAR(10),
    email VARCHAR(50),
    gioi_tinh BIT,
    ngay_sinh DATE,
    dia_chi NVARCHAR(MAX),
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Tai khoan
CREATE TABLE tai_khoan(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten_dang_nhap VARCHAR(50),
    mat_khau VARCHAR(50),
    id_chuc_vu UNIQUEIDENTIFIER,
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)
--Voucher
CREATE TABLE voucher(
    id UNIQUEIDENTIFIER DEFAULT NEWID() NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten VARCHAR(50),
    loai_giam_gia VARCHAR(50),
    ngay_bat_dau Date,
    ngay_ket_thuc Date,
    gia_tri_giam DECIMAL(10,3),
    gia_tri_toi_thieu DECIMAL(10,3),
    so_luong INT,
    ngay_tao Date,
    ngay_sua Date,
    trang_thai BIT
)

--Bao Cao
CREATE TABLE bao_cao(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    ten NVARCHAR(25),
    mo_ta NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

--Ho tro
CREATE TABLE ho_tro(
    id UNIQUEIDENTIFIER DEFAULT (newid()) NOT NULL PRIMARY KEY,
    ma VARCHAR(10),
    tieu_de NVARCHAR(50),
    noi_dung NVARCHAR(255),
    ngay_tao DATE,
    ngay_sua DATE,
    trang_thai BIT
)

ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_ThuongHieu FOREIGN KEY (id_thuong_hieu) REFERENCES thuong_hieu(ID)
ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_MauSac FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(ID)
ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_ChatLieu FOREIGN KEY (id_chat_lieu) REFERENCES chat_lieu(ID)
ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_KichThuoc FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(ID)
ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_HinhAnh FOREIGN KEY (id_hinh_anh) REFERENCES hinh_anh(ID)
ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_DanhMuc FOREIGN KEY (id_danh_muc) REFERENCES danh_muc(ID)
ALTER TABLE chi_tiet_san_pham ADD CONSTRAINT FK_SanPhamChiTiet_SanPham FOREIGN KEY (id_san_pham) REFERENCES san_pham(ID)

ALTER TABLE tai_khoan ADD CONSTRAINT FK_TaiKhoan_ChucVu FOREIGN KEY (id_chuc_vu) REFERENCES chuc_vu(ID);
ALTER TABLE nhan_vien ADD CONSTRAINT FK_NhanVien_TaiKhoan FOREIGN KEY (id_tai_khoan) REFERENCES tai_khoan(ID);
ALTER TABLE khach_hang ADD CONSTRAINT FK_KhachHang_TaiKhoan FOREIGN KEY (id_tai_khoan) REFERENCES tai_khoan(ID);
ALTER TABLE gio_hang ADD CONSTRAINT FK_GioHang_KhachHang FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(ID);

ALTER TABLE gio_hang_chi_tiet ADD CONSTRAINT FK_GioHangChiTiet_GioHang FOREIGN KEY (id_gio_hang) REFERENCES gio_hang(ID);
ALTER TABLE gio_hang_chi_tiet ADD CONSTRAINT FK_GioHangChiTiet_SanPham FOREIGN KEY (id_san_pham) REFERENCES san_pham(ID);

ALTER TABLE hoa_don ADD CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(ID);
ALTER TABLE hoa_don ADD CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(ID);
ALTER TABLE hoa_don ADD CONSTRAINT FK_HoaDon_Voucher FOREIGN KEY (id_voucher) REFERENCES voucher(ID);

ALTER TABLE hoa_don_chi_tiet ADD CONSTRAINT FK_HoaDonChiTiet_HoaDon FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(ID);
ALTER TABLE hoa_don_chi_tiet ADD CONSTRAINT FK_HoaDonChiTiet_SanPham FOREIGN KEY (id_san_pham) REFERENCES san_pham(ID);


INSERT INTO chuc_vu (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('CV001', N'Quản lý', '2024-04-21', '2024-04-21', 1),
('CV002', N'Nhân viên bán hàng', '2024-04-21', '2024-04-21', 1),
('CV003', N'Kế toán', '2024-04-21', '2024-04-21', 1),
('CV004', N'Nhân viên vận chuyển', '2024-04-21', '2024-04-21', 1),
('CV005', N'Nhân viên kho', '2024-04-21', '2024-04-21', 1);

INSERT INTO danh_muc (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('DM001', N'Thời trang nam', '2024-04-21', '2024-04-21', 1),
('DM002', N'Thời trang nữ', '2024-04-21', '2024-04-21', 1),
('DM003', N'Điện thoại di động', '2024-04-21', '2024-04-21', 1),
('DM004', N'Máy tính xách tay', '2024-04-21', '2024-04-21', 1),
('DM005', N'Đồ gia dụng', '2024-04-21', '2024-04-21', 1)

INSERT INTO chat_lieu (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('CL001', N'Vải cotton', '2024-04-21', '2024-04-21', 1),
('CL002', N'Vải linen', '2024-04-21', '2024-04-21', 1),
('CL003', N'Vải denim', '2024-04-21', '2024-04-21', 1),
('CL004', N'Vải polyester', '2024-04-21', '2024-04-21', 1),
('CL005', N'Vải silk', '2024-04-21', '2024-04-21', 1);

INSERT INTO hinh_anh (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('HA001', 'Image1.jpg', '2024-04-21', '2024-04-21', 1),
('HA002', 'Image2.jpg', '2024-04-21', '2024-04-21', 1),
('HA003', 'Image3.jpg', '2024-04-21', '2024-04-21', 1),
('HA004', 'Image4.jpg', '2024-04-21', '2024-04-21', 1),
('HA005', 'Image5.jpg', '2024-04-21', '2024-04-21', 1);

INSERT INTO mau_sac (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('MS001', N'Đen', '2024-04-21', '2024-04-21', 1),
('MS002', N'Trắng', '2024-04-21', '2024-04-21', 1),
('MS003', N'Đỏ', '2024-04-21', '2024-04-21', 1),
('MS004', N'Xanh lá', '2024-04-21', '2024-04-21', 1),
('MS005', N'Xanh dương', '2024-04-21', '2024-04-21', 1);

INSERT INTO thuong_hieu (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('TH001', N'Adidas', '2024-04-21', '2024-04-21', 1),
('TH002', N'Nike', '2024-04-21', '2024-04-21', 1),
('TH003', N'Gucci', '2024-04-21', '2024-04-21', 1),
('TH004', N'LV', '2024-04-21', '2024-04-21', 1),
('TH005', N'LEVENT', '2024-04-21', '2024-04-21', 1);

INSERT INTO kich_thuoc (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('KT001', 'XS', '2024-04-21', '2024-04-21', 1),
('KT002', 'S', '2024-04-21', '2024-04-21', 1),
('KT003', 'M', '2024-04-21', '2024-04-21', 1),
('KT004', 'L', '2024-04-21', '2024-04-21', 1),
('KT005', 'XL', '2024-04-21', '2024-04-21', 1);


INSERT INTO san_pham (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('SP001', N'Áo thun nam', '2024-04-21', '2024-04-21', 1),
('SP002', N'Quần jean nam', '2024-04-21', '2024-04-21', 1),
('SP003', N'Ao phông', '2024-04-21', '2024-04-21', 1),
('SP004', N'Quần đùi nữ', '2024-04-21', '2024-04-21', 1),
('SP005', N'Quần âu', '2024-04-21', '2024-04-21', 1);


INSERT INTO phuong_thuc_thanh_toan (ma, ten, ngay_tao, ngay_sua, trang_thai)
VALUES 
('PTTT001', N'Thanh toán khi nhận hàng', '2024-04-21', '2024-04-21', 1),
('PTTT002', N'Chuyển khoản ngân hàng', '2024-04-21', '2024-04-21', 1),
('PTTT003', N'Thanh toán qua thẻ tín dụng', '2024-04-21', '2024-04-21', 1),
('PTTT004', N'Ví điện tử', '2024-04-21', '2024-04-21', 1),
('PTTT005', N'Trả góp', '2024-04-21', '2024-04-21', 1);