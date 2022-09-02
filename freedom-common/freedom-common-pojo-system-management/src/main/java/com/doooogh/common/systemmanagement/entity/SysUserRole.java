package com.doooogh.common.systemmanagement.entity;

import com.doooogh.publicused.entity.BaseDateEntity;
import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 用户角色
 */

@Data
public class SysUserRole extends BaseDateEntity {

    private Long userId;

    private Long roleId;

}
