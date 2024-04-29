package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.business.entity.UmsStoreEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmsStoreMapper;
import cn.platform.com.business.model.request.UmsStorePageRequest;
import cn.platform.com.business.model.request.UmsStoreRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsStoreResponse;
import cn.platform.com.business.service.UmsStoreService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsStoreServiceImpl extends ServiceImpl<UmsStoreMapper, UmsStoreEntity> implements UmsStoreService {

    @Override
    public int add(UmsStoreRequest request) {
        UmsStoreEntity umsStoreEntity = BeanUtil.copyProperties(request, UmsStoreEntity.class);
        return baseMapper.insert(umsStoreEntity);
    }

    @Override
    public int modify(Integer id, UmsStoreRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsStoreEntity umsStoreEntity = BeanUtil.copyProperties(request, UmsStoreEntity.class);
        umsStoreEntity.setId(id);

        return baseMapper.updateById(umsStoreEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsStoreResponse> page(UmsStorePageRequest request) {
        IPage<UmsStoreEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsStoreEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getName()), UmsStoreEntity::getName, request.getName());

        List<UmsStoreEntity> umsStoreEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsStoreEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsStoreResponse> umsStoreList = umsStoreEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsStoreResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsStoreList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
