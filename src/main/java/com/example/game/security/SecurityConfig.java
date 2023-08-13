package com.example.game.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    private final AuthenticationProviderService authenticationProvider;

    public SecurityConfig(AuthenticationProviderService authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringRequestMatchers(
                    new AntPathRequestMatcher("/h2-console/**"), 
                    new AntPathRequestMatcher("/game-session-results")))
                .authorizeHttpRequests()
                    .requestMatchers("/home", "/sign-up").permitAll()
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .and()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                    .formLogin(Customizer.withDefaults())
                    .formLogin(login -> login.defaultSuccessUrl("/dashboard", true))
                .authenticationProvider(authenticationProvider)
                .headers(headers -> headers.frameOptions().sameOrigin())
                .build();
    }
}
