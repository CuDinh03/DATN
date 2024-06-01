package fpl.but.datn.controller;

import fpl.but.datn.dto.request.KhachHangDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IKhachHangService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/khach-hang")

public class KhachHangController {
    @Autowired
    IKhachHangService khachHangService;

    @GetMapping("/all")
    ApiResponse<List<KhachHangDto>> getAll() {

        List<KhachHangDto> lstKhachHangDto = TranferDatas.convertToListKhachHangDto(khachHangService.getAll());
        ApiResponse<List<KhachHangDto>> apiResponse = new ApiResponse<>();

        if (!lstKhachHangDto.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach khach hang");
            apiResponse.setResult(lstKhachHangDto);
        } else {
            throw new AppException(ErrorCode.LIST_KHACHHANG_NOT_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("{id}")
    ApiResponse<KhachHangDto> detail(@PathVariable UUID id) {
        ApiResponse<KhachHangDto> apiResponse = new ApiResponse<>();
        if (id != null) {
            KhachHang khachHang = khachHangService.findById(id);
            if (khachHang != null) {
                KhachHangDto khachHangDto = TranferDatas.convertKhachHangToDto(khachHang);
                apiResponse.setMessage("Lấy khach hang thành công!");
                apiResponse.setResult(khachHangDto);
            } else {
                throw new AppException(ErrorCode.KHACHHANG_NOT_FOUND);
            }
        } else {
            apiResponse.setMessage("Id không hợp lệ!");
        }
        return apiResponse;
    }

    @PostMapping("/add")
    public ApiResponse<KhachHang> add(@RequestBody @Valid KhachHangDto khachHangDto) {
        ApiResponse<KhachHang> apiResponse = new ApiResponse<>();
        if (khachHangDto != null) {
            apiResponse.setResult(khachHangService.add(TranferDatas.convertToKhachHangEntity(khachHangDto)));
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    KhachHang update(@PathVariable UUID id,@RequestBody KhachHangDto khachHangDto) {
        UUID idKhachHang = null;
        if (id != null){
            idKhachHang = id;
        }
        if (idKhachHang != null){
            return khachHangService.update(TranferDatas.convertToKhachHangEntity(khachHangDto), idKhachHang);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        UUID idKhachHang = UUID.fromString(id);
        if (khachHangService.delete(idKhachHang)){
            return "Xoa thanh cong!";
        } else {
            return "Xoa that bai!";
        }
    }
}
