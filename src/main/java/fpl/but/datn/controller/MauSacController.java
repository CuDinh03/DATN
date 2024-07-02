package fpl.but.datn.controller;

import fpl.but.datn.dto.request.MauSacDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.exception.AppException;
import fpl.but.datn.exception.ErrorCode;
import fpl.but.datn.service.impl.MauSacService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mau-sac")
public class MauSacController {

    @Autowired
    private MauSacService mauSacService;


}
