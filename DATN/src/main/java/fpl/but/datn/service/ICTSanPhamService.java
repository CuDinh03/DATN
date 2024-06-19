package fpl.but.datn.service;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.MauSac;
import fpl.but.datn.service.impl.MauSacService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ICTSanPhamService {

    List getAll();

    ChiTietSanPham create(ChiTietSanPham chiTietSanPham);

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
}
