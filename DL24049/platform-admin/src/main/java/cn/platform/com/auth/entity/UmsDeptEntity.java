package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 21:08
 */
@Data
@TableName("ums_dept")
public class UmsDeptEntity extends BaseEntity implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**部门编号*/
    private String deptNum;

    /**部门名称*/
    private String deptName;

    /**父部门id*/
    private long parentId;

    /**排序*/
    private int sort;

    /**部门描述*/
    private String description;
}
