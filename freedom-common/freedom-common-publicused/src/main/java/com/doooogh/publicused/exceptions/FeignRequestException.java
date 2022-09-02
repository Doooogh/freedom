package com.doooogh.publicused.exceptions;

import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Data
public class FeignRequestException extends RuntimeException {

    private int code;

    public FeignRequestException(int code, String message) {
        super(message);
        this.code = code;
    }
}
