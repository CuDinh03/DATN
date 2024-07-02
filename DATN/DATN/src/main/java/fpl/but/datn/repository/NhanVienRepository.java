package fpl.but.datn.repository;

import fpl.but.datn.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    Optional<NhanVien> findByMa(String ma);
    boolean existsByMa(String ma);
}
