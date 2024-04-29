package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsActivityPageRequest extends BasePageRequest {
     /**名称*/
     private String name;
     /**所属景点*/
     private Integer placeId;
}
