package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsActivityEntity;
import cn.platform.com.business.model.request.UmsActivityPageRequest;
import cn.platform.com.business.model.request.UmsActivityRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsActivityResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsActivityService extends IService<UmsActivityEntity> {

    int add(UmsActivityRequest request);

    int modify(Integer id, UmsActivityRequest request);

    int delete(Integer id);

    PageResponse<UmsActivityResponse> page(UmsActivityPageRequest request);
}
