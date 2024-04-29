package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.convert.PermissionConvert;
import cn.platform.com.auth.entity.UmsPermissionEntity;
import cn.platform.com.auth.entity.UmsPermissionRoleEntity;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.enums.PermissionEnum;
import cn.platform.com.auth.mapper.UmsPermissionMapper;
import cn.platform.com.auth.mapper.UmsPermissionRoleMapper;
import cn.platform.com.auth.model.PermissionTreeNode;
import cn.platform.com.auth.model.request.PermissionAddRequest;
import cn.platform.com.auth.model.request.PermissionModifyRequest;
import cn.platform.com.auth.model.response.PermissionRouterResponse;
import cn.platform.com.auth.service.UmsPermissionService;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.util.SecurityUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Service
public class UmsPermissionServiceImpl extends ServiceImpl<UmsPermissionMapper, UmsPermissionEntity> implements UmsPermissionService {

    @Resource
    private UmsPermissionMapper umsPermissionMapper;
    @Resource
    private UmsPermissionRoleMapper umsPermissionRoleMapper;

    @Override
    public List<PermissionTreeNode> tree() {
        return getTreeData(0L, null, null, "tree", null);
    }

    @Override
    public List<UmsPermissionEntity> selectMenuByUserId(Long userId) {
        return umsPermissionMapper.selectMenuByUserId(userId);
    }

    @Override
    public List<PermissionRouterResponse> router(List<UmsRoleEntity> umsRoleEntityList) {
        List<Long> roleIdList  = new ArrayList<>();
        List<UmsPermissionEntity> permissionEntityList = new ArrayList<>();
        if(CollUtil.isNotEmpty(umsRoleEntityList)) {
            umsRoleEntityList.forEach(entity -> roleIdList.add(entity.getId()));
            permissionEntityList = umsPermissionMapper.selectMenuByRoleId(roleIdList);
        }

        if(CollUtil.isNotEmpty(permissionEntityList)){
            return permissionEntityList.stream().filter(entity-> StrUtil.isNotEmpty(entity.getComponent())).map(entity->{
                PermissionRouterResponse router = new PermissionRouterResponse();
                router.setName(entity.getComponent());
                router.setPath(entity.getUrl());
                if(entity.getComponent().endsWith("/")) {
                    router.setComponent( entity.getComponent());
                }else{
                    router.setComponent("/" + entity.getComponent());
                }
                router.setTitle(entity.getName());
                return router;
            }).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public int add(PermissionAddRequest request) {

        checkParentId(request.getParentId());

        checkPermissionType(request.getType(), request.getUrl());

        UmsPermissionEntity umsPermissionEntity = PermissionConvert.instance.permissionAddRequest2UmsPermissionEntity(request);
        return umsPermissionMapper.insert(umsPermissionEntity);
    }

    @Override
    public int modify(PermissionModifyRequest request) {

        UmsPermissionEntity umsPermissionEntity = umsPermissionMapper.selectById(request.getId());
        if(umsPermissionEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        checkParentId(request.getParentId());

        checkPermissionType(request.getType(), request.getUrl());

        PermissionConvert.instance.modifyUmsPermissionEntity(request, umsPermissionEntity);

        return umsPermissionMapper.updateById(umsPermissionEntity);
    }

    @Override
    public Optional<UmsPermissionEntity> detail(Long id) {
        if(id == null) {
            return Optional.empty();
        }

        UmsPermissionEntity umsPermissionEntity = umsPermissionMapper.selectById(id);
        if(umsPermissionEntity == null){
            return Optional.empty();
        }

        return Optional.of(umsPermissionEntity);
    }

    @Override
    public int delete(Long id) {
        UmsPermissionEntity umsPermissionEntity = umsPermissionMapper.selectById(id);
        if(umsPermissionEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        //判断是否有下级
        List<UmsPermissionEntity> childrenList = getChildren(id);
        if(CollUtil.isNotEmpty(childrenList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.PERMISSION_NOT_DELETE_HAS_CHILDREN);
        }

        //判断是否被角色授权
        LambdaQueryWrapper<UmsPermissionRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsPermissionRoleEntity :: getPermissionId, id);
        Long roleCount = umsPermissionRoleMapper.selectCount(wrapper);
        if(roleCount > 0){
            throw new BizCustomException(AuthSystemBizCodeEnum.PERMISSION_NOT_DELETE_USED_BY_ROLE);
        }

        return umsPermissionMapper.deleteById(id);
    }

    @Override
    public List<PermissionTreeNode> menu(List<UmsRoleEntity> umsRoleEntityList) {
        return getTreeData(0L, PermissionEnum.MENU, true, "menu", umsRoleEntityList);
    }

    //校验上级权限是否存在
    private void checkParentId(long parentId){
        if(parentId == 0){
            return;
        }

        UmsPermissionEntity umsPermissionEntity = umsPermissionMapper.selectById(parentId);
        if(umsPermissionEntity == null){
            throw new BizCustomException(AuthSystemBizCodeEnum.PERMISSION_PARENT_NOT_EXIST);
        }
    }

    //校验权限类型取值
    private void checkPermissionType(Byte type, String url){
        PermissionEnum permissionEnum = PermissionEnum.getPermissionEnum(type);
        if(permissionEnum == null){
            throw new BizCustomException(AuthSystemBizCodeEnum.PERMISSION_TYPE_VALUE_ERROR);
        }

//        if(permissionEnum == PermissionEnum.MENU && StrUtil.isEmpty(url)){
//            throw new BizCustomException(AuthSystemBizCodeEnum.PERMISSION_TYPE_MENU_MUST_URL);
//        }
    }


    private List<PermissionTreeNode> getTreeData(Long parentId, PermissionEnum permissionEnum, Boolean enable, String type, List<UmsRoleEntity> umsRoleEntityList){
        List<PermissionTreeNode> list = new ArrayList<>();
        List<UmsPermissionEntity> entityList = null;
        if(type.equals("tree")) {
            LambdaQueryWrapper<UmsPermissionEntity> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(UmsPermissionEntity::getParentId, parentId);
            if (enable != null) {
                wrapper.eq(UmsPermissionEntity::getEnable, enable);
            }
            if (permissionEnum != null) {
                wrapper.eq(UmsPermissionEntity::getType, permissionEnum.getType());
            }
            wrapper.orderByAsc(UmsPermissionEntity::getSort);
            entityList = umsPermissionMapper.selectList(wrapper);
        }else{
            List<Long> roleIdList  = new ArrayList<>();
            if(CollUtil.isNotEmpty(umsRoleEntityList)) {
                umsRoleEntityList.forEach(entity -> roleIdList.add(entity.getId()));
                entityList = umsPermissionMapper.selectMenuByRoleIdParentId(roleIdList, parentId);
            }
        }
        if(CollUtil.isEmpty(entityList)){
            return list;
        }

        for(UmsPermissionEntity entity : entityList){
            PermissionTreeNode subTreeNode = PermissionConvert.instance.umsPermissionEntity2PermissionTreeNode(entity);
            List<PermissionTreeNode> childrenList = getTreeData(subTreeNode.getId(), permissionEnum, enable, type, umsRoleEntityList);
            subTreeNode.setChildren(childrenList);
            list.add(subTreeNode);
        }

        return list;
    }

    private List<UmsPermissionEntity> getChildren(Long parentId){
        if(parentId == null){
            return new ArrayList<>();
        }

        LambdaQueryWrapper<UmsPermissionEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsPermissionEntity:: getParentId, parentId);
        return umsPermissionMapper.selectList(wrapper);
    }
}
