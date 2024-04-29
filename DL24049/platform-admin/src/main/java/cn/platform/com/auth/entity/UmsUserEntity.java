package cn.platform.com.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Getter
@Setter
@TableName("ums_user")
public class UmsUserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**用户id*/
    @TableId(type = IdType.AUTO)
    private Long id;

    /**登录名称*/
    @TableField("login_name")
    private String loginName;

    /**用户昵称*/
    @TableField("nickname")
    private String nickname;

    /**用户密码*/
    @TableField("`password`")
    private String password;

    /**邮箱*/
    @TableField("email")
    private String email;

    /**手机号码*/
    @TableField("phone")
    private String phone;

    /**头像地址*/
    @TableField("head_img")
    private String headImg;

    /**账号过期时间*/
    @TableField("account_expired_time")
    private Date accountExpiredTime;

    /**密码过期时间*/
    @TableField("pwd_expired_time")
    private Date pwdExpiredTime;

    /**启用状态*/
    @TableField("`enable`")
    private Boolean enable;

    /**锁定状态*/
    @TableField("`locked`")
    private Boolean locked;
    private String age;

    /**所属部门*/
    @TableField("`dept_id`")
    private Long deptId;
}
