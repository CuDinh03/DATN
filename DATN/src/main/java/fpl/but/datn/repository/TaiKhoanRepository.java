package fpl.but.datn.repository;

import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, UUID> {
}
