package cn.platform.com.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-08 21:29
 */
@Tag(name = "用户",description = "用户详情实体类")
@Data
public class UserResponse {

    @Schema(title = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long id;

    @Schema(title = "登录名称")
    @NotEmpty(message = "登录名称不能为空")
    @Length(min = 6, max = 24, message = "登录名称长度：6-24位")
    private String loginName;

    @Schema(title = "用户昵称")
    @NotEmpty(message = "用户昵称不能为空")
    @Length(max = 12, message = "用户昵称不能超过12位")
    private String nickname;

    @Schema(title = "邮箱")
    @Email
    private String email;

    @Schema(title = "手机号码")
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    private String phone;

    @Schema(title = "头像地址")
    private String headImg;

    @Schema(title = "所属部门")
    private DeptResponse dept;

    private String age;

    @Schema(title = "角色")
    private List<RoleResponse> roleList;

    @Schema(title = "启用状态")
    private Boolean enable;

    @Schema(title = "锁定状态")
    private Boolean locked;
}
