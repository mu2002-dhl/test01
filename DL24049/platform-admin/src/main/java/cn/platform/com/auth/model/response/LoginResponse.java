package cn.platform.com.auth.model.response;

import cn.platform.com.auth.model.PermissionTreeNode;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/26 21:53
 */
@Tag(name = "用户登录响应实体类")
@Data
public class LoginResponse {

    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "登录名称")
    private String loginName;

    @Schema(title = "用户昵称")
    private String nickname;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "手机号码")
    private String phone;

    @Schema(title = "头像地址")
    private String headImg;

    @Schema(title = "令牌")
    private String token;

    @Schema(title = "部门")
    private DeptResponse dept;

    @Schema(title = "角色")
    private List<RoleResponse> roleList;

    @Schema(title = "前端route")
    private List<PermissionRouterResponse> routeList;

    @Schema(title = "导航菜单")
    private List<PermissionTreeNode> menuList;
}
