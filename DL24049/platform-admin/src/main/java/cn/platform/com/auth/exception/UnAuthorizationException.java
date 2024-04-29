package cn.platform.com.auth.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/11 21:27
 */
@Data
public class UnAuthorizationException extends RuntimeException{
    private String msg;
    private int code;

    public UnAuthorizationException(){
        super();
    }

    public UnAuthorizationException(String msg){
        super(msg);
        this.msg = msg;
        this.code = HttpStatus.UNAUTHORIZED.value();
    }
}
