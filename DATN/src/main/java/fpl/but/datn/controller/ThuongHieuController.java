package fpl.but.datn.controller;

import fpl.but.datn.entity.ThuongHieu;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/thuong-hieu")
public class ThuongHieuController {

    @Autowired
    private InterfaceService<ThuongHieu> thuongHieuService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(thuongHieuService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAll(@RequestBody ThuongHieu thuongHieu){
        return ResponseEntity.ok(thuongHieuService.addNew(thuongHieu));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ThuongHieu thuongHieu, @PathVariable UUID id){
        return ResponseEntity.ok(thuongHieuService.update(thuongHieu,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (thuongHieuService.delete(id)){
            return ResponseEntity.ok("Xóa thành công");
        }else
            return ResponseEntity.ok("Xóa thất bại");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(thuongHieuService.findById(id));
    }
}
