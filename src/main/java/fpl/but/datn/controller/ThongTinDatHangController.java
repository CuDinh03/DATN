package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.request.ThongTinDatHangDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ThongTinDatHang;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IThongTinDatHangService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/thong-tin-dat-hang")
public class ThongTinDatHangController {
    @Autowired
    private IThongTinDatHangService service;

    @GetMapping()
    ApiResponse<List<ThongTinDatHangDto>> getAll(){
        List<ThongTinDatHangDto> listDto = TranferDatas.convertListThongTinDatHangToDto(service.getAll());
        ApiResponse<List<ThongTinDatHangDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách dia chi thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }

        return apiResponse;
    }

    @GetMapping("/all/{id}")
    ApiResponse<List<ThongTinDatHangDto>> getDCByIdKhachHang(@PathVariable("id") UUID idKhachHang){
        List<ThongTinDatHangDto> dto = TranferDatas.convertListThongTinDatHangToDto(service.getThongTinDatHangByIdKhachHang(idKhachHang));
        ApiResponse<List<ThongTinDatHangDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách địa chỉ thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        return apiResponse;
    }
}
