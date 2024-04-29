package cn.platform.com.business.service;

import cn.platform.com.business.entity.UmsCommentEntity;
import cn.platform.com.business.model.request.UmsCommentPageRequest;
import cn.platform.com.business.model.request.UmsCommentRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsCommentResponse;
import com.baomidou.mybatisplus.extension.service.IService;


public interface UmsCommentService extends IService<UmsCommentEntity> {

    int add(UmsCommentRequest request);

    int modify(Integer id, UmsCommentRequest request);

    int delete(Integer id);

    PageResponse<UmsCommentResponse> page(UmsCommentPageRequest request);
}
