package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsActivityRequest {

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
     /**所属景点*/
     private Integer placeId;
}
