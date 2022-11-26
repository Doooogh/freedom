package com.doooogh.handlers;

import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 自定义未授权拒绝访问处理类
 */
public class NoAccessHandler implements AccessDeniedHandler {


    /**
     * @description: 认证失败，拒绝访问
     * @author Li
     * @date 2022/10/29
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.out(response, ResultEnum.NO_PERMISSION);
    }
}
