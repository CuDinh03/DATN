package fpl.but.datn.repository;

import fpl.but.datn.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {

//    @Query("SELECT hdct From HoaDonChiTiet hdct INNER JOIN HoaDon hd ON hdct.idHoaDon = hd.id WHERE hd.id =: hoaDonId")
//    List<HoaDonChiTiet> findChiTietByHoaDonId(@Param("hoaDonId") UUID hoaDonId);

}
