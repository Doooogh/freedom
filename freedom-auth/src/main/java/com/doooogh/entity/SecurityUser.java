package com.doooogh.entity;

import com.doooogh.common.systemmanagement.entity.SysRole;
import com.doooogh.common.systemmanagement.entity.SysUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */

@Data
@NoArgsConstructor
public class SecurityUser implements UserDetails {


    private Long userId;
    //状态
    private String status;

    private List<String> roleList;


    public SecurityUser(SysUser sysUser) {
        this.userId = sysUser.getId();
        this.roleList = sysUser.getRoleList().stream().map(SysRole::getCode).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
