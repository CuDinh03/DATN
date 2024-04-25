package fpl.but.datn.service;
import fpl.but.datn.entity.MauSac;

import java.util.List;
import java.util.UUID;

public interface MauSacService {

    List<MauSac> getAll();

    MauSac getOneById(UUID id);

    Boolean addMauSac(MauSac mauSac);

    Boolean updateMauSac(MauSac mauSac);

    Boolean deleteByIdMauSac(UUID id);
}
