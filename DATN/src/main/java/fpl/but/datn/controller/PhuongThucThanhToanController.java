package fpl.but.datn.controller;


import fpl.but.datn.dto.request.PhuongThucThanhToanDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.PhuongThucThanhToan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IPhuongThucThanhToanService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/phuong-thuc-thanh-toan")
public class PhuongThucThanhToanController {

    @Autowired
    IPhuongThucThanhToanService phuongThucThanhToanService;

    @GetMapping("/all")
    ApiResponse<List<PhuongThucThanhToanDto>> getAll() {

        List<PhuongThucThanhToanDto> lsPhuongThucThanhToanDto = TranferDatas.convertListPTThanhToanToDto(phuongThucThanhToanService.getAll());
        ApiResponse<List<PhuongThucThanhToanDto>> apiResponse = new ApiResponse<>();

        if (!lsPhuongThucThanhToanDto.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach phuong thuc thanh toan");
            apiResponse.setResult(lsPhuongThucThanhToanDto);
        } else {
            throw new AppException(ErrorCode.LIST_PTTHANHTOAN_NOT_FOUND);
        }
        return apiResponse;
    }

    @GetMapping("{id}")
    ApiResponse<PhuongThucThanhToanDto> detail(@PathVariable UUID id) {
        ApiResponse<PhuongThucThanhToanDto> apiResponse = new ApiResponse<>();
        if (id != null) {
            PhuongThucThanhToan phuongThucThanhToan = phuongThucThanhToanService.findById(id);
            if (phuongThucThanhToan != null) {
                PhuongThucThanhToanDto phuongThucThanhToanDto = TranferDatas.convertPTThanhToanToDto(phuongThucThanhToan);
                apiResponse.setMessage("Lấy phương thức thanh toán thành công!");
                apiResponse.setResult(phuongThucThanhToanDto);
            } else {
                throw new AppException(ErrorCode.PTTHANHTOAN_NOT_FOUND);
            }
        } else {
            apiResponse.setMessage("Id không hợp lệ!");
        }
        return apiResponse;
    }

    @PostMapping("/add")
    public ApiResponse<PhuongThucThanhToan> add(@RequestBody @Valid PhuongThucThanhToanDto phuongThucThanhToanDto) {
        ApiResponse<PhuongThucThanhToan> apiResponse = new ApiResponse<>();
        if (phuongThucThanhToanDto != null) {
            apiResponse.setResult(phuongThucThanhToanService.add(TranferDatas.convertPPThanhToanToEntity(phuongThucThanhToanDto)));
        }
        return apiResponse;
    }

    @PutMapping("/update/{id}")
    PhuongThucThanhToan update(@PathVariable UUID id, @RequestBody PhuongThucThanhToanDto phuongThucThanhToanDto) {
        UUID idPhuongThucThanhToan = null;
        if (id != null){
            idPhuongThucThanhToan = id;
        }
        if (idPhuongThucThanhToan != null){
            return phuongThucThanhToanService.update(TranferDatas.convertPPThanhToanToEntity(phuongThucThanhToanDto), idPhuongThucThanhToan);
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        UUID idPhuongThucThanhToan = UUID.fromString(id);
        if (phuongThucThanhToanService.delete(idPhuongThucThanhToan)){
            return "Xoa thanh cong!";
        } else {
            return "Xoa that bai!";
        }
    }

}
