package fpl.but.datn.controller;

import fpl.but.datn.entity.ThanhToan;
import fpl.but.datn.service.InterfaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/thanh-toan")
public class ThanhToanController {

    @Autowired
    private InterfaceService<ThanhToan> thanhToanService;

    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(thanhToanService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<?> getAll(@RequestBody ThanhToan thanhToan){
        return ResponseEntity.ok(thanhToanService.addNew(thanhToan));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ThanhToan thanhToan, @PathVariable UUID id){
        return ResponseEntity.ok(thanhToanService.update(thanhToan,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (thanhToanService.delete(id)){
            return ResponseEntity.ok("Xóa thành công");
        }else
            return ResponseEntity.ok("Xóa thất bại");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(thanhToanService.findById(id));
    }

}
