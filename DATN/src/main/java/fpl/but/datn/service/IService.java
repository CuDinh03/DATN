package fpl.but.datn.service;

import java.util.List;
import java.util.UUID;

public interface IService<T>{
    List<T> getAll();
    T addNew(T t);
    T update(T t, UUID id);
    boolean delete(UUID id);
    T findById(UUID id);
}
