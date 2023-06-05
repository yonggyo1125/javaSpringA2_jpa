package org.koreait.configs;

import org.koreait.models.member.LoginFailureHandler;
import org.koreait.models.member.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(f -> f
                .loginPage("/member/login")
                .usernameParameter("userId")
                .passwordParameter("userPw")
                //.defaultSuccessUrl("/")  // 로그인 성공시 유입될 URL
                //.failureUrl("/member/login") // 로그인 실패시 유입될 URL
                        .failureHandler(new LoginFailureHandler())
                        .successHandler(new LoginSuccessHandler())
             )
                .logout(f -> f
                        .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                        .logoutSuccessUrl("/member/login") // 로그아웃 성공시 URL 
                );

        http.authorizeHttpRequests(f -> f
                .requestMatchers("/mypage/**").authenticated() // 로그인한 회원만 접근 가능한 URL
                .requestMatchers("/admin/**").hasAuthority("ADMIN") // 관리자만 접근 가능한 페이지
                .anyRequest().permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
