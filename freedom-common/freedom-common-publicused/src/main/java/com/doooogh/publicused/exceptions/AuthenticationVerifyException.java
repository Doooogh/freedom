package com.doooogh.publicused.exceptions;

import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 验证异常
 */
@Data
public class AuthenticationVerifyException extends RuntimeException {
    private int code;

    public AuthenticationVerifyException(int code, String message) {
        super(message);
        this.code = code;
    }
}
