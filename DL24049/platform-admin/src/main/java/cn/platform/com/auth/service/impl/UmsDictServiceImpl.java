package cn.platform.com.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.entity.UmsDictEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsDictItemMapper;
import cn.platform.com.auth.mapper.UmsDictMapper;
import cn.platform.com.auth.model.request.UmsDictPageRequest;
import cn.platform.com.auth.model.request.UmsDictRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.auth.model.response.UmsDictResponse;
import cn.platform.com.auth.service.UmsDictService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsDictServiceImpl extends ServiceImpl<UmsDictMapper, UmsDictEntity> implements UmsDictService {

    @Resource
    private UmsDictItemMapper umsDictItemMapper;

    @Override
    public int add(UmsDictRequest request) {
        UmsDictEntity umsDictEntity = BeanUtil.copyProperties(request, UmsDictEntity.class);
        return baseMapper.insert(umsDictEntity);
    }

    @Override
    public int modify(Integer id, UmsDictRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsDictEntity umsDictEntity = BeanUtil.copyProperties(request, UmsDictEntity.class);
        umsDictEntity.setId(id);

        return baseMapper.updateById(umsDictEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        umsDictItemMapper.deleteByDictId(id);
        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsDictResponse> page(UmsDictPageRequest request) {
        IPage<UmsDictEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsDictEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getDictCode()), UmsDictEntity::getDictCode, request.getDictCode());
        wrapper.like(StrUtil.isNotEmpty(request.getDictName()), UmsDictEntity::getDictName, request.getDictName());

        List<UmsDictEntity> umsDictEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsDictEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsDictResponse> umsDictList = umsDictEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsDictResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsDictList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
