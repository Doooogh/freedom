package com.doooogh.freedom.auth.biz.handlers;

import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Li m13283354149@163.com
 * @date 2024/01/15
 * @description
 * 资源服务器处理权限不足
 */
@Component
public class CusRequestAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                ResponseUtil.out(response, ResultEnum.NO_PERMISSION);
        }
}
