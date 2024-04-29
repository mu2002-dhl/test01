package cn.platform.com.auth.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsDictItemRequest {

     /**字典Id*/
     private Integer dictId;
     /**字典项名称*/
     private String itemName;
     /**字典项内容*/
     private String itemValue;
     /**排序*/
     private Integer sort;
}
