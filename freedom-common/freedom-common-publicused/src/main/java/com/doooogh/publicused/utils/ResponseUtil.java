package com.doooogh.publicused.utils;

import com.alibaba.fastjson.JSON;
import com.doooogh.publicused.enums.ResultEnum;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 响应工具类
 */


public class ResponseUtil {


    /**
     * @description: 自定义错误返回
     * @author Li
     * @date 2022/10/29
     */
    public static void out(HttpServletResponse response, ResultEnum resultEnum) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.setStatus(resultEnum.getCode());
            writer.write(JSON.toJSONString(resultEnum));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(null!=writer){
                writer.flush();
                writer.close();
            }
        }

    }

}
