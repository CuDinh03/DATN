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
    @Query("SELECT tk FROM TaiKhoan tk where tk.trangThai = 1 ORDER BY tk.ngayTao DESC")
    Page<TaiKhoan> findAll(Pageable pageable);
    @Query("SELECT tk FROM TaiKhoan tk INNER JOIN  ChucVu cv on tk.idChucVu.id = cv.id WHERE cv.ten = :role and tk.trangThai = 1 ORDER BY tk.ngayTao DESC")
    Page<TaiKhoan> findByTenChucVu(@Param("role") String role, Pageable pageable);
    @Query("SELECT nd FROM TaiKhoan tk INNER JOIN NguoiDung nd on tk.id = nd.idTaiKhoan.id WHERE tk.tenDangNhap = :tenDangNhap")
    Optional<TaiKhoan> findByNguoiDungByTenDangNhap(@Param("tenDangNhap") String tenDangNhap);
    Optional<TaiKhoan> findByTenDangNhap(String tenDangNhap);

    boolean existsByTenDangNhap(String tenDangNhap);
}
