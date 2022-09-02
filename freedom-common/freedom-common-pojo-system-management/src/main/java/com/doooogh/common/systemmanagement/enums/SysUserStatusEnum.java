package com.doooogh.common.systemmanagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 用户状态
 */
@Getter
@AllArgsConstructor
public enum SysUserStatusEnum {

        NORMAL("正常"),
        LOCKED("锁定"),
        DISABLED("禁用"),
        ;
        private String message;
}
