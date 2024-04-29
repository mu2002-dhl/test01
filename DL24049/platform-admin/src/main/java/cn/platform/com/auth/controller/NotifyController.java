package cn.platform.com.auth.controller;

import cn.platform.com.auth.service.NotifyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/24 22:17
 */
@RestController
@RequestMapping("/notify")
@Slf4j
public class NotifyController {

    @Resource
    private NotifyService notifyService;

    @Operation(summary = "获取图形验证码")
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response){
        BufferedImage bufferedImage = notifyService.generateCaptchaImage(request);
        try(ServletOutputStream outputStream= response.getOutputStream();) {
            ImageIO.write(bufferedImage, "jpg", outputStream);
            outputStream.flush();
        }catch (Exception e){
            log.error("获取图形验证码异常", e);
        }
    }
}
