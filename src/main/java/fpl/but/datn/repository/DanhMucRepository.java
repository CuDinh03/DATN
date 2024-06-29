package fpl.but.datn.repository;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {

    @Query("SELECT dm FROM DanhMuc dm ORDER BY dm.ngayTao DESC")
    Page<DanhMuc> findAll(Pageable pageable);
    Optional<DanhMuc> findByMa(String ma);
    boolean existsByMa(String ma);
    
    //  Lấy ra danh mục có trạng thái đang hoạt động
    @Query("SELECT dm FROM DanhMuc dm WHERE dm.trangThai = 1")
    List<DanhMuc> findAllDanhMucDangHoatDong();
}
