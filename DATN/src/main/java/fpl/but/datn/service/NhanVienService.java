package fpl.but.datn.service;
import fpl.but.datn.entity.NhanVien;

import java.util.List;
import java.util.UUID;

public interface NhanVienService {

    List<NhanVien> getAll();

    NhanVien getOneById(UUID id);

    Boolean addNhanVien(NhanVien nhanVien);

    Boolean updateNhanVien(NhanVien nhanVien);

    Boolean deleteByIdNhanVien(UUID id);
}
