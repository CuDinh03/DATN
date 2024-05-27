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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

//@CrossOrigin(origins = "http://localhost:4200")
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
    ApiResponse<Page<TaiKhoanDto>> getAccounts(@RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<TaiKhoan> taiKhoanPage = taiKhoanService.getAllTaiKhoanPageable(pageable);
        List<TaiKhoanDto> listDto = TranferDatas.convertListTaiKhoanToDto(taiKhoanPage.getContent());

        ApiResponse<Page<TaiKhoanDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách tài khoản thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, taiKhoanPage.getTotalElements()));
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/all/{role}")
    public ApiResponse<Page<TaiKhoanDto>> getAccountsByRoles(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "5") int size,
                                                             @PathVariable String role) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TaiKhoan> taiKhoanPage = taiKhoanService.findByRoles(role, pageable);
        List<TaiKhoanDto> listDto = TranferDatas.convertListTaiKhoanToDto(taiKhoanPage.getContent());

        ApiResponse<Page<TaiKhoanDto>> apiResponse = new ApiResponse<>();
        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách tài khoản thành công");
            apiResponse.setResult(new PageImpl<>(listDto, pageable, taiKhoanPage.getTotalElements()));
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
        TaiKhoanDto dto = TranferDatas.convertToDto(taiKhoanService.getByID(idAccount));
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
    ApiResponse<TaiKhoanDto> updateAccount(@PathVariable String id, @RequestBody TaiKhoanDto request) {
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        TaiKhoan taiKhoan = new TaiKhoan();
        if (request != null)
            taiKhoan = taiKhoanService.update(idAccount, TranferDatas.convertToEntity(request));

        return ApiResponse.<TaiKhoanDto>builder().result(TranferDatas.convertToDto(taiKhoan)).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteAccount(@PathVariable String id) {
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        taiKhoanService.delete(idAccount);
        return ApiResponse.<Void>builder().build();
    }

}