package fpl.but.datn.controller;

import fpl.but.datn.dto.request.SanPhamDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.SanPhamService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/san-pham")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping("/all")
    ApiResponse<List<SanPhamDto>> getAll() {
        List<SanPhamDto> listDto = TranferDatas.convertListSanPhamToDto(sanPhamService.getAll());
        ApiResponse<List<SanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LIST_SANPHAM_FOUND);
        }
        return apiResponse;
    }
}
