package fpl.but.datn.controller;

import fpl.but.datn.entity.HoaDon;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/hoa-don")
public class HoaDonController {
    @Autowired
    private IService<HoaDon> hoaDonIService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(hoaDonIService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody HoaDon hoaDon){
        return ResponseEntity.ok(hoaDonIService.addNew(hoaDon));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody HoaDon hoaDon, @PathVariable UUID id){
        return ResponseEntity.ok(hoaDonIService.update(hoaDon,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (hoaDonIService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(hoaDonIService.findById(id));
    }
}
