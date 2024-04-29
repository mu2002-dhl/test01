package cn.platform.com.auth.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsDictRequest {

     /**字典编号*/
     private String dictCode;
     /**字典名称*/
     private String dictName;
}
