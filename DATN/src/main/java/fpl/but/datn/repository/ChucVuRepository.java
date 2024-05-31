package fpl.but.datn.repository;

import fpl.but.datn.entity.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {

    Optional<ChucVu> findByMa(String ma);

    Optional<ChucVu> findByTen(String ten);

    boolean existsByMa(String ma);
}
