package com.doooogh.publicused.utils;

import com.alibaba.fastjson.JSON;
import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.response.Result;

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
    //todo 返回会乱码
    public static void out(HttpServletResponse response, ResultEnum resultEnum) {
        PrintWriter writer = null;
        try {
            Result result=new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMessage());
            writer = response.getWriter();
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json");
            response.setStatus(resultEnum.getCode());
            writer.write(JSON.toJSONString(result));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(null!=writer){
                writer.flush();
                writer.close();
            }
        }
    }

    public static void out(HttpServletResponse response, int code,String  message) {
        PrintWriter writer = null;
        try {
            Result result=new Result();
            result.setCode(code);
            result.setMessage(message);
            writer = response.getWriter();
            response.setContentType("application/json");
            response.setStatus(code);
            writer.write(JSON.toJSONString(result));
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
