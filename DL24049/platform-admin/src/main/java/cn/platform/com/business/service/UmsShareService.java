package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsShareEntity;
import cn.platform.com.business.model.request.UmsSharePageRequest;
import cn.platform.com.business.model.request.UmsShareRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsShareResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsShareService extends IService<UmsShareEntity> {

    int add(UmsShareRequest request);

    int modify(Integer id, UmsShareRequest request);

    int delete(Integer id);

    PageResponse<UmsShareResponse> page(UmsSharePageRequest request);
}
