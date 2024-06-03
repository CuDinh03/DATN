package fpl.but.datn.controller;

import fpl.but.datn.dto.request.DanhMucDto;
import fpl.but.datn.dto.request.GioHangDto;
import fpl.but.datn.dto.response.ApiResponse;
import fpl.but.datn.entity.GioHang;
import fpl.but.datn.service.IDanhMucService;
import fpl.but.datn.service.IGioHangService;
import fpl.but.datn.tranferdata.TranferDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/gio-hang")
public class GioHangController {

    @Autowired
    private IGioHangService gioHangService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(gioHangService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody GioHang gioHang){
        return ResponseEntity.ok(gioHangService.create(gioHang));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody GioHang gioHang, @PathVariable UUID id){
        return ResponseEntity.ok(gioHangService.update(gioHang,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (gioHangService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }



}
