package fpl.but.datn.controller;


import fpl.but.datn.dto.request.ChucVuDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hoa-don")
public class HoaDonController {

    @Autowired
    private IHoaDonService hoaDonService;

    @GetMapping("/all")
    ApiResponse<List<HoaDonDto>> getAll(){
        List<HoaDonDto> dto = TranferDatas.convertListHoaDonToDto(hoaDonService.getAll());
        ApiResponse<List<HoaDonDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<HoaDonDto> getBaoCao(@PathVariable String id) {
        ApiResponse<HoaDonDto> apiResponse = new ApiResponse<>();
        UUID idHoaDon = null;
        if (id != null) idHoaDon = UUID.fromString(id);
        HoaDonDto dto = TranferDatas.convertToDto(hoaDonService.findById(idHoaDon));
        apiResponse.setMessage("Lấy hóa đơn thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }


}
