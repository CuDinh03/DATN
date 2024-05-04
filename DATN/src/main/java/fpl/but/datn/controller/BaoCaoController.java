package fpl.but.datn.controller;

import fpl.but.datn.dto.request.BaoCaoDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IService;
import fpl.but.datn.service.impl.BaoCaoServiceImpl;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/bao-cao")
public class BaoCaoController {

    @Autowired
    private BaoCaoServiceImpl baoCaoService;

    @PostMapping("/addNew")
    ApiResponse<BaoCao> createBaoCao(@RequestBody @Valid BaoCaoDto request) {
        ApiResponse<BaoCao> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(baoCaoService.create(TranferDatas.convertToEntity(request)));

        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<List<BaoCaoDto>> getAccounts() {
        List<BaoCaoDto> listDto = TranferDatas.convertListBaoCaoToDto(baoCaoService.getAll());
        ApiResponse<List<BaoCaoDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách tài khoản thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_ACCOUNTS_FOUND);
        }

        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<BaoCaoDto> getAccount(@PathVariable String id) {
        ApiResponse<BaoCaoDto> apiResponse = new ApiResponse<>();
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        BaoCaoDto dto = TranferDatas.convertToDto(baoCaoService.findById(idAccount));
        apiResponse.setMessage("Lấy tài khoản thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    BaoCao updateAccount(@PathVariable String id, @RequestBody BaoCaoDto request) {
        UUID idAccount = null;
        if (id != null) idAccount = UUID.fromString(id);
        if (request != null)
            return baoCaoService.update(TranferDatas.convertToEntity(request), idAccount);
        return null;
    }

    @DeleteMapping("/{id}")
    String deleteAccount(@PathVariable String id) {
        UUID idAccount = UUID.fromString(id);
        baoCaoService.delete(idAccount);
        return "xoa thanh cong";
    }
}
