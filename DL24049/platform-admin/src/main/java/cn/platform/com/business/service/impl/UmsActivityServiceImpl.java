package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.util.SecurityUtil;
import cn.platform.com.business.entity.UmsActivityApplyEntity;
import cn.platform.com.business.entity.UmsActivityEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.entity.UmsPlacesEntity;
import cn.platform.com.business.mapper.UmsActivityApplyMapper;
import cn.platform.com.business.mapper.UmsActivityMapper;
import cn.platform.com.business.mapper.UmsPlacesMapper;
import cn.platform.com.business.model.request.UmsActivityPageRequest;
import cn.platform.com.business.model.request.UmsActivityRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.response.UmsActivityResponse;
import cn.platform.com.business.service.UmsActivityService;
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
public class UmsActivityServiceImpl extends ServiceImpl<UmsActivityMapper, UmsActivityEntity> implements UmsActivityService {

    @Resource
    private UmsPlacesMapper umsPlacesMapper;
    @Resource
    private UmsActivityApplyMapper activityApplyMapper;

    @Override
    public int add(UmsActivityRequest request) {
        UmsActivityEntity umsActivityEntity = BeanUtil.copyProperties(request, UmsActivityEntity.class);
        return baseMapper.insert(umsActivityEntity);
    }

    @Override
    public int modify(Integer id, UmsActivityRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsActivityEntity umsActivityEntity = BeanUtil.copyProperties(request, UmsActivityEntity.class);
        umsActivityEntity.setId(id);

        return baseMapper.updateById(umsActivityEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmsActivityResponse> page(UmsActivityPageRequest request) {
        IPage<UmsActivityEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmsActivityEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getName()), UmsActivityEntity::getName, request.getName());
        wrapper.eq(request.getPlaceId()!=null, UmsActivityEntity::getPlaceId, request.getPlaceId());

/*        boolean isAdmin = true;
        UmsRoleEntity umsRoleEntity = SecurityUtil.getCurrentLoginUser().getUmsRoleEntityList().get(0);
        if(!umsRoleEntity.getNum().equals("role_admim")){
            isAdmin = false;
        }
        final boolean  finalIsAdmin = isAdmin;*/
        List<UmsActivityEntity> umsActivityEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsActivityEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmsActivityResponse> umsActivityList = umsActivityEntityList.stream()
                    .map(entity -> {
                        UmsActivityResponse umsActivityResponse = BeanUtil.copyProperties(entity, UmsActivityResponse.class);
                        if(entity.getPlaceId() != null){
                            UmsPlacesEntity umsPlacesEntity = umsPlacesMapper.selectById(entity.getPlaceId());
                            if(umsPlacesEntity!=null) {
                                umsActivityResponse.setPlaceName(umsPlacesEntity.getName());
                            }
                        }

                        LambdaQueryWrapper<UmsActivityApplyEntity> applyWrapper = Wrappers.lambdaQuery();
                        applyWrapper.eq(UmsActivityApplyEntity :: getCreator, SecurityUtil.getCurrentLoginUser().getUmsUserEntity().getId());
                        applyWrapper.eq(UmsActivityApplyEntity :: getActivityId, entity.getId());
                        List<UmsActivityApplyEntity> umsActivityApplyEntities = activityApplyMapper.selectList(applyWrapper);
                        if(CollUtil.isNotEmpty(umsActivityApplyEntities)){
                            umsActivityResponse.setApply(true);
                            umsActivityResponse.setApplyId(umsActivityApplyEntities.get(0).getId());
                        }else{
                            umsActivityResponse.setApply(false);
                        }
                        return umsActivityResponse;
                    })
                    .collect(Collectors.toList());
            return new PageResponse<>(umsActivityList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }
}
