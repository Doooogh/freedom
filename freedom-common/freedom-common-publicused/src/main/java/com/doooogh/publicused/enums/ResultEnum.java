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
    INVALID_GRANT(400, "账号密码错误"),
    NO_AUTH(401, "认证失败"),
    NO_PERMISSION(403, "没有权限"),
    NOT_FIND_LOGIN_INFORMATION(500, "用户不存在"),
    NOT_LOGIN(500, "未登录，请先进行登录"),
    ILLEGAL_ARGUMENT(500, "参数校验错误"),
    USER_DISABLED(500, "已被禁用"),
    USER_LOCKED(500, "已被锁定"),
    ACCOUNT_EXPIRED(500, "账号过期"),
    CREDENTIALS_EXPIRED(500, "证书过期"),
    ;

    private int code;
    private String message;

}
