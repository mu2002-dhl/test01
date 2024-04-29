package cn.platform.com.auth.aggregate;

import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.model.request.UserAddRequest;
import cn.platform.com.auth.model.request.UserModifyRequest;
import cn.platform.com.auth.service.UmsUserRoleService;
import cn.platform.com.auth.service.UmsUserService;
import cn.platform.com.auth.exception.BizCustomException;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/***
 * @title 用户聚合层
 */
@Component
public class UmsUserAggregate {

    @Resource
    private UmsUserService umsUserService;

    @Resource
    private UmsUserRoleService umsUserRoleService;

    @Transactional
    public int add(UserAddRequest request){
        UmsUserEntity umsUserEntity = umsUserService.add(request);
        if(umsUserEntity == null || umsUserEntity.getId() == null){
            throw new BizCustomException(CommonBizCodeEnum.ADD_FAILURE);
        }

        return umsUserRoleService.userRoleBinding(umsUserEntity, request.getRoleIds());
    }

    @Transactional
    public int modify(UserModifyRequest request){
        UmsUserEntity umsUserEntity = umsUserService.modify(request);
        if(umsUserEntity == null || umsUserEntity.getId() == null){
            throw new BizCustomException(CommonBizCodeEnum.MODIFY_FAILURE);
        }

        return umsUserRoleService.userRoleBinding(umsUserEntity, request.getRoleIds());
    }
}
