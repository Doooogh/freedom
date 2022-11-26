package com.doooogh.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

        @Override
        public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
                super.configure(security);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
                endpoints.
                super.configure(endpoints);
        }
}
