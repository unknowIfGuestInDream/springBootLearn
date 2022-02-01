package com.tlcsdm.learn.study.status;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author: 唐 亮
 * @date: 2022/2/2 0:03
 * @since: 1.0
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Component
public @interface OrderStatusProcessor {

    /**
     * 状态枚举
     */
    OrderStatusEnum status();
}
