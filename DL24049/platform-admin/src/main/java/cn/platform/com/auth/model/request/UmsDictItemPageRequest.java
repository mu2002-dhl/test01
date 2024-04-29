package cn.platform.com.auth.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsDictItemPageRequest extends BasePageRequest {
     /**字典项名称*/
     private String itemName;
     /**字典项内容*/
     private String itemValue;
     private Integer dictId;
}
