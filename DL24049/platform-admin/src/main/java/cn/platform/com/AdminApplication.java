package cn.platform.com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * @Description
 * @Author lih
 * @Data 2023/10/8 21:44
 */
@SpringBootApplication
@MapperScan({
        "cn.platform.com.auth.mapper",
        "cn.platform.com.business.mapper"
})
public class AdminApplication {
    //前端启动：cd  到
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
}
