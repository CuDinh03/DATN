package fpl.but.datn.repository;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {

    @Query(value = "SELECT hdct.* FROM hoa_don_chi_tiet hdct INNER JOIN hoa_don hd ON hdct.hoa_don_id = hd.id WHERE hd.id = :idHoaDon", nativeQuery = true)
    List<HoaDonChiTiet> findAllHoaDonChiTietByIdHoaDon(@Param("idHoaDon") UUID idHoaDon);


}