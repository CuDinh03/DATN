package fpl.but.datn.repository;

import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, UUID> {
    @Query("SELECT tk FROM TaiKhoan tk  ORDER BY tk.ngayTao DESC")
    Page<TaiKhoan> findAll(Pageable pageable);

    @Query("SELECT nd FROM TaiKhoan tk INNER JOIN NguoiDung nd on tk.id = nd.taiKhoan.id WHERE tk.tenDangNhap = :tenDangNhap")
    Optional<TaiKhoan> findByNguoiDungByTenDangNhap(@Param("tenDangNhap") String tenDangNhap);
    Optional<TaiKhoan> findByTenDangNhap(String tenDangNhap);

    boolean existsByTenDangNhap(String tenDangNhap);
}
