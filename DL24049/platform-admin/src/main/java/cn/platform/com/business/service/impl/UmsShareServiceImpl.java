package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.mapper.UmsUserMapper;
import cn.platform.com.business.entity.UmsShareEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmsShareMapper;
import cn.platform.com.business.model.request.UmsSharePageRequest;
import cn.platform.com.business.model.request.UmsShareRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsShareResponse;
import cn.platform.com.business.service.UmsShareService;
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
public class UmsShareServiceImpl extends ServiceImpl<UmsShareMapper, UmsShareEntity> implements UmsShareService {

    @Resource
    private UmsUserMapper umsUserMapper;

    @Override
    public int add(UmsShareRequest request) {
        UmsShareEntity umsShareEntity = BeanUtil.copyProperties(request, UmsShareEntity.class);
        return baseMapper.insert(umsShareEntity);
    }

    @Override
    public int modify(Integer id, UmsShareRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsShareEntity umsShareEntity = BeanUtil.copyProperties(request, UmsShareEntity.class);
        umsShareEntity.setId(id);

        return baseMapper.updateById(umsShareEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsShareResponse> page(UmsSharePageRequest request) {
        IPage<UmsShareEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsShareEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getTitle()), UmsShareEntity::getTitle, request.getTitle());
        wrapper.eq(request.getCreator()!=null, UmsShareEntity::getCreator, request.getCreator());
        wrapper.eq(StrUtil.isNotEmpty(request.getType()), UmsShareEntity::getType, request.getType());
        wrapper.orderByAsc(UmsShareEntity::getCreateTime);
        List<UmsShareEntity> umsShareEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsShareEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsShareResponse> umsShareList = umsShareEntityList.stream()
                    .map(entity -> {
                        UmsShareResponse umsShareResponse = BeanUtil.copyProperties(entity, UmsShareResponse.class);
                        if(umsShareResponse!=null){
                            UmsUserEntity umsUserEntity = umsUserMapper.selectById(umsShareResponse.getCreator());
                            if(umsUserEntity!=null){
                                umsShareResponse.setCreateName(umsUserEntity.getNickname());
                            }
                        }
                        return umsShareResponse;
                    })
                    .collect(Collectors.toList());
            return new PageResponse<>(umsShareList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
