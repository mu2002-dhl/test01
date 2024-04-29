package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/30 21:42
 */
@Data
@Tag(name = "权限",description = "权限新增实体类")
public class PermissionAddRequest {

    @Schema(title = "权限名称")
    @NotEmpty(message = "权限名称不能为空")
    private String name;

    @Schema(title = "权限标识")
    private String permission;

    @Schema(title = "前端访问地址")
    private String url;

    @Schema(title = "权限类型")
    @NotNull(message = "权限类型不能为空")
    private Byte type;

    @Schema(title = "排序")
    private int sort;

    @Schema(title = "上级权限")
    private long parentId;

    @Schema(title = "是否可用")
    private Boolean enable;

    @Schema(title = "图标")
    private String icon;

    @Schema(title = "前端组件名称")
    private String component;
}
