package fpl.but.datn.controller;

import fpl.but.datn.dto.request.BaoCaoDto;
import fpl.but.datn.dto.request.HinhAnhDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IBaoCaoService;
import fpl.but.datn.service.IHinhAnhService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hinh-anh")
public class HinhAnhController {

    @Autowired
    private IHinhAnhService hinhAnhService;
    @GetMapping("/all")
    ApiResponse<List<HinhAnhDto>> getAll() {
        List<HinhAnhDto> listDto = TranferDatas.convertListHinhAnhToDto(hinhAnhService.getAll());
        ApiResponse<List<HinhAnhDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hinh anh thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_REPORT_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    ApiResponse<HinhAnh> create(@RequestBody @Valid HinhAnhDto request){
        ApiResponse<HinhAnh> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(hinhAnhService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }
}
