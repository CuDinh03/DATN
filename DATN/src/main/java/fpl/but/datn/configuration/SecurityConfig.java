package fpl.but.datn.configuration;

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
    private final String[] PUBLIC_ENDPOINT = {"/api/auth/log-in", "/api/users/create"};
    private final String[] ADMIN_ENDPOINT_GET = {
            "/api/users/all","/api/users/{id}", "/api/users/myInfo",
            "/api/voucher/all","/api/voucher/allVouchers","/api/voucher/{id}",
            "/api/khs/all","/api/khs/{sdt}"
    };
    private final String[] ADMIN_ENDPOINT_POST = {"/api/voucher/create",
            "/api/khs/create"
    };
    private final String[] ADMIN_ENDPOINT_PUT = {"/api/voucher/{id}", "/api/users/{id}"};
    private final String[] ADMIN_ENDPOINT_DELETE = {"/api/voucher/{id}","/api/users/{id}"};

    private final String[] CUSTOMER_END_POINT = {};

    @Value("${jwt.signerKey}")
    private String signerKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // cho phep cac patterns co endpoint duoc truy cap
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINT).permitAll()
                .requestMatchers(HttpMethod.GET, ADMIN_ENDPOINT_GET)
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, ADMIN_ENDPOINT_POST)
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, ADMIN_ENDPOINT_DELETE)
                .hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, ADMIN_ENDPOINT_PUT)
                .hasRole("ADMIN")
                .anyRequest().authenticated());

        httpSecurity.oauth2ResourceServer(
                oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );

        //huy cau hinh mac dinh bao ve endpoint cua security
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