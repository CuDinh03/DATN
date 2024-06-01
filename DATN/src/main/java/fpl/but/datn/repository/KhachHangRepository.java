package fpl.but.datn.repository;

import fpl.but.datn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    Optional<KhachHang> findByMa(String ma);
    boolean existsByMa(String ma);
}
