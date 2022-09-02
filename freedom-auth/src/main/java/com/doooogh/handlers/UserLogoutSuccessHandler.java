package com.doooogh.handlers;

import com.doooogh.constants.AuthRedisConstant;
import com.doooogh.entity.SecurityUser;
import com.doooogh.servers.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 成功登出
 */
@Component
@Slf4j
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisUtil redisUtil;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Long userId = securityUser.getUserId();
        redisUtil.delete(AuthRedisConstant.LOGIN_USER + userId);
        log.error("用户{}已登出", userId);
    }
}
