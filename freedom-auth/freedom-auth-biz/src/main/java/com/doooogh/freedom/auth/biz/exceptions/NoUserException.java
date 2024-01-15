package com.doooogh.freedom.auth.biz.exceptions;

import com.doooogh.freedom.auth.biz.enums.AuthorizationEnum;
import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2024/01/15
 * @description
 */
@Data
public class NoUserException extends Exception {
    private int code;


    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public NoUserException() {
        super(AuthorizationEnum.NO_USER.getMessage());
        this.code = AuthorizationEnum.NO_USER.getCode();
    }
}
