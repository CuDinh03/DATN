package fpl.but.datn.repository;

import fpl.but.datn.entity.ChiTietSanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CTSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {

    boolean existsByMa(String ma); // thay doi

    // LIST ChiTietSanPham với trạng thái = 1 và sắp xếp theo ngày tạo
    @Query("SELECT ctsp FROM ChiTietSanPham ctsp ORDER BY ctsp.ngayTao DESC")
    Page<ChiTietSanPham> findAllSapXepNgayTao(Pageable pageable);

    // chuyển trạng thái theo id SPCT
    // Spring Data JPA không hỗ trợ các câu truy vấn UPDATE hoặc DELETE mà chỉ hỗ trợ các câu truy vấn SELECT.
    // Để giải quyết vấn đề này, bạn có thể sử dụng một cách tiếp cận khác bằng cách sử dụng @Modifying và @Query annotation kết hợp với EntityManager.
    @Modifying
    @Transactional
    @Query("UPDATE ChiTietSanPham c SET c.trangThai = 0 WHERE c.id = :id")
    int updateTrangThai(@Param("id") UUID id);


}
