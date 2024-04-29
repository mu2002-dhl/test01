package cn.platform.com.business.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UmsCommentResponse {
     /***/
     private Integer id;
     /**关联对象id*/
     private Integer objId;
     /**评论对象*/
     private String type;
     /**描述*/
     private String description;
     /**评分*/
     private String score;
     /**创建时间*/
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
}
