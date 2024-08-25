package fpl.but.datn.repository;

import fpl.but.datn.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

@Repository
public interface CTSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID>, JpaSpecificationExecutor<ChiTietSanPham> {

    boolean existsByMa(String ma);
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp ORDER BY ctsp.ngayTao DESC")
    List<ChiTietSanPham> findByNgayTao();

    @Query("SELECT COUNT(c) FROM ChiTietSanPham c WHERE c.ma = :ma AND c.sanPham = :sanPham AND c.thuongHieu = :thuongHieu AND c.chatLieu = :chatLieu AND c.danhMuc = :danhMuc AND c.kichThuoc = :kichThuoc AND c.mauSac = :mauSac")
    long countByCriteria(
            @Param("ma") String ma,
            @Param("sanPham") SanPham sanPham,
            @Param("thuongHieu") ThuongHieu thuongHieu,
            @Param("chatLieu") ChatLieu chatLieu,
            @Param("danhMuc") DanhMuc danhMuc,
            @Param("kichThuoc") KichThuoc kichThuoc,
            @Param("mauSac") MauSac mauSac
    );

    @Query("SELECT c FROM ChiTietSanPham c WHERE c.ma = :ma AND c.sanPham = :sanPham AND c.thuongHieu = :thuongHieu AND c.chatLieu = :chatLieu AND c.danhMuc = :danhMuc AND c.kichThuoc = :kichThuoc AND c.mauSac = :mauSac")
    ChiTietSanPham findByCriteria(
            @Param("ma") String ma,
            @Param("sanPham") SanPham sanPham,
            @Param("thuongHieu") ThuongHieu thuongHieu,
            @Param("chatLieu") ChatLieu chatLieu,
            @Param("danhMuc") DanhMuc danhMuc,
            @Param("kichThuoc") KichThuoc kichThuoc,
            @Param("mauSac") MauSac mauSac
    );


    // LIST ChiTietSanPham với trạng thái = 1 và sắp xếp theo ngày tạo
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp where ctsp.trangThai = 1 ORDER BY ctsp.ngayTao DESC")
    Page<ChiTietSanPham> findAllSapXepNgayTao(Pageable pageable);

    // chuyển trạng thái theo id SPCT
    // Spring Data JPA không hỗ trợ các câu truy vấn UPDATE hoặc DELETE mà chỉ hỗ trợ các câu truy vấn SELECT.
    // Để giải quyết vấn đề này, bạn có thể sử dụng một cách tiếp cận khác bằng cách sử dụng @Modifying và @Query annotation kết hợp với EntityManager.
    @Modifying
    @Transactional
    @Query("UPDATE ChiTietSanPham c SET c.trangThai = 0 WHERE c.id = :id")
    int updateTrangThai(@Param("id") UUID id);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp ORDER BY ctsp.ngayTao DESC ")
    Page<ChiTietSanPham> finAll(Pageable pageable);

    @Query("SELECT c.mauSac FROM ChiTietSanPham c WHERE c.sanPham.ma = :maSanPhamChiTiet")
    List<MauSac> findMauSacsByMaSanPhamChiTiet(@Param("maSanPhamChiTiet") String maSanPhamChiTiet);

    @Query("SELECT c.kichThuoc FROM ChiTietSanPham c WHERE c.sanPham.ma = :maSanPhamChiTiet")
    List<KichThuoc> findkichThuocsByMaSanPhamChiTiet(@Param("maSanPhamChiTiet") String maSanPhamChiTiet);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.sanPham.ma = :ma AND ctsp.kichThuoc.id = :kichThuoc AND ctsp.mauSac.id = :mauSac")
    ChiTietSanPham findChiTietSanPhamByMauSacAndKichThuoc(@Param("ma") String ma, @Param("kichThuoc") UUID kichThuoc, @Param("mauSac") UUID mauSac);
    @Query("SELECT c FROM ChiTietSanPham c WHERE c.ma = :ma AND c.kichThuoc.id = :kichThuoc")
    List<ChiTietSanPham> findChiTietSanPhamByMaAndKichThuoc(@Param("ma") String ma, @Param("kichThuoc") UUID kichThuoc);
    // Tim kiem
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.sanPham.id = :sanPhamId")
    List<ChiTietSanPham> findCTSPBySanPhamId(@Param("sanPhamId") UUID sanPhamId);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.chatLieu.id = :chatLieuId")
    List<ChiTietSanPham> findCTSPByChatLieuId(@Param("chatLieuId") UUID chatLieuId);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.danhMuc.id = :danhMucId")
    List<ChiTietSanPham> findCTSPByDanhMucId(@Param("danhMucId") UUID danhMucId);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.kichThuoc.id = :kichThuocId")
    List<ChiTietSanPham> findCTSPByKichThuocId(@Param("kichThuocId") UUID kichThuocId);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.mauSac.id = :mauSacId")
    List<ChiTietSanPham> findCTSPByMauSacId(@Param("mauSacId") UUID mauSacId);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.thuongHieu.id = :thuongHieuId")
    List<ChiTietSanPham> findCTSPByThuongHieuId(@Param("thuongHieuId") UUID thuongHieuId);

    @Query("select hd.chiTietSanPham from HoaDonChiTiet hd where hd.hoaDon.id = :hoaDonId")
    List<ChiTietSanPham> getCtspByHoaDon(@Param("hoaDonId") UUID hoaDonId);
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.trangThai = :trangThai")
    List<ChiTietSanPham> getAllByTrangThai(@Param("trangThai") Integer trangThai);

    @Query("SELECT c FROM ChiTietSanPham c WHERE " +
            "(:mauSacId IS NULL OR c.mauSac.id = :mauSacId) AND " +
            "(:kichThuocId IS NULL OR c.kichThuoc.id = :kichThuocId) AND " +
            "(:danhMucId IS NULL OR c.danhMuc.id = :danhMucId)")
    List<ChiTietSanPham> findByFilter(
            @Param("mauSacId") UUID mauSacId,
            @Param("kichThuocId") UUID kichThuocId,
            @Param("danhMucId") UUID danhMucId);

    Page<ChiTietSanPham> findAll(Specification<ChiTietSanPham> spec, Pageable pageable);

    @Query("SELECT ctsp FROM ChiTietSanPham ctsp WHERE ctsp.sanPham.id = :sanPhamId AND ctsp.kichThuoc.id = :kichThuocId AND ctsp.mauSac.id = :mauSacId")
    ChiTietSanPham getByMKS (@Param("sanPhamId") UUID sanPhamId ,@Param("kichThuocId")UUID kichThuocId , @Param("mauSacId") UUID mauSacId);

    @Query("SELECT c FROM ChiTietSanPham c " +
            "LEFT JOIN c.sanPham sp " +
            "LEFT JOIN c.thuongHieu th " +
            "LEFT JOIN c.chatLieu cl " +
            "LEFT JOIN c.danhMuc dm " +
            "LEFT JOIN c.kichThuoc kt " +
            "LEFT JOIN c.mauSac ms " +
            "WHERE " +
            "LOWER(c.ma) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(sp.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(th.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(cl.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(dm.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(kt.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(ms.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(c.soLuong AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(c.giaNhap AS string) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(c.giaBan AS string) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<ChiTietSanPham> findByKeyword(@Param("keyword") String keyword, Pageable pageable);

}
