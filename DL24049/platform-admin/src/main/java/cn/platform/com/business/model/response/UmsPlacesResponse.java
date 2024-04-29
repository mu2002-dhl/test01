package cn.platform.com.business.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UmsPlacesResponse {
     /***/
     private Integer id;
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
     /**创建时间*/
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
}
