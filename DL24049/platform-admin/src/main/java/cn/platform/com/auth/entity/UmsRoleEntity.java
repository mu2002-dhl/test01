package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Getter
@Setter
@TableName("ums_role")
public class UmsRoleEntity extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    /**角色id*/
    @TableId(type = IdType.AUTO)
    private Long id;

    /**角色编号*/
    private String num;

    /**角色名称*/
    @TableField("`name`")
    private String name;

    /**角色描述*/
    @TableField("`description`")
    private String description;
}
