package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsActivityApplyPageRequest extends BasePageRequest {
     /**活动ID*/
     private Integer activityId;
     /**创建者*/
     private Long creator;
}
