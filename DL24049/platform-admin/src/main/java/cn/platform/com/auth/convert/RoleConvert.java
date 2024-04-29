package cn.platform.com.auth.convert;

import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.model.response.RoleResponse;
import cn.platform.com.auth.model.request.RoleAddRequest;
import cn.platform.com.auth.model.request.RoleModifyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/3 19:38
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleConvert {
    RoleConvert instance = Mappers.getMapper(RoleConvert.class);

    UmsRoleEntity roleAddRequest2UmsRoleEntity(RoleAddRequest request);

    UmsRoleEntity roleModifyRequestUmsRoleEntity(RoleModifyRequest request);

    void modifyUmsRoleEntity(RoleModifyRequest request, @MappingTarget UmsRoleEntity entity);

    RoleResponse umsRoleEntity2RoleResponse(UmsRoleEntity entity);
}
