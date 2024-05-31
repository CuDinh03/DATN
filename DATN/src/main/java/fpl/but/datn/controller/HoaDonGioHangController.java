package fpl.but.datn.controller;

import fpl.but.datn.dto.request.GioHangHoaDonDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonGioHangService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hoa-don-gio-hang")
public class HoaDonGioHangController {
    @Autowired
    private IHoaDonGioHangService hoaDonGioHang;

    @GetMapping("/all")
    ApiResponse<List<GioHangHoaDonDto>> getAll(){
        List<GioHangHoaDonDto> dto = TranferDatas.convertListGioHangHoaDonToDto(hoaDonGioHang.getAll());
        ApiResponse<List<GioHangHoaDonDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/all/{id}")
    ApiResponse<List<GioHangHoaDonDto>> getAllByIdHoaDon(@PathVariable("id") UUID id){
        List<GioHangHoaDonDto> dto = TranferDatas.convertListGioHangHoaDonToDto(hoaDonGioHang.getAllByIdHoaDon(id));
        ApiResponse<List<GioHangHoaDonDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }
}
