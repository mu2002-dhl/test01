package cn.platform.com.auth.model.request;

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
 * @Data 2023/10/18 21:50
 */
@Tag(name = "用户",description = "用户修改实体类")
@Data
public class UserModifyRequest {

    @Schema(title = "用户id")
    @NotNull(message = "用户id不能为空")
    private Long id;

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
    private Long deptId;

    private String age;
    @Schema(title = "角色")
    private List<Long> roleIds;
}
