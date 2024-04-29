package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/20 21:17
 */
@Tag(name = "用户登录")
@Data
public class LoginRequest {

    @Schema(title = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String loginName;

    @Schema(title = "密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    @Schema(title = "验证码")
    @NotEmpty(message = "验证码不能为空")
    private String captcha;
}
