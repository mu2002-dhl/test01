package cn.platform.com.auth.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.entity.UmsPermissionEntity;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.mapper.UmsDeptMapper;
import cn.platform.com.auth.mapper.UmsRoleMapper;
import cn.platform.com.auth.model.LoginUser;
import cn.platform.com.auth.service.UmsPermissionService;
import cn.platform.com.auth.service.UmsUserService;
import cn.platform.com.auth.exception.UnAuthorizationException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 根据用户名查找用户，交给security进行登录校验
 * @Author lih
 * @Data 2023/10/10 21:34
 */
@Service
public class SecurityServiceHandler implements UserDetailsService {

    @Resource
    private UmsUserService umsUserService;
    @Resource
    private UmsPermissionService umsPermissionService;
    @Resource
    private UmsDeptMapper umsDeptMapper;
    @Resource
    private UmsRoleMapper umsRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        LambdaQueryWrapper<UmsUserEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsUserEntity::getLoginName, loginName);
        UmsUserEntity userEntity = umsUserService.getOne(wrapper);
        if(userEntity == null){
            throw new UnAuthorizationException("用户不存在");
        }

        List<String> permissions = null;
        List<UmsPermissionEntity> menuList = umsPermissionService.selectMenuByUserId(userEntity.getId());
        if(CollUtil.isNotEmpty(menuList)){
            permissions = menuList.stream()
                    .filter(permission-> StrUtil.isNotEmpty(permission.getPermission()))
                    .map(permission->permission.getPermission())
                    .collect(Collectors.toList());
        }
        LoginUser loginUser = new LoginUser(userEntity, permissions);
        if(userEntity.getDeptId() != null){
            UmsDeptEntity umsDeptEntity = umsDeptMapper.selectById(userEntity.getDeptId());
            loginUser.setUmsDeptEntity(umsDeptEntity);
        }
        loginUser.setUmsRoleEntityList(umsRoleMapper.selectByUserId(userEntity.getId()));

        return loginUser;
    }
}
