package fpl.but.datn.service;

import fpl.but.datn.entity.KichThuoc;

import java.util.List;
import java.util.UUID;

public interface IKichThuocService {
    List<KichThuoc> getAll();

    KichThuoc findById(UUID id);

    KichThuoc add(KichThuoc kichThuoc);

    KichThuoc update(KichThuoc kichThuoc, UUID id);

    Boolean delete(UUID id);
}
