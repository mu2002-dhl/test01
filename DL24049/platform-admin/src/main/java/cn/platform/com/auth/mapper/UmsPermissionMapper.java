package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsPermissionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsPermissionMapper extends BaseMapper<UmsPermissionEntity> {
    List<UmsPermissionEntity> selectMenuByUserId(@Param("userId") Long userId);

    List<UmsPermissionEntity> selectMenuByRoleIdParentId(@Param("roleIdList")List<Long> roleIdList, @Param("parentId")Long parentId);
    List<UmsPermissionEntity> selectMenuByRoleId(@Param("roleIdList")List<Long> roleIdList);
}
