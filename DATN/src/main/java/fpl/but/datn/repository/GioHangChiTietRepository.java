package fpl.but.datn.repository;


import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {

    @Query(value = "SELECT ghct.* FROM gio_hang_chi_tiet ghct INNER JOIN gio_hang gh ON ghct.gio_hang_id = gh.id WHERE gh.id = :idGioHang ", nativeQuery = true)
    List<GioHangChiTiet> findAllByIdGioHang(@Param("idGioHang") UUID idGioHang);

    @Query(value = "SELECT ghct.*, ha.* FROM gio_hang_chi_tiet ghct " +
            "INNER JOIN gio_hang gh ON ghct.gio_hang_id = gh.id " +
            "INNER JOIN chi_tiet_san_pham ctsp ON ghct.chi_tiet_san_pham_id = ctsp.id " +
            "INNER JOIN hinh_anh ha ON ctsp.id = ha.chi_tiet_san_pham_id " +
            "WHERE gh.id = :idGioHang ", nativeQuery = true)
    List<Object[]> findAllChiTietAndHinhAnhByIdGioHang(@Param("idGioHang") UUID idGioHang);

    @Query(value = "SELECT g FROM GioHangChiTiet g WHERE g.gioHang = :gioHang AND g.chiTietSanPham = :chiTietSanPham")
    Optional<GioHangChiTiet> findByGioHangAndSanPhamChiTiet(GioHang gioHang, ChiTietSanPham chiTietSanPham);


}
