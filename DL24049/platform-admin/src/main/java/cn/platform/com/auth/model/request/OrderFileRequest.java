package cn.platform.com.auth.model.request;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2024-01-16 20:15
 */
@Data
public class OrderFileRequest {
    private Integer orderId;
    private List<FileRequest> fileRequestList;
}
