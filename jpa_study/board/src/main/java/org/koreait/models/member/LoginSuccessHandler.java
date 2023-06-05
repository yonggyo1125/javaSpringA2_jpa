package org.koreait.models.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute("loginField");
        session.removeAttribute("message");
        session.removeAttribute("userId");

        /* 아이디 쿠키 저장 처리 S */
        Cookie cookie = new Cookie("savedId", request.getParameter("userId"));
        if (request.getParameter("saveId") == null) { // 쿠키 삭제 
           cookie.setMaxAge(0);
        } else { // 쿠키 저장
            cookie.setMaxAge(60 * 60 * 24 * 365);
        }
        response.addCookie(cookie);
        /* 아이디 쿠키 저장 처리 E */

        String url = request.getContextPath() + "/";
        response.sendRedirect(url);
    }
}
