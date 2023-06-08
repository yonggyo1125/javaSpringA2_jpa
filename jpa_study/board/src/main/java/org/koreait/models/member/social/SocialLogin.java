package org.koreait.models.member.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.koreait.configs.SocialConfig;
import org.springframework.stereotype.Component;

import java.io.*;
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
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://kauth.kakao.com/oauth/token");

            conn = (HttpURLConnection) url.openConnection();
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
            StringBuffer sb = new StringBuffer(7000);
            try(InputStream in = conn.getInputStream();
                InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                BufferedReader br = new BufferedReader(isr)) {
                String line = null;
                while((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }

            String result = sb.toString();
            ObjectMapper om = new ObjectMapper();
            TokenInfo tokenInfo = om.readValue(result, TokenInfo.class);
            System.out.println(tokenInfo);
            /** 응답 E */


        } catch (Exception e) {
            e.printStackTrace();
            if (conn != null) { // 응답 코드가 200대가 아닌 경우
                  StringBuffer sb = new StringBuffer(7000);
                  try (InputStream in = conn.getErrorStream();
                      InputStreamReader isr = new InputStreamReader(in, "UTF-8");
                      BufferedReader br = new BufferedReader(isr)) {

                      String line = null;
                      while((line = br.readLine()) != null) {
                          sb.append(line);
                      }

                      System.out.println(sb.toString());
                  } catch (IOException e2) {
                      e2.printStackTrace();
                  }
            }

        }
        return null;
    }
}
