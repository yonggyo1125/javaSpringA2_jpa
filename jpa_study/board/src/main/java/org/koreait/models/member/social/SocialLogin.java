package org.koreait.models.member.social;

import org.koreait.configs.SocialConfig;
import org.springframework.stereotype.Component;

@Component
public class SocialLogin {
    public String getAuthUrl() {
        String url = String.format("https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",
                SocialConfig.restApiKey, SocialConfig.restApiCallback);

        return url;
    }
}
