package cn.platform.com.business.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UmsActivityResponse {
     /***/
     private Integer id;
     /**名称*/
     private String name;
     /**主办单位*/
     private String unit;
     /**最大人数*/
     private String num;
     /**联系方式*/
     private String phone;
     /**活动描述*/
     private String description;
     /**创建时间*/
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
     /**所属景点*/
     private Integer placeId;
     private String placeName;
     private boolean apply;
     private Integer applyId;
}
