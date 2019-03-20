package com.xiao5.mannager.entity.repository;

import com.xiao5.mannager.entity.Users;

/**
 * TODO
 *
 * @author WuTian Bing
 * @version 1.0
 * @classname IUsersRepository
 * @date 2019/3/20 21:15
 **/
public interface IUsersRepository {

    Users qryUsersById(Long id);
}
