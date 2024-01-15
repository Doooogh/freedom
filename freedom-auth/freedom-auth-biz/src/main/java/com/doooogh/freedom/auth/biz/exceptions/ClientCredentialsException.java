package com.doooogh.freedom.auth.biz.exceptions;

import com.doooogh.freedom.auth.biz.enums.AuthorizationEnum;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;

/**
 * @author Li m13283354149@163.com
 * @date 2024/01/15
 * @description
 * 客户端ID、秘钥异常
 */
@Data
public class ClientCredentialsException extends AuthenticationException {
    private int code;


    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public ClientCredentialsException() {
        super(AuthorizationEnum.CLIENT_ERROR.getMessage());
        this.code = AuthorizationEnum.NO_USER.getCode();
    }
}
