package cn.platform.com.business.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsStorePageRequest extends BasePageRequest {
     /**名称*/
     private String name;
}
