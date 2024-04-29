package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsPermissionRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsPermissionRoleMapper extends BaseMapper<UmsPermissionRoleEntity> {
    int roleAuthPermission(@Param("roleId")Long roleId, @Param("permissionList")List<Long> permissionList);
}
