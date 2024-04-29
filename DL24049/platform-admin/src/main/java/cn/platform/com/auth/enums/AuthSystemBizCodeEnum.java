package cn.platform.com.auth.enums;

import lombok.Getter;

public enum AuthSystemBizCodeEnum implements BaseBizCode {

    /**系统级别错误*/
    LOGIN_CAPTCHA_ERROR(20001, "验证码错误"),
    LOGIN_USERNAME_PASSWORD_ERROR(20002, "账号密码错误"),

    /**部门级别错误*/
    DEPT_SAME_LEVEL_NAME_NOT_REPEAT(30001, "同级部门名称不能重复"),
    DEPT_PARENT_NOT_EXIST(30002, "上级部门不存在"),
    DEPT_NOT_DELETE_HAS_CHILDREN(30003, "存在下级部门，无法删除"),
    DEPT_NOT_DELETE_USED_BY_USER(40004, "用户已绑定，无法删除"),

    /**权限级别错误*/
    PERMISSION_PARENT_NOT_EXIST(40001, "上级权限不存在"),
    PERMISSION_TYPE_VALUE_ERROR(40002, "权限类型有误"),
    PERMISSION_TYPE_MENU_MUST_URL(40003, "权限类型为菜单必须设置访问地址"),
    PERMISSION_NOT_DELETE_USED_BY_ROLE(40004, "权限已授权给角色，无法删除"),
    PERMISSION_NOT_DELETE_HAS_CHILDREN(40005, "存在下级权限，无法删除"),

    /**角色级别错误*/
    ROLE_NAME_REPEAT(50001, "角色名称重复"),
    ROLE_NUM_REPEAT(50002, "角色编号重复"),
    ROLE_NOT_DELETE_USED_BY_USER(50003, "角色已被用户使用，无法删除"),
    ROLE_AUTH_ROLE_NOT_FOND(50004, "授权权限的角色不存在"),
    ROLE_AUTH_PERMISSION_NOT_FOND(50005, "授权给角色的权限不存在"),

    /**用户级别错误*/
    USER_PHONE_EXIST(60001, "手机号重复"),
    USER_EMAIL_EXIST(60002, "邮箱重复"),
    USER_DEPT_NOT_EXIST(60003, "用户所属部门不存在"),
    USER_ROLE_NOT_EXIST(60004, "授权给用户的角色不存在"),
    USER_LOGIN_NAME_EXIST(60002, "登录名称重复");

    @Getter
    private int code;
    @Getter
    private String message;

    AuthSystemBizCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
