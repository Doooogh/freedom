package com.doooogh.freedom.systemmanagement.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.doooogh.common.systemmanagement.entity.SysRole;
import com.doooogh.common.systemmanagement.entity.SysUserRole;
import com.doooogh.freedom.systemmanagement.biz.mapper.SysUserRoleMapper;
import com.doooogh.publicused.entity.BaseEntity;
import com.doooogh.freedom.systemmanagement.biz.mapper.SysRoleMapper;
import com.doooogh.freedom.systemmanagement.biz.mapper.SysRoleMenuMapper;
import com.doooogh.freedom.systemmanagement.biz.service.SysRoleService;
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
