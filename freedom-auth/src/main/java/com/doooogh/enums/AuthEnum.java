package com.doooogh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 用户认证授权枚举
 */
@Getter
@AllArgsConstructor
public enum AuthEnum {

    NOT_FIND_USER("用户不存在"),
    PASSWORD_ERROR("用户名或密码错误"),
    CREDENTIALS_EXPIRED("凭证已过期"),
    ACCOUNT_DISABLED("该用户已被禁用"),
    ACCOUNT_LOCKED("该用户已被锁定"),
    PERMISSION_DENIED("没有访问权限"),
    ;
    private String message;
}
