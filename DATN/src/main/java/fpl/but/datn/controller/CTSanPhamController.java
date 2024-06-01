package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChiTietSanPhamDto;
import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.service.IDanhMucService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ct-san-pham")
public class CTSanPhamController {

    @Autowired
    private ICTSanPhamService ctSanPhamService;
//    @GetMapping()
//    public ResponseEntity<?> getAll(){
//        return ResponseEntity.ok(ctSanPhamService.getAll());
//    }
@GetMapping("/all")
ApiResponse<List<ChiTietSanPhamDto>> getAll() {
    List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.getAll());
    ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<List<ChiTietSanPhamDto>>();

    if (!listDto.isEmpty()) {
        apiResponse.setMessage("Lấy danh sách CTSP thành công");
        apiResponse.setResult(listDto);
    } else {
        throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
    }
    return apiResponse;
}

//    @PostMapping("/addNew")
//    public ResponseEntity<?> getAll(@RequestBody ChiTietSanPham ctSanPham){
//        return ResponseEntity.ok(ctSanPhamService.create(ctSanPham));
//    }

    @PostMapping("/create")
    ApiResponse<ChiTietSanPham> create(@RequestBody @Valid ChiTietSanPhamDto request) {
        ApiResponse<ChiTietSanPham> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(ctSanPhamService.create(TranferDatas.convertToEntity(request)));
        return apiResponse;
    }

//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> update(@RequestBody ChiTietSanPham ctSanPham, @PathVariable UUID id){
//        return ResponseEntity.ok(ctSanPhamService.update(ctSanPham,id));
//    }
@PutMapping("/{id}")
ChiTietSanPham update(@RequestBody ChiTietSanPhamDto request, @PathVariable String id) {
    UUID idCTSP = null;
    if (id != null) idCTSP = UUID.fromString(id);
    if (request != null)
        return ctSanPhamService.update(TranferDatas.convertToEntity(request), idCTSP);
    return null;
}


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (ctSanPhamService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(ctSanPhamService.findById(id));
    }
}
