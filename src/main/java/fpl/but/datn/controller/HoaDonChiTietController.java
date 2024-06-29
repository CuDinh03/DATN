package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.request.HoaDonDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IHoaDonChiTietService;
import fpl.but.datn.service.impl.HoaDonChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hoa-don-chi-tiet")
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;

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

    @GetMapping("/allKh/{id}")
    public ApiResponse<List<HoaDonChiTietDto>> getAllHoaDonCTByIdHoaDonKh(@PathVariable("id") UUID idHoaDon) {
        List<HoaDonChiTiet> hoaDonChiTiets = hoaDonChiTietService.getHoaDonChiTietByIdHoaDon(idHoaDon);
        List<HoaDonChiTietDto> dtoList = new ArrayList<>();

        if (!hoaDonChiTiets.isEmpty()) {
            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                HoaDonChiTietDto dto = TranferDatas.convertToDto(hoaDonChiTiet);

                // Lấy danh sách hình ảnh của sản phẩm chi tiết
                List<HinhAnh> hinhAnhList = hoaDonChiTiet.getChiTietSanPham().getHinhAnh();
                List<String> hinhAnhUrls = new ArrayList<>();
                for (HinhAnh hinhAnh : hinhAnhList) {
                    hinhAnhUrls.add(hinhAnh.getUrl());
                }
                dto.setHinhAnhUrls(hinhAnhUrls);

                dtoList.add(dto);
            }
            ApiResponse<List<HoaDonChiTietDto>> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Lấy danh sách HÓA ĐƠN chi tiết thành công");
            apiResponse.setResult(dtoList);
            return apiResponse;
        } else {
            throw new AppException(ErrorCode.NO_CARTDETAIl_FOUND);
        }
    }

    @GetMapping("/all/{id}")
    ApiResponse<List<HoaDonChiTietDto>> getAllHDCTByIdHoaDon(@PathVariable("id") UUID idHoaDon){
        List<HoaDonChiTietDto> dto = TranferDatas.convertListHoaDonChiTietToDto(hoaDonChiTietService.getHoaDonChiTietByIdHoaDon(idHoaDon));
        ApiResponse<List<HoaDonChiTietDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách hoa don thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_ORDER_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<HoaDonChiTietDto> detail(@PathVariable String id) {
        ApiResponse<HoaDonChiTietDto> apiResponse = new ApiResponse<>();
        UUID idHoaDonChiTiet = null;
        if (id != null){
            idHoaDonChiTiet = UUID.fromString(id);
            HoaDonChiTietDto dto = TranferDatas.convertToDto(hoaDonChiTietService.findById(idHoaDonChiTiet));
            apiResponse.setMessage("Lấy hóa đơn thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }
}
