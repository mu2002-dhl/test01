package cn.platform.com.auth.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/15 14:35
 */
@Data
public class ForbiddenException extends RuntimeException{
    private int code;
    private String msg;

    public ForbiddenException(String msg){
        super(msg);
        this.msg = msg;
        this.code = HttpStatus.FORBIDDEN.value();
    }
}
