package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.business.entity.UmsCommentEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmsCommentMapper;
import cn.platform.com.business.model.request.UmsCommentPageRequest;
import cn.platform.com.business.model.request.UmsCommentRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsCommentResponse;
import cn.platform.com.business.service.UmsCommentService;
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
public class UmsCommentServiceImpl extends ServiceImpl<UmsCommentMapper, UmsCommentEntity> implements UmsCommentService {

    @Override
    public int add(UmsCommentRequest request) {
        UmsCommentEntity umsCommentEntity = BeanUtil.copyProperties(request, UmsCommentEntity.class);
        return baseMapper.insert(umsCommentEntity);
    }

    @Override
    public int modify(Integer id, UmsCommentRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsCommentEntity umsCommentEntity = BeanUtil.copyProperties(request, UmsCommentEntity.class);
        umsCommentEntity.setId(id);

        return baseMapper.updateById(umsCommentEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsCommentResponse> page(UmsCommentPageRequest request) {
        IPage<UmsCommentEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsCommentEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(request.getObjId()!=null, UmsCommentEntity::getObjId, request.getObjId());
        wrapper.like(StrUtil.isNotEmpty(request.getType()), UmsCommentEntity::getType, request.getType());

        List<UmsCommentEntity> umsCommentEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsCommentEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsCommentResponse> umsCommentList = umsCommentEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsCommentResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsCommentList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
