package cn.platform.com.business.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UmsNoticeResponse {
     /***/
     private Integer id;
     /**标题*/
     private String title;
     /**内容*/
     private String content;
     /**是否最新公告*/
     private String isNew;
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
