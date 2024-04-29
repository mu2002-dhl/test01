package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Getter
@Setter
@TableName("ums_permission_role")
public class UmsPermissionRoleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**权限id*/
    private Long permissionId;

    /**角色id*/
    private Long roleId;
}
