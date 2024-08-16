package fpl.but.datn.repository;

import fpl.but.datn.entity.NguoiDung;
import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, UUID> {

    @Query("select nd from NguoiDung nd where nd.taiKhoan.id = :id")
    Optional<NguoiDung> findByTaiKhoanid (@Param("id") UUID id);

}
