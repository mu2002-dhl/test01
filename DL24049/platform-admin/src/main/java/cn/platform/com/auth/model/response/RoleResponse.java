package cn.platform.com.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/3 20:29
 */
@Data
@Tag(name = "角色",description = "角色详情实体类")
public class RoleResponse {

    @Schema(title = "角色id")
    private Long id;

    @Schema(title = "角色名称")
    private String name;

    @Schema(title = "角色编号")
    private String num;

    @Schema(title = "角色描述")
    private String description;
}
