package com.xiao5.mannager.core.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import java.util.List;
import java.util.Map;

/**
 * @Description Json 工具类
 * @Author WangJJ
 * @Date 2018/11/7 1:06 PM
 */
public class JsonHelper {

    public static <T> T jsonToObject(String json, Class<T> clazz) {
        return JSONUtil.toBean(json, clazz);
    }

    public static String objectToJson(Object obj) {
        return JSONUtil.toJsonStr(obj);
    }

    public static <T> T objectToBean(Object object, Class<T> clazz) {
        return jsonToObject(objectToJson(object), clazz);
    }

    public static <T> T xmlToObject(String xml, Class<T> clazz) {
        JSONObject jsonObject = JSONUtil.xmlToJson(xml);
        return JSONUtil.toBean(jsonObject, clazz);
    }

    public static <T> List<T> jsonToList(String json, Class<T> clazz) {
        if (StringHelper.isEmpty(json)) {
            return null;
        }
        return JSONUtil.toList(JSONUtil.parseArray(json), clazz);
    }

    public static <T> T parseMap2Object(Map<String, Object> paramMap, Class<T> cls) {
        return JsonHelper.jsonToObject(JsonHelper.objectToJson(paramMap), cls);
    }
}
