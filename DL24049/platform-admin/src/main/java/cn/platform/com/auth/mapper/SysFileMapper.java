package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.SysFileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface SysFileMapper extends BaseMapper<SysFileEntity> {
    List<SysFileEntity> selectByObjId(Integer objId);
}
