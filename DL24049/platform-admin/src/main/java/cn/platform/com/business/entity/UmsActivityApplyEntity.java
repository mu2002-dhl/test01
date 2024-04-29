package cn.platform.com.business.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("ums_activity_apply")
public class UmsActivityApplyEntity extends BaseEntity{

     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;
     /**活动ID*/
     private Integer activityId;
}
