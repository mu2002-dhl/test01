package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.model.response.RoleResponse;
import cn.platform.com.auth.model.request.RoleAddRequest;
import cn.platform.com.auth.model.request.RoleModifyRequest;
import cn.platform.com.auth.model.request.RolePageRequest;
import cn.platform.com.auth.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsRoleService extends IService<UmsRoleEntity> {

    PageResponse<RoleResponse> page(RolePageRequest request);

    int add(RoleAddRequest request);

    int modify(RoleModifyRequest request);

    int delete(Long id);

    Optional<UmsRoleEntity> detail(Long id);

    List<RoleResponse> all();
}
