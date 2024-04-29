package cn.platform.com.business.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UmsStoreResponse {
     /***/
     private Integer id;
     /**名称*/
     private String name;
     /**地址*/
     private String address;
     /**经营范围*/
     private String scope;
     /**法人*/
     private String charge;
     /**联系方式*/
     private String phone;
     /**创建时间*/
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
}
