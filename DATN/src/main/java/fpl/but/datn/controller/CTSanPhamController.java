package fpl.but.datn.controller;

import fpl.but.datn.dto.request.*;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.*;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.ICTSanPhamService;
import fpl.but.datn.service.IDanhMucService;
import fpl.but.datn.service.impl.CTSanPhamService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/chi-tiet-san-pham")
public class CTSanPhamController {

    @Autowired
    private ICTSanPhamService ctSanPhamService;

    @GetMapping("/all")
    ApiResponse<Page<ChiTietSanPhamDto>> getDanhMuc(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = ctSanPhamService.getAllChiTietSanPhamPageable(pageable);
        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(chiTietSanPhamPage.getContent());
        ApiResponse<Page<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách sa pham thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, chiTietSanPhamPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/findAllKichThuocByMaCTSP/{ma}")
    ApiResponse<List<KichThuocDto>> finAllKichThuocByMaCTSP(@PathVariable String ma) {
        List<KichThuocDto> dto = TranferDatas.convertListKichThuocToDto(ctSanPhamService.findkichThuocsByMaSanPhamChiTiet(ma));
        ApiResponse<List<KichThuocDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()) {
            apiResponse.setMessage("lay danh sach kich thuoc thanh cong");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }


    @GetMapping("/all/sap-xep-ngay-tao")
    ApiResponse<Page<ChiTietSanPhamDto>> getCTSPSapXep(@RequestParam(defaultValue = "0") int page,
                                                       @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChiTietSanPham> chiTietSanPhamPage = ctSanPhamService.getAllChiTietSanPhamPageableSapXepNGayTao(pageable);
        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(chiTietSanPhamPage.getContent());
        ApiResponse<Page<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách san pham thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, chiTietSanPhamPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/getAll")
    ApiResponse<List<ChiTietSanPhamDto>> getAll() {
        List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.getAll());
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
        }

        return apiResponse;
    }

