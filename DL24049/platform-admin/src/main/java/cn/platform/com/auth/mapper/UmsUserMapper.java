package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.model.request.UserPageRequest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsUserMapper extends BaseMapper<UmsUserEntity> {

    Page<UmsUserEntity> query(@Param("page") Page<UmsUserEntity> page, @Param("userPageRequest") UserPageRequest userPageRequest);

    List<UmsUserEntity> selectByRoleId(Long roleId);

}
