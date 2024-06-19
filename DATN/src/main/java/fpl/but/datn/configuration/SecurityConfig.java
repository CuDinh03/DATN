package fpl.but.datn.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final String[] PUBLIC_ENDPOINT = {
            "/api/auth/log-in", "/api/users/create", "/api/users/check-username",
            "/api/chi-tiet-san-pham/getAll", "/api/chi-tiet-san-pham/all/{id}", "/api/chi-tiet-san-pham/{id}",
            "/api/chi-tiet-san-pham/findAllMauSacByMaCTSP/{ma}", "/api/chi-tiet-san-pham/findAllKichThuocByMaCTSP/{ma}",
            "/api/chi-tiet-san-pham/findChiTietSanPhamByMauSacAndKichThuoc/{ma}", "/api/chi-tiet-san-pham/findSanPhamByKichThuoc/{ma}",
            "/api/thanhtoan/onl", "/api/voucher/all", "/api/voucher/allVouchers", "/api/voucher/{id}"


    };

    private final String[] ADMIN_ENDPOINT_GET = {
            "/api/users/all", "/api/users/{id}", "/api/users/myInfo",
            "/api/voucher/all", "/api/voucher/allVouchers", "/api/voucher/{id}",
            "/api/khs/all", "/api/khs/{sdt}",
            "/api/chi-tiet-san-pham/all","/api/chi-tiet-san-pham/addNew", "/api/chi-tiet-san-pham/update/{id}", "/api/chi-tiet-san-pham/delete/{id}", "/api/chi-tiet-san-pham/detail/{id}",
            "/api/danh-muc/all", "/api/danh-muc/{id}",
            "/api/hoa-don-chi-tiet/all/{id}", "/api/hoa-don-chi-tiet/{id}", "/api/gio-hang-chi-tiet/all/{id}",
            "/api/hoa-don/all", "/api/hoa-don/find/{ma}",
            "/api/hoa-don-gio-hang/all", "/api/hoa-don-gio-hang/all/{id}",
            "/api/voucher/all","/api/voucher/allVouchers","/api/voucher/{id}",
            "/api/hoa-don/{ma}","/api/hoa-don/updateTrangThai/{id}"
    };
    private final String[] ADMIN_ENDPOINT_POST = {"/api/voucher/create",
            "/api/khs/create",
            "/api/danh-muc/create",
            "/api/hoa-don-gio-hang/create",
            "/api/gio-hang-chi-tiet/create",
            "/api/thanhtoan",
            "/api/voucher/create", "/api/khs/create", "/api/danh-muc/create",
            "/api/hoa-don-gio-hang/create", "/api/gio-hang-chi-tiet/create", "/api/thanhtoan"
    };

    private final String[] ADMIN_ENDPOINT_PUT = {
            "/api/voucher/{id}", "/api/users/{id}", "/api/danh-muc/{id}"
    };

    private final String[] ADMIN_ENDPOINT_DELETE = {
            "/api/voucher/{id}", "/api/users/{id}", "/api/danh-muc/{id}"
    };

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.GET, ADMIN_ENDPOINT_GET).hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, ADMIN_ENDPOINT_POST).hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, ADMIN_ENDPOINT_DELETE).hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, ADMIN_ENDPOINT_PUT).hasRole("ADMIN")
                .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


}
