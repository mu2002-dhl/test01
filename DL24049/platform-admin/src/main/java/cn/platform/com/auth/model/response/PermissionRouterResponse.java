package cn.platform.com.auth.model.response;

import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2024-01-26 21:47
 */
@Data
public class PermissionRouterResponse {
    private String name;
    private String path;
    private String component;
    private String title;
}
