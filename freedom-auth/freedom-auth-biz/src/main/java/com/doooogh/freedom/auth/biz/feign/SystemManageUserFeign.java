package com.doooogh.freedom.auth.biz.feign;

import com.doooogh.common.systemmanagement.entity.SysUser;
import com.doooogh.publicused.response.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/05
 * @description
 */
@FeignClient(name = "system-management", url = "http://127.0.0.1:8111/system-management")
public interface SystemManageUserFeign {

    @GetMapping("/sysUser/getUserByUsername/{username}")
    Result<SysUser> getUserByUsername(@PathVariable("username") String username);
}
