package cn.platform.com.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.entity.UmsDictEntity;
import cn.platform.com.auth.entity.UmsDictItemEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsDictItemMapper;
import cn.platform.com.auth.mapper.UmsDictMapper;
import cn.platform.com.auth.model.request.UmsDictItemPageRequest;
import cn.platform.com.auth.model.request.UmsDictItemRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.auth.model.response.UmsDictItemResponse;
import cn.platform.com.auth.service.UmsDictItemService;
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
public class UmsDictItemServiceImpl extends ServiceImpl<UmsDictItemMapper, UmsDictItemEntity> implements UmsDictItemService {

    @Resource
    private UmsDictMapper umsDictMapper;

    @Override
    public int add(UmsDictItemRequest request) {
        UmsDictItemEntity umsDictItemEntity = BeanUtil.copyProperties(request, UmsDictItemEntity.class);
        return baseMapper.insert(umsDictItemEntity);
    }

    @Override
    public int modify(Integer id, UmsDictItemRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsDictItemEntity umsDictItemEntity = BeanUtil.copyProperties(request, UmsDictItemEntity.class);
        umsDictItemEntity.setId(id);

        return baseMapper.updateById(umsDictItemEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsDictItemResponse> page(UmsDictItemPageRequest request) {
        IPage<UmsDictItemEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsDictItemEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getItemName()), UmsDictItemEntity::getItemName, request.getItemName());
        wrapper.like(StrUtil.isNotEmpty(request.getItemValue()), UmsDictItemEntity::getItemValue, request.getItemValue());
        wrapper.eq(request.getDictId() != null, UmsDictItemEntity::getDictId, request.getDictId());

        List<UmsDictItemEntity> umsDictItemEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsDictItemEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsDictItemResponse> umsDictItemList = umsDictItemEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmsDictItemResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umsDictItemList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }

    @Override
    public List<UmsDictItemResponse> selectByDictCode(String dictCode) {
        if(StrUtil.isEmpty(dictCode)){
            return new ArrayList<>();
        }

        UmsDictEntity umsDictEntity = umsDictMapper.selectByDictCode(dictCode);
        if(ObjectUtil.isEmpty(umsDictEntity)){
            return new ArrayList<>();
        }

        LambdaQueryWrapper<UmsDictItemEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsDictItemEntity :: getDictId, umsDictEntity.getId());
        List<UmsDictItemEntity> umsDictItemEntityList = baseMapper.selectList(wrapper);
        if(CollUtil.isEmpty(umsDictItemEntityList)){
            return new ArrayList<>();
        }

        return umsDictItemEntityList.stream()
                .map(entity -> BeanUtil.copyProperties(entity, UmsDictItemResponse.class))
                .collect(Collectors.toList());
    }
}
