package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.business.entity.UmsNoticeEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmsNoticeMapper;
import cn.platform.com.business.model.request.UmsNoticePageRequest;
import cn.platform.com.business.model.request.UmsNoticeRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsNoticeResponse;
import cn.platform.com.business.service.UmsNoticeService;
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
public class UmsNoticeServiceImpl extends ServiceImpl<UmsNoticeMapper, UmsNoticeEntity> implements UmsNoticeService {

    @Override
    public int add(UmsNoticeRequest request) {
        UmsNoticeEntity umsNoticeEntity = BeanUtil.copyProperties(request, UmsNoticeEntity.class);
        return baseMapper.insert(umsNoticeEntity);
    }

    @Override
    public int modify(Integer id, UmsNoticeRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsNoticeEntity umsNoticeEntity = BeanUtil.copyProperties(request, UmsNoticeEntity.class);
        umsNoticeEntity.setId(id);

        return baseMapper.updateById(umsNoticeEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsNoticeResponse> page(UmsNoticePageRequest request) {
        IPage<UmsNoticeEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsNoticeEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getTitle()), UmsNoticeEntity::getTitle, request.getTitle());
        wrapper.like(StrUtil.isNotEmpty(request.getIsNew()), UmsNoticeEntity::getIsNew, request.getIsNew());

        List<UmsNoticeEntity> umsNoticeEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsNoticeEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsNoticeResponse> umsNoticeList = umsNoticeEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsNoticeResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsNoticeList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
