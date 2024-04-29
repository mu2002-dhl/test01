package cn.platform.com.auth.convert;

import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.model.response.DeptResponse;
import cn.platform.com.auth.model.DeptTreeNode;
import cn.platform.com.auth.model.request.DeptAddRequest;
import cn.platform.com.auth.model.request.DeptModifyRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DeptConvert {

    DeptConvert instance = Mappers.getMapper(DeptConvert.class);

    UmsDeptEntity deptAddRequest2UmsDeptEntity(DeptAddRequest request);

    void modifyUmsDeptEntity(DeptModifyRequest request, @MappingTarget UmsDeptEntity entity);

    DeptTreeNode umsDeptEntity2DeptTreeNode(UmsDeptEntity entity);

    DeptResponse umsDeptEntity2DeptResponse(UmsDeptEntity entity);
}
