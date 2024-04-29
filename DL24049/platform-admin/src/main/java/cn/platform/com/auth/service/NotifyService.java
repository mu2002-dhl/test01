package cn.platform.com.auth.service;

import jakarta.servlet.http.HttpServletRequest;

import java.awt.image.BufferedImage;

public interface NotifyService {

    /**获取captcha code*/
    BufferedImage generateCaptchaImage(HttpServletRequest request);

    /**校验captcha code*/
    boolean checkCaptchaKey(HttpServletRequest request, String code);
}
