package fpl.but.datn.service;

import fpl.but.datn.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IService<T> {
    T getByID(UUID id);
    T createAccount( T t);

    T update (UUID uuid, T t);

    void delete(UUID id);

    List<T> getAll();

    Page<T> getAllPageable(Pageable pageable);
}
