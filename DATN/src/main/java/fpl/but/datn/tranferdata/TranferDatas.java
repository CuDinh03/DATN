package fpl.but.datn.tranferdata;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.entity.*;
import fpl.but.datn.repository.KhachHangRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

public class TranferDatas {


    static KhachHangRepository khachHangRepository = new KhachHangRepository() {
        @Override
        public Page<KhachHang> findAllPage(Pageable pageable) {
            return null;
        }

        @Override
        public Optional<KhachHang> getKhachHangBySdt(String sdt) {
            return Optional.empty();
        }

        @Override
        public KhachHang findKHByTenDangNhap(String tenDangNhap) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends KhachHang> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends KhachHang> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<KhachHang> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public KhachHang getOne(UUID uuid) {
            return null;
        }

        @Override
        public KhachHang getById(UUID uuid) {
            return null;
        }

        @Override
        public KhachHang getReferenceById(UUID uuid) {
            return null;
        }

        @Override
        public <S extends KhachHang> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends KhachHang> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends KhachHang> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public List<KhachHang> findAll() {
            return null;
        }

        @Override
        public List<KhachHang> findAllById(Iterable<UUID> uuids) {
            return null;
        }

        @Override
        public <S extends KhachHang> S save(S entity) {
            return null;
        }

        @Override
        public Optional<KhachHang> findById(UUID uuid) {
            return Optional.empty();
        }

        @Override
        public boolean existsById(UUID uuid) {
            return false;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(UUID uuid) {

        }

        @Override
        public void delete(KhachHang entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends UUID> uuids) {

        }

        @Override
        public void deleteAll(Iterable<? extends KhachHang> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public List<KhachHang> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<KhachHang> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public <S extends KhachHang> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends KhachHang> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends KhachHang> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends KhachHang> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends KhachHang, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }
    };
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

    //danh muc
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

    //chat lieu
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

    // hinh anh
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

    // Kich thuoc
    public static KichThuocDto convertToDto(KichThuoc entity) {
        KichThuocDto dto = new KichThuocDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static KichThuoc convertToEntity(KichThuocDto dto) {
        KichThuoc entity = new KichThuoc();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }

    public static List<KichThuocDto> convertListKichThuocToDto(List<KichThuoc> entityList) {
        List<KichThuocDto> dtoList = new ArrayList<>();
        for (KichThuoc entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    //mau sac

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

    //san pham
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

    //thuong hieu
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

    //voucher
    public static VoucherDto convertToDto(Voucher entity) {
        VoucherDto dto = new VoucherDto();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getTen() != null) {
            dto.setTen(entity.getTen());
        }
        if (entity.getMa() != null) {
            dto.setMa(entity.getMa());
        }
        if (entity.getLoaiGiamGia() != null) {
            dto.setLoaiGiamGia(entity.getLoaiGiamGia());
        }
        if (entity.getNgayBatDau() != null) {
            dto.setNgayBatDau(entity.getNgayBatDau());
        }
        if (entity.getNgayKetThuc() != null) {
            dto.setNgayKetThuc(entity.getNgayKetThuc());
        }
        if (entity.getGiaTriGiam() != null) {
            dto.setGiaTriGiam(entity.getGiaTriGiam());
        }
        if (entity.getGiaTriToiThieu() != null) {
            dto.setGiaTriToiThieu(entity.getGiaTriToiThieu());
        }
        if (entity.getSoLuong() != null) {
            dto.setSoLuong(entity.getSoLuong());
        }
        if (entity.getNgayTao() != null) {
            dto.setNgayTao(entity.getNgayTao());
        }
        if (entity.getNgaySua() != null) {
            dto.setNgaySua(entity.getNgaySua());
        }
        if (entity.getTrangThai() != null) {
            dto.setTrangThai(entity.getTrangThai());
        }
        return dto;
    }

    public static Voucher convertToEntity(VoucherDto dto) {
        Voucher entity = new Voucher();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getTen() != null) {
            entity.setTen(dto.getTen());
        }
        if (dto.getMa() != null) {
            entity.setMa(dto.getMa());
        }
        if (dto.getLoaiGiamGia() != null) {
            // Assuming the LoaiGiamGia name is set in the DTO
            entity.setLoaiGiamGia(dto.getLoaiGiamGia());
        }
        if (dto.getNgayBatDau() != null) {
            entity.setNgayBatDau(dto.getNgayBatDau());
        }
        if (dto.getNgayKetThuc() != null) {
            entity.setNgayKetThuc(dto.getNgayKetThuc());
        }
        if (dto.getGiaTriGiam() != null) {
            entity.setGiaTriGiam(dto.getGiaTriGiam());
        }
        if (dto.getGiaTriToiThieu() != null) {
            entity.setGiaTriToiThieu(dto.getGiaTriToiThieu());
        }
        if (dto.getSoLuong() != null) {
            entity.setSoLuong(dto.getSoLuong());
        }
        if (dto.getNgayTao() != null) {
            entity.setNgayTao(dto.getNgayTao());
        }
        if (dto.getNgaySua() != null) {
            entity.setNgaySua(dto.getNgaySua());
        }
        if (dto.getTrangThai() != null) {
            entity.setTrangThai(dto.getTrangThai());
        }
        return entity;
    }

    public static List<VoucherDto> convertListVoucherToDto(List<Voucher> entityList) {
        List<VoucherDto> dtoList = new ArrayList<>();
        for (Voucher entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }


    //bao cao
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

    //chi tiet san pham
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

    //hoa don

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

    //hoa don chi tiet
    public static HoaDonChiTietDto convertToDto(HoaDonChiTiet entity){
        HoaDonChiTietDto dto = new HoaDonChiTietDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getHoaDon() != null) dto.setHoaDon(entity.getHoaDon());
        if (entity.getChiTietSanPham() != null) dto.setChiTietSanPham(entity.getChiTietSanPham());
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
    public static KhachHangDto convertToDto(KhachHang entity) {
        KhachHangDto dto = new KhachHangDto();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getMa() != null) {
            dto.setMa(entity.getMa());
        }
        if (entity.getTen() != null) {
            dto.setTen(entity.getTen());
        }
        if (entity.getTaiKhoan() != null) {
            dto.setTaiKhoan(entity.getTaiKhoan());
        }
        if (entity.getEmail() != null) {
            dto.setEmail(entity.getEmail());
        }
        if (entity.getSdt() != null) {
            dto.setSdt(entity.getSdt());
        }
        if (entity.getGioiTinh() != null) {
            dto.setGioiTinh(entity.getGioiTinh());
        }
        if (entity.getNgaySinh() != null) {
            dto.setNgaySinh(entity.getNgaySinh());
        }
        if (entity.getDiaChi() != null) {
            dto.setDiaChi(entity.getDiaChi());
        }
        if (entity.getNgaySua() != null) {
            dto.setNgaySua(entity.getNgaySua());
        }
        if (entity.getNgayTao() != null) {
            dto.setNgayTao(entity.getNgayTao());
        }
        if (entity.getTrangThai() != null) {
            dto.setTrangThai(entity.getTrangThai());
        }
        return dto;
    }

    // Chuyển đổi từ DTO KhachHangDto sang entity KhachHang
    public static KhachHang convertToEntity(KhachHangDto dto) {
        KhachHang entity = new KhachHang();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getMa() != null) {
            entity.setMa(dto.getMa());
        }
        if (dto.getTen() != null) {
            entity.setTen(dto.getTen());
        }
        if (dto.getTaiKhoan() != null) {
            entity.setTaiKhoan(dto.getTaiKhoan());
        }
        if (dto.getEmail() != null) {
            entity.setEmail(dto.getEmail());
        }
        if (dto.getSdt() != null) {
            entity.setSdt(dto.getSdt());
        }
        if (dto.getGioiTinh() != null) {
            entity.setGioiTinh(dto.getGioiTinh());
        }
        if (dto.getNgaySinh() != null) {
            entity.setNgaySinh(dto.getNgaySinh());
        }
        if (dto.getDiaChi() != null) {
            entity.setDiaChi(dto.getDiaChi());
        }
        if (dto.getNgaySua() != null) {
            entity.setNgaySua(dto.getNgaySua());
        }
        if (dto.getNgayTao() != null) {
            entity.setNgayTao(dto.getNgayTao());
        }
        if (dto.getTrangThai() != null) {
            entity.setTrangThai(dto.getTrangThai());
        }
        return entity;
    }

    // Chuyển đổi danh sách entity KhachHang sang danh sách DTO KhachHangDto
    public static List<KhachHangDto> convertListKhachHangToDto(List<KhachHang> entityList) {
        List<KhachHangDto> dtoList = new ArrayList<>();
        for (KhachHang entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }
    //hoa don gio hang

    public static GioHangHoaDonDto convertToDto(GioHangHoaDon entity) {
        GioHangHoaDonDto dto = new GioHangHoaDonDto();
        if (entity.getId() != null) {
            dto.setId(entity.getId());
        }
        if (entity.getGioHang() != null) {
            dto.setGioHang(entity.getGioHang());
        }
        if (entity.getHoaDon() != null) {
            dto.setHoaDon(entity.getHoaDon());
        }

        return dto;
    }

    // Chuyển đổi từ DTO GioHangHoaDonDto sang entity GioHangHoaDon
    public static GioHangHoaDon convertToEntity(GioHangHoaDonDto dto) {
        GioHangHoaDon entity = new GioHangHoaDon();
        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getGioHang() != null) {
            entity.setGioHang(dto.getGioHang());
        }
        if (dto.getHoaDon() != null) {
            entity.setHoaDon(dto.getHoaDon());
        }
        return entity;
    }

    // Chuyển đổi danh sách entity GioHangHoaDon sang danh sách DTO GioHangHoaDonDto
    public static List<GioHangHoaDonDto> convertListGioHangHoaDonToDto(List<GioHangHoaDon> entityList) {
        List<GioHangHoaDonDto> dtoList = new ArrayList<>();
        for (GioHangHoaDon entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    public static GioHangChiTietDto convertToDto(GioHangChiTiet entity){
        GioHangChiTietDto dto = new GioHangChiTietDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getGioHang() != null) dto.setGioHang(entity.getGioHang());
        if (entity.getChiTietSanPham() != null) dto.setChiTietSanPham(entity.getChiTietSanPham());
        if (entity.getGioHang() != null) dto.setGioHang(entity.getGioHang());
        if (entity.getChiTietSanPham() != null) dto.setChiTietSanPham(entity.getChiTietSanPham());
        if (entity.getSoLuong() != null) dto.setSoLuong(entity.getSoLuong());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static GioHangChiTiet convertToEntity(GioHangChiTietDto dto){
        GioHangChiTiet entity = new GioHangChiTiet();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getGioHang() != null) entity.setGioHang(dto.getGioHang());
        if (dto.getChiTietSanPham() != null) entity.setChiTietSanPham(dto.getChiTietSanPham());
        if (dto.getGioHang() != null) entity.setGioHang(dto.getGioHang());
        if (dto.getChiTietSanPham() != null) entity.setChiTietSanPham(dto.getChiTietSanPham());
        if (dto.getSoLuong() != null) entity.setSoLuong(dto.getSoLuong());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<GioHangChiTietDto> convertListGioHangChiTietToDto(List<GioHangChiTiet> entityList) {
        List<GioHangChiTietDto> dtoList = new ArrayList<>();
        for (GioHangChiTiet entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

    public static List<GioHangChiTiet> convertListGioHangChiTietToEntity(List<GioHangChiTietDto> dtoList) {
        List<GioHangChiTiet> entityList = new ArrayList<>();
        for (GioHangChiTietDto dto : dtoList) {
            entityList.add(convertToEntity(dto));
        }
        return entityList;
    }
    public static GioHangDto convertToDto(GioHang entity){
        GioHangDto dto = new GioHangDto();
        if (entity.getId() != null) dto.setId(entity.getId());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getKhachHang() != null) dto.setKhachHang(entity.getKhachHang());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }
    public static GioHang convertToEntity(GioHangDto dto){
        GioHang entity = new GioHang();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getMa() != null) entity.setMa(dto.getMa());
        if (dto.getKhachHang() != null) entity.setKhachHang(dto.getKhachHang());
        if (dto.getNgayTao() != null) entity.setNgayTao(dto.getNgayTao());
        if (dto.getNgaySua() != null) entity.setNgaySua(dto.getNgaySua());
        if (dto.getTrangThai() != null) entity.setTrangThai(dto.getTrangThai());
        return entity;
    }
    public static List<GioHangDto> convertListGioHangToDto(List<GioHang> entityList) {
        List<GioHangDto> dtoList = new ArrayList<>();
        for (GioHang entity : entityList) {
            dtoList.add(convertToDto(entity));
        }
        return dtoList;
    }

}