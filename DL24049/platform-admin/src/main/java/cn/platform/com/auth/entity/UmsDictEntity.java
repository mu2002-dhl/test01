package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("ums_dict")
public class UmsDictEntity extends BaseEntity{

     /**字典编号*/
     private String dictCode;
     /**字典名称*/
     private String dictName;
     /**主键Id*/
     @TableId(value = "id", type = IdType.AUTO)
     private Integer id;
}
