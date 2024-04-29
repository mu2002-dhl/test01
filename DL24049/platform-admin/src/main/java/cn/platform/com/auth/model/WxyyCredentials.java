package cn.platform.com.auth.model;

import lombok.Data;

/**
 * @Description
 * @Data 2024-04-24 14:08
 */
@Data
public class WxyyCredentials {
    private String refresh_token;
    private String expires_in;
    private String session_key;
    private String access_token;
    private String scope;
    private String session_secret;
}
