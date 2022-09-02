package com.doooogh.handlers;

import com.doooogh.constants.AuthRedisConstant;
import com.doooogh.entity.SecurityUser;
import com.doooogh.servers.redis.utils.RedisUtil;
import com.doooogh.utils.JwtTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 登陆成功
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // TODO: 2022/9/2 登录成功
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        String token = jwtTokenUtil.generateToken(securityUser);
        redisUtil.set(AuthRedisConstant.LOGIN_USER+securityUser.getUserId(),token);
    }
}
