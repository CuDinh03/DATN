package fpl.but.datn.repository;

import fpl.but.datn.entity.GiaoHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GiaoHangRepository extends JpaRepository<GiaoHang, UUID> {
}
