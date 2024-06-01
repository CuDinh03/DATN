package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChiTietSanPhamDto;
import fpl.but.datn.dto.request.GioHangChiTietDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IGioHangChiTietService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/gio-hang-chi-tiet")
public class GioHangChiTietController {
    @Autowired
    private IGioHangChiTietService iGioHangChiTietService;
//    @GetMapping()
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok(iGioHangChiTietService.getAll());
//    }

    @GetMapping("/all")
    ApiResponse<List<GioHangChiTietDto>> getAll() {
        List<GioHangChiTietDto> listDto = TranferDatas.convertListGioHangChiTietToDto(iGioHangChiTietService.getAll());
        ApiResponse<List<GioHangChiTietDto>> apiResponse = new ApiResponse<List<GioHangChiTietDto>>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách GHCT thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }
//    @PostMapping("/addNew")
//    public ResponseEntity<?> getAll(@RequestBody GioHangChiTiet gioHangChiTiet){
//        return ResponseEntity.ok(iGioHangChiTietService.create(gioHangChiTiet));
//    }

    @PostMapping("/create")
    ApiResponse<GioHangChiTiet> create(@RequestBody @Valid GioHangChiTietDto request) {
        ApiResponse<GioHangChiTiet> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(iGioHangChiTietService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@RequestBody GioHangChiTiet gioHangChiTiet, @PathVariable UUID id){
//        return ResponseEntity.ok(iGioHangChiTietService.update(gioHangChiTiet,id));
//    }

    @PutMapping("/{id}")
    GioHangChiTiet update(@RequestBody GioHangChiTietDto request, @PathVariable String id) {
        UUID idGHCT = null;
        if (id != null) idGHCT = UUID.fromString(id);
        if (request != null)
            return iGioHangChiTietService.update(TranferDatas.convertToEntity(request), idGHCT);
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (iGioHangChiTietService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(iGioHangChiTietService.findById(id));
    }
}
