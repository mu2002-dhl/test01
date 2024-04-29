package cn.platform.com.business.model.request;

import lombok.Data;

import java.util.Date;

@Data
public class UmsStoreRequest {

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
}
