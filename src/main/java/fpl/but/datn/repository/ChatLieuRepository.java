package fpl.but.datn.repository;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    @Query("SELECT cl FROM ChatLieu cl ORDER BY cl.ngayTao DESC")
    Page<ChatLieu> findAll(Pageable pageable);

    Optional<ChatLieu> findByMa(String ma);
    boolean existsByMa(String ma);

    //  Lấy ra chất liệu có trạng thái đang hoạt động
    @Query("SELECT cl FROM ChatLieu cl WHERE cl.trangThai = 1")
    List<ChatLieu> findAllChatLieuDangHoatDong();
}