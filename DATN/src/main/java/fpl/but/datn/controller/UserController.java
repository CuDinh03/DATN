package fpl.but.datn.controller;

import fpl.but.datn.dto.request.TaiKhoanDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.service.impl.TaiKhoanService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping
    ApiResponse<TaiKhoan> createAccount(@RequestBody @Valid TaiKhoanDto request) {
        ApiResponse<TaiKhoan> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(taiKhoanService.createAccount(TranferDatas.convertToEntity(request)));

    return apiResponse;
    }

    @GetMapping
    List<TaiKhoanDto> getAccounts() {
        List<TaiKhoanDto> listDto = TranferDatas.convertListTaiKhoanToDto(taiKhoanService.getAllTaiKhoan());
        return listDto;
    }

    @GetMapping("/{id}")
    TaiKhoanDto getAccount(@PathVariable String id) {
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        TaiKhoanDto dto = TranferDatas.convertToDto(taiKhoanService.getTaiKhoan(idAccount));
        return dto;
    }

    @PutMapping("/{id}")
    TaiKhoan updateAccount(@PathVariable String id, @RequestBody TaiKhoanDto request) {
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        if (request != null)
            return taiKhoanService.updateTaiKhoan(idAccount, TranferDatas.convertToEntity(request));
        return null;
    }

    @DeleteMapping("/{id}")
    String deleteAccount(@PathVariable String id) {
        UUID idAccount = UUID.fromString(id);
        taiKhoanService.deleteTaiKhoan(idAccount);
        return "xoa thanh cong";
    }

}
