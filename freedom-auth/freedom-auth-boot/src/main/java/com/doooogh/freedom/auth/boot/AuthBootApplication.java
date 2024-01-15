package com.doooogh.freedom.auth.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/04
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.doooogh")
@EnableFeignClients(basePackages = "com.doooogh.freedom.auth")
@MapperScan("com.doooogh.freedom.auth.**.mapper")
public class AuthBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthBootApplication.class);
    }
}
