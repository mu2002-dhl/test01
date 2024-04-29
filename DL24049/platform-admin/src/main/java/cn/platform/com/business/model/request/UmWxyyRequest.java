package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmWxyyRequest {

     /**搜索内容*/
     private String search;
     /**返回内容*/
     private String reply;
     /**类型*/
     private String type;
}
