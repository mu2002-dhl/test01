package cn.platform.com.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/19 20:56
 */
@Tag(name = "用户新增响应实体类")
@Data
public class UserAddResponse {

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
}
