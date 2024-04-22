package fpl.but.datn.service.Impl;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.repository.ChatLieuRepository;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements IService<ChatLieu> {
    @Autowired
    private ChatLieuRepository chatLieuRepository;
    @Override
    public List<ChatLieu> getAll() {
        return chatLieuRepository.findAll();
    }

    @Override
    public ChatLieu addNew(ChatLieu chatLieu) {
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu update(ChatLieu chatLieu, UUID id) {
        Optional<ChatLieu> optional = chatLieuRepository.findById(id);
        return optional.map(o -> {
            o.setMa(chatLieu.getMa());
            o.setMoTa(chatLieu.getMoTa());
            o.setTen(chatLieu.getTen());
            o.setTrangThai(chatLieu.isTrangThai());
            return chatLieuRepository.save(o);
        }).orElse(null);

    }

    @Override
    public boolean delete(UUID id) {
        Optional<ChatLieu> optional = chatLieuRepository.findById(id);
        if (optional.isPresent()){
            ChatLieu chatLieu = optional.get();
            chatLieuRepository.delete(chatLieu);
            return true;
        }else {
            return false;
        }

    }

    @Override
    public ChatLieu findById(UUID id) {
        return chatLieuRepository.findById(id).get();
    }
}
