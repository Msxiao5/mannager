package com.xiao5.mannager.controller;


import com.xiao5.mannager.entity.Users;
import com.xiao5.mannager.service.CustomerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客户管理controller
 * @author WuTian Bing
 * @version 1.0
 * @classname CustomerController
 * @date 2019/3/20 14:09
 **/
@Api("客户管理")
@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Resource
    CustomerService customerService;

    @GetMapping("/qryUsersById/{id}")
    public Users qryUsersById(@PathVariable("id") Long id){
        //调用开始时间
        Long startTimeMillis = System.currentTimeMillis();

        Users users =  customerService.qryUsersById(id);

        //耗时
        Long timeConsuming = System.currentTimeMillis() - startTimeMillis;
        log.info("【时长:{}毫秒】", timeConsuming);
        return users;
    }
}
