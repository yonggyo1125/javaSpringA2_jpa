package org.koreait.controllers.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/social")
public class SocialLoginController {

    @ResponseBody
    @GetMapping("/login")
    public void login(String code) {
        System.out.println(code);
    }

}
