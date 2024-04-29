package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.business.entity.UmsActivityApplyEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmsActivityApplyMapper;
import cn.platform.com.business.model.request.UmsActivityApplyPageRequest;
import cn.platform.com.business.model.request.UmsActivityApplyRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsActivityApplyResponse;
import cn.platform.com.business.service.UmsActivityApplyService;
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
public class UmsActivityApplyServiceImpl extends ServiceImpl<UmsActivityApplyMapper, UmsActivityApplyEntity> implements UmsActivityApplyService {

    @Override
    public int add(UmsActivityApplyRequest request) {
        UmsActivityApplyEntity umsActivityApplyEntity = BeanUtil.copyProperties(request, UmsActivityApplyEntity.class);
        return baseMapper.insert(umsActivityApplyEntity);
    }

    @Override
    public int modify(Integer id, UmsActivityApplyRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsActivityApplyEntity umsActivityApplyEntity = BeanUtil.copyProperties(request, UmsActivityApplyEntity.class);
        umsActivityApplyEntity.setId(id);

        return baseMapper.updateById(umsActivityApplyEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsActivityApplyResponse> page(UmsActivityApplyPageRequest request) {
        IPage<UmsActivityApplyEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsActivityApplyEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(request.getActivityId()!=null, UmsActivityApplyEntity::getActivityId, request.getActivityId());
        wrapper.like(request.getCreator()!=null, UmsActivityApplyEntity::getCreator, request.getCreator());

        List<UmsActivityApplyEntity> umsActivityApplyEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsActivityApplyEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsActivityApplyResponse> umsActivityApplyList = umsActivityApplyEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsActivityApplyResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsActivityApplyList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
