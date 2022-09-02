package com.doooogh.service;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.entity.SecurityUser;
import com.doooogh.enums.AuthEnum;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Service
public class CusUserDetailsService implements UserDetailsService {


    @Resource
    private AuthSysUserService authSysUserService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = authSysUserService.getUserByUsername(username);
        if (sysUser == null) {
            throw new UsernameNotFoundException(AuthEnum.NOT_FIND_USER.getMessage());
        }
        SecurityUser securityUser = new SecurityUser(sysUser);
        return securityUser;
    }
}
