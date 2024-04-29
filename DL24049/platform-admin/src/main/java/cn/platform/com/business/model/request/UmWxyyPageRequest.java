package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmWxyyPageRequest extends BasePageRequest {
     /**搜索内容*/
     private String search;
     private String type;
}
