package com.doooogh.filters;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 自定义过滤器-认证过滤器  token 账号密码 登录
 */

import com.doooogh.constants.AuthRedisConstant;
import com.doooogh.entity.LoginUser;
import com.doooogh.entity.SecurityUser;
import com.doooogh.publicused.response.Result;
import com.doooogh.servers.redis.utils.RedisUtil;
import com.doooogh.utils.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 * @author Li
 * @description: 自定义 登录过滤
 * @date 2022/10/29
 */
@Slf4j
public class PasswordLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public PasswordLoginFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.setPostOnly(false);
        //设置登录路径和提交方式
        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));
    }

    @SneakyThrows
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), new ArrayList<>()));
    }

    /**
     * @description: 认证成功后逻辑
     * @author Li
     * @date 2022/10/29
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
        //认证成功，得到认证成功之后用户信息
        SecurityUser user = (SecurityUser) authResult.getPrincipal();
        String username = user.getUsername();
        String token = jwtTokenUtil.generateToken(user);
        //todo 把token存放到redis
        redisUtil.set(AuthRedisConstant.LOGIN_USER + username, token);
        //返回token
        Result.success("登录成功", token);
    }
}
