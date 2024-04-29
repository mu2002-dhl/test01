package cn.platform.com.business.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UmsShareResponse {
     /***/
     private Integer id;
     /**标题*/
     private String title;
     /**描述*/
     private String description;
     /**创建时间*/
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     private Date createTime;
     /**创建者*/
     private Long creator;
     private String createName;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
     /**图片地址*/
     private String url;
     /**类型*/
     private String type;
}
