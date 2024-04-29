package cn.platform.com.business.mapper;

import cn.platform.com.business.entity.UmsActivityApplyEntity;
import cn.platform.com.business.model.response.UmsActivityApplyResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;


public interface UmsActivityApplyMapper extends BaseMapper<UmsActivityApplyEntity> {
    List<UmsActivityApplyResponse> selectByActivityId(Integer activityId);
}