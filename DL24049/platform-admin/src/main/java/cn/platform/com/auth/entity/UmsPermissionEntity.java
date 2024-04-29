package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.Data;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Data
@TableName("ums_permission")
public class UmsPermissionEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**权限名称*/
    private String name;

    /**权限标识*/
    private String permission;

    /**前端访问地址*/
    private String url;

    /**权限类型*/
    private Byte type;

    /**排序*/
    private int sort;

    /**上级权限*/
    private Long parentId;

    /**是否可用*/
    private Boolean enable;

    /**图标*/
    private String icon;

    /**前端组件名称*/
    private String component;
}
