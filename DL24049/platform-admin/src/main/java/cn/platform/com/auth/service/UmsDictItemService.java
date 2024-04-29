package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsDictItemEntity;
import cn.platform.com.auth.model.request.UmsDictItemPageRequest;
import cn.platform.com.auth.model.request.UmsDictItemRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.auth.model.response.UmsDictItemResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface UmsDictItemService extends IService<UmsDictItemEntity> {

    int add(UmsDictItemRequest request);

    int modify(Integer id, UmsDictItemRequest request);

    int delete(Integer id);

    PageResponse<UmsDictItemResponse> page(UmsDictItemPageRequest request);

    List<UmsDictItemResponse> selectByDictCode(String dictCode);
}
