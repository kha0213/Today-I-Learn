package com.example.secubefore.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin()
//                .loginPage("/login.html") // 사용자정의 로그인 페이지
                .defaultSuccessUrl("/home", true) // 로그인 성공 후 이동페이지
                .failureUrl("/login.html?error=true") // 로그인 실패 후 이동페이지
                .usernameParameter("username") //id 파라미터명
                .passwordParameter("password") // 패스워드 파라미터
                .loginProcessingUrl("/login") // 로그인 form action url
                .successHandler(getSuccessHandler())
//                .failureHandler((request, response, exception) -> {
//                    log.info("■■■■■■■■■■■ 로그인 실패 ■■■■■■■■■■■");
//                    log.info("exception" , exception);
//                })
            .permitAll();
    }
    private static AuthenticationSuccessHandler getSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("■■■■■■■■■■■ 로그인 성공 ■■■■■■■■■■■");
            log.info("authentication : [{}]", authentication);
            response.sendRedirect("/home");
        };
    }
}