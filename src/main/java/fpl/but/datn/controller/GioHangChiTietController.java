package fpl.but.datn.controller;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.service.IGioHangChiTietService;
import fpl.but.datn.service.IGioHangService;
import fpl.but.datn.service.impl.GioHangChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private GioHangChiTietService gioHangChiTietService1;

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

    @GetMapping("/allKh/{id}")
    public ApiResponse<List<GioHangChiTietDto>> getAllGioHangCTByIdGioHangKh(@PathVariable("id") UUID idGioHang) {
        List<GioHangChiTiet> gioHangChiTiets = gioHangChiTietService.getAllByIdGioHang(idGioHang);
        List<GioHangChiTietDto> dtoList = new ArrayList<>();

        if (!gioHangChiTiets.isEmpty()) {
            for (GioHangChiTiet gioHangChiTiet : gioHangChiTiets) {
                GioHangChiTietDto dto = TranferDatas.convertToDto(gioHangChiTiet);

                // Lấy danh sách hình ảnh của sản phẩm chi tiết
                List<HinhAnh> hinhAnhList = gioHangChiTiet.getChiTietSanPham().getHinhAnh();
                List<String> hinhAnhUrls = new ArrayList<>();
                for (HinhAnh hinhAnh : hinhAnhList) {
                    hinhAnhUrls.add(hinhAnh.getUrl());
                }
                dto.setHinhAnhUrls(hinhAnhUrls);

                dtoList.add(dto);
            }

            ApiResponse<List<GioHangChiTietDto>> apiResponse = new ApiResponse<>();
            apiResponse.setMessage("Lấy danh sách giỏ hàng chi tiết thành công");
            apiResponse.setResult(dtoList);
            return apiResponse;
        } else {
            throw new AppException(ErrorCode.NO_CARTDETAIl_FOUND);
        }
    }


    @PostMapping("/addProductToCart")
    public ApiResponse<GioHangChiTiet> addProductToGioHang(
            @RequestParam UUID idGioHang,
            @RequestParam UUID idSanPham,
            @RequestParam Integer soLuong) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.addProductToGioHang(idGioHang, idSanPham, soLuong);
        ApiResponse<GioHangChiTiet> response = new ApiResponse<>();
        response.setMessage("Thêm sản phẩm vào giỏ hàng thành công");
        response.setResult(gioHangChiTiet);
        return response;
    }

    @PostMapping("/addProductToCartKH")
    public ApiResponse<GioHangChiTiet> addProductToGioHangKH(
            @RequestParam UUID idGioHang,
            @RequestParam UUID idSanPham,
            @RequestParam Integer soLuong) {
        GioHangChiTiet gioHangChiTiet = gioHangChiTietService.addProductToGioHangKH(idGioHang, idSanPham, soLuong);
        ApiResponse<GioHangChiTiet> response = new ApiResponse<>();
        response.setMessage("Thêm sản phẩm vào giỏ hàng thành công");
        response.setResult(gioHangChiTiet);
        return response;
    }

    @PutMapping("/updateCartKH/{id}")
    public ApiResponse<GioHangChiTiet> updateGioHangChiTietKH(@PathVariable UUID id, @RequestParam Integer soLuong) {
        ApiResponse<GioHangChiTiet> apiResponse = new ApiResponse<>();

        try {
            GioHangChiTiet updatedGioHangChiTiet = gioHangChiTietService.updateGioHangChiTietKH(id, soLuong);

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
            createdDto.setTrangThai(1);
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
