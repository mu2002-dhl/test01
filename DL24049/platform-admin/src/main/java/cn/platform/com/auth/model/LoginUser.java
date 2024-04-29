package cn.platform.com.auth.model;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.entity.UmsUserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/12 21:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUser implements UserDetails {

    private List<String> permissions;
    private UmsUserEntity umsUserEntity;
    private List<UmsRoleEntity> umsRoleEntityList;
    private UmsDeptEntity umsDeptEntity;
    private String token;

    @JsonIgnore
    private List<SimpleGrantedAuthority> authorities;

    public LoginUser(UmsUserEntity umsUserEntity, List<String> permissions){
        this.umsUserEntity = umsUserEntity;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null){
            return authorities;
        }

        if(CollUtil.isEmpty(permissions)){
            return authorities;
        }

        authorities = permissions.stream().filter(authorities-> StrUtil.isNotEmpty(authorities)).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return umsUserEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUserEntity.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        if(umsUserEntity.getAccountExpiredTime() == null){
            return true;
        }
        return DateUtil.compare(new Date(), umsUserEntity.getAccountExpiredTime()) <= 0;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !umsUserEntity.getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(umsUserEntity.getPwdExpiredTime() == null){
            return true;
        }
        return DateUtil.compare(new Date(), umsUserEntity.getPwdExpiredTime()) <= 0;
    }

    @Override
    public boolean isEnabled() {
        return umsUserEntity.getEnable();
    }
}
