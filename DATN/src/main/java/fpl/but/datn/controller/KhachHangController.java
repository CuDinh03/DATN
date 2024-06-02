package fpl.but.datn.controller;

import fpl.but.datn.dto.request.KhachHangDto;
import fpl.but.datn.dto.request.KhachHangDto;
import fpl.but.datn.dto.request.TaiKhoanDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.entity.KhachHang;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.KhachHangService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/khs")
@Slf4j
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;

    @PostMapping("/create")
    ApiResponse<KhachHang> createKhachHang(@RequestBody @Valid KhachHangDto request){
        ApiResponse<KhachHang> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(khachHangService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }
    
    @GetMapping("/all")
    ApiResponse<Page<KhachHangDto>> getAccounts(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<KhachHang> KhachHangPage = khachHangService.getAllPageable(pageable);
        List<KhachHangDto> listDto = TranferDatas.convertListKhachHangToDto(KhachHangPage.getContent());

        ApiResponse<Page<KhachHangDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách Khách hàng thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, KhachHangPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_CUSTOMERS_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/{sdt}")
    ApiResponse<KhachHangDto> getKHBySdt(@PathVariable String sdt){
        ApiResponse<KhachHangDto> apiResponse = new ApiResponse<>();
        KhachHangDto dto = TranferDatas.convertToDto(khachHangService.getKhachHangBySdt(sdt));
        apiResponse.setMessage("Lấy Khách hàng thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }
}
