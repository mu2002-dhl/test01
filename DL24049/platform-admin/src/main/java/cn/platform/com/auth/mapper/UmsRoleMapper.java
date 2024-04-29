package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsRoleEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsRoleMapper extends BaseMapper<UmsRoleEntity> {
    List<UmsRoleEntity> selectByUserId(Long userId);
}
