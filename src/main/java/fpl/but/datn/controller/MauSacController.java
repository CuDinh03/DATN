package fpl.but.datn.controller;

import fpl.but.datn.dto.request.MauSacDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.MauSac;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.MauSacService;
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
@RequestMapping("/api/mau-sac")
public class MauSacController {

    @Autowired
    private MauSacService mauSacService;

    @GetMapping("/getAll")
    ApiResponse<List<MauSacDto>> getAll() {
        List<MauSacDto> listDto = TranferDatas.convertListMauSacToDto(mauSacService.getAll());
        ApiResponse<List<MauSacDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách màu sắc thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_MAUSAC_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<Page<MauSacDto>> getMauSac(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MauSac> mauSacPage = mauSacService.getAllMauSacPageable(pageable);
        List<MauSacDto> listDto = TranferDatas.convertListMauSacToDto(mauSacPage.getContent());

        ApiResponse<Page<MauSacDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách màu sắc thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, mauSacPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_MAUSAC_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/getAll/dang-hoat-dong")
    ApiResponse<List<MauSacDto>> getAllDangHoatDong() {

        List<MauSacDto> lstMauSacDtos = TranferDatas.convertListMauSacToDto(mauSacService.getAllMauSacDangHoatDong());
        ApiResponse<List<MauSacDto>> apiResponse = new ApiResponse<>();

        if (!lstMauSacDtos.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach mau sac!");
            apiResponse.setResult(lstMauSacDtos);
        } else {
            throw new AppException(ErrorCode.LIST_COLOR_NOT_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<MauSac> create(@RequestBody @Valid MauSacDto request) {
        ApiResponse<MauSac> apiResponse = new ApiResponse<>();
        if (request != null) {
            apiResponse.setResult(mauSacService.create(TranferDatas.convertToEntity(request)));
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    MauSac update(@RequestBody MauSacDto request, @PathVariable String id) {
        UUID idMauSac = null;
        if (id != null) {
            idMauSac = UUID.fromString(id);
        }
        if (request != null) {
            return mauSacService.update(TranferDatas.convertToEntity(request), idMauSac);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        UUID idMauSac;
        if (id != null) {
            idMauSac = UUID.fromString(id);
            mauSacService.delete(idMauSac);
        } return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idMauSac;
        if (id != null) {
            idMauSac = UUID.fromString(id);
            mauSacService.open(idMauSac);
        } return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/{id}")
    ApiResponse<MauSacDto> detail(@PathVariable String id) {
        ApiResponse<MauSacDto> apiResponse = new ApiResponse<>();
        UUID idMauSac;
        if (id != null){
            idMauSac = UUID.fromString(id);
            MauSacDto dto = TranferDatas.convertToDto(mauSacService.findById(idMauSac));
            apiResponse.setMessage("Lấy màu sắc thành công");
            apiResponse.setResult(dto);
        }return apiResponse;
    }


}
