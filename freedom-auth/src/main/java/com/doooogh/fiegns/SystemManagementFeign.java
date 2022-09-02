package com.doooogh.fiegns;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.publicused.constants.ServerConstant;
import com.doooogh.publicused.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description
 */
@FeignClient(name = ServerConstant.SYSTEM_MANAGEMENT)
@Component
public interface SystemManagementFeign {

    /**
     *
     * @description: 根据用户名获取用户信息
     * @author Li
     * @date 2022/9/3
     */
    @GetMapping("/sysUser/getUserByUsername/{username}")
    Result<SysUser> getUserByUsername(@PathVariable("username") String username);


}
