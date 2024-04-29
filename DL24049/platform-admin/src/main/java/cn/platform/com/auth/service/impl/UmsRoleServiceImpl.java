package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.convert.RoleConvert;
import cn.platform.com.auth.entity.UmsPermissionRoleEntity;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsPermissionRoleMapper;
import cn.platform.com.auth.mapper.UmsRoleMapper;
import cn.platform.com.auth.mapper.UmsUserRoleMapper;
import cn.platform.com.auth.model.request.RoleAddRequest;
import cn.platform.com.auth.model.request.RoleModifyRequest;
import cn.platform.com.auth.model.request.RolePageRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.auth.model.response.RoleResponse;
import cn.platform.com.auth.service.UmsRoleService;
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
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRoleEntity> implements UmsRoleService {

    @Resource
    private UmsRoleMapper umsRoleMapper;
    @Resource
    private UmsUserRoleMapper umsUserRoleMapper;
    @Resource
    private UmsPermissionRoleMapper umsPermissionRoleMapper;

    @Override
    public PageResponse<RoleResponse> page(RolePageRequest request) {
        IPage<UmsRoleEntity> page = new Page<>(request.getPageNum(), request.getPageSize());
        LambdaQueryWrapper<UmsRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getName()), UmsRoleEntity::getName, request.getName());
        wrapper.like(StrUtil.isNotEmpty(request.getNum()), UmsRoleEntity::getNum, request.getNum());
        List<UmsRoleEntity> umsRoleEntityList = umsRoleMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umsRoleEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<RoleResponse> roleResponseList = umsRoleEntityList.stream()
                    .map(entity -> RoleConvert.instance.umsRoleEntity2RoleResponse(entity))
                    .collect(Collectors.toList());
            return new PageResponse<>(roleResponseList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }

    @Override
    public int add(RoleAddRequest request) {
        UmsRoleEntity umsRoleEntity = RoleConvert.instance.roleAddRequest2UmsRoleEntity(request);
        checkName(null, umsRoleEntity.getName());
        checkNum(null, umsRoleEntity.getNum());
        return umsRoleMapper.insert(umsRoleEntity);
    }

    @Override
    public int modify(RoleModifyRequest request) {
        UmsRoleEntity umsRoleEntity = umsRoleMapper.selectById(request.getId());
        if(umsRoleEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        checkName(umsRoleEntity.getId(), request.getName());
        checkNum(umsRoleEntity.getId(), request.getNum());

        RoleConvert.instance.modifyUmsRoleEntity(request, umsRoleEntity);
        return umsRoleMapper.updateById(umsRoleEntity);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        if(id == null){
            return 0;
        }

        if(umsUserRoleMapper.countByRoleId(id)!=0){
            throw new BizCustomException(AuthSystemBizCodeEnum.ROLE_NOT_DELETE_USED_BY_USER);
        }

        UmsRoleEntity umsRoleEntity = umsRoleMapper.selectById(id);
        if(umsRoleEntity == null){
            return 0;
        }

        LambdaQueryWrapper<UmsPermissionRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsPermissionRoleEntity :: getRoleId, id);
        umsPermissionRoleMapper.delete(wrapper);

        return umsRoleMapper.deleteById(id);
    }

    @Override
    public Optional<UmsRoleEntity> detail(Long id) {
        if(id == null){
            return Optional.empty();
        }

        UmsRoleEntity umsRoleEntity = umsRoleMapper.selectById(id);
        if(umsRoleEntity == null){
            return Optional.empty();
        }

        return Optional.of(umsRoleEntity);
    }

    @Override
    public List<RoleResponse> all() {
        List<UmsRoleEntity> umsRoleEntityList = umsRoleMapper.selectList(Wrappers.emptyWrapper());
        if(CollUtil.isEmpty(umsRoleEntityList)){
            return new ArrayList<>();
        }

        return umsRoleEntityList.stream()
                .map(entity -> RoleConvert.instance.umsRoleEntity2RoleResponse(entity)).collect(Collectors.toList());
    }


    private void checkName(Long id, String name){
        LambdaQueryWrapper<UmsRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsRoleEntity::getName, name);
        wrapper.ne(id!=null, UmsRoleEntity::getId, id);
        List<UmsRoleEntity> umsRoleEntityList = umsRoleMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(umsRoleEntityList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.ROLE_NAME_REPEAT);
        }
    }

    private void checkNum(Long id, String num){
        LambdaQueryWrapper<UmsRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsRoleEntity::getNum, num);
        wrapper.ne(id!=null, UmsRoleEntity::getId, id);
        List<UmsRoleEntity> umsRoleEntityList = umsRoleMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(umsRoleEntityList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.ROLE_NUM_REPEAT);
        }
    }
}
