package fpl.but.datn.service;

import fpl.but.datn.entity.GioHang;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IHinhAnhService {
    List getAll();
    HinhAnh create(HinhAnh hinhAnh);
    HinhAnh update(HinhAnh hinhAnh, UUID id);
    boolean delete(UUID id);
    HinhAnh findById(UUID id);
    List finAllByChiTietSanPham(UUID id);

}
