package cn.platform.com.auth.controller;

import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.util.ResponseData;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 22:04
 */
public class BaseController {

    /**添加返回结果*/
    public ResponseData buildAddResponseData(int affectedRows){
        return affectedRows > 0 ? ResponseData.buildSuccess("添加成功") : ResponseData.buildBizCodeEnum(CommonBizCodeEnum.ADD_FAILURE);
    }

    /**修改返回结果*/
    public ResponseData buildModifyResponseData(int affectedRows){
        return affectedRows > 0 ? ResponseData.buildSuccess("修改成功") : ResponseData.buildBizCodeEnum(CommonBizCodeEnum.MODIFY_FAILURE);
    }

    /**返回删除结果*/
    public ResponseData buildDeleteResponseData(int affectedRows){
        return affectedRows > 0 ? ResponseData.buildSuccess("删除成功") : ResponseData.buildBizCodeEnum(CommonBizCodeEnum.DELETE_FAILURE);
    }
}
