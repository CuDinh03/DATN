package fpl.but.datn.controller;

import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
}
