package fpl.but.datn.repository;

import fpl.but.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    Optional<KhachHang> findByMa(String ma);
    boolean existsByMa(String ma);

    @Query("SELECT kh from KhachHang kh where kh.trangThai = true order by kh.ngayTao DESC")
    Page<KhachHang> findAllPage(Pageable pageable);

    @Query("select kh from KhachHang kh where kh.sdt = :sdt and kh.trangThai = true ")
    Optional<KhachHang> getKhachHangBySdt(@Param("sdt") String sdt);

    // Tim khach hang theo id_TaiKhoan
//    @Query("select kh from KhachHang kh, TaiKhoan tk where kh.taiKhoan = :idTaiKhoan and tk.id = :idTaiKhoan")
//    KhachHang getKhachHangByIdTaiKhoan(@Param("idTaiKhoan") UUID idTaiKhoan);

    @Query("select kh from KhachHang kh where kh.taiKhoan.id = :idTaiKhoan")
    Optional<KhachHang> getKhachHangByIdTaiKhoan(@Param("idTaiKhoan") UUID idTaiKhoan);
}
