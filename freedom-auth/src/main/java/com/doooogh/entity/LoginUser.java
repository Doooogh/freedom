package com.doooogh.entity;

import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Data
public class LoginUser {

        private String username;

        private String password;

        /**
         * 验证码
         */
        private String verificationCode;
}
