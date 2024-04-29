package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-08 21:25
 */
@Tag(name = "用户",description = "用户修改密码实体类")
@Data
public class UserChangePwdRequest {

    @Schema(title = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long id;

    @Schema(title = "用户密码")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6,max = 24, message = "密码长度范围：6-24位")
    private String password;
}
