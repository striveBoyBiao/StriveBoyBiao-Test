package com.zizhuling.test.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 登录次数限制
 * @author: hebiao
 * @date: 2022/11/7 16:37
 */
@Retention( RetentionPolicy.RUNTIME )
@Target(ElementType.METHOD)
public @interface AccessLimit {
    /**
     * 时间
     */
    int seconds();
    /**
     * 最大次数
     */
    int maxCount();
    boolean needLogin()default true;
}
