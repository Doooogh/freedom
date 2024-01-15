package com.doooogh.freedom.auth.biz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Li m13283354149@163.com
 * @date 2023/07/04
 * @description
 */
@RestController
public class TestController {

        @GetMapping("/test")
        public String test(){
                return "testStr";
        }
}
