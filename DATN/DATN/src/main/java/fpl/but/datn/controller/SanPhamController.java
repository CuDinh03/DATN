package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.SanPhamDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.SanPham;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.SanPhamService;
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
@RequestMapping("/api/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/getAll")
    ApiResponse<List<SanPhamDto>> getAll() {
        List<SanPhamDto> listDto = TranferDatas.convertListSanPhamToDto(sanPhamService.getAll());
        ApiResponse<List<SanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách san pham thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_SANPHAM_FOUND);
        }

        return apiResponse;
    }
    @GetMapping("/all")
    ApiResponse<Page<SanPhamDto>> getSanPham(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<SanPham> sanPhamPage = sanPhamService.getAllSanPhamPageable(pageable);
        List<SanPhamDto> listDto = TranferDatas.convertListSanPhamToDto(sanPhamPage.getContent());

        ApiResponse<Page<SanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách san pham thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, sanPhamPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_SANPHAM_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/getAll/dang-hoat-dong")
    ApiResponse<List<SanPhamDto>> getAllDangHoatDong() {
        List<SanPhamDto> listDto = TranferDatas.convertListSanPhamToDto(sanPhamService.getAllSanPhamDangHoatDong());
        ApiResponse<List<SanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LIST_SANPHAM_FOUND);
        }
        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<SanPham> create(@RequestBody @Valid SanPhamDto request) {
        ApiResponse<SanPham> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(sanPhamService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }

    @PutMapping("/{id}")
    SanPham update(@RequestBody SanPhamDto request, @PathVariable String id) {
        UUID idSanPham = null;
        if (id != null) idSanPham = UUID.fromString(id);
        if (request != null)
            return sanPhamService.update(TranferDatas.convertToEntity(request), idSanPham);
        return null;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        UUID idSanPham = null;
        if (id != null) {
            idSanPham = UUID.fromString(id);
            sanPhamService.delete(idSanPham);
        } return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idSanPham = null;
        if (id != null) {
            idSanPham = UUID.fromString(id);
            sanPhamService.open(idSanPham);
        } return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/{id}")
    ApiResponse<SanPhamDto> detail(@PathVariable String id) {
        ApiResponse<SanPhamDto> apiResponse = new ApiResponse<>();
        UUID idSanPham = null;
        if (id != null){
            idSanPham = UUID.fromString(id);
            SanPhamDto dto = TranferDatas.convertToDto(sanPhamService.findById(idSanPham));
            apiResponse.setMessage("Lấy sản phẩm thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }
}
