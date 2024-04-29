package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsUserRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsUserRoleMapper extends BaseMapper<UmsUserRoleEntity> {
    int countByRoleId(Long roleId);
}
