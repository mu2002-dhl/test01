package cn.platform.com.auth.util;

import cn.hutool.json.JSONUtil;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/15 14:29
 */
@Slf4j
public class WebUtils {
    /**
     * 将字符串渲染到客户端
     *
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e) {
            log.error("rend obj to web error", e);
        }
        return null;
    }

    /***
     * @title 渲染未授权错误到客户端
     * @param response
     * @return
     */
    public static String renderUnAuthorization(HttpServletResponse response){
        try {
            ResponseData responseData = ResponseData.buildBizCodeEnum(CommonBizCodeEnum.UNAUTHORIZED_ERROR);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(JSONUtil.toJsonStr(responseData));
        }
        catch (IOException e) {
            log.error("rend obj to web error", e);
        }
        return null;
    }
}
