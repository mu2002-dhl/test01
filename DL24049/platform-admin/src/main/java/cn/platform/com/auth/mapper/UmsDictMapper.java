package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsDictEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface UmsDictMapper extends BaseMapper<UmsDictEntity> {

    UmsDictEntity selectByDictCode(String dictCode);
}