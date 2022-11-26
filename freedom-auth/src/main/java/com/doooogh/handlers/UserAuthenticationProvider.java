package com.doooogh.handlers;

import com.doooogh.common.systemmanagement.enums.SysUserStatusEnum;
import com.doooogh.entity.SecurityUser;
import com.doooogh.enums.AuthEnum;
import com.doooogh.service.AuthSysRoleService;
import com.doooogh.service.CusUserDetailsService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 自定义登录验证
 */
@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private CusUserDetailsService cusUserDetailsService;

    @Resource
    private AuthSysRoleService authSysRoleService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取表单输入中返回的用户名
        String userName = (String) authentication.getPrincipal();
        // 获取表单中输入的密码
        String password = (String) authentication.getCredentials();
        // 查询用户是否存在
        SecurityUser userInfo = (SecurityUser) cusUserDetailsService.loadUserByUsername(userName);
        // 我们还要判断密码是否正确，这里我们的密码使用BCryptPasswordEncoder进行加密的
        if (!new BCryptPasswordEncoder().matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException(AuthEnum.PASSWORD_ERROR.getMessage());
        }
        // 还可以加一些其他信息的判断，比如用户账号已停用等判断
        if (userInfo.getStatus().equals(SysUserStatusEnum.LOCKED.name())) {
            throw new LockedException(AuthEnum.ACCOUNT_LOCKED.getMessage());
        } else if (userInfo.getStatus().equals(SysUserStatusEnum.DISABLED.name())) {
            throw new DisabledException(AuthEnum.ACCOUNT_DISABLED.getMessage());
        }
        // 角色集合
        Set<GrantedAuthority> authorities = new HashSet<>();
        // 查询用户角色
        List<String> roleList = userInfo.getRoleList();
        for (String roleCode : roleList) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleCode));
        }
        // 进行登录
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
