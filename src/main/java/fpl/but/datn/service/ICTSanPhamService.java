package fpl.but.datn.service;

import fpl.but.datn.dto.request.FilterSanPhamRequest;
import fpl.but.datn.dto.request.HinhAnhRequest;
import fpl.but.datn.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ICTSanPhamService {

    List<ChiTietSanPham> getAll();

    ChiTietSanPham create(ChiTietSanPham chiTietSanPham);

    ChiTietSanPham create(ChiTietSanPham request, List<HinhAnhRequest> hinhAnhs);

    ChiTietSanPham update(ChiTietSanPham chiTietSanPham, UUID id);

    boolean updateTrangThai(UUID id);

    boolean delete(UUID id);

    ChiTietSanPham findById(UUID id);

    // ChiTietSanPham findByDuLieu(UUID id, ......);

    Page<ChiTietSanPham> getAllChiTietSanPhamPageable(Pageable pageable);

    Page<ChiTietSanPham> getAllChiTietSanPhamPageableSapXepNGayTao(Pageable pageable);

    List<MauSac> findAllMauSacByMaCTSP(String maChiTietSanPham);
    List<KichThuoc> findkichThuocsByMaSanPhamChiTiet(String maChiTietSanPham);
    ChiTietSanPham findChiTietSanPhamByMauSacAndKichThuoc(String ma, UUID kichThuoc, UUID mauSac);

    List<ChiTietSanPham> findSanPhamByKichThuoc(String ma, UUID kichThuoc);

    // Tim Kiem
    List<ChiTietSanPham> findCTSPBySanPhamId(UUID id);

    List<ChiTietSanPham> findCTSPByChatLieuId(UUID id);

    List<ChiTietSanPham> findCTSPByDanhMucId(UUID id);

    List<ChiTietSanPham> findCTSPByKichThuocId(UUID id);

    List<ChiTietSanPham> findCTSPByMauSacId(UUID id);

    List<ChiTietSanPham> findCTSPByThuongHieuId(UUID id);
    List<ChiTietSanPham> saveCtsp(SanPham sanPham,
                                  List<MauSac> mauSacList,
                                  ChatLieu chatLieu,
                                  DanhMuc danhMuc,
                                  ThuongHieu thuongHieu,
                                  List<KichThuoc> kichThuocList);

    List<ChiTietSanPham> getCtsp();
    List<ChiTietSanPham> saveListCt(List<ChiTietSanPham> list);

    List<ChiTietSanPham> saveListCt(List<ChiTietSanPham> list, List<HinhAnhRequest> hinhAnhs);
    List<ChiTietSanPham> findByFilter(UUID mauSac, UUID kichThuoc, UUID danhMuc);
    Page<ChiTietSanPham> filterSanPham(FilterSanPhamRequest request, int page, int size);

    ChiTietSanPham getByMKS ( UUID sanPhamId , UUID kichThuocId , UUID mauSacId);


    Page<ChiTietSanPham> search(String keyword, Pageable pageable);

}
