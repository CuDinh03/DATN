package fpl.but.datn.controller;

import fpl.but.datn.dto.request.NhanVienDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.NhanVien;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.INhanVienService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    INhanVienService nhanVienService;

    @GetMapping("/all")
    ApiResponse<List<NhanVienDto>> getAll() {

        List<NhanVienDto> lstNhanVienDto = TranferDatas.convertToListNhanVienDto(nhanVienService.getAll());
        ApiResponse<List<NhanVienDto>> apiResponse = new ApiResponse<>();

        if (!lstNhanVienDto.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach nhan vien");
            apiResponse.setResult(lstNhanVienDto);
        } else {
            throw new AppException(ErrorCode.LIST_NHANVIEN_NOT_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("{id}")
    ApiResponse<NhanVienDto> detail(@PathVariable UUID id) {
        ApiResponse<NhanVienDto> apiResponse = new ApiResponse<>();
        if (id != null) {
            NhanVien nhanVien = nhanVienService.findById(id);
            if (nhanVien != null) {
                NhanVienDto nhanVienDto = TranferDatas.convertNhanVienToDto(nhanVien);
                apiResponse.setMessage("Lấy nhan vien thành công!");
                apiResponse.setResult(nhanVienDto);
            } else {
                throw new AppException(ErrorCode.NHANVIEN_NOT_FOUND);
            }
        } else {
            apiResponse.setMessage("Id không hợp lệ!");
        }
        return apiResponse;
    }

    @PostMapping("/add")
    public ApiResponse<NhanVien> add(@RequestBody @Valid NhanVienDto nhanVienDto) {
        ApiResponse<NhanVien> apiResponse = new ApiResponse<>();
        if (nhanVienDto != null) {
            apiResponse.setResult(nhanVienService.add(TranferDatas.convertToNhanVienEntity(nhanVienDto)));
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    NhanVien update(@PathVariable UUID id,@RequestBody NhanVienDto nhanVienDto) {
        UUID idNhanVien = null;
        if (id != null){
            idNhanVien = id;
        }
        if (idNhanVien != null){
            return nhanVienService.update(TranferDatas.convertToNhanVienEntity(nhanVienDto), idNhanVien);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        UUID idNhanVien = UUID.fromString(id);
        if (nhanVienService.delete(idNhanVien)){
            return "Xoa thanh cong!";
        } else {
            return "Xoa that bai!";
        }
    }
}
