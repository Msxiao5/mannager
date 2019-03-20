package com.xiao5.mannager.core.convert;

import com.xiao5.mannager.core.utils.DateUtils;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * 实体DTO类型转换工具类
 * @author Wu Tianbing
 */
public class EntityDtoTypeConvertor {
    public Date asDate(String date) {
        return DateUtils.jtDateFormat.parse(date);
    }
    public String asString(Date date) {
        if (date==null){
            return null;
        }
        return DateUtils.jtDateFormat.format(date);
    }


    public Long asLong(String str){
        if(StringUtils.isEmpty(str)){
            return null;
        }
        return Long.valueOf(str);
    }
}