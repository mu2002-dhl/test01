package cn.platform.com.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/1 11:49
 */
@Data
public class PermissionTreeNode {
    @Schema(title = "权限Id")
    private Long id;

    @Schema(title = "权限名称")
    private String name;

    @Schema(title = "权限标识")
    private String permission;

    @Schema(title = "前端访问地址")
    private String url;

    @Schema(title = "权限类型")
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

    private List<PermissionTreeNode> children;
}
