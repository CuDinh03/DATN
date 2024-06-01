package fpl.but.datn.repository;

import fpl.but.datn.entity.KichThuoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface KichThuocRepository extends JpaRepository<KichThuoc, UUID> {
    Optional<KichThuoc> findByMa(String ma);
    boolean existsByMa(String ma);
}
