package com.doooogh.freedom.auth.biz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Li m13283354149@163.com
 * @date 2024/01/15
 * @description 认证结果
 */
@Getter
@AllArgsConstructor
public enum AuthorizationEnum {


        NO_USER(500,"该用户不存在"),
        PASSWORK_ERROR(500,"账号密码错误"),
        CLIENT_ERROR(500,"客户端验证错误"),
        UNSUPPORT_GRANT_TYPE(500,"不支持该授权类型"),
        ;

        private int code;

        private String message;
}
