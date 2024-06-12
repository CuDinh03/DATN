package fpl.but.datn.configuration;

import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.repository.TaiKhoanRepository;
import fpl.but.datn.service.impl.ChucVuService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.Date;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;
    ChucVuService chucVuService;
    ChucVuRepository chucVuRepository;

    @Bean
    ApplicationRunner applicationRunner(TaiKhoanRepository repository) {
        return args -> {
            if (repository.findByTenDangNhap("admin").isEmpty()) {
                if (chucVuRepository.findByTen("ADMIN").isEmpty()) {
                    ChucVu chucVu = ChucVu.builder()
                            .ten("ADMIN")
                            .ma("CV01")
                            .ngayTao(new Date())
                            .ngaySua(new Date())
                            .trangThai(Boolean.TRUE)
                            .build();
                    chucVuRepository.save(chucVu);
                    log.warn("ADMIN Role has been created!");

                }
                if (chucVuRepository.findByTen("CUSTOMER").isEmpty()) {
                    ChucVu chucVu = ChucVu.builder()
                            .ten("CUSTOMER")
                            .ma("CV02")
                            .ngayTao(new Date())
                            .ngaySua(new Date())
                            .trangThai(Boolean.TRUE)
                            .build();
                    chucVuRepository.save(chucVu);
                    log.warn("CUSTOMER Role has been created!");

                }
                if (chucVuRepository.findByTen("STAFF").isEmpty()) {
                    ChucVu chucVu = ChucVu.builder()
                            .ten("STAFF")
                            .ma("CV03")
                            .ngayTao(new Date())
                            .ngaySua(new Date())
                            .trangThai(Boolean.TRUE)
                            .build();
                    chucVuRepository.save(chucVu);
                    log.warn("STAFF Role has been created!");

                }

                ChucVu chucVu = chucVuService.getChucVuByName("ADMIN");
                TaiKhoan taiKhoan = TaiKhoan.builder()
                        .tenDangNhap("admin")
                        .ma("TK01")
                        .ngayTao(new Date())
                        .ngaySua(new Date())
                        .idChucVu(chucVu)
                        .trangThai(1)
                        .matKhau(passwordEncoder.encode("admin"))
                        .build();

                repository.save(taiKhoan);
                log.warn("admin user has been created with default password: admin, change it !! ");
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
