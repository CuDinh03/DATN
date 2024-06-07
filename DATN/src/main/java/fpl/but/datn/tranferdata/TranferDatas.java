package fpl.but.datn.tranferdata;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.entity.*;

import java.util.ArrayList;
import java.util.List;

public class TranferDatas {

    //  ---------- MauSac
    public static MauSacDto convertToMauSacDto(MauSac entity) {
        MauSacDto mauSacDto = new MauSacDto();
        if (entity.getId() != null) mauSacDto.setId(entity.getId());
        if (entity.getMa() != null) mauSacDto.setMa(entity.getMa());
        if (entity.getTen() != null) mauSacDto.setTen(entity.getTen());
        if (entity.getNgaySua() != null) mauSacDto.setNgaySua(entity.getNgaySua());
        if (entity.getNgayTao() != null) mauSacDto.setNgayTao(entity.getNgayTao());
        if (entity.getTrangThai() != null) mauSacDto.setTrangThai(entity.getTrangThai());
        return mauSacDto;
    }

    public static MauSac convertToMauSacEntity(MauSacDto dto) {
        MauSac mauSac = new MauSac();
        if (dto.getId() != null) mauSac.setId(dto.getId());
        if (dto.getMa() != null) mauSac.setMa(dto.getMa());
        if (dto.getTen() != null) mauSac.setTen(dto.getTen());
        if (dto.getNgaySua() != null) mauSac.setNgaySua(dto.getNgaySua());
        if (dto.getNgayTao() != null) mauSac.setNgayTao(dto.getNgayTao());
        if (dto.getTrangThai() != null) mauSac.setTrangThai(dto.getTrangThai());
        return mauSac;
    }

    public static List<MauSacDto> convertToListMauSacDto(List<MauSac> lstMauSac) {
        List<MauSacDto> lstMauSacDto = new ArrayList<>();
        for (MauSac mauSac : lstMauSac) {
            lstMauSacDto.add(convertToMauSacDto(mauSac));
        }
        return lstMauSacDto;
    }

    // ---------- Kich Thuoc
    public static KichThuocDto convertKichThuocToDto(KichThuoc entity) {
        KichThuocDto kichThuocDto = new KichThuocDto();
        if (entity.getId() != null) kichThuocDto.setId(entity.getId());
        if (entity.getMa() != null) kichThuocDto.setMa(entity.getMa());
        if (entity.getTen() != null) kichThuocDto.setTen(entity.getTen());
        if (entity.getNgaySua() != null) kichThuocDto.setNgaySua(entity.getNgaySua());
        if (entity.getNgayTao() != null) kichThuocDto.setNgayTao(entity.getNgayTao());
        if (entity.getTrangThai() != null) kichThuocDto.setTrangThai(entity.getTrangThai());
        return kichThuocDto;
    }

    public static KichThuoc convertToKichThuocEntity(KichThuocDto dto) {
        KichThuoc kichThuoc = new KichThuoc();
        if (dto.getId() != null) kichThuoc.setId(dto.getId());
        if (dto.getMa() != null) kichThuoc.setMa(dto.getMa());
        if (dto.getTen() != null) kichThuoc.setTen(dto.getTen());
        if (dto.getNgaySua() != null) kichThuoc.setNgaySua(dto.getNgaySua());
        if (dto.getNgayTao() != null) kichThuoc.setNgayTao(dto.getNgayTao());
        if (dto.getTrangThai() != null) kichThuoc.setTrangThai(dto.getTrangThai());
        return kichThuoc;
    }

    public static List<KichThuocDto> convertToListkichThuocDto(List<KichThuoc> lstKichThuoc) {
        List<KichThuocDto> lstkichThuocDto = new ArrayList<>();
        for (KichThuoc kichThuoc : lstKichThuoc) {
            lstkichThuocDto.add(convertKichThuocToDto(kichThuoc));
        }
        return lstkichThuocDto;
    }

