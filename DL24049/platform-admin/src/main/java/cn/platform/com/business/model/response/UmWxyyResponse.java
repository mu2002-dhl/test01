package cn.platform.com.business.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UmWxyyResponse {
     /***/
     private Integer id;
     /**搜索内容*/
     private String search;
     /**返回内容*/
     private String reply;
     /**类型*/
     private String type;
     /**创建时间*/
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**修改时间*/
     private Date modifyTime;
     /**修改者*/
     private Long modifier;
}
