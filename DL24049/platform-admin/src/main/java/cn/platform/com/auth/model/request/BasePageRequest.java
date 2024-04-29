package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/3 20:24
 */
@Data
public class BasePageRequest {

    @Schema(title = "页大小")
    private int pageSize;

    @Schema(title = "当前页")
    private int pageNum;

    @Schema(title = "排序字段")
    private String orderBy;
}
