package fpl.but.datn.controller;

import fpl.but.datn.entity.GioHangChiTiet;
import fpl.but.datn.entity.HoaDonChiTiet;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Controller
@RequestMapping("/gio-hang-chi-tiet")
public class GioHangChiTietController {
    @Autowired
    private IService<GioHangChiTiet> gioHangChiTietIService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(gioHangChiTietIService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody GioHangChiTiet gioHangChiTiet){
        return ResponseEntity.ok(gioHangChiTietIService.addNew(gioHangChiTiet));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody GioHangChiTiet gioHangChiTiet, @PathVariable UUID id){
        return ResponseEntity.ok(gioHangChiTietIService.update(gioHangChiTiet,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (gioHangChiTietIService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(gioHangChiTietIService.findById(id));
    }
}
