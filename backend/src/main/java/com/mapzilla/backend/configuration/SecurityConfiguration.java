package com.mapzilla.backend.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthConverter jwtAuthConverter;

//    @Bean
//    @Profile("dev")
//    public SecurityFilterChain securityFilterChainDev(HttpSecurity http) throws Exception {
//        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(
//                        auth ->
//                                auth.requestMatchers("/hello-world")
//                                        .permitAll()
//                                        .requestMatchers("/error")
//                                        .permitAll()
//                                        .anyRequest()
//                                        .permitAll())
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/api/v0/**").permitAll()
                                .requestMatchers("/hello-world").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/v0/**").permitAll()
                        .anyRequest().authenticated()
//                                .anyRequest().permitAll() // so far we let that say
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(new JwtAuthConverter())
                        )
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }


    private CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(List.of("*"));
            configuration.setAllowedMethods(List.of("*"));
            configuration.setAllowedHeaders(List.of("*"));
            return configuration;
        };
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://keycloak:8080/realms/Mapzilla/protocol/openid-connect/certs").build();
    }


    //TODO: think of it later, maybe use it or maybe throw it away
//    @Bean
//    public JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
//        converter.setJwtGrantedAuthoritiesConverter(
//                jwt -> {
//                    @SuppressWarnings("unchecked")
//                    var realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");
//                    @SuppressWarnings("unchecked")
//                    var roles = (List<String>) realmAccess.get("roles");
//
//                    // Convert Keycloak roles to Spring Security authorities
//                    return roles.stream()
//                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                            .collect(Collectors.toList());
//                });
//        return converter;
//    }
}


