package fpl.but.datn.controller;

import fpl.but.datn.dto.request.BaoCaoDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.IBaoCaoService;
import fpl.but.datn.tranferdata.TranferDatas;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bao-cao")
public class BaoCaoController {

    @Autowired
    private IBaoCaoService baoCaoService;

    @PostMapping("/create")
    ApiResponse<BaoCao> create(@RequestBody @Valid BaoCaoDto request) {
        ApiResponse<BaoCao> apiResponse = new ApiResponse<>();
        if (request != null)
            apiResponse.setResult(baoCaoService.create(TranferDatas.convertToEntity(request)));

        return apiResponse;
    }

    @GetMapping("/all")
    ApiResponse<List<BaoCaoDto>> getAll() {
        List<BaoCaoDto> listDto = TranferDatas.convertListBaoCaoToDto(baoCaoService.getAll());
        ApiResponse<List<BaoCaoDto>> apiResponse = new ApiResponse<>();

        if (!listDto.isEmpty()) {
            apiResponse.setMessage("Lấy danh sách báo cáo thành công");
            apiResponse.setResult(listDto);
        } else {
            throw new AppException(ErrorCode.NO_REPORT_FOUND);
        }

        return apiResponse;
    }


    @GetMapping("/{id}")
    ApiResponse<BaoCaoDto> getBaoCao(@PathVariable String id) {
        ApiResponse<BaoCaoDto> apiResponse = new ApiResponse<>();
        UUID idBaoCao = null;
        if (id != null) idBaoCao = UUID.fromString(id);
        BaoCaoDto dto = TranferDatas.convertToDto(baoCaoService.findById(idBaoCao));
        apiResponse.setMessage("Lấy báo cáo thành công");
        apiResponse.setResult(dto);
        return apiResponse;
    }

    @PutMapping("/{id}")
    BaoCao update(@PathVariable String id, @RequestBody BaoCaoDto request) {
        UUID idBaoCao = null;
        if (id != null) idBaoCao = UUID.fromString(id);
        if (request != null)
            return baoCaoService.update(TranferDatas.convertToEntity(request), idBaoCao);
        return null;
    }

    @DeleteMapping("/{id}")
    String delete(@PathVariable String id) {
        UUID idBaoCao = UUID.fromString(id);
        if (baoCaoService.delete(idBaoCao)) {
            return "xóa thành công";
        } else {
            return "xóa thất bại";

        }

    }
}
