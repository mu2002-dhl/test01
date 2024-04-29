package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/3 19:36
 */
@Data
@Tag(name = "角色",description = "角色修改实体类")
public class RoleModifyRequest {

    @Schema(title = "角色id")
    @NotNull(message = "角色id不能为空")
    private Long id;

    @Schema(title = "角色名称")
    @NotEmpty(message = "角色名称不能为空")
    private String name;


    @Schema(title = "角色编号")
    @NotEmpty(message = "角色编号不能为空")
    private String num;


    @Schema(title = "角色描述")
    private String description;
}
