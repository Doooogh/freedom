package com.doooogh.publicused.utils;

import com.doooogh.publicused.enums.ResultEnum;
import com.doooogh.publicused.exceptions.FeignRequestException;
import com.doooogh.publicused.response.Result;

/**
 * @author Li m13283354149@163.com
 * @date 2022/9/2
 * @description 服务之间调用判断
 */

public class FeignUtil {

    public static void verify(Result result) throws FeignRequestException {
        int code = result.getCode();
        if(code!= ResultEnum.SUCCESS.getCode()){
            throw new FeignRequestException(result.getCode(),String.format("服务调用失败，错误信息：%s",result.getMessage()));
        }
    }

}
