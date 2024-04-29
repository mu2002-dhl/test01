package cn.platform.com.auth.entity;

import cn.platform.com.auth.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

@Data
@TableName("ums_dict_item")
public class UmsDictItemEntity extends BaseEntity{

     /**字典Id*/
     private Integer dictId;
     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;
     /**字典项名称*/
     private String itemName;
     /**字典项内容*/
     private String itemValue;
     /**排序*/
     private Integer sort;
}
