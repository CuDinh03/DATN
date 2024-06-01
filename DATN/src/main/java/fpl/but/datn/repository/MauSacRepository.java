package fpl.but.datn.repository;

import fpl.but.datn.entity.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    Optional<MauSac> findByMa(String ma);
    boolean existsByMa(String ma);
}
