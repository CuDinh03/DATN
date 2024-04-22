package fpl.but.datn.controller;

import fpl.but.datn.entity.ChatLieu;
import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/chat-lieu")
public class ChatLieuController {
    @Autowired
    private IService<ChatLieu> chatLieuService;
    @GetMapping()
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(chatLieuService.getAll());
    }

    @PostMapping("/addNew")
    public ResponseEntity<?> getAll(@RequestBody ChatLieu chatLieu){
        return ResponseEntity.ok(chatLieuService.addNew(chatLieu));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody ChatLieu chatLieu, @PathVariable UUID id){
        return ResponseEntity.ok(chatLieuService.update(chatLieu,id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        if (chatLieuService.delete(id)){
            return ResponseEntity.ok("xoa thanh cong");
        }else
            return ResponseEntity.ok("xoa that bai");
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable UUID id){
        return ResponseEntity.ok(chatLieuService.findById(id));
    }
}
