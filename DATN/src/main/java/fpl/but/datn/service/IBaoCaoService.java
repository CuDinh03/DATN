package fpl.but.datn.service;

import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.entity.DanhMuc;

import java.util.List;
import java.util.UUID;

public interface IBaoCaoService {
    List getAll();
    BaoCao create(BaoCao baoCao);
    BaoCao update(BaoCao baoCao, UUID id);
    boolean delete(UUID id);
    BaoCao findById(UUID id);
}
