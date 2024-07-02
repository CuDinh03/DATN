package fpl.but.datn.service;

import fpl.but.datn.entity.NhanVien;

import java.util.List;
import java.util.UUID;

public interface INhanVienService {
    List<NhanVien> getAll();

    NhanVien findById(UUID id);

    NhanVien add(NhanVien nhanVien);

    NhanVien update(NhanVien nhanVien, UUID id);

    Boolean delete(UUID id);
}
