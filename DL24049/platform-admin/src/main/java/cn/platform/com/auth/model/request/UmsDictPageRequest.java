package cn.platform.com.auth.model.request;

import cn.platform.com.auth.model.request.BasePageRequest;
import lombok.Data;

@Data
public class UmsDictPageRequest extends BasePageRequest {
     /**字典编号*/
     private String dictCode;
     /**字典名称*/
     private String dictName;
}
