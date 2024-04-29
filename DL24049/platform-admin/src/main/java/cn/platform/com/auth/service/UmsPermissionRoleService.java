package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsPermissionRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsPermissionRoleService extends IService<UmsPermissionRoleEntity> {
    int rolePermissionBinding(Long roleId, List<Long> permissionList);

    List<Long> getRoleAuthPermission(Long roleId);
}
