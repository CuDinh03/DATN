package fpl.but.datn.repository;

import fpl.but.datn.entity.PhuongThucThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PhuongThucThanhToanRepository extends JpaRepository<PhuongThucThanhToan, UUID> {
    Optional<PhuongThucThanhToan> findByMa(String ma);
    boolean existsByMa(String ma);
}
