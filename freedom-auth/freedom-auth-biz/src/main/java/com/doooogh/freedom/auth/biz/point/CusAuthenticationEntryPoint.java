package com.doooogh.freedom.auth.biz.point;

import com.doooogh.freedom.auth.biz.exceptions.ClientCredentialsException;
import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/05
 * @description
 */
@Component
@Slf4j
public class CusAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
        if(ex instanceof ClientCredentialsException){
            ResponseUtil.out(response,((ClientCredentialsException) ex).getCode(), ex.getMessage());
        }else{
            ResponseUtil.out(response, ResultEnum.NO_AUTH);
        }
    }
}
