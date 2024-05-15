package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.ChatLieuRepository;
import fpl.but.datn.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatLieuService implements IChatLieuService {
    @Autowired
    private ChatLieuRepository chatLieuRepository;

    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.findAll();
    }

    @Override
    public ChatLieu create(ChatLieu request) {

        ChatLieu chatLieu = new ChatLieu();

        if (chatLieuRepository.existsByMa(request.getMa())) {
            throw new AppException(ErrorCode.CHATLIEU_EXISTED);
        }
        chatLieu.setMa(request.getMa());
        chatLieu.setId(UUID.randomUUID());
        chatLieu.setTen(request.getTen());
        chatLieu.setNgayTao(new Date());
        chatLieu.setNgaySua(new Date());
        chatLieu.setTrangThai(true);

        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu update(ChatLieu request, UUID id) {
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setMa(request.getMa());
        chatLieu.setTen(request.getTen());
        chatLieu.setId(id);
        chatLieu.setNgayTao(new Date());
        chatLieu.setNgaySua(new Date());
        chatLieu.setTrangThai(true);
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public boolean delete(UUID id) {
        Optional<ChatLieu> optional = chatLieuRepository.findById(id);
        if (optional.isPresent()) {
            ChatLieu chatLieu = optional.get();
            chatLieuRepository.delete(chatLieu);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public ChatLieu findById(UUID id) {
        return chatLieuRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION));
    }
}
