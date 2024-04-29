package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.model.DeptTreeNode;
import cn.platform.com.auth.model.request.DeptAddRequest;
import cn.platform.com.auth.model.request.DeptModifyRequest;

import java.util.List;
import java.util.Optional;

public interface UmsDeptService {

    List<DeptTreeNode> tree();

    int add(DeptAddRequest request);

    int modify(DeptModifyRequest request);

    Optional<UmsDeptEntity> detail(Long deptId);

    int delete(Long id);
}
