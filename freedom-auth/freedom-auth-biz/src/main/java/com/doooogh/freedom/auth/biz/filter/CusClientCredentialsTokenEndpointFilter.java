package com.doooogh.freedom.auth.biz.filter;

import com.doooogh.freedom.auth.biz.exceptions.ClientCredentialsException;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/08
 * @description 自定义客户端验证过滤器
 */
@Data
public class CusClientCredentialsTokenEndpointFilter extends ClientCredentialsTokenEndpointFilter {

    private final AuthorizationServerSecurityConfigurer configurer;
    private AuthenticationEntryPoint authenticationEntryPoint;


    public CusClientCredentialsTokenEndpointFilter(AuthorizationServerSecurityConfigurer configurer, AuthenticationEntryPoint authenticationEntryPoint) {
        this.configurer = configurer;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    /**
     * @description: 需要重写这个方法返回AuthenticationManager
     * @author Li
     * @date 2024/1/15
     */
    @Override
    protected AuthenticationManager getAuthenticationManager() {
        return configurer.and().getSharedObject(AuthenticationManager.class);
    }

    /**
     * @description: 设置AuthenticationEntryPoint主要逻辑
     * @author Li
     * @date 2024/1/15
     */
    @Override
    public void afterPropertiesSet() {
        setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                authenticationEntryPoint.commence(httpServletRequest, httpServletResponse, new ClientCredentialsException());
            }
        });
        setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                // 无操作-仅允许过滤器链继续到令牌端点
            }
        });
    }
}
