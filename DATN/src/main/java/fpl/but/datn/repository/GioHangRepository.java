package fpl.but.datn.repository;

import fpl.but.datn.entity.GioHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {

    @Query(value = "SELECT gh.* FROM gio_hang gh INNER JOIN khach_hang kh ON gh.khach_hang_id = kh.id WHERE kh.id = :idKhachHang ORDER BY gh.ngay_tao DESC", nativeQuery = true)
    GioHang findGioHangByKhachHang(@Param("idKhachHang") UUID idKhachHang);



}
