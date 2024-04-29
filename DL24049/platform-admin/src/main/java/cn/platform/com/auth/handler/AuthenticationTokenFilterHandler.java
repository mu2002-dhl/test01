package cn.platform.com.auth.handler;

import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.constant.RedisConstant;
import cn.platform.com.auth.model.LoginUser;
import cn.platform.com.auth.util.SecurityUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * @Description 认证过滤器，校验请求头中的token（不认证的接口也要经过这个过滤器）
 * @Author lih
 * @Data 2023/10/14 20:41
 */
@Component
public class AuthenticationTokenFilterHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(StrUtil.isEmpty(token)){
            token = request.getParameter(HttpHeaders.AUTHORIZATION);
        }
        if(StrUtil.isEmpty(token)){
            //没有token交给后续过滤器处理
            filterChain.doFilter(request, response);
            return;
        }

        //解析token
        LoginUser loginUser = SecurityUtil.loginMap.get(token);
        if(loginUser != null){
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            //必须要把用户信息放入SecurityContextHolder中才行，因为后面其他的security自带的过滤器都是从SecurityContextHolder中获得用户信息
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
