package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsSharePageRequest extends BasePageRequest {
     /**标题*/
     private String title;
     /**创建者*/
     private Long creator;
     /**类型*/
     private String type;
}
