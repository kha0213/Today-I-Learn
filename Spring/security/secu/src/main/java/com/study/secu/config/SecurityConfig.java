package com.study.secu.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public AuthenticationManager filterChain(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN");
        return auth.build();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                (auth) -> auth.anyRequest().authenticated()
                ).httpBasic();
        http.formLogin()
                //.loginPage("/login.html")   // 사용자 정의 로그인 페이지
                .defaultSuccessUrl("/home") //로그인 성공 후 이동할 페이지
                .failureUrl("/login.html?error=true") // 로그인 실패 후 이동 페이지
                .usernameParameter("username") // 아이디 파라미터명 설정
                .passwordParameter("password") // 패스워드 파라미터명 설정
                .loginProcessingUrl("/login") // 로그인 Form Action Url
                .successHandler(loginSuccessHandler()) // 로그인 성공 후 핸들러
                .failureHandler((request, response, exception) ->
                        log.info("로그인 실패", exception)) // 로그인 실패 후 핸들러
                .permitAll();           // 로그인페이지는 인증 안 받아도 됨

        return http.build();
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        return (request, response, authentication) ->
                log.info("로그인 성공 authentication [{}]", authentication);
    }
}
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(
//                (authz) -> authz.anyRequest().authenticated()
//            ).httpBasic(Customizer.withDefaults());
//    }
//}
