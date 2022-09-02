package com.doooogh.systemmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doooogh.common.systemmanagement.entity.SysRole;

import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * @description: 根据用户ID获取角色列表
     * @author Li
     * @date 2022/9/2
     */
    List<SysRole> getRoleListByUserId(Long userId);
}
