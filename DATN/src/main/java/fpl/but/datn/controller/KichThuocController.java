package fpl.but.datn.controller;

import fpl.but.datn.dto.request.KichThuocDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.KichThuoc;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IKichThuocService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/kich-thuoc")
public class KichThuocController {

    @Autowired
    IKichThuocService kichThuocService;

    @GetMapping("/getAll")
    ApiResponse<List<KichThuocDto>> getAll() {

        List<KichThuocDto> lstKichThuocDto = TranferDatas.convertListKichThuocToDto(kichThuocService.getAll());
        ApiResponse<List<KichThuocDto>> apiResponse = new ApiResponse<>();

        if (!lstKichThuocDto.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach kich thuoc");
            apiResponse.setResult(lstKichThuocDto);
        } else {
            throw new AppException(ErrorCode.LIST_KICHTHUOC_NOT_FOUND);
        }
        return apiResponse;
    }


    @GetMapping("/getAll/dang-hoat-dong")
    ApiResponse<List<KichThuocDto>> getAllDangHoatDong() {

        List<KichThuocDto> lstKichThuocDto = TranferDatas.convertListKichThuocToDto(kichThuocService.getAllKichThuocDangHoatDong());
        ApiResponse<List<KichThuocDto>> apiResponse = new ApiResponse<>();

        if (!lstKichThuocDto.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach kich thuoc");
            apiResponse.setResult(lstKichThuocDto);
        } else {
            throw new AppException(ErrorCode.LIST_KICHTHUOC_NOT_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("{id}")
    ApiResponse<KichThuocDto> detail(@PathVariable UUID id) {
        ApiResponse<KichThuocDto> apiResponse = new ApiResponse<>();
        if (id != null) {
            KichThuoc kichThuoc = kichThuocService.findById(id);
            if (kichThuoc != null) {
                KichThuocDto mauSacDto = TranferDatas.convertToDto(kichThuoc);
                apiResponse.setMessage("Lấy kích thước thành công!");
                apiResponse.setResult(mauSacDto);
            } else {
                throw new AppException(ErrorCode.KICHTHUOC_NOT_FOUND);
            }
        } else {
            apiResponse.setMessage("Id không hợp lệ!");
        }
        return apiResponse;
    }

    @PostMapping("/add")
    public ApiResponse<KichThuoc> add(@RequestBody @Valid KichThuocDto kichThuocDto) {
        ApiResponse<KichThuoc> apiResponse = new ApiResponse<>();
        if (kichThuocDto != null) {
            apiResponse.setResult(kichThuocService.add(TranferDatas.convertToEntity(kichThuocDto)));
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    KichThuoc update(@PathVariable UUID id,@RequestBody KichThuocDto kichThuocDto) {
        UUID idKichthuoc = null;
        if (id != null){
            idKichthuoc = id;
        }
        if (idKichthuoc != null){
            return kichThuocService.update(TranferDatas.convertToEntity(kichThuocDto), idKichthuoc);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        UUID idKichthuoc = UUID.fromString(id);
        if (kichThuocService.delete(idKichthuoc)){
            return "Xoa thanh cong!";
        } else {
            return "Xoa that bai!";
        }
    }
}
