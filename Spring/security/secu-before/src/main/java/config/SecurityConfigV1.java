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

/**
 * form login, session 관리,
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfigV1 extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 기본 form 로그인 formLogin()
         */
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
                .failureHandler((request, response, exception) -> {
                    log.info("■■■■■■■■■■■ 로그인 실패 ■■■■■■■■■■■");
                    log.info("exception" , exception);
                })
            .permitAll(); // 이걸 해야 로그인 페이지는 인증 없이 접근 가능하다.

        // 로그아웃
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .addLogoutHandler(SecurityConfigV1::logout)
                .deleteCookies("remember-me");

        // Remember-me
        http.rememberMe()
                .rememberMeParameter("remember-me") // 리멤버미 기본 파라미터 명
                .tokenValiditySeconds(3600) // 토큰 유효 시간 default 14일
                .alwaysRemember(false) // true : 리멤버미 기능이 활성화 되어있지 않아도 항상 실행
                .userDetailsService(userDetailsService);

        /**
         * http.sessionManagement() : 세션 관리 기능
         * 1. 세션 관리
         *    - 인증 시 사용자의 세션정보를 등록, 조회, 삭제 등의 세션 이력을 관리
         * 2. 동시적 세션 제어
         *    - 동일 계정으로 접속이 허용되는 최대 세션수를 제한
         * 3. 세션 고정 보호
         *    - 인증 할 때마다 세션쿠키를 새로 발급하여 공격자의 쿠키 조작을 방지
         * 4. 세션 생성 정책
         *    - ALWAYS : 항상 세션 생성
         *    - IF_REQUIRED : 세션이 필요시 생성 (default)
         *    - NEVER : 생성하지 않지만 세션이 존재하면 사용
         *    - STATELESS : 생성도 안하고 사용도 안함 (세션 기능 사용 안 함. JWT 같은 것 사용할 때 설정 필요)
         */
        http.sessionManagement()
                .maximumSessions(1) // 최대 동시 접속 세션 갯수
                .maxSessionsPreventsLogin(true) // 동시 로그인 차단 (default(false) : 기본 세션 만료)
                .expiredUrl("/expired") // 세션 만료 시 이동할 페이지
                .and()
                .invalidSessionUrl("/invalid"); // 세션 유효하지 않을 시 이동할 페이지
        http.sessionManagement()
                .sessionFixation()
                .changeSessionId(); // 기본값
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
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