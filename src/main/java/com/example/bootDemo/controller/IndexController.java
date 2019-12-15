package com.example.bootDemo.controller;

import com.example.bootDemo.common.model.response.ResponseException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shengtao on 2019/12/8.
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @GetMapping("/test/exception")
    public String index(String name) {
        System.out.println(name);
        if (StringUtils.isEmpty(name)) {
            throw new ResponseException("这是自定义业务异常");
        }
        return name;
    }
}
