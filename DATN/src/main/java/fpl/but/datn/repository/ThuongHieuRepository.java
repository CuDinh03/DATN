package fpl.but.datn.repository;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ThuongHieuRepository extends JpaRepository<ThuongHieu, UUID> {
    @Query("SELECT th FROM ThuongHieu th ORDER BY th.ngayTao DESC")
    Page<ThuongHieu> findAll(Pageable pageable);

    Optional<ThuongHieu> findByMa(String ma);
    boolean existsByMa(String ma);
}
