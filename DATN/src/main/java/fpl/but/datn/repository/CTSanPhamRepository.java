package fpl.but.datn.repository;

import fpl.but.datn.entity.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CTSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
}
