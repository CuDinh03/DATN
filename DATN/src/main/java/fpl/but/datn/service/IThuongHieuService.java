package fpl.but.datn.service;

import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.ThuongHieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IThuongHieuService {
    List<ThuongHieu> getAll();
    ThuongHieu create(ThuongHieu thuongHieu);
    ThuongHieu update(ThuongHieu thuongHieu, UUID id);
    void delete(UUID id);
    void open(UUID id);

    ThuongHieu findById(UUID id);
    Page<ThuongHieu> getAllThuongHieuPageable(Pageable pageable);

    List<ThuongHieu> getAllThuongHieuDangHoatDong();
}
