package fpl.but.datn.repository;

import fpl.but.datn.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, UUID> {
}
