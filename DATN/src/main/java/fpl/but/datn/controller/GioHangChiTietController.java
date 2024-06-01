package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.dto.request.GioHangHoaDonDto;
import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IGioHangChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/api/gio-hang-chi-tiet")
public class GioHangChiTietController {
    @Autowired
    private IGioHangChiTietService gioHangChiTietService;

    @GetMapping("/all/{id}")
    ApiResponse<Page<GioHangChiTietDto>> getGioHangChiTiet(@PathVariable("id") UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<GioHangChiTiet> danhMucPage = gioHangChiTietService.getAllByIdGioHang(id,pageable);
        List<GioHangChiTietDto> listDto = TranferDatas.convertListGioHangChiTietToDto(danhMucPage.getContent());

        ApiResponse<Page<GioHangChiTietDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách Gio hang thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, danhMucPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<GioHangChiTiet> updateGioHangChiTiet(@PathVariable UUID id, @RequestParam Integer soLuong) {
        ApiResponse<GioHangChiTiet> apiResponse = new ApiResponse<>();

        try {
            GioHangChiTiet updatedGioHangChiTiet = gioHangChiTietService.updateGioHangChiTiet(id, soLuong);

            if (updatedGioHangChiTiet == null) {
                apiResponse.setMessage("GioHangChiTiet đã bị xóa vì số lượng là 0");
                apiResponse.setResult(null);
            } else {
                apiResponse.setMessage("Cập nhật GioHangChiTiet thành công");
                apiResponse.setResult(updatedGioHangChiTiet);
            }
        } catch (AppException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResult(null);
        }

        return apiResponse;
    }
}
