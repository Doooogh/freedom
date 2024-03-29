package com.doooogh.freedom.auth.biz.config;

import com.doooogh.freedom.auth.biz.filter.CusClientCredentialsTokenEndpointFilter;
import com.doooogh.freedom.auth.biz.point.CusAuthenticationEntryPoint;
import com.doooogh.freedom.auth.biz.point.CusWebResponseExceptionTranslator;
import com.doooogh.freedom.auth.biz.service.CusUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/04
 * @description OAuth 相关配置
 * 认证服务配置
 */
@Configuration
@EnableAuthorizationServer
public class OAuthConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    @Autowired
    private CusWebResponseExceptionTranslator cusWebResponseExceptionTranslator;

    @Autowired
    private OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint;

    @Autowired
    private CusUserDetailService cusUserDetailService;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()// 使用in‐memory存储
                .withClient("c1")// client_id
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .resourceIds("res1")
                .authorizedGrantTypes("authorization_code", "password", "client_credentials", "implicit", "refresh_token")// 该client允许的授权类型 authorization_code,password,refresh_token,implicit,client_credentials
                .scopes("all")// 允许的授权范围
                .autoApprove(false) //加上验证回调地址
                .authorities("admin")
                .redirectUris("http://www.baidu.com");
    }

    //设置授权码模式的授权码如何存取，暂时采用内存方式
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }

    @Bean
    public AuthorizationServerTokenServices tokenService() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setClientDetailsService(clientDetailsService);
        service.setSupportRefreshToken(true);
        service.setTokenStore(tokenStore);
        //令牌增强
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> tokenEnhancers = new ArrayList<>();
        tokenEnhancers.add(accessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(tokenEnhancers);

        service.setTokenEnhancer(tokenEnhancerChain);
        service.setAccessTokenValiditySeconds(7200); // 令牌默认有效期2小时
        service.setRefreshTokenValiditySeconds(259200); // 刷新令牌默认有效期3天
        return service;
    }


    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)//认证管理器
                .userDetailsService(cusUserDetailService)
                .tokenServices(tokenService())//令牌管理服务
                .accessTokenConverter(accessTokenConverter)
                .authorizationCodeServices(authorizationCodeServices())//授权码服务
                //设置异常WebResponseExceptionTranslator  用于处理用户名密码 授权类型不正确异常
                .exceptionTranslator(cusWebResponseExceptionTranslator)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        CusClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter = new CusClientCredentialsTokenEndpointFilter(security, oAuth2AuthenticationEntryPoint);
        clientCredentialsTokenEndpointFilter.afterPropertiesSet();
        security.addTokenEndpointAuthenticationFilter(clientCredentialsTokenEndpointFilter);
        security
                .passwordEncoder(passwordEncoder())
                .tokenKeyAccess("permitAll()")                    //oauth/token_key是公开
                .checkTokenAccess("permitAll()")                  //oauth/check_token公开
                //这里不能加 allowFormAuthenticationForClients
//                一旦设置了 allowFormAuthenticationForClients 为true，则会创建 ClientCredentialsTokenEndpointFilter，此时自定义的自然失效了。
//                .allowFormAuthenticationForClients()
                .authenticationEntryPoint(new CusAuthenticationEntryPoint()) //认证失败处理
        ;
        //表单认证（申请令牌）
    }


} 
