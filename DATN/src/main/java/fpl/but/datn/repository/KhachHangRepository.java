package fpl.but.datn.repository;

import fpl.but.datn.entity.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository

public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
}
