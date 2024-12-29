package fpl.but.datn.controller;


import fpl.but.datn.dto.request.ChucVuDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.dto.response.ChucVuResponse;
import fpl.but.datn.service.impl.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/chuc-vu")
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;
    @PostMapping
    ApiResponse<ChucVuResponse> create(@RequestBody ChucVuDto request) {
        return ApiResponse.<ChucVuResponse>builder()
                .result(chucVuService.create(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<ChucVuResponse>> getAll() {
        return ApiResponse.<List<ChucVuResponse>>builder()
                .result(chucVuService.getAll())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable String role) {
        chucVuService.delete(role);
        return ApiResponse.<Void>builder().build();
    }
}
