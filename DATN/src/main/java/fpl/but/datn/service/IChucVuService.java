package fpl.but.datn.service;

import fpl.but.datn.entity.ChucVu;

import java.util.List;
import java.util.UUID;

public interface IChucVuService {
    List getAll();
    ChucVu create(ChucVu chucVu);
    ChucVu update(ChucVu chucVu, UUID id);
    boolean delete(UUID id);
    ChucVu findById(UUID id);
}
