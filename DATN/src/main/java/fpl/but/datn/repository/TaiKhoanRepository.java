package fpl.but.datn.repository;

import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, UUID> {
    Optional<TaiKhoan> findByTenDangNhap(String tenDangNhap);
}
