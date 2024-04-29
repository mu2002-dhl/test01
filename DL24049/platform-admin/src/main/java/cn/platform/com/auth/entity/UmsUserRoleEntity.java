package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Getter
@Setter
@TableName("ums_user_role")
public class UmsUserRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**用户id*/
    private Long userId;

    /**角色id*/
    private Long roleId;
}
