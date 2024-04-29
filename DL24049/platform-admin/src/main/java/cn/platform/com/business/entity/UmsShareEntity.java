package cn.platform.com.business.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("ums_share")
public class UmsShareEntity extends BaseEntity{

     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;
     /**标题*/
     private String title;
     /**描述*/
     private String description;
     /**图片地址*/
     private String url;
     /**类型*/
     private String type;
}
