package fpl.but.datn.repository;

import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {
    @Query("SELECT HDCT FROM HoaDonChiTiet HDCT ORDER BY HDCT.ngayTao DESC")
    Page<HoaDonChiTiet> findAll(Pageable pageable);
}
