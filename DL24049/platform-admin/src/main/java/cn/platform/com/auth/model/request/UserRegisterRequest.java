package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/18 21:50
 */
@Tag(name = "用户",description = "用户注册")
@Data
public class UserRegisterRequest {

    @Schema(title = "登录名称")
    @NotEmpty(message = "登录名称不能为空")
    @Length(min = 6, max = 24, message = "登录名称长度：6-24位")
    private String loginName;

    @Schema(title = "用户昵称")
    @NotEmpty(message = "用户昵称不能为空")
    @Length(max = 12, message = "用户昵称不能超过12位")
    private String nickname;

    @Schema(title = "用户密码")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,max = 24, message = "密码长度范围：6-24位")
    private String password;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "手机号码")
    private String phone;

    private String age;

    @Schema(title = "角色")
    private List<Long> roleIds;
}
