package fpl.but.datn.service.impl;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.SanPham;
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
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setId(id);
        chatLieu.setMa(request.getMa());
        chatLieu.setTen(request.getTen());
        chatLieu.setNgayTao(new Date());
        chatLieu.setNgaySua(new Date());
        chatLieu.setTrangThai(request.getTrangThai());
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public void delete(UUID id) {
        ChatLieu taiKhoan = findById(id);
        taiKhoan.setTrangThai(0);
        chatLieuRepository.save(taiKhoan);

    }

    @Override
    public void open(UUID id) {
        ChatLieu taiKhoan = findById(id);
        taiKhoan.setTrangThai(1);
        chatLieuRepository.save(taiKhoan);

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
