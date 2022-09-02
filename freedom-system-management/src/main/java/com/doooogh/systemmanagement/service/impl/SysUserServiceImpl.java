package com.doooogh.systemmanagement.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doooogh.common.systemmanagement.entity.SysRole;
import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.systemmanagement.mapper.SysUserMapper;
import com.doooogh.systemmanagement.service.SysRoleService;
import com.doooogh.systemmanagement.service.SysUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Resource
    private SysRoleService sysRoleService;

    @Override
    public SysUser getUserByUsername(String username) {
        Assert.notNull(username, "username is not null");
        SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        Assert.notNull(sysUser, "not find user");
        List<SysRole> roleList = sysRoleService.getRoleListByUserId(sysUser.getId());
        sysUser.setRoleList(roleList);
        return sysUser;
    }
}
