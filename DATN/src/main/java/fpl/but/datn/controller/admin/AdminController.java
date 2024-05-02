package fpl.but.datn.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping()
    public String admin(){
        return "admin/index";
    }

    @GetMapping("/menu")
    public String menu(){
        return "admin/menu";
    }
}
