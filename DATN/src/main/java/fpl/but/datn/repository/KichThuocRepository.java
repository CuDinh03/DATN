package fpl.but.datn.repository;

import fpl.but.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
    Optional<KichThuoc> findByMa(String ma);
    boolean existsByMa(String ma);

    //  Lấy ra kích thước có trạng thái đang hoạt động
    @Query("SELECT kt FROM KichThuoc kt WHERE kt.trangThai = 1")
    List<KichThuoc> findAllKichThuocDangHoatDong();
}
