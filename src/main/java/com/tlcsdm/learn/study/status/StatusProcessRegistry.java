package com.tlcsdm.learn.study.status;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 通过BeanPostProcessor实现对bean后置处理，对注解的类型bean对象进行缓存
 *
 * @author: 唐 亮
 * @date: 2022/2/2 0:16
 * @since: 1.0
 */
@Slf4j
@Component
public class StatusProcessRegistry implements BeanPostProcessor {

    /**
     * 第一层key是订单状态
     * 第二层是对应的处理器
     */
    private static Map<Integer, StatusProcessor> statusProcessorMap = new ConcurrentHashMap<>();

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName====>" + beanName);
        if (bean instanceof StatusProcessor && bean.getClass().isAnnotationPresent(OrderStatusProcessor.class)) {
            OrderStatusProcessor annotation = bean.getClass().getAnnotation(OrderStatusProcessor.class);
            OrderStatusEnum orderStatusEnum = annotation.status();
            Integer statusCode = orderStatusEnum.getStatusCode();
            if (!statusProcessorMap.containsKey(statusCode)) {
                statusProcessorMap.put(statusCode, (StatusProcessor) bean);
            }
        }

        return bean;
    }

    public StatusProcessor acquireStatusProcessor(Integer statusCode) {
        return statusProcessorMap.get(statusCode);
    }

}
