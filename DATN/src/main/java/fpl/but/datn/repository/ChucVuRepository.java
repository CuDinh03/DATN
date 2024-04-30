package fpl.but.datn.repository;

import fpl.but.datn.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
}
