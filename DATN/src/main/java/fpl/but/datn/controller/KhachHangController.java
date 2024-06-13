package fpl.but.datn.controller;

import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.dto.request.KhachHangDto;
import fpl.but.datn.dto.request.KhachHangDto;
import fpl.but.datn.dto.request.TaiKhoanDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
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
import java.util.UUID;
import java.util.Objects;

@RestController
@RequestMapping("/api/khs")
@Slf4j
public class KhachHangController {
    @Autowired
    KhachHangService khachHangService;

    @GetMapping("/getAll")
    ApiResponse<List<KhachHangDto>> getAll() {
        List<KhachHangDto> listDto = TranferDatas.convertListKhachHangToDto(khachHangService.getAll());
        ApiResponse<List<KhachHangDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách khách hàng thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_CUSTOMERS_FOUND);
        }

        return apiResponse;
    }

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
    @PutMapping("/{id}")
    KhachHang update(@RequestBody KhachHangDto request, @PathVariable String id) {
        UUID idKhachHang = null;
        if (id != null) idKhachHang = UUID.fromString(id);
        if (request != null)
            return khachHangService.update(TranferDatas.convertToEntity(request), idKhachHang);
        return null;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        UUID idKhachHang = null;
        if (id != null) {
            idKhachHang = UUID.fromString(id);
            khachHangService.delete(idKhachHang);
        } return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idKhachHang = null;
        if (id != null) {
            idKhachHang = UUID.fromString(id);
            khachHangService.open(idKhachHang);
        } return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/detail/{id}")
    ApiResponse<KhachHangDto> detail(@PathVariable String id) {
        ApiResponse<KhachHangDto> apiResponse = new ApiResponse<>();
        UUID idKhachHang = null;
        if (id != null){
            idKhachHang = UUID.fromString(id);
            KhachHangDto dto = TranferDatas.convertToDto(khachHangService.findById(idKhachHang));
            apiResponse.setMessage("Lấy khách hàng thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @GetMapping("/findUsername/{tenDangNhap}")
    ApiResponse<KhachHangDto> findKHByTenDangNhapo(@PathVariable String tenDangNhap){
        ApiResponse<KhachHangDto> apiResponse =  new ApiResponse<>();
        KhachHangDto dto = TranferDatas.convertToDto(khachHangService.findKHByTenDangNhap(tenDangNhap));
        apiResponse.setMessage("Lấy Khách hàng thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }

}
