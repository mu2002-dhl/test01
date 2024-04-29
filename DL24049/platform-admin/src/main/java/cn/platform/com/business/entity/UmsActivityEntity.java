package cn.platform.com.business.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("ums_activity")
public class UmsActivityEntity extends BaseEntity{

     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
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
     /**所属景点*/
     private Integer placeId;
}
