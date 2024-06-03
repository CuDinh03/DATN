package fpl.but.datn.repository;

import fpl.but.datn.entity.GioHangHoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface GioHangHoaDonRepository extends JpaRepository<GioHangHoaDon, UUID> {
    @Query(value = "SELECT hdgh.* FROM gio_hang_hoa_don hdgh INNER JOIN hoa_don hd ON hdgh.hoa_don_id = hd.id WHERE hd.id = :idHoaDon AND hd.trang_thai = 1", nativeQuery = true)
    List<GioHangHoaDon> findAllHoaDonGioHangByIdHoaDon(@Param("idHoaDon") UUID idHoaDon);

    @Query("SELECT ghhd FROM GioHangHoaDon ghhd ORDER BY ghhd.ngayTao DESC")
    List<GioHangHoaDon> findAllByNgayTao();

    @Query("select ghhd from GioHangHoaDon ghhd where ghhd.hoaDon.id = :idHoaDon")
    GioHangHoaDon findByIdHoaDon (@Param("idHoaDon") UUID idHoaDon);
}
