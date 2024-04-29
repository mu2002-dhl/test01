package cn.platform.com.auth.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 21:14
 */
@Data
@Tag(name = "部门",description = "部门修改实体类")
public class DeptModifyRequest {

    @Schema(title = "部门id")
    @NotNull(message = "部门id不能为空")
    private Long id;

    @Schema(title = "部门编号")
    private String deptNum;

    @Schema(title = "部门名称")
    @NotEmpty(message = "部门名称不能为空")
    private String deptName;

    @Schema(title = "上级部门id")
    private long parentId;

    @Schema(title = "排序")
    private int sort;

    @Schema(title = "部门描述")
    private String description;
}
