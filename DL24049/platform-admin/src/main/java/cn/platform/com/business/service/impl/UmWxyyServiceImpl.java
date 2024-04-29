package cn.platform.com.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.model.WxyyAsk;
import cn.platform.com.auth.model.WxyyAskMessage;
import cn.platform.com.auth.model.WxyyCredentials;
import cn.platform.com.auth.model.WxyyResult;
import cn.platform.com.auth.util.SecurityUtil;
import cn.platform.com.business.entity.UmWxyyEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.business.mapper.UmWxyyMapper;
import cn.platform.com.business.model.request.UmWxyyPageRequest;
import cn.platform.com.business.model.request.UmWxyyRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.business.model.request.UmsWxyyAskRequest;
import cn.platform.com.business.model.response.UmWxyyResponse;
import cn.platform.com.business.service.UmWxyyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UmWxyyServiceImpl extends ServiceImpl<UmWxyyMapper, UmWxyyEntity> implements UmWxyyService {

    @Value("${wxyy.clientId}")
    private String clientId;

    @Value("${wxyy.clientSecret}")
    private String clientSecret;

    private String accessToken;
    @PostConstruct
    public void init(){
        try {
            HttpRequest post = HttpUtil.createPost("https://aip.baidubce.com/oauth/2.0/token?grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret);
            post.header("Content-Type", "application/json");
            post.header("Accept", "application/json");
            HttpResponse execute = post.execute();
            WxyyCredentials wxyyCredentials = JSONUtil.toBean(execute.body(), WxyyCredentials.class);
            log.info("获取文心一言accessToken返回信息，{}", wxyyCredentials);
            accessToken = wxyyCredentials.getAccess_token();
        }catch (Exception e){
            log.error("获取文心一言accessToken错误", e);
        }

    }
    @Override
    public int add(UmWxyyRequest request) {
        UmWxyyEntity umWxyyEntity = BeanUtil.copyProperties(request, UmWxyyEntity.class);
        return baseMapper.insert(umWxyyEntity);
    }

    @Override
    public int modify(Integer id, UmWxyyRequest request) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmWxyyEntity umWxyyEntity = BeanUtil.copyProperties(request, UmWxyyEntity.class);
        umWxyyEntity.setId(id);

        return baseMapper.updateById(umWxyyEntity);
    }

    @Override
    public int delete(Integer id) {
        if(baseMapper.selectById(id) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return baseMapper.deleteById(id);
    }

    @Override
    public PageResponse<UmWxyyResponse> page(UmWxyyPageRequest request) {
        IPage<UmWxyyEntity> page = new Page<>(request.getPageNum(), request.getPageSize());

        LambdaQueryWrapper<UmWxyyEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.like(StrUtil.isNotEmpty(request.getSearch()), UmWxyyEntity::getSearch, request.getSearch());
        wrapper.eq(StrUtil.isNotEmpty(request.getType()), UmWxyyEntity::getType, request.getType());
        UmsUserEntity umsUserEntity = SecurityUtil.getCurrentLoginUser().getUmsUserEntity();
        wrapper.eq(UmWxyyEntity :: getCreator, umsUserEntity.getId());
        wrapper.orderByDesc(UmWxyyEntity :: getCreateTime);
        List<UmWxyyEntity> umWxyyEntityList = baseMapper.selectList(page, wrapper);
        if(CollUtil.isEmpty(umWxyyEntityList)){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UmWxyyResponse> umWxyyList = umWxyyEntityList.stream()
                    .map(entity -> BeanUtil.copyProperties(entity, UmWxyyResponse.class))
                    .collect(Collectors.toList());
            return new PageResponse<>(umWxyyList, page.getTotal(), page.getSize(), page.getCurrent());
        }
    }

    @Override
    public WxyyResult ask(UmsWxyyAskRequest request) {
        if(StrUtil.isEmpty(request.getQuestion())){
            return new WxyyResult("", "输入查询内容为空", System.currentTimeMillis());
        }

        if(StrUtil.isEmpty(accessToken)){
            return new WxyyResult("", "assessToken获取失败，请联系管理员", System.currentTimeMillis());
        }
        try {
            String askUrl = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/yi_34b_chat?access_token=" + accessToken;
            HttpRequest post = HttpUtil.createPost(askUrl);
            ArrayList<WxyyAsk> wxyyAsks = new ArrayList<>();
            wxyyAsks.add(new WxyyAsk("user", "hi"));
            wxyyAsks.add(new WxyyAsk("assistant", "Hello! How can I assist you today?"));
            wxyyAsks.add(new WxyyAsk("user", request.getQuestion()));
            WxyyAskMessage wxyyAskMessage = new WxyyAskMessage(wxyyAsks);
            post.body(JSONUtil.toJsonStr(wxyyAskMessage));
            post.header("Content-Type", "application/json");
            post.header("Accept", "application/json");
            String body = post.execute().body();
            log.info("问答返回信息，{}", body);
            WxyyResult wxyyResult = JSONUtil.toBean(body, WxyyResult.class);
            if(wxyyResult == null || StrUtil.isEmpty(wxyyResult.getResult())){
                return new WxyyResult("", "问答失败，文心一言回复内容为空", System.currentTimeMillis());
            }

            UmWxyyRequest umWxyyRequest = new UmWxyyRequest();
            umWxyyRequest.setType(request.getType());
            umWxyyRequest.setSearch(request.getQuestion());
            umWxyyRequest.setReply(wxyyResult.getResult());
            add(umWxyyRequest);
            return wxyyResult;
        }catch (Exception e){
            return new WxyyResult("", "问答失败，"+e.getMessage(), System.currentTimeMillis());
        }
    }
}
