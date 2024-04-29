package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsNoticePageRequest extends BasePageRequest {
     /**标题*/
     private String title;
     /**是否最新公告*/
     private String isNew;
}
