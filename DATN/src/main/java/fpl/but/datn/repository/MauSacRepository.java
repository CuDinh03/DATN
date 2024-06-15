package fpl.but.datn.repository;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface MauSacRepository extends JpaRepository<MauSac, UUID> {
    @Query("SELECT ms FROM MauSac ms ORDER BY ms.ngayTao DESC")
    Page<MauSac> findAll(Pageable pageable);

    Optional<MauSac> findByMa(String ma);
    boolean existsByMa(String ma);

}
