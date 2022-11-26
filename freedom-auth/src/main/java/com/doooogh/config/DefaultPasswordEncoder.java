package com.doooogh.config;

import cn.hutool.crypto.SecureUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 密码处理
 */
public class DefaultPasswordEncoder implements PasswordEncoder {


    /**
     * @description: 密码进行MD5加密
     * @author Li
     * @date 2022/10/29
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return SecureUtil.md5(rawPassword.toString());
    }

    /**
     * @description: 进行密码比对
     * @author Li
     * @date 2022/10/29
     */

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(SecureUtil.md5(encodedPassword));
    }
}
