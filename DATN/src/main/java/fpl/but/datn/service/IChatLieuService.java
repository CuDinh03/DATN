package fpl.but.datn.service;

import fpl.but.datn.entity.ChatLieu;

import java.util.List;
import java.util.UUID;

public interface IChatLieuService {
    List getAll();
    ChatLieu create(ChatLieu chatLieu);
    ChatLieu update(ChatLieu chatLieu, UUID id);
    boolean delete(UUID id);
    ChatLieu findById(UUID id);
}
