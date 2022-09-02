package com.doooogh.systemmanagement.controller;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.publicused.response.Result;
import com.doooogh.systemmanagement.service.SysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;


    @GetMapping("/getUserByUsername/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        SysUser userByUsername = sysUserService.getUserByUsername(username);
        return Result.success(userByUsername);
    }

}
