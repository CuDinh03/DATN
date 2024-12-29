package fpl.but.datn.controller;

import fpl.but.datn.dto.request.GioHangDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.service.IGioHangService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/gio-hang")
public class GioHangController {

    @Autowired
    private IGioHangService gioHangService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(gioHangService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody GioHang gioHang){
        return ResponseEntity.ok(gioHangService.create(gioHang));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody GioHang gioHang, @PathVariable UUID id){
        return ResponseEntity.ok(gioHangService.update(gioHang,id));
    }

    @GetMapping("/findByKhachHang/{id}")
    ApiResponse<GioHangDto> findByIdKhachHang(@PathVariable UUID id) {
        ApiResponse<GioHangDto> apiResponse = new ApiResponse<>();
        if (id != null){
            GioHangDto dto = TranferDatas.convertToDto(gioHangService.findByIdKhachHang(id));
            apiResponse.setMessage("Lấy Hóa đơn thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<GioHangDto> detail(@PathVariable String id) {
        ApiResponse<GioHangDto> apiResponse = new ApiResponse<>();
        UUID idGioHang;
        if (id != null){
            idGioHang = UUID.fromString(id);
            GioHangDto dto = TranferDatas.convertToDto(gioHangService.findById(idGioHang));
            apiResponse.setMessage("Lấy Hóa đơn thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (gioHangService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }



}
