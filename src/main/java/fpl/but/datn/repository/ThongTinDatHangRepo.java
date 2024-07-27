package fpl.but.datn.repository;

import fpl.but.datn.entity.ThongTinDatHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThongTinDatHangRepo extends JpaRepository<ThongTinDatHang, UUID> {

    @Query("select ttdh from ThongTinDatHang ttdh inner join KhachHang kh on ttdh.khachHang.id = kh.id where kh.id = :idKhachHang")
    List<ThongTinDatHang> findAllByKhachHang(@Param("idKhachHang") UUID idKhachHang);

}
