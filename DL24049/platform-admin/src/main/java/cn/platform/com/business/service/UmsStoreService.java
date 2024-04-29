package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsStoreEntity;
import cn.platform.com.business.model.request.UmsStorePageRequest;
import cn.platform.com.business.model.request.UmsStoreRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsStoreResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsStoreService extends IService<UmsStoreEntity> {

    int add(UmsStoreRequest request);

    int modify(Integer id, UmsStoreRequest request);

    int delete(Integer id);

    PageResponse<UmsStoreResponse> page(UmsStorePageRequest request);
}
