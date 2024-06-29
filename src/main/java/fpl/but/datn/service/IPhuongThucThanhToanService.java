package fpl.but.datn.service;



import fpl.but.datn.entity.PhuongThucThanhToan;

import java.util.List;
import java.util.UUID;

public interface IPhuongThucThanhToanService {

    List<PhuongThucThanhToan> getAll();

    PhuongThucThanhToan findById(UUID id);

    PhuongThucThanhToan add(PhuongThucThanhToan phuongThucThanhToan);

    PhuongThucThanhToan update(PhuongThucThanhToan phuongThucThanhToan, UUID id);

    Boolean delete(UUID id);

}
