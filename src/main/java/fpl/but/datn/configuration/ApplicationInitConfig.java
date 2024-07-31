package fpl.but.datn.configuration;


import fpl.but.datn.constant.PredefinedRole;
import fpl.but.datn.entity.ChucVu;
import fpl.but.datn.entity.TaiKhoan;
import fpl.but.datn.repository.ChucVuRepository;
import fpl.but.datn.repository.TaiKhoanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashSet;


@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationInitConfig {

    PasswordEncoder passwordEncoder;

    @NonFinal
    static final String ADMIN_USER_NAME = "admin";

    @NonFinal
    static final String ADMIN_PASSWORD = "admin";

    @Bean
    ApplicationRunner applicationRunner(TaiKhoanRepository userRepository, ChucVuRepository roleRepository) {
        log.info("Initializing application.....");
        return args -> {
            if (userRepository.findByTenDangNhap(ADMIN_USER_NAME).isEmpty()) {

                if (roleRepository.findById(PredefinedRole.ADMIN_ROLE).isEmpty()){
                    ChucVu chucVu = ChucVu.builder()
                            .name(PredefinedRole.ADMIN_ROLE)
                            .description("Role admin")
                            .build();
                    roleRepository.save(chucVu);
                    log.warn("ADMIN Role has been created!");

                }
                if (roleRepository.findById(PredefinedRole.STAFF_ROLE).isEmpty()){
                    ChucVu chucVu = ChucVu.builder()
                            .name(PredefinedRole.STAFF_ROLE)
                            .description("Role staff")
                            .build();
                    roleRepository.save(chucVu);
                    log.warn("Staff Role has been created!");

                }
                if (roleRepository.findById(PredefinedRole.CUSTOMER_ROLE).isEmpty()){
                    ChucVu chucVu = ChucVu.builder()
                            .name(PredefinedRole.CUSTOMER_ROLE)
                            .description("Role customer")
                            .build();
                    roleRepository.save(chucVu);
                    log.warn("Customer Role has been created!");

                }
                ChucVu chucVu = roleRepository.getReferenceById("ADMIN");


                var roles = new HashSet<ChucVu>();
                roles.add(chucVu);

                TaiKhoan user = TaiKhoan.builder()
                        .tenDangNhap(ADMIN_USER_NAME)
                        .matKhau(passwordEncoder.encode(ADMIN_PASSWORD))
                        .chucVus(roles)
                        .ma("TK01")
                        .ngayTao(new Date())
                        .ngaySua(new Date())
                        .trangThai(1)
                        .build();

                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change it");
            }
            log.info("Application initialization completed .....");
        };
    }
}
