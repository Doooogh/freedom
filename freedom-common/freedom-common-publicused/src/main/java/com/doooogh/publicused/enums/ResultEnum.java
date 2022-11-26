package com.doooogh.publicused.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 返回值枚举
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "成功"),
    ERROR(500, "系统错误"),
    NO_PERMISSION(403, "没有权限"),
    NOT_FIND_LOGIN_INFORMATION(500, "认证失败"),
    NOT_LOGIN(500, "未登录，请先进行登录"),
    ILLEGAL_ARGUMENT(500, "参数校验错误"),
    ;

    private int code;
    private String message;

}
