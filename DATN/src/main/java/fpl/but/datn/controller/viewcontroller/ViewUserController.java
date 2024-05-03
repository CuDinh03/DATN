package fpl.but.datn.controller.viewcontroller;

import fpl.but.datn.dto.TaiKhoanDto;
import fpl.but.datn.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class ViewUserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String viewAdmin(){

        return "admin/index";
    }

    @GetMapping("/get-all-user")
    public String getAllUser(Model model){
        ResponseEntity<List<TaiKhoanDto>> response = restTemplate.exchange("http://localhost:9091/api/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TaiKhoanDto>>() {}
        );
        List<TaiKhoanDto> users = response.getBody();
        model.addAttribute("users", users);
        return "admin/viewAll";
    }
}
