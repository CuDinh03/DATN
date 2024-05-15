package fpl.but.datn.repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import fpl.but.datn.dto.GioHangDto;
import fpl.but.datn.entity.GioHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GioHangRepository extends JpaRepository<GioHang, UUID> {

    @Query(value = "SELECT gh.ma, kh.ten as khachHang, gh.trang_thai FROM gio_hang gh JOIN khach_hang kh ON gh.id_khach_hang = kh.id ", nativeQuery = true)
    List<GioHangDto> getAll();
}
