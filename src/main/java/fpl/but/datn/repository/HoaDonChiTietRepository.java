package fpl.but.datn.repository;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.HoaDonChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface HoaDonChiTietRepository extends JpaRepository<HoaDonChiTiet, UUID> {


    @Query(value = "SELECT hdct.* FROM hoa_don_chi_tiet hdct INNER JOIN hoa_don hd ON hdct.hoa_don_id = hd.id WHERE hd.id = :idHoaDon ORDER BY hd.ngay_tao DESC", nativeQuery = true)

    List<HoaDonChiTiet> findAllHoaDonChiTietByIdHoaDon(@Param("idHoaDon") UUID idHoaDon);

    @Query(value = "SELECT hdct, ha FROM HoaDonChiTiet hdct " +
            "INNER JOIN HoaDon hd ON hdct.hoaDon.id = hd.id " +
            "INNER JOIN ChiTietSanPham ctsp ON hdct.chiTietSanPham.id = ctsp.id " +
            "INNER JOIN HinhAnh ha ON ctsp.id = ha.chiTietSanPham.id " +
            "WHERE hd.id = :idGioHang ORDER BY hdct.ngaySua DESC", nativeQuery = false)
    List<Object[]> findAllChiTietAndHinhAnhByIdHoaDon(@Param("idGioHang") UUID idGioHang);

    @Query(value = "SELECT hdct, ha FROM HoaDonChiTiet hdct " +
            "INNER JOIN HoaDon hd ON hdct.hoaDon.id = hd.id " +
            "INNER JOIN ChiTietSanPham ctsp ON hdct.chiTietSanPham.id = ctsp.id " +
            "INNER JOIN HinhAnh ha ON ctsp.id = ha.chiTietSanPham.id " +
            "WHERE hd.id = :idGioHang ORDER BY hdct.ngaySua DESC")
    List<Object[]> findAllChiTietAndHinhAnhByIdHoaDonKH(@Param("idGioHang") UUID idGioHang);

    @Query("SELECT h FROM HoaDonChiTiet h ORDER BY h.soLuong DESC")
    List<HoaDonChiTiet> findTopSellingProducts();

}