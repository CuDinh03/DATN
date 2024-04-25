package fpl.but.datn.service;
import fpl.but.datn.entity.PhuongThucThanhToan;

import java.util.List;
import java.util.UUID;

public interface PhuongThucThanhToanService {
    List<PhuongThucThanhToan> getAll();

    PhuongThucThanhToan getOneById(UUID id);

    Boolean addPhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan);

    Boolean updatePhuongThucThanhToan(PhuongThucThanhToan phuongThucThanhToan);

    Boolean deleteByIdPhuongThucThanhToan(UUID id);
}
