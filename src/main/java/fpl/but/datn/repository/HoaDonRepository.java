package fpl.but.datn.repository;

import fpl.but.datn.dto.response.MonthlySalesData;

import fpl.but.datn.entity.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query("SELECT hd FROM HoaDon hd ORDER BY hd.ngaySua DESC")
    Page<HoaDon> findAllPage(Pageable pageable);
    @Query("SELECT hd from HoaDon hd where hd.ma = :ma ")
    Optional<HoaDon> findByMa(@Param("ma") String ma);
    @Query("SELECT hd from HoaDon hd where hd.ma like %:ma% ")
    Optional<HoaDon> findByMaKH(@Param("ma") String ma);
    @Query("SELECT hd from HoaDon hd where hd.khachHang.id = :khachHangId AND hd.ma like %:ma% ")
    Optional<HoaDon> findByMaAndKhachHang(@Param("ma") String ma, @Param("khachHangId") UUID khachHangId);
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangThai = :trangThai ORDER BY hd.ngaySua DESC")
    Page<HoaDon> findByTrangThai(Pageable pageable, Integer trangThai);
    @Query("SELECT hd FROM HoaDon hd WHERE hd.trangThai = :trangThai ORDER BY hd.ngaySua DESC")
    List<HoaDon> findByTrangThai1(Integer trangThai);
    @Query(value = "SELECT hd.* FROM hoa_don hd WHERE hd.trang_thai = :trangThai AND hd.khach_hang_id = :khachHangId ORDER BY hd.ngay_sua DESC", nativeQuery = true)
    List<HoaDon> findByTrangThaiAndKhachHangId(@Param("trangThai") Integer trangThai,@Param("khachHangId") UUID khachHangId);
    @Query(value = "SELECT hd.* FROM hoa_don hd INNER JOIN khach_hang kh ON hd.khach_hang_id = kh.id WHERE kh.id = :idKhachHang ORDER BY hd.ngay_sua DESC", nativeQuery = true)
    List<HoaDon> findHoaDonByKhachHang(@Param("idKhachHang") UUID idKhachHang);
    List<HoaDon> findByNgayTaoBetween(Date startDate, Date endDate);

    @Query("SELECT h FROM HoaDon h WHERE h.ngayTao BETWEEN :startDate AND :endDate AND h.khachHang.id = :khachHangId")
    List<HoaDon> findByNgayTaoBetweenAndKhachHangId(@Param("startDate") Date startDate,
                                                    @Param("endDate") Date endDate,
                                                    @Param("khachHangId") UUID khachHangId);

    @Query("SELECT new fpl.but.datn.dto.response.MonthlySalesData(MONTH(h.ngayTao), COUNT(h.id), SUM(h.tongTien)) " +
            "FROM HoaDon h WHERE h.trangThai = 4 " +
            "GROUP BY MONTH(h.ngayTao) " +
            "ORDER BY MONTH(h.ngayTao)")
    List<MonthlySalesData> findMonthlySalesData1();
}
