package cn.platform.com.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description
 * @Data 2024-04-26 15:02
 */
@Data
@AllArgsConstructor
public class WxyyResult {
    private String id;
    private String result;
    private Long created;
}