    // ---------- NhanVien
    public static NhanVienDto convertNhanVienToDto(NhanVien entity) {
        NhanVienDto nhanVienDto = new NhanVienDto();
        if (entity.getId() != null) nhanVienDto.setId(entity.getId());
        if (entity.getMa() != null) nhanVienDto.setMa(entity.getMa());
        if (entity.getTen() != null) nhanVienDto.setTen(entity.getTen());
        if (entity.getEmail() != null) nhanVienDto.setEmail(entity.getEmail());
        if (entity.getSdt() != null) nhanVienDto.setSdt(entity.getSdt());
        if (entity.getGioiTinh() != null) nhanVienDto.setGioiTinh(entity.getGioiTinh());
        if (entity.getNgaySinh() != null) nhanVienDto.setNgaySinh(entity.getNgaySinh());
        if (entity.getDiaChi() != null) nhanVienDto.setDiaChi(entity.getDiaChi());
        if (entity.getNgaySua() != null) nhanVienDto.setNgaySua(entity.getNgaySua());
        if (entity.getNgayTao() != null) nhanVienDto.setNgayTao(entity.getNgayTao());
        if (entity.getTrangThai() != null) nhanVienDto.setTrangThai(entity.getTrangThai());
        if (entity.getTaiKhoan() != null) nhanVienDto.setIdTaiKhoan(entity.getTaiKhoan());
        return nhanVienDto;
    }

    public static NhanVien convertToNhanVienEntity(NhanVienDto dto) {
        NhanVien nhanVien = new NhanVien();
        if (dto.getId() != null) nhanVien.setId(dto.getId());
        if (dto.getMa() != null) nhanVien.setMa(dto.getMa());
        if (dto.getTen() != null) nhanVien.setTen(dto.getTen());
        if (dto.getEmail() != null) nhanVien.setEmail(dto.getEmail());
        if (dto.getSdt() != null) nhanVien.setSdt(dto.getSdt());
        if (dto.getGioiTinh() != null) nhanVien.setGioiTinh(dto.getGioiTinh());
        if (dto.getNgaySinh() != null) nhanVien.setNgaySinh(dto.getNgaySinh());
        if (dto.getDiaChi() != null) nhanVien.setDiaChi(dto.getDiaChi());
        if (dto.getNgaySua() != null) nhanVien.setNgaySua(dto.getNgaySua());
        if (dto.getNgayTao() != null) nhanVien.setNgayTao(dto.getNgayTao());
        if (dto.getTrangThai() != null) nhanVien.setTrangThai(dto.getTrangThai());
        if (dto.getIdTaiKhoan() != null) nhanVien.setTaiKhoan(dto.getIdTaiKhoan());
        return nhanVien;
    }

    public static List<NhanVienDto> convertToListNhanVienDto(List<NhanVien> lstNhanVien) {
        List<NhanVienDto> lstNhanVienDto = new ArrayList<>();
        for (NhanVien nhanVien : lstNhanVien) {
            lstNhanVienDto.add(convertNhanVienToDto(nhanVien));
        }
        return lstNhanVienDto;
    }


    // ---------- KhachHang
    public static KhachHangDto convertToDto(KhachHang entity) {
        KhachHangDto khachHangDto = new KhachHangDto();
        if (entity.getId() != null) khachHangDto.setId(entity.getId());
        if (entity.getMa() != null) khachHangDto.setMa(entity.getMa());
        if (entity.getTen() != null) khachHangDto.setTen(entity.getTen());
        if (entity.getEmail() != null) khachHangDto.setEmail(entity.getEmail());
        if (entity.getSdt() != null) khachHangDto.setSdt(entity.getSdt());
        if (entity.getGioiTinh() != null) khachHangDto.setGioiTinh(entity.getGioiTinh());
        if (entity.getNgaySinh() != null) khachHangDto.setNgaySinh(entity.getNgaySinh());
        if (entity.getDiaChi() != null) khachHangDto.setDiaChi(entity.getDiaChi());
        if (entity.getNgaySua() != null) khachHangDto.setNgaySua(entity.getNgaySua());
        if (entity.getNgayTao() != null) khachHangDto.setNgayTao(entity.getNgayTao());
        if (entity.getTrangThai() != null) khachHangDto.setTrangThai(entity.getTrangThai());
        if (entity.getTaiKhoan() != null) khachHangDto.setIdTaiKhoan(entity.getTaiKhoan());
        return khachHangDto;
    }

