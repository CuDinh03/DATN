package fpl.but.datn.controller;

import fpl.but.datn.dto.request.BaoCaoDto;
import fpl.but.datn.dto.request.HinhAnhDto;
import fpl.but.datn.dto.request.HoaDonChiTietDto;
import fpl.but.datn.dto.request.ThuongHieuDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.ThuongHieu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IBaoCaoService;
import fpl.but.datn.service.IHinhAnhService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hinh-anh")
public class HinhAnhController {

    @Autowired
    private IHinhAnhService hinhAnhService;
    @GetMapping("/getAll")
    ApiResponse<List<HinhAnhDto>> getAll() {
        List<HinhAnhDto> listDto = TranferDatas.convertListHinhAnhToDto(hinhAnhService.getAll());
        ApiResponse<List<HinhAnhDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hinh anh thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_IMAGES_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<Page<HinhAnhDto>> getHinhAnh(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<HinhAnh> hinhAnhPage = hinhAnhService.getAllHinhAnhPageable(pageable);
        List<HinhAnhDto> listDto = TranferDatas.convertListHinhAnhToDto(hinhAnhPage.getContent());

        ApiResponse<Page<HinhAnhDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách hình ảnnh thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, hinhAnhPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_IMAGES_FOUND);
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

    @PutMapping("/{id}")
    HinhAnh update(@RequestBody HinhAnhDto request, @PathVariable String id) {
        UUID idHinhAnh = null;
        if (id != null) {
            idHinhAnh = UUID.fromString(id);
        }
        if (request != null) {
            return hinhAnhService.update(TranferDatas.convertToEntity(request), idHinhAnh);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id) {
        UUID idHinhAnh = null;
        if (id != null) {
            idHinhAnh = UUID.fromString(id);
            hinhAnhService.delete(idHinhAnh);
        } return ApiResponse.<Void>builder().build();
    }

    @DeleteMapping("/open/{id}")
    ApiResponse<Void> open(@PathVariable String id) {
        UUID idHinhAnh = null;
        if (id != null) {
            idHinhAnh = UUID.fromString(id);
            hinhAnhService.open(idHinhAnh);
        } return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/{id}")
    ApiResponse<HinhAnhDto> detail(@PathVariable String id) {
        ApiResponse<HinhAnhDto> apiResponse = new ApiResponse<>();
        UUID idHinhAnh = null;
        if (id != null){
            idHinhAnh = UUID.fromString(id);
            HinhAnhDto dto = TranferDatas.convertToDto(hinhAnhService.findById(idHinhAnh));
            apiResponse.setMessage("Lấy hình ảnh thành công");
            apiResponse.setResult(dto);
        }return apiResponse;
    }

    @GetMapping("/all/{id}")
    ApiResponse<List<HinhAnhDto>> getAllByChiTietSanPham(@PathVariable("id") UUID idChiTietSanPham){
        List<HinhAnhDto> dto = TranferDatas.convertListHinhAnhToDto(hinhAnhService.finAllByChiTietSanPham(idChiTietSanPham));
        ApiResponse<List<HinhAnhDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách Hinh ảnh thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.NO_IMAGES_FOUND);
        }
        return apiResponse;
    }
}
