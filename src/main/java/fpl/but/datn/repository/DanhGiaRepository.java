package fpl.but.datn.repository;

import fpl.but.datn.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, UUID> {
    @Query("SELECT COUNT(d) FROM DanhGia d WHERE d.hoaDonChiTiet.chiTietSanPham.id = :productId")
    long countByChiTietSanPhamId(@Param("productId") UUID productId);

    @Query("SELECT AVG(d.diem) FROM DanhGia d WHERE d.hoaDonChiTiet.chiTietSanPham.id = :productId")
    Double averageDiemByChiTietSanPhamId(@Param("productId") UUID productId);

}
