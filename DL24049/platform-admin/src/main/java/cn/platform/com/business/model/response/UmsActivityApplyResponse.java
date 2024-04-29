package cn.platform.com.business.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UmsActivityApplyResponse {
     /***/
     private Integer id;
     /**活动ID*/
     private Integer activityId;
     /**创建时间*/
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
     private Date createTime;
     /**创建者*/
     private Long creator;
     private String nickname;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
}
