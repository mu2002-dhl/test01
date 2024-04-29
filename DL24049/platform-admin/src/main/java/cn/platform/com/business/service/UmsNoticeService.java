package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsNoticeEntity;
import cn.platform.com.business.model.request.UmsNoticePageRequest;
import cn.platform.com.business.model.request.UmsNoticeRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsNoticeResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsNoticeService extends IService<UmsNoticeEntity> {

    int add(UmsNoticeRequest request);

    int modify(Integer id, UmsNoticeRequest request);

    int delete(Integer id);

    PageResponse<UmsNoticeResponse> page(UmsNoticePageRequest request);
}
