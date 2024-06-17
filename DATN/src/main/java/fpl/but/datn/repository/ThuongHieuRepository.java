package fpl.but.datn.repository;

import fpl.but.datn.entity.ThuongHieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, UUID> {

    //  Lấy ra thương hiệu có trạng thái đang hoạt động
    @Query("SELECT th FROM ThuongHieu th WHERE th.trangThai = 1")
    List<ThuongHieu> findAllThuongHieuDangHoatDong();

}
