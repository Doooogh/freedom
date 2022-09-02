package com.doooogh.handlers;

import com.doooogh.enums.AuthEnum;
import com.doooogh.publicused.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 登录失败处理
 */
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)  {
        // 这些对于操作的处理类可以根据不同异常进行不同处理
        if (exception instanceof UsernameNotFoundException) {
            log.error("【登录失败】" + exception.getMessage());
            Result.error(AuthEnum.NOT_FIND_USER.getMessage());
        } else if (exception instanceof LockedException) {
            log.error("【登录失败】" + exception.getMessage());
            Result.error(AuthEnum.ACCOUNT_LOCKED.getMessage());
        } else if (exception instanceof BadCredentialsException) {
            log.error("【登录失败】" + exception.getMessage());
            Result.error(AuthEnum.PASSWORD_ERROR.getMessage());
        } else if (exception instanceof DisabledException) {
            log.error("【登录失败】" + exception.getMessage());
            Result.error(AuthEnum.ACCOUNT_DISABLED.getMessage());
        }
        Result.error("【登录失败】系统错误");
    }
}
