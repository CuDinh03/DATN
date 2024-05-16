package fpl.but.datn.controller;

import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.service.IHoaDonChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/hoa-don-chi-tiet")
public class HoaDonChiTietController {
    @Autowired
    private IHoaDonChiTietService hoaDonChiTietIService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(hoaDonChiTietIService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody HoaDonChiTiet hoaDonChiTiet){
        return ResponseEntity.ok(hoaDonChiTietIService.create(hoaDonChiTiet));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody HoaDonChiTiet hoaDonChiTiet, @PathVariable UUID id){
        return ResponseEntity.ok(hoaDonChiTietIService.update(hoaDonChiTiet,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (hoaDonChiTietIService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(hoaDonChiTietIService.findById(id));
    }
}
