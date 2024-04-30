package fpl.but.datn.repository;

import fpl.but.datn.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {
}
