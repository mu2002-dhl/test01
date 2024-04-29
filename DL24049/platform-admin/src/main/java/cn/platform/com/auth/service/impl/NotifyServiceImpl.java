package cn.platform.com.auth.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.platform.com.auth.service.NotifyService;
import cn.platform.com.auth.util.NetworkUtil;
import com.google.code.kaptcha.Producer;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/24 21:57
 */
@Service
@Slf4j
public class NotifyServiceImpl implements NotifyService {

    Map<String, String> captchaMap = new HashMap<>();

    @Resource
    private Producer captchaProducer;

    @Override
    public BufferedImage generateCaptchaImage(HttpServletRequest request) {
        String captchaText = captchaProducer.createText();
        String captchaKey = getCaptchaKey(request);
        captchaMap.put(captchaKey, captchaText);
        return  captchaProducer.createImage(captchaText);
    }

    @Override
    public boolean checkCaptchaKey(HttpServletRequest request, String code) {
        if(StrUtil.isEmpty(code)){
            return false;
        }

        String captchaKey = getCaptchaKey(request);
        String captchaText = captchaMap.get(captchaKey);
        if(StrUtil.isEmpty(captchaText) || !code.equals(captchaText)){
            return false;
        }
        //验证码为一次性，使用后去掉
        captchaMap.remove(captchaKey);
        return true;
    }

    private String getCaptchaKey(HttpServletRequest request){
        String ipAddr = NetworkUtil.getIpAddr(request);
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        return DigestUtil.md5Hex(ipAddr+userAgent);
    }
}
