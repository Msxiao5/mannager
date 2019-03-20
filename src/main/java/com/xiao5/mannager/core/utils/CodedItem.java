package com.xiao5.mannager.core.utils;


import org.apache.commons.lang.StringUtils;

/**
 * 通用数据操作类型枚举接口
 * @author Wu Tianbing
 */
public interface CodedItem {
    /**
     * 编码
     * @return
     */
    String getCode();

    /**
     * 编码
     * @param code
     */
    void setCode(String code);

    /**
     * 描述
     * @return
     */
    String getDesc();

    /**
     * 描述
     * @param desc
     */
   void setDesc(String desc);

    /**
     * 根据paramEnum枚举值 code 获取枚举类相应code的枚举
     * @param paramEnum 枚举
     * @param code 枚举
     * @return code相对应的枚举
     */
    static Enum getEnumByCode(Enum paramEnum, String code){

       if(StringUtils.isNotBlank(code) && null != paramEnum){
           for (Object o : paramEnum.getDeclaringClass().getEnumConstants()) {

               if (((CodedItem)o).getCode().equals(code)) {
                   return (Enum)o;
               }
           }

       }

       return null;
   }

    /**
     * 判断paramEnum枚举值 与code获取到的枚举值是否相等
     * @param paramEnum 枚举值
     * @param code code
     * @return 是否相等
     */
    static boolean checkEqualsByEnumAndCode(Enum paramEnum, String code){

        if(StringUtils.isNotBlank(code) && null != paramEnum){
            for (Object o : paramEnum.getDeclaringClass().getEnumConstants()) {

                if (((CodedItem)o).getCode().equals(code)) {
                    return (o).equals(paramEnum);
                }
            }

        }

        return false;
    }
}
