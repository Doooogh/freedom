package com.doooogh.service;

import com.doooogh.common.systemmanagement.entity.SysRole;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Service
public class AuthSysRoleService {


    /**
     * @description: 根据用户ID获取用户角色列表
     * @author Li
     * @date 2022/9/2
     */
    public List<SysRole> getRoleListByUserId(Long userId){
        // TODO: 2022/9/2 获取用户角色
       return new ArrayList<>();
    }
}
