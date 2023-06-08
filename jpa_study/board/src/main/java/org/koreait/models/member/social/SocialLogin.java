package org.koreait.models.member.social;

import org.koreait.configs.SocialConfig;
import org.springframework.stereotype.Component;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class SocialLogin {
    public String getAuthUrl() {
        String url = String.format("https://kauth.kakao.com/oauth/authorize?client_id=%s&redirect_uri=%s&response_type=code",
                SocialConfig.restApiKey, SocialConfig.restApiCallback);

        return url;
    }

    public String getAccessToken(String code) {
        /**
         * https://kauth.kakao.com/oauth/token
         * grant_type=authorization_code
         * client_id=
         * redirect_uri=
         * code=
         */

        try {
            URL url = new URL("https://kauth.kakao.com/oauth/token");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);

            /** 요청 S */
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

            String params = String.format("grant_type=authorization_code&client_id=%s&redirect_uri=%s&code=%s", SocialConfig.restApiKey, SocialConfig.restApiCallback, code);

            try (OutputStream out = conn.getOutputStream()) {
                // Body 출력
                out.write(params.getBytes());
            }
            /** 요청 E */
            /** 응답 S */

            /** 응답 E */


        } catch (Exception e) {

        }
        return null;
    }
}
