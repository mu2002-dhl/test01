package cn.platform.com.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Data 2024-04-24 14:15
 */
@Data
@AllArgsConstructor
public class WxyyAskMessage {
    private List<WxyyAsk> messages;
}
