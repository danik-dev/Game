package com.example.game.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    private final AuthenticationProviderService authenticationProvider;

    public SecurityConfig(AuthenticationProviderService authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers("/home", "/sign-up"
//                        , "/game-session-results", "/dashboard", "/leaderboard", "/statistics"
                ).permitAll()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                .and()
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults());
        http.authenticationProvider(authenticationProvider);
        http.formLogin().defaultSuccessUrl("/dashboard", true);
        return http.build();
    }
}
