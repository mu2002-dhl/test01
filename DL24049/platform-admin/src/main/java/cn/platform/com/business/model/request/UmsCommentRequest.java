package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsCommentRequest {

     /**关联对象id*/
     private Integer objId;
     /**评论对象*/
     private String type;
     /**描述*/
     private String description;
     /**评分*/
     private String score;
}
