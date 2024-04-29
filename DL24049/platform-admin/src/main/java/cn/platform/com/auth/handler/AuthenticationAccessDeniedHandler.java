package cn.platform.com.auth.handler;

import cn.hutool.json.JSONUtil;
import cn.platform.com.auth.util.ResponseData;
import cn.platform.com.auth.util.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description 自定义未授权（禁止）访问返回
 * @Author lih
 * @Data 2023/10/15 14:34
 */
@Component
public class AuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    /**
     * 在 Security配置类中 正确配置了 AccessDeniedHandler，但是发现实际运行时 AccessDeniedHandler 没有被触发！
     * 问题原因
     * 出现这种问题的原因一般都是因为项目中还配置了 GlobalExceptionHandler 。
     * 由于GlobalExceptionHandler 全局异常处理器会比 AccessDeniedHandler 先捕获 AccessDeniedException 异常，因此当配置了 GlobalExceptionHandler 后，会发现 AccessDeniedHandler 失效了。
     * 解决方案
     **     原有的 GlobalExceptionHandler 不用修改，只需要增加一个 自定义的 AccessDeniedExceptionHandler 即可。
     */

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseData jsonData = ResponseData.buildCodeAndMsg( HttpStatus.FORBIDDEN.value(), "权限不足");
        WebUtils.renderString(response, JSONUtil.toJsonStr(jsonData));
    }
}
