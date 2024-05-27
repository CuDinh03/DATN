package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChatLieuDto;
import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.TaiKhoanDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IDanhMucService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/danh-muc")
public class DanhMucController {
    @Autowired
    private IDanhMucService danhMucService;

    @GetMapping("/all")
    ApiResponse<Page<DanhMucDto>> getDanhMuc(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DanhMuc> danhMucPage = danhMucService.getAllDanhMucPageable(pageable);
        List<DanhMucDto> listDto = TranferDatas.convertListDanhMucToDto(danhMucPage.getContent());

        ApiResponse<Page<DanhMucDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách danh mục thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, danhMucPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<DanhMuc> create(@RequestBody @Valid DanhMucDto request) {
        ApiResponse<DanhMuc> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(danhMucService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }

    @PutMapping("/{id}")
    DanhMuc update(@RequestBody DanhMucDto request, @PathVariable String id) {
        UUID idDanhMuc = null;
        if (id != null) idDanhMuc = UUID.fromString(id);
        if (request != null)
            return danhMucService.update(TranferDatas.convertToEntity(request), idDanhMuc);
        return null;
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable String id) {
        UUID idDanhMuc = UUID.fromString(id);
        if (danhMucService.delete(idDanhMuc)) {
            return "xoa thanh cong";
        } else
            return "xoa that bai";
    }

    @GetMapping("/{id}")
    ApiResponse<DanhMucDto> detail(@PathVariable String id) {
        ApiResponse<DanhMucDto> apiResponse = new ApiResponse<>();
        UUID idDanhMuc = null;
        if (id != null){
            idDanhMuc = UUID.fromString(id);
            DanhMucDto dto = TranferDatas.convertToDto(danhMucService.findById(idDanhMuc));
            apiResponse.setMessage("Lấy danh mục thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }
}
