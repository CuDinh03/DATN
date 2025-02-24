package fpl.but.datn.repository;

import fpl.but.datn.entity.BaoCao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface BaoCaoRepository extends JpaRepository<BaoCao, UUID> {
    Optional<BaoCao> findByMa(String ma);
    boolean existsByMa(String ma);
}