package com.doooogh.servers.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 配置分页插件
 */
@Configuration
public class MybatisPlusConfig {


        /**
         * @description: 最新版配置
         * @author Li
         * @date 2022/9/2
         */
        @Bean
        public MybatisPlusInterceptor paginationInterceptor() {
                MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
                interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
                return interceptor;
        }
}
