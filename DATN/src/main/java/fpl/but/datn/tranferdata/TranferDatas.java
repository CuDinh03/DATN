package fpl.but.datn.tranferdata;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.entity.*;
import fpl.but.datn.repository.KhachHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        if (entity.getTen() != null) dto.setTen(entity.getTen());
        if (entity.getMa() != null) dto.setMa(entity.getMa());
        if (entity.getNgayTao() != null) dto.setNgayTao(entity.getNgayTao());
        if (entity.getNgaySua() != null) dto.setNgaySua(entity.getNgaySua());
        if (entity.getTrangThai() != null) dto.setTrangThai(entity.getTrangThai());
        return dto;
    }

    public static HinhAnh convertToEntity(HinhAnhDto dto) {
        HinhAnh entity = new HinhAnh();
        if (dto.getId() != null) entity.setId(dto.getId());
        if (dto.getTen() != null) entity.setTen(dto.getTen());
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
        if (entity.getIdSanPham() != null) dto.setIdSanPham(entity.getIdSanPham());
        if (entity.getIdHinhAnh() != null) dto.setIdHinhAnh(entity.getIdHinhAnh());
        if (entity.getIdThuongHieu() != null) dto.setIdThuongHieu(entity.getIdThuongHieu());
        if (entity.getIdChatLieu() != null) dto.setIdChatLieu(entity.getIdChatLieu());
        if (entity.getIdDanhMuc() != null) dto.setIdDanhMuc(entity.getIdDanhMuc());
        if (entity.getIdKichThuoc() != null) dto.setIdKichThuoc(entity.getIdKichThuoc());
        if (entity.getIdMauSac() != null) dto.setIdMauSac(entity.getIdMauSac());
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
        if (dto.getIdSanPham() != null) entity.setIdSanPham(dto.getIdSanPham());
        if (dto.getIdHinhAnh() != null) entity.setIdHinhAnh(dto.getIdHinhAnh());
        if (dto.getIdThuongHieu() != null) entity.setIdThuongHieu(dto.getIdThuongHieu());
        if (dto.getIdChatLieu() != null) entity.setIdChatLieu(dto.getIdChatLieu());
        if (dto.getIdDanhMuc() != null) entity.setIdDanhMuc(dto.getIdDanhMuc());
        if (dto.getIdKichThuoc() != null) entity.setIdKichThuoc(dto.getIdKichThuoc());
        if (dto.getIdMauSac() != null) entity.setIdMauSac(dto.getIdMauSac());
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
        if (entity.getIdTaiKhoan() != null) {
            dto.setIdTaiKhoan(entity.getIdTaiKhoan());
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
        if (dto.getIdTaiKhoan() != null) {
            entity.setIdTaiKhoan(dto.getIdTaiKhoan());
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

}