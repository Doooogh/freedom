package com.doooogh.freedom.systemmanagement.api.controller;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.freedom.systemmanagement.biz.service.SysUserService;
import com.doooogh.publicused.response.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
