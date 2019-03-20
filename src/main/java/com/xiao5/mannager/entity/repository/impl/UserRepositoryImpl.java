package com.xiao5.mannager.entity.repository.impl;

import com.xiao5.mannager.entity.Users;
import com.xiao5.mannager.entity.repository.IUsersRepository;
import com.xiao5.mannager.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author WuTian Bing
 * @version 1.0
 * @classname UserRepositoryImpl
 * @date 2019/3/20 21:16
 **/
@Repository("userRepositoryImpl")
public class UserRepositoryImpl implements IUsersRepository {
    @Autowired
    UsersMapper usersMapper;

    @Override
    public Users qryUsersById(Long id) {
        return usersMapper.qryUsersById(id);
    }
}
