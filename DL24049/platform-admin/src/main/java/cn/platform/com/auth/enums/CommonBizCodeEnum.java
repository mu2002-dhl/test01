package cn.platform.com.auth.enums;

import lombok.Getter;

public enum CommonBizCodeEnum implements BaseBizCode{

    ADD_FAILURE(10001, "新增失败"),
    MODIFY_FAILURE(10002, "修改失败"),
    DELETE_FAILURE(10003, "删除失败"),
    PARAMETER_ERROR(10004, "参数错误"),
    FORBIDDEN_ERROR(10005, "权限不足"),
    UNAUTHORIZED_ERROR(10006, "未授权访问"),
    INTERNAL_SERVER_ERROR(10007, "服务器异常"),
    OPERATOR_DATA_NOT_EXIST(10008, "待操作的数据不存在"),
    NOT_OPERATION_NOT_YOU_SELF_DATA(10009, "无法操作其他人的数据");

    @Getter
    private int code;
    @Getter
    private String message;

    CommonBizCodeEnum(int code, String message){
        this.code = code;
        this.message = message;
    }
}
