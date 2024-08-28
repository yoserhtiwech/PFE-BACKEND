package com.PFE.EndOfYearProject.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

//import javax.servlet.Filter;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {

    private final JwtFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers(new AntPathRequestMatcher("/User/**"),
                                new AntPathRequestMatcher("/Group/**"),
                                new AntPathRequestMatcher("/Number/**"),
                                new AntPathRequestMatcher("/Calls/**"),
                                new AntPathRequestMatcher("/Contact/**"),
                                new AntPathRequestMatcher("/SMS/**"),
                               new AntPathRequestMatcher("/Auth/**") ,
                                new AntPathRequestMatcher("/Role/**"),
                                new AntPathRequestMatcher("onlineUsers"),
                                new AntPathRequestMatcher("/ws/**")
                        ).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/api/admin/**")).hasRole("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/supervisor/**")).hasRole("SUPERVISOR")
                        .requestMatchers(new AntPathRequestMatcher("/api/user/**")).hasRole("USER")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
