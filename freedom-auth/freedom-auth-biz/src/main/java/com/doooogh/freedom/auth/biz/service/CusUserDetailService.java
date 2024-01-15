package com.doooogh.freedom.auth.biz.service;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.freedom.auth.biz.entity.OauthUser;
import com.doooogh.freedom.auth.biz.enums.AuthorizationEnum;
import com.doooogh.freedom.auth.biz.feign.SystemManageUserFeign;
import com.doooogh.publicused.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/05
 * @description
 */
@Component
@Slf4j
public class CusUserDetailService implements UserDetailsService {


    @Autowired
    private SystemManageUserFeign systemManageUserFeign;

    @Override
    public UserDetails loadUserByUsername(String username) {
        OauthUser oauthUser = null;
        try {
            Result<SysUser> userByUsername = systemManageUserFeign.getUserByUsername(username);
            SysUser data = userByUsername.getData();
            oauthUser = new OauthUser(data);
            // todo 根据DB中信息 做状态判断
            return oauthUser;
        } catch (IllegalArgumentException e) {
            throw new UsernameNotFoundException(AuthorizationEnum.NO_USER.getMessage());
        } catch (Exception e) {
            throw new UsernameNotFoundException(AuthorizationEnum.PASSWORK_ERROR.getMessage());
        }
    }
}
