package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-10 16:47
 */
@Data
@Tag(name = "角色",description = "角色授权权限实体类")
public class RoleAuthPermissionRequest {
    @NotNull(message = "授权角色不能为空")
    private Long roleId;
    private List<Long> permissionList;
}
