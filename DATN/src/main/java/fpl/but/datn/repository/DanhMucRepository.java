package fpl.but.datn.repository;

import fpl.but.datn.entity.DanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DanhMucRepository extends JpaRepository<DanhMuc, UUID> {
}
