package fpl.but.datn.repository;

import fpl.but.datn.entity.GiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GiaoHangRepository extends JpaRepository<GiaoHang, UUID> {
    GiaoHang findByHoaDon_Id(UUID hoaDonId);

}
