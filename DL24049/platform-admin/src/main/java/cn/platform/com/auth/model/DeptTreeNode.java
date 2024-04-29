package cn.platform.com.auth.model;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/28 21:17
 */
@Data
public class DeptTreeNode {
    private Long id;
    private String deptName;
    private String deptNum;
    private Long parentId;
    private int sort;
    private String label;
    private String value;
    private List<DeptTreeNode> children;
}
