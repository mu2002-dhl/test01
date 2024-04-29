package cn.platform.com.business.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("ums_places")
public class UmsPlacesEntity extends BaseEntity{

     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
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
}
