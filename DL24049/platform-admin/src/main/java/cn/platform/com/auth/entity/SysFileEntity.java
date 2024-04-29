package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-23 20:50
 */
@Data
@TableName("sys_file")
public class SysFileEntity extends BaseEntity{
    private String id;

    /**原始文件名称*/
    private String originName;

    /**文件后缀名*/
    private String extend;

    /**文件文件大小*/
    private Long size;

    /**文件所属模块*/
    private String module;

    private Integer objId;

    /**客户是否选中 0-未选中，1-选中*/
    private int userSelect;
}
