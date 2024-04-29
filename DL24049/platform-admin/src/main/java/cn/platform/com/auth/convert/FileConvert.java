package cn.platform.com.auth.convert;

import cn.platform.com.auth.entity.SysFileEntity;
import cn.platform.com.auth.model.response.FileResponse;
import cn.platform.com.auth.model.request.FileRequest;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FileConvert {

    FileConvert instance = Mappers.getMapper(FileConvert.class);

    SysFileEntity fileRequest2SysFileEntity(FileRequest request);

    FileResponse sysFileEntity2FileResponse(SysFileEntity entity);
}
