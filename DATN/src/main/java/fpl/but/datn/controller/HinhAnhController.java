package fpl.but.datn.controller;

import fpl.but.datn.entity.HinhAnh;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/hinh-anh")
public class HinhAnhController {
    @Autowired
    private IService<HinhAnh> hinhAnhService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(hinhAnhService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody HinhAnh hinhAnh){
        return ResponseEntity.ok(hinhAnhService.addNew(hinhAnh));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody HinhAnh hinhAnh, @PathVariable UUID id){
        return ResponseEntity.ok(hinhAnhService.update(hinhAnh,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (hinhAnhService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(hinhAnhService.findById(id));
    }
}
