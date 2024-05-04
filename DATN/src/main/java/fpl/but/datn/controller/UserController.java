package fpl.but.datn.controller;

import fpl.but.datn.dto.request.TaiKhoanDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.dto.response.TaiKhoanResponse;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.TaiKhoanService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("/create")
    ApiResponse<TaiKhoan> createAccount(@RequestBody @Valid TaiKhoanDto request) {
        ApiResponse<TaiKhoan> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(taiKhoanService.createAccount(TranferDatas.convertToEntity(request)));

        return apiResponse;
    }


    @GetMapping("/all")
    ApiResponse<List<TaiKhoanDto>> getAccounts() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Ten Dang Nhap: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        List<TaiKhoanDto> listDto = TranferDatas.convertListTaiKhoanToDto(taiKhoanService.getAllTaiKhoan());
        ApiResponse<List<TaiKhoanDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách tài khoản thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }


    @GetMapping("/{id}")
    ApiResponse<TaiKhoanDto> getAccount(@PathVariable String id) {
        ApiResponse<TaiKhoanDto> apiResponse = new ApiResponse<>();
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        TaiKhoanDto dto = TranferDatas.convertToDto(taiKhoanService.getTaiKhoan(idAccount));
        apiResponse.setMessage("Lấy tài khoản thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }

    @GetMapping("/myInfo")
    ApiResponse<TaiKhoanResponse> getMyinfo() {
        return ApiResponse.<TaiKhoanResponse>builder()
                .result(taiKhoanService.getMyInfo()).build();
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
