package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.platform.com.auth.convert.DeptConvert;
import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsDeptMapper;
import cn.platform.com.auth.mapper.UmsUserMapper;
import cn.platform.com.auth.model.DeptTreeNode;
import cn.platform.com.auth.model.request.DeptAddRequest;
import cn.platform.com.auth.model.request.DeptModifyRequest;
import cn.platform.com.auth.service.UmsDeptService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 21:16
 */
@Service
public class UmsDeptServiceImpl implements UmsDeptService {

    @Resource
    private UmsDeptMapper umsDeptMapper;
    @Resource
    private UmsUserMapper umsUserMapper;

    @Override
    public List<DeptTreeNode> tree() {
        return getTreeData(0L);
    }

    @Override
    public int add(DeptAddRequest request) {
        UmsDeptEntity umsDeptEntity = DeptConvert.instance.deptAddRequest2UmsDeptEntity(request);

        checkName(null, umsDeptEntity.getDeptName());

        checkParentId(request.getParentId());

        return umsDeptMapper.insert(umsDeptEntity);
    }

    @Override
    public int modify(DeptModifyRequest request) {
        UmsDeptEntity umsDeptEntity = umsDeptMapper.selectById(request.getId());
        if(umsDeptEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        checkName(request.getId(), request.getDeptName());

        checkParentId(request.getParentId());

        DeptConvert.instance.modifyUmsDeptEntity(request, umsDeptEntity);

        return umsDeptMapper.updateById(umsDeptEntity);
    }

    @Override
    public Optional<UmsDeptEntity> detail(Long deptId){
        if(deptId == null){
            return Optional.empty();
        }

        UmsDeptEntity entity = umsDeptMapper.selectById(deptId);
        if(entity == null){
            return Optional.empty();
        }
        return Optional.of(entity);
    }

    @Override
    public int delete(Long id) {
        if(id == null){
            return 0;
        }
        UmsDeptEntity entity = umsDeptMapper.selectById(id);
        if(entity == null){
            return 0;
        }

        //存在下级部门无法删除
        List<UmsDeptEntity> childrenList = getChildren(id);
        if(CollUtil.isNotEmpty(childrenList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.DEPT_NOT_DELETE_HAS_CHILDREN);
        }

        //部门是否被用户占用
        LambdaQueryWrapper<UmsUserEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsUserEntity::getDeptId, id);
        Long userCount = umsUserMapper.selectCount(wrapper);
        if(userCount > 0){
            throw new BizCustomException(AuthSystemBizCodeEnum.DEPT_NOT_DELETE_USED_BY_USER);
        }

        return umsDeptMapper.deleteById(id);
    }

    //校验同级部门下，名称不能重复
    private void checkName(Long id, String deptName){
        LambdaQueryWrapper<UmsDeptEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsDeptEntity:: getDeptName, deptName);
        wrapper.ne(id!=null, UmsDeptEntity::getId, id);
        List<UmsDeptEntity> umsDeptEntityList = umsDeptMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(umsDeptEntityList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.DEPT_SAME_LEVEL_NAME_NOT_REPEAT);
        }
    }

    //校验上级部门是否存在
    private void checkParentId(Long parentId){
        if(parentId == null || parentId == 0){
            return;
        }

        UmsDeptEntity umsDeptEntity = umsDeptMapper.selectById(parentId);
        if(umsDeptEntity == null){
            throw new BizCustomException(AuthSystemBizCodeEnum.DEPT_PARENT_NOT_EXIST);
        }
    }

    private List<DeptTreeNode> getTreeData(Long parentId){
        List<DeptTreeNode> treeNodeList = new ArrayList<>();
        LambdaQueryWrapper<UmsDeptEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsDeptEntity::getParentId, parentId);
        wrapper.orderByAsc(UmsDeptEntity:: getSort);
        List<UmsDeptEntity> list = umsDeptMapper.selectList(wrapper);
        if(CollUtil.isEmpty(list)){
            return treeNodeList;
        }
        for(UmsDeptEntity entity : list){
            DeptTreeNode deptTreeNode = DeptConvert.instance.umsDeptEntity2DeptTreeNode(entity);
            deptTreeNode.setLabel(deptTreeNode.getDeptName());
            deptTreeNode.setValue(deptTreeNode.getId()+"");
            List<DeptTreeNode> childrenList = getTreeData(entity.getId());
            deptTreeNode.setChildren(childrenList);
            treeNodeList.add(deptTreeNode);
        }

        return treeNodeList;
    }

    private List<UmsDeptEntity> getChildren(Long parentId){
        if(parentId == null){
            return new ArrayList<>();
        }

        LambdaQueryWrapper<UmsDeptEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsDeptEntity:: getParentId, parentId);
        return umsDeptMapper.selectList(wrapper);
    }
}
