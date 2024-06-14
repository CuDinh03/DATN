package fpl.but.datn.repository;

import fpl.but.datn.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("SELECT sp FROM SanPham sp ORDER BY sp.ngayTao DESC")
    Page<SanPham> findAll(Pageable pageable);
    Optional<SanPham> findByMa(String ma);
    boolean existsByMa(String ma);

    // Lấy ra sản phẩm có trạng thái đang hoạt động
    @Query("SELECT sp FROM SanPham sp WHERE sp.trangThai = 1")
    List<SanPham> findAllSanPhamDangHoatDong();
}