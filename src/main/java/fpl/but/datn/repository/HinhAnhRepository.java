package fpl.but.datn.repository;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
    @Query(value = "SELECT ha.* FROM hinh_anh ha INNER JOIN chi_tiet_san_pham ctsp ON ha.chi_tiet_san_pham_id = ctsp.id WHERE ctsp.id = :idChiTietSanPham", nativeQuery = true)
    List<HinhAnh> findAllByChiTietSanPham(@Param("idChiTietSanPham") UUID idChiTietSanPham);

//    @Query("SELECT ha FROM HinhAnh ha ORDER BY ha.ngayTao DESC")
//    Page<HinhAnh> findAll(Pageable pageable);

    Optional<HinhAnh> findByMa(String ma);
    boolean existsByMa(String ma);
}
