package com.doooogh.handlers;

import com.doooogh.constants.AuthRedisConstant;
import com.doooogh.entity.SecurityUser;
import com.doooogh.servers.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 登出
 */
@Component
@Slf4j
public class UserLogoutSuccessHandler implements LogoutHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String username = securityUser.getUsername();
        redisUtil.delete(AuthRedisConstant.LOGIN_USER + username);
        log.info("用户{}已成功登出", username);
    }
}
