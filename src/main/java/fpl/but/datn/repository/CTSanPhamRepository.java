package fpl.but.datn.repository;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CTSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp ORDER BY ctsp.ngayTao DESC ")
    Page<ChiTietSanPham> finAll(Pageable pageable);

    @Query("SELECT c.mauSac FROM ChiTietSanPham c WHERE c.ma = :maSanPhamChiTiet")
    List<MauSac> findMauSacsByMaSanPhamChiTiet(@Param("maSanPhamChiTiet") String maSanPhamChiTiet);

    @Query("SELECT c.kichThuoc FROM ChiTietSanPham c WHERE c.ma = :maSanPhamChiTiet")
    List<KichThuoc> findkichThuocsByMaSanPhamChiTiet(@Param("maSanPhamChiTiet") String maSanPhamChiTiet);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.ma = :ma AND ctsp.kichThuoc.id = :kichThuoc AND ctsp.mauSac.id = :mauSac")
    ChiTietSanPham findChiTietSanPhamByMauSacAndKichThuoc(@Param("ma") String ma,@Param("kichThuoc")UUID kichThuoc, @Param("mauSac") UUID mauSac );

    @Query("SELECT c FROM ChiTietSanPham c WHERE c.ma = :ma AND c.kichThuoc.id = :kichThuoc")
    List<ChiTietSanPham> findChiTietSanPhamByMaAndKichThuoc(@Param("ma") String ma, @Param("kichThuoc") UUID kichThuoc);
}
