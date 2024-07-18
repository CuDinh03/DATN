package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.repository.ChatLieuRepository;
import fpl.but.datn.service.IChatLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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
        Random random = new Random();

        if (chatLieuRepository.existsByMa(request.getMa()))
            throw new AppException(ErrorCode.CHATLIEU_EXISTED);

        chatLieu.setMa("CL" + random.nextInt(1000));
        chatLieu.setTen(request.getTen());
        chatLieu.setNgayTao(new Date());
        chatLieu.setNgaySua(new Date());
        chatLieu.setTrangThai(1);

        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu update(ChatLieu request, UUID id) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(id);

        if (optionalChatLieu.isPresent()) {
            ChatLieu chatLieu = optionalChatLieu.get();
            chatLieu.setMa(request.getMa());
            chatLieu.setTen(request.getTen());
            chatLieu.setNgaySua(new Date());
            chatLieu.setTrangThai(request.getTrangThai());
            return chatLieuRepository.save(chatLieu);
        } else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
    }

    @Override
    public void delete(UUID id) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(id);

        if (optionalChatLieu.isPresent()) {
            ChatLieu chatLieu = optionalChatLieu.get();
            chatLieu.setTrangThai(0);
            chatLieuRepository.save(chatLieu);
        } else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
    }

    @Override
    public void open(UUID id) {
        Optional<ChatLieu> optionalChatLieu = chatLieuRepository.findById(id);

        if (optionalChatLieu.isPresent()) {
            ChatLieu chatLieu = optionalChatLieu.get();
            chatLieu.setTrangThai(1);
            chatLieuRepository.save(chatLieu);
        } else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }

    }

    @Override
    public ChatLieu findById(UUID id) {
        return chatLieuRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.NO_CHATLIEU_FOUND));
    }

    @Override
    public Page<ChatLieu> getAllChatLieuPageable(Pageable pageable) {
        return chatLieuRepository.findAll(pageable);
    }

    @Override
    public List<ChatLieu> getAllChatLieuDangHoatDong() {
        return chatLieuRepository.findAllChatLieuDangHoatDong();
    }
}