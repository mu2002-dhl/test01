package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.business.entity.UmsPlacesEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmsPlacesMapper;
import cn.platform.com.business.model.request.UmsPlacesPageRequest;
import cn.platform.com.business.model.request.UmsPlacesRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsPlacesResponse;
import cn.platform.com.business.service.UmsPlacesService;
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
public class UmsPlacesServiceImpl extends ServiceImpl<UmsPlacesMapper, UmsPlacesEntity> implements UmsPlacesService {

    @Override
    public int add(UmsPlacesRequest request) {
        UmsPlacesEntity umsPlacesEntity = BeanUtil.copyProperties(request, UmsPlacesEntity.class);
        return baseMapper.insert(umsPlacesEntity);
    }

    @Override
    public int modify(Integer id, UmsPlacesRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsPlacesEntity umsPlacesEntity = BeanUtil.copyProperties(request, UmsPlacesEntity.class);
        umsPlacesEntity.setId(id);

        return baseMapper.updateById(umsPlacesEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsPlacesResponse> page(UmsPlacesPageRequest request) {
        IPage<UmsPlacesEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsPlacesEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getName()), UmsPlacesEntity::getName, request.getName());

        List<UmsPlacesEntity> umsPlacesEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsPlacesEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsPlacesResponse> umsPlacesList = umsPlacesEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsPlacesResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsPlacesList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }

    @Override
    public UmsPlacesResponse detail(Integer id) {
        if(id== null){
            return null;
        }
        UmsPlacesEntity umsPlacesEntity = baseMapper.selectById(id);
        if(umsPlacesEntity == null){
            return null;
        }

        umsPlacesEntity.setSearchCount(umsPlacesEntity.getSearchCount() == null ? 1 : umsPlacesEntity.getSearchCount()+1);
        baseMapper.updateById(umsPlacesEntity);
        return BeanUtil.copyProperties(umsPlacesEntity, UmsPlacesResponse.class);
    }
}
