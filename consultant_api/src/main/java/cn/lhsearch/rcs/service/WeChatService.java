package cn.lhsearch.rcs.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.lhsearch.rcs.entity.User;

@Service
public class WeChatService {

    @Value("${wechat.app_id}")
    private String appId;
    @Value("${wechat.app_secret}")
    private String appSecret;
    @Value("${wechat.access_token_url}")
    private String accessTokenUrl;
    @Value("${wechat.user_info_url}")
    private String userInfoUrl;

    public User login(String code) {
        User result = new User();

        Map<String, Object> accessTokenResponse = getAccessTokenResponse(code);
        String accessToken = (String)accessTokenResponse.get("access_token");
        if (accessToken == null)
            return result;

        String openId = (String)accessTokenResponse.get("openid");
        String unionId = (String)accessTokenResponse.get("unionid");

        Map<String, Object> userInfoResponse = getUserInfoResponse(accessToken, openId);
        String nickName = (String)userInfoResponse.get("nickname");

        result.setWeChatOpenId(openId);
        result.setWeChatUnionId(unionId);
        result.setWeChatNickName(nickName);
        return result;
    }

    private Map<String, Object> getAccessTokenResponse(String code) {
        Map<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("appid", appId);
        uriVariables.put("secret", appSecret);
        uriVariables.put("code", code);
        uriVariables.put("grant_type", "authorization_code");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(accessTokenUrl, String.class, uriVariables);
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        return jsonParser.parseMap(response);
    }

    private Map<String, Object> getUserInfoResponse(String accessToken, String openId) {
        HashMap<String, Object> uriVariables = new HashMap<>();
        uriVariables.put("access_token", accessToken);
        uriVariables.put("openid", openId);
        uriVariables.put("lang", "zh_CN");
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(accessTokenUrl, String.class, uriVariables);
        JsonParser jsonParser = JsonParserFactory.getJsonParser();
        return jsonParser.parseMap(response);
    }
}
