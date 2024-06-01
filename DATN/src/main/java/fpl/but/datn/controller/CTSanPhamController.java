package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChiTietSanPhamDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ct-san-pham")
public class CTSanPhamController {

    @Autowired
    private ICTSanPhamService ctSanPhamService;

    @GetMapping("/all")
    ApiResponse<Page<ChiTietSanPhamDto>> getSanPhamChiTiet(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietSanPham> CTSanPhamPage = ctSanPhamService.getAllCTSanPhamPageable(pageable);

        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(CTSanPhamPage.getContent());

        ApiResponse<Page<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách danh mục thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, CTSanPhamPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }
        return apiResponse;
    }


//    @GetMapping("/all")
//    ApiResponse<List<ChiTietSanPhamDto>> getAll() {
//        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.getAll());
//        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
//
//        if (!listDto.isEmpty()) {
//            apiResponse.setMessage("Lấy danh sách CTSP thành công");
//            apiResponse.setResult(listDto);
//        } else {
//            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
//        }
//        return apiResponse;
//    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody ChiTietSanPham ctSanPham){
        return ResponseEntity.ok(ctSanPhamService.create(ctSanPham));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ChiTietSanPham ctSanPham, @PathVariable UUID id){
        return ResponseEntity.ok(ctSanPhamService.update(ctSanPham,id));
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
