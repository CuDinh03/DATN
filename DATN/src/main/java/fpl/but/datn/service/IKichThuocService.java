package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.KichThuoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IKichThuocService {
    List<KichThuoc> getAll();

//    KichThuoc findById(UUID id);

    KichThuoc create(KichThuoc kichThuoc);

    KichThuoc update(KichThuoc kichThuoc, UUID id);

    void delete(UUID id);

    void open(UUID id);

    KichThuoc findById(UUID id);

    Page<KichThuoc> getAllKichThuocPageable(Pageable pageable);

    List<KichThuoc> getAllKichThuocDangHoatDong();

}
