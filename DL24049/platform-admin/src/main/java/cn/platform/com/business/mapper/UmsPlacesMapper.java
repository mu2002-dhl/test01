package cn.platform.com.business.mapper;

import cn.platform.com.business.entity.UmsPlacesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;


public interface UmsPlacesMapper extends BaseMapper<UmsPlacesEntity> {
    List<UmsPlacesEntity> searchRank();

    List<Map<String, String>> ageRank();
}