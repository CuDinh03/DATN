package fpl.but.datn.service;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface IChatLieuService {
    List getAll();
    ChatLieu create(ChatLieu chatLieu);
    ChatLieu update(ChatLieu chatLieu, UUID id);
    void delete(UUID id);
    void open(UUID id);

    ChatLieu findById(UUID id);
    Page<ChatLieu> getAllChatLieuPageable(Pageable pageable);
}
