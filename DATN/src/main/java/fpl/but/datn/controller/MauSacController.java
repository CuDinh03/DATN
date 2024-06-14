package fpl.but.datn.controller;

import fpl.but.datn.dto.request.MauSacDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.MauSac;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IMauSacService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/mau-sac")
public class MauSacController {

    @Autowired
    IMauSacService mauSacService;

    @GetMapping("/all")
    ApiResponse<List<MauSacDto>> getAll() {

        List<MauSacDto> lstMauSacDtos = TranferDatas.convertListMauSacToDto(mauSacService.getAll());
        ApiResponse<List<MauSacDto>> apiResponse = new ApiResponse<>();

        if (!lstMauSacDtos.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach mau sac!");
            apiResponse.setResult(lstMauSacDtos);
        } else {
            throw new AppException(ErrorCode.LIST_COLOR_NOT_FOUND);
        }

        return apiResponse;
    }


    @GetMapping("/all/dang-hoat-dong")
    ApiResponse<List<MauSacDto>> getAllDangHoatDong() {

        List<MauSacDto> lstMauSacDtos = TranferDatas.convertListMauSacToDto(mauSacService.getAllMauSacDangHoatDong());
        ApiResponse<List<MauSacDto>> apiResponse = new ApiResponse<>();

        if (!lstMauSacDtos.isEmpty()) {
            apiResponse.setMessage("Tim thay danh sach mau sac!");
            apiResponse.setResult(lstMauSacDtos);
        } else {
            throw new AppException(ErrorCode.LIST_COLOR_NOT_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<MauSacDto> detail(@PathVariable UUID id) {
        ApiResponse<MauSacDto> apiResponse = new ApiResponse<>();
        if (id != null) {
            MauSac mauSac = mauSacService.findById(id);
            if (mauSac != null) {
                MauSacDto mauSacDto = TranferDatas.convertToDto(mauSac);
                apiResponse.setMessage("Lấy màu sắc thành công!");
                apiResponse.setResult(mauSacDto);
            } else {
                throw new AppException(ErrorCode.COLOR_NOT_FOUND);
            }
        } else {
            apiResponse.setMessage("Id không hợp lệ!");
        }
        return apiResponse;
    }

    @PostMapping("/add")
    ApiResponse<MauSac> add(@RequestBody @Valid MauSacDto mauSacDto) {

        ApiResponse<MauSac> apiResponse = new ApiResponse<>();

        if (mauSacDto != null) {
            MauSac mauSac1 = mauSacService.add(TranferDatas.convertToEntity(mauSacDto));
            apiResponse.setResult(mauSac1);
        }

        return apiResponse;
    }

    @PutMapping("/update/{id}")
    ApiResponse<MauSac> update(@PathVariable UUID id, @RequestBody MauSacDto mauSacDto) {
        ApiResponse<MauSac> apiResponse = new ApiResponse<>();
        if (id != null) {
            UUID idMS = id;
            MauSac mauSac1 = mauSacService.update(TranferDatas.convertToEntity(mauSacDto), idMS);
            apiResponse.setMessage("Update màu sắc thành công!");
            apiResponse.setResult(mauSac1);
            return apiResponse;
        }
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable UUID id){
        if (mauSacService.delete(id)){
            return "Xoa thanh cong!";
        } else {
            return "Xoa that bai";
        }
    }
}
