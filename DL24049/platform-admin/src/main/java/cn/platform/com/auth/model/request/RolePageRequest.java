package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/3 20:24
 */
@Data
@Tag(name = "角色",description = "角色分页查询实体类")
public class RolePageRequest extends BasePageRequest{

    @Schema(title = "角色名称")
    private String name;

    @Schema(title = "角色编号")
    private String num;
}
