package fpl.but.datn.repository;

import fpl.but.datn.entity.DanhGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface DanhGiaRepository extends JpaRepository<DanhGia, UUID> {

}
