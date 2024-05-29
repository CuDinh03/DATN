package fpl.but.datn.service;

import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ITaiKhoanService {

    List<TaiKhoan> getAllTk();
    TaiKhoan getByID(UUID id);
    TaiKhoan createAccount( TaiKhoan taiKhoan);

    TaiKhoan update (UUID uuid, TaiKhoan taiKhoan);

    void delete(UUID id);

    Page<TaiKhoan> getAllTaiKhoanPageable(Pageable pageable);

    Page<TaiKhoan> findByRoles(String role, Pageable pageable);




}
