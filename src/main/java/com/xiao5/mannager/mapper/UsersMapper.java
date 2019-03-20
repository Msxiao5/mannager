package com.xiao5.mannager.mapper;

import com.xiao5.mannager.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 客户mapper
 * @author WuTian Bing
 * @version 1.0
 * @classname UsersMapper
 * @date 2019/3/20 16:16
 **/
@Mapper
public interface UsersMapper {

    Users qryUsersById(@Param(value = "id") Long id);
}
