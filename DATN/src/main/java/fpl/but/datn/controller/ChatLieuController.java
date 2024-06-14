package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChatLieuDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.ChatLieuService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chat-lieu")
public class ChatLieuController {
    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("/all")
    ApiResponse<List<ChatLieuDto>> getAll() {
        List<ChatLieuDto> listDto = TranferDatas.convertListChatLieuToDto(chatLieuService.getAll());
        ApiResponse<List<ChatLieuDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách chất liệu thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_CHATLIEU_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/all/dang-hoat-dong")
    ApiResponse<List<ChatLieuDto>> getAllDangHoatDong() {
        List<ChatLieuDto> listDto = TranferDatas.convertListChatLieuToDto(chatLieuService.getAllChatLieuDangHoatDong());
        ApiResponse<List<ChatLieuDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách chất liệu thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_CHATLIEU_FOUND);
        }
        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<ChatLieu> create(@RequestBody @Valid ChatLieuDto request) {
        ApiResponse<ChatLieu> apiResponse = new ApiResponse<>();
        if (request != null) {
            apiResponse.setResult(chatLieuService.create(TranferDatas.convertToEntity(request)));
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    ChatLieu update(@RequestBody ChatLieuDto request, @PathVariable String id) {
        UUID idChatLieu = null;
        if (id != null) {
            idChatLieu = UUID.fromString(id);
        }
        if (request != null) {
            return chatLieuService.update(TranferDatas.convertToEntity(request), idChatLieu);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable String id) {
        UUID idChatLieu = UUID.fromString(id);
        if (chatLieuService.delete(idChatLieu)) {
            return "xoa thanh cong";
        } else
            return "xoa that bai";
    }

    @GetMapping("/{id}")
    ApiResponse<ChatLieuDto> detail(@PathVariable String id) {
        ApiResponse<ChatLieuDto> apiResponse = new ApiResponse<>();
        UUID idChatLieu = null;
        if (id != null){
            idChatLieu = UUID.fromString(id);
            ChatLieuDto dto = TranferDatas.convertToDto(chatLieuService.findById(idChatLieu));
            apiResponse.setMessage("Lấy chất liệu thành công");
            apiResponse.setResult(dto);
        }return apiResponse;
    }
}
