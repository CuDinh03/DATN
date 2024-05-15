package fpl.but.datn.service;

import fpl.but.datn.entity.DanhMuc;

import java.util.List;
import java.util.UUID;

public interface IDanhMucService {
    List getAll();
    DanhMuc create(DanhMuc danhMuc);
    DanhMuc update(DanhMuc danhMuc, UUID id);
    boolean delete(UUID id);
    DanhMuc findById(UUID id);
}
