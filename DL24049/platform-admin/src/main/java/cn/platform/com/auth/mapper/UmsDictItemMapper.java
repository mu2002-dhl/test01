package cn.platform.com.auth.mapper;

import cn.platform.com.auth.entity.UmsDictItemEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;


public interface UmsDictItemMapper extends BaseMapper<UmsDictItemEntity> {
    int deleteByDictId(Integer dictId);
}