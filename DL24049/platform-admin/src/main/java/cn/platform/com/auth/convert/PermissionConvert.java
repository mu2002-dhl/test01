package cn.platform.com.auth.convert;

import cn.platform.com.auth.entity.UmsPermissionEntity;
import cn.platform.com.auth.model.PermissionTreeNode;
import cn.platform.com.auth.model.request.PermissionAddRequest;
import cn.platform.com.auth.model.request.PermissionModifyRequest;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/30 21:53
 */
@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PermissionConvert {

    PermissionConvert instance = Mappers.getMapper(PermissionConvert.class);

    UmsPermissionEntity permissionAddRequest2UmsPermissionEntity(PermissionAddRequest request);

    void modifyUmsPermissionEntity(PermissionModifyRequest request, @MappingTarget UmsPermissionEntity entity);

    PermissionTreeNode umsPermissionEntity2PermissionTreeNode(UmsPermissionEntity entity);

}
