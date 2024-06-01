package fpl.but.datn.repository;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface CTSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("SELECT CTSP FROM ChiTietSanPham CTSP ORDER BY CTSP.ngayTao DESC")
    Page<ChiTietSanPham> findAll(Pageable pageable);
    Optional<ChiTietSanPham> findByMa(String ma);
    boolean existsByMa(String ma);
}
