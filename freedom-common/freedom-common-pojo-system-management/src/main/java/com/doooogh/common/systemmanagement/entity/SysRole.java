package com.doooogh.common.systemmanagement.entity;

import com.doooogh.publicused.entity.BaseDateEntity;
import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 角色
 */

@Data
public class SysRole extends BaseDateEntity {

    private String name;

    private String code;

    private String description;

}
