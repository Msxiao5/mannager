package com.xiao5.mannager.manager;

import com.xiao5.mannager.entity.Users;

/**
 * TODO
 *
 * @author WuTian Bing
 * @version 1.0
 * @classname UsersManager
 * @date 2019/3/20 21:17
 **/
public class UsersManager {


    public static Users qryUsersById(Long id) {

        return Users.repository().qryUsersById(id);
    }
}
