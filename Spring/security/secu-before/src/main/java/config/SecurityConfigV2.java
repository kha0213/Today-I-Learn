package config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfigV2 extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 인가 API 권한 설정
         * 주의 : 구체적인 경로를 먼저 적어주자.
         * authenticated() : 인증된 사용자의 접근을 허용
         * fullyAuthenticated()	: 인증된 사용자의 접근을 허용, rememberMe 인증 제외
         * permitAll()	: 무조건 접근을 허용
         * denyAll()	: 무조건 접근을 허용하지 않음
         * anonymous()	: 익명사용자의 접근을 허용
         * rememberMe() : 기억하기를 통해 인증된 사용자의 접근을 허용
         * access(String)	: 주어진 SpEL 표현식의 평가 결과가 true이면 접근을 허용
         * hasRole(String)	: 사용자가 주어진 역할이 있다면 접근을 허용
         * hasAuthority(String) : 사용자가 주어진 권한이 있다면
         * hasAnyRole(String...) : 사용자가 주어진 권한이 있다면 접근을 허용
         * hasAnyAuthority(String...) : 사용자가 주어진 권한 중 어떤 것이라도 있다면 접근을 허용
         * hasIpAddress(String)	: 주어진 IP로부터 요청이 왔다면 접근을 허용
         */
        http.antMatcher("/auth/**")
                .authorizeRequests()
                .antMatchers("/auth/user").hasRole("USER")
                .antMatchers("/auth/admin/pay").hasRole("ADMIN")
                .antMatchers("/auth/admin/** ").access(" hasAnyRole('ADMIN', 'SYS')")
                .anyRequest()
                .authenticated();
        http.formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
        auth.inMemoryAuthentication().withUser("sys").password("{noop}1111").roles("SYS");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}1111").roles("ADMIN");
    }

    private static void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        log.info("■■■■■■■■■■■ 로그아웃 성공 ■■■■■■■■■■■");
        HttpSession session = request.getSession();
        session.invalidate();
        log.info("■■■■■■■■■■■ 세션 초기화 ■■■■■■■■■■■");
    }
    private static AuthenticationSuccessHandler getSuccessHandler() {
        return (request, response, authentication) -> {
            log.info("■■■■■■■■■■■ 로그인 성공 ■■■■■■■■■■■");
            log.info("authentication : [{}]", authentication);
            response.sendRedirect("/home");
        };
    }
}