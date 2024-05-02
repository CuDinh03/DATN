package fpl.but.datn.controller;

import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.entity.BaoCao;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/bao-cao")
public class BaoCaoController {

    @Autowired
    private IService<BaoCao> baoCaoService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(baoCaoService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody BaoCao baoCao){
        return ResponseEntity.ok(baoCaoService.addNew(baoCao));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody BaoCao baoCao, @PathVariable UUID id){
        return ResponseEntity.ok(baoCaoService.update(baoCao,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (baoCaoService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(baoCaoService.findById(id));
    }
}
