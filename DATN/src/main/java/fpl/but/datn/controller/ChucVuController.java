package fpl.but.datn.controller;

import fpl.but.datn.dto.request.ChatLieuDto;
import fpl.but.datn.dto.request.ChucVuDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.ChucVuService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/chuc-vu")
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;
    @PostMapping("/create")
    ApiResponse<ChucVu> create(@RequestBody @Valid ChucVuDto request){
        ApiResponse<ChucVu> apiResponse = new ApiResponse<>();
        if (request != null){
            apiResponse.setResult(chucVuService.create(TranferDatas.convertToEntity(request)));
        }
        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<List<ChucVuDto>> getAll(){
        List<ChucVuDto> dto = TranferDatas.convertListChucVuToDto(chucVuService.getAll());
        ApiResponse<List<ChucVuDto>> apiResponse = new ApiResponse<>();
        if (!dto.isEmpty()){
            apiResponse.setMessage("Lấy danh sách chức vụ thành công");
            apiResponse.setResult(dto);
        }else {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<ChucVuDto> detail(@PathVariable String id){
        ApiResponse<ChucVuDto> apiResponse = new ApiResponse<>();
        UUID idChucVu = null;
        if (id != null){
            idChucVu = UUID.fromString(id);
            ChucVuDto dto = TranferDatas.convertToDto(chucVuService.findById(idChucVu));
            apiResponse.setMessage("Lấy chức vụ thành công");
            apiResponse.setResult(dto);
        }return apiResponse;
    }

    @PutMapping("/{id}")
    ChucVu update(@PathVariable String id, @RequestBody ChucVuDto request){
        UUID idChucVu = null;
        if (id != null){
            idChucVu = UUID.fromString(id);
        }
        if (request != null){
            return chucVuService.update(TranferDatas.convertToEntity(request), idChucVu);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable String id) {
        UUID idChucVu = UUID.fromString(id);
        if (chucVuService.delete(idChucVu)) {
            return "xoa thanh cong";
        } else
            return "xoa that bai";
    }
}
