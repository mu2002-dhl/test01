package cn.platform.com.auth.util;

import cn.hutool.json.JSONUtil;
import cn.platform.com.auth.enums.BaseBizCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/18 22:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData {
    /**状态码：0-成功，其他-失败*/
    private Integer code;
    /**数据*/
    private Object data;
    /**描述*/
    private String msg;

    public static ResponseData buildSuccess(){
        return new ResponseData(0, null, null);
    }

    public static ResponseData buildSuccess(Object data){
        return new ResponseData(0, data, null);
    }

    public static ResponseData buildSuccess(String msg){
        return new ResponseData(0, null, msg);
    }
    public static ResponseData buildSuccess(Object data, String msg){
        return new ResponseData(0, data, msg);
    }

    public static ResponseData buildCodeAndMsg(int code, String msg) {
        return new ResponseData(code, null, msg);
    }

    public static ResponseData buildBizCodeEnum(BaseBizCode baseBizCode){
        return new ResponseData(baseBizCode.getCode(), null, baseBizCode.getMessage());
    }

    public boolean success(){
        return this.code == 0;
    }

    public <T> T getData(Class<T> cls){
        if(data == null){
            return null;
        }
        return JSONUtil.toBean(JSONUtil.toJsonStr(this.data), cls);
    }

    public <T> List<T> getDataList(Class<T> cls){
        if(data == null){
            return null;
        }

        return JSONUtil.toList(JSONUtil.toJsonStr(this.data), cls);
    }
}
