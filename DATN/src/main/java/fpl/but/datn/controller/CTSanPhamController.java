package fpl.but.datn.controller;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.service.IDanhMucService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.annotation.security.PermitAll;
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
@RequestMapping("/api/chi-tiet-san-pham")
public class CTSanPhamController {

    @Autowired
    private ICTSanPhamService ctSanPhamService;

    @GetMapping("/all")
    ApiResponse<Page<ChiTietSanPhamDto>> getDanhMuc(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = ctSanPhamService.getAllChiTietSanPhamPageable(pageable);
        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(chiTietSanPhamPage.getContent());

        ApiResponse<Page<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách sa pham thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, chiTietSanPhamPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/getAll")
        // Cho phép truy cập mà không cần phải xác thực
    ApiResponse<List<ChiTietSanPhamDto>> getAll() {
        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.getAll());
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<ChiTietSanPham> create(@RequestBody @Valid ChiTietSanPhamDto request) {
        ApiResponse<ChiTietSanPham> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(ctSanPhamService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }




@PutMapping("/{id}")
ChiTietSanPham update(@RequestBody ChiTietSanPhamDto request, @PathVariable String id) {
    UUID idCTSP = null;
    if (id != null) idCTSP = UUID.fromString(id);
    if (request != null)
        return ctSanPhamService.update(TranferDatas.convertToEntity(request), idCTSP);
    return null;
}


@DeleteMapping("/{id}")
ApiResponse<Void> delete(@PathVariable String id) {
    UUID idCTSP = null;
    if (id != null) {
        idCTSP = UUID.fromString(id);
        ctSanPhamService.delete(idCTSP);
    } return ApiResponse.<Void>builder().build();
}

    @GetMapping("/{id}")
    ApiResponse<ChiTietSanPhamDto> detail(@PathVariable String id) {
        ApiResponse<ChiTietSanPhamDto> apiResponse = new ApiResponse<>();
        UUID idChiTietSanPham = null;
        if (id != null){
            idChiTietSanPham = UUID.fromString(id);
            ChiTietSanPhamDto dto = TranferDatas.convertToDto(ctSanPhamService.findById(idChiTietSanPham));
            apiResponse.setMessage("Lấy chi tiết sản phẩm thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @GetMapping("/findAllMauSacByMaCTSP/{ma}")
    ApiResponse<List<MauSacDto>> finAllMauSacByMaCTSP(@PathVariable String ma){
        List<MauSacDto> dto = TranferDatas.convertListMauSacToDto(ctSanPhamService.findAllMauSacByMaCTSP(ma));
        ApiResponse<List<MauSacDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("lay danh sach mau sac thanh cong");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }

    @GetMapping("/findAllKichThuocByMaCTSP/{ma}")
    ApiResponse<List<KichThuocDto>> finAllKichThuocByMaCTSP(@PathVariable String ma){
        List<KichThuocDto> dto = TranferDatas.convertListKichThuocToDto(ctSanPhamService.findkichThuocsByMaSanPhamChiTiet(ma));
        ApiResponse<List<KichThuocDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("lay danh sach kich thuoc thanh cong");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }

    @GetMapping("/findChiTietSanPhamByMauSacAndKichThuoc/{ma}")
    ApiResponse<ChiTietSanPhamDto>  findAllByKichThuocAndMauSac(@PathVariable String ma, @RequestParam UUID kichThuoc, @RequestParam UUID mauSac){
        ChiTietSanPhamDto dto = TranferDatas.convertToDto(ctSanPhamService.findChiTietSanPhamByMauSacAndKichThuoc(ma, kichThuoc, mauSac));
        ApiResponse<ChiTietSanPhamDto>  apiResponse = new ApiResponse<>();
        if (dto != null){
            apiResponse.setMessage("lay danh sach san pham thanh cong");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/findSanPhamByKichThuoc/{ma}")
    ApiResponse<List<ChiTietSanPhamDto>> finAllMauSacByMaCTSP(@PathVariable String ma, @RequestParam UUID kichThuoc){
        List<ChiTietSanPhamDto> dto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findSanPhamByKichThuoc(ma, kichThuoc));
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("lay danh sach sản phẩm thanh cong");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }
}
