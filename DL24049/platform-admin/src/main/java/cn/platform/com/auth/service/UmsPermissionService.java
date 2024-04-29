package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsPermissionEntity;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.model.PermissionTreeNode;
import cn.platform.com.auth.model.request.PermissionAddRequest;
import cn.platform.com.auth.model.request.PermissionModifyRequest;
import cn.platform.com.auth.model.response.PermissionRouterResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsPermissionService extends IService<UmsPermissionEntity> {

    List<PermissionTreeNode> tree();

    List<UmsPermissionEntity> selectMenuByUserId(Long userId);

    List<PermissionRouterResponse> router(List<UmsRoleEntity> umsRoleEntityList);

    int add(PermissionAddRequest request);

    int modify(PermissionModifyRequest request);

    Optional<UmsPermissionEntity> detail(Long id);

    int delete(Long id);

    List<PermissionTreeNode> menu(List<UmsRoleEntity> umsRoleEntityList);
}
