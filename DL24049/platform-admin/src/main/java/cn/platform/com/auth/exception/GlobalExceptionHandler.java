package cn.platform.com.auth.exception;

import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.util.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 全局异常拦截
 * @Author lih
 * @Data 2023/8/29 20:53
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public ResponseData authenticationAccessDeniedException(AccessDeniedException e){
        return ResponseData.buildBizCodeEnum(CommonBizCodeEnum.FORBIDDEN_ERROR);
    }

    @ExceptionHandler(BizCustomException.class)
    @ResponseBody
    public ResponseData bizCustomException(BizCustomException e){
        log.error("【业务异常】: {}", e.getMessage(), e);
        return ResponseData.buildBizCodeEnum(e.getBaseBizCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseData methodArgumentNotValidException(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorBuilder = new StringBuilder();
        for(FieldError fieldError : fieldErrors){
            String field = fieldError.getField();
            String defaultMessage = fieldError.getDefaultMessage();
            errorBuilder.append("【").append(field).append(":").append(defaultMessage).append("】");
        }
        return ResponseData.buildCodeAndMsg(CommonBizCodeEnum.PARAMETER_ERROR.getCode(), errorBuilder.toString());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData handler(Exception e){
        log.error("【系统异常】: {}", e.getMessage(), e);
        return ResponseData.buildCodeAndMsg(CommonBizCodeEnum.INTERNAL_SERVER_ERROR.getCode(), e.getMessage());
    }
}
