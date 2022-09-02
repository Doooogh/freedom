package com.doooogh.systemmanagement.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.doooogh.common.systemmanagement.entity.SysUser;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
public interface SysUserService extends IService<SysUser> {


        /**
         * @description: 根据username 获取用户信息
         * @author Li
         * @date 2022/9/2
         */
        SysUser getUserByUsername(String username);

}
