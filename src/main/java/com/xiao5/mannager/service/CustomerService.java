package com.xiao5.mannager.service;

import com.xiao5.mannager.entity.Users;
import com.xiao5.mannager.manager.UsersManager;
import com.xiao5.mannager.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户业务层
 * @author WuTian Bing
 * @version 1.0
 * @classname CustomerService
 * @date 2019/3/20 15:55
 **/
@Service
public class CustomerService {

    public Users qryUsersById(Long id) {
        return UsersManager.qryUsersById(id);
    }
}
