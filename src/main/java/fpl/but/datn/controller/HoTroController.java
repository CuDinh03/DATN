package fpl.but.datn.controller;

import fpl.but.datn.entity.HoTro;
import fpl.but.datn.service.IHoTroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/ho-tro")
public class HoTroController {
    @Autowired
    private IHoTroService hoTroService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(hoTroService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody HoTro hoTro){
        return ResponseEntity.ok(hoTroService.create(hoTro));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody HoTro hoTro, @PathVariable UUID id){
        return ResponseEntity.ok(hoTroService.update(hoTro,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (hoTroService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(hoTroService.findById(id));
    }

}
