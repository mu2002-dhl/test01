package cn.platform.com.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.aggregate.UmsUserAggregate;
import cn.platform.com.auth.convert.UserConvert;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.mapper.UmsUserMapper;
import cn.platform.com.auth.model.request.*;
import cn.platform.com.auth.model.response.UserResponse;
import cn.platform.com.auth.service.UmsUserService;
import cn.platform.com.auth.util.FileUtil;
import cn.platform.com.auth.util.ResponseData;
import cn.platform.com.auth.util.SecurityUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Value("${platform.filePath}")
    private String filePath;

    @Resource
    private UmsUserService umsUserService;
    @Resource
    private UmsUserMapper umsUserMapper;

    @Resource
    private UmsUserAggregate umsUserAggregate;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UserPageRequest request){
        return ResponseData.buildSuccess(umsUserService.page(request));
    }

    @GetMapping("all")
    public ResponseData all(){
        List<UmsUserEntity> umsUserEntities = umsUserMapper.selectList(Wrappers.emptyWrapper());
        List<UserResponse> userResponseList = new ArrayList<>();
        if(CollUtil.isNotEmpty(umsUserEntities)) {
            userResponseList = umsUserEntities.stream()
                    .map(entity -> UserConvert.instance.umsUserEntity2UserResponse(entity)).collect(Collectors.toList());
        }

        return ResponseData.buildSuccess(userResponseList);
    }

    @Operation(summary = "用户新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UserAddRequest request){
        return buildAddResponseData(umsUserAggregate.add(request));
    }

    @Operation(summary = "用户注册")
    @PostMapping("register")
    public ResponseData register(@RequestBody @Valid UserRegisterRequest request){
        UserAddRequest userAddRequest = BeanUtil.copyProperties(request, UserAddRequest.class);
        int affectedRows = umsUserAggregate.add(userAddRequest);
        return affectedRows > 0 ? ResponseData.buildSuccess("注册成功") : ResponseData.buildBizCodeEnum(CommonBizCodeEnum.ADD_FAILURE);
    }

    @Operation(summary = "用户修改")
    @PutMapping
    public ResponseData modify(@RequestBody @Valid UserModifyRequest request){
        return buildModifyResponseData(umsUserAggregate.modify(request));
    }

    @Operation(summary = "用户删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Long id){
        return buildDeleteResponseData(umsUserService.delete(id));
    }

    @Operation(summary = "用户详情")
    @GetMapping("{id}")
    public ResponseData detail(@PathVariable("id")Long id){
        Optional<UserResponse> detailOptional = umsUserService.detail(id);
        if(detailOptional.isEmpty()){
            return ResponseData.buildBizCodeEnum(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return ResponseData.buildSuccess(detailOptional.get());
    }

    @Operation(summary = "密码修改")
    @PutMapping("changePwd")
    public ResponseData changePwd(@RequestBody @Valid UserChangePwdRequest request){
        return buildModifyResponseData(umsUserService.changePwd(request));
    }

    @Operation(summary = "启用修改")
    @PutMapping("/changeEnable/{id}")
    public ResponseData changeEnable(@PathVariable("id")Long id){
        return buildModifyResponseData(umsUserService.changeEnable(id));
    }

    @Operation(summary = "锁定状态修改")
    @PutMapping("/changeLocked/{id}")
    public ResponseData changeLocked(@PathVariable("id")Long id){
        return buildModifyResponseData(umsUserService.changeLocked(id));
    }

    @Operation(summary = "头像上传")
    @PostMapping("/avatar/upload")
    public ResponseData avatarUpload(MultipartFile file) throws Exception{
        umsUserService.avatarUpload(file);
        return ResponseData.buildSuccess();
    }

    @Operation(summary = "图片预览")
    @GetMapping("/avatar/view")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) throws Exception{
        UmsUserEntity umsUserEntity = SecurityUtil.getCurrentLoginUser().getUmsUserEntity();
        Optional<UserResponse> userResponseOptional = umsUserService.detail(umsUserEntity.getId());
        String sourcePath = filePath+"//"+ userResponseOptional.get().getHeadImg();
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        String headImg = umsUserEntity.getHeadImg();
        if(StrUtil.isEmpty(headImg)){
            return;
        }
        FileUtil.setFileDownloadHeader(request, headImg);
        FileUtil.writeBytes(sourcePath, response.getOutputStream());
    }

    @GetMapping("/selectByRoleId/{roleId}")
    public ResponseData selectByRoleId(@PathVariable("roleId")Long roleId){
        List<UmsUserEntity> umsUserEntities = umsUserMapper.selectByRoleId(roleId);
        if(CollUtil.isEmpty(umsUserEntities)){
            return ResponseData.buildSuccess();
        }

        List<UserResponse> collect = umsUserEntities.stream().map(entity -> UserConvert.instance.umsUserEntity2UserResponse(entity)).collect(Collectors.toList());
        return ResponseData.buildSuccess(collect);
    }
}
