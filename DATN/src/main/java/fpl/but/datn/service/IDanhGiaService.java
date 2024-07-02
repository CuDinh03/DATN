package fpl.but.datn.service;

import fpl.but.datn.entity.DanhGia;
import fpl.but.datn.entity.DanhMuc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IDanhGiaService {

    List getAll();
    DanhGia create(DanhGia DanhGia);
    DanhGia update(DanhGia DanhGia, UUID id);
    void delete(UUID id);
    void open(UUID id);
    DanhGia findById(UUID id);
    Page<DanhGia> getAllDanhGiaPageable(Pageable pageable);
}
