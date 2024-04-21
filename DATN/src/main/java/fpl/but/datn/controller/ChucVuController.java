package fpl.but.datn.controller;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.service.ChucVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("chucVu")
public class ChucVuController {

    @Autowired
    private ChucVuService chucVuService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(chucVuService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody ChucVu chucVu){
        return ResponseEntity.ok(chucVuService.addNew(chucVu));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ChucVu chucVu, @PathVariable UUID id){
        return ResponseEntity.ok(chucVuService.update(chucVu,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (chucVuService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(chucVuService.findById(id));
    }
}
