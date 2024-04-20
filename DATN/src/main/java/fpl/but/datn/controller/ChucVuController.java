package fpl.but.datn.controller;

import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("chucVu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuRepository;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(chucVuRepository.getAll());
    }
}
