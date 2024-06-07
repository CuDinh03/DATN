package fpl.but.datn.repository;

import fpl.but.datn.entity.GioHangChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangChiTietRepository extends JpaRepository<GioHangChiTiet, UUID> {
    @Query(value = "SELECT ghct.* FROM gio_hang_chi_tiet ghct INNER JOIN gio_hang gh ON ghct.gio_hang_id = gh.id WHERE gh.id = :idGioHang ", nativeQuery = true)
    List<GioHangChiTiet> findAllByIdGioHang(@Param("idGioHang") UUID idGioHang);
    // => Lay ra gio hang chi tiet dua theo ID gio hang
}
