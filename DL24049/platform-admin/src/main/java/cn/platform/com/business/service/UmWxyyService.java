package cn.platform.com.business.service;

import cn.platform.com.auth.model.WxyyResult;
import cn.platform.com.business.entity.UmWxyyEntity;
import cn.platform.com.business.model.request.UmWxyyPageRequest;
import cn.platform.com.business.model.request.UmWxyyRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.request.UmsWxyyAskRequest;
import cn.platform.com.business.model.response.UmWxyyResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmWxyyService extends IService<UmWxyyEntity> {

    int add(UmWxyyRequest request);

    int modify(Integer id, UmWxyyRequest request);

    int delete(Integer id);

    PageResponse<UmWxyyResponse> page(UmWxyyPageRequest request);

    WxyyResult ask(UmsWxyyAskRequest request);
}
