package com.doooogh.freedom.auth.biz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/04
 * @description spring security 配置
 *  Spring Security 5.7.0-M2 版本开始 被标记为弃用
 * 资源配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {




    //认证管理器
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
