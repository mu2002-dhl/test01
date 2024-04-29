package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsPlacesRequest {

     /**名称*/
     private String name;
     /**地址*/
     private String address;
     /**门票价格*/
     private String price;
     /**联系方式*/
     private String phone;
     /**描述*/
     private String description;
     /**等级*/
     private String degree;
     /**点击量*/
     private Integer searchCount;
     /**关键词*/
     private String keyWord;
}
