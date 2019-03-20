package com.xiao5.mannager.core.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author WuTian Bing
 * @version 1.0
 * @classname ApplicationContextUtil
 * @date 2019/3/20 18:51
 **/
@Component("defaultApplicationContextUtil")
@Lazy(value=false)
@Order(0)
public class ApplicationContextUtil implements ApplicationContextAware {

    // 声明一个静态变量保存
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(final ApplicationContext contex) {
        ApplicationContextUtil.context = contex;
    }

    public static ApplicationContext getContext() {
        return ApplicationContextUtil.context;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(final String beanId) {
        return (T) ApplicationContextUtil.context.getBean(beanId);
    }

    public static boolean containsBean(final String beanId) {
        return ApplicationContextUtil.context.containsBean(beanId);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(final String beanId, Object... args) {
        return (T) ApplicationContextUtil.context.getBean(beanId, args);
    }

    public static <T> T getBean(Class<T> clazz) {
        return ApplicationContextUtil.context.getBean(clazz);
    }


}
