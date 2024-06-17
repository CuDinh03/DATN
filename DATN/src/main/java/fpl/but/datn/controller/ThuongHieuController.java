package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ThuongHieuDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.ThuongHieuService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