    public static KhachHang convertToEntity(KhachHangDto dto) {
        KhachHang khachHang = new KhachHang();
        if (dto.getId() != null) khachHang.setId(dto.getId());
        if (dto.getMa() != null) khachHang.setMa(dto.getMa());
        if (dto.getTen() != null) khachHang.setTen(dto.getTen());
        if (dto.getEmail() != null) khachHang.setEmail(dto.getEmail());
        if (dto.getSdt() != null) khachHang.setSdt(dto.getSdt());
        if (dto.getGioiTinh() != null) khachHang.setGioiTinh(dto.getGioiTinh());
        if (dto.getNgaySinh() != null) khachHang.setNgaySinh(dto.getNgaySinh());
        if (dto.getDiaChi() != null) khachHang.setDiaChi(dto.getDiaChi());
        if (dto.getNgaySua() != null) khachHang.setNgaySua(dto.getNgaySua());
        if (dto.getNgayTao() != null) khachHang.setNgayTao(dto.getNgayTao());
        if (dto.getTrangThai() != null) khachHang.setTrangThai(dto.getTrangThai());
        if (dto.getIdTaiKhoan() != null) khachHang.setTaiKhoan(dto.getIdTaiKhoan());
        return khachHang;
    }

    public static List<KhachHangDto> convertToListDto(List<KhachHang> lstKhachHang) {
        List<KhachHangDto> lstKhachHangDto= new ArrayList<>();
        for (KhachHang khachHang : lstKhachHang) {
            lstKhachHangDto.add(convertToDto(khachHang));
        }
        return lstKhachHangDto;
    }

