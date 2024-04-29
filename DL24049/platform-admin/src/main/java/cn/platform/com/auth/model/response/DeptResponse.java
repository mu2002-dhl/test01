package cn.platform.com.auth.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/30 15:01
 */
@Data
@Tag(name = "部门",description = "部门详情实体类")
public class DeptResponse {
    @Schema(title = "部门Id")
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
