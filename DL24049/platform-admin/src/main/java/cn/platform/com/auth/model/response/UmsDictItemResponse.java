package cn.platform.com.auth.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UmsDictItemResponse {
     /**创建时间*/
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**字典Id*/
     private Integer dictId;
     /***/
     private Integer id;
     /**字典项名称*/
     private String itemName;
     /**字典项内容*/
     private String itemValue;
     /**修改者*/
     private Long modifier;
     /**修改时间*/
     private Date modifyTime;
     /**排序*/
     private Integer sort;
}
