package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsNoticeRequest {

     /**标题*/
     private String title;
     /**内容*/
     private String content;
     /**是否最新公告*/
     private String isNew;
}
