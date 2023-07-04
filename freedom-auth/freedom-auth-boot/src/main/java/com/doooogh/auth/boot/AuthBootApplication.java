package com.doooogh.auth.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/04
 * @description
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.doooogh")
public class AuthBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthBootApplication.class);
    }
}
