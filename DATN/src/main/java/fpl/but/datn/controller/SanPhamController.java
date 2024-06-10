package fpl.but.datn.controller;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.entity.SanPham;
import fpl.but.datn.service.impl.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/san-pham")
public class SanPhamController {
    @Autowired
    private SanPhamService sanPhamService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(sanPhamService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody SanPham sanPham){
        return ResponseEntity.ok(sanPhamService.create(sanPham));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody SanPham sanPham, @PathVariable UUID id){
        return ResponseEntity.ok(sanPhamService.update(sanPham,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (sanPhamService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(sanPhamService.findById(id));
    }
}