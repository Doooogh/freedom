package com.doooogh.freedom.auth.biz.config;

import com.doooogh.freedom.auth.biz.point.CusAuthenticationEntryPoint;
import com.doooogh.freedom.auth.biz.service.CusUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/04
 * @description spring security 配置
 * 资源配置
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private CusUserDetailService cusUserDetailService;


    //认证管理器
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cusUserDetailService);

//        auth.inMemoryAuthentication().withUser("admin").password(new BCryptPasswordEncoder().encode("1234")).authorities("admin");
//        auth.inMemoryAuthentication().withUser("common").password(new BCryptPasswordEncoder().encode("1234")).authorities("common");
    }



    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()//exceptionHandling()方法就是调用ExceptionHandlingConfigurer去配置ExceptionTranslationFilter
                .authenticationEntryPoint(new CusAuthenticationEntryPoint()) //用于处理未经身份验证的请求
                .and()
                .csrf().disable()
                ;
    }




}
