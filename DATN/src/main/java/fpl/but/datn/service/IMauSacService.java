package fpl.but.datn.service;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.MauSac;
import java.util.List;
import java.util.UUID;

public interface IMauSacService {
    List<MauSac> getAll();

    MauSac findById(UUID id);

    MauSac add(MauSac mauSac);

    MauSac update(MauSac mauSac, UUID id);

    Boolean delete(UUID id);

    List<MauSac> getAllMauSacDangHoatDong();
}
