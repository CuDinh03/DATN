package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.entity.NguoiDung;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.service.impl.NguoiDungService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface INguoiDungService {

    List getAll();
    NguoiDung create(NguoiDung nguoiDung);
    NguoiDung update(NguoiDung nguoiDung, UUID id);
    void delete(UUID id);
    void open(UUID id);
    NguoiDung findById(UUID id);
    Page<NguoiDung> getAllDanhMucPageable(Pageable pageable);
    Optional<NguoiDung> findByTaiKhoanid (UUID id);

}
