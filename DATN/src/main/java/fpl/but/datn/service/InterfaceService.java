package fpl.but.datn.service;

import java.util.List;
import java.util.UUID;

public interface InterfaceService <H> {

    List<H> getAll();

    H addNew(H h);

    H update(H h, UUID id);

    boolean delete (UUID id);

    H findById (UUID id);
}
