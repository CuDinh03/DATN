package fpl.but.datn.repository;

import fpl.but.datn.entity.Voucher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, UUID> {

    Optional<Voucher> findByTen(String ten);

    @Query("SELECT vc from Voucher vc where vc.trangThai = 1 order by vc.ngayTao DESC ")
    Page<Voucher> findAllPage(Pageable pageable);

    Optional<Voucher> findByMa(String ma);
}
