package fpl.but.datn.controller;

import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.dto.request.GioHangHoaDonDto;
import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IGioHangChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/gio-hang-chi-tiet")
public class GioHangChiTietController {
    @Autowired
    private IGioHangChiTietService gioHangChiTietService;
    @GetMapping("/all/{id}")
    ApiResponse<List<GioHangChiTietDto>> getAllByIdGioHang(@PathVariable("id") UUID id){
        List<GioHangChiTietDto> dto = TranferDatas.convertListGioHangChiTietToDto(gioHangChiTietService.getAllByIdGioHang(id));
        ApiResponse<List<GioHangChiTietDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách gio hang thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }
}
