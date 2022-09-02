package com.doooogh.systemmanagement.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doooogh.common.systemmanagement.entity.SysRole;
import com.doooogh.common.systemmanagement.entity.SysUserRole;
import com.doooogh.publicused.entity.BaseEntity;
import com.doooogh.systemmanagement.mapper.SysRoleMapper;
import com.doooogh.systemmanagement.mapper.SysRoleMenuMapper;
import com.doooogh.systemmanagement.mapper.SysUserRoleMapper;
import com.doooogh.systemmanagement.service.SysRoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;


    @Override
    public List<SysRole> getRoleListByUserId(Long userId) {
        List<SysRole> roleList = new ArrayList<>();
        Assert.notNull(userId, "userId must not be null");
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>()
                .eq(SysUserRole::getUserId, userId));
        List<Long> roleIdList = new ArrayList<>();
        if (CollUtil.isNotEmpty(sysUserRoles)) {
            roleIdList = sysUserRoles.stream().map(SysUserRole::getRoleId).distinct().collect(Collectors.toList());
        }
        if (CollUtil.isEmpty(roleIdList)) {
            return roleList;
        }
        roleList = this.list(new LambdaQueryWrapper<SysRole>()
                .in(BaseEntity::getId, roleIdList));
        return roleList;
    }
}
