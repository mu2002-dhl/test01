package cn.platform.com.auth.util;

import cn.hutool.core.collection.CollUtil;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-14 19:58
 */
public class SecurityUtil {

    public static Map<String, LoginUser> loginMap = new HashMap();

    public static LoginUser getCurrentLoginUser(){
        if(SecurityContextHolder.getContext()!=null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            LoginUser loginUser = (LoginUser) authentication.getPrincipal();
            return loginUser;
        }else{
            return null;
        }
    }

    public static Set<String> getRoleNames(){
        List<UmsRoleEntity> umsRoleEntityList = getCurrentLoginUser().getUmsRoleEntityList();
        if(CollUtil.isNotEmpty(umsRoleEntityList)){
            return umsRoleEntityList.stream().map(entity->entity.getName()).collect(Collectors.toSet());
        }

        return new HashSet<>();
    }
}
