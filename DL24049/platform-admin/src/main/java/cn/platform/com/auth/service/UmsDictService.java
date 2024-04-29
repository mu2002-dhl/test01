package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsDictEntity;
import cn.platform.com.auth.model.request.UmsDictPageRequest;
import cn.platform.com.auth.model.request.UmsDictRequest;
import cn.platform.com.auth.model.response.UmsDictResponse;
import cn.platform.com.auth.model.response.PageResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsDictService extends IService<UmsDictEntity> {

    int add(UmsDictRequest request);

    int modify(Integer id, UmsDictRequest request);

    int delete(Integer id);

    PageResponse<UmsDictResponse> page(UmsDictPageRequest request);
}
