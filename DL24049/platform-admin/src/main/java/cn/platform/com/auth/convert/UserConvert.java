package cn.platform.com.auth.convert;

import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.model.request.UserAddRequest;
import cn.platform.com.auth.model.request.UserModifyRequest;
import cn.platform.com.auth.model.response.LoginResponse;
import cn.platform.com.auth.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConvert {

    UserConvert instance = Mappers.getMapper(UserConvert.class);

    UmsUserEntity userAddRequest2UmsUserEntity(UserAddRequest request);

    LoginResponse umsUserEntity2LoginResponse(UmsUserEntity entity);

    UserResponse umsUserEntity2UserResponse(UmsUserEntity entity);

    void modifyUmsEntity(UserModifyRequest request, @MappingTarget UmsUserEntity entity);
}
