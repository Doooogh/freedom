package com.doooogh.common.systemmanagement.entity;

import com.doooogh.publicused.entity.BaseDateEntity;
import lombok.Data;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 菜单
 */

@Data
public class SysRoleMenu extends BaseDateEntity {

    private Long roleId;

    private Long menuId;

}
