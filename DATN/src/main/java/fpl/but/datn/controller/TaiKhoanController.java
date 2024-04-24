package fpl.but.datn.controller;

import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/tai-khoan")
public class TaiKhoanController {
    @Autowired
    private InterfaceService<TaiKhoan> taiKhoanService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(taiKhoanService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAll(@RequestBody TaiKhoan taiKhoan){
        return ResponseEntity.ok(taiKhoanService.addNew(taiKhoan));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody TaiKhoan taiKhoan, @PathVariable UUID id){
        return ResponseEntity.ok(taiKhoanService.update(taiKhoan,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (taiKhoanService.delete(id)){
            return ResponseEntity.ok("Xóa thành công");
        }else
            return ResponseEntity.ok("Xóa thất bại");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(taiKhoanService.findById(id));
    }
}
