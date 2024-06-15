package fpl.but.datn.service;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IMauSacService {
    List getAll();
    MauSac create(MauSac mauSac);
    MauSac update(MauSac mauSac, UUID id);
    void delete(UUID id);
    void open(UUID id);

    MauSac findById(UUID id);
    Page<MauSac> getAllMauSacPageable(Pageable pageable);
}
