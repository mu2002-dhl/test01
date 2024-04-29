package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsUserRoleEntity;
import cn.platform.com.auth.entity.UmsUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsUserRoleService extends IService<UmsUserRoleEntity> {
    int userRoleBinding(UmsUserEntity userEntity, List<Long> roleIds);
}
