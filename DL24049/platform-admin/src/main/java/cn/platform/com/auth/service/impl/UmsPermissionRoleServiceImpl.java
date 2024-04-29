package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.platform.com.auth.entity.UmsPermissionEntity;
import cn.platform.com.auth.entity.UmsPermissionRoleEntity;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsPermissionMapper;
import cn.platform.com.auth.mapper.UmsPermissionRoleMapper;
import cn.platform.com.auth.mapper.UmsRoleMapper;
import cn.platform.com.auth.service.UmsPermissionRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Service
public class UmsPermissionRoleServiceImpl extends ServiceImpl<UmsPermissionRoleMapper, UmsPermissionRoleEntity> implements UmsPermissionRoleService {

    @Resource
    private UmsRoleMapper umsRoleMapper;
    @Resource
    private UmsPermissionMapper umsPermissionMapper;

    @Resource
    private UmsPermissionRoleMapper umsPermissionRoleMapper;

    @Override
    public int rolePermissionBinding(Long roleId, List<Long> permissionList) {
        if(roleId == null){
            return 0;
        }

        UmsRoleEntity umsRoleEntity = umsRoleMapper.selectById(roleId);
        if(umsRoleEntity == null){
            throw new BizCustomException(AuthSystemBizCodeEnum.ROLE_AUTH_PERMISSION_NOT_FOND);
        }

        if(CollUtil.isNotEmpty(permissionList)){
            for(Long permissionId : permissionList){
                UmsPermissionEntity umsPermissionEntity = umsPermissionMapper.selectById(permissionId);
                if(umsPermissionEntity == null){
                    throw new BizCustomException(AuthSystemBizCodeEnum.ROLE_AUTH_PERMISSION_NOT_FOND);
                }
            }
        }

        LambdaQueryWrapper<UmsPermissionRoleEntity> deleteWrapper = Wrappers.lambdaQuery();
        deleteWrapper.eq(UmsPermissionRoleEntity :: getRoleId, roleId);
        umsPermissionRoleMapper.delete(deleteWrapper);


        if(CollUtil.isNotEmpty(permissionList)){
            for(Long permissionId : permissionList){
                UmsPermissionRoleEntity umsPermissionRoleEntity = new UmsPermissionRoleEntity();
                umsPermissionRoleEntity.setRoleId(roleId);
                umsPermissionRoleEntity.setPermissionId(permissionId);
                umsPermissionRoleMapper.insert(umsPermissionRoleEntity);
            }
        }
        return 1;
    }

    @Override
    public List<Long> getRoleAuthPermission(Long roleId) {
        if(roleId == null){
            return new ArrayList<>();
        }

        LambdaQueryWrapper<UmsPermissionRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsPermissionRoleEntity :: getRoleId, roleId);

        List<UmsPermissionRoleEntity> permissionRoleList = umsPermissionRoleMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(permissionRoleList)){
            return permissionRoleList.stream().map(entity-> entity.getPermissionId()).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
