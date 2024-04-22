package fpl.but.datn.controller;

import fpl.but.datn.entity.DanhMuc;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@Controller
@RequestMapping("/danh-muc")
public class DanhMucController {
    @Autowired
    private IService<DanhMuc> danhMucService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(danhMucService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody DanhMuc danhMuc){
        return ResponseEntity.ok(danhMucService.addNew(danhMuc));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody DanhMuc danhMuc, @PathVariable UUID id){
        return ResponseEntity.ok(danhMucService.update(danhMuc,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (danhMucService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(danhMucService.findById(id));
    }
}
