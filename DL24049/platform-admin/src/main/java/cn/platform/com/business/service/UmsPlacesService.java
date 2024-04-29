package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsPlacesEntity;
import cn.platform.com.business.model.request.UmsPlacesPageRequest;
import cn.platform.com.business.model.request.UmsPlacesRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsPlacesResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsPlacesService extends IService<UmsPlacesEntity> {

    int add(UmsPlacesRequest request);

    int modify(Integer id, UmsPlacesRequest request);

    int delete(Integer id);

    PageResponse<UmsPlacesResponse> page(UmsPlacesPageRequest request);

    UmsPlacesResponse detail(Integer id);
}
