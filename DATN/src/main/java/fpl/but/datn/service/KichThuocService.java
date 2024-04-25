package fpl.but.datn.service;
import fpl.but.datn.entity.KichThuoc;

import java.util.List;
import java.util.UUID;

public interface KichThuocService {

    List<KichThuoc> getAll();

    KichThuoc getOneById(UUID id);

    Boolean addKichThuoc(KichThuoc kichThuoc);

    Boolean updateKichThuoc(KichThuoc kichThuoc);

    Boolean deleteByIdKichThuoc(UUID id);
}