    @PostMapping("/add")
    public ApiResponse<ChiTietSanPham> add(@RequestBody @Valid ChiTietSanPhamDto chiTietSanPhamDto) {
        ApiResponse<ChiTietSanPham> apiResponse = new ApiResponse<>();
        if (chiTietSanPhamDto != null) {
            apiResponse.setResult(ctSanPhamService.create(TranferDatas.convertToEntity(chiTietSanPhamDto)));
            apiResponse.setMessage("Thêm chi tiết sản phẩm thành công");
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    ChiTietSanPham update(@PathVariable UUID id, @RequestBody ChiTietSanPhamDto chiTietSanPhamDto) {
        UUID idCTSP = null;
        if (id != null) {
            idCTSP = id;
            return ctSanPhamService.update(TranferDatas.convertToEntity(chiTietSanPhamDto), idCTSP);
        }
        return null;
    }

    @GetMapping("/findAllMauSacByMaCTSP/{ma}")
    ApiResponse<List<MauSacDto>> finAllMauSacByMaCTSP(@PathVariable String ma) {
        List<MauSacDto> dto = TranferDatas.convertListMauSacToDto(ctSanPhamService.findAllMauSacByMaCTSP(ma));
        ApiResponse<List<MauSacDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()) {
            apiResponse.setMessage("lay danh sach mau sac thanh cong");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }

    @GetMapping("/findChiTietSanPhamByMauSacAndKichThuoc/{ma}")
    ApiResponse<ChiTietSanPhamDto> findAllByKichThuocAndMauSac(@PathVariable String ma, @RequestParam UUID kichThuoc, @RequestParam UUID mauSac) {
        ChiTietSanPhamDto dto = TranferDatas.convertToDto(ctSanPhamService.findChiTietSanPhamByMauSacAndKichThuoc(ma, kichThuoc, mauSac));
        ApiResponse<ChiTietSanPhamDto> apiResponse = new ApiResponse<>();
        if (dto != null) {
            apiResponse.setMessage("lay danh sach san pham thanh cong");
            apiResponse.setResult(dto);
        } else {
            throw new AppException(ErrorCode.NO_PRODUCT_DETAIL_FOUND);
        }
        return apiResponse;
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (ctSanPhamService.delete(id)) {
            return ResponseEntity.ok("xoa thanh cong");
        } else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/{id}")
    ApiResponse<ChiTietSanPhamDto> detail(@PathVariable String id) {
        ApiResponse<ChiTietSanPhamDto> apiResponse = new ApiResponse<>();
        UUID idChiTietSanPham = null;
        if (id != null) {
            idChiTietSanPham = UUID.fromString(id);
            ChiTietSanPhamDto dto = TranferDatas.convertToDto(ctSanPhamService.findById(idChiTietSanPham));
            apiResponse.setMessage("Lấy chi tiết sản phẩm thành công");
            apiResponse.setResult(dto);
        }
        return apiResponse;
    }

    @GetMapping("/updateTrangThai/{id}")
    public ResponseEntity<String> updateTrangThai(@PathVariable UUID id) {
        boolean updated = ctSanPhamService.updateTrangThai(id);
        if (updated) {
            return ResponseEntity.ok("Updated trạng thái thành công cho chi tiết sản phẩm có id = " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy chi tiết sản phẩm có id = " + id);
        }
    }


    // TÌM KIẾM
    @GetMapping("/getBySanPhamId/{id}")
    ApiResponse<List<ChiTietSanPhamDto>> getCTSPBySanPhamId(@PathVariable UUID id) {
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        UUID sanPhamId = null;
        if (id != null) {
            sanPhamId = id;
            List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findCTSPBySanPhamId(sanPhamId));
            apiResponse.setMessage("Lấy danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/getByChatLieuId/{id}")
    ApiResponse<List<ChiTietSanPhamDto>> getCTSPByChatLieuId(@PathVariable UUID id) {
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (id != null) {
            List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findCTSPByChatLieuId(id));
            apiResponse.setMessage("Lấy danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/getByDanhMucId/{id}")
    ApiResponse<List<ChiTietSanPhamDto>> getCTSPByDanhMucId(@PathVariable UUID id) {
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (id != null) {
            List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findCTSPByDanhMucId(id));
            apiResponse.setMessage("Lấy danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/getByKichThuocId/{id}")
    ApiResponse<List<ChiTietSanPhamDto>> getCTSPByKichThuocId(@PathVariable UUID id) {
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (id != null) {
            List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findCTSPByKichThuocId(id));
            apiResponse.setMessage("Lấy danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/getByMauSacId/{id}")
    ApiResponse<List<ChiTietSanPhamDto>> getCTSPByMauSacId(@PathVariable UUID id) {
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (id != null) {
            List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findCTSPByMauSacId(id));
            apiResponse.setMessage("Lấy danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("/getByThuongHieuId/{id}")
    ApiResponse<List<ChiTietSanPhamDto>> getCTSPByThuongHieuId(@PathVariable UUID id) {
        ApiResponse<List<ChiTietSanPhamDto>> apiResponse = new ApiResponse<>();
        if (id != null) {
            List<ChiTietSanPhamDto> listDto = TranferDatas.convertListChiTietSanPhamToDto(ctSanPhamService.findCTSPByThuongHieuId(id));
            apiResponse.setMessage("Lấy danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_LISTSPChiTiet_FOUND);
        }
        return apiResponse;
    }

    @PostMapping("/saveAllSp")
    public ApiResponse<List<ChiTietSanPham>> saveChiTietSanPham(
            @RequestBody SaveCtspRequest saveCtspRequest) {
        ApiResponse<List<ChiTietSanPham>> apiResponse = new ApiResponse<>();
        List<ChiTietSanPham> chiTietSanPhams = ctSanPhamService.saveCtsp(
                saveCtspRequest.getSanPham(),
                saveCtspRequest.getMauSacList(),
                saveCtspRequest.getChatLieu(),
                saveCtspRequest.getDanhMuc(),
                saveCtspRequest.getThuongHieu(),
                saveCtspRequest.getKichThuocList()
        );
        apiResponse.setMessage("Lưu chi tiết sản phẩm thành công");
        apiResponse.setResult(chiTietSanPhams);
        return apiResponse;
    }

    @GetMapping("/getCtsp")
    public ApiResponse<List<ChiTietSanPham>> getCtsp(){
        ApiResponse<List<ChiTietSanPham>> apiResponse = new ApiResponse<>();
        List<ChiTietSanPham> chiTietSanPhams = ctSanPhamService.getCtsp();
        apiResponse.setMessage("Show chi tiết sản phẩm thành công");
        apiResponse.setResult(chiTietSanPhams);
        return apiResponse;
    }

    @PostMapping("/saveListCt")
    public ApiResponse<List<ChiTietSanPham>> saveListCt(@RequestBody List<ChiTietSanPham> list) {
        ApiResponse<List<ChiTietSanPham>> apiResponse = new ApiResponse<>();
        try {
            List<ChiTietSanPham> savedList = ctSanPhamService.saveListCt(list);
            apiResponse.setMessage("Lưu danh sách chi tiết sản phẩm thành công");
            apiResponse.setResult(savedList);
        } catch (Exception e) {
            apiResponse.setMessage("Có lỗi xảy ra khi lưu danh sách chi tiết sản phẩm");
            e.printStackTrace();
        }
        return apiResponse;
    }

}
