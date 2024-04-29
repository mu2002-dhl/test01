package cn.platform.com.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.platform.com.auth.config.SystemConfig;
import cn.platform.com.auth.model.response.SystemResponse;
import cn.platform.com.auth.util.ResponseData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author lih
 * @Data 2024-02-18 16:29
 */
@RestController
@RequestMapping("system")
public class SystemController {

    @Resource
    private SystemConfig systemConfig;

    @GetMapping("info")
    public ResponseData info(){
        SystemResponse systemResponse = BeanUtil.copyProperties(systemConfig, SystemResponse.class);
        return ResponseData.buildSuccess(systemResponse);
    }
}
