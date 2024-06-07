package fpl.but.datn.controller;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.service.IGioHangChiTietService;
import fpl.but.datn.service.IGioHangService;
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

    @Autowired
    private IGioHangService gioHangService;

    @Autowired
    private ICTSanPhamService ctSanPhamService;

    @GetMapping("/all/{id}")
    ApiResponse<List<GioHangChiTietDto>> getAllGioHangCTByIdGioHang(@PathVariable("id") UUID idGioHang){
        List<GioHangChiTietDto> dto = TranferDatas.convertListGioHangChiTietToDto(gioHangChiTietService.getAllByIdGioHang(idGioHang));
        ApiResponse<List<GioHangChiTietDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách gio hang chi tiet thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_CARTDETAIl_FOUND);
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<GioHangChiTiet> updateGioHangChiTiet(@PathVariable UUID id, @RequestParam Integer soLuong) {
        ApiResponse<GioHangChiTiet> apiResponse = new ApiResponse<>();

        try {
            GioHangChiTiet updatedGioHangChiTiet = gioHangChiTietService.updateGioHangChiTiet(id, soLuong);

            if (updatedGioHangChiTiet == null) {
                apiResponse.setMessage("Gio hang chi tiet đã bị xóa vì số lượng là 0");
                apiResponse.setResult(null);
            } else {
                apiResponse.setMessage("Cập nhật gio hang chi tiet thành công");
                apiResponse.setResult(updatedGioHangChiTiet);
            }
        } catch (AppException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResult(null);
        }

        return apiResponse;
    }

    @PostMapping("/create")
    public ApiResponse<GioHangChiTietDto> createGioHangChiTiet(@RequestBody GioHangChiTietDto request) {
        ApiResponse<GioHangChiTietDto> apiResponse = new ApiResponse<>();
        try {
            GioHangChiTietDto createdDto = new GioHangChiTietDto();
            createdDto.setId(UUID.randomUUID());
            createdDto.setGioHang(request.getGioHang());
            createdDto.setChiTietSanPham(request.getChiTietSanPham());
            createdDto.setSoLuong(1);
            createdDto.setNgayTao(request.getNgayTao());
            createdDto.setNgaySua(request.getNgaySua());
            createdDto.setTrangThai(Boolean.TRUE);
            gioHangChiTietService.create(TranferDatas.convertToEntity(createdDto));
            apiResponse.setMessage("Created Gio Hang Chi Tiet successfully");

            apiResponse.setResult(createdDto);

        } catch (AppException e) {
            apiResponse.setMessage(e.getMessage());
            apiResponse.setResult(null);
        }

        return apiResponse;
    }

}
