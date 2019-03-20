package com.xiao5.mannager.core.utils;


import org.apache.commons.lang.StringUtils;

/**
 * desc: 枚举通用类
 * Date: 2019/3/19
 *
 * @author Wu Tianbing
 */
public class EnumUtils {
    public static <E extends Enum<E>> E parseCode(Class<E> enumClass, String value) {
        if(StringUtils.isBlank(value)){
            return null;
        }

        if(CodedItem.class.isAssignableFrom(enumClass)) {
            E[] enumValues = enumClass.getEnumConstants();
            for (E enumValue : enumValues) {
                if (((CodedItem) enumValue).getCode().equals(value)) {
                    return enumValue;
                }
            }
        } else {
            try {
                return Enum.valueOf(enumClass, value);
            } catch (IllegalArgumentException err) {
            }
        }

        throw new IllegalArgumentException(
                String.format("\"%s\" is not a valid %s", value, enumClass.getSimpleName())
        );
    }

    public static <E extends Enum<E>> String toString(E value) {
        if(value == null){
            return null;
        }

        if(value instanceof CodedItem) {
            return ((CodedItem)value).getCode();
        }else{
            return value.name();
        }
    }
}

