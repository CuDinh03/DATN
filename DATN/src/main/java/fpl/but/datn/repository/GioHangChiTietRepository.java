package fpl.but.datn.repository;


import fpl.but.datn.entity.GioHangChiTiet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {
    @Query(value = "SELECT ghct.* FROM gio_hang_chi_tiet ghct INNER JOIN gio_hang gh ON ghct.id_gio_hang = gh.id WHERE gh.id = :idGioHang ", nativeQuery = true)
    List<GioHangChiTiet> findAllByIdGioHang(@Param("idGioHang") UUID idGioHang);
}
