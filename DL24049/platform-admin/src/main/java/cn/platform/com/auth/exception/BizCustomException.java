package cn.platform.com.auth.exception;

import cn.platform.com.auth.enums.BaseBizCode;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 21:29
 */
@Data
public class BizCustomException extends RuntimeException{
    private int code;
    private String msg;

    private BaseBizCode baseBizCode;

    public BizCustomException(BaseBizCode baseBizCode){
        super(baseBizCode.getMessage());
        this.msg = baseBizCode.getMessage();
        this.code = baseBizCode.getCode();
        this.baseBizCode = baseBizCode;
    }
}
