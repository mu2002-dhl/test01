package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsShareRequest {

     /**标题*/
     private String title;
     /**描述*/
     private String description;
     /**图片地址*/
     private String url;
     /**类型*/
     private String type;
}
