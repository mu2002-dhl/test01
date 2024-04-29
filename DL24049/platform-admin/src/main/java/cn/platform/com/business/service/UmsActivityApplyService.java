package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsActivityApplyEntity;
import cn.platform.com.business.model.request.UmsActivityApplyPageRequest;
import cn.platform.com.business.model.request.UmsActivityApplyRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsActivityApplyResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsActivityApplyService extends IService<UmsActivityApplyEntity> {

    int add(UmsActivityApplyRequest request);

    int modify(Integer id, UmsActivityApplyRequest request);

    int delete(Integer id);

    PageResponse<UmsActivityApplyResponse> page(UmsActivityApplyPageRequest request);
}
