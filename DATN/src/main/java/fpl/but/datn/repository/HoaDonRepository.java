package fpl.but.datn.repository;

import fpl.but.datn.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {

    @Query("SELECT hd from HoaDon hd where hd.ma = :ma ")
    Optional<HoaDon> findByMa(@Param("ma") String ma);

}
