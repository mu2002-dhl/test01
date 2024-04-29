package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.convert.RoleConvert;
import cn.platform.com.auth.convert.UserConvert;
import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.entity.UmsUserRoleEntity;
import cn.platform.com.auth.mapper.UmsUserRoleMapper;
import cn.platform.com.auth.model.response.DeptResponse;
import cn.platform.com.auth.model.response.RoleResponse;
import cn.platform.com.auth.service.UmsUserService;
import cn.platform.com.auth.constant.CommonConstant;
import cn.platform.com.auth.convert.DeptConvert;
import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.model.LoginUser;
import cn.platform.com.auth.enums.AuthSystemBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.UmsDeptMapper;
import cn.platform.com.auth.mapper.UmsRoleMapper;
import cn.platform.com.auth.mapper.UmsUserMapper;
import cn.platform.com.auth.model.request.UserAddRequest;
import cn.platform.com.auth.model.request.UserChangePwdRequest;
import cn.platform.com.auth.model.request.UserModifyRequest;
import cn.platform.com.auth.model.request.UserPageRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.auth.model.response.UserResponse;
import cn.platform.com.auth.util.SecurityUtil;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUserEntity> implements UmsUserService {

    @Value("${platform.filePath}")
    private String filePath;
    @Resource
    private UmsUserMapper umsUserMapper;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UmsDeptMapper umsDeptMapper;
    @Resource
    private UmsRoleMapper umsRoleMapper;
    @Resource
    private UmsUserRoleMapper umsUserRoleMapper;

    @Override
    public PageResponse<UserResponse> page(UserPageRequest request) {
        Page<UmsUserEntity> page = new Page<>(request.getPageNum(), request.getPageSize());
        Page<UmsUserEntity> pageResult = umsUserMapper.query(page, request);
        if(CollUtil.isEmpty(pageResult.getRecords())){
            return new PageResponse<>(new ArrayList<>(), page.getTotal(), page.getSize(), page.getCurrent());
        }else{
            List<UserResponse> userResponseList = pageResult.getRecords().stream()
                    .map(entity -> userEntity2UserResponse(entity)).collect(Collectors.toList());
            return new PageResponse<>(userResponseList, pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent());
        }
    }

    @Override
    @Transactional
    public UmsUserEntity add(UserAddRequest request) {
        UmsUserEntity umsUserEntity = UserConvert.instance.userAddRequest2UmsUserEntity(request);

        //密码加密
        String encodePassword = passwordEncoder.encode(request.getPassword());
        umsUserEntity.setPassword(encodePassword);

        Date accountExpiredTime = DateUtil.offsetMonth(new Date(), CommonConstant.ACCOUNT_EXPIRE);
        Date pwdExpiredTime = DateUtil.offsetMonth(new Date(), CommonConstant.PWD_EXPIRE);
        umsUserEntity.setAccountExpiredTime(accountExpiredTime);
        umsUserEntity.setPwdExpiredTime(pwdExpiredTime);
        umsUserEntity.setEnable(true);
        umsUserEntity.setLocked(false);

        checkLoginName(null, umsUserEntity.getLoginName());
        checkPhone(null, umsUserEntity.getPhone());
        checkEmail(null, umsUserEntity.getEmail());
        checkDept(umsUserEntity.getDeptId());
        umsUserMapper.insert(umsUserEntity);
        return umsUserEntity;
    }

    @Override
    public UmsUserEntity modify(UserModifyRequest request) {
        UmsUserEntity umsUserEntity = umsUserMapper.selectById(request.getId());
        if(umsUserEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        checkLoginName(request.getId(), umsUserEntity.getLoginName());
        checkPhone(request.getId(), request.getPhone());
        checkEmail(request.getId(), request.getEmail());
        checkDept(request.getDeptId());

        UserConvert.instance.modifyUmsEntity(request, umsUserEntity);
        umsUserMapper.updateById(umsUserEntity);
        return umsUserEntity;
    }

    @Override
    @Transactional
    public int delete(Long id) {
        UmsUserEntity umsUserEntity = umsUserMapper.selectById(id);
        if(umsUserEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        //删除用户绑定的角色
        LambdaQueryWrapper<UmsUserRoleEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(UmsUserRoleEntity :: getUserId, id);
        umsUserRoleMapper.delete(wrapper);

        return umsUserMapper.deleteById(id);
    }

    @Override
    public Optional<UserResponse> detail(Long id) {
        if(id == null){
            return Optional.empty();
        }

        UmsUserEntity umsUserEntity = umsUserMapper.selectById(id);
        if(umsUserEntity == null){
            return Optional.empty();
        }

        return Optional.of(userEntity2UserResponse(umsUserEntity));
    }

    @Override
    public int changePwd(UserChangePwdRequest request) {
        if(umsUserMapper.selectById(request.getId()) == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        UmsUserEntity umsUserEntity = new UmsUserEntity();
        umsUserEntity.setId(request.getId());
        umsUserEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        return umsUserMapper.updateById(umsUserEntity);
    }

    @Override
    public int changeEnable(Long id) {
        UmsUserEntity umsUserEntity = umsUserMapper.selectById(id);
        if(umsUserEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        umsUserEntity.setId(id);
        umsUserEntity.setEnable(!umsUserEntity.getEnable());
        return umsUserMapper.updateById(umsUserEntity);
    }

    @Override
    public int changeLocked(Long id) {
        UmsUserEntity umsUserEntity = umsUserMapper.selectById(id);
        if(umsUserEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        umsUserEntity.setId(id);
        umsUserEntity.setLocked(!umsUserEntity.getLocked());
        return umsUserMapper.updateById(umsUserEntity);
    }

    @Override
    public void avatarUpload(MultipartFile file) throws Exception{
        String fileExtend = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = String.valueOf(UUID.randomUUID()).replaceAll("-", "")+fileExtend;
        String path = DateUtil.formatDate(new Date())+"//"+ fileName;
        File sourceFile = FileUtil.touch(filePath+"//"+ path);
        file.transferTo(sourceFile);

        LoginUser loginUser = SecurityUtil.getCurrentLoginUser();
        UmsUserEntity umsUserEntity = umsUserMapper.selectById(loginUser.getUmsUserEntity().getId());
        umsUserEntity.setHeadImg(path);
        umsUserMapper.updateById(umsUserEntity);

        LoginUser currentLoginUser = SecurityUtil.getCurrentLoginUser();
        if(currentLoginUser!=null) {
            currentLoginUser.getUmsUserEntity().setHeadImg(path);
        }
    }


    private void checkPhone(Long id, String phone){
        if(StrUtil.isEmpty(phone)){
            return;
        }
        LambdaQueryWrapper<UmsUserEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(id!=null, UmsUserEntity :: getId, id);
        wrapper.eq(UmsUserEntity :: getPhone, phone);
        List<UmsUserEntity> umsUserEntityList = umsUserMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(umsUserEntityList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.USER_PHONE_EXIST);
        }
    }

    private void checkLoginName(Long id, String loginName){
        LambdaQueryWrapper<UmsUserEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(id!=null, UmsUserEntity :: getId, id);
        wrapper.eq(UmsUserEntity :: getLoginName, loginName);
        List<UmsUserEntity> umsUserEntityList = umsUserMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(umsUserEntityList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.USER_LOGIN_NAME_EXIST);
        }
    }

    private void checkEmail(Long id, String email){
        if(StrUtil.isEmpty(email)){
            return;
        }
        LambdaQueryWrapper<UmsUserEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.ne(id!=null, UmsUserEntity :: getId, id);
        wrapper.eq(UmsUserEntity :: getEmail, email);
        List<UmsUserEntity> umsUserEntityList = umsUserMapper.selectList(wrapper);
        if(CollUtil.isNotEmpty(umsUserEntityList)){
            throw new BizCustomException(AuthSystemBizCodeEnum.USER_EMAIL_EXIST);
        }
    }

    private void checkDept(Long deptId){
        if(deptId == null){
            return;
        }
        UmsDeptEntity entity = umsDeptMapper.selectById(deptId);
        if(entity == null){
            throw new BizCustomException(AuthSystemBizCodeEnum.USER_DEPT_NOT_EXIST);
        }
    }

    private UserResponse userEntity2UserResponse(UmsUserEntity entity){
        UserResponse userResponse = UserConvert.instance.umsUserEntity2UserResponse(entity);
        List<UmsRoleEntity> umsRoleEntityList = umsRoleMapper.selectByUserId(entity.getId());
        if(CollUtil.isNotEmpty(umsRoleEntityList)) {
            List<RoleResponse> roleResponseList = umsRoleEntityList.stream().
                    map(roleEntity -> RoleConvert.instance.umsRoleEntity2RoleResponse(roleEntity)).collect(Collectors.toList());
            userResponse.setRoleList(roleResponseList);
        }

        UmsDeptEntity umsDeptEntity = umsDeptMapper.selectById(entity.getDeptId());
        DeptResponse deptResponse = DeptConvert.instance.umsDeptEntity2DeptResponse(umsDeptEntity);
        userResponse.setDept(deptResponse);
        return userResponse;
    }
}
