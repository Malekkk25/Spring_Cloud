package com.malek.vols.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest
                                                                          request) {
                        CorsConfiguration config = new CorsConfiguration();

                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }).and()


                .authorizeHttpRequests()
                //.antMatchers().permitAll();
                .antMatchers("/api/all/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/getbyid/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/image/get/info/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/api/image/load/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/addvol/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/image/get/uploadImageProd/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/updatevol/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/delvol/**").hasAuthority("ADMIN")
                .antMatchers("/air/**").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated().and()
                .addFilterBefore(new JWTAuthorizationFilter(), BasicAuthenticationFilter.class);

        return http.build();

    }


}