package fpl.but.datn.repository;

import fpl.but.datn.entity.PhuongThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan, UUID> {
}
