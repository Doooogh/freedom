package com.doooogh.publicused.response;

import com.doooogh.publicused.enums.ResultEnum;
import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Data
public class Result<T> {


    private int code;

    private String message;

    private T data;

    public Result() {
        this(ResultEnum.ERROR);
    }


    public Result(ResultEnum resultEnum) {
        this(resultEnum, null);
    }

    public Result(ResultEnum resultEnum, T data) {
        this(resultEnum.getCode(), resultEnum.getMessage(), data);
    }


    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public static Result success(String message, Object data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
    }

    public static Result success(Object data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
    }

    public static Result error(String message) {
        return new Result(ResultEnum.ERROR.getCode(), message, null);
    }

    public static Result error(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMessage(), null);
    }

}
