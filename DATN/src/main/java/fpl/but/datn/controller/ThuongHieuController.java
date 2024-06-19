package fpl.but.datn.controller;

import fpl.but.datn.dto.request.KichThuocDto;
import fpl.but.datn.dto.request.ThuongHieuDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.entity.ThuongHieu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.KichThuocService;
import fpl.but.datn.service.impl.ThuongHieuService;
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
@RequestMapping("/api/thuong-hieu")
public class ThuongHieuController {
    @Autowired
    private ThuongHieuService thuongHieuService;

    @GetMapping("/getAll")
    ApiResponse<List<ThuongHieuDto>> getAll() {
        List<ThuongHieuDto> listDto = TranferDatas.convertListThuongHieuToDto(thuongHieuService.getAll());
        ApiResponse<List<ThuongHieuDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách thương hiệu thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_THUONGHIEU_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<Page<ThuongHieuDto>> getThuongHieu(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ThuongHieu> thuongHieuPage = thuongHieuService.getAllThuongHieuPageable(pageable);
        List<ThuongHieuDto> listDto = TranferDatas.convertListThuongHieuToDto(thuongHieuPage.getContent());

        ApiResponse<Page<ThuongHieuDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách thương hiệu thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, thuongHieuPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }
    @GetMapping("/getAll/dang-hoat-dong")
    ApiResponse<List<ThuongHieuDto>> getAllDangHoatDong() {
        List<ThuongHieuDto> listDto = TranferDatas.convertListThuongHieuToDto(thuongHieuService.getAllThuongHieuDangHoatDong());
        ApiResponse<List<ThuongHieuDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách thương hiệu thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_THUONGHIEU_FOUND);
        }
        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<ThuongHieu> create(@RequestBody @Valid ThuongHieuDto request) {
        ApiResponse<ThuongHieu> apiResponse = new ApiResponse<>();
        if (request != null) {
            apiResponse.setResult(thuongHieuService.create(TranferDatas.convertToEntity(request)));
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    ThuongHieu update(@RequestBody ThuongHieuDto request, @PathVariable String id) {
        UUID idThuongHieu = null;
        if (id != null) {
            idThuongHieu = UUID.fromString(id);
        }
        if (request != null) {
            return thuongHieuService.update(TranferDatas.convertToEntity(request), idThuongHieu);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        UUID idThuongHieu = null;
        if (id != null) {
            idThuongHieu = UUID.fromString(id);
            thuongHieuService.delete(idThuongHieu);
        } return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idThuongHieu = null;
        if (id != null) {
            idThuongHieu = UUID.fromString(id);
            thuongHieuService.open(idThuongHieu);
        } return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/{id}")
    ApiResponse<ThuongHieuDto> detail(@PathVariable String id) {
        ApiResponse<ThuongHieuDto> apiResponse = new ApiResponse<>();
        UUID idThuongHieu = null;
        if (id != null){
            idThuongHieu = UUID.fromString(id);
            ThuongHieuDto dto = TranferDatas.convertToDto(thuongHieuService.findById(idThuongHieu));
            apiResponse.setMessage("Lấy thương hiệu thành công");
            apiResponse.setResult(dto);
        }return apiResponse;
    }
}
