package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonChiTietService;
import fpl.but.datn.service.impl.HoaDonChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    private IHoaDonChiTietService hoaDonChiTietService;

    @GetMapping("/all")
    ApiResponse<List<HoaDonChiTietDto>> getAll(){
        List<HoaDonChiTietDto> dto = TranferDatas.convertListHoaDonChiTietToDto(hoaDonChiTietService.getAll());
        ApiResponse<List<HoaDonChiTietDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<HoaDonChiTiet> create(@RequestBody @Valid HoaDonChiTietDto request) {
        ApiResponse<HoaDonChiTiet> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(hoaDonChiTietService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }
}
