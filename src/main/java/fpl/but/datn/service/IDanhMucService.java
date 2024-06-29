package fpl.but.datn.service;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDanhMucService {
    List getAll();
    DanhMuc create(DanhMuc danhMuc);
    DanhMuc update(DanhMuc danhMuc, UUID id);
    void delete(UUID id);
    void open(UUID id);
    DanhMuc findById(UUID id);
    Page<DanhMuc> getAllDanhMucPageable(Pageable pageable);

    List<DanhMuc> getAllDanhMucDangHoatDong();
}
