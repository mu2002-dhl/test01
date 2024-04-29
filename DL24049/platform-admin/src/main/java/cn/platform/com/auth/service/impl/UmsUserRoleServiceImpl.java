package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.entity.UmsUserRoleEntity;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsRoleMapper;
import cn.platform.com.auth.mapper.UmsUserRoleMapper;
import cn.platform.com.auth.service.UmsUserRoleService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Service
public class UmsUserRoleServiceImpl extends ServiceImpl<UmsUserRoleMapper, UmsUserRoleEntity> implements UmsUserRoleService {

    @Resource
    private UmsUserRoleMapper umsUserRoleMapper;
    @Resource
    private UmsRoleMapper umsRoleMapper;

    @Override
    @Transactional
    public int userRoleBinding(UmsUserEntity userEntity, List<Long> roleIds) {
        if(userEntity == null || userEntity.getId() == null){
            return 0;
        }

        if(CollUtil.isNotEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                UmsRoleEntity umsRoleEntity = umsRoleMapper.selectById(roleId);
                if(umsRoleEntity == null){
                    throw new BizCustomException(AuthSystemBizCodeEnum.USER_ROLE_NOT_EXIST);
                }
            }
        }

        LambdaQueryWrapper<UmsUserRoleEntity> deleteWrapper = Wrappers.lambdaQuery();
        deleteWrapper.eq(UmsUserRoleEntity::getUserId, userEntity.getId());
        umsUserRoleMapper.delete(deleteWrapper);

        if(CollUtil.isNotEmpty(roleIds)) {
            for (Long roleId : roleIds) {
                UmsUserRoleEntity umsUserRoleEntity = new UmsUserRoleEntity();
                umsUserRoleEntity.setUserId(userEntity.getId());
                umsUserRoleEntity.setRoleId(roleId);
                umsUserRoleMapper.insert(umsUserRoleEntity);
            }
        }

        return 1;
    }
}
