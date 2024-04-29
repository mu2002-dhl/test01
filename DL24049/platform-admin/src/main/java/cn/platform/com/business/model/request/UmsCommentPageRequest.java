package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsCommentPageRequest extends BasePageRequest {
     /**关联对象id*/
     private Integer objId;
     /**评论对象*/
     private String type;
}
