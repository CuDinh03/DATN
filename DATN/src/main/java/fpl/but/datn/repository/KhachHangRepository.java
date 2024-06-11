package fpl.but.datn.repository;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {

    @Query("SELECT kh from KhachHang kh where kh.trangThai = 1 order by kh.ngayTao DESC")
    Page<KhachHang> findAllPage(Pageable pageable);

    @Query("select kh from KhachHang kh where kh.sdt = :sdt and kh.trangThai = 1 ")
    Optional<KhachHang> getKhachHangBySdt(@Param("sdt") String sdt);

//    Optional<KhachHang> findByMa(String ma);
//    boolean existsByMa(String ma);

    @Query(value = "SELECT kh.* FROM tai_khoan tk JOIN khach_hang kh ON tk.id = kh.tai_khoan_id WHERE tk.ten_dang_nhap = :tenDangNhap", nativeQuery = true)
    KhachHang findKHByTenDangNhap(@Param("tenDangNhap") String tenDangNhap );



}
