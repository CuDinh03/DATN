package fpl.but.datn.repository;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
    @Query("SELECT kt FROM KichThuoc kt ORDER BY kt.ngayTao DESC")
    Page<KichThuoc> findAll(Pageable pageable);

    Optional<KichThuoc> findByMa(String ma);
    boolean existsByMa(String ma);

    //  Lấy ra kích thước có trạng thái đang hoạt động
    @Query("SELECT kt FROM KichThuoc kt WHERE kt.trangThai = 1")
    List<KichThuoc> findAllKichThuocDangHoatDong();
}
