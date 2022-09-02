package com.doooogh.service;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.fiegns.SystemManagementFeign;
import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.exceptions.FeignRequestException;
import com.doooogh.publicused.response.Result;
import com.doooogh.publicused.utils.FeignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Service
@Slf4j
public class AuthSysUserService {

    @Resource
    private SystemManagementFeign systemManagementFeign;


    /**
     * @description: 根据用户名获取用户信息
     * @author Li
     * @date 2022/9/3
     */
    public SysUser getUserByUsername(String username)  {
        Result<SysUser> userByUsername = systemManagementFeign.getUserByUsername(username);
        FeignUtil.verify(userByUsername);
        return userByUsername.getData();
    }
}
