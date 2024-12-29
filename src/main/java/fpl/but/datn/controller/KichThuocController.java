package fpl.but.datn.controller;

import fpl.but.datn.dto.request.KichThuocDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.KichThuocService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/kich-thuoc")
public class KichThuocController {
    @Autowired
    private KichThuocService kichThuocService;

    @GetMapping("/getAll")
    ApiResponse<List<KichThuocDto>> getAll() {

        List<KichThuocDto> lstKichThuocDto = TranferDatas.convertListKichThuocToDto(kichThuocService.getAll());
        ApiResponse<List<KichThuocDto>> apiResponse = new ApiResponse<>();

        if (!lstKichThuocDto.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach kich thuoc");
            apiResponse.setResult(lstKichThuocDto);
        } else {
            throw new AppException(ErrorCode.LIST_KICHTHUOC_NOT_FOUND);
        }
        return apiResponse;
    }


    @GetMapping("/getAll/dang-hoat-dong")
    ApiResponse<List<KichThuocDto>> getAllDangHoatDong() {

        List<KichThuocDto> lstKichThuocDto = TranferDatas.convertListKichThuocToDto(kichThuocService.getAllKichThuocDangHoatDong());
        ApiResponse<List<KichThuocDto>> apiResponse = new ApiResponse<>();

        if (!lstKichThuocDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách kích thước thành công");
            apiResponse.setResult(lstKichThuocDto);
        } else {
            throw new AppException(ErrorCode.NO_KICHTHUOC_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<Page<KichThuocDto>> getKichThuoc(@RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<KichThuoc> kichThuocPage = kichThuocService.getAllKichThuocPageable(pageable);
        List<KichThuocDto> listDto = TranferDatas.convertListKichThuocToDto(kichThuocPage.getContent());

        ApiResponse<Page<KichThuocDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách kích thước thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, kichThuocPage.getTotalElements()));
        }

        return apiResponse;
    }

    @GetMapping("{id}")
    ApiResponse<KichThuocDto> detail(@PathVariable UUID id) {
        ApiResponse<KichThuocDto> apiResponse = new ApiResponse<>();
        if (id != null) {
            KichThuoc kichThuoc = kichThuocService.findById(id);
            if (kichThuoc != null) {
                KichThuocDto mauSacDto = TranferDatas.convertToDto(kichThuoc);
                apiResponse.setMessage("Lấy kích thước thành công!");
                apiResponse.setResult(mauSacDto);
            } else {
                throw new AppException(ErrorCode.KICHTHUOC_NOT_FOUND);
            }
        } else {
            throw new AppException(ErrorCode.NO_KICHTHUOC_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<KichThuoc> create(@RequestBody @Valid KichThuocDto request) {
        ApiResponse<KichThuoc> apiResponse = new ApiResponse<>();
        if (request != null) {
            apiResponse.setResult(kichThuocService.create(TranferDatas.convertToEntity(request)));
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    KichThuoc update(@RequestBody KichThuocDto request, @PathVariable String id) {
        UUID idKichThuoc = null;
        if (id != null) {
            idKichThuoc = UUID.fromString(id);
        }
        if (request != null) {
            return kichThuocService.update(TranferDatas.convertToEntity(request), idKichThuoc);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        UUID idKichThuoc;
        if (id != null) {
            idKichThuoc = UUID.fromString(id);
            kichThuocService.delete(idKichThuoc);
        } return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idKichThuoc = null;
        if (id != null) {
            idKichThuoc = UUID.fromString(id);
            kichThuocService.open(idKichThuoc);
        } return ApiResponse.<Void>builder().build();
    }
}
