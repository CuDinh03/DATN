package fpl.but.datn.repository;

import fpl.but.datn.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    Optional<MauSac> findByMa(String ma);
    boolean existsByMa(String ma);

    //  Lấy ra màu sắc có trạng thái đang hoạt động
    @Query("SELECT ms FROM MauSac ms WHERE ms.trangThai = 1")
    List<MauSac> findAllMauSacDangHoatDong();
}
