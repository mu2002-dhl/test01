package cn.platform.com.business.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("ums_store")
public class UmsStoreEntity extends BaseEntity{

     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
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
}
