package fpl.but.datn.repository;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHangChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {
    @Query("SELECT GHCT FROM GioHangChiTiet GHCT ORDER BY GHCT.ngayTao DESC")
    Page<GioHangChiTiet> findAll(Pageable pageable);
//    Optional<GioHangChiTiet> findByMa(String ma);
//    boolean existsByMa(String ma);
}
