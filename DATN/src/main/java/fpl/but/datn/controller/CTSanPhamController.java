package fpl.but.datn.controller;

import fpl.but.datn.entity.ChiTietSanPham;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/ct-san-pham")
public class CTSanPhamController {

    @Autowired
    private IService<ChiTietSanPham> ctSanPhamService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(ctSanPhamService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody ChiTietSanPham ctSanPham){
        return ResponseEntity.ok(ctSanPhamService.addNew(ctSanPham));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ChiTietSanPham ctSanPham, @PathVariable UUID id){
        return ResponseEntity.ok(ctSanPhamService.update(ctSanPham,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (ctSanPhamService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(ctSanPhamService.findById(id));
    }
}
