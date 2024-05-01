package fpl.but.datn.configuration;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.repository.TaiKhoanRepository;
import fpl.but.datn.service.impl.ChucVuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    ChucVuService chucVuService;
    @Bean
    ApplicationRunner applicationRunner(TaiKhoanRepository repository){
        return args -> {
           if (repository.findByTenDangNhap("admin").isEmpty()){
               ChucVu chucVu = chucVuService.getChucVu(UUID.fromString("862e5d3b-3e00-4ee6-aa24-4cc4916e69fb"));
               TaiKhoan taiKhoan = TaiKhoan.builder()
                       .tenDangNhap("admin")
                       .idChucVu(chucVu)
                       .matKhau(passwordEncoder.encode("admin"))
                       .build();

               repository.save(taiKhoan);
               log.warn("admin user has been created with default password: admin, change it !! ");
           }
        };
    }
}
