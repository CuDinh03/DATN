package fpl.but.datn.repository;

import fpl.but.datn.entity.HinhAnh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HinhAnhRepository extends JpaRepository<HinhAnh, UUID> {
}
