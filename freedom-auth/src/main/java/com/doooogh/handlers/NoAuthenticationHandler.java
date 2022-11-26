package com.doooogh.handlers;

import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.utils.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 未登录处理类
 */
public class NoAuthenticationHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.out(response, ResultEnum.NOT_LOGIN);
    }
}
