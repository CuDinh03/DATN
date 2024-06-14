package fpl.but.datn.service;

import fpl.but.datn.entity.ThuongHieu;

import java.util.List;
import java.util.UUID;

public interface IThuongHieuService {
    List<ThuongHieu> getAll();

    ThuongHieu findById(UUID id);

    ThuongHieu add(ThuongHieu thuongHieu);

    ThuongHieu update(ThuongHieu thuongHieu, UUID id);

    Boolean delete(UUID id);
}
