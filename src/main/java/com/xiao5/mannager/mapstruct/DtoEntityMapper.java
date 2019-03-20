package com.xiao5.mannager.mapstruct;

import com.xiao5.mannager.core.convert.EntityDtoTypeConvertor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 实体转换类
 * @author WuTian Bing
 * @version 1.0
 * @classname DtoEntityMapper
 * @date 2019/3/20 15:57
 **/
@Mapper(uses = EntityDtoTypeConvertor.class)
public class DtoEntityMapper {

    DtoEntityMapper INSTANCE = Mappers.getMapper(DtoEntityMapper.class);



}
