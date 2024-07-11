package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhGiaDto;
import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhGia;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IDanhGiaService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/danh-gia")
public class DanhGiaController {
    @Autowired
    private IDanhGiaService danhGiaService;

    @GetMapping("/find-all")
    ApiResponse<List<DanhGiaDto>> getAll() {
        List<DanhGiaDto> listDto = TranferDatas.convertListDanhGiaToDto(danhGiaService.getAll());
        ApiResponse<List<DanhGiaDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách đánh giá thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.LIST_RATING_NOT_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<DanhGia> createDanhGia(@RequestBody @Valid DanhGiaDto request){
        ApiResponse<DanhGia> apiResponse = new ApiResponse<>();
        if (request != null){
            apiResponse.setResult(danhGiaService.create(TranferDatas.convertToEntity(request)));
        }else {
            throw new AppException(ErrorCode.ADD_RATING_NOT_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/count/{productId}")
    public ApiResponse<Long> countDanhGiaByProductId(@PathVariable UUID productId) {
        ApiResponse<Long> apiResponse = new ApiResponse<>();
        long count = danhGiaService.countByChiTietSanPhamId(productId);
        apiResponse.setResult(count);
        return apiResponse;
    }

    @GetMapping("/average/{productId}")
    public ApiResponse<Double> averageDiemByProductId(@PathVariable UUID productId) {
        ApiResponse<Double> apiResponse = new ApiResponse<>();
        Double averageDiem = danhGiaService.averageDiemByChiTietSanPhamId(productId);
        apiResponse.setResult(averageDiem);
        return apiResponse;
    }

}
