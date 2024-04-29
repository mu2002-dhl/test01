package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 21:05
 */
@Data
public class BaseEntity implements Serializable {
    /**创建者*/
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private Long creator;

    /**创建时间*/
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**修改者*/
    @TableField(value = "modifier", fill = FieldFill.INSERT_UPDATE)
    private Long modifier;

    /**修改时间*/
    @TableField(value = "modify_time", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
}
