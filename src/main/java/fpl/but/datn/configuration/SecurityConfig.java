package fpl.but.datn.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/**", "/api/users/create", "/api/users/check-username", "/api/users/myInfo",
            "/api/chi-tiet-san-pham/getAll","/api/chi-tiet-san-pham/all/{id}","/api/chi-tiet-san-pham/findAllMauSacByMaCTSP/{ma}",
            "/api/chi-tiet-san-pham/findAllKichThuocByMaCTSP/{ma}", "/api/chi-tiet-san-pham/findChiTietSanPhamByMauSacAndKichThuoc/{ma}","/api/chi-tiet-san-pham/findSanPhamByKichThuoc/{ma}",
            "/api/thanhtoan/onl", "/api/voucher/all", "/api/voucher/allVouchers", "/api/voucher/{id}",
            "/api/hoa-don-chi-tiet/all/{id}",
            "/api/auth/log-in", "/api/users/create", "/api/users/check-username", "/api/users/myInfo",
            "/api/chi-tiet-san-pham/all/{id}", "/api/chi-tiet-san-pham/{id}","/api/chi-tiet-san-pham/findChiTietSanPhamByMauSacAndKichThuoc/{ma}",
            "/api/chi-tiet-san-pham/", "/api/chi-tiet-san-pham/findSanPhamByKichThuoc/{ma}",
            "/api/voucher/all", "/api/voucher/allVouchers", "/api/voucher/{id}",
            "/api/hoa-don-chi-tiet/all/{id}",
            "/api/voucher/create", "/api/khs/create", "/api/danh-muc/create",
            "/api/hoa-don-gio-hang/create", "/api/gio-hang-chi-tiet/create", "/api/thanhtoan",
            "/api/hoa-don-chi-tiet/all/{id}", "/api/hoa-don-chi-tiet/{id}",
            "/api/hoa-don/{id}", "api/hoa-don/byTrangThaiAndKhachHang", "/api/hoa-don/updateTrangThai/{id}","/api/hoa-don/findHd/{ma}","/api/hoa-don/find-time",
            "/api/danh-gia/count/{productId}", "/api/danh-gia/average/{productId}",
            "api/hoa-don/yeuCauSuaHoaDon/{id}",
            "api/mau-sac/getAll",
            "api/kich-thuoc/getAll",
            "api/danh-muc/getAll",
            "/api/chi-tiet-san-pham/filter"
    };

    @Autowired
    private CustomJwtDecoder customJwtDecoder;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                .permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS)
                .permitAll()
                .anyRequest()
                .authenticated());

        httpSecurity.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer
                        .decoder(customJwtDecoder)
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()));
        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
