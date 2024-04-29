package cn.platform.com.auth.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.UUID;
import cn.platform.com.auth.convert.DeptConvert;
import cn.platform.com.auth.convert.RoleConvert;
import cn.platform.com.auth.convert.UserConvert;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.model.LoginUser;
import cn.platform.com.auth.model.request.LoginRequest;
import cn.platform.com.auth.model.response.LoginResponse;
import cn.platform.com.auth.service.NotifyService;
import cn.platform.com.auth.service.UmsPermissionService;
import cn.platform.com.auth.util.ResponseData;
import cn.platform.com.auth.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/12 21:31
 */
@Tag(name = "认证管理")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private NotifyService notifyService;
    @Resource
    private UmsPermissionService umsPermissionService;

    @Operation(summary = "登录")
    @PostMapping("/login")
    public ResponseData login(@Valid @RequestBody LoginRequest user, HttpServletRequest httpRequest){
        //校验验证码
        if(!notifyService.checkCaptchaKey(httpRequest, user.getCaptcha())){
           return ResponseData.buildBizCodeEnum(AuthSystemBizCodeEnum.LOGIN_CAPTCHA_ERROR);
        }

        //校验密码
        //authenticationManager.authenticate调用SecurityServiceHandler.loadUserByUsername方法
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getLoginName(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(authenticate == null){
            return ResponseData.buildBizCodeEnum(AuthSystemBizCodeEnum.LOGIN_USERNAME_PASSWORD_ERROR);
        }

        LoginUser loginUser = (LoginUser)authenticate.getPrincipal();
        loginUser.getUmsUserEntity().setPassword(null);
        String token = UUID.randomUUID().toString(false).replaceAll("-", "");
        loginUser.setToken(token);
        SecurityUtil.loginMap.put(token, loginUser);

        LoginResponse loginResponse = UserConvert.instance.umsUserEntity2LoginResponse(loginUser.getUmsUserEntity());
        loginResponse.setDept(DeptConvert.instance.umsDeptEntity2DeptResponse(loginUser.getUmsDeptEntity()));
        List<UmsRoleEntity> roleList = loginUser.getUmsRoleEntityList();
        if(CollUtil.isNotEmpty(roleList)) {
            loginResponse.setRoleList(roleList.stream().map(entity-> RoleConvert.instance.umsRoleEntity2RoleResponse(entity)).collect(Collectors.toList()));
        }
        loginResponse.setToken(token);
        loginResponse.setRouteList(umsPermissionService.router(roleList));
        loginResponse.setMenuList(umsPermissionService.menu(roleList));
        return ResponseData.buildSuccess(loginResponse, "登录成功");
    }

    @Operation(summary = "退出")
    @GetMapping("/logout")
    public ResponseData logout(){
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String token = loginUser.getToken();
        if(SecurityUtil.loginMap.containsKey(token)) {
            SecurityUtil.loginMap.remove(token);
        }
        return ResponseData.buildSuccess("注销成功");
    }

    @GetMapping("/index")
    @PreAuthorize("hasAuthority('admin')")
    public ResponseData index(){
        return ResponseData.buildSuccess("index");
    }

    @GetMapping("/noAuth")
    @PreAuthorize("hasAuthority('noAuth')")
    public ResponseData noAuth(){
        return ResponseData.buildSuccess("noAuth");
    }
}
