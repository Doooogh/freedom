package com.doooogh.freedom.auth.biz.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.doooogh.common.systemmanagement.entity.SysRole;
import com.doooogh.common.systemmanagement.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/05
 * @description
 */
@Data
public class OauthUser implements UserDetails {

    private String username;

    private String password;


    private String name;

    private String email;

    private String mobile;

    //状态
    private String status;

    private String englishName;

    @TableField(exist = false)
    private List<SysRole> roleList;

    public OauthUser(SysUser sysUser) {
        this.username = sysUser.getUsername();
        this.password = sysUser.getPassword();
        this.roleList = sysUser.getRoleList();
        this.status = sysUser.getUserStatus();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("admin"));
        list.add(new SimpleGrantedAuthority("common"));
        return list;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.equals("active");
    }
}
