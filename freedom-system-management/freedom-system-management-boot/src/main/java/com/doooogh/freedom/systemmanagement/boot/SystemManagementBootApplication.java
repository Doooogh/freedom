package com.doooogh.freedom.systemmanagement.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/05
 * @description
 */

@SpringBootApplication
@ComponentScan("com.doooogh.freedom.systemmanagement")
@MapperScan("com.doooogh.**.mapper")
public class SystemManagementBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemManagementBootApplication.class);
    }
}
