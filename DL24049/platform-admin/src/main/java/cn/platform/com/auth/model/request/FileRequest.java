package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-23 20:53
 */
@Data
public class FileRequest {

    @NotEmpty(message = "文件id不能为空")
    @Schema(title = "文件id")
    private String id;

    @NotEmpty(message = "原始文件名称不能为空")
    @Schema(title = "原始文件名称")
    private String originName;

    @NotEmpty(message = "文件后缀名不能为空")
    @Schema(title = "文件后缀名")
    private String extend;

    @NotEmpty(message = "文件大小不能为空")
    @Schema(title = "文件大小")
    private Long size;

    @NotEmpty(message = "文件所属模块不能为空")
    @Schema(title = "文件所属模块")
    private String module;

    @NotEmpty(message = "创建时间不能为空")
    @Schema(title = "创建时间")
    private Date createTime;
}
