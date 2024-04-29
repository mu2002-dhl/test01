package cn.platform.com.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-22 21:53
 */
@Data
public class FileResponse {

    @Schema(title = "文件id")
    private String id;

    private String uid;

    @Schema(title = "原始文件名称")
    private String originName;

    @Schema(title = "文件后缀名")
    private String extend;

    @Schema(title = "文件文件大小")
    private Long size;

    @Schema(title = "文件所属模块")
    private String module;

    private boolean selected;

    @Schema(title = "创建时间")
    private Date createTime;
}
