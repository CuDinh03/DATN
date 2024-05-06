package fpl.but.datn.controller;

import fpl.but.datn.dto.request.AuthenticationRequest;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.dto.response.AuthenticationResponse;
import fpl.but.datn.service.impl.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private TaiKhoanService taiKhoanService;
    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
       var result = taiKhoanService.authenticate(request);
       return ApiResponse.<AuthenticationResponse>builder()
               .code(1000)
               .result(result)
               .build();
    }
}
