package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.UmsUserEntity;
import cn.platform.com.auth.model.request.UserAddRequest;
import cn.platform.com.auth.model.request.UserChangePwdRequest;
import cn.platform.com.auth.model.request.UserModifyRequest;
import cn.platform.com.auth.model.request.UserPageRequest;
import cn.platform.com.auth.model.response.PageResponse;
import cn.platform.com.auth.model.response.UserResponse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
public interface UmsUserService extends IService<UmsUserEntity> {

    PageResponse<UserResponse> page(UserPageRequest request);

    UmsUserEntity add(UserAddRequest request);

    UmsUserEntity modify(UserModifyRequest request);

    int delete(Long id);

    Optional<UserResponse> detail(Long id);

    int changePwd(UserChangePwdRequest request);

    int changeEnable(Long id);

    int changeLocked(Long id);

    void avatarUpload(MultipartFile file) throws Exception;
}
