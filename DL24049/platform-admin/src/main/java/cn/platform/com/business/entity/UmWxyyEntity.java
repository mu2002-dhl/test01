package cn.platform.com.business.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("um_wxyy")
public class UmWxyyEntity extends BaseEntity{

     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;
     /**搜索内容*/
     private String search;
     /**返回内容*/
     private String reply;
     /**类型*/
     private String type;
}