    // ---------- Chuc vu
    public static ChucVuDto convertToDto(ChucVu entity) {
        ChucVuDto dto = new ChucVuDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static ChucVu convertToEntity(ChucVuDto dto) {
        ChucVu entity = new ChucVu();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<ChucVuDto> convertListChucVuToDto(List<ChucVu> entityList) {
        List<ChucVuDto> dtoList = new ArrayList<>();
        for (ChucVu entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- PhuongThucThanhToan
    public static PhuongThucThanhToanDto convertPTThanhToanToDto(PhuongThucThanhToan entity) {
        PhuongThucThanhToanDto dto = new PhuongThucThanhToanDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static PhuongThucThanhToan convertPPThanhToanToEntity(PhuongThucThanhToanDto dto) {
        PhuongThucThanhToan entity = new PhuongThucThanhToan();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<PhuongThucThanhToanDto> convertListPTThanhToanToDto(List<PhuongThucThanhToan> entityList) {
        List<PhuongThucThanhToanDto> dtoList = new ArrayList<>();
        for (PhuongThucThanhToan entity : entityList) {
            dtoList.add(convertPTThanhToanToDto(entity));
        }
        return dtoList;
    }

    // -------- Tai Khoan
    public static TaiKhoanDto convertToDto(TaiKhoan entity) {
        TaiKhoanDto dto = new TaiKhoanDto();
        if (entity.getId() != null) dto.setId(entity.getId());

        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getTenDangNhap() != null) dto.setTenDangNhap(entity.getTenDangNhap());

        if (entity.getMatKhau() != null) dto.setMatKhau(entity.getMatKhau());

        if (entity.getIdChucVu() != null) dto.setIdChucVu(entity.getIdChucVu());

        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());

        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());

        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());

        return dto;
    }

    public static TaiKhoan convertToEntity(TaiKhoanDto dto) {
        TaiKhoan entity = new TaiKhoan();
        if (dto.getId() != null) entity.setId(dto.getId());

        if (dto.getMa() != null) entity.setMa(dto.getMa());

        if (dto.getTenDangNhap() != null) entity.setTenDangNhap(dto.getTenDangNhap());

        if (dto.getMatKhau() != null) entity.setMatKhau(dto.getMatKhau());

        if (dto.getIdChucVu() != null) entity.setIdChucVu(dto.getIdChucVu());

        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());

        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());

        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<TaiKhoanDto> convertListTaiKhoanToDto(List<TaiKhoan> entityList) {
        List<TaiKhoanDto> dtoList = new ArrayList<>();
        for (TaiKhoan entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- danh muc
    public static DanhMucDto convertToDto(DanhMuc entity) {
        DanhMucDto dto = new DanhMucDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static DanhMuc convertToEntity(DanhMucDto dto) {
        DanhMuc entity = new DanhMuc();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<DanhMucDto> convertListDanhMucToDto(List<DanhMuc> entityList) {
        List<DanhMucDto> dtoList = new ArrayList<>();
        for (DanhMuc entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- chat lieu
    public static ChatLieuDto convertToDto(ChatLieu entity) {
        ChatLieuDto dto = new ChatLieuDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static ChatLieu convertToEntity(ChatLieuDto dto) {
        ChatLieu entity = new ChatLieu();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<ChatLieuDto> convertListChatLieuToDto(List<ChatLieu> entityList) {
        List<ChatLieuDto> dtoList = new ArrayList<>();
        for (ChatLieu entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- hinh anh
    public static HinhAnhDto convertToDto(HinhAnh entity) {
        HinhAnhDto dto = new HinhAnhDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getUrl() != null) dto.setUrl(entity.getUrl());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static HinhAnh convertToEntity(HinhAnhDto dto) {
        HinhAnh entity = new HinhAnh();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getUrl() != null) entity.setUrl(dto.getUrl());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<HinhAnhDto> convertListHinhAnhToDto(List<HinhAnh> entityList) {
        List<HinhAnhDto> dtoList = new ArrayList<>();
        for (HinhAnh entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }


    // ---------- mau sac
    public static MauSacDto convertToDto(MauSac entity) {
        MauSacDto dto = new MauSacDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static MauSac convertToEntity(MauSacDto dto) {
        MauSac entity = new MauSac();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<MauSacDto> convertListMauSacToDto(List<MauSac> entityList) {
        List<MauSacDto> dtoList = new ArrayList<>();
        for (MauSac entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- san pham
    public static SanPhamDto convertToDto(SanPham entity){
        SanPhamDto dto = new SanPhamDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static SanPham convertToEntity(SanPhamDto dto){
        SanPham entity = new SanPham();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<SanPhamDto> convertListSanPhamToDto(List<SanPham> entityList) {
        List<SanPhamDto> dtoList = new ArrayList<>();
        for (SanPham entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    //---------- thuong hieu
    public static ThuongHieuDto convertToDto(ThuongHieu entity){
        ThuongHieuDto dto = new ThuongHieuDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static ThuongHieu convertToEntity(ThuongHieuDto dto){
        ThuongHieu entity = new ThuongHieu();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<ThuongHieuDto> convertListThuongHieuToDto(List<ThuongHieu> entityList) {
        List<ThuongHieuDto> dtoList = new ArrayList<>();
        for (ThuongHieu entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- voucher
    public static VoucherDto convertToDto(Voucher entity){
        VoucherDto dto = new VoucherDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getGiaTriToiThieu() != null) dto.setGiaTriToiThieu(entity.getGiaTriToiThieu());
        if (entity.getSoLuong() != null) dto.setSoLuong(entity.getSoLuong());
        if (entity.getGiaTriGiam() != null) dto.setGiaTriGiam(entity.getGiaTriGiam());
        if (entity.getNgayBatDau() != null) dto.setNgayBatDau(entity.getNgayBatDau());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static Voucher convertToEntity(VoucherDto dto){
        Voucher entity = new Voucher();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getGiaTriToiThieu() != null) entity.setGiaTriToiThieu(dto.getGiaTriToiThieu());
        if (dto.getSoLuong() != null) entity.setSoLuong(dto.getSoLuong());
        if (dto.getGiaTriGiam() != null) entity.setGiaTriGiam(dto.getGiaTriGiam());
        if (dto.getNgayBatDau() != null) entity.setNgayBatDau(dto.getNgayBatDau());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<VoucherDto> convertListVoucherToDto(List<Voucher> entityList) {
        List<VoucherDto> dtoList = new ArrayList<>();
        for (Voucher entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- bao cao
    public static BaoCaoDto convertToDto(BaoCao entity){
        BaoCaoDto dto = new BaoCaoDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setTen(entity.getMa());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMoTa() != null) dto.setMoTa(entity.getMoTa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static BaoCao convertToEntity(BaoCaoDto dto){
        BaoCao entity = new BaoCao();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getMoTa() != null) entity.setMoTa(dto.getMoTa());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<BaoCaoDto> convertListBaoCaoToDto(List<BaoCao> entityList) {
        List<BaoCaoDto> dtoList = new ArrayList<>();
        for (BaoCao entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- chi tiet san pham
    public static ChiTietSanPhamDto convertToDto(ChiTietSanPham entity){
        ChiTietSanPhamDto dto = new ChiTietSanPhamDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getSanPham() != null) dto.setSanPham(entity.getSanPham());
        if (entity.getHinhAnh() != null) dto.setHinhAnh(entity.getHinhAnh());
        if (entity.getThuongHieu() != null) dto.setThuongHieu(entity.getThuongHieu());
        if (entity.getChatLieu() != null) dto.setChatLieu(entity.getChatLieu());
        if (entity.getDanhMuc() != null) dto.setDanhMuc(entity.getDanhMuc());
        if (entity.getKichThuoc() != null) dto.setKichThuoc(entity.getKichThuoc());
        if (entity.getMauSac() != null) dto.setMauSac(entity.getMauSac());
        if (entity.getSoLuong() != null) dto.setSoLuong(entity.getSoLuong());
        if (entity.getGiaNhap() != null) dto.setGiaNhap(entity.getGiaNhap());
        if (entity.getGiaBan() != null) dto.setGiaBan(entity.getGiaBan());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgayNhap() != null) dto.setNgayNhap(entity.getNgayNhap());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static ChiTietSanPham convertToEntity(ChiTietSanPhamDto dto){
        ChiTietSanPham entity = new ChiTietSanPham();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getSanPham() != null) entity.setSanPham(dto.getSanPham());
        if (dto.getHinhAnh() != null) entity.setHinhAnh(dto.getHinhAnh());
        if (dto.getThuongHieu() != null) entity.setThuongHieu(dto.getThuongHieu());
        if (dto.getChatLieu() != null) entity.setChatLieu(dto.getChatLieu());
        if (dto.getDanhMuc() != null) entity.setDanhMuc(dto.getDanhMuc());
        if (dto.getKichThuoc() != null) entity.setKichThuoc(dto.getKichThuoc());
        if (dto.getMauSac() != null) entity.setMauSac(dto.getMauSac());
        if (dto.getSoLuong() != null) entity.setSoLuong(dto.getSoLuong());
        if (dto.getGiaNhap() != null) entity.setGiaNhap(dto.getGiaNhap());
        if (dto.getGiaBan() != null) entity.setGiaBan(dto.getGiaBan());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgayNhap() != null) entity.setNgayNhap(dto.getNgayNhap());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<ChiTietSanPhamDto> convertListChiTietSanPhamToDto(List<ChiTietSanPham> entityList) {
        List<ChiTietSanPhamDto> dtoList = new ArrayList<>();
        for (ChiTietSanPham entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- hoa don

    public static HoaDonDto convertToDto(HoaDon entity){
        HoaDonDto dto = new HoaDonDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNguoiDung() != null) dto.setNhanVien(entity.getNguoiDung());
        if (entity.getKhachHang() != null) dto.setKhachHang(entity.getKhachHang());
        if (entity.getTongTien() != null) dto.setTongTien(entity.getTongTien());
        if (entity.getTongTienGiam() != null) dto.setTongTienGiam(entity.getTongTienGiam());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static HoaDon convertToEntity(HoaDonDto dto){
        HoaDon entity = new HoaDon();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getNhanVien() != null) entity.setNguoiDung(dto.getNhanVien());
        if (dto.getKhachHang() != null) entity.setKhachHang(dto.getKhachHang());
        if (dto.getTongTien() != null) entity.setTongTien(dto.getTongTien());
        if (dto.getTongTienGiam() != null) entity.setTongTienGiam(dto.getTongTienGiam());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<HoaDonDto> convertListHoaDonToDto(List<HoaDon> entityList) {
        List<HoaDonDto> dtoList = new ArrayList<>();
        for (HoaDon entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    // ---------- hoa don chi tiet
    public static HoaDonChiTietDto convertToDto(HoaDonChiTiet entity){
        HoaDonChiTietDto dto = new HoaDonChiTietDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getHoaDon() != null) dto.setHoaDon(entity.getHoaDon());
        if (entity.getChiTietSanPham()!= null) dto.setChiTietSanPham(entity.getChiTietSanPham());
        if (entity.getSoLuong() != null) dto.setSoLuong(entity.getSoLuong());
        if (entity.getGiaBan() != null) dto.setGiaBan(entity.getGiaBan());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static HoaDonChiTiet convertToEntity(HoaDonChiTietDto dto){
        HoaDonChiTiet entity = new HoaDonChiTiet();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getHoaDon() != null) entity.setHoaDon(dto.getHoaDon());
        if (dto.getChiTietSanPham() != null) entity.setChiTietSanPham(dto.getChiTietSanPham());
        if (dto.getSoLuong() != null) entity.setSoLuong(dto.getSoLuong());
        if (dto.getGiaBan() != null) entity.setGiaBan(dto.getGiaBan());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<HoaDonChiTietDto> convertListHoaDonChiTietToDto(List<HoaDonChiTiet> entityList) {
        List<HoaDonChiTietDto> dtoList = new ArrayList<>();
        for (HoaDonChiTiet entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }
}