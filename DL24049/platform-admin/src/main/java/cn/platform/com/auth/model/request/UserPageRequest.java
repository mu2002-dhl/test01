package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-08 21:27
 */
@Data
@Tag(name = "角色",description = "角色分页查询实体类")
public class UserPageRequest extends BasePageRequest{

    @Schema(title = "登录名称")
    private String loginName;

    @Schema(title = "用户昵称")
    private String nickname;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "手机号码")
    private String phone;

    private Integer roleId;
}
