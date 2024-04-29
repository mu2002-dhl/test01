package cn.platform.com.auth.model.response;

import lombok.Data;

import java.util.Date;

@Data
public class UmsDictResponse {
     /**创建时间*/
     private Date createTime;
     /**创建者*/
     private Long creator;
     /**字典编号*/
     private String dictCode;
     /**字典名称*/
     private String dictName;
     /***/
     private Integer id;
     /**修改者*/
     private Long modifier;
     /**修改时间*/
     private Date modifyTime;
}
